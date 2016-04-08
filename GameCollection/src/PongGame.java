//Where all logic will take place
import java.awt.Graphics;
import java.util.TimerTask;
/**
 * 
 * @author lbutler
 */
/**
 * 
 * TimerTask extension that runs the pong game using pong control
 *
 */
public class PongGame extends TimerTask {
	//constructor
	/**
	 * 
	 */
	PongHuman human;
	/**
	 * 
	 */
	PongComputer computer;
	/**
	 * 
	 */
	PongBall ball;
	/**
	 * 
	 */
	Graphics g;
	/**
	 * 
	 */
	PongControl control;
	/**
	 * Custom constructor for the Pong game
	 */
	public PongGame(){
		human = new PongHuman();
		computer = new PongComputer(this);
		ball = new PongBall(this);
		control = new PongControl(this);
	}
	//step function
	/**
	 * 
	 */
	public void run(){
		control.run(this);
		
	}

}
