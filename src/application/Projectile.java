package application;

import java.util.Random;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public abstract class Projectile {

	// Creating fields
	public int xPos, yPos, width, height;
	public int speed;
	public Image img;
	public Image imgArr [];
	public ImageView iview;
	public Random rnd;
	
	// Methods
	
	// Abstract move method to be overriden
	public abstract void move();
	
	// Returns mask of image
	public Bounds getMask () {

		Rectangle rect = new Rectangle(xPos, yPos, width, height);

		return rect.getBoundsInParent();

	}
	
	// Returns imageview
	public ImageView getNode() {
		return iview;
	}
	
	// Returns xPos
	public int getX() {
		return xPos;
	}
	
	// Returns yPos
	public int getY() {
		return yPos;
	}
	
	// Assigns xPos
	public void setX(int x) {
		xPos = x;
		iview.setX(xPos);
	}
	
	// Assigns yPos
	public void setY(int y) {
		yPos = y;
		iview.setY(yPos);
	}
	
	// Returns width
	public int getWidth() {
		return width;
	}
	
	// Returns height
	public int getHeight() {
		return height;
	}
	
	// Set speed to passed in value
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	// Returns whether the object is offscreen or not
	public boolean isOffScreen(int width) {
		
		boolean isOffScreen = false;
		
		// If its xPos is less than 0 or greater than the screen width it is offscreen
		if (xPos >= width || xPos + this.width <= 0) {
			isOffScreen = true;
		}
		
		return isOffScreen;
		
	}
	
}
