package picross;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Thomas Stanley
 * Class contains all buttons and button functionality for both Design
 * and Play modes.
 */
public class Controller extends JFrame implements ActionListener{
	
	/*
	 * required serial#
	 */
	private static final long serialVersionUID = 1L;
	
	View myView = new View();
	Model myModel = new Model();
	
	JButton buttonArr[][] = new JButton[5][5];
	
	JButton designSubmit = new JButton();
	JButton playSubmit = new JButton();
	
	/**
	 * Empty Class Constructor
	 */
	public Controller(){
		
		
	}
	
	/**
	 * Calls the splash() method in View Class. Creates 'Main Menu' frame with a few different options.
	 */
	public void start(){
		myView.splash();
	}
	
	/**
	 * Method is called by view method when the 'play' button is clicked on the launcher menu. Creates game with default grid.
	 */
	public void launcherPlay() {
		playMode(myModel.getGrid(), false, false, myModel);
	}
	
	/**
	 * Method is called by view method if 'Random' is clicked in launcher menu. Creates randomised game.
	 */
	public void randPlay() {
		playMode(myModel.getGrid(), true, true, myModel);
	}
	
	
	/**
	 * @param panel Center Panel passed from Design Mode
	 * @param frame Design Frame, used to close design window upon 'Submit' button click
	 * The method that contain the action listener for the "Submit" button in design mode.
	 */
	public void designSubmit(JPanel panel, JFrame frame) {
		
		designSubmit.setText("Submit");
		panel.add(designSubmit, BorderLayout.SOUTH);
		
		designSubmit.addActionListener(new ActionListener(){
			

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				playMode(myModel.getGrid(), true,false, myModel);
			}
			
		});
	}
	
	/**
	 * Button is used in the design panel for 'Save' mode to save the grid to a file for later use.
	 * @param panel	The panel passed by View Class is the scoreBoard Panel.
	 * @param frame	The frame passed by View class to dispose of once the button is clicked.
	 */
	public void saveSubmit(JPanel panel, JFrame frame) {
		
		designSubmit.setText("Save");
		panel.add(designSubmit, BorderLayout.SOUTH);
		
		designSubmit.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				saveToFile(myModel.getGrid());
				myModel.setSavedGridFileNum(myModel.getSavedGridFileNum() + 1);
				myView.launcher();
			}
			
		});
	}
	
	/**
	 * Button in play mode to submit your finished grid during play. Upon correct grid, message and final score is displayed, on incorrect guess message is displayed.
	 * @param panel Panel for button to be added on to, panel passed from View class is scorePanel.
	 * @param frame Frame passed from View class to be disposed if correct guess made.
	 * @param model Model object passed to compare grids from two different model objects. If those two grids match, condition is met.
	 */
	public void playSubmit(JPanel panel, JFrame frame, Model model) {

	    playSubmit.setText("Submit");
	    panel.add(playSubmit, BorderLayout.SOUTH);
	    playSubmit.addActionListener(new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if(gridCompare(myModel.getGrid(), model.getGrid())) {
	                model.setFinalScore(myModel.getSecond()*model.getScore());
	                myModel.getScoreLabel().setText(myModel.getScoreLabel().getText()+"<br/>You Win!<br/>Final Score:<br/>"+model.getFinalScore()+"<br/>Returning...");
	                Timer timer = new Timer();
	                timer.schedule(new TimerTask() {
	                    boolean executed = false;

	                    @Override
	                    public void run() {
	                        if (!executed) {
	                            executed = true;
	                            frame.dispose();
	                            myView.launcher();
	                        }
	                    }
	                }, 3000);
	            } else {
	                myModel.getScoreLabel().setText(myModel.getScoreLabel().getText()+"Try Again!!<br/>");
	            }
	        }
	    });
	}
		
	/**
	 * Creates and maintains the timer displayed in the top right of the play window. 'Second' variable in Model class is displayed in the label and
	 * is decremented by 1 every second. If 'Second' reaches 0, a message is displayed, and the window closes after a three second delay.
	 * @param label Label passed from View class for the 'Second' variable to be displayed on.
	 * @param frame Frame passed form View class to be disposed if the time runs out.
	 */
	public void countdown(JLabel label, JFrame frame) {
	    Timer timer = new Timer();
	    timer.scheduleAtFixedRate(new TimerTask() {
	        @Override
	        public void run() {
	            if (myModel.getSecond() >= 10) {
	                label.setText("00:" + myModel.getSecond());
	            } else if (myModel.getSecond() < 10) {
	                label.setText("00:0" + myModel.getSecond());
	            }
	            if (myModel.getSecond() > 0) {
	                myModel.setSecond(myModel.getSecond() - 1);
	            } else {
	            	label.setText("0:00");
	                myModel.getScoreLabel().setText("<html>Time Out!!<br/>You Lose!!");
	                playSubmit.setEnabled(false);
	                Timer timer2 = new Timer();
	                timer2.schedule(new TimerTask() {
	                    boolean executed = false;

	                    @Override
	                    public void run() {
	                        if (!executed) {
	                            executed = true;
	                            frame.dispose();
	                            myView.launcher();
	                        }
	                    }
	                }, 3000);
	                timer.cancel();
	            }
	        }
	    }, 0, 1000);
	}
	
	/**
	 * Method is called for each button in a button array that makes the grid in the center of the play board.
	 * Gives each button some details and an actionListener
	 * @param panel Panel passed from View class for the button to be added. Panel passed from View is centerPanel.
	 * @param isPlay Boolean value to differentiate between design and play modes for the buttons, since button behaviour is different in both modes.
	 * @param model Model object passed from play(Model model) method in View class.
	 */
	public void buttonDetails(JPanel panel, boolean isPlay,Model model) {
	    for(int row = 0; row < buttonArr.length; row++) {
	        for(int col = 0; col < buttonArr[row].length; col++) {
	            final int r = row;
	            final int c = col;
	            buttonArr[row][col]=new JButton();
	            buttonArr[row][col].setBackground((new Color(176, 144, 245)));
	            buttonArr[row][col].setBorder(BorderFactory.createBevelBorder(1,new Color(25,25, 87),new Color(106, 88, 188)));
	            
	            buttonArr[row][col].addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                	myModel.getGrid()[r][c]=1;
         
               
	                	if(isPlay==true){
	                		//if the play grid element is a 0, the empty grid will be set back to 0.
	                		//This makes it easier to identify a win condition,
	                		//as the two arrays will be completely the same if the correct tiles
	                		//are selected
	                		if(model.getPlayGrid()[r][c]==0) {
	                			myModel.getGrid()[r][c]=0;
	                		}
	                		//if the two grids are equal, AND are both 1, to stop empty tiles matching as well, correct guess is detected.
	                		if(model.getPlayGrid()[r][c]==myModel.getGrid()[r][c] && model.getPlayGrid()[r][c]==1) {
	                    		model.setScore(model.getScore() + 100);
	                    		myModel.increaseTime();
	                    		buttonArr[r][c].setEnabled(false);
	                    		buttonArr[r][c].setBackground(new Color(25,25, 87));
	                    		myModel.getScoreLabel().setText(myModel.getScoreLabel().getText()+model.getScore()+"<br/>");
	                    	}else {
	                    		myModel.decreaseTime();
	                    	}
	                	}else {
	                		buttonArr[r][c].setEnabled(false);
                    		buttonArr[r][c].setBackground(new Color(25,25, 87));
	                	}
	                }
	                
	            });
	            panel.add(buttonArr[row][col]);
	        }
	    }
	}
	
	
	/**
	 * Method called to start a game, either pre-designed, not designed, or random. This is done through boolean values passed.
	 * @param designGrid Grid designed by user. Is passed to setPlayGrid.
	 * @param isDesigned Value passed in method call to indicate if the grid has been designed or not.
	 * @param isRand Value passed in method call to indicate of the grid should be randomised.
	 * @param model Model object passed.
	 */
	public void playMode(int[][] designGrid, boolean isDesigned, boolean isRand, Model model) {
		
		model.setPlayGrid(model.mapGrid(isDesigned, isRand, designGrid, model.getGrid()));
		myView.play(model);
		
	}
	
	
	/**
	 * Method called by Model class in a for loop to display clues on the top and left panels in play mode.
	 * @param top Boolean value for if the top panel is being used. If false, the left panel is being used.
	 * @param z Index of Label array passed
	 * @param grid grid passed from Model object to iterate through for values
	 * @return returns a string to be passed to the label.setText() for the label in that iteration of the for loop in Model Class.
	 */
	public String clueNum(boolean top, int z, int[][] grid) {
	    int sum = 0;
	    if (top) {
	        for (int i = 0; i < grid.length; i++) {
	            sum += grid[i][z];
	        }
	    } else {
	        for (int j = 0; j < grid[z].length; j++) {
	            sum += grid[z][j];
	        }
	    }
	    return String.valueOf(sum);
	}
	
	
	/**
	 * Called from View class to be used as the int[][] parameter in playMode().
	 * Reads a file selected by the file picker and creates an int[][].
	 * @param file File picked by file picker in View
	 * @return int[][] from file returned
	 */
	int[][] fileLoader(File file){
	    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
	        String line;
	        int row = 0;
	        while ((line = br.readLine()) != null) {
	            String[] input = line.split(",");
	            for (int i = 0; i < 5; i++) {
	                myModel.getGrid()[row][i] = Integer.parseInt(input[i]);
	            }
	            row++;
	        }
	    } catch (Exception e) {
	        System.out.println("Read from file error.");
	        e.printStackTrace();
	    }
	    return myModel.getGrid();
	}
	
	/**
	 * Method called from design() in View class. Reads the grid given by user and saves to csv file for later loading. 
	 * @param grid Grid to be saved passed
	 */
	public void saveToFile(int[][] grid) {
	    JFileChooser saver = new JFileChooser();
	    int response = saver.showSaveDialog(null);
	    if (response == JFileChooser.APPROVE_OPTION) {
	        try {
	            File file = saver.getSelectedFile();
	            FileWriter writer = new FileWriter(file);
	            for (int i = 0; i < grid.length; i++) {
	                for (int j = 0; j < grid[i].length; j++) {
	                    writer.write(grid[i][j] + ",");
	                }
	                writer.write("\n");
	            }
	            writer.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
	}
	
	/**
	 * Method called by submitPlay() to check if the user given solution is correct. Compares two int[][], the correct one and the user created one.
	 * @param grid1 First int[][] to be compared
	 * @param grid2 Second int[][] to be compared
	 * @return returns boolean value for if the arrays are the same.
	 */
	public boolean gridCompare(int[][] grid1, int[][] grid2) {
	    for (int i = 0; i < 5; i++) {
	        for (int j = 0; j < 5; j++) {
	            if (grid1[i][j] != grid2[i][j]) {
	                return false;
	            }
	        }
	    }
	    return true;	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
}
	
	
