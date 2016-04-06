import java.awt.Color;
import java.awt.Graphics;

public class PongBall {
	int x;
	int y;
	int xSpeed;
	int ySpeed;
	//constructor
	public PongBall(PongGame game){
		x = 300;
		y = 200;
		xSpeed = -3;
		ySpeed = 0;	
	}
	//draw
	public void paintComponent(Graphics g){
		g.setColor(Color.WHITE);
		g.fillOval(x, y, 7, 7);
	}
	//move function
	public void move(PongHuman human, PongComputer computer){
		x+= xSpeed;
		y+= ySpeed;
		int leftx = x-7;
		int rightx = x+7;
		int topy = y-7;
		int boty = y+7;
		if(x < 0 || x > 600){
			if(x > 600){
				//goal was scored
			} else {
				//human was scored on
			}
			ySpeed = 0; //stop ball
			//delay for celebration
			reset();
		}
		//ball hits wall
		//top wall
		if(y - 7 < 0){
			y = 7;
			ySpeed = -ySpeed;
		
		} else if(y + 7 > 400){ //bottom wall
			y = 393;
			ySpeed = -ySpeed;
		
		}
		//left side of field, where human is
		if(leftx < 300){
			if((leftx < (human.x + human.width)) && (rightx > human.x) && (topy < (human.y + human.height)) && (boty > human.y)){
				//reverse xspeed direction
				xSpeed = 3;
				//bonus speed
				double  addition = (y - human.y)/50;
				ySpeed += (human.ySpeed /2) + addition;
				x += xSpeed;
			}
			
		} else{ //check right side
			if(leftx < (computer.x + computer.width) && rightx > computer.x && topy<(computer.y + computer.height) && boty > computer.y){
				xSpeed = -3;
				double addition = (y - computer.y) / 50;
				ySpeed += (computer.ySpeed / 2) + addition;
				x += xSpeed;
			}
			
			
		}
		
		
	}
	public void reset(){
		xSpeed = -3;
		ySpeed = 0;
		x = 300;
		y = 200;
		
	}

}
