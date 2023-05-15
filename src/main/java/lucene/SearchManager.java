package lucene;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

import model.SearchAttributeMode2Enum;
import model.SearchResult;
import utils.Commons;

public class SearchManager {

	private static final String INDEX_PATH = "C:\\Users\\a.todhri\\IdeaProjects\\LyriSearch-Lucene\\src\\main\\resources\\indexes";

	public static long searchIndexTotalResults(String searchTerm, String searchAttribute) {
		try {
			Directory directory = FSDirectory.open(Paths.get(INDEX_PATH));
			IndexReader reader = DirectoryReader.open(directory);
			IndexSearcher searcher = new IndexSearcher(reader);

			QueryParser parser = new QueryParser(searchAttribute, new StandardAnalyzer());
			Query query = parser.parse(searchTerm);

			return searcher.search(query, 1).totalHits.value;
		} catch (IOException | ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static long searchIndexGroupedByYearTotalResults(String searchTerm, String searchAttribute, String year) {
		try {
			Directory directory = FSDirectory.open(Paths.get(INDEX_PATH));
			IndexReader reader = DirectoryReader.open(directory);
			IndexSearcher searcher = new IndexSearcher(reader);

			QueryParser parser = new QueryParser(searchAttribute, new StandardAnalyzer());
			QueryParser yearParser = new QueryParser(Commons.searchAttributeByMode(SearchAttributeMode2Enum.YEAR.getIndexValue(), Commons.INDEX_FILES_MODE), new StandardAnalyzer());

			// Create a BooleanQuery to combine the two search terms
			BooleanQuery.Builder queryBuilder = new BooleanQuery.Builder();

			Query query1 = parser.parse(searchTerm);
			Query query2 = yearParser.parse(String.valueOf(year));

			// Add the search terms to the BooleanQuery
			queryBuilder.add(query1, BooleanClause.Occur.MUST);
			queryBuilder.add(query2, BooleanClause.Occur.MUST);

			Query query = queryBuilder.build();

			return searcher.search(query, 10).totalHits.value;
		} catch (IOException | ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static List<SearchResult> searchIndex(String searchTerm, String searchAttribute, int pageNumber, int pageSize) throws IOException, ParseException {
		Directory directory = FSDirectory.open(Paths.get(INDEX_PATH));
		IndexReader reader = DirectoryReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(reader);

		QueryParser parser = new QueryParser(searchAttribute, new StandardAnalyzer());
		Query query = parser.parse(searchTerm);

		TopDocs results;
		ScoreDoc[] hits;
		List<SearchResult> resultsList = new ArrayList<>();

		if (pageNumber == 1) {
			results = searcher.search(query, pageSize);
			hits = results.scoreDocs;
		} else {
			int previousPageEnd = (pageNumber - 1) * pageSize;
			ScoreDoc lastScoreDoc = getLastScoreDoc(searcher, query, previousPageEnd);

			if (lastScoreDoc != null) {
				results = searcher.searchAfter(lastScoreDoc, query, pageSize);
				hits = results.scoreDocs;
			} else {
				hits = new ScoreDoc[0];
			}
		}

		for (int i = 0; i < hits.length; i++) {
			int docId = hits[i].doc;
			addDocumentToList(searcher, resultsList, docId);
		}

		reader.close();

		return resultsList;
	}

	public static List<SearchResult> searchIndexGroupByYear(String searchTerm1, String year, String searchAttribute, int pageNumber, int pageSize) {
		try {
			Directory directory = FSDirectory.open(Paths.get(INDEX_PATH));
			IndexReader reader = DirectoryReader.open(directory);
			IndexSearcher searcher = new IndexSearcher(reader);

			QueryParser parser = new QueryParser(searchAttribute, new StandardAnalyzer());
			QueryParser yearParser = new QueryParser(Commons.searchAttributeByMode(SearchAttributeMode2Enum.YEAR.getIndexValue(), Commons.INDEX_FILES_MODE), new StandardAnalyzer());

			// Create a BooleanQuery to combine the two search terms
			BooleanQuery.Builder queryBuilder = new BooleanQuery.Builder();

			Query query1 = parser.parse(searchTerm1);
			Query query2 = yearParser.parse(String.valueOf(year));

			// Add the search terms to the BooleanQuery
			queryBuilder.add(query1, BooleanClause.Occur.MUST);
			queryBuilder.add(query2, BooleanClause.Occur.MUST);

			Query query = queryBuilder.build();

			TopDocs results;
			ScoreDoc[] hits;
			List<SearchResult> resultsList = new ArrayList<>();

			if (pageNumber == 1) {
				results = searcher.search(query, pageSize);
				hits = results.scoreDocs;
			} else {
				int previousPageEnd = (pageNumber - 1) * pageSize;
				ScoreDoc lastScoreDoc = getLastScoreDoc(searcher, query, previousPageEnd);

				if (lastScoreDoc != null) {
					results = searcher.searchAfter(lastScoreDoc, query, pageSize);
					hits = results.scoreDocs;
				} else {
					hits = new ScoreDoc[0];
				}
			}

			for (ScoreDoc hit : hits) {
				int docId = hit.doc;
				addDocumentToList(searcher, resultsList, docId);
			}

			reader.close();

			return resultsList;

		} catch (IOException | ParseException e) {
			throw new RuntimeException(e);
		}

	}

	private static void addDocumentToList(IndexSearcher searcher, List<SearchResult> resultsList, int docId) throws IOException {
		Document document = searcher.doc(docId);
		String title = document.get(Commons.searchAttributeByMode("Song Title", Commons.INDEX_FILES_MODE));
		String artist = document.get(Commons.searchAttributeByMode("Singer", Commons.INDEX_FILES_MODE));
		String lyrics = document.get(Commons.searchAttributeByMode("Keyword", Commons.INDEX_FILES_MODE));

		resultsList.add(new SearchResult(title, artist, lyrics));
	}

	private static ScoreDoc getLastScoreDoc(IndexSearcher searcher, Query query, int numResults) throws IOException {
		TopDocs results = searcher.search(query, numResults);
		ScoreDoc[] hits = results.scoreDocs;

		if (hits.length > 0) {
			return hits[hits.length - 1];
		}

		return null;
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
			addDocumentToList(searcher, resultsList, docId);
		}
		reader.close();

		return resultsList;
	}
}