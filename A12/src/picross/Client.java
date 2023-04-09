package picross;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.URL;
import javax.swing.*;

/**
 * @author Thomas Stanley
 * Client class constructs client GUI and functions
 */
public class Client extends JFrame{
	
    /**
	 * Required serialisation
	 */
	private static final long serialVersionUID = 1L;
	private JLabel piccrossLogoLabel;
    private JTextArea textArea;
    private JTextField userField;
    private JTextField serverField;
    private JTextField portField;
    private JButton connectButton;
    private JButton endButton;
    private JButton receiveGameButton;
    private JButton playButton;
    private Socket socket;
    private int[][] grid = new int[5][5];
    
    Controller buttons = new Controller();
    Model model = new Model();
	
	/**
	 * Client class constructor 
	 */
	public Client() {

	
    JPanel contentPane = new JPanel();
    contentPane.setLayout(new BorderLayout());
    setContentPane(contentPane);

    //logo pane
    JPanel topPanel = new JPanel();
    topPanel.setPreferredSize(new Dimension(0, 150)); // height of 200 pixels
    contentPane.add(topPanel, BorderLayout.PAGE_START);
    
    URL clientURL = Game.class.getResource("/images/piccorssLogoClient.png");
    ImageIcon clientIcon = new ImageIcon(clientURL);
    piccrossLogoLabel = new JLabel(clientIcon);
    topPanel.add(piccrossLogoLabel);

    // bottom panel has the text area
    JPanel bottomPanel = new JPanel(new BorderLayout());
    contentPane.add(bottomPanel, BorderLayout.CENTER);

    // input panel has the buttons
    JPanel inputPanel = new JPanel(new FlowLayout());
    bottomPanel.add(inputPanel, BorderLayout.PAGE_START);

    userField = new JTextField(10);
    JScrollPane userScrollPane = new JScrollPane(new JLabel("User:"));
    inputPanel.add(userScrollPane);
    inputPanel.add(userField);

    //default server set
    serverField = new JTextField(10);
    serverField.setText("localhost");
    JScrollPane serverScrollPane = new JScrollPane(new JLabel("Server:"));
    inputPanel.add(serverScrollPane);
    inputPanel.add(serverField);

    //default field set
    portField = new JTextField(10);
    portField.setText("2000");
    JScrollPane portScrollPane = new JScrollPane(new JLabel("Port:"));
    inputPanel.add(portScrollPane);
    inputPanel.add(portField);

    connectButton = new JButton("Connect");
    connectButton.setPreferredSize(new Dimension(90, 20));
    connectButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // connect to server with entered port number
                String server = serverField.getText();
                int port = Integer.parseInt(portField.getText());
                socket = new Socket(server, port);

                // give name of client to output stream
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                String clientName = userField.getText();
                outputStream.writeUTF(clientName);

                textArea.append("Connected to server " + server + " on port " + port + "\n");
            } catch (Exception ex) {
                textArea.append("Error: " + ex.getMessage() + "\n");
            }
        }
    });
    
    endButton = new JButton("End");
    endButton.setPreferredSize(new Dimension(60, 20));
    endButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Close the socket and display a message
                socket.close();
                textArea.append("Connection to server has been severed.\n");
            } catch (Exception ex) {
                textArea.append("Error: " + ex.getMessage() + "\n");
            }
        }
    });

    inputPanel.add(new JLabel());
    inputPanel.add(connectButton);
    inputPanel.add(endButton);

    JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 5, 5));
    bottomPanel.add(buttonPanel, BorderLayout.CENTER);

    
    receiveGameButton = new JButton("Fetch Grid");
    receiveGameButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // send message to the server to request grid
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                outputStream.writeUTF("request grid");

                // get grid from input stream
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                grid = (int[][]) inputStream.readObject();
                textArea.append("Grid Received\n");
            } catch (Exception ex) {
                textArea.append("Error: " + ex.getMessage() + "\n");
            }
        }
    });
    
    playButton = new JButton("Play");
    playButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
            	buttons.playMode(grid, true , false, model);
            } catch (Exception ex) {
               //
            }
        }
    });

    buttonPanel.add(receiveGameButton);
    buttonPanel.add(playButton);

    textArea = new JTextArea(5, 0);
    textArea.setText("Please wait for server to generate grid before fetching...\n");
    JScrollPane textScrollPane = new JScrollPane(textArea);
    bottomPanel.add(textScrollPane, BorderLayout.PAGE_END);

    //set frame details
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setTitle("Piccross Client");
    setPreferredSize(new Dimension(700, 325));
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
	}
}
