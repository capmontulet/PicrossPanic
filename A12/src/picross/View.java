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

/**
 * View Class contains all UI elements, contains methods for different modes of the game, where the UI differs in each.
 * @author Thomas Stanley
 *
 */
public class View extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel topLabels[] = new JLabel[5];
	private JLabel leftLabels[] = new JLabel[5];
	private JFrame frame = new JFrame();

	
	Color borderColour = new Color(25,25, 87);
	Color standardColour = new Color(106, 88, 188);
	Border border = BorderFactory.createLineBorder(borderColour, 3, true);
	
	/**
	 * Empty Constructor
	 */
	public View() {
	
	}
	
	/**
	 * Method Displays splash screen for five seconds before calling the launcher.
	 */
	public void splash() {
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
			System.out.println("Splash error");
		}
		splashWindow.setVisible(false);
		splashWindow.dispose();
		
		launcher();
	}
	
	/**
	 * Displays the launcher. The launcher contains some buttons for different play options and a menubar with some other functionality.
	 */
	public void launcher() {
		Controller buttons = new Controller();
		Border border = BorderFactory.createLineBorder(borderColour, 3, true);
		
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
		JMenu online = new JMenu("C/S A32");
		online.setBorder(BorderFactory.createLineBorder((Color.red), 5));
		JMenu edit = new JMenu("Edit");
		JMenuItem client = new JMenuItem("Client");
		JMenuItem server = new JMenuItem("Server");
		JMenuItem changeBC = new JMenuItem("Change Border Colour");
		JMenuItem changeLC = new JMenuItem("Change Label Colour");
		JMenuItem loadGrid = new JMenuItem("Load Grid");
		JMenuItem saveGrid = new JMenuItem("Save Grid");
		JMenuItem exit = new JMenuItem("Exit");
		JMenuItem howTo = new JMenuItem("How to Play");
		
		changeBC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				borderColour = JColorChooser.showDialog(null, "Border Colour Change", borderColour);
				
			}
			
		});
		
		client.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Client client = new Client();
			}
			
		});
		
		server.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Server server = new Server();
			}
			
		});
		
		URL changeURL = Game.class.getResource("/images/piciconcol.gif");
		ImageIcon changeIcon = new ImageIcon(changeURL);
		changeBC.setIcon(changeIcon);
	
		changeLC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				standardColour = JColorChooser.showDialog(null, "Border Colour Change", standardColour);
				
			}
			
		});
		
		changeLC.setIcon(changeIcon);
		
		loadGrid.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser loader = new JFileChooser();
				int response = loader.showOpenDialog(null);
				if(response==JFileChooser.APPROVE_OPTION) {
					File filePicker = new File(loader.getSelectedFile().getAbsolutePath());
					buttons.playMode(buttons.fileLoader(filePicker), true, false, buttons.myModel);
					frame.dispose();
				}
				
			}
			
		});
		URL loadURL = Game.class.getResource("/images/piciconabt.gif");
		ImageIcon loadIcon = new ImageIcon(loadURL);
		loadGrid.setIcon(loadIcon);
		
		saveGrid.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				design(false);
			}
			
		});
		URL saveURL = Game.class.getResource("/images/piciconnew.gif");
		ImageIcon saveIcon = new ImageIcon(saveURL);
		saveGrid.setIcon(saveIcon);
		
		
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		
		URL exitURL = Game.class.getResource("/images/piciconext.gif");
		ImageIcon exitIcon = new ImageIcon(exitURL);
		exit.setIcon(exitIcon);
		
		howTo.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		        JPanel howToPanel = new JPanel(new BorderLayout());
		        howToPanel.setBackground(standardColour);
		        howToPanel.setBorder(border);
		        
		        JLabel boxLabel = new JLabel("<html>Fill in the play grid by looking at the numbers in the top and left hand side boxes. These numbers indicate how many correct tiles there are in that column/row. Try to figure out which ones are correct then hit 'Submit'.<br/><br/>The clock is ticking, and an incorrect guess will cost you three seconds, while a correct guess will give you three extra seconds.<br/><br/>Good Luck!");
		        boxLabel.setFont(new Font("Arial", Font.BOLD, 20));
		        boxLabel.setForeground(borderColour);
		        boxLabel.setHorizontalAlignment(SwingConstants.CENTER);
		        boxLabel.setVerticalAlignment(SwingConstants.TOP);
		        howToPanel.add(boxLabel, BorderLayout.CENTER);
		        
		        JButton closeButton = new JButton("Close");
		        closeButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                frame.getContentPane().remove(howToPanel);
		                frame.revalidate();
		                frame.repaint();
		            }
		        });
		        howToPanel.add(closeButton, BorderLayout.SOUTH);
		        frame.add(howToPanel, BorderLayout.CENTER);
		        frame.revalidate();
		        frame.repaint();
		    }
		});
		
		URL howURL = Game.class.getResource("/images/piciconhlp.gif");
		ImageIcon howIcon = new ImageIcon(howURL);
		howTo.setIcon(howIcon);
		
		file.add(loadGrid);
		file.add(saveGrid);
		file.add(exit);
		edit.add(changeBC);
		edit.add(changeLC);
		online.add(client);
		online.add(server);
		help.add(howTo);
		menuBar.add(file);
		menuBar.add(edit);
		menuBar.add(online);
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
		
		//random option
		JButton rand = new JButton("Random");
		rand.setPreferredSize(new Dimension(95,45));
		rand.setFont(new Font("Bad Signal", Font.BOLD, 15));
		rand.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				buttons.randPlay();
			}
					
		});
		
		
		//radio button creation for localisation
		JRadioButton eng = new JRadioButton("English");
		JRadioButton ger = new JRadioButton("German");
		
		eng.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Localisation not implemented");
			}
			
		});
		
		ger.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Localisation not implemented");
			}
			
		});
		
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
		launcherPanel.add(rand);
		
		frame.add(launcherPanel, BorderLayout.SOUTH);
		
		frame.pack();
		frame.setVisible(true);
		
	}
	
	/**
	 * Method displays the design mode. Boolean value to indicate whether method has been called in save mode. If it has, the button text is different, and the instruction text is different.
	 * @param isDesign Passed to indicate whether method has been called from save mode.
	 */
	public void design(boolean isDesign) {
		
		Controller buttons = new Controller();
		Border border = BorderFactory.createLineBorder(borderColour, 3, true);
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
		leftPanel.setBackground(standardColour);
		centerPanel.setBackground(standardColour);
		topPanel.setBackground(standardColour);
		topNums.setBackground(standardColour);
		rightPanel.setBackground(standardColour);
		clockPanel.setBackground(standardColour);
		logoPanel.setBackground(standardColour);
		
		
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
		viewClueCreate(topNums,border,true, buttons, buttons.myModel);
		//making labels for left panel
		viewClueCreate(leftPanel, border, false, buttons, buttons.myModel);
		
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
	
	/**
	 * Method is called to start play mode. Model is passed from Controller if called from there.
	 * @param model Model object passed from Controller.
	 */
	public void play(Model model) {
		Controller buttons = new Controller();
		Border border = BorderFactory.createLineBorder(borderColour, 3, true);
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
		leftPanel.setBackground(standardColour);
		centerPanel.setBackground(new Color(85, 54, 217));
		
		topPanel.setBackground(standardColour);
		topNums.setBackground(standardColour);
		
		rightPanel.setBackground(standardColour);
		scorePanel.setBackground(Color.black);
		
		clockPanel.setBackground(standardColour);
		logoPanel.setBackground(standardColour);
		
		
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
		
		//making labels for top panel
		viewClueCreate(topNums,border,true, buttons, model);
		//making labels for left panel
		viewClueCreate(leftPanel, border, false, buttons, model);
		
		
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
		buttons.countdown(clockLabel, frame);
		
		
		
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
	
	
	/**
	 * Method displays clues on both top and side panels on game board. Uses for loop to change label details and passes label[] index to clueNum method to set its text.
	 * @param panel Panel passed from View to add the labels created to. Both topPanel and leftPanel will be passed from View.
	 * @param border border passed from View for use on labels.
	 * @param top Boolean value to indicate if method is being called for the top labels, or the left hand side labels.
	 * @param buttons Controller object passed from View to call its clueNum method on each label[] element.
	 * @param model Model object passed from View. Normally this would not be needed as the method is in the Model class, however the specific Model object from View needs
	 * 		  to be manipulated.
	 */
	public void viewClueCreate(JPanel panel,Border border, boolean top, Controller buttons, Model model) {
		if(top==true) {
		for(int i = 0;i<5;i++) {
			getLeftLabels()[i]=new JLabel();
			getLeftLabels()[i].setForeground(Color.BLACK);
			getLeftLabels()[i].setFont(new Font("Arial", Font.PLAIN, 40));
			getLeftLabels()[i].setHorizontalAlignment(JLabel.CENTER);
			getLeftLabels()[i].setBorder(border);
			getLeftLabels()[i].setText(buttons.clueNum(true, i, model.getPlayGrid()));
			panel.add(getLeftLabels()[i]);
			}
		}else{
		for(int i = 0;i<5;i++) {
			getLeftLabels()[i]=new JLabel();
			getLeftLabels()[i].setForeground(Color.BLACK);
			getLeftLabels()[i].setFont(new Font("Arial", Font.PLAIN, 40));
			getLeftLabels()[i].setHorizontalAlignment(JLabel.CENTER);
			getLeftLabels()[i].setBorder(border);
			getLeftLabels()[i].setText(buttons.clueNum(false, i, model.getPlayGrid()));
			panel.add(getLeftLabels()[i]);
			}
		}
	}

	/**
	 * getter for topLabel array
	 * @return topLabels array
	 */
	public JLabel[] getTopLabels() {
		return topLabels;
	}

	/**
	 * setter for topLabels array
	 * @param topLabels topLabels variable
	 */
	public void setTopLabels(JLabel topLabels[]) {
		this.topLabels = topLabels;
	}

	/**
	 * getter for leftLabels array
	 * @return leftLabels
	 */
	public JLabel[] getLeftLabels() {
		return leftLabels;
	}

	/**
	 * setter for leftLabels array
	 * @param leftLabels leftLabels variable
	 */
	public void setLeftLabels(JLabel leftLabels[]) {
		this.leftLabels = leftLabels;
	}

}
