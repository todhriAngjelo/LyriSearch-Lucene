package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Commons {
	public static final File indexFolder = new File("C:\\Users\\a.todhri\\IdeaProjects\\LyriSearch-Lucene\\src\\main\\resources\\indexes");
	public static final String RESOURCES_CLASSPATH = "C:\\Users\\a.todhri\\IdeaProjects\\LyriSearch-Lucene\\src\\main\\resources";

	public static final  String INDEX_CLASSPATH = "C:\\Users\\a.todhri\\IdeaProjects\\LyriSearch-Lucene\\src\\main\\resources\\indexes";

	public static final List<String> SEARCH_HISTORY = new ArrayList<>();

	public static String getIndexInitializedStatus() {
		File indexDir = new File(INDEX_CLASSPATH);
		if (indexDir.exists()) {
			return "Index is initialized";
		} else {
			return "Index is not initialized";
		}

	}

	public static boolean indexInitialized() {
		File indexDir = new File(INDEX_CLASSPATH);
		return indexDir.exists();
	}
}
