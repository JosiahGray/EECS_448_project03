import javax.swing.*;
import java.awt.event.*;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.Random;

public class Nim
{
	private JPanel gamePanel;
	private JLabel instrLabel;
	private JLabel movesLabel;
	private JLabel stonesLabel;
	private JPanel buttonPanel;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton newGameButton;
	
	private int stones;
	private boolean gameOver;
	private boolean isPlayerTurn;
	private int playerTake;
	private int compTake;
	private Random rand;
	
	public Nim()
	{
		rand = new Random();
		
		setupGame();
		
		reset();
	}
	
	public Component getContent()
	{
		return gamePanel;
	}
	
	public void reset()
	{
		stones = rand.nextInt(5) + 21;
		gameOver = false;
		playerTake = 0;
		compTake = 0;
		
		stonesLabel.setText(stoneString(stones));
		movesLabel.setText("You make the first move.");
	}
	
	public String stoneString(int n)
	{
		String display = "Remaining stones: " + n + "<br>";
		
		for(int i = 1; i <= n; i++)
		{
			display += "@";
			
			if((i%5) == 0)
			{
				display += "<br>";
			}
		}
		
		display = "<html> " + display + " </html>";
		return display;
	}
	
	private void setupGame()
	{
		gamePanel = new JPanel(new GridLayout(0,1));
		buttonPanel = new JPanel(new GridLayout(1,3));
		
		instrLabel = new JLabel("<html> Welcome to Nim!<br>"
				+ "You and the computer will take turns<br>"
				+ "taking between 1-3 stones from the pile.<br>"
				+ "Whoever takes the last stone loses!</html>");
		stonesLabel = new JLabel("");
		movesLabel = new JLabel("");
		
		button1 = new JButton("1");
		button2 = new JButton("2");
		button3 = new JButton("3");
		newGameButton = new JButton("New Game");
		
		button1.addActionListener(button1Listener());
		button2.addActionListener(button2Listener());
		button3.addActionListener(button3Listener());
		newGameButton.addActionListener(newGame());
		
		buttonPanel.add(button1);
		buttonPanel.add(button2);
		buttonPanel.add(button3);
		
		gamePanel.add(instrLabel);
		gamePanel.add(stonesLabel);
		gamePanel.add(movesLabel);
		gamePanel.add(buttonPanel);
		gamePanel.add(newGameButton);
	}
	
	//set up action listeners
	/////////////////////////
	private ActionListener button1Listener()
	{
		ActionListener listener = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
			}
		};
		
		return listener;
	}
	
	private ActionListener button2Listener()
	{
		ActionListener listener = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
			}
		};
		
		return listener;
	}
	
	private ActionListener button3Listener()
	{
		ActionListener listener = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
			}
		};
		
		return listener;
	}
	
	private ActionListener newGame()
	{
		ActionListener listener = new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				reset();
			}
		};
		
		return listener;
	}
}
