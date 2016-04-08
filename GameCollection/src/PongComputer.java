import java.awt.Color;
import java.awt.Graphics;

//All AI logic
public class PongComputer {
	/**
	 * Integer to represent the x coordinate of the current location of the computer
	 */
	int x;
	/**
	 * Integer to represent the y coordinate of the current location of the computer
	 */
	int y;
	/**
	 * Integer to represent the width of the paddle
	 */
	int width;
	/**
	 * Integer to represent the height of the paddle
	 */
	int height;
	/**
	 * Integer to represent the x speed of the paddle
	 */
	int xSpeed;
	/**
	 * Integer to represent the y speed of the paddle
	 */
	int ySpeed;
	//constructor
	/**
	 * The PongComputer constructor. Constructs a new computer instance, setting x coordinate and y coordinate to middle right of the field
	 * @param game the game instance to which pong computer will be referring to in order to determine its movements
	 */
	public PongComputer(PongGame game){
		x = 575;
		y = 175;
		width = 10;
		height = 50;
		xSpeed = 0;
		ySpeed = 0;		
	}
	//draw
	/**
	 * The painComponent for pong computer paddle
	 * @pre Graphics g has been validly declared elsewhere, PongGame taking place
	 * @post Will draw the computer paddle according to the graphic instructions
	 * @param g Graphics g, the graphics content that will be doing the painting
	 */
	public void paintComponent(Graphics g){
		g.setColor(Color.GREEN); //sets paddle color
		//g.drawRect(x, y, width, height);
		g.fillRect(x, y, width, height); //fills the paddle accordingly
	}
	//move
	/**
	 * The move function, deals with all computer AI logic to move accordingly to the ball
	 * @pre PongGame occurring and valid PongBall declaration
	 * @post Will move the computer according to PongBall's position
	 * @param ball The PongBall the computer is referring to in all movement decisions
	 */
	public void move(PongBall ball){
		int yPosition = ball.y; //gets y position
		int difference = -(y + (height/2) - yPosition); //determines difference
		if(difference < 0 && difference < -6){ //determines whether paddle will move up, down, or remain where it is
			difference = - 5;
		} else if(difference > 0 && difference > 6){
			difference = 5;
		}
		//move paddle
		y+= difference;
		ySpeed = 5;
		if(y < 0){ //if paddle is off the top
			y = 0;
			ySpeed = 0;
		} else if(y + height > 400){ //if paddle is off the bottom 
			y = 400-height;
			ySpeed = 0;
		}
		
		
	}

}
