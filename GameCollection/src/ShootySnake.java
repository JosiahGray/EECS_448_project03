/*
*	@author Paul McElroy
* Idea for extending TimerTask and using executeGameLoop as my run function
* gotten from Liia Butler.
*/
import java.util.Timer;
public class ShootySnake {
		//constructor
		/**
		* The ShootySnake constructor. Constructs a new ShootySnake instance which creates a Timer, and a ShootySnakeDriver is run through that Timer's schedule method
		*/
	public ShootySnake()
	{
		Timer myTimer = new Timer();
		myTimer.schedule(new ShootySnakeDriver(), 0, 10);
	}
}
