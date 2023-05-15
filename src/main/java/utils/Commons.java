package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import model.SearchAttributeMode13Enum;
import model.SearchAttributeMode2Enum;

public class Commons {
	public static final File indexFolder = new File("C:\\Users\\a.todhri\\IdeaProjects\\LyriSearch-Lucene\\src\\main\\resources\\indexes");
	public static final String RESOURCES_CLASSPATH = "C:\\Users\\a.todhri\\IdeaProjects\\LyriSearch-Lucene\\src\\main\\resources";
	public static final String INDEX_CLASSPATH = "C:\\Users\\a.todhri\\IdeaProjects\\LyriSearch-Lucene\\src\\main\\resources\\indexes";
	public static final List<String> SEARCH_HISTORY = new ArrayList<>();
	public static final int INDEX_FILES_MODE = 2;

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

	public static String searchAttributeByMode(String searchByComboBoxValue, int mode) {
		return switch (mode) {
			case 1, 3 -> switch (searchByComboBoxValue) {
				case "Keyword" -> SearchAttributeMode13Enum.KEYWORD.getIndexValue();
				case "Singer" -> SearchAttributeMode13Enum.ARTIST.getIndexValue();
				case "Song Title" -> SearchAttributeMode13Enum.SONG.getIndexValue();
				default -> null;
			};
			case 2 -> switch (searchByComboBoxValue) {
				case "Keyword" -> SearchAttributeMode2Enum.KEYWORD.getIndexValue();
				case "Singer" -> SearchAttributeMode2Enum.ARTIST.getIndexValue();
				case "Song Title" -> SearchAttributeMode2Enum.SONG.getIndexValue();
				case "Year" -> SearchAttributeMode2Enum.YEAR.getIndexValue();
				default -> null;
			};
			default -> null;
		};
	}
}
