package Project;

import javax.swing.*;
import java.awt.*;

public class MovieManagerGUIHomePage {
	
	public static void main(String[] args) {
		
		JFrame homePage = new JFrame("Home Page");
		
		homePage.setSize(720, 500);
		homePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		homePage.setLayout(null);
		homePage.setResizable(false);
		
		ImageIcon image = new ImageIcon("C:\\Users\\johan\\OneDrive\\Documents\\UTA\\Courses\\Spring 2025\\CSE 1325\\coding\\project\\OIP.jpg");
		Image scaledImage = image.getImage().getScaledInstance(720, 500, Image.SCALE_SMOOTH);
		ImageIcon backgroundImage = new ImageIcon(scaledImage);
		JLabel backgroundLabel = new JLabel(backgroundImage);
		backgroundLabel.setBounds(0, 0, 720, 500);
		homePage.setContentPane(new JLabel(backgroundImage));
		
		JButton searchButton = new JButton("Discover...");
		searchButton.setBounds(550, 390, 120, 40);
		homePage.add(searchButton);
		
		
		ImageIcon logoImage = new ImageIcon("C:\\Users\\johan\\OneDrive\\Documents\\UTA\\Courses\\Spring 2025\\CSE 1325\\coding\\project\\download.png");
		scaledImage = logoImage.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		ImageIcon lgImage = new ImageIcon(scaledImage);
		JLabel logoLabel = new JLabel(lgImage);
		logoLabel.setBounds(270, 100, 150, 150);
		homePage.add(logoLabel);
		
		searchButton.addActionListener(e -> {
            homePage.dispose(); 
            MovieManagerGUISearch searchFrame = new MovieManagerGUISearch();
            searchFrame.setVisible(true);
        });
		
		homePage.setVisible(true);
	}

}
