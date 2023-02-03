package picross;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

public class Game extends JFrame{
	
	Game(){
		
	}
	
	public static void main(String[] args) throws IOException{
		
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		try {
            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src/images/launcherBack2.png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
  
		
		Border border = BorderFactory.createLineBorder(new Color(25,25, 87), 3, true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(450,250);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		
		//setting icon
		frame.setTitle("Thomas Stanley - Picross Panic Launcher");
		ImageIcon icon = new ImageIcon("src/images/logo.png");
		frame.setIconImage(icon.getImage());
		
		
		JPanel testPanel = new JPanel();
		testPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		testPanel.setOpaque(false);
		JButton design = new JButton("Design");
		design.setPreferredSize(new Dimension(85,45));
		design.setFont(new Font("Bad Signal", Font.BOLD, 15));
		design.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Design design = new Design();
			}
			
		});
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
		testPanel.add(design);
		testPanel.add(play);
		
		frame.add(testPanel, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
		
	}

}
