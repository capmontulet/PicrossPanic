package picross;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class Panels extends JFrame{
	
	Panels(){
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(816, 519);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setTitle("Picross Panic");
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
//		logoPanel.setBackground(Color.red);
		leftPanel.setBackground(Color.green);
		centerPanel.setBackground(Color.blue);
		topPanel.setBackground(Color.BLACK);
		rightPanel.setBackground(Color.gray);
		clockPanel.setBackground(Color.YELLOW);
		
		
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
