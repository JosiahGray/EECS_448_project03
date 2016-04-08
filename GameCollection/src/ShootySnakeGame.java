/*
 *  @author Paul McElroy
 *
 *  Adapted from and built on code/ideas by Michael Birken
 *  in his GameTemplate Example found here:
 *   http://meatfighter.com/gametemplate/
 *
 */

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ShootySnakeGame extends JFrame {

  private static final int WIDTH = 640;
  private static final int HEIGHT = 480;
  private static final int FRAMES_PER_SECOND = 60;
  private static final long FRAME_PERIOD = 1000000000L / FRAMES_PER_SECOND;

  private final boolean[] KEYS = new boolean[65535]; // pressed keys

  private final int BALLS = 200; //Total number of Balls that must be defeated
  private final int BEAMS = 100; //Max number of Beam balls on the screen at once
  private final double BALL_SIZE = WIDTH / 20; //Diameter of the enemy balls
  private final double BEAM_SIZE = BALL_SIZE/5; //Diameter of the Beam balls
  private final double PLAYER_SIZE = BALL_SIZE; //Diameter of the player ball
  private final double BALL_VELOCITY = 2; //How fast the balls move
  private final double DIV = 10; //Controls tightness of chain. 1 is tightest.
  private final int PLAYER_VELOCITY = 10; //How fast the player moves
  private JPanel panel;
  private Image image;
  private Graphics imageGraphics;
  private Ball[] balls = new Ball[BALLS]; //The chain of total enemy balls
  private Ball[] beams = new Ball[BEAMS]; //The chain of total Beam balls
  private Ball player; //The player object
  private long lastShot;
  private final long spawnInvulnerabilityCounter = 4000000000L;
  private long respawnStart;
  private int lives;
  private Boolean gameOver;
  private Boolean playerWon;

  public ShootySnakeGame() {
    initFrame();
    initImage();
    initModel();
  }

  public void executeGameLoop() {
    if(!gameOver) {
      long nextFrameStart = System.nanoTime();
      do {
        updateModel();
        nextFrameStart += FRAME_PERIOD;
      } while(nextFrameStart < System.nanoTime());
      renderFrame();
      long remaining = nextFrameStart - System.nanoTime();
      if (remaining > 0) {
        try {
          Thread.sleep(remaining / 1000000);
        } catch(Throwable t) {
        }
      }
    }
  }

  private void updateModel() {

    // Move the balls
    moveBalls();
    // Check if all balls are disabled
    checkBalls(); //victory condition
    // Move the player
    if (KEYS[KeyEvent.VK_LEFT] && player.x > 0) {
      player.x -= PLAYER_VELOCITY;
    }
    if (KEYS[KeyEvent.VK_RIGHT] && player.x < WIDTH - PLAYER_SIZE/2) {
      player.x += PLAYER_VELOCITY;
    }
    if (KEYS[KeyEvent.VK_UP] && player.y > 0) {
      player.y -= PLAYER_VELOCITY;
    }
    if (KEYS[KeyEvent.VK_DOWN] && player.y < HEIGHT - PLAYER_SIZE/2) {
      player.y += PLAYER_VELOCITY;
    }
    if (KEYS[KeyEvent.VK_SPACE] && (System.nanoTime() - lastShot > 1000000000L/10) &&
        (System.nanoTime() - respawnStart > spawnInvulnerabilityCounter))
    {
      lastShot = System.nanoTime();
      addBeam(player.x + PLAYER_SIZE/2, player.y + PLAYER_SIZE/2, 4, 0);
    }
    moveBeams();
    findCollisions();
  }

  private void renderFrame() {
    imageGraphics.setColor(Color.BLACK);
    imageGraphics.fillRect(0, 0, WIDTH, HEIGHT);
    if(!gameOver)
    {
      // Render balls
      for(int i = 0; i < BALLS; i++) {
        Ball ball = balls[i];
        if(!ball.disabled)
        {
          if(i != BALLS-1 && !balls[i+1].disabled)
            imageGraphics.setColor(Color.BLUE);
          else
            imageGraphics.setColor(Color.CYAN);
          imageGraphics.fillOval((int)(ball.x), (int)(ball.y), (int)BALL_SIZE, (int)BALL_SIZE);
        }
      }
      // Render player
      if(!(System.nanoTime() - respawnStart < spawnInvulnerabilityCounter))
      {
        imageGraphics.setColor(Color.GREEN);
      }
      else if(System.nanoTime() - respawnStart < spawnInvulnerabilityCounter/3)
        imageGraphics.setColor(Color.RED);
      else if(System.nanoTime() - respawnStart < spawnInvulnerabilityCounter*2/3)
        imageGraphics.setColor(Color.ORANGE);
      else
        imageGraphics.setColor(Color.YELLOW);
      imageGraphics.fillOval(
        (int)player.x, (int)player.y, (int)PLAYER_SIZE, (int)PLAYER_SIZE);

      // Render beams
      imageGraphics.setColor(Color.RED);
      for(int i = 0; i < BEAMS; i++)
      {
        if(!beams[i].disabled)
          imageGraphics.fillOval((int)(beams[i].x), (int)(beams[i].y), (int)BEAM_SIZE, (int)BEAM_SIZE);
      }

      //Render lives
      int fontSize = 20;
      imageGraphics.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
      imageGraphics.setColor(Color.GREEN);
      imageGraphics.drawString("LIVES " + lives, 10, 20);
    }
    else if(gameOver)
    {
      String message;
      if(!playerWon)
        message = "Game Over!";
      else
        message = "You Won!";
      int fontSize = 20;
      imageGraphics.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
      imageGraphics.setColor(Color.GREEN);
      imageGraphics.drawString(message, WIDTH/2, HEIGHT/2);
    }

    // Draw to screen
    Graphics panelGraphics = panel.getGraphics();
    if (panelGraphics != null) {
      panelGraphics.drawImage(image, 0, 0, null);
      panelGraphics.dispose();
    }
  }

  private void initFrame() {
    setTitle("Shooty Snake");
    setResizable(false);
    panel = (JPanel)getContentPane();
    panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  private void initImage() {
    image = createImage(WIDTH, HEIGHT);
    imageGraphics = image.getGraphics();
  }

  private void initModel() {
    // Set lives
    lives = 3;
    gameOver = false;

    player = new Ball(WIDTH/4, HEIGHT/2, 0, 0);
    for(double i = 0; i < BALLS; i++)
    {
      balls[(int)(BALLS - i - 1)] = new Ball(
        (WIDTH - BALL_SIZE)/2 + i*BALL_SIZE,
        (HEIGHT - BALL_SIZE)/2,
        BALL_VELOCITY,
        BALL_VELOCITY
      );
    }
    lastShot = System.nanoTime();
    for(int i = 0; i < BEAMS; i++)
    {
      beams[i] = new Ball(-100, -100, 0, 0);
      beams[i].disabled = true;
    }
  }

  public void processKeyEvent(KeyEvent e) {
    KEYS[e.getKeyCode()] = e.getID() == 401;
  }


  private void moveBalls()
  {
    for(int i = 0; i < BALLS; i++)
    {
      Ball ball = balls[i];

      if(i != BALLS - 1 && !balls[i+1].disabled)
      {
        ball.vx = (balls[i+1].x - ball.x);
        ball.vy = (balls[i+1].y - ball.y);
        ball.x += ball.vx/DIV;
        ball.y += ball.vy/DIV;
      }
      else
      {
        if (ball.x >= WIDTH - BALL_SIZE) {
          ball.vx = -ball.vx;
        } else if (ball.x <= 0) {
          ball.vx = -ball.vx;
        }
        if (ball.y >= HEIGHT - BALL_SIZE) {
          ball.vy = -ball.vy;
        } else if (ball.y <= 0) {
          ball.vy = -ball.vy;
        }
        if(i == BALLS-1)
        {
          ball.x += ball.vx;
          ball.y += ball.vy;
        }
        else
        {
          ball.x += ball.vx/DIV;
          ball.y += ball.vy/DIV;
        }
      }
    }
  }

  private void checkBalls()
  {
    int count = 0;
    for(int i = 0; i < BALLS; i++)
    {
      if(balls[i].disabled)
        count++;
    }
    if(count >= BALLS)
    {
      win();
    }
  }

  private void addBeam(double x, double y, double vx, double vy) {
    for(int i = 0; i < BEAMS-1; i++){
      beams[i] = beams[i+1];
    }
    beams[BEAMS-1] = new Ball(x,y,vx,vy);
  }

  private void moveBeams()
  {
    for(int i = 0; i < BEAMS; i++)
    {
      Ball beam = beams[i];
      beam.x += beam.vx;
      beam.y += beam.vy;
    }
  }

  private void findCollisions()
  {
      ballHitsPlayer();
      beamHitsBall();
  }

  private void ballHitsPlayer()
  {
    if(System.nanoTime() - respawnStart > spawnInvulnerabilityCounter)
      for(int i = 0; i < BALLS; i++)
      {
        if(circlesIntersect(balls[i], player, BALL_SIZE/2, PLAYER_SIZE/2) && !balls[i].disabled)
        {
          if(lives > 0)
            respawn();
          else
            lose();
        }
      }
  }

  private void beamHitsBall()
  {
    for(int i = 0; i < BEAMS; i++)
    {
      for(int j = 0; j < BALLS; j++)
      {
        if(circlesIntersect(beams[i], balls[j], BEAM_SIZE/4, BALL_SIZE/2) && !beams[i].disabled && !balls[j].disabled)
        {
          beams[i].disabled = true;
          balls[j].disabled = true;
        }
      }
    }
  }

  private Boolean circlesIntersect(Ball b1, Ball b2, double size1, double size2)
  {
    return java.awt.geom.Point2D.distance(b1.x + size1, b1.y + size1, b2.x + size2, b2.y + size2) < size1 + size2;
  }

  private void respawn()
  {
    lives--;
    respawnStart = System.nanoTime();
    lastShot = System.nanoTime();
    for(int i = 0; i < BEAMS; i++)
    {
      beams[i] = new Ball(-100, -100, 0, 0);
      beams[i].disabled = true;
    }
    player.x = WIDTH/2;
    player.y = HEIGHT/2;
  }

  private void lose()
  {
    gameOver = true;
    playerWon = false;
  }

  private void win()
  {
    gameOver = true;
    playerWon = true;
  }
}

class Ball {
  public double x;
  public double y;
  public double vx;
  public double vy;
  public Boolean disabled;
  public double radius;

  public Ball(double mx, double my, double mvx, double mvy) {
    x = mx;
    y = my;
    vx = mvx;
    vy = mvy;
    disabled = false;
  }
}
