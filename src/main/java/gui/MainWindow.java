package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.jgoodies.forms.layout.FormLayout;

import javax.swing.*;
import lucene.SearchFiles;
import utils.CustomQuery;
import utils.Recommendation;
import utils.SearchReturn;

public class MainWindow {

	private String recommendationPath = "C:\\Users\\a.todhri\\IdeaProjects\\LyriSearch-Lucene";
	private JFrame frame;
	private FormLayout formLayout = new FormLayout();
	private BorderLayout borderLayout = new BorderLayout();
	private JPanel searchPanel = new JPanel();
	private int numberOfColumns = 25;
	private int numberOfRows;
	private JButton searchButton = new JButton("Search");
	private JButton clearButton = new JButton("Clear");
	private SearchFiles searchFiles = new SearchFiles();
	private String fields[] = {"username", "location", "hashtags", "text"};
	private SearchReturn searchReturn;
	private static int topK = 5;
	private Recommendation userRecommendations[];
	private Recommendation locationRecommendations[];
	private Recommendation hashtagsRecommendations[];
	private RecommendationWindow recommendationWindow;
	private JTextField lastClickedText;
	ResultWindow resultWindow;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainWindow() {
		initialize();
	}

	private void initialize() {
		initRecommendations();
		loadRecommendation(userRecommendations, "usernames");
		loadRecommendation(locationRecommendations, "locations");
		loadRecommendation(hashtagsRecommendations, "hashtags");

		frame = new JFrame();
		frame.setBounds(100, 100, 600, 300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(borderLayout);
		addMainWindowListener();
		createColumns();
		searchPanel.setLayout(formLayout);

		addLabel("username");
		addLabel("location");
		addLabel("hashtags");
		addLabel("keyword");
		addButtons();

		frame.add(searchPanel, BorderLayout.CENTER);
		frame.revalidate();
		frame.repaint();
	}

	private void createColumns() {
		for (int i = 0; i < numberOfColumns; i++) {
//		    formLayout.appendColumn(FormFactory.RELATED_GAP_COLSPEC);
//		    formLayout.appendColumn(FormFactory.DEFAULT_COLSPEC );
		}
	}

	private void addLabel(String labelName) {
		addRow();
		addRow();
		addRow();
		JLabel label = new JLabel(labelName);
		searchPanel.add(label, "2, " + numberOfRows * 2 + ", left, default");
		JTextField textField = new JTextField();
		textField.setName(labelName);
		addTextFieldListener(textField);
		searchPanel.add(textField, "4, " + numberOfRows * 2 + ", right, default");
		textField.setColumns(25);
	}

	private void addRow() {
//		formLayout.appendRow(FormFactory.RELATED_GAP_ROWSPEC);
//	    formLayout.appendRow(FormFactory.DEFAULT_ROWSPEC);
		numberOfRows += 1;
	}

	private void addButtons() {
		addRow();
		addRow();
		addRow();
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		buttonPanel.add(searchButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(8, 0)));
		buttonPanel.add(clearButton);
		searchPanel.add(buttonPanel, "4, " + numberOfRows * 2 + ", left, default");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Component component : searchPanel.getComponents()) {
					if (component.getClass().getName().equals("javax.swing.JTextField")) {
						((JTextField) component).setText("");
						frame.revalidate();
						frame.repaint();
					}
				}
			}
		});
	}

	public void search() {
		ArrayList<CustomQuery> customQueries = new ArrayList<CustomQuery>();
		int counter = 0;
		String words[];
		JLabel lastLabel = new JLabel();
		for (Component component : searchPanel.getComponents()) {
			if (component.getClass().getName().equals("javax.swing.JLabel")) {
				lastLabel = (JLabel) component;
				continue;
			}
			if (component.getClass().getName().equals("javax.swing.JTextField")) {
				words = ((JTextField) component).getText().split(",");
				for (String word : words) {
					if (word.equals("")) continue;
					customQueries.add(new CustomQuery(lastLabel.getText(), word));
				}
				counter += 1;
			}
		}
		try {
			searchReturn = searchFiles.search(customQueries);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (resultWindow != null) {
			resultWindow.dispose();
		}

		resultWindow = new ResultWindow(frame.getBounds().getLocation().getX(), frame.getBounds().getLocation().getY(), searchReturn);
	}

	public void addMainWindowListener() {
		frame.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				closeRecommendationWindow();
			}
		});
	}

	public void initRecommendations() {
		userRecommendations = new Recommendation[topK];
		locationRecommendations = new Recommendation[topK];
		hashtagsRecommendations = new Recommendation[topK];
		for (int i = 0; i < topK; i++) {
			userRecommendations[i] = new Recommendation(" ", 0);
			locationRecommendations[i] = new Recommendation(" ", 0);
			hashtagsRecommendations[i] = new Recommendation(" ", 0);
		}

	}

	public void loadRecommendation(Recommendation recommendations[], String fileName) {
		String line, field = "";
		int counter;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(recommendationPath + fileName + ".txt"));
			line = br.readLine();
			while (line != null) {
				field = line.split("~~~")[0];
				counter = Integer.parseInt(line.split("~~~")[1]);
				if (counter > recommendations[recommendations.length - 1].getCounter()) {
					recommendations[recommendations.length - 1] = new Recommendation(field, counter);
					Arrays.sort(recommendations, Recommendation.CounterComparator);
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addTextFieldListener(JTextField textField) {
		textField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				closeRecommendationWindow();

				if (textField.getName().equals("username")) {
					if (userRecommendations[0].getCounter() == 0) return;
					createRecommendationWindow(userRecommendations, textField.getLocationOnScreen().getX(), textField.getLocationOnScreen().getY() + textField.getSize().getHeight());
				} else if (textField.getName().equals("location")) {
					if (locationRecommendations[0].getCounter() == 0) return;
					createRecommendationWindow(locationRecommendations, textField.getLocationOnScreen().getX(), textField.getLocationOnScreen().getY() + textField.getSize().getHeight());
				} else if (textField.getName().equals("hashtags")) {
					if (hashtagsRecommendations[0].getCounter() == 0) return;
					createRecommendationWindow(hashtagsRecommendations, textField.getLocationOnScreen().getX(), textField.getLocationOnScreen().getY() + textField.getSize().getHeight());
				}

				lastClickedText = textField;
			}
		});
	}

	public void createRecommendationWindow(Recommendation recommendations[], double x, double y) {

		recommendationWindow = new RecommendationWindow((int) x, (int) y, recommendations);
		recommendationWindow.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				if (recommendationWindow.labelClicked()) {
					if (lastClickedText != null) {
						if (lastClickedText.getText().equals("")) {
							lastClickedText.setText(recommendationWindow.getReturnedRecommendation());
						} else {
							lastClickedText.setText(lastClickedText.getText() + " " + recommendationWindow.getReturnedRecommendation());
						}
					}
				}
			}

		});
	}

	public void closeRecommendationWindow() {
		if (recommendationWindow != null) {
			recommendationWindow.dispose();
		}
	}

	public static int getK() {
		return topK;
	}

}
