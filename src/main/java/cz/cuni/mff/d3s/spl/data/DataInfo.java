package cz.cuni.mff.d3s.spl.data;


import java.util.Map;

public class DataInfo {
	private String id;
	private String name = null;
	private Map<String, String> metadata = null;
	public static DataInfo defaultInstance = new DataInfo("default");

	public DataInfo(String id) {
		this(id, null, null);
	}

	public DataInfo(String id, String name) {
		this(id, name, null);
	}

	public DataInfo(String id, String name, Map<String, String> metadata) {
		this.id = id;
		this.name = name;
		this.metadata = metadata;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<String, String> metadata) {
		this.metadata = metadata;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null) return false;
		if (other == this) return true;
		if (!(other instanceof DataInfo))return false;
		DataInfo otherDataInfo = (DataInfo)other;
		return otherDataInfo.id.equals(this.id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
