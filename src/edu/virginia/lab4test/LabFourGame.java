package edu.virginia.lab4test;

import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;

public class LabFourGame extends Game{
	
	Sprite mario = new Sprite("mario","mario.png");
	Sprite coin = new Sprite("coint", "coin.png");
	
	private QuestManager qm;
	private PickedUpEvent p;
	
	BufferedImage background = this.readImage("background.png");
			
	public LabFourGame() {
		super("Lab Four Test Game", 1440, 900);
		
		mario.setxPosition(100);
		mario.setyPosition(100);
		
		coin.setxPosition(1050);
		coin.setyPosition(350);
		
		this.addChild(mario);
		this.addChild(coin);
		
		qm = new QuestManager(coin);
		coin.addEventListener(qm, PickedUpEvent.COIN_PICKED_UP);
		p = new PickedUpEvent(PickedUpEvent.COIN_PICKED_UP, coin);
		qm.handleEvent(p);
	}
	
	private boolean gotIt() {
		return (Math.pow((mario.getxPosition()+mario.getUnscaledWidth()/2) - (coin.getxPosition()+coin.getUnscaledWidth()/2), 2)
				+ Math.pow((mario.getyPosition()+mario.getUnscaledHeight()/2) - (coin.getyPosition()+coin.getUnscaledHeight()/2), 2)) < 2500;
	}
	
	@Override
	public void update(ArrayList<String> pressedKeys){
		super.update(pressedKeys);
		if (coin != null & mario != null) {
			if(pressedKeys.contains("↑") && mario.getyPosition() > 0) {
				mario.setyPosition(mario.getyPosition() - 5);
			}
			if (pressedKeys.contains("↓") && mario.getyPosition() < (900-mario.getUnscaledHeight()*mario.getScaleY())) {
				mario.setyPosition(mario.getyPosition() + 5);
			}
			if (pressedKeys.contains("←") && mario.getxPosition() > 0) {
				mario.setxPosition(mario.getxPosition() - 5);
			}
			if (pressedKeys.contains("→") && mario.getxPosition() < (1440-mario.getUnscaledWidth()*mario.getScaleX())) {
				mario.setxPosition(mario.getxPosition() + 5);
			}
			if (gotIt()) {
				coin.dispatchEvent(p);
				coin.setVisible(false);
			}
		}
	}
	
	@Override
	public void draw(Graphics g){
		g.drawImage(background, 0, 0, null);
		super.draw(g);
		
		if (mario != null) {
			mario.draw(g);
		}
		if (coin != null) {
			coin.draw(g);
		}
	}
	
	public static void main(String[] args) {
		LabFourGame game = new LabFourGame();
		game.start();
	}

}
