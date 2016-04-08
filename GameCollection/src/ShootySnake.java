+/*
 +*	@author Paul McElroy
 +* Idea for extending TimerTask and using executeGameLoop as my run function
 +* gotten from Liia Butler.
 +*/
import java.util.Timer;
public class ShootySnake {
	public ShootySnake()
	{
		Timer myTimer = new Timer();
		myTimer.schedule(new ShootySnakeDriver(), 0, 10);
	}
}
