/*
*	@author Paul McElroy
* Idea for extending TimerTask and using executeGameLoop as my run function
* gotten from Liia Butler.
*/
import java.awt.Graphics;
import java.util.TimerTask;
public class ShootySnakeDriver extends TimerTask {
	 /**
	 * ShootySnakeGame object that the user will interact with.
	 */
	ShootySnakeGame game;

	//constructor
	/**
	* The ShootySnakeDriver constructor. Constructs a new driver instance, setting game to a new ShootySnakeGame object, which starts the game.
	*/
	public ShootySnakeDriver(){
		game = new ShootySnakeGame();
	}

	//constructor
	/**
	* Defines the run method for ShootySnakeDriver, an extended TimerTask class. Run will run the ShootySnakeGame executeGameLoop using the Schedule functionality of the Timer class.
	*/
	public void run(){
		game.executeGameLoop();
	}

}
