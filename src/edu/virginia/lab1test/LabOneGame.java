package edu.virginia.lab1test;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import com.sun.xml.internal.fastinfoset.algorithm.HexadecimalEncodingAlgorithm;

import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;

/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class LabOneGame extends Game {

	/* Create a sprite object for our game. We'll use mario */
	Sprite mario = new Sprite("Mario", "Mario.png");
	
	AnimatedSprite BigMario = new AnimatedSprite("BigMario", new ArrayList<String>(Arrays.asList("BigMario_1.png", "BigMario_2.png", "BigMario_3.png",
					"BigMario_4.png", "BigMario_5.png", "BigMario_6.png")));
	
	Timer t = new Timer();
	
	int hp = 15;
	int secondPassed = 0;
	int speed = 10;
	
	/**
	 * Constructor. See constructor in Game.java for details on the parameters given
	 * */
	public LabOneGame() {
		super("Lab One Test Game", 1000, 800);
		
		this.getScenePanel().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (secondPassed != 60 && hp > 0 && 
						e.getX() <= mario.getxPosition()+mario.getUnscaledWidth()*mario.getScaleX() && 
						e.getX() >= mario.getxPosition() && 
						e.getY() >= mario.getyPosition() && 
						e.getY() <= mario.getyPosition()+mario.getUnscaledHeight()*mario.getScaleY()) {
					hp--;
				}
			}
		});
		
		TimerTask task = new TimerTask() {
			public void run() {
				if (secondPassed < 60 && hp > 0) {
					secondPassed++;
				}
			}
		};
		t.scheduleAtFixedRate(task, 1000, 1000);
		
	}
	
	/**
	 * Engine will automatically call this update method once per frame and pass to us
	 * the set of keys (as strings) that are currently being pressed down
	 * */
	@Override
	public void update(ArrayList<String> pressedKeys){
		super.update(pressedKeys);
		
		/* Make sure mario is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
		if(mario != null && secondPassed != 60 && hp > 0) {
			if(pressedKeys.contains("↑") && mario.getyPosition() > 0) {
				mario.setyPosition(mario.getyPosition() - speed-(15-hp));
			}
			if (pressedKeys.contains("↓") && mario.getyPosition() < (800-mario.getUnscaledHeight()*mario.getScaleY())) {
				mario.setyPosition(mario.getyPosition() + speed+(15-hp));
			}
			if (pressedKeys.contains("←") && mario.getxPosition() > 0) {
				mario.setxPosition(mario.getxPosition() - speed-(15-hp));
			}
			if (pressedKeys.contains("→") && mario.getxPosition() < (1000-mario.getUnscaledWidth()*mario.getScaleX())) {
				mario.setxPosition(mario.getxPosition() + speed+(15-hp));
			}
			if (pressedKeys.contains("A")) {
				if (mario.getScaleX() < 4.0f && mario.getScaleY() < 4.0f) {
					mario.setScaleX(mario.getScaleX() + 0.1f);
					mario.setScaleY(mario.getScaleY() + 0.1f);
				}
			}
			if (pressedKeys.contains("S")) {
				if (mario.getScaleX() >= 0.1f && mario.getScaleY() >= 0.1f) {
					mario.setScaleX(mario.getScaleX() - 0.1f);
					mario.setScaleY(mario.getScaleY() - 0.1f);
				}
			}
			if (pressedKeys.contains("Q")) {
				mario.setRotation(mario.getRotation() + Math.PI/24);
			}
			if (pressedKeys.contains("W")) {
				mario.setRotation(mario.getRotation() - Math.PI/24);
			}
			if (pressedKeys.contains("V")) {
				if (mario.isVisible() == true) {
					mario.setVisible(false);
				}
				else mario.setVisible(true);
			}
			if (pressedKeys.contains("Z")) {
				if (mario.getAlpha() >= 0.1f) {
					mario.setAlpha(mario.getAlpha() - 0.1f);
				}
			}
			if (pressedKeys.contains("X")) {
				if (mario.getAlpha() <= 0.9f) {
					mario.setAlpha(mario.getAlpha() + 0.1f);
				}
			}
			if (pressedKeys.contains("I")) {
				//if (mario.getPivotPoint().y > 0) {
					mario.getPivotPoint().y = mario.getPivotPoint().y-5;
				//}
			}
			if (pressedKeys.contains("K")) {
				//if (mario.getPivotPoint().y < mario.getUnscaledHeight()*mario.getScaleY()) {
					mario.getPivotPoint().y = mario.getPivotPoint().y+5;
					//mario.setxPosition(mario.getyPosition()+10);
				//}
			}
			if (pressedKeys.contains("J")) {
				//if (mario.getPivotPoint().x > 0) {
					mario.getPivotPoint().x = mario.getPivotPoint().x-5;
					//mario.setxPosition(mario.getxPosition()-10);
				//}
			}
			if (pressedKeys.contains("L")) {
				//if (mario.getPivotPoint().x < mario.getUnscaledWidth()*mario.getScaleX()) {
					mario.getPivotPoint().x = mario.getPivotPoint().x+5;
					
				//}
			}
 			mario.update(pressedKeys);
		}
	}
	
	/**
	 * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
	 * the screen, we need to make sure to override this method and call mario's draw method.
	 * */
	@Override
	public void draw(Graphics g){
		super.draw(g);
		g.setFont(new Font("Din Condensed", Font.PLAIN, 80));
		g.drawString("Timer: " + (60-secondPassed), 750, 80);
		g.drawString("HP: "+hp, 400, 80);
		g.setFont(new Font("Din Condensed", Font.PLAIN, 150));
		
		
		/* Same, just check for null in case a frame gets thrown in before Mario is initialized */
		if(mario != null) {
			mario.draw(g);
			
		}
		if(BigMario != null) {
			g.translate(500, 400);
			BigMario.setAnimation("run", new int[]{0,5});
			BigMario.animate("run");
			BigMario.draw(g);
			g.translate(-500, -400);
		}
		g.drawRect(mario.getPivotPoint().x, mario.getPivotPoint().y, 5, 5);
		g.fillRect(mario.getPivotPoint().x, mario.getPivotPoint().y, 5, 5);
		
		
		if (hp != 0) {
			if (secondPassed == 60) {
				stop();
			g.drawString("Player 1 Wins!", 200, 400);
			}
		}
		else {
			stop();
			g.drawString("Player 2 Wins!", 200, 400);
		}
		
	}

	/**
	 * Quick main class that simply creates an instance of our game and starts the timer
	 * that calls update() and draw() every frame
	 * */
	public static void main(String[] args) {
		LabOneGame game = new LabOneGame();
		game.start();
	}
}
