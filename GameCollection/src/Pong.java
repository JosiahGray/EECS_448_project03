/*
 * 	@author Liia Butler
 * 
 */
import java.util.Timer;
public class Pong {
	public Pong(){ //see if this is needed
		Timer myTimer = new Timer();
		myTimer.schedule(new PongGame(), 0, 10);
		
		
	}
}
