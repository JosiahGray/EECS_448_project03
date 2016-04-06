//does all human logic
import java.awt.*;
public class PongHuman {
	int x;
	int y;
	int width;
	int height;
	int xSpeed;
	int ySpeed;
	//constructor
	public PongHuman(){
		x = 15;
		y = 175;
		width = 10;
		height = 50;
		xSpeed = 0;
		ySpeed= 0;
	}
	//draw function
	public void paintComponent(Graphics g){
		g.setColor(Color.GREEN);
		g.drawRect(x, y, width, height);
		g.fillRect(x, y, width, height);
				
	}
	//move function
	public void move(Boolean up){
		System.out.println("hello friend");
		int updatey;
		if(up){
			updatey = -4;
			
		} else{
			updatey = 4;
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
