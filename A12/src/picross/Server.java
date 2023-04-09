package picross;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.Random;
import javax.swing.*;

/**
 * @author Thomas Stanley
 *	Server class constructs client GUI and functions
 */
public class Server extends JFrame{
	
    /**
	 * Required serialisation 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel piccorssLogoLabel;
    private JTextArea textArea;
	private JTextField portField;
    private JButton resultsButton;
    private JButton endButton;
    private JButton executeButton;
    private JButton generateButton;
    private ServerSocket serverSocket;
    private static Random random = new Random();
    private int[][] grid = new int[5][5];
	
	/**
	 * Server constructor
	 */
	public Server() {
		
	
    JPanel contentPane = new JPanel();
    contentPane.setLayout(new BorderLayout());
    setContentPane(contentPane);

    // logo pane
    JPanel topPanel = new JPanel();
    topPanel.setPreferredSize(new Dimension(0, 150));
    contentPane.add(topPanel, BorderLayout.PAGE_START);
    
    URL clientURL = Game.class.getResource("/images/piccorssLogoServer.png");
    ImageIcon clientIcon = new ImageIcon(clientURL);
    piccorssLogoLabel = new JLabel(clientIcon);
    topPanel.add(piccorssLogoLabel);

    // bottom panel for text area
    JPanel bottomPanel = new JPanel(new BorderLayout());
    contentPane.add(bottomPanel, BorderLayout.CENTER);

    //input panel for buttons
    JPanel inputPanel = new JPanel(new FlowLayout());
    bottomPanel.add(inputPanel, BorderLayout.PAGE_START);

    //port field has default port
    portField = new JTextField(10);
    portField.setText("2000");
    JScrollPane portScrollPane = new JScrollPane(new JLabel("Port:"));
    inputPanel.add(portScrollPane);
    inputPanel.add(portField);

    executeButton = new JButton("Execute");
    executeButton.setPreferredSize(new Dimension(90, 20));
    executeButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int port = Integer.parseInt(portField.getText());
            Server server = Server.this;
            try {
                serverSocket = new ServerSocket(port);
                textArea.append("Server started on port " + port + ".\n");
                textArea.append("Waiting for client connection...\n");

                // thread created for clients
                Thread serverThread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            while (true) {
                                // takes incoming client connections
                                Socket clientSocket = serverSocket.accept();

                                // new thread for multiple clients
                                new Thread(new ClientHandler(clientSocket, server)).start();
                            }
                        } catch (Exception ex) {
                            textArea.append("Client connection error: " + ex.getMessage() + "\n");
                        }
                    }
                };
                
                // swingworker implemented to prevent gui locking up from prolonged use
                SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        serverThread.start();
                        return null;
                    }
                };
                worker.execute();
            } catch (Exception ex) {
                textArea.append("Error starting server: " + ex.getMessage() + "\n");
            }
        }
    });
    
    resultsButton = new JButton("Results");
    resultsButton.setPreferredSize(new Dimension(90, 20));

    generateButton = new JButton("Generate Grid");
    generateButton.setPreferredSize(new Dimension(120,20));
    generateButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            setGrid(randomGrid()); // generate the grid
            textArea.append("Grid Generated");
        }
    });
    
    endButton = new JButton();
    endButton.setPreferredSize(new Dimension(60, 20));
    endButton.setText("End");
    endButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                serverSocket.close();
                textArea.append("Server socket closed.\n");
            } catch (Exception ex) {
                textArea.append("Error closing server socket: " + ex.getMessage() + "\n");
            }
        }
    });

    
    inputPanel.add(executeButton);
    inputPanel.add(resultsButton);
    inputPanel.add(generateButton);
    inputPanel.add(endButton);

    JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 0, 0));
    bottomPanel.add(buttonPanel, BorderLayout.CENTER);

    textArea = new JTextArea(5, 0);
    JScrollPane textScrollPane = new JScrollPane(textArea);
    bottomPanel.add(textScrollPane, BorderLayout.PAGE_END);

    // set frame details
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setTitle("Picross Server");
    setPreferredSize(new Dimension(650, 300));
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
	}

	/**
	 * Getter for textArea
	 * @return returns textArea
	 */
	public JTextArea getTextArea() {
		return textArea;
	}
	
	/**
	 * Setter for text area
	 * @param textArea textArea passed
	 */
	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	/**
	 * Client Handler class help to send and receive messages and data between server and clients.
	 * @author Thomas Stanley
	 */
	class ClientHandler implements Runnable {
	    private Socket socket;
	    private Server server;
	    
	    public ClientHandler(Socket socket, Server server) {
	        this.socket = socket;
	        this.server = server;
	    }
	    
	    public void run() {
	        try {
	            // getting client name
	            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
	            String clientName = inputStream.readUTF();
	            
	            // sends client IP and name to textArea
	            textArea.append("Client connected: " + socket.getInetAddress().getHostAddress() + ", Name: " + clientName + "\n");
	            
	            // if the clients requests a grid, it sends.
	            while (true) {
	                String message = inputStream.readUTF();
	                if (message.equals("request grid")) {
	                    try {
	                        // sends grid to client
	                        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
	                        outputStream.writeObject(server.getGrid());
	                        outputStream.flush();
	                    } catch (Exception ex) {
	                        textArea.append("Error sending grid to client.\n");
	                    }
	                }
	            }
	        } catch (Exception ex) {
	            textArea.append("Client Disconnected.\n");
	        }
	    }
	}

	/**
	 * randomGrid method returns a randomised a 5x5 2d integer array with 1's and 0's.
	 * @return randomised 2d array
	 */
	public static int[][] randomGrid() {
		int[][] grid = new int[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				grid[i][j] = random.nextInt(2);
			}
		}
		return grid;
	}

	
	/**
	 * getter for grid value
	 * @return grid value
	 */
	public int[][] getGrid() {
		return grid;
	}

	
	/**
	 * Setter for gird value
	 * @param grid grid value
	 */
	public void setGrid(int[][] grid) {
		this.grid = grid;
	}
}
