package picross;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Design extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	/**
	 * 
	 */
	

	public Design(){
		
		//new Game object
		Controller buttons = new Controller();
		
		
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(850,600);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());
		frame.setResizable(true);
		frame.setTitle("Thomas Stanley - Picross Panic Design Mode");
		ImageIcon icon = new ImageIcon("src/images/logo.png");
		frame.setIconImage(icon.getImage());
		
		
		//panel creation
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		
		JPanel clockPanel = new JPanel();
		
		JPanel logoPanel = new JPanel();
		
		JPanel topNums = new JPanel();
		topNums.setLayout(new GridLayout());
		
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(5,1));
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(5,5));
		
		JPanel rightPanel = new JPanel();
		
		
		
		
		//panel colouring
		leftPanel.setBackground(new Color(106, 88, 188));
		centerPanel.setBackground(new Color(85, 54, 217));
		topPanel.setBackground(new Color(106, 88, 188));
		topNums.setBackground(new Color(106, 88, 188));
		rightPanel.setBackground(new Color(106, 88, 188));
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
		
		leftPanel.setPreferredSize(new Dimension(175,100));
		
		centerPanel.setPreferredSize(new Dimension(100,100));
		
		
		
		
		
		//logo panel
		Border border = BorderFactory.createLineBorder(new Color(25,25, 87), 3, true);
		ImageIcon logo = new ImageIcon("src/images/piccross2.png");
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
		clockLabel.setVerticalTextPosition(JLabel.CENTER);
		clockLabel.setFont(new Font("Bad Signal", Font.PLAIN, 42));
		clockPanel.setBorder(border);
		clockPanel.add(clockLabel);
		
		//center panel creation
		buttons.CenterPanelButtons(centerPanel);
		centerPanel.setBorder(border);
		
		//left panel label creation
		JLabel leftLabel1 = new JLabel();
		leftLabel1.setBorder(border);
		leftPanel.add(leftLabel1);
		
		JLabel leftLabel2 = new JLabel();
		leftLabel2.setBorder(border);
		leftPanel.add(leftLabel2);
		
		
		JLabel leftLabel3 = new JLabel();
		leftLabel3.setBorder(border);
		leftPanel.add(leftLabel3);
		
		
		JLabel leftLabel4 = new JLabel();
		leftLabel4.setBorder(border);
		leftPanel.add(leftLabel4);
		
		JLabel leftLabel5 = new JLabel();
		leftLabel5.setBorder(border);
		leftPanel.add(leftLabel5);
		
		
		//top panel label adding
		JLabel topNumsLabel1 = new JLabel();
		topNumsLabel1.setBorder(border);
		topNums.add(topNumsLabel1);
		
		JLabel topNumsLabel2 = new JLabel();
		topNumsLabel2.setBorder(border);
		topNums.add(topNumsLabel2);
		
		JLabel topNumsLabel3 = new JLabel();
		topNumsLabel3.setBorder(border);
		topNums.add(topNumsLabel3);
		
		JLabel topPanelLabel4 = new JLabel();
		topPanelLabel4.setBorder(border);
		topNums.add(topPanelLabel4);
		
		JLabel topPanelLabel5 = new JLabel();
		topPanelLabel5.setBorder(border);
		topNums.add(topPanelLabel5);
		
		topPanel.add(clockPanel, BorderLayout.EAST);
		topPanel.add(logoPanel, BorderLayout.WEST);
		topPanel.add(topNums, BorderLayout.CENTER);
		
		
		//right panel label creation
		rightPanel.setBorder(border);
		JLabel rightLabel = new JLabel();
		rightLabel.setHorizontalAlignment(JLabel.CENTER);
		rightLabel.setVerticalAlignment(JLabel.BOTTOM);
		rightLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		rightLabel.setText("<html>Design Mode!<br/><br/>-Select the tile<br/>you wish to add<br/>to your grid<br/> for play mode!<br/><br/>-Press 'Submit'<br/> when are done!</html>");
		rightLabel.setForeground(Color.black);
		buttons.designButtons(rightPanel, frame);
		rightPanel.add(rightLabel);
		
		
		
		//panel adding
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(rightPanel, BorderLayout.EAST);
		frame.add(leftPanel, BorderLayout.WEST);
		frame.add(centerPanel, BorderLayout.CENTER);
		
//		frame.pack();
		frame.setVisible(true);
	
	}
}
