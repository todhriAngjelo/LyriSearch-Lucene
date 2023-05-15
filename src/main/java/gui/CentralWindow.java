package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.lucene.queryparser.classic.ParseException;

import javax.swing.*;
import lucene.IndexManager;
import lucene.SearchManager;
import model.SearchAttributeEnum;
import model.SearchResult;
import utils.Commons;

public class CentralWindow extends JFrame implements ActionListener {

	private final JTextField searchField;
	private final JComboBox<String> searchByComboBox;
	private final JButton searchButton;
	private final JButton recommendedSongButton;
	private final JButton indexButton;
	private final JLabel indexStatusLabel;

	public CentralWindow() {
		// Add GUI components to the window
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());

		// Set window properties
		setTitle("Lyrics Search");
		setSize(800, 400);
		setLocationRelativeTo(null); // center the window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImageIcon logoIcon = new ImageIcon("src/main/resources/LyriSearch header image.PNG");
		Image logoImage = logoIcon.getImage();
		int logoWidth = (int) Math.min(0.6 * getWidth(), 0.6 * getHeight()); // calculate logo width based on window size
		Image resizedLogoImage = logoImage.getScaledInstance(logoWidth, -1, Image.SCALE_SMOOTH);
		ImageIcon resizedLogoIcon = new ImageIcon(resizedLogoImage);
		JLabel logoLabel = new JLabel(resizedLogoIcon);

		GridBagConstraints logoGridBag = new GridBagConstraints();
		logoGridBag.gridx = 2;
		logoGridBag.gridy = 1;
		logoGridBag.insets = new Insets(10, 10, 10, 10);
		contentPane.add(logoLabel, logoGridBag);

		JLabel titleLabel = new JLabel("Lyrics Search");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		GridBagConstraints titleGridBag = new GridBagConstraints();
		titleGridBag.gridx = 2;
		titleGridBag.gridy = 0;  // Adjust the y-coordinate to change the position
		titleGridBag.insets = new Insets(10, 10, 10, 10);
		contentPane.add(titleLabel, titleGridBag);

		recommendedSongButton = new JButton("Recommended song for you");
		indexButton = new JButton("Index ( only for the first time )");
		indexStatusLabel = new JLabel(Commons.getIndexInitializedStatus()); // check if index is initiated


		searchField = new JTextField(20);
		GridBagConstraints searchFieldGridBag = new GridBagConstraints();
		searchFieldGridBag.gridx = 2;
		searchFieldGridBag.gridy = 2;
		searchFieldGridBag.fill = GridBagConstraints.HORIZONTAL;
		searchFieldGridBag.weightx = 1.0;
		searchFieldGridBag.insets = new Insets(10, 0, 10, 10);
		contentPane.add(searchField, searchFieldGridBag);

		searchLabel(contentPane);

		searchBy(contentPane);

		searchByComboBox = new JComboBox<>(new DefaultComboBoxModel<>(new String[]{"Keyword", "Song Title", "Singer"}));
		searchButton = new JButton("Search");
		searchButton.setPreferredSize(new Dimension(120, 40));
		searchByComboBox(contentPane);

		searchButton(contentPane);

		indexButton(contentPane);

		indexStatusLabel(contentPane);

		recommendedSongButton(contentPane);

		// Add action listeners to buttons
		searchButton.addActionListener(this);
		recommendedSongButton.addActionListener(this);
		indexButton.addActionListener(this);

		helpButton(contentPane);

	}

	private void searchByComboBox(Container contentPane) {
		GridBagConstraints searchByComboBoxGridBag = new GridBagConstraints();
		searchByComboBoxGridBag.gridx = 5;
		searchByComboBoxGridBag.gridy = 2;
		searchByComboBoxGridBag.anchor = GridBagConstraints.WEST;
		searchByComboBoxGridBag.insets = new Insets(10, 0, 10, 10);
		contentPane.add(searchByComboBox, searchByComboBoxGridBag);
	}

	private void searchBy(Container contentPane) {
		GridBagConstraints searchByGridBag = new GridBagConstraints();
		// Search by combo box
		searchByGridBag.gridx = 4;
		searchByGridBag.gridy = 2;
		searchByGridBag.anchor = GridBagConstraints.EAST;
		searchByGridBag.insets = new Insets(10, 10, 10, 10);
		contentPane.add(new JLabel("Search by:"), searchByGridBag);
	}

	private void searchLabel(Container contentPane) {
		JLabel searchLabel = new JLabel("Search:");
		GridBagConstraints searchlabelGridBag = new GridBagConstraints();
		// Search label
		searchlabelGridBag.gridx = 1;
		searchlabelGridBag.gridy = 2;
		searchlabelGridBag.gridwidth = 1;
		searchlabelGridBag.insets = new Insets(10, 10, 10, 10);
		searchlabelGridBag.anchor = GridBagConstraints.EAST;
		contentPane.add(searchLabel, searchlabelGridBag);
	}

	private void searchButton(Container contentPane) {
		GridBagConstraints searchButtonGridBag = new GridBagConstraints();
		searchButtonGridBag.gridx = 3;
		searchButtonGridBag.gridy = 2;
		searchButtonGridBag.anchor = GridBagConstraints.CENTER;
		searchButtonGridBag.insets = new Insets(10, 10, 10, 10);
		contentPane.add(searchButton, searchButtonGridBag);
	}

	private void indexButton(Container contentPane) {
		GridBagConstraints indexButtonGridBag = new GridBagConstraints();
		indexButtonGridBag.gridx = 2;
		indexButtonGridBag.gridy = 3;
		indexButtonGridBag.gridwidth = 1;
		indexButtonGridBag.fill = GridBagConstraints.HORIZONTAL;
		indexButtonGridBag.insets = new Insets(10, 10, 10, 10);
		contentPane.add(indexButton, indexButtonGridBag);
	}

	private void recommendedSongButton(Container contentPane) {
		GridBagConstraints recommnededSongButtonGridBag = new GridBagConstraints();
		recommnededSongButtonGridBag.gridx = 3;
		recommnededSongButtonGridBag.gridy = 3;
		recommnededSongButtonGridBag.gridwidth = 2;
		recommnededSongButtonGridBag.fill = GridBagConstraints.HORIZONTAL;
		recommnededSongButtonGridBag.insets = new Insets(10, 10, 10, 10);
		contentPane.add(recommendedSongButton, recommnededSongButtonGridBag);
	}

	private void indexStatusLabel(Container contentPane) {
		GridBagConstraints indexStatusLabelGridBag = new GridBagConstraints();
		// Index status label
		indexStatusLabelGridBag.gridx = 2;
		indexStatusLabelGridBag.gridy = 4;
		contentPane.add(indexStatusLabel, indexStatusLabelGridBag);
	}

	private void helpButton(Container contentPane) {
		JButton helpButton = new JButton("Help ?");
		helpButton.addActionListener(e -> JOptionPane.showMessageDialog(CentralWindow.this, "Usage details:\n\n" +
				"1. Store the CSV file containing the lyrics data in the root directory of the project.\n" +
				"2. Click the 'Index' button to initialize the search index.\n" +
				"3. Select the search criteria from the 'Search by' dropdown.\n" +
				"4. Enter the search keyword in the search field.\n" +
				"5. Click the 'Search' button to perform the search.\n" +
				"6. Use the 'History' button to view previous search results.", "Usage Details", JOptionPane.INFORMATION_MESSAGE));

		GridBagConstraints helpButtonGridBag = new GridBagConstraints(); // Help button
		helpButtonGridBag.gridx = 5;
		helpButtonGridBag.gridy = 3;
		helpButtonGridBag.anchor = GridBagConstraints.EAST;
		helpButtonGridBag.insets = new Insets(10, 0, 10, 10);
		contentPane.add(helpButton, helpButtonGridBag);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchButton) {
			String searchTerm = searchField.getText();
			String searchBy = (String) searchByComboBox.getSelectedItem();

			if (!Commons.indexInitialized()) {
				JOptionPane.showMessageDialog(this, "Please initialize the index first.");
				return;
			}

			if (searchTerm.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please enter a word to search for.");
				return;
			}

			// Perform the search and display the results
			List<SearchResult> results;
			try {
				results = SearchManager.searchIndex(searchTerm, SearchAttributeEnum.valueFromDisplayName(searchBy));
			} catch (IOException | ParseException ex) {
				throw new RuntimeException(ex);
			}

			if (results.isEmpty()) {
				JOptionPane.showMessageDialog(this, "No results found.");
				return;
			}

			if (!Commons.SEARCH_HISTORY.contains(searchTerm)) {
				Commons.SEARCH_HISTORY.add(searchTerm);
			}

			int pageSize = 10;
			final int[] currentPage = {1};

			// Display the first page of results
			displayResults(searchTerm, results, currentPage[0], pageSize, null);

		} else if (e.getSource() == recommendedSongButton) {
			if (Commons.SEARCH_HISTORY.isEmpty()) {
				JOptionPane.showMessageDialog(this, "No search history available.");
				return;
			}

			String[] searchTerms = Commons.SEARCH_HISTORY.toArray(new String[0]);

			try {
				List<SearchResult> results = SearchManager.searchIndex(searchTerms, SearchAttributeEnum.KEYWORD.getIndexValue());
				if (results.isEmpty()) {
					JOptionPane.showMessageDialog(this, "No results found for the combined search terms.");
				} else {
					SearchResult mostFrequentSong = findMostFrequentSong(results);
					if (mostFrequentSong != null) {
						String songTitle = mostFrequentSong.getSongTitle();
						String artist = mostFrequentSong.getSinger();
						String youtubeLink = "https://www.youtube.com/results?search_query=" + songTitle + " " + artist + " official video";

						String message = "Recommended Song based on your search results:<br>";
						message += "- Title: <a href=\"" + youtubeLink + "\">" + songTitle + "</a><br>";
						message += "- Artist: " + artist + "<br><br>";
						message += "Lyrics:<br>";
						message += mostFrequentSong.getLyrics()
								.replaceAll("(?m)^\\s+", "") // remove all leading whitespace
								.replaceAll("(?m)$", "<br/>");

						JLabel messageLabel = new JLabel("<html>" + message + "</html>");

						messageLabel.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
									try {
										String encodedQuery = URLEncoder.encode(songTitle + " " + artist + " official video", StandardCharsets.UTF_8);
										String youtubeURL = "https://www.youtube.com/results?search_query=" + encodedQuery;
										URI uri = new URI(youtubeURL);
										Desktop.getDesktop().browse(uri);
									} catch (Exception ex) {
										ex.printStackTrace();
									}
								}
							}
						});

						JOptionPane.showMessageDialog(null, messageLabel, "Most Frequent Song", JOptionPane.PLAIN_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "No songs found for the combined search terms.", "Most Frequent Song", JOptionPane.PLAIN_MESSAGE);
					}
				}
			} catch (IOException | ParseException ex) {
				throw new RuntimeException(ex);
			}
		} else if (e.getSource() == indexButton) {
			if (Commons.indexInitialized()) {
				int option = JOptionPane.showConfirmDialog(this, "Do you want to re-initialize the index?", "Confirm",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (option == JOptionPane.YES_OPTION) {
					IndexManager.createIndex(true, Commons.RESOURCES_CLASSPATH);
					indexStatusLabel.setText("Index re-initialized");
				}
			} else {
				IndexManager.createIndex(true, Commons.RESOURCES_CLASSPATH);
				indexStatusLabel.setText("Index initialized");
			}
		}
	}

	private SearchResult findMostFrequentSong(List<SearchResult> results) {
		Map<String, Integer> songOccurrences = new HashMap<>();
		for (SearchResult result : results) {
			String songTitle = result.getSongTitle();
			songOccurrences.put(songTitle, songOccurrences.getOrDefault(songTitle, 0) + 1);
		}
		int maxOccurrences = 0;
		SearchResult mostFrequentSong = null;
		for (SearchResult result : results) {
			String songTitle = result.getSongTitle();
			int occurrences = songOccurrences.get(songTitle);
			if (occurrences > maxOccurrences) {
				maxOccurrences = occurrences;
				mostFrequentSong = result;
			}
		}
		return mostFrequentSong;
	}

//	private void displayResults(String searchTerm, List<SearchResult> results, int currentPage, int pageSize, JFrame previousDialog) {
//		// Create a dialog to hold the result scroll pane and pagination controls
//		JFrame dialog = new JFrame();
//		dialog.setTitle("Search Results - Page " + currentPage);
//		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//		dialog.setLayout(new BorderLayout());
//		dialog.pack();
//		dialog.setLocationRelativeTo(null); // Center the dialog on the screen
//		dialog.setVisible(true);
//
//		if (previousDialog != null) {
//			previousDialog.dispose();
//		}
//
//		int numResults = results.size();
//		int numPages = (int) Math.ceil((double) numResults / pageSize);
//		int startIndex = (currentPage - 1) * pageSize;
//		int endIndex = Math.min(startIndex + pageSize, numResults);
//		List<SearchResult> pageResults = results.subList(startIndex, endIndex);
//
//		StringBuilder resultText = new StringBuilder("<html><body>");
//		for (int i = 0; i < pageResults.size(); i++) {
//			SearchResult result = pageResults.get(i);
//			String songTitle = result.getSongTitle().replaceAll("(?i)" + Pattern.quote(searchTerm), "<b style='font-weight: bold;'>$0</b>");
//			String singer = result.getSinger().replaceAll("(?i)" + Pattern.quote(searchTerm), "<b style='font-weight: bold;'>$0</b>");
//			String lyrics = result.getLyrics()
//					.replaceAll("(?i)" + Pattern.quote(searchTerm), "<b style='font-weight: bold;'>$0</b>")
//					.replaceAll("(?m)^\\s+", "") // remove all leading whitespace
//					.replaceAll("(?m)$", "<br/>"); // replace line ends with <br/>
//
//			String bgColor = i % 2 == 0 ? "#f2f2f2" : "white";
//			resultText.append("<div style='background-color:").append(bgColor).append(";'>");
//			resultText.append("<p><i>").append(singer).append(" - </i> <i>").append(songTitle).append("</i></p><p>").append(lyrics).append("</p><hr/>");
//			resultText.append("</div>");
//		}
//		resultText.append("</body></html>");
//
//		JEditorPane resultEditorPane = new JEditorPane();
//		resultEditorPane.setContentType("text/html");
//		resultEditorPane.setText(resultText.toString());
//		resultEditorPane.setEditable(false);
//		resultEditorPane.setPreferredSize(new Dimension(600, Integer.MAX_VALUE));
//
//		JScrollPane resultScrollPane = new JScrollPane(resultEditorPane);
//		resultScrollPane.setPreferredSize(new Dimension(600, 400));
//
//		// Create the pagination controls
//		JPanel paginationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//		JButton prevButton = new JButton("Prev");
//		JButton nextButton = new JButton("Next");
//		JLabel pageLabel = new JLabel("Page " + currentPage + " of " + numPages);
//
//		paginationPanel.add(prevButton);
//		paginationPanel.add(pageLabel);
//		paginationPanel.add(nextButton);
//
//		// Add action listeners to the pagination buttons
//
//		final int[] finalCurrentPage = {currentPage};
//		prevButton.addActionListener(e -> {
//			if (finalCurrentPage[0] > 1) {
//				finalCurrentPage[0]--;
//				displayResults(searchTerm, results, finalCurrentPage[0], pageSize, dialog);
//				pageLabel.setText("Page " + finalCurrentPage[0] + " of " + numPages);
//			}
//		});
//
//		nextButton.addActionListener(e -> {
//			if (finalCurrentPage[0] < numPages) {
//				finalCurrentPage[0]++;
//				displayResults(searchTerm, results, finalCurrentPage[0], pageSize, dialog);
//				pageLabel.setText("Page " + finalCurrentPage[0] + " of " + numPages);
//			}
//		});
//
//		// Create a panel to hold the result scroll pane and pagination controls
//		JPanel contentPanel = new JPanel(new BorderLayout());
//		contentPanel.add(resultScrollPane, BorderLayout.CENTER);
//		contentPanel.add(paginationPanel, BorderLayout.SOUTH);
//
//		// Create a panel to hold the result scroll pane
//		JPanel resultPanel = new JPanel(new BorderLayout());
//		resultPanel.add(resultScrollPane, BorderLayout.CENTER);
//
//		// Add the result panel and pagination panel to the dialog
//		dialog.add(resultPanel, BorderLayout.CENTER);
//		dialog.add(paginationPanel, BorderLayout.SOUTH);
//
//		// Pack and display the dialog
//		dialog.pack();
//		dialog.setLocationRelativeTo(null); // Center the dialog on the screen
//		dialog.setVisible(true);
//	}

	private void displayResults(String searchTerm, List<SearchResult> results, int currentPage, int pageSize, JFrame previousDialog) {
		// Create a dialog to hold the result scroll pane and pagination controls
		JFrame dialog = new JFrame();
		dialog.setTitle("Search Results - Page " + currentPage);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setLayout(new BorderLayout());

		if (previousDialog != null) {
			previousDialog.dispose();
		}

		int numResults = results.size();
		int numPages = (int) Math.ceil((double) numResults / pageSize);
		int startIndex = (currentPage - 1) * pageSize;
		int endIndex = Math.min(startIndex + pageSize, numResults);
		List<SearchResult> pageResults = results.subList(startIndex, endIndex);

		StringBuilder resultText = new StringBuilder("<html><body>");
		for (int i = 0; i < pageResults.size(); i++) {
			SearchResult result = pageResults.get(i);
			String songTitle = result.getSongTitle().replaceAll("(?i)" + Pattern.quote(searchTerm), "<b style='font-weight: bold;'>$0</b>");
			String singer = result.getSinger().replaceAll("(?i)" + Pattern.quote(searchTerm), "<b style='font-weight: bold;'>$0</b>");
			String lyrics = result.getLyrics()
					.replaceAll("(?i)" + Pattern.quote(searchTerm), "<b style='font-weight: bold;'>$0</b>")
					.replaceAll("(?m)^\\s+", "") // remove all leading whitespace
					.replaceAll("(?m)$", "<br/>"); // replace line ends with <br/>

			String bgColor = i % 2 == 0 ? "#f2f2f2" : "white";
			resultText.append("<div style='background-color:").append(bgColor).append(";'>");
			resultText.append("<p><i>").append(singer).append(" - </i> <i>").append(songTitle).append("</i></p><p>").append(lyrics).append("</p><hr/>");
			resultText.append("</div>");
		}
		resultText.append("</body></html>");

		JEditorPane resultEditorPane = new JEditorPane();
		resultEditorPane.setContentType("text/html");
		resultEditorPane.setText(resultText.toString());
		resultEditorPane.setEditable(false);

		JScrollPane resultScrollPane = new JScrollPane(resultEditorPane);
		resultScrollPane.setPreferredSize(new Dimension(600, 400));

		// Create the pagination controls
		JPanel paginationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton prevButton = new JButton("Prev");
		JButton nextButton = new JButton("Next");
		JLabel pageLabel = new JLabel("Page " + currentPage + " of " + numPages);
		JLabel totalResultsLabel = new JLabel("Total Results: " + numResults);
		JLabel rangeLabel = new JLabel("Results Range: " + (startIndex + 1) + " - " + endIndex);

		paginationPanel.add(prevButton);
		paginationPanel.add(pageLabel);
		paginationPanel.add(nextButton);
		paginationPanel.add(totalResultsLabel);
		paginationPanel.add(rangeLabel);

		final int[] finalCurrentPage = {currentPage};
		prevButton.addActionListener(e -> {
			if (finalCurrentPage[0] > 1) {
				finalCurrentPage[0]--;
				displayResults(searchTerm, results, finalCurrentPage[0], pageSize, dialog);
				pageLabel.setText("Page " + finalCurrentPage[0] + " of " + numPages);
			}
		});

		nextButton.addActionListener(e -> {
			if (finalCurrentPage[0] < numPages) {
				finalCurrentPage[0]++;
				displayResults(searchTerm, results, finalCurrentPage[0], pageSize, dialog);
				pageLabel.setText("Page " + finalCurrentPage[0] + " of " + numPages);
			}
		});

		// Create a panel to hold the result scroll pane and pagination controls
		JPanel contentPanel = new JPanel(new BorderLayout());
		contentPanel.add(resultScrollPane, BorderLayout.CENTER);
		contentPanel.add(paginationPanel, BorderLayout.SOUTH);

		// Add the content panel to the dialog
		dialog.add(contentPanel, BorderLayout.CENTER);

		// Pack and display the dialog
		dialog.pack();
		dialog.setLocationRelativeTo(null); // Center the dialog on the screen
		dialog.setVisible(true);
	}


	public static void main(String[] args) {
		CentralWindow window = new CentralWindow();
		window.setVisible(true);
	}
}
