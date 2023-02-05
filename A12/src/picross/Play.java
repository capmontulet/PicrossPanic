package picross;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

/**
 * @author Thomas Stanley
 *	Play class where the user plays the main game. A new frame is opened with
 *	the design grid loaded in if created.
 */
public class Play {
	

	/**
	 * required Serial #
	 */
	private static final long serialVersionUID = 3L;
	

	/**
	 * If the grid is designed, it is passed here, or if the user goes straight to play mode, a default grid is used.
	 * @param designGrid The design grid pass from design frame, or the blank grid if not.
	 * @param isDesigned Boolean value for if the grid is user designed or not.
	 */
	public Play(int[][] designGrid, boolean isDesigned){
		
		Controller buttons = new Controller();
		int[][] defaultGrid = new int[5][5];
		
		//button.grid = blank
		//designGrid is player made
		//defaultGrid is default
		int[][] playGrid = mapGrid(isDesigned, designGrid, defaultGrid);
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(850,600);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());
		frame.setResizable(true);
		frame.setTitle("Thomas Stanley - Picross Panic Play Mode");
		ImageIcon icon = new ImageIcon("src/images/logo.png");
		frame.setIconImage(icon.getImage());
		
		
		//panel creation
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		JPanel topNums = new JPanel();
		topNums.setLayout(new GridLayout());
		topNums.setFont(new Font("Arial", Font.PLAIN, 42));
		
		JPanel clockPanel = new JPanel();
		
		JPanel logoPanel = new JPanel();
		
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(5,1));
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(5,5));
		
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout(10,10));
		JPanel scorePanel = new JPanel();
		
		
		
		//panel colouring
		leftPanel.setBackground(new Color(106, 88, 188));
		centerPanel.setBackground(new Color(85, 54, 217));
		
		topPanel.setBackground(new Color(106, 88, 188));
		topNums.setBackground(new Color(106, 88, 188));
		
		rightPanel.setBackground(new Color(106, 88, 188));
		scorePanel.setBackground(Color.black);
		
		clockPanel.setBackground(new Color(106, 88, 188));
		logoPanel.setBackground(new Color(106, 88, 188));
		
		
		//panel sizing
		
		
		topPanel.setPreferredSize(new Dimension(100,110));
		
		topNums.setPreferredSize(new Dimension(50,110));
		topPanel.add(topNums);
		
		logoPanel.setPreferredSize(new Dimension(175,110));
		topPanel.add(logoPanel);
		
		clockPanel.setPreferredSize(new Dimension(175,100));
		topPanel.add(clockPanel);
		
		rightPanel.setPreferredSize(new Dimension(175,100));
		scorePanel.setPreferredSize(new Dimension(150,80));
		
		leftPanel.setPreferredSize(new Dimension(175,100));
		
		centerPanel.setPreferredSize(new Dimension(100,100));
		
		
		
		
		
		//logo panel
		Border border = BorderFactory.createLineBorder(new Color(25,25, 87), 3, true);
		URL logoURL = Game.class.getResource("/images/piccross2.png");
		ImageIcon logo = new ImageIcon(logoURL);
		Image logoResize = logo.getImage();
		Image newLogo = logoResize.getScaledInstance(175, 100, java.awt.Image.SCALE_DEFAULT);
		ImageIcon logo2 = new ImageIcon(newLogo);
		JLabel logoLabel = new JLabel();
		logoLabel.setIcon(logo2);
		logoLabel.setBorder(border);
		logoPanel.add(logoLabel);	
		
		
		
		//clock panel creation
		JLabel clockLabel = new JLabel();
		clockLabel.setForeground(Color.black);
		clockLabel.setVerticalAlignment(JLabel.CENTER);
		clockLabel.setFont(new Font("Bad Signal", Font.PLAIN, 42));
		clockPanel.setBorder(border);
		clockPanel.add(clockLabel);
		
		//center panel creation
		buttons.CenterPanelButtons(centerPanel);
		centerPanel.setBorder(border);
		
		//left panel label creation
		JLabel leftLabel1 = new JLabel();
		numberLabelCreation(leftLabel1, leftPanel, border);
		
		JLabel leftLabel2 = new JLabel();
		numberLabelCreation(leftLabel2, leftPanel, border);
		
		
		JLabel leftLabel3 = new JLabel();
		numberLabelCreation(leftLabel3, leftPanel, border);
		
		
		JLabel leftLabel4 = new JLabel();
		numberLabelCreation(leftLabel4, leftPanel, border);
		
		JLabel leftLabel5 = new JLabel();
		numberLabelCreation(leftLabel5, leftPanel, border);
		
		
		//top panel label adding
		JLabel topNumsLabel1 = new JLabel();
		numberLabelCreation(topNumsLabel1, topNums, border);
		
		JLabel topNumsLabel2 = new JLabel();
		numberLabelCreation(topNumsLabel2, topNums, border);
		
		JLabel topNumsLabel3 = new JLabel();
		numberLabelCreation(topNumsLabel3, topNums, border);
		
		JLabel topNumsLabel4 = new JLabel();
		numberLabelCreation(topNumsLabel4, topNums, border);
		
		JLabel topNumsLabel5 = new JLabel();
		numberLabelCreation(topNumsLabel5, topNums, border);
		
		topPanel.add(clockPanel, BorderLayout.EAST);
		topPanel.add(logoPanel, BorderLayout.WEST);
		topPanel.add(topNums, BorderLayout.CENTER);
		
		
		//right panel label creation
		rightPanel.setBorder(new CompoundBorder(
				border,
				BorderFactory.createEmptyBorder(10, 10, 10, 10)
				));
		
		scorePanel.setBorder(BorderFactory.createLineBorder(Color.white,3));
		scorePanel.setBackground(Color.black);
		JLabel scoreLabel = new JLabel();
		scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
		scoreLabel.setForeground(Color.white);
		scoreLabel.setText("Score:");
		
		JButton submit = new JButton("Submit");
		scorePanel.add(scoreLabel);
		
		rightPanel.add(submit, BorderLayout.SOUTH);
		rightPanel.add(scorePanel, BorderLayout.CENTER);
		
		
		//button object after rightlabel creation
		buttons.countdown(clockLabel);
		
		
		
		//panel adding
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(rightPanel, BorderLayout.EAST);
		frame.add(leftPanel, BorderLayout.WEST);
		frame.add(centerPanel, BorderLayout.CENTER);
		
	//	frame.pack();
		frame.setVisible(true);
	
	}
	
	/*
	 * Method for creating labels in fewer lines. 
	 */
	void numberLabelCreation(JLabel label, JPanel panel, Border border) {
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Arial", Font.PLAIN, 40));
		label.setText("(2)");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBorder(border);
		panel.add(label);
	}
	
	/*
	 * Method creates a default playing grid if not designed beforehand.
	 * Returns playing grid to be used for the class.
	 */
	int[][] mapGrid(boolean isDesigned, int[][] designGrid, int[][] defaultGrid) {
		if(!isDesigned) {
			defaultGrid[0][2]=1;
			defaultGrid[1][2]=1;
			defaultGrid[2][0]=1;
			defaultGrid[2][1]=1;
			defaultGrid[2][2]=1;
			defaultGrid[2][3]=1;
			defaultGrid[2][4]=1;
			defaultGrid[3][1]=1;
			defaultGrid[3][3]=1;
			defaultGrid[4][1]=1;
			defaultGrid[4][3]=1;
			//returns defaultGrid
			return defaultGrid;
		}else {
		return designGrid;
		}
	}

}
