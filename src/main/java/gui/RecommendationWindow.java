package gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

import utils.Recommendation;

public class RecommendationWindow extends JWindow{
	
	private JPanel panel = new JPanel();
	private JLabel[] recommendationLabels = new JLabel[5];
	private String returnedRecommendation = "";
	
	
	public RecommendationWindow(int x, int y, Recommendation recommendations[]){
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setBackground(new Color(255,255,255));
		
		
		for (int i = 0; i < recommendations.length; i++){
			recommendationLabels[i] = new JLabel(recommendations[i].getLabel());
			recommendationLabels[i].setBackground(new Color(255, 255, 255));
			addLabelListener(i);
			panel.add(recommendationLabels[i]);
		}
		
		add(panel);
		pack();
		setVisible(true);
		setLocation(x, y);
	}
	
	public void addLabelListener(int i){
		recommendationLabels[i].addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
            	returnedRecommendation = recommendationLabels[i].getText();
            	dispose();
            }
            
            public void mouseEntered(MouseEvent e){
            	recommendationLabels[i].setBackground(new Color(211,211,211));
            }
            
            public void mouseExited(MouseEvent e){
            	recommendationLabels[i].setBackground(new Color(255, 255, 255));
            }
            	
        });
		recommendationLabels[i].setOpaque(true);
	}
	
	public boolean labelClicked(){
		return !returnedRecommendation.equals("");
	}
	
	public String getReturnedRecommendation(){
		return returnedRecommendation;
	}

}