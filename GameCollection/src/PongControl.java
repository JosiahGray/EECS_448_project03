//where input will come in
//will implement action listener
//then updates human move, so human doesn't have to do weird action listener stuff
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Component;

import javax.swing.*;
import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
public class PongControl extends JFrame implements KeyListener{
	//https://docs.oracle.com/javase/tutorial/uiswing/events/keylistener.html
	JPanel content;
	PongGame theGame;
	Dimension d;
	PongDisplay display;
	PongBall ball;
	PongHuman human;
	PongComputer computer;
	
	public PongControl(PongGame game){
		super("PONG!");
		content = new JPanel();
		theGame = game;
		ball = game.ball;
		human = game.human;
		computer = game.computer;
		d = new Dimension(600,400);
		//display = new PongDisplay(game);
		createAndDisplayGUI();
		
	}
	public void createAndDisplayGUI(){
		//this.getContentPane().add(display.getContent());
		this.setLayout(new FlowLayout());
		this.setMinimumSize(d);
		//this.getContentPane().add(display);
		this.getContentPane().setBackground(Color.BLACK);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	public Component getContent(){
		return (content);
	}
	public void update(){
		human.move();
		ball.move(human, computer);
	}
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		ball.paint(g);
	}
	public void keyPressed(KeyEvent e){
		//get key codes
		int id = e.getID();
		System.out.println(id);
		
	}
	public void keyReleased(KeyEvent e){
		
	}
	public void keyTyped(KeyEvent e){
		; 
	}

}
