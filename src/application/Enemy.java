package application;

import java.util.Random;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Enemy {

	// Fields
	public ImageView iv;
	public int xPos, yPos, width, height, dir;
	public final int LEFT = 1, RIGHT = 2, UP = 3, DOWN = 4;
	public double speed;
	public Image imgLeft, imgRight;
	public Random rnd;
	public Laser laser;
	
	
	// Methods
	
	// Method to move 
	public abstract void move();
	
	// Method to return node
	public abstract ImageView getNode();
	
	// Sets xPos to passed in value
	public void setX(int x) {
		xPos = x;
		iv.setX(xPos);
	}
	
	// Sets yPos to passed in value
	public void setY(int y) {
		yPos = y;
		iv.setY(yPos);

	}
	
	// Method to set xPos and yPos to passed in variables
	public abstract void setLocation(int x, int y);
	
	// Method to return xPos
	public int getX() {
		return xPos;
	}
	
	// Method to return yPos
	public int getY() {
		return yPos;
	}
	
	// Method to return width
	public int getWidth() {
		return width;
	}
	
	// Method to return height
	public int getHeight() {
		return height;
	}

	// Method to return mask of enemy
	public Bounds getMask() {
		return iv.getBoundsInParent();
	}
	
}
