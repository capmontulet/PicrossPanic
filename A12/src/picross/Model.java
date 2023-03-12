package picross;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Model {
	
	private int[][] grid = new int[5][5];
	private int[][] playGrid = new int[5][5];

	JLabel topLabels[] = new JLabel[5];
	JLabel leftLabels[] = new JLabel[5];
	JLabel scoreLabel = new JLabel();
	
	
	private int second=15;
	int score = 0;
	int sumRow;
	int savedGridFileNum = 1;
	int finalScore = 0;
	
	
	
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
			return defaultGrid;
		}else {
		return designGrid;
		}
		
	}
	
	public void clueCreate(JPanel panel,Border border, boolean top, Controller buttons, Model model) {
		if(top==true) {
		for(int i = 0;i<5;i++) {
			topLabels[i]=new JLabel();
			topLabels[i].setForeground(Color.BLACK);
			topLabels[i].setFont(new Font("Arial", Font.PLAIN, 40));
			topLabels[i].setHorizontalAlignment(JLabel.CENTER);
			topLabels[i].setBorder(border);
			topLabels[i].setText(buttons.clueNum(true, i, model.getPlayGrid()));
			panel.add(topLabels[i]);
			}
		}else{
		for(int i = 0;i<5;i++) {
			leftLabels[i]=new JLabel();
			leftLabels[i].setForeground(Color.BLACK);
			leftLabels[i].setFont(new Font("Arial", Font.PLAIN, 40));
			leftLabels[i].setHorizontalAlignment(JLabel.CENTER);
			leftLabels[i].setBorder(border);
			leftLabels[i].setText(buttons.clueNum(false, i, model.getPlayGrid()));
			panel.add(leftLabels[i]);
			}
		}
	}

	public void scoreText(JPanel panel) {
		scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
		scoreLabel.setForeground(Color.white);
		scoreLabel.setText("<html>Score:<br/>");
		scoreLabel.setAutoscrolls(true);
		panel.add(scoreLabel);
	}

	public int getSecond() {
		return second;
	}



	public void setSecond(int second) {
		this.second = second;
	}



	public int[][] getGrid() {
		return grid;
	}



	public void setGrid(int[][] grid) {
		this.grid = grid;
	}
	
	
	public int[][] getPlayGrid() {
		return playGrid;
	}

	public void setPlayGrid(int[][] playGrid) {
		this.playGrid = playGrid;
	}
	
	public void increaseTime() {
		this.second+=3;
	}
	
	public void decreaseTime() {
		this.second-=3;
	}
	
	
	

}
