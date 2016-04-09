import javax.swing.*;
import java.awt.event.*;
//import java.awt.Component;
//import java.awt.GridLayout;
/**
 * Pong Sources: 
 * @source //https://docs.oracle.com/javase/tutorial/uiswing/events/keylistener.html
 * @source //http://stackoverflow.com/questions/8980185/how-to-reduce-lag-in-my-java-2d-platformer
 *
 */

public class GameCollection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		createAndDisplayGUI();
	}
	/**
	 * Creates and displays user interface, implements menu items for each game on a menu and a frame
	 */
	private static void createAndDisplayGUI()
	{
		//declare components
		JFrame frame = new JFrame("Game Collection");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuNim;
		JMenuItem menuPong;
		JMenuItem menuShooty;

		//create menu components
		menuBar = new JMenuBar();
		menu = new JMenu("Games");

		menuNim = new JMenuItem("Nim");
		menuNim.addActionListener(nimListener());

		menuPong = new JMenuItem("Pong");
		menuPong.addActionListener(pongListener());

		menuShooty = new JMenuItem("Shooty Snake");
		menuShooty.addActionListener(shootyListener());

		//build menu
		menu.add(menuNim);
		menu.add(menuPong);
		menu.add(menuShooty);
		menuBar.add(menu);

		//create empty components for spacing
		JPanel emptyPanel;
		JLabel emptyLabel;
		emptyPanel = new JPanel();
		emptyLabel = new JLabel("                                        ");
		emptyPanel.add(emptyLabel);

		//add components to frame
		frame.setJMenuBar(menuBar);
		frame.getContentPane().add(emptyPanel);

		frame.pack();
		frame.setVisible(true);
	}
	/**
	 * Waits for action to implement nim game
	 * @return Action listener listener
	 */

	private static ActionListener nimListener()
	{
		ActionListener listener = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//set up Nim game
				JFrame nimFrame = new JFrame("Nim");
				Nim nimGame = new Nim();

				nimFrame.getContentPane().add(nimGame.getContent());
				nimFrame.pack();
				nimFrame.setLocationRelativeTo(null);
				nimFrame.setVisible(true);

			}
		};

		return listener;
	}
	/**
	 * Waits for action to implement pong game
	 * @return Action listener listener
	 */
	private static ActionListener pongListener()
	{
		ActionListener listener = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//set up Pong game
				Pong pongGame = new Pong();
			}
		};

		return listener;
	}
	/**
	 * Waits for action to implement shooty snake game
	 * @return Actionlistener listener
	 */

	private static ActionListener shootyListener()
	{
		ActionListener listener = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//set up Shooty Snake game
				ShootySnake shootyGame = new ShootySnake();
			}
		};

		return listener;
	}
}
