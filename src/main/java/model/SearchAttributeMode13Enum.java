package model;

public enum SearchAttributeMode13Enum {

	KEYWORD("Keyword", "lyrics"),
	ARTIST("Singer", "artist"),
	SONG("Song Title", "song_name");

	private final String displayName;
	private final String indexValue;

	SearchAttributeMode13Enum(String displayName, String indexValue) {
		this.displayName = displayName;
		this.indexValue = indexValue;
	}

	public String getIndexValue() {
		return indexValue;
	}

	public String getDisplayName() {
		return displayName;
	}
}
