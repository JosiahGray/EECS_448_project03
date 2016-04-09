//where input will come in
//will implement action listener
//then updates human move, so human doesn't have to do weird action listener stuff
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Component;

import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
/**
 * 
 * @author lbutler
 * PongControl which controls all of the display and user input of the pong game
 *
 */
public class PongControl extends JFrame implements KeyListener{
	//https://docs.oracle.com/javase/tutorial/uiswing/events/keylistener.html
	/**
	 * 	JPanel used to set the background of the game
	 */
	JPanel content;
	/**
	 * 	PongGame instance to which the control will be referencing
	 */
	PongGame theGame;
	/**
	 * Dimensions that the game is desired to span over
	 */
	Dimension d;
	/**
	 * PongBall instance to which the control will be referencing
	 */
	PongBall ball;
	/**
	 * PongHuman instance to which the control will be referencing
	 */
	PongHuman human;
	/**
	 * PongComputer instance to which the control will be referencing
	 */
	PongComputer computer;
	/**
	 * Pong Control constructor
	 * @param game the pong game instance which will set all of the member variables to game's member variables
	 * @post creates new pong control instance
	 */
	public PongControl(PongGame game){
		super("PONG!");
		content = new JPanel();
		theGame = game;
		ball = game.ball;
		human = game.human;
		computer = game.computer;
		d = new Dimension(600,400);
		addKeyListener(this);
		createAndDisplayGUI();
	}
	/**
	 * Lays out the content that will be shown for the pong game
	 * @post shows new content for pong game
	 */
	public void createAndDisplayGUI(){
		//this.getContentPane().add(display.getContent());
		this.setLayout(new FlowLayout());
		this.setMinimumSize(d);
		this.setMaximumSize(d);
		content.setOpaque(true);
		this.setContentPane(content);
		this.getContentPane().setBackground(Color.BLACK);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		setFocusable(true);
		this.setVisible(true);
	}
	/**
	 * Will create and return a new dimension instance
	 * @pre Pong game taking place
	 * @post will return new dimension instance of desired size
	 * @return new Dimension instance of desired dimensions
	 */
	public Dimension getPreferredSize(){
		return new Dimension(600, 400);
	}
	/**
	 * Will return a component containing all content in PongControl
	 * @pre PongGame currently taking place
	 * @post Returns Component containing all current content
	 * @return Component containing all elements in PongControl
	 */
	public Component getContent(){
		return (this);
	}
	/**
	 * The general update function for the pong program
	 * @pre PongGame currently taking place
	 * @post moves computer and ball according to the .move instructions in each class
	 */
	public void update(){
		computer.move(ball);
		ball.move(human, computer);
	}
	/**
	 * The general drawing function for the pong program. Takes care of all graphics
	 * @pre PongGame currently taking place
	 * @post Draws all related components seen on screen of pong game
	 * @param Graphics g is the graphics object being referenced in all painting 
	 */
	public void paintComponent(Graphics g){
		//http://stackoverflow.com/questions/8980185/how-to-reduce-lag-in-my-java-2d-platformer
		super.paintComponents(g);
		g.setColor(Color.BLACK); //sets background to black
		g.fillRect(0, 0, 600, 400); //fills in background
		ball.paintComponent(g);
		human.paintComponent(g);
		computer.paintComponent(g);
		Toolkit.getDefaultToolkit().sync(); //supposed to improve lag time
	}
	/**
	 * @pre Valid PongGame being implemented and human has a keyboard to use
	 * @post If one of the designated move keys is pushed, will call the human move function accordingly
	 * @param e KeyEvent, the key pressed when key listener is listening
	 */
	public void keyPressed(KeyEvent e){
		//get key codes
		char id = e.getKeyChar();
		if(id == '8'){ //will act as moving up
			human.move(true);
		} else if(id == '2'){//will act as moving down
			human.move(false);
		}
	}
	/**
	 * Empty function, wanted when implementing key listener
	 */
	public void keyReleased(KeyEvent e){
		;
	}
	/**
	 *  Empty function, wanted when implementing key listener
	 */
	public void keyTyped(KeyEvent e){
		;
	}
	/**
	 * The timer task running function that does the updating and drawing work
	 * @param game PongGame that is taking place to which all graphics will refer to when drawing location
	 */
	public void run(PongGame game){
		update(); //updates position
		removeAll(); //removes all previous graphics
		revalidate(); //makes sure all graphic calls are valid
		this.paintComponent(super.getGraphics()); //paints
	}

}
