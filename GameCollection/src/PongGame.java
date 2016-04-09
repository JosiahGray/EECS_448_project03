//Where all logic will take place
import java.awt.Graphics;
import java.util.TimerTask;
/**
 * 
 * @author lbutler
 * TimerTask extension that runs the pong game using pong control
 *
 */
public class PongGame extends TimerTask {
	//constructor
	/**
	 * The PongHuman that will be playing in the game
	 */
	PongHuman human;
	/**
	 * The PongComputer that will be implementing the AI
	 */
	PongComputer computer;
	/**
	 * The PongBall that will be controlled in the game
	 */
	PongBall ball;
	/**
	 * The graphics component that will be used in drawing the graphics
	 */
	Graphics g;
	/**
	 * PongControl that controls pong
	 */
	PongControl control;
	/**
	 * Custom constructor for the Pong game
	 * Creates new human, computer, ball, and control instances for the game
	 */
	public PongGame(){
		human = new PongHuman();
		computer = new PongComputer(this);
		ball = new PongBall(this);
		control = new PongControl(this);
	}
	//step function
	/**
	 * The run function for the PongGame, calls control to run the actual Pong logic
	 * @post calls control's run method with this game instance
	 */
	public void run(){
		control.run(this);
		
	}

}
