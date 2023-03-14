package picross;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//import javax.swing.Timer;
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
	
//	Timer timer = new Timer();
	
	/**
	 * Empty Class Constructor
	 */
	public Controller(){
		
		
	}
	
	public void start(){
		myView.launcher();
	}
	
	
	public void launcherPlay() {
		playMode(myModel.getGrid(), false, false, myModel);
	}
	
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
	
	public void saveSubmit(JPanel panel, JFrame frame) {
		
		designSubmit.setText("Save");
		panel.add(designSubmit, BorderLayout.SOUTH);
		
		designSubmit.addActionListener(new ActionListener(){
			

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				saveToFile(myModel.getGrid(), "Saved Grid"+Integer.toString(myModel.savedGridFileNum)+".csv");
				myModel.savedGridFileNum++;
				myView.launcher();
			}
			
		});
	}
	
	public void playSubmit(JPanel panel, JFrame frame, Model model) {

	    playSubmit.setText("Submit");
	    panel.add(playSubmit, BorderLayout.SOUTH);
	    
	    playSubmit.addActionListener(new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if(gridCompare(myModel.getGrid(), model.getGrid())) {
	                model.finalScore=myModel.getSecond()*model.score;
	                myModel.scoreLabel.setText(myModel.scoreLabel.getText()+"<br/>You Win!<br/>Final Score:<br/>"+model.finalScore+"<br/>Returning...");
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
	                myModel.scoreLabel.setText(myModel.scoreLabel.getText()+"Try Again!!<br/>");
	            }
	        }
	    });
	}
		
	//countdown panel
	/**
	 * @param label Label passed from both Design and Play modes.
	 * The method that runs the countdown clock in the clock panel.
	 * Uses an action listener to change time, and can also have events
	 * happen at certain times of play.
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
	                myModel.scoreLabel.setText("<html>Time Out!!<br/>You Lose!!");
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
	 * This method is used in Controller class to create button details of center panel of both Design and Play modes
	 * @param panel Center panel passed
	 * @param button Button passed from controller class
	 * @param x Row of 2d Array
	 * @param y Column of 2d Array
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
	                		if(model.getPlayGrid()[r][c]==myModel.getGrid()[r][c] && model.getPlayGrid()[r][c]==1) {
	                    		model.score+=100;
	                    		myModel.increaseTime();
	                    		buttonArr[r][c].setEnabled(false);
	                    		buttonArr[r][c].setBackground(new Color(25,25, 87));
	                    		myModel.scoreLabel.setText(myModel.scoreLabel.getText()+model.score+"<br/>");
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
	
	
	
	public void playMode(int[][] designGrid, boolean isDesigned, boolean isRand, Model model) {
		
		
		model.setPlayGrid(model.mapGrid(isDesigned, isRand, designGrid, model.getGrid()));
		myView.play(model);
		
		
		
	}
	
	
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
	
	int[][] fileLoader(File file,Model myModel){
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
	
	public void saveToFile(int[][] grid, String name) {
	    try {
	        Path filePath = Paths.get("src", "Saved Grids", name);
	        FileWriter writer = new FileWriter(filePath.toString());
	        for (int i = 0; i < grid.length; i++) {
	            for (int j = 0; j < grid[i].length; j++) {
	                writer.write(grid[i][j] + ",");
	            }
	            writer.write("\n");
	        }
	        writer.close();
	    } catch (Exception e) {
	        System.out.println("Save to file error.");
	    }
	}
	
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
	
	
