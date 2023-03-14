package picross;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * Model Class contains the variables behind the working of the game. Contain methods for manipulating the int[][] and labels associated with gameplay.
 * @author Thomas Stanley
 *
 */
public class Model {
	
	private int[][] grid = new int[5][5];
	private int[][] playGrid = new int[5][5];
	private JLabel scoreLabel = new JLabel();
	private Random rand = new Random();

	
	private int second=15;
	private int score = 0;
	private int sumRow;
	private int savedGridFileNum = 1;
	private int finalScore = 0;
	
	
	/**
	 * Method called when the play grid needs to be defined. If called with no design, a default grid is used.
	 * If called after grid is designed, the designed grid is returned.
	 * If called for random play, the grid elements are randomised
	 * @param isDesigned Boolean value passed to indicate if the grid has been designed or not
	 * @param isRand Boolean value to indicated if 'random' has been selected
	 * @param designGrid designGrid passed, this grid is used if designed beforehand
	 * @param defaultGrid returned for both play grid and random grid modes.
	 * @return grid returned by method
	 */
	int[][] mapGrid(boolean isDesigned, boolean isRand, int[][] designGrid, int[][] defaultGrid) {
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
			return defaultGrid;
		}else if(isRand) {
			for(int row = 0; row<defaultGrid.length; row++) {
				for(int col = 0; col < defaultGrid[row].length;col++) {
					defaultGrid[row][col]= rand.nextInt(2);
				}
			}
		return defaultGrid;	
		}else{
		return designGrid;
		}
		
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
	public void clueCreate(JPanel panel,Border border, boolean top, Controller buttons, Model model) {
		if(top==true) {
		for(int i = 0;i<5;i++) {
			buttons.myView.getTopLabels()[i]=new JLabel();
			buttons.myView.getTopLabels()[i].setForeground(Color.BLACK);
			buttons.myView.getTopLabels()[i].setFont(new Font("Arial", Font.PLAIN, 40));
			buttons.myView.getTopLabels()[i].setHorizontalAlignment(JLabel.CENTER);
			buttons.myView.getTopLabels()[i].setBorder(border);
			buttons.myView.getTopLabels()[i].setText(buttons.clueNum(true, i, model.getPlayGrid()));
			panel.add(buttons.myView.getTopLabels()[i]);
			}
		}else{
		for(int i = 0;i<5;i++) {
			buttons.myView.getLeftLabels()[i]=new JLabel();
			buttons.myView.getLeftLabels()[i].setForeground(Color.BLACK);
			buttons.myView.getLeftLabels()[i].setFont(new Font("Arial", Font.PLAIN, 40));
			buttons.myView.getLeftLabels()[i].setHorizontalAlignment(JLabel.CENTER);
			buttons.myView.getLeftLabels()[i].setBorder(border);
			buttons.myView.getLeftLabels()[i].setText(buttons.clueNum(false, i, model.getPlayGrid()));
			panel.add(buttons.myView.getLeftLabels()[i]);
			}
		}
	}

	/**
	 * Called by View to create 'Score' area on the play board.
	 * @param panel Panel passed from View to put label into.
	 */
	public void scoreText(JPanel panel) {
		getScoreLabel().setFont(new Font("Arial", Font.BOLD, 16));
		getScoreLabel().setForeground(Color.white);
		getScoreLabel().setText("<html>Score:<br/>");
		getScoreLabel().setAutoscrolls(true);
		panel.add(getScoreLabel());
	}

	/**
	 * getter for second variable
	 * @return returns second
	 */
	public int getSecond() {
		return second;
	}



	/**
	 * setter for second variable
	 * @param second second variable
	 */
	public void setSecond(int second) {
		this.second = second;
	}


	/**
	 * getter for grid variable
	 * @return returns second
	 */
	public int[][] getGrid() {
		return grid;
	}


	/**
	 * setter for grid variable
	 * @param grid grid variable
	 */
	public void setGrid(int[][] grid) {
		this.grid = grid;
	}
	
	/**
	 * getter for playGrid
	 * @return returns grid
	 */
	public int[][] getPlayGrid() {
		return playGrid;
	}

	/**
	 * setter for playGid
	 * @param playGrid playGrid variable
	 */
	public void setPlayGrid(int[][] playGrid) {
		this.playGrid = playGrid;
	}
	
	/**
	 * Increases time by three seconds. Used for a correct guess in play mode.
	 */
	public void increaseTime() {
		this.second+=3;
	}
	
	/**
	 * Decreases time by three seconds. Used for an incorrect guess in play mode.
	 */
	public void decreaseTime() {
		this.second-=3;
	}

	/**
	 * getter for finalScore
	 * @return finalScore
	 */
	public int getFinalScore() {
		return finalScore;
	}

	/**
	 * setter for finalScore
	 * @param finalScore finalScore variable
	 */
	public void setFinalScore(int finalScore) {
		this.finalScore = finalScore;
	}

	/**
	 * getter for score
	 * @return score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * setter for score
	 * @param score score variable
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * getter for sumRow
	 * @return sumRow
	 */
	public int getSumRow() {
		return sumRow;
	}

	/**
	 * setter for sumRow
	 * @param sumRow sumRow variable
	 */
	public void setSumRow(int sumRow) {
		this.sumRow = sumRow;
	}

	/**
	 * getter for savedGridFileNum
	 * @return savedGridFileNum
	 */
	public int getSavedGridFileNum() {
		return savedGridFileNum;
	}

	/**
	 * setter for savedGridFileNum
	 * @param savedGridFileNum savedGridFileNum variable
	 */
	public void setSavedGridFileNum(int savedGridFileNum) {
		this.savedGridFileNum = savedGridFileNum;
	}

	/**
	 * getter forscoreLabel
	 * @return scoreLabel
	 */
	public JLabel getScoreLabel() {
		return scoreLabel;
	}

	/**
	 * setter for scoreLabel
	 * @param scoreLabel scoreLabel variable
	 */
	public void setScoreLabel(JLabel scoreLabel) {
		this.scoreLabel = scoreLabel;
	}
	
	
	

}
