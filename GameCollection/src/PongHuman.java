//does all human logic
import java.awt.*;
/**
 * 
 * PongControl which controls all of the display and user input of the pong game
 *
 */
public class PongHuman {
	/**
	 * Integer to represent the x coordinate of the current location of the human
	 */
	int x;
	/**
	 * Integer to represent the y coordinate of the current location of the human
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
	 * The PongHuman constructor. Constructs a new human instance, setting x coordinate and y coordinate to middle left of the field
	 * @post constructs new PongHuman instance
	 * @param game the game instance to which pong human will be referring to in order to determine its movements
	 */
	public PongHuman(){
		x = 15;
		y = 175;
		width = 10;
		height = 50;
		xSpeed = 0;
		ySpeed= 0;
	}
	//draw function
	/**
	 * The painComponent for pong human paddle
	 * @pre Graphics g has been validly declared elsewhere, PongGame taking place
	 * @post Will draw the computer paddle according to the graphic instructions
	 * @param g Graphics g, the graphics content that will be doing the painting
	 */
	public void paintComponent(Graphics g){
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);		
	}
	//move function
	/**
	 * The move function, deals with all logic to follow up after a move key has been implemented 
	 * @pre Valid pong game taking place and key press is giving correct input
	 * @param up Boolean value to determine whether paddle should be moved up or down
	 * @post Will move the human paddle up or down according to the boolean input
	 */
	public void move(Boolean up){
		int updatey;
		if(up){
			updatey = -5;
			
		} else{
			updatey = 5;
		}
		y += updatey;
		if(y<0){
			y = 0;
			ySpeed = 0;
		} else if (y + height > 400){
			y = 400 - height;
			ySpeed = 0;
		}
		
	}
	

}
