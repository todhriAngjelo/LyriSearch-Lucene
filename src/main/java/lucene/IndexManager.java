package lucene;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import model.IndexCreationModeEnum;
import utils.Commons;

public class IndexManager {

	public static void createIndex(boolean deletePreviousIndex, String csvFilepath) {
		if (deletePreviousIndex) {
			deletePreviousIndex(Commons.indexFolder);
		}

		String indexPath = csvFilepath + "\\indexes";
		String csvFilePath = csvFilepath + IndexCreationModeEnum.valueFromMode(Commons.INDEX_FILES_MODE).getFilepath();

		final Path docDir = Paths.get(csvFilePath);
		if (!Files.isReadable(docDir)) {
			System.out.println("Document directory '" + docDir.toAbsolutePath() + "' does not exist or is not readable, please check the path");
			System.exit(1);
		}

		Date start = new Date();
		try {
			System.out.println("Indexing to directory '" + indexPath + "'...");

			Directory directory = FSDirectory.open(Paths.get(indexPath));
			Analyzer analyzer = new StandardAnalyzer();

			IndexWriterConfig config = new IndexWriterConfig(analyzer);
			config.setOpenMode(OpenMode.CREATE);

			IndexWriter writer = new IndexWriter(directory, config);
			indexDocs(writer, docDir);
			writer.close();

			Date end = new Date();
			System.out.println(end.getTime() - start.getTime() + " total milliseconds");

		} catch (IOException e) {
			System.out.println(" caught a " + e.getClass() +
					"\n with message: " + e.getMessage());
		}
	}

	static void indexDocs(final IndexWriter writer, Path path) throws IOException {
		if (Files.isDirectory(path)) {
			Files.walkFileTree(path, new SimpleFileVisitor<>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
					try {
						indexDoc(writer, file);
					} catch (IOException ignore) {
					}
					return FileVisitResult.CONTINUE;
				}
			});
		} else {
			indexDoc(writer, path);
		}

		writer.close();
	}

	static void indexDoc(IndexWriter writer, Path csvFile) throws IOException {
		Reader reader = Files.newBufferedReader(csvFile);
		CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
		for (CSVRecord record : parser) {
			Document document = new Document();
			for (String header : parser.getHeaderNames()) {
				String value = record.get(header);
				IndexableField field = new TextField(header, value, Field.Store.YES);
				document.add(field);
			}
			writer.addDocument(document);
		}
		reader.close();
	}

	public static void deletePreviousIndex(final File folder) {
		if (folder.isDirectory()) {
			File[] files = folder.listFiles();
			if (files != null) {
				for (File file : files) {
					if (file.isDirectory()) {
						deletePreviousIndex(file);
					} else {
						file.delete();
					}
				}
			}
		}
	}
}