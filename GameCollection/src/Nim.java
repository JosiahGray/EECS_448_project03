import javax.swing.*;
import java.awt.event.*;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.Random;

/**
 * A class that creates a Nim game.
 * @author Josiah Gray
 *
 */
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
	
	/**
	 * The constructor for the Nim class.
	 * Sets up the game and starts a new game.
	 */
	public Nim()
	{
		rand = new Random();
		
		//set up game panel
		setupGame();
		
		//start new game
		reset();
	}
	
	/**
	 * A method to get the content panel that holds the game.
	 * @return JPanel component that holds the game
	 */
	public Component getContent()
	{
		return gamePanel;
	}
	
	/**
	 * Resets the game, returning all variables to their initial state.
	 */
	private void reset()
	{
		//set game variables to initial values
		//(what they should look like at the start of the game)
		stones = rand.nextInt(5) + 21;
		gameOver = false;
		isPlayerTurn = true;
		
		nextLastMove = "";
		lastMove = "";
		
		stonesLabel.setText(stoneString(stones));
		updateMoves("You make the first move.");
	}
	
	/**
	 * Updates the moves played.
	 * The last move is moved to the second to last move
	 * and the last move is set equal to the newMove parameter.
	 * @param newMove
	 */
	private void updateMoves(String newMove)
	{
		//set second to last move equal to last move
		nextLastMove = lastMove;
		
		//set last move equal to the newMove parameter
		lastMove = newMove;
		
		//display updated moves
		movesLabel.setText("<html>" + nextLastMove + "<br>" + lastMove + "</html>");
	}
	
	/**
	 * Generates and returns a string telling the number of remaining stones
	 * and an ascii representation of the pile of remaining stones.
	 * @param n
	 * @return string representing the number of remaining stones
	 */
	private String stoneString(int n)
	{
		//add number of remaining stones to display string
		String display = "Remaining stones: " + n + "<br>";
		
		//generate ascii art representing the pile of remaining stones
		for(int i = 1; i <= n; i++)
		{
			display += "@";
			
			if((i%5) == 0)
			{
				display += "<br>";
			}
		}
		
		//add html tags to allow panel to display multiple lines
		display = "<html> " + display + " </html>";
		return display;
	}
	
	/**
	 * Subtracts the number of stones n from the total pile of stones.
	 * @param n
	 * @return false if game is over (number of stones == 0), true otherwise
	 */
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
	
	/**
	 * Execute player's turn and then follow with the computer's turn if the game is still going.
	 * @param stonesTaken
	 */
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
	
	/**
	 * Sets up the frame the game is played in.
	 */
	private void setupGame()
	{
		//create and set up GUI components
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
		
		//attach listeners to buttons
		button1.addActionListener(button1Listener());
		button2.addActionListener(button2Listener());
		button3.addActionListener(button3Listener());
		newGameButton.addActionListener(newGame());
		
		//add buttons to button panel
		buttonPanel.add(button1);
		buttonPanel.add(button2);
		buttonPanel.add(button3);
		
		//add components to game panel
		gamePanel.add(instrLabel);
		gamePanel.add(stonesLabel);
		gamePanel.add(movesLabel);
		gamePanel.add(buttonPanel);
		gamePanel.add(newGameButton);
	}
	
	/**
	 * Sets up the action listener for button1
	 * @return action listener for button1
	 */
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
	
	/**
	 * Sets up the action listener for button2
	 * @return action listener for button2
	 */
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
	
	/**
	 * Sets up the action listener for button3
	 * @return action listener for button3
	 */
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
	
	/**
	 * Sets up the action listener for new game button
	 * @return action listener for new game button
	 */
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
