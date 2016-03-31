/*
 * 	@author Liia Butler
 * 
 */


import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
public class Pong {
	public static void main(String[] args){ //see if this is needed
		Timer myTimer = new Timer();
		myTimer.schedule(new PongGame(), 0, 10);
		
	}
}
