package model;

public enum SearchAttributeMode2Enum {

	KEYWORD("Keyword", "Lyric"),
	ARTIST("Singer", "Artist"),
	SONG("Song Title", "Title"),
	YEAR("Release Year", "Year");

	private final String displayName;
	private final String indexValue;

	SearchAttributeMode2Enum(String displayName, String indexValue) {
		this.displayName = displayName;
		this.indexValue = indexValue;
	}

	public static String valueFromDisplayName(String displayName) {
		for (SearchAttributeMode2Enum searchAttributeEnum : SearchAttributeMode2Enum.values()) {
			if (searchAttributeEnum.displayName.equals(displayName)) {
				return searchAttributeEnum.indexValue;
			}
		}
		return null;
	}

	public String getIndexValue() {
		return indexValue;
	}
}
