package picross;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Panels extends JFrame{

	public Panels(){
		
		//new Game object
		Game buttons = new Game();
		
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(816, 519);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setTitle("Thomas Stanley - Picross Panic");
		ImageIcon icon = new ImageIcon("src/images/logo.png");
		frame.setIconImage(icon.getImage());
		
		
		//panel creation
		JPanel logoPanel = new JPanel();
		JPanel leftPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel topPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		JPanel clockPanel = new JPanel();
		
		
		
		//panel colouring
		leftPanel.setBackground(new Color(106, 88, 188));
		centerPanel.setBackground(new Color(85, 54, 217));
		topPanel.setBackground(new Color(106, 88, 188));
		rightPanel.setBackground(new Color(106, 88, 188));
		clockPanel.setBackground(new Color(106, 88, 188));
		
		
		//panel sizing
		logoPanel.setBounds(0, 0, 175, 100);
		logoPanel.setLayout(null);
		topPanel.setBounds(175, 0, 450, 100);
		topPanel.setLayout(null);
		rightPanel.setBounds(625, 100, 175, 380);
		rightPanel.setLayout(null);
		clockPanel.setBounds(625, 0, 175, 100);
		clockPanel.setLayout(null);
		leftPanel.setBounds(0, 100, 175, 380);
		leftPanel.setLayout(null);
		centerPanel.setBounds(175,100,450,380);
		centerPanel.setLayout(null);
		
		
		
		
		//logo panel
		Border border = BorderFactory.createLineBorder(new Color(25,25, 87), 3, true);
		ImageIcon logo = new ImageIcon("src/images/piccross2.png");
		Image logoResize = logo.getImage();
		Image newLogo = logoResize.getScaledInstance(175, 100, java.awt.Image.SCALE_DEFAULT);
		ImageIcon logo2 = new ImageIcon(newLogo);
		JLabel logoLabel = new JLabel();
		logoLabel.setBounds(0, 0, 175, 100);
		logoLabel.setIcon(logo2);
		logoLabel.setBorder(border);
		logoPanel.add(logoLabel);
		
		
		
		//left panel label creation
		JLabel leftLabel1 = new JLabel();
		leftLabel1.setBounds(0, 0, 175, 76);
		leftLabel1.setBorder(border);
		leftPanel.add(leftLabel1);
		
		JLabel leftLabel2 = new JLabel();
		leftLabel2.setBounds(0, 76, 175, 76);
		leftLabel2.setBorder(border);
		leftPanel.add(leftLabel2);
		
		
		JLabel leftLabel3 = new JLabel();
		leftLabel3.setBounds(0, 152, 175, 76);
		leftLabel3.setBorder(border);
		leftPanel.add(leftLabel3);
		
		
		JLabel leftLabel4 = new JLabel();
		leftLabel4.setBounds(0, 228, 175, 76);
		leftLabel4.setBorder(border);
		leftPanel.add(leftLabel4);
		
		JLabel leftLabel5 = new JLabel();
		leftLabel5.setBounds(0, 304, 175, 76);
		leftLabel5.setBorder(border);
		leftPanel.add(leftLabel5);
		
		
		//top panel label adding
		JLabel topPanelLabel1 = new JLabel();
		topPanelLabel1.setBounds(0, 0, 90, 100);
		topPanelLabel1.setBorder(border);
		topPanel.add(topPanelLabel1);
		
		JLabel topPanelLabel2 = new JLabel();
		topPanelLabel2.setBounds(90, 0, 90, 100);
		topPanelLabel2.setBorder(border);
		topPanel.add(topPanelLabel2);
		
		JLabel topPanelLabel3 = new JLabel();
		topPanelLabel3.setBounds(180, 0, 90, 100);
		topPanelLabel3.setBorder(border);
		topPanel.add(topPanelLabel3);
		
		JLabel topPanelLabel4 = new JLabel();
		topPanelLabel4.setBounds(270, 0, 90, 100);
		topPanelLabel4.setBorder(border);
		topPanel.add(topPanelLabel4);
		
		JLabel topPanelLabel5 = new JLabel();
		topPanelLabel5.setBounds(360, 0, 90, 100);
		topPanelLabel5.setBorder(border);
		topPanel.add(topPanelLabel5);
		
		
		
		//clock panel creation
		JLabel clockLabel = new JLabel();
		clockLabel.setBounds(0, 0, 175, 100);
		clockLabel.setForeground(Color.black);
		clockLabel.setHorizontalAlignment(JLabel.CENTER);
		clockLabel.setFont(new Font("Calibri", Font.PLAIN, 42));
		clockPanel.setBorder(border);
		clockPanel.add(clockLabel);
		buttons.countdown(clockLabel);
		
		
		
		
		
		//right panel label creation
		rightPanel.setBorder(border);
		
		
		//center panel creation
		buttons.CenterPanelButtons(centerPanel);
		
		
		//panel adding
		frame.add(logoPanel);
		frame.add(topPanel);
		frame.add(rightPanel);
		frame.add(clockPanel);
		frame.add(leftPanel);
		frame.add(centerPanel);
		
				
		frame.setVisible(true);
	
	}
}
