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
	private Random rand;
	
	private String nextLastMove;
	private String lastMove;
	
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
	
	private void reset()
	{
		stones = rand.nextInt(5) + 21;
		gameOver = false;
		isPlayerTurn = true;
		
		nextLastMove = "";
		lastMove = "";
		
		stonesLabel.setText(stoneString(stones));
		updateMoves("You make the first move.");
	}
	
	private void updateMoves(String newMove)
	{
		nextLastMove = lastMove;
		lastMove = newMove;
		movesLabel.setText("<html>" + nextLastMove + "<br>" + lastMove + "</html>");
	}
	
	private String stoneString(int n)
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
	
	private boolean takeStones(int n)
	{
		stones = stones - n;
		
		//returns a boolean: true ~ game over; false ~ game is still going
		if(stones <= 0)
		{
			//game over
			stones = 0;
			
			//update stone pile
			stonesLabel.setText(stoneString(stones));
			return true;
		}
		else
		{
			//game still going
			//update stone pile
			stonesLabel.setText(stoneString(stones));
			return false;
		}
	}
	
	private void playerTurn(int stonesTaken)
	{
		//only active if game is not over and it's the player's turn
		if(!gameOver && isPlayerTurn)
		{
			//player's turn to take stones
			gameOver = takeStones(stonesTaken);
			updateMoves("You took " + stonesTaken + " stones.");
			//check if player lost this turn
			if(gameOver)
			{
				isPlayerTurn = false;
				//updateMoves("You took the last stone.");
				updateMoves("You Lose...");
			}
			else
			{
				//computer's turn
				isPlayerTurn = false;
				
				//computer takes random number of stones (1-3)
				int compTake = rand.nextInt(3) + 1;
				gameOver = takeStones(compTake);
				updateMoves("The computer took " + compTake + " stones.");
				
				//check if computer just lost
				if(gameOver)
				{
					isPlayerTurn = false;
					//updateMoves("The computer took the last stone.");
					updateMoves("You Win!");
				}
				else
				{
					//if computer did not lose this turn, game continues
					isPlayerTurn = true;
				}
			}
		}
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
				playerTurn(1);
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
				playerTurn(2);
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
				playerTurn(3);
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
