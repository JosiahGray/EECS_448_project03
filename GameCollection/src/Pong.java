/*
 * 	@author Liia Butler
 * 
 */
import java.util.Timer;
/**
 * The main class of Pong, starts the timer, and timer task in pong game runs the program
 */
public class Pong {
	/**
	 * The "main method" of pong class. Acts as gateway into the pong universe
	 * @post new timer instance is created and scheduled new timer task PongGame executes every 10 ms
	 */
	public Pong(){ 
		Timer myTimer = new Timer();
		myTimer.schedule(new PongGame(), 0, 10);
	}
}
