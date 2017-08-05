package cz.cuni.mff.d3s.spl.data;

/**
 * One data revision.
 */
public class Revision {
	/**
	 * Name of the revision. Could be file name, version control
	 * system hash, etc.
	 */
	public String name;

	/**
	 * Unix timestamp of the revision or 0 if the timestamp is unknown.
	 */
	public long timestamp;

	/**
	 * Data for given revision.
	 */
	public DataSource data;

	/**
	 * Constructor.
	 *
	 * @param n Name of the revision.
	 * @param t Unix timestamp of the revision.
	 * @param d Revision data.
	 */
	public Revision(String n, long t, DataSource d) {
		name = n;
		timestamp = t;
		data = d;
	}

	/**
	 * Constructor with unknown timestamp.
	 *
	 * @param n Name of the revision.
	 * @param d Revision data.
	 */
	public Revision(String n, DataSource d) {
		this(n, 0, d);
	}
}
