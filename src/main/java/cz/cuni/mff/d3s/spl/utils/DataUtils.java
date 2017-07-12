package cz.cuni.mff.d3s.spl.utils;

import cz.cuni.mff.d3s.spl.DataSource;
import cz.cuni.mff.d3s.spl.data.Revision;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataUtils {
	public static Map<String, DataSource> getRevisionMap(List<Revision> revisions) {
		Map<String, DataSource> result = new HashMap<>();
		for (Revision rev : revisions) {
			result.put(rev.name, rev.data);
		}
		return result;
	}
}
