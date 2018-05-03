package cz.cuni.mff.d3s.spl;


import cz.cuni.mff.d3s.spl.data.DataInfo;
import cz.cuni.mff.d3s.spl.data.DataSource;
import cz.cuni.mff.d3s.spl.data.Revision;
import cz.cuni.mff.d3s.spl.data.readers.DataReader;
import cz.cuni.mff.d3s.spl.data.readers.JmhJsonRevisionReader;
import cz.cuni.mff.d3s.spl.data.readers.StructuredDataReader;
import cz.cuni.mff.d3s.spl.formula.Formula;
import cz.cuni.mff.d3s.spl.formula.SplFormula;
import cz.cuni.mff.d3s.spl.interpretation.WelchTestInterpretation;
import cz.cuni.mff.d3s.spl.restapi.CorsInterceptor;
import cz.cuni.mff.d3s.spl.restapi.TestsApi;
import cz.cuni.mff.d3s.spl.restapi.factories.TestsApiServiceFactory;
import cz.cuni.mff.d3s.spl.restapi.impl.TestsApiServiceImpl;
import cz.cuni.mff.d3s.spl.utils.DataUtils;
import cz.cuni.mff.d3s.spl.visualization.WebVisualizer;
import org.apache.commons.cli.*;
import org.wso2.msf4j.MicroservicesRunner;

import java.awt.*;
import java.io.*;
import java.net.JarURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;


public class Main {
	private static final double SIGNIFICANCE_LEVEL = 0.95;

	public static void main(String[] args) {
		CommandLineParser parser = new DefaultParser();
		Options options = createOptions();

		try {
			CommandLine line = parser.parse(options, args);

			String jarFormulas = line.getOptionValue("jar-formulas");
			String fileFormulas = line.getOptionValue("file-formulas");
			String[] cmdFormulas = line.getOptionValues("commandline-formulas");
			String dataDir = line.getOptionValue("data-dir");
			String revisionMapping = line.getOptionValue("revision-mapping");
			boolean printUnknownOnly = line.hasOption("print-unknown");
			boolean startServer = line.hasOption("server");

			// if not set, data directory is default location in ./data dir from current JAR location
			if (dataDir == null || dataDir.isEmpty()) {
				URI fullPath = Main.class.getResource("").toURI();
				JarURLConnection connection = (JarURLConnection) fullPath.toURL().openConnection();
				File jarDir = new File(connection.getJarFileURL().toURI());
				dataDir = Paths.get(jarDir.getPath(), "..", "data").normalize().toString();

				System.out.printf("Used data directory: %s\n", dataDir);
			}


			// Formulas are processed in order jar, file and command line.
			// Latter options have higher priority and will override previous values.
			// Pair of FQ benchmark name and corresponding SPL formula.
			Map<String, String> formulas = new HashMap<>();
			readJarFormulas(formulas, jarFormulas);
			readFileFormulas(formulas, fileFormulas);
			readCommandlineFormulas(formulas, cmdFormulas);

			DataReader reader = new StructuredDataReader<>(new JmhJsonRevisionReader.RevisionFactory());
			Map<DataInfo, List<Revision>> data = reader.readData(new String[]{dataDir});
			System.out.println();

			if (startServer) {
				// Start REST API server to allow fetching data from visualization tools
				TestsApiServiceFactory.setService(new TestsApiServiceImpl(data));
				new MicroservicesRunner(42000)
						.addInterceptor(new CorsInterceptor())
						.deploy(new TestsApi())
						.deploy(new WebVisualizer())
						.start();
				if (Desktop.isDesktopSupported()) {
					Desktop.getDesktop().browse(new URI("http://localhost:42000/index.html"));
				}
			} else {
				// Get custom mapping of revisions form file.
				Map<String, String> customRevisionMap = getCustomRevisionMapping(revisionMapping);

				// Flag if there are unknown versions and user should use 'print-unknown' option to show them
				boolean unknownVersionsPresent = false;

				for (Map.Entry<DataInfo, List<Revision>> benchmarkItem : data.entrySet()) {
					String benchmarkName = benchmarkItem.getKey().getId();
					String formulaString = null;

					// Wildcard method identifier for all methods
					if (formulas.containsKey("*")) {
						formulaString = formulas.get("*");
					}

					// Method fully qualified name without additional params
					String benchmarkBasename = benchmarkName.substring(0, benchmarkName.indexOf('@'));
					if (formulas.containsKey(benchmarkBasename)) {
						formulaString = formulas.get(benchmarkBasename);
					}

					// Method fully qualified name including additional info like measurement mode or execution params
					if (formulas.containsKey(benchmarkName)) {
						formulaString = formulas.get(benchmarkName);
					}

					if (formulaString == null) {
						unknownVersionsPresent = true;
						if (printUnknownOnly) {
							System.out.printf("Benchmark: %s\n\tno formula, skipping...\n", benchmarkName);
						}
						continue;
					}

					Formula formula = SplFormula.create(formulaString);
					formula.setInterpretation(new WelchTestInterpretation());

					// get benchmark's revisions in better format for us
					Map<String, DataSource> revisionMap = DataUtils.getRevisionMap(benchmarkItem.getValue());
					boolean isUnknownForFormula = false;
					// Bind variables to formula (according to revisions collection)
					for (String variable : formula.getVariables()) {
						// try if there is real revision of that name
						if (revisionMap.containsKey(variable)) {
							formula.bind(variable, revisionMap.get(variable));
						}
						// else try to find another revision is custom mapping
						else if (customRevisionMap.containsKey(variable)) {
							formula.bind(variable, revisionMap.get(customRevisionMap.get(variable)));
						}
						// else there is no such revision
						else {
							if (printUnknownOnly) {
								System.out.printf("Benchmark: %s\n\tformula: %s\n\tunknown version: %s\n", benchmarkName,
										formulaString, variable);
							}
							unknownVersionsPresent = true;
							isUnknownForFormula = true;
						}
					}

					// if we want only get missing versions, skip formula evaluating
					// (or if there are some missing ones, evaluation make no sense)
					if (!printUnknownOnly && !isUnknownForFormula) {
						Result result = formula.evaluate(SIGNIFICANCE_LEVEL);
						System.out.printf("Benchmark: %s\n\tformula: %s\n\tresult: %s\n", benchmarkName, formulaString,
								result.toString().toUpperCase());
					}
				}

				// when there are unknown versions and we are not displaying them, show the warning
				if (unknownVersionsPresent && !printUnknownOnly) {
					System.out.println("\nWARNING: Some formulas refer to unknown versions! To show them use '-p' option");
				}
			}
		} catch (ParseException e) {
			// oops, something went wrong
			System.err.println("Parsing failed. Reason: " + e.getMessage());
			printHelp(options);
		} catch (DataReader.ReaderException e) {
			System.err.println("Cannot read measured data. Reason: " + e.getMessage());
		} catch (IOException | URISyntaxException e) {
			System.err.println("Cannot open your default browser. Reason: " + e.getMessage());
		}
	}

	private static void readJarFormulas(Map<String, String> formulas, String jarFormulas) {
		if (jarFormulas != null) {
			try {
				File jarFile = new File(jarFormulas);
				URL url = new URL("jar:file:" + jarFile.getAbsolutePath() + "!/META-INF/SPLFormulas");
				InputStream is = url.openStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				String line;
				List<String> lines = new ArrayList<>();
				while ((line = reader.readLine()) != null) {
					lines.add(line);
				}
				parseStringsIntoMap(lines, formulas);
			} catch (IOException | NullPointerException e) {
				System.err.println("Cannot read SPL formulas from JAR, assuming no formulas.");
			}
		}
	}

	private static void readFileFormulas(Map<String, String> formulas, String fileFormulas) {
		try {
			List<String> lines = Files.readAllLines(Paths.get(fileFormulas), StandardCharsets.UTF_8);
			parseStringsIntoMap(lines, formulas);
		} catch (IOException e) {
			System.err.println("Cannot read SPL formulas from file, assuming empty one.");
		} catch (NullPointerException e) {
			// there is no such formula file
		}
	}

	private static void readCommandlineFormulas(Map<String, String> formulas, String[] cmdFormulas) {
		if (cmdFormulas != null) {
			parseStringsIntoMap(cmdFormulas, formulas);
		}
	}

	private static void parseStringsIntoMap(String[] data, Map<String, String> output) {
		for (String line : data) {
			// skip empty lines and comments
			if (line.equals("") || line.startsWith("#")) {
				continue;
			}
			String[] parsed = line.split(":");
			if (parsed.length == 2) {
				// save only well formed lines
				output.put(parsed[0], parsed[1]);
			} else {
				System.err.println("Malformed line '" + line + "'. Exactly one ':' expected.");
			}
		}
	}

	private static void parseStringsIntoMap(Collection<String> data, Map<String, String> output) {
		parseStringsIntoMap(data.toArray(new String[] {}), output);
	}

	private static Map<String, String> getCustomRevisionMapping(String revisionsMappingFile) {
		List<String> lines;
		try {
			lines = Files.readAllLines(Paths.get(revisionsMappingFile), StandardCharsets.UTF_8);
			Map<String, String> result = new HashMap<>();
			parseStringsIntoMap(lines, result);
			return result;
		} catch (IOException e) {
			System.err.println("Cannot read mapping file, assuming empty one.");
		} catch (NullPointerException e) {
			// there is no such mapping file, return empty mapping
		}
		return new HashMap<>();
	}

	private static void printHelp(Options options) {
		String header = "Evaluate measured data against SPL formulas.";
		String footer = "";
		HelpFormatter helpFormatter = new HelpFormatter();
		helpFormatter.printHelp("spl-evaluation-java", header, options, footer, true);
	}

	private static Options createOptions() {
		Options options = new Options();

		options.addOption(Option.builder("j")
				.longOpt("jar-formulas")
				.hasArg()
				.argName("benchmarks.jar")
				.desc("Read SPL formulas from JAR file.")
				.build()
		);

		options.addOption(Option.builder("f")
				.longOpt("file-formulas")
				.hasArg()
				.argName("formula_file")
				.desc("Read SPL formulas from text file.")
				.build()
		);

		options.addOption(Option.builder("c")
				.longOpt("commandline-formulas")
				.hasArgs()
				.argName("formulas")
				.desc("Read SPL formulas from commandline arguments.")
				.build()
		);

		options.addOption(Option.builder("d")
				.longOpt("data-dir")
				.hasArg()
				.argName("data_directory")
				.desc("Path to directory with measured data, defaults to 'data/'.")
				.build()
		);

		options.addOption(Option.builder("r")
				.longOpt("revision-mapping")
				.hasArg()
				.argName("mapping_file")
				.desc("Mapping of formula revisions to file names.")
				.build()
		);

		options.addOption(Option.builder("p")
				.longOpt("print-unknown")
				.desc("Print unknown revisions and exit.")
				.build()
		);

		options.addOption(Option.builder("S")
				.longOpt("server")
				.desc("Start local server for data visualization.")
				.build()
		);

		return options;
	}
}
