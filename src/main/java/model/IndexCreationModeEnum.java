package model;

public enum IndexCreationModeEnum {

	MODE_1(1, "lyrics.csv", false, "The minified lyrics.csv file with 100 records"),
	MODE_2(2, "/csv", true, "The csv folder with the csv files split by artist"),
	MODE_3(3, "lyrics - full.csv", false, "The full lyrics.csv file with the full lyrics set");

	private final int mode;
	private final String filepath;
	private final boolean isFolder;
	private final String description;

	IndexCreationModeEnum(int mode, String filepath, boolean isFolder, String description) {
		this.mode = mode;
		this.filepath = filepath;
		this.isFolder = isFolder;
		this.description = description;
	}

	public String getFilepath() {
		return filepath;
	}

	public boolean isFolder() {
		return isFolder;
	}

	public String getDescription() {
		return description;
	}

	public int getMode() {
		return mode;
	}

	public static IndexCreationModeEnum valueFromMode(int mode) {
		for (IndexCreationModeEnum indexCreationModeEnum : IndexCreationModeEnum.values()) {
			if (indexCreationModeEnum.mode == mode) {
				return indexCreationModeEnum;
			}
		}
		return null;
	}
}
