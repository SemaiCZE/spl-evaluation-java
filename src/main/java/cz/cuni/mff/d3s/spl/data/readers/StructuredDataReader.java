package cz.cuni.mff.d3s.spl.data.readers;

import cz.cuni.mff.d3s.spl.data.DataInfo;
import cz.cuni.mff.d3s.spl.data.DataSource;
import cz.cuni.mff.d3s.spl.data.Revision;
import cz.cuni.mff.d3s.spl.utils.Factory;

import java.io.File;
import java.util.*;

/**
 * Data reader from formats like JMH JSON. Generic type provides
 * revision reader from specific format variant.
 */
public class StructuredDataReader<T extends RevisionReader> implements DataReader {
	/**
	 * Revision reader instance.
	 */
	private T reader;

	/**
	 * Constructor which creates revision reader instance.
	 *
	 * @param readerFactory Factory for creating instance of revision reader.
	 *                      The instance must be of the same type as generic
	 *                      type T.
	 */
	public StructuredDataReader(Factory<T> readerFactory) {
		reader = readerFactory.getInstance();
	}

	/**
	 * Reads multiple revision data from files. Requires one argument,
	 * directory where are all revisions, one per file. Revision name
	 * is set as corresponding filename. Each revision file should contain
	 * data for all measured benchmarks/methods/... with names. RevisionReader
	 * of T type should preserve these names. Revisions are ordered by their
	 * filesystem modification time.
	 *
	 * @param args Array of 1 string with path to root directory of measured data.
	 * @return Map with name of measured unit as key and list of revisions for that
	 *          unit as value.
	 */
	@Override
	public Map<DataInfo, List<Revision>> readData(String[] args) throws ReaderException {
		if (args.length != 1) {
			throw new ReaderException("Invalid number of arguments - expected: 1, provided: " + args.length);
		}

		Map<DataInfo, List<Revision>> data = new HashMap<>();

		File dir = new File(args[0]);
		File[] files = dir.listFiles();
		if (files == null) {
			throw new ReaderException("No files could be fetched from directory " + dir.getName());
		}

		Arrays.sort(files, new FileComparator());

		for (File file : files) {
			System.out.printf("Reading data from %s revision...", file.getName());

			Map<DataInfo, DataSource> revisionData = new HashMap<>();
			if (file.isDirectory()) {
				File[] subfiles = file.listFiles();
				if (subfiles != null && subfiles.length > 0) {
					Arrays.sort(subfiles, new FileComparator());
					revisionData = reader.readRevision(subfiles);
				}
			} else {
				revisionData = reader.readRevision(file);
			}

			long timestamp = getTimestampFromName(file.getName());

			for (Map.Entry<DataInfo, DataSource> benchmark : revisionData.entrySet()) {
				if (!data.containsKey(benchmark.getKey())) {
					data.put(benchmark.getKey(), new ArrayList<>());
				}
				data.get(benchmark.getKey()).add(new Revision(file.getName(), timestamp, benchmark.getValue()));
			}

			System.out.println(" ok");
		}

		return data;
	}

	/**
	 * Function for parsing strings in format v-<timestamp>-<identifier>,
	 * for example 'v-1501159669-7649a1c363f58f732b0503130ea93f0ef0719e15'
	 * @param name String to be parsed
	 * @return Parsed timestamp or 0 on parsing errors
	 */
	private static long getTimestampFromName(String name) {
		// trim the leading 'v-' sequence
		String prefix = "v-";
		if (name.startsWith(prefix)) {
			name = name.substring(prefix.length());
		}

		long timestamp = 0;
		int dashIndex = name.indexOf('-');
		if (dashIndex == -1) {
			return timestamp;
		}
		String namePrefix = name.substring(0, dashIndex);
		try {
			timestamp = Long.parseLong(namePrefix);
		} catch (NumberFormatException ignored) {}

		return timestamp;
	}

	/**
	 * Compare files by timestamps parsed from their names. When the timestamps are equal,
	 * compare lexicographically their names.
	 */
	private static class FileComparator implements Comparator<File> {
		@Override
		public int compare(File x, File y) {
			long xTimestamp = getTimestampFromName(x.getName());
			long yTimestamp = getTimestampFromName(y.getName());

			long timestampDiff = xTimestamp - yTimestamp;
			if (timestampDiff != 0) {
				return timestampDiff > 0 ? 1 : -1;
			} else {
				return x.getName().compareTo(y.getName());
			}
		}
	}
}
