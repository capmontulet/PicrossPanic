package picross;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;


public class View extends JFrame implements ActionListener{

	
	
	public View() {
		
		
	}
	
	public void splash() {
		Controller buttons = new Controller();
		
		JWindow splashWindow = new JWindow();

		URL splashURL = Game.class.getResource("/images/splash.gif");
		ImageIcon splashIcon = new ImageIcon(splashURL);
		splashWindow.setContentPane(new JLabel(splashIcon));
		
		splashWindow.pack();
		splashWindow.setLocationRelativeTo(null);
		splashWindow.setVisible(true);
		try {
		    Thread.sleep(5000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		splashWindow.setVisible(false);
		splashWindow.dispose();
		
		launcher();
	}
	
	
	public void launcher() {
		Controller buttons = new Controller();
		JFrame frame = new JFrame();
		Border border = BorderFactory.createLineBorder(new Color(25,25, 87), 3, true);
		
		URL bgURL = Game.class.getResource("/images/launcherBack2.png");
		ImageIcon bg = new ImageIcon(bgURL);
        frame.setContentPane(new JLabel(bg));
      
        //frame details
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(450,250);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		
		
		//Menu Bar
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu help = new JMenu("Help");
		JMenu edit = new JMenu("Edit");
		JMenuItem changeCS = new JMenuItem("Change Colour Scheme");
		JMenuItem loadGrid = new JMenuItem("Load Grid");
		JMenuItem saveGrid = new JMenuItem("Save Grid");
		JMenuItem exit = new JMenuItem("Exit");
		JMenuItem howTo = new JMenuItem("How to Play");
		
		changeCS.addActionListener(this);
		
		loadGrid.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser loader = new JFileChooser();
				int response = loader.showOpenDialog(null);
				if(response==JFileChooser.APPROVE_OPTION) {
					File filePicker = new File(loader.getSelectedFile().getAbsolutePath());
					buttons.playMode(buttons.fileLoader(filePicker,buttons.myModel), true, buttons.myModel);
				}
				
			}
			
		});
		
		saveGrid.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				design(false);
			}
			
		});
		
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		
		howTo.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // create a panel to hold the box and button
		        JPanel howToPanel = new JPanel(new BorderLayout());
		        howToPanel.setBackground(new Color(106, 88, 188));
		        howToPanel.setBorder(border);
		        
		        // create the box
		        JLabel boxLabel = new JLabel("<html>Fill in the play grid by looking at the numbers in the top and left hand side boxes. These numbers indicate how many correct tiles there are in that column/row. Try to figure out which ones are correct then hit 'Submit'.<br/><br/>The clock is ticking, and an incorrect guess will cost you three seconds, while a correct guess will give you three extra seconds.<br/><br/>Good Luck!");
		        boxLabel.setFont(new Font("Arial", Font.BOLD, 20));
		        boxLabel.setForeground(new Color(25,25, 87));
		        boxLabel.setHorizontalAlignment(SwingConstants.CENTER);
		        boxLabel.setVerticalAlignment(SwingConstants.TOP);
		        howToPanel.add(boxLabel, BorderLayout.CENTER);
		        
		        // create the button
		        JButton closeButton = new JButton("Close");
		        closeButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                // remove the panel from the frame
		                frame.getContentPane().remove(howToPanel);
		                frame.revalidate();
		                frame.repaint();
		            }
		        });
		        howToPanel.add(closeButton, BorderLayout.SOUTH);
		        
		        // add the panel to the center of the frame
		        frame.add(howToPanel, BorderLayout.CENTER);
		        
		        // revalidate and repaint the frame to update the changes
		        frame.revalidate();
		        frame.repaint();
		    }
		});
		
		file.add(loadGrid);
		file.add(saveGrid);
		file.add(exit);
		edit.add(changeCS);
		help.add(howTo);
		menuBar.add(file);
		menuBar.add(edit);
		menuBar.add(help);
		frame.add(menuBar, BorderLayout.NORTH);
		
		
		
		//setting icon
		frame.setTitle("Thomas Stanley - Picross Panic Launcher");
		ImageIcon icon = new ImageIcon("src/images/logo.png");
		frame.setIconImage(icon.getImage());
		
		//creating button panel
		JPanel launcherPanel = new JPanel();
		launcherPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		launcherPanel.setOpaque(false);
		JButton design = new JButton("Design");
		design.setPreferredSize(new Dimension(85,45));
		design.setFont(new Font("Arial", Font.BOLD, 15));
		
		//design option opens new Design frame
		design.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				design(true);
			}
			
		});
		
		//play option opens new Play frame
		JButton play = new JButton("Play");
		play.setPreferredSize(new Dimension(85,45));
		play.setFont(new Font("Bad Signal", Font.BOLD, 15));
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				buttons.launcherPlay();
			}
			
		});
		
		
		//radio button creation for localisation
		JRadioButton eng = new JRadioButton("English");
		JRadioButton ger = new JRadioButton("German");
		
		URL engURL = Game.class.getResource("/images/realengflag.gif");
		ImageIcon engFlag= new ImageIcon(engURL);
		
		URL gerURL = Game.class.getResource("/images/piciconger.gif");
		ImageIcon gerFlag= new ImageIcon(gerURL);
		
		eng.setIcon(engFlag);
		ger.setIcon(gerFlag);
		
		
		launcherPanel.add(ger, FlowLayout.LEFT);
		launcherPanel.add(eng);
		
		launcherPanel.add(design);
		launcherPanel.add(play);
		
		frame.add(launcherPanel, BorderLayout.SOUTH);
		
		frame.pack();
		frame.setVisible(true);
		
	}
	
	public void design(boolean isDesign) {
		
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
		rightPanel.setLayout(new BorderLayout(10,10));
		
		
		
		
		//panel colouring
		leftPanel.setBackground(new Color(106, 88, 188));
		centerPanel.setBackground(new Color(106, 88, 188));
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
		clockLabel.setVerticalTextPosition(JLabel.CENTER);
		clockLabel.setFont(new Font("Bad Signal", Font.PLAIN, 42));
		clockPanel.setBorder(border);
		clockPanel.add(clockLabel);
		
		//center panel creation
		buttons.buttonDetails(centerPanel, false, null);
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
		rightPanel.setBorder(new CompoundBorder(
				border,
				BorderFactory.createEmptyBorder(10, 10, 10, 10)
				));
		JLabel rightLabel = new JLabel();
		rightLabel.setHorizontalAlignment(JLabel.CENTER);
		rightLabel.setVerticalAlignment(JLabel.BOTTOM);
		rightLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		rightLabel.setForeground(Color.black);
		
		//differentiating between design and save mode
		if(isDesign == true) {
		rightLabel.setText("<html>Design Mode!<br/><br/>-Select the tile<br/>you wish to add<br/>to your grid<br/> for play mode!<br/><br/>-Press 'Submit'<br/> when are done!</html>");
		buttons.designSubmit(rightPanel, frame);
		}else {
			rightLabel.setText("<html>Save Mode!<br/><br/>-Select the tile<br/>you wish to add<br/>to your grid to<br/>be saved<br/><br/>-Press 'Save'<br/> when are done!</html>");	
		buttons.saveSubmit(rightPanel, frame);
		}
		
		rightPanel.add(rightLabel, BorderLayout.NORTH);
		
		
		
		//panel adding
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(rightPanel, BorderLayout.EAST);
		frame.add(leftPanel, BorderLayout.WEST);
		frame.add(centerPanel, BorderLayout.CENTER);
		
		frame.setVisible(true);
	}
	
	public void play(Model model) {
		Controller buttons = new Controller();
		
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
		clockLabel.setFont(new Font("Arial", Font.PLAIN, 42));
		clockPanel.setBorder(border);
		clockPanel.add(clockLabel);
		
		//center panel creation
		buttons.buttonDetails(centerPanel, true, model);
		centerPanel.setBorder(border);
		
		
		buttons.myModel.clueCreate(topNums,border,true, buttons, model);
		//making labels for left panel
		buttons.myModel.clueCreate(leftPanel, border, false, buttons, model);
		
		
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

		
		
		
		
		buttons.playSubmit(rightPanel, frame, model);
		
		
		buttons.myModel.scoreText(scorePanel);
		
		
		rightPanel.add(scorePanel, BorderLayout.CENTER);
		
		
		//button object after rightlabel creation
		buttons.countdown(clockLabel);
		
		
		
		//panel adding
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(rightPanel, BorderLayout.EAST);
		frame.add(leftPanel, BorderLayout.WEST);
		frame.add(centerPanel, BorderLayout.CENTER);
		
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
