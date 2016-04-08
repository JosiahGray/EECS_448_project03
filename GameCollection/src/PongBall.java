import java.awt.Color;
import java.awt.Graphics;

/**
 * PongBall which controls all of the ball logic of the pong game
 */
public class PongBall {
	/**
	 * Integer to represent the x coordinate of the current location of the ball
	 */
	int x;
	/**
	 * Integer to represent the y coordinate of the current location of the ball
	 */
	int y;
	/**
	 * Integer to represent how fast the ball is moving in the x direction
	 */
	int xSpeed;
	/**
	 * Integer to represent how fast the ball is moving in the y direction
	 */
	int ySpeed;
	//constructor
	/**
	 * The PongBall constructor. Constructs a new ball instance, setting x coordinate and y coordinate to middle of field and ball speed to 3 to the left each timer function call
	 * @param game PongGame instance to which the ball is referring to when implementing ball behavior logic
	 */
	public PongBall(PongGame game){
		x = 300;
		y = 200;
		xSpeed = -3;
		ySpeed = 0;	
	}
	//draw
	/**
	 * Draws the ball and makes it a radius 7 ball
	 * @param g Graphics instance g to which the ball color and size are implemented and drawn
	 */
	public void paintComponent(Graphics g){
		g.setColor(Color.WHITE);
		g.fillOval(x, y, 7, 7);//size 7
	}
	//move function
	/**
	 * The move function that determines where the ball will be located the next timer task call and implements all colision logic
	 * @param human The pong human the ball refers to when checking for colision and "hit bonus"
	 * @param computer The pong computer the ball refers to when checking for colision
	 */
	public void move(PongHuman human, PongComputer computer){
		x+= xSpeed; //move x coordinate over by xspeed
		y+= ySpeed; //move  y coordinate over by yspeed
		int leftx = x-7; //assign the leftmost side of ball
		int rightx = x+7; //rightmost side of ball
		int topy = y-7; //top of ball
		int boty = y+7; //bottom of ball
		if(x < 0 || x > 600){ //if ball x coordinate is out of playing dimensions
			if(x > 600){ //if player scored
				//goal was scored
			} else { //if computer scored
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
			if((leftx < (human.x + human.width)) && (rightx > human.x) && (topy < (human.y + human.height)) && (boty > human.y)){ //if ball coordinates intersect with human coordinates, indicating colision 
				//reverse xspeed direction
				xSpeed = 3;
				//bonus speed in y direction according to where on the paddle the ball bounced off
				double  addition = (y - human.y)/50;
				ySpeed += (human.ySpeed /2) + addition;
				x += xSpeed; //move ball over
			}
			
		} else{ //check right side
			if(leftx < (computer.x + computer.width) && rightx > computer.x && topy<(computer.y + computer.height) && boty > computer.y){ //if ball coordinates intersect with computer coordinates, indicating colision
				xSpeed = -3; //reverse xspeed direction
				double addition = (y - computer.y) / 50; //bonus speed in y direction according to where on the paddle the ball bounced off
				ySpeed += (computer.ySpeed / 2) + addition; 
				x += xSpeed; //move ball over
			}
			
			
		}
		
		
	}
	/**
	 * Resets the ball to original coordinates, to center and moves ball to human side 
	 * @pre Valid Pong game and ball is off screen
	 * @post Ball is reset to original coordinates and x speed is towards human side and no y speed bonus has begun
	 */
	public void reset(){
		xSpeed = -3;
		ySpeed = 0;
		x = 300;
		y = 200;
		
	}

}
