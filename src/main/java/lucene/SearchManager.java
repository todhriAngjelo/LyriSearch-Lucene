package lucene;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import model.SearchResult;

public class SearchManager {

	private IndexReader reader;
	private IndexSearcher searcher;
	private Analyzer analyzer;
	private static String historyPath = "C:\\Users\\Chryssa\\Documents\\cse\\8osemester\\anakthshPlhroforias\\project\\splitTweetWithTabs\\history\\";
	private static final String INDEX_PATH = "C:\\Users\\a.todhri\\IdeaProjects\\LyriSearch-Lucene\\src\\main\\resources\\indexes";
	private String queries = null;
	private boolean raw = false;
	private String queryString = null;
	private int hitsPerPage = 10;
	private BufferedReader in = null;
	private static HashMap<String, Integer> usernames = new HashMap<String, Integer>();
	private static HashMap<String, Integer> locations = new HashMap<String, Integer>();
	private static HashMap<String, Integer> hashtags = new HashMap<String, Integer>();

	public SearchManager() {
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init() throws IOException {
		reader = DirectoryReader.open(FSDirectory.open(Paths.get(INDEX_PATH)));
		searcher = new IndexSearcher(reader);
		analyzer = new StandardAnalyzer();

		if (queries != null) {
			in = Files.newBufferedReader(Paths.get(queries), StandardCharsets.UTF_8);
		} else {
			in = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
		}
	}

	public static List<SearchResult> searchIndex(String searchTerm, String searchAttribute) throws IOException, ParseException {
		Directory directory = FSDirectory.open(Paths.get(INDEX_PATH));
		IndexReader reader = DirectoryReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(reader);

		QueryParser parser = new QueryParser(searchAttribute, new StandardAnalyzer());
		Query query = parser.parse(searchTerm);

		TopDocs results = searcher.search(query, 10);
		ScoreDoc[] hits = results.scoreDocs;
		System.out.println("Hits: " + hits.length);
		List<SearchResult> resultsList = new ArrayList<>();

		for (int i = 0; i < hits.length; i++) {
			int docId = hits[i].doc;
			Document document = searcher.doc(docId);
			String title = document.get("song_name");
			String artist = document.get("artist");
			String lyrics = document.get("lyrics");

			resultsList.add(new SearchResult(title, artist, lyrics));
		}
		reader.close();

		return resultsList;
	}

	public static List<SearchResult> searchIndex(String[] searchTerms, String searchAttribute) throws IOException, ParseException {
		Directory directory = FSDirectory.open(Paths.get(INDEX_PATH));
		IndexReader reader = DirectoryReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(reader);

		BooleanQuery.Builder queryBuilder = new BooleanQuery.Builder();
		for (String searchTerm : searchTerms) {
			TermQuery termQuery = new TermQuery(new Term(searchAttribute, searchTerm));
			queryBuilder.add(termQuery, BooleanClause.Occur.SHOULD);
		}
		BooleanQuery query = queryBuilder.build();

		TopDocs results = searcher.search(query, 10);
		ScoreDoc[] hits = results.scoreDocs;
		System.out.println("Hits: " + hits.length);
		List<SearchResult> resultsList = new ArrayList<>();

		for (int i = 0; i < hits.length; i++) {
			int docId = hits[i].doc;
			Document document = searcher.doc(docId);
			String title = document.get("song_name");
			String artist = document.get("artist");
			String lyrics = document.get("lyrics");

			resultsList.add(new SearchResult(title, artist, lyrics));
		}
		reader.close();

		return resultsList;
	}

}
