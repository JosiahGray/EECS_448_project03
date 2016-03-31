//Where all logic will take place
import java.util.Timer;
import java.util.TimerTask;
public class PongGame extends TimerTask {
	//constructor
	PongDisplay background;
	PongHuman human;
	PongComputer computer;
	PongBall ball;
	public PongGame(){
		background = new PongDisplay();
		human = new PongHuman();
		computer = new PongComputer();
		ball = new PongBall();
	}
	public void run(){
		//where things will update
		human.move();
		computer.move(ball);
		//clear background graphics (take over to pong display)
		//draw everything...
	}

}
