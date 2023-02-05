package picross;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * @author Thomas Stanley
 * Main class extends JFrame
 *
 */
public class Game extends JFrame{
	
	/**
	 * Required serial#
	 */
	private static final long serialVersionUID = 1L;

	Game(){
		
	}
	
	/**
	 * @param args Arguments given
	 * @throws IOException
	 * main Class creates Launcher with options for English and German,
	 * and Design and Play modes.
	 */
	public static void main(String[] args) throws IOException{
		
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		
		
		URL bgURL = Game.class.getResource("/images/launcherBack2.png");
		ImageIcon bg = new ImageIcon(bgURL);
        frame.setContentPane(new JLabel(bg));
      
  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(450,250);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		
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
				Design design = new Design();
			}
			
		});
		
		//play option opens new Play frame
		JButton play = new JButton("Play");
		play.setPreferredSize(new Dimension(85,45));
		play.setFont(new Font("Bad Signal", Font.BOLD, 15));
		play.addActionListener(new ActionListener() {
			int[][] grid;
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Play play = new Play(grid, false);
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

}
