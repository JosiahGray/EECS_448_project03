/*
*	@author Paul McElroy
* Idea for extending TimerTask and using executeGameLoop as my run function
* gotten from Liia Butler.
*/
import java.awt.Graphics;
import java.util.TimerTask;
public class ShootySnakeDriver extends TimerTask {
	//constructor
	ShootySnakeGame game;
	Graphics panelGraphics;
	long remaining;
	long nextFrameStart;
	public ShootySnakeDriver(){
		game = new ShootySnakeGame();
	}
	//step function
	public void run(){
		game.executeGameLoop();
	}

}
