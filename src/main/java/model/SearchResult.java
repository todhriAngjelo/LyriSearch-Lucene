package model;

public class SearchResult {
	private String songTitle;
	private String singer;
	private String lyrics;

	public SearchResult(String songTitle, String singer, String lyrics) {
		this.songTitle = songTitle;
		this.singer = singer;
		this.lyrics = lyrics;
	}

	public SearchResult() {
	}

	public String getSongTitle() {
		return songTitle;
	}

	public void setSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getLyrics() {
		return lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}
}
