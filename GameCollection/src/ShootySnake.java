/*
 * 	@author Liia Butler
 *
 */
import java.util.Timer;
public class ShootySnake {
	public ShootySnake(){ //see if this is needed
		Timer myTimer = new Timer();
		myTimer.schedule(new ShootySnakeDriver(), 0, 10);
	}
}
