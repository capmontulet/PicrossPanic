package picross;

/**
 * @author Thomas Stanley
 * Main class extends JFrame
 *
 */
public class Game{
	
	/**
	 * @param args Arguments given
	 * main Class creates Launcher with options for English and German,
	 * and Design and Play modes.
	 */
	public static void main(String[] args){
		
		Controller game = new Controller();
		game.start();
	}

}
