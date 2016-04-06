//Where all logic will take place
import java.awt.Graphics;
import java.util.TimerTask;
public class PongGame extends TimerTask {
	//constructor
	PongHuman human;
	PongComputer computer;
	PongBall ball;
	Graphics g;
	PongControl control;
	public PongGame(){
		human = new PongHuman();
		computer = new PongComputer(this);
		ball = new PongBall(this);
		control = new PongControl(this);
	}
	//step function
	public void run(){
		control.run(this);
		
	}

}
