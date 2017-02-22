package edu.virginia.lab3test;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;

public class LabThreeGame extends Game {
	
	Sprite sun = new Sprite("sun", "sun.png");
	
	Sprite mercury = new Sprite("mercury", "mercury.png");
	Sprite venus = new Sprite("venus", "venus.png");
	Sprite earth = new Sprite("earth", "earth.png");
	Sprite mars = new Sprite("mars", "mars.png");
	Sprite jupitor = new Sprite("jupitor", "jupitor.png");
	Sprite saturn = new Sprite("saturn", "saturn.png");
	Sprite uranus = new Sprite("uranus", "uranus.png");
	Sprite neptune = new Sprite("neptune", "neptune.png");
	
	Sprite moon = new Sprite("moon", "moon.png");
	Sprite martian_moon1 = new Sprite("martian_moon1", "martian_moon1.png");
	Sprite martian_moon2 = new Sprite("martian_moon2", "martian_moon2.png");
	BufferedImage space = this.readImage("space.png");
	
	int mercury_period = 70;
	int venus_period = 100;
	int earth_period = 350;
	int mars_period = 500;
	int jupiter_period = 400;
	int saturn_period = 500;
	int uranus_period = 600;
	int neptune_period = 700;
	int moon_period = 30;
	int martian_moon1_period = 50;
	int martian_moon2_period = 80;

	public LabThreeGame() {
		super("Lab Three Test Game", 1440, 900);
		this.addChild(sun);
		sun.addChild(mercury);
		sun.addChild(venus);
		sun.addChild(earth);
		sun.addChild(mars);
		sun.addChild(jupitor);
		sun.addChild(saturn);
		sun.addChild(uranus);
		sun.addChild(neptune);
		earth.addChild(moon);
		mars.addChild(martian_moon1);
		mars.addChild(martian_moon2);
		
		this.setScaleX(0.3f);
		this.setScaleY(0.3f);
		this.setxPosition(720);
		this.setyPosition(450);
		this.setPivotPoint(new Point(720,450));
		sun.setxPosition((int)(-(sun.getUnscaledWidth()*sun.getScaleX())/2));
		sun.setyPosition((int)(-(sun.getUnscaledHeight()*sun.getScaleY())/2));
		sun.setPivotPoint(new Point(this.getxPosition(),this.getyPosition()));
		
		for (int i = 0; i < sun.getChildren().size(); i++) {
			sun.getChildByIndex(i).setPivotPoint(new Point((int)(sun.getUnscaledWidth()*sun.getScaleX())/2,
					(int)(sun.getUnscaledHeight()*sun.getScaleY())/2));
			//sun.getChildByIndex(i).setScaleX((i+2)/10);
			//sun.getChildByIndex(i).setScaleY((i+2)/10);
		}
		
		mercury.setScaleX(0.2f);
		mercury.setScaleY(0.2f);
		venus.setScaleX(0.25f);
		venus.setScaleY(0.25f);
		earth.setScaleX(0.3f);
		earth.setScaleY(0.3f);
		mars.setScaleX(0.4f);
		mars.setScaleY(0.4f);
		jupitor.setScaleX(0.5f);
		jupitor.setScaleY(0.5f);
		saturn.setScaleX(0.55f);
		saturn.setScaleY(0.55f);
		uranus.setScaleX(0.6f);
		uranus.setScaleY(0.6f);
		neptune.setScaleX(0.7f);
		neptune.setScaleY(0.7f);
		
		
		moon.setPivotPoint(new Point((int)(moon.getUnscaledWidth()*moon.getScaleX())/2,
				(int)(moon.getUnscaledHeight()*moon.getScaleY())/2));
		martian_moon1.setPivotPoint(new Point((int)(martian_moon1.getUnscaledWidth()*martian_moon1.getScaleX())/2,
				(int)(martian_moon1.getUnscaledHeight()*martian_moon1.getScaleY())/2));
		martian_moon2.setPivotPoint(new Point((int)(martian_moon2.getUnscaledWidth()*martian_moon2.getScaleX())/2,
				(int)(martian_moon2.getUnscaledHeight()*martian_moon2.getScaleY())/2));
		
		mercury.setxPosition(200);
		venus.setxPosition(300);
		earth.setxPosition(400);
		mars.setxPosition(550);
		jupitor.setxPosition(700);
		saturn.setxPosition(800);
		uranus.setxPosition(900);
		neptune.setxPosition(1000);
		
		moon.setScaleX(0.3f);
		moon.setScaleY(0.3f);
		moon.setxPosition(250);
		moon.setyPosition(250);
		
		martian_moon1.setScaleX(0.5f);
		martian_moon1.setScaleY(0.5f);
		martian_moon1.setxPosition(200);
		martian_moon1.setyPosition(200);
	
		martian_moon2.setScaleX(0.4f);
		martian_moon2.setScaleY(0.4f);
		martian_moon2.setxPosition(250);
		martian_moon2.setyPosition(250);
		
	}
	
	public void ellipse(Sprite center) {
		for (int i = 0; i < center.getChildren().size(); i++) {
			if(center.getChildByIndex(i).getRotation() >= Math.PI * 2) {
				center.getChildByIndex(i).setRotation(center.getChildByIndex(i).getRotation()% Math.PI * 2);  
				}
	        
	        if (center.getChildByIndex(i).getRotation() <= Math.PI) {
	        	center.getChildByIndex(i).setPivotPoint(new Point(center.getChildByIndex(i).getPivotPoint().x-1, center.getChildByIndex(i).getPivotPoint().y));
	        }
	        else {
	        	center.getChildByIndex(i).setPivotPoint(new Point(center.getChildByIndex(i).getPivotPoint().x+1, center.getChildByIndex(i).getPivotPoint().y));
	        	}
		}
	}

	@Override
	protected void update(ArrayList<String> pressedKeys) {
		super.update(pressedKeys);
		if(pressedKeys.contains("↑")) {
			this.setyPosition(this.getyPosition() - 20);
		}
		if (pressedKeys.contains("↓")) {
			this.setyPosition(this.getyPosition() + 20);
		}
		if (pressedKeys.contains("←")) {
			this.setxPosition(this.getxPosition() - 20);
		}
		if (pressedKeys.contains("→")) {
			this.setxPosition(this.getxPosition() + 20);
		}
		if (pressedKeys.contains("Q")) {
			if (this.getScaleX() < 4.0f && sun.getScaleY() < 4.0f) {
				this.setScaleX(this.getScaleX() + 0.02f);
				this.setScaleY(this.getScaleY() + 0.02f);
			}
		}
		if (pressedKeys.contains("W")) {
			if (this.getScaleX() >= 0.1f && this.getScaleY() >= 0.1f) {
				this.setScaleX(this.getScaleX() - 0.02f);
				this.setScaleY(this.getScaleY() - 0.02f);
			}
		}
		if (pressedKeys.contains("A")) {
			this.setRotation(this.getRotation() + Math.PI/24);
		}
		if (pressedKeys.contains("S")) {
			this.setRotation(this.getRotation() - Math.PI/24);
		}
		
		if (sun != null && earth !=null) {
			mercury.setRotation(mercury.getRotation()+Math.PI/mercury_period);
			venus.setRotation(venus.getRotation()+Math.PI/venus_period);
			earth.setRotation(earth.getRotation()+Math.PI/earth_period);
			mars.setRotation(mars.getRotation()+Math.PI/mars_period);
			jupitor.setRotation(jupitor.getRotation()+Math.PI/jupiter_period);
			saturn.setRotation(saturn.getRotation()+Math.PI/saturn_period);
			uranus.setRotation(uranus.getRotation()+Math.PI/uranus_period);
			neptune.setRotation(neptune.getRotation()+Math.PI/neptune_period);
			ellipse(sun);
			
			moon.setRotation(moon.getRotation()+Math.PI/moon_period);
			ellipse(earth);
			
			martian_moon1.setRotation(martian_moon1.getRotation()+Math.PI/martian_moon1_period);
			martian_moon2.setRotation(martian_moon2.getRotation()+Math.PI/martian_moon2_period);
			ellipse(mars);
		}
	}
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(space, 0, 0, null);
		//g.setColor(Color.white);
		//g.drawArc(545, 350, 350, 200, 0, 360);
		//g.drawArc(545, 275, 350, 350, 0, 360);
		super.draw(g);
		
	}

	public static void main(String[] args) {
		LabThreeGame game = new LabThreeGame();
		game.start();
		
	}

	

}
