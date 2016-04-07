import javax.swing.*;
import java.awt.event.*;
//import java.awt.Component;
//import java.awt.GridLayout;

public class GameCollection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		createAndDisplayGUI();
	}
	
	private static void createAndDisplayGUI()
	{
		//declare components
		JFrame frame = new JFrame("Game Collection");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuNim;
		JMenuItem menuPong;
		JMenuItem menuMerge;
		
		//create menu components
		menuBar = new JMenuBar();
		menu = new JMenu("Games");
		
		menuNim = new JMenuItem("Nim");
		menuNim.addActionListener(nimListener());
		
		menuPong = new JMenuItem("Pong");
		menuPong.addActionListener(pongListener());
		
		menuMerge = new JMenuItem("Merge Conflict");
		menuMerge.addActionListener(mergeListener());
		
		//build menu
		menu.add(menuNim);
		menu.add(menuPong);
		menu.add(menuMerge);
		
		menuBar.add(menu);
		
		frame.setJMenuBar(menuBar);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	private static ActionListener nimListener()
	{
		ActionListener listener = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				/*
				 * put code to run Nim here
				 */
				
				//set up Nim game
				JFrame nimFrame = new JFrame("Nim");
				//nimFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exit when closed
				Nim nimGame = new Nim();
				
				nimFrame.getContentPane().add(nimGame.getContent());
				nimFrame.pack();
				nimFrame.setVisible(true);
				
			}
		};
		
		return listener;
	}
	
	private static ActionListener pongListener()
	{
		ActionListener listener = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				/*
				 * put code to run Pong here
				 */
				Pong pongGame = new Pong();
			}
		};
		
		return listener;
	}
	
	private static ActionListener mergeListener()
	{
		ActionListener listener = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				/*
				 * put code to run Merge Conflict here
				 */
			}
		};
		
		return listener;
	}
}
