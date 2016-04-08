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
public class PongControl extends JFrame implements KeyListener{
	//https://docs.oracle.com/javase/tutorial/uiswing/events/keylistener.html
	JPanel content;
	PongGame theGame;
	Dimension d;
	PongBall ball;
	PongHuman human;
	PongComputer computer;
	Graphics myGraph;
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
	public Dimension getPreferredSize(){
		return new Dimension(600, 400);
	}
	public Component getContent(){
		return (this);
	}
	public void update(){
		computer.move(ball);
		ball.move(human, computer);
	}
	public void paintComponent(Graphics g){
		//http://stackoverflow.com/questions/8980185/how-to-reduce-lag-in-my-java-2d-platformer
		super.paintComponents(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 600, 400);
		ball.paintComponent(g);
		human.paintComponent(g);
		computer.paintComponent(g);
		Toolkit.getDefaultToolkit().sync();
	}
	public void keyPressed(KeyEvent e){
		//get key codes
		char id = e.getKeyChar();
		if(id == '8'){
			human.move(true);
		} else if(id == '2'){
			human.move(false);
		}
	}
	public void keyReleased(KeyEvent e){
		;
	}
	public void keyTyped(KeyEvent e){
		;
	}
	public void run(PongGame game){
		update();
		removeAll();
		revalidate();
		this.paintComponent(super.getGraphics());
	}

}
