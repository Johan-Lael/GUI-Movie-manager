package Project;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class MovieManagerGUISearch extends JFrame {
	
	private MovieManager movieManager;
	private JTextArea displayArea;
	private JTextField textField;
	private JTextField baitField;
	private JComboBox<Genre> GenreComboBox;
	private boolean filtered = false;
	
	private JPanel cardPanel;
	JPanel panel1;
	JPanel panel2;
	
	private static JTextField titleField, studioField, durationField, likesField;
	private JComboBox<Genre> GenreComboBox2;
	private JComboBox<Rating> RatingComboBox;
	
    class ImagePanel extends JPanel {
        private Image backgroundImage;

        public ImagePanel(String imagePath) {
            backgroundImage = new ImageIcon(imagePath).getImage();
        }

       
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
	
	public MovieManagerGUISearch() {
		movieManager = new MovieManager();
		setTitle("Discovery");
		setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        
        
        cardPanel = new JPanel(new CardLayout());
        panel1 = new ImagePanel("C:\\Users\\johan\\OneDrive\\Documents\\UTA\\Courses\\Spring 2025\\CSE 1325\\coding\\project\\OIP.jpg");
        panel1.setLayout(new GridBagLayout());
        panel2 = new ImagePanel("C:\\Users\\johan\\OneDrive\\Documents\\UTA\\Courses\\Spring 2025\\CSE 1325\\coding\\project\\OIP.jpg");
        panel2.setLayout(new GridBagLayout());
        
        panel1.setBackground(new Color(175, 255, 255, 200)); // Semi-transparent background
        panel2.setBackground(new Color(175, 255, 255, 200));
        
//      ImageIcon image = new ImageIcon("C:\\Users\\johan\\OneDrive\\Documents\\UTA\\Courses\\Spring 2025\\CSE 1325\\coding\\project\\OIP.jpg");
//		Image scaledImage = image.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
//		ImageIcon backgroundImage = new ImageIcon(scaledImage);
//		JLabel backgroundLabel = new JLabel(backgroundImage);
//		setContentPane(new JLabel(backgroundImage));
		
		baitField = new JTextField("");
		baitField.setPreferredSize(new Dimension(0, 0));
		
		textField = new JTextField("  search...");
		textField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals("  search...")) {
                    textField.setText("");
                }
            }
        });
		textField.setPreferredSize(new Dimension(400, 30));
		
		JButton search = new JButton("Search");
		search.addActionListener(e -> searchMovie());
		
		JButton delete = new JButton("Delete");
		delete.addActionListener(e -> removeMovie());
		
        GenreComboBox = new JComboBox<>(Genre.values());
        GenreComboBox.setSelectedItem(Genre.UNKNOWN);
        
        JButton filter = new JButton("Filter");
		filter.addActionListener(e -> filterMovie());
        
		JButton sort = new JButton("Sort");
		sort.addActionListener(e -> sortMovie());
		
		displayArea = new JTextArea("  Catalog: \n");
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setPreferredSize(new Dimension(700, 400));
        
        initializeAddMoviePanel();
        
        JButton addMovieButton = new JButton("New Movie");
		addMovieButton.addActionListener(e -> {
			showAddMoviePanel();
		});
		
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Padding
        
        gbc.gridx = 0; gbc.gridy = 0;
        panel1.add(baitField, gbc);

        gbc.gridx = 1; gbc.gridy = 0; gbc.gridwidth = 2;
        panel1.add(textField, gbc);

        gbc.gridx = 3; gbc.gridy = 0; gbc.gridwidth = 1;
        panel1.add(search, gbc);

        gbc.gridx = 4; gbc.gridy = 0; gbc.gridwidth = 1;
        panel1.add(delete, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        panel1.add(GenreComboBox, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        panel1.add(filter, gbc);

        gbc.gridx = 4; gbc.gridy = 1;
        panel1.add(sort, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(scrollPane, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 1;
        panel1.add(addMovieButton, gbc);

        cardPanel.add(panel1, "Panel1");
        cardPanel.add(panel2, "Panel2");
        
        add(cardPanel, BorderLayout.CENTER);
        
        ((CardLayout) cardPanel.getLayout()).show(cardPanel, "Panel1");
	}
	
	
    private void searchMovie() {
    	String title = textField.getText();
    	Movie movie = movieManager.searchMovie(title);
    	if(movie==null) {
    		clear();
    		displayArea.append("Movie not Found!\n");
    	}else {
    		clear();
    		displayArea.append(movie.toString() + "\n");
    	}
    }
    
    private void removeMovie() {
    	String title = textField.getText();
    	movieManager.removeMovie(title);
    	clear();
        displayArea.append("Movie Removed!\n");
        updateDisplay();
    }
    
    private void filterMovie() {
    	if(!filtered) {
	    	Genre genre = (Genre) GenreComboBox.getSelectedItem();
	    	Movie[] filteredMovies = movieManager.filterMoviesByGenre(genre);
	    	clear();
			displayArea.append("Movies filtered by "+genre+"!\n");
			for (Movie movie : filteredMovies) {
	            if (movie != null) {
	            	 displayArea.append(movie.toString() + "\n");
	            }
	        }
    	}else {
    		clear();
    		updateDisplay();
    	}
    	filtered = !filtered;
    }
    
    private void sortMovie() {

    	movieManager.sortMoviesByName();
    	clear();
		displayArea.append("Movies sorted by title!\n");
        updateDisplay();
    }
    
    public void addMovie() {
		String title = titleField.getText();
		String studio = studioField.getText();
		int duration = Integer.parseInt(durationField.getText());
		int likes = Integer.parseInt(likesField.getText());
		Rating rating = (Rating) RatingComboBox.getSelectedItem();
		Genre genre = (Genre) GenreComboBox2.getSelectedItem();
		movieManager.addMovie(new Movie(title, studio, duration, likes, rating, genre));
		clearPanel2();
	}
	
    private void clear() {
    	textField.setText("  search...");
    	displayArea.setText("  Catalog: \n");
    }
    
    private void clearPanel2() {
    	titleField.setText("");
    	studioField.setText("");
    	durationField.setText("");
    	likesField.setText("");
    	GenreComboBox2.setSelectedItem(Genre.UNKNOWN);
    	RatingComboBox.setSelectedItem(Rating.UNKNOWN);
    }
    
    private void updateDisplay() {
    	for (Movie movie : movieManager.getMovies()) {
            if (movie != null) {
            	 displayArea.append(movie.toString() + "\n");
            }
        }
    }
    
    private void initializeAddMoviePanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Padding
        
        gbc.anchor = GridBagConstraints.LINE_START;

        JLabel titleLabel = new JLabel("Title: ");
        titleLabel.setForeground(Color.WHITE);
        titleField = new JTextField();
        gbc.gridx = 0; gbc.gridy = 0;
        panel2.add(titleLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        panel2.add(titleField, gbc);

        JLabel studioLabel = new JLabel("Studio: ");
        studioLabel.setForeground(Color.WHITE);
        studioField = new JTextField();
        gbc.gridx = 0; gbc.gridy = 1; 
        panel2.add(studioLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        panel2.add(studioField, gbc);

        JLabel durationLabel = new JLabel("Duration:");
        durationLabel.setForeground(Color.WHITE);
        durationField = new JTextField();
        gbc.gridx = 0; gbc.gridy = 2;
        panel2.add(durationLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        panel2.add(durationField, gbc);

        JLabel likesLabel = new JLabel("Likes:");
        likesLabel.setForeground(Color.WHITE);
        likesField = new JTextField();
        gbc.gridx = 0; gbc.gridy = 3;
        panel2.add(likesLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        panel2.add(likesField, gbc);

        JLabel GenreLabel = new JLabel("Genre:");
        GenreLabel.setForeground(Color.WHITE);
        GenreComboBox2 = new JComboBox<>(Genre.values());
        GenreComboBox2.setSelectedItem(Genre.UNKNOWN);
        gbc.gridx = 0; gbc.gridy = 4;
        panel2.add(GenreLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        panel2.add(GenreComboBox2, gbc);

        JLabel RatingLabel = new JLabel("Rating:");
        RatingLabel.setForeground(Color.WHITE);
        RatingComboBox = new JComboBox<>(Rating.values());
        RatingComboBox.setSelectedItem(Rating.UNKNOWN);
        gbc.gridx = 0; gbc.gridy = 5;
        panel2.add(RatingLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 5;
        panel2.add(RatingComboBox, gbc);

        JButton addMovieButton = new JButton("Add Movie");
        addMovieButton.addActionListener(e -> {addMovie();
        ((CardLayout) cardPanel.getLayout()).show(cardPanel, "Panel1");
        clear();
        displayArea.append("New movie Added to catalog!\n");
        updateDisplay();
        });
        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        panel2.add(addMovieButton, gbc);
        
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> {clearPanel2();
        	((CardLayout) cardPanel.getLayout()).show(cardPanel, "Panel1");
        });
        gbc.gridx = 4; gbc.gridy = 6;  // New row for the close button
        panel2.add(closeButton, gbc);
    }
    
    private void showAddMoviePanel() {
    	((CardLayout) cardPanel.getLayout()).show(cardPanel, "Panel2");
    }
}
