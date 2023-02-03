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
	int second=15;
	
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
	
	JButton designSubmit = new JButton();
	
	public Controller(){
		
	}
	
	//center button creation
	void CenterPanelButtons(JPanel panel){

	
	//1st row
	buttonDetails(panel, zeroZero,0,0);

	buttonDetails(panel, zeroOne,0,1);

	buttonDetails(panel, zeroTwo,0,2);

	buttonDetails(panel, zeroThree,0,3);
	
	buttonDetails(panel, zeroFour, 0,4);
	

	//second row
	
	buttonDetails(panel, oneZero, 1,0);
		
	buttonDetails(panel, oneOne,1,1);
	
	buttonDetails(panel, oneTwo,1,2);
	
	buttonDetails(panel, oneThree,1,3);
	
	buttonDetails(panel, oneFour,1,4);
	
	
	//third row
	buttonDetails(panel, twoZero,2,0);
	
	buttonDetails(panel, twoOne,2,1);
	
	buttonDetails(panel, twoTwo,2,2);
	
	buttonDetails(panel, twoThree,2,3);
	
	buttonDetails(panel, twoFour,2,4);
	
	
	//fourth row
	buttonDetails(panel, threeZero,3,0);
	
	buttonDetails(panel, threeOne,3,1);
	
	buttonDetails(panel, threeTwo,3,2);
	
	buttonDetails(panel, threeThree,3,3);
	
	buttonDetails(panel, threeFour,3,4);
	
	
	//fifth row
	buttonDetails(panel, fourZero,4,0);
	
	buttonDetails(panel, fourOne,4,1);
	
	buttonDetails(panel, fourTwo,4,2);
	
	buttonDetails(panel, fourThree,4,3);
	
	buttonDetails(panel, fourFour,4,4);
	
		
	
	}
	
	
	public void designButtons(JPanel panel, JFrame frame) {
		boolean isDesigned = true;
		designSubmit.setText("Submit");
		panel.add(designSubmit);
		
		designSubmit.addActionListener(new ActionListener(){
			

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Play play = new Play (grid, true);
			}
			
		});
	}
		
	//countdown panel
	public void countdown(JLabel label, JLabel rightLabel) {
		Timer timer = new Timer(1000, new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {
				second--;
				label.setText("00:"+second);
				if(second < 50) {
					
				
				}
				
			}
			
		});
		timer.start();
		
	}
	
	
	public void buttonDetails(JPanel panel,JButton button,int x, int y) {
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				grid[x][y]=1;
			}
			
		});
		panel.add(button);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
