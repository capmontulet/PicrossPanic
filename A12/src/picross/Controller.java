package picross;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Controller extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	int[][] grid = new int[5][5];
	int second=60;
	
	JButton zeroZero = new JButton();
	JButton zeroOne = new JButton();
	JButton zeroTwo = new JButton();
	JButton zeroThree = new JButton();
	JButton zeroFour = new JButton();
	JButton oneZero = new JButton();
	JButton oneOne = new JButton();
	JButton oneTwo = new JButton();
	JButton oneThree = new JButton();
	JButton oneFour = new JButton();
	JButton twoZero = new JButton();
	JButton twoOne = new JButton();
	JButton twoTwo = new JButton();
	JButton twoThree = new JButton();
	JButton twoFour = new JButton();
	JButton threeZero = new JButton();
	JButton threeOne = new JButton();
	JButton threeTwo = new JButton();
	JButton threeThree = new JButton();
	JButton threeFour = new JButton();
	JButton fourZero = new JButton();
	JButton fourOne = new JButton();
	JButton fourTwo = new JButton();
	JButton fourThree = new JButton();
	JButton fourFour = new JButton();
	
	public Controller(){
		
	}
	
	//center button creation
	void CenterPanelButtons(JPanel panel){

	
	//1st row
	zeroZero.setBounds(0, 0, 90, 76);
	buttonDetails(panel, zeroZero, "pos 0,0",0,0);

	zeroOne.setBounds(90, 0, 90, 76);	
	buttonDetails(panel, zeroOne, "pos 0,1", 0,1);

	zeroTwo.setBounds(180, 0, 90, 76);
	buttonDetails(panel, zeroTwo, "pos 0,2", 0,2);

	zeroThree.setBounds(270, 0, 90, 76);
	buttonDetails(panel, zeroThree, "pos 0,3", 0,3);
	
	zeroFour.setBounds(360, 0, 90, 76);
	buttonDetails(panel, zeroFour, "pos 0,4", 0,4);
	

	//second row
	oneZero.setBounds(0, 76, 90, 76);
	buttonDetails(panel, oneZero, "pos 1,0", 1,0);
	
	oneOne.setBounds(90, 76, 90, 76);	
	buttonDetails(panel, oneOne, "pos 1,1", 1,1);
	
	oneTwo.setBounds(180, 76, 90, 76);
	buttonDetails(panel, oneTwo, "pos 1,2", 1,2);
	
	oneThree.setBounds(270, 76, 90, 76);
	buttonDetails(panel, oneThree, "pos 1,3", 1,3);
	
	oneFour.setBounds(360, 76, 90, 76);
	buttonDetails(panel, oneFour, "pos 1,4", 1,4);
	
	
	//third row
	twoZero.setBounds(0, 152, 90, 76);
	buttonDetails(panel, twoZero, "pos 2,0", 2,0);
	
	twoOne.setBounds(90, 152, 90, 76);	
	buttonDetails(panel, twoOne, "pos 2,1", 2,1);
	
	twoTwo.setBounds(180, 152, 90, 76);
	buttonDetails(panel, twoTwo, "pos 2,2", 2,2);
	
	twoThree.setBounds(270, 152, 90, 76);
	buttonDetails(panel, twoThree, "pos 2,3", 2,3);
	
	twoFour.setBounds(360, 152, 90, 76);
	buttonDetails(panel, twoFour, "pos 2,4", 2,4);
	
	
	//fourth row
	threeZero.setBounds(0, 228, 90, 76);
	buttonDetails(panel, threeZero, "pos 3,0",3,0);
	
	threeOne.setBounds(90, 228, 90, 76);	
	buttonDetails(panel, threeOne, "pos 3,1",3,1);
	
	threeTwo.setBounds(180, 228, 90, 76);
	buttonDetails(panel, threeTwo, "pos 3,2",3,2);
	
	threeThree.setBounds(270, 228, 90, 76);
	buttonDetails(panel, threeThree, "pos 3,3",3,3);
	
	threeFour.setBounds(360, 228, 90, 76);
	buttonDetails(panel, threeFour, "pos 3,4",3,4);
	
	
	//fifth row
	fourZero.setBounds(0, 304, 90, 76);
	buttonDetails(panel, fourZero, "pos 4,0",4,0);
	
	fourOne.setBounds(90, 304, 90, 76);	
	buttonDetails(panel, fourOne, "pos 4,1",4,1);
	
	fourTwo.setBounds(180, 304, 90, 76);
	buttonDetails(panel, fourTwo, "pos 4,2",4,2);
	
	fourThree.setBounds(270, 304, 90, 76);
	buttonDetails(panel, fourThree, "pos 4,3",4,3);
	
	fourFour.setBounds(360, 304, 90, 76);
	fourFour.setText("print arr");
	fourFour.addActionListener(e -> printArray(grid));
	panel.add(fourFour);
	
		
	
	}
	
	
		
	//countdown panel
	public void countdown(JLabel label, JLabel rightLabel) {
		Timer timer = new Timer(1000, new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {
				second--;
				label.setText("00:"+second);
				if(second < 50) {
					rightLabel.setText("Hurry!!");
				}
				
			}
			
		});
		timer.start();
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==fourFour) {
			
		}
		
	}
	
	//for demo
	public void printArray(int[][] array) {
		for (int i = 0; i < array.length; i++) { 
	         for (int j = 0; j < array[i].length; j++) { 
	            System.out.print(array[i][j] + " ");
	         }
	         System.out.println();
	      }
		System.out.println();
	}
	
	
	public void buttonDetails(JPanel panel,JButton button, String position, int x, int y) {
		button.setText(position);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				grid[x][y]=1;
			}
			
		});
		panel.add(button);
		
	}
	
}
