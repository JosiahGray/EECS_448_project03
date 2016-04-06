import java.awt.Color;
import java.awt.Graphics;

//All AI logic
public class PongComputer {
	int x;
	int y;
	int width;
	int height;
	int xSpeed;
	int ySpeed;
	//constructor
	public PongComputer(PongGame game){
		x = 575;
		y = 0;
		width = 10;
		height = 50;
		xSpeed = 3;
		ySpeed = 0;		
	}
	//draw
	public void paintComponent(Graphics g){
		g.setColor(Color.GREEN);
		//g.drawRect(x, y, width, height);
		g.fillRect(x, y, width, height);	
	}
	//move
	public void move(PongBall ball){
		int yPosition = ball.y;
		int difference = -(y + (height/2) - yPosition);
		if(difference < 0 && difference < -6){
			difference = - 5;
		} else if(difference > 0 && difference > 6){
			difference = 5;
		}
		//move paddle
		y+= difference;
		ySpeed = 5;
		if(y < 0){
			y = 0;
			ySpeed = 0;
		} else if(y + height > 400){
			y = 400-height;
			ySpeed = 0;
		}
		
		
	}

}
