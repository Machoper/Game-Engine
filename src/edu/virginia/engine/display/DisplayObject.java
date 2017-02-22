package edu.virginia.engine.display;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventDispatcher;
import edu.virginia.engine.events.IEventListener;

/**
 * A very basic display object for a java based gaming engine
 * 
 * */
public class DisplayObject implements IEventDispatcher{

	/* All DisplayObject have a unique id */
	private String id;

	/* The image that is displayed by this object */
	private BufferedImage displayImage;
	
	/* Should be true iff this display object is meant to be drawn */
	private boolean visible = true;
	
	/* Describe the x,y position where this object will be drawn */
	private int xPosition = 0;
	private int yPosition = 0;
	
	/* The point, relative to the upper left corner of the image that is the origin of this object */
	private Point pivotPoint = new Point(0,0);
	
	/* Scales the image up or down. 1.0 would be actual size. */
	private float scaleX = 1.0f;
	private float scaleY = 1.0f;
	
	/* Defines the amount (in degrees or radians, your choice) to rotate this object */
	private double rotation;
	
	/* Defines how transparent to draw this object. */
	private float alpha = 1.0f;
	
	/* Setters and getters for fields */
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public int getxPosition() {
		return xPosition;
	}
	
	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}
	
	public int getyPosition() {
		return yPosition;
	}
	
	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}
	
	public Point getPivotPoint() {
		return pivotPoint;
	}
	
	public void setPivotPoint(Point pivotPoint) {
		this.pivotPoint = pivotPoint;
	}
	
	
	public float getScaleX() {
		return scaleX;
	}

	public void setScaleX(float scaleX) {
		this.scaleX = scaleX;
	}

	public float getScaleY() {
		return scaleY;
	}

	public void setScaleY(float scaleY) {
		this.scaleY = scaleY;
	}

	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}

	/**
	 * Constructors: can pass in the id OR the id and image's file path and
	 * position OR the id and a buffered image and position
	 */
	public DisplayObject(String id) {
		this.setId(id);
	}

	public DisplayObject(String id, String fileName) {
		this.setId(id);
		this.setImage(fileName);
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}


	/**
	 * Returns the unscaled width and height of this display object
	 * */
	public int getUnscaledWidth() {
		if(displayImage == null) return 0;
		return displayImage.getWidth();
	}

	public int getUnscaledHeight() {
		if(displayImage == null) return 0;
		return displayImage.getHeight();
	}

	public BufferedImage getDisplayImage() {
		return this.displayImage;
	}

	protected void setImage(String imageName) {
		if (imageName == null) {
			return;
		}
		displayImage = readImage(imageName);
		if (displayImage == null) {
			System.err.println("[DisplayObject.setImage] ERROR: " + imageName + " does not exist!");
		}
	}


	/**
	 * Helper function that simply reads an image from the given image name
	 * (looks in resources\\) and returns the bufferedimage for that filename
	 * */
	public BufferedImage readImage(String imageName) {
		BufferedImage image = null;
		try {
			String file = ("resources" + File.separator + imageName);
			image = ImageIO.read(new File(file));
		} catch (IOException e) {
			System.out.println("[Error in DisplayObject.java:readImage] Could not read image " + imageName);
			e.printStackTrace();
		}
		return image;
	}

	public void setImage(BufferedImage image) {
		if(image == null) return;
		displayImage = image;
	}


	/**
	 * Invoked on every frame before drawing. Used to update this display
	 * objects state before the draw occurs. Should be overridden if necessary
	 * to update objects appropriately.
	 * */
	protected void update(ArrayList<String> pressedKeys) {
		
	}

	/**
	 * Draws this image. This should be overloaded if a display object should
	 * draw to the screen differently. This method is automatically invoked on
	 * every frame.
	 * */
	public void draw(Graphics g) {
		
		if (displayImage != null && visible == true) {
			
			/*
			 * Get the graphics and apply this objects transformations
			 * (rotation, etc.)
			 */
			Graphics2D g2d = (Graphics2D) g;
			applyTransformations(g2d);

			/* Actually draw the image, perform the pivot point translation here */
			g2d.drawImage(displayImage, 0/*-displayImage.getWidth()/2*/, 0/*-displayImage.getHeight()*/,
					(int) (getUnscaledWidth()),
					(int) (getUnscaledHeight()), null);
			
			/*
			 * undo the transformations so this doesn't affect other display
			 * objects
			 */
			reverseTransformations(g2d);
		}
	}

	/**
	 * Applies transformations for this display object to the given graphics
	 * object
	 * */
	protected void applyTransformations(Graphics2D g2d) {
		g2d.rotate(rotation, pivotPoint.getX(),pivotPoint.getY());
		g2d.translate(xPosition, yPosition);
		g2d.scale(scaleX, scaleY);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		
	}

	/**
	 * Reverses transformations for this display object to the given graphics
	 * object
	 * */
	protected void reverseTransformations(Graphics2D g2d) {
		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2d.scale(1/scaleX, 1/scaleX);
		g2d.translate(-xPosition, -yPosition);
		g2d.rotate(-rotation, pivotPoint.getX(),pivotPoint.getY());
	}

	@Override
	public void addEventListener(IEventListener listener, String eventType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEventListener(IEventListener listener, String eventType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispatchEvent(Event event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasEventListener(IEventListener listener, String eventType) {
		// TODO Auto-generated method stub
		return false;
	}

}