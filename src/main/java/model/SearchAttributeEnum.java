package model;

public enum SearchAttributeEnum {

	KEYWORD("Keyword", "lyrics"),
	ARTIST("Singer", "artist"),
	SONG("Song Title", "song_name");

	private final String displayName;
	private final String indexValue;

	SearchAttributeEnum(String displayName, String indexValue) {
		this.displayName = displayName;
		this.indexValue = indexValue;
	}

	public static String valueFromDisplayName(String displayName) {
		for (SearchAttributeEnum searchAttributeEnum : SearchAttributeEnum.values()) {
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
