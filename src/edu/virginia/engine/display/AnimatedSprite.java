package edu.virginia.engine.display;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimatedSprite extends Sprite{
	private List<BufferedImage> frames = new ArrayList<BufferedImage>();
	private int currentFrame = 0;
	private int startIndex;
	private int endIndex;
	private int animationSpeed;
	private boolean isPlaying = true;
	private String animationName;
	private Map<String, int[]> animations = new HashMap<String, int[]>();
	
	public boolean isPlaying() {
		return isPlaying;
	}

	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}

	public void setAnimationSpeed(int animationSpeed) {
		this.animationSpeed = animationSpeed;
	}
	
	public void setAnimation(String name, int[] index) {
		this.animations.put(name, index);
	}
	
	public void animate(String name) {
		if (animations.containsKey(name)) {
			this.animationName = name;
			startIndex = animations.get(name)[0];
			endIndex = animations.get(name)[1];
		}
		
	}

	public AnimatedSprite(String id, List<String> fileNames) {
		super(id);
		for (String fileName : fileNames) {
			BufferedImage image = this.readImage(fileName);
			if (image != null) {
				frames.add(image);
			}
		}
	}
	
	@Override
	public void update(ArrayList<String> pressedKeys) {
		super.update(pressedKeys);
	}
	
	@Override
	public void draw(Graphics g) {
		if (isPlaying) {
			if (currentFrame < endIndex) {
				currentFrame++;
			}
			super.setImage(frames.get(currentFrame));
			if (currentFrame >= endIndex) {
				currentFrame = startIndex;
			}
		}
		super.draw(g);
	}
	
}
