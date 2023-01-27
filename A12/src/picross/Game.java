package picross;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;


public class Game extends JFrame implements ActionListener{
	

	int grid[][];
	int second=0;
	
	public Game(){

	
	
	
	}
	
	//center button creation
	void CenterPanelButtons(JPanel panel){
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
	
	
	zeroZero.setBounds(0, 0, 90, 76);
	zeroZero.setText("pos 0,0");
	panel.add(zeroZero);
	zeroOne.setBounds(90,0 , 90, 76);	
	zeroOne.setText("pos 0,1");
	panel.add(zeroOne);
	zeroTwo.setBounds(180, 0, 90, 76);
	panel.add(zeroTwo);
	
	oneZero.setBounds(0, 76, 90, 76);
	oneZero.setText("pos 1,0");
	panel.add(oneZero);
	
		
	}
	
	
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
		
	//countdown panel
	public void countdown(JLabel label) {
		Timer timer = new Timer(1000, new ActionListener() {
			
			
			

			@Override
			public void actionPerformed(ActionEvent e) {
				second++;
				label.setText(""+second);
				
			}
			
		});
		timer.start();
		
	}
	
	
}
