package edu.virginia.engine.display;

import java.util.ArrayList;
import java.util.List;

public class PhysicsSprite extends AnimatedSprite {
	
	private int gravity;
	private boolean havingG;

	public PhysicsSprite(String id, List<String> fileNames) {
		super(id, fileNames);
		setHavingG(false);
		setGravity(1);
	}


	public int getGravity() {
		return gravity;
	}

	public void setGravity(int gravity) {
		this.gravity = gravity;
	}

	public boolean isHavingG() {
		return havingG;
	}

	public void setHavingG(boolean havingG) {
		this.havingG = havingG;
	}

}
