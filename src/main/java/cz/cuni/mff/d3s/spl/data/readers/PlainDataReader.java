package cz.cuni.mff.d3s.spl.data.readers;

import cz.cuni.mff.d3s.spl.data.DataInfo;
import cz.cuni.mff.d3s.spl.data.DataSource;
import cz.cuni.mff.d3s.spl.data.Revision;
import cz.cuni.mff.d3s.spl.utils.Factory;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Data reader from plain text formats. See readData() documentation for
 * more info about requested data format, hierarchical structure and revision
 * reader requirements.
 */
public class PlainDataReader<T extends RevisionReader> implements DataReader {
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
	public PlainDataReader(Factory<T> readerFactory) {
		reader = readerFactory.getInstance();
	}

	/**
	 * Reads multiple revision data from files. Each argument is directory containing
	 * multiple files, one file per benchmark run. Runs in each directory are joined
	 * to a single revision. Revision order preserves input arguments order. Revision
	 * name is set as corresponding directory name. RevisionReader of T type is expected
	 * to return map with "default" key and single DataSource value.
	 *
	 * @param args Array of 1 string with path to root directory of measured data.
	 * @return Map with name of measured unit as key and list of revisions for that
	 *          unit as value.
	 */
	@Override
	public Map<DataInfo, List<Revision>> readData(String[] args) throws ReaderException {
		Map<DataInfo, List<Revision>> data = new HashMap<>();
		data.put(DataInfo.defaultInstance, new LinkedList<>());

		for (String dirname : args) {
			File dir = new File(dirname);

			System.out.printf("Reading data from %s...", dirname);
			Map<DataInfo, DataSource> revision = reader.readRevision(dir.listFiles());
			System.out.printf(" ok, %d run(s).\n", revision.get(DataInfo.defaultInstance).makeSnapshot().getRunCount());

			data.get(DataInfo.defaultInstance).add(new Revision(dir.getName(), revision.get(DataInfo.defaultInstance)));
		}

		return data;
	}
}
