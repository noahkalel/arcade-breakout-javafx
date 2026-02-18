package application;

import java.util.Random;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Platforms {

	// Fields
	public Image img;
	public ImageView iviewPlatform;
	public double xPos, yPos, width, height, speed, dirX, dirY;
	public final int UP = 1, DOWN = 2, RIGHT = 3, LEFT = 4;
	private Random rnd;
	private boolean isOnScreen;
	
	// Constructors
	public Platforms(int xPos, int yPos) {
		
		// Initializing fields
		this.xPos = xPos;
		this.yPos = yPos;
		
		img = new Image("file:platformer/platforms/platform.png");
		iviewPlatform = new ImageView(img);
		iviewPlatform.setPreserveRatio(true);
		iviewPlatform.setFitWidth(300);
		
		iviewPlatform.setX(xPos);
		iviewPlatform.setY(yPos);
		
		width = iviewPlatform.getFitWidth() - 60;
		height = 108;
		
		speed = 3;
		
		dirX = LEFT;
		dirY = UP;
		
		rnd = new Random();
				
	}
	
	// Methods
	
	// Return height
	public double getHeight() {
		return height;
	}

	// Return width
	public double getWidth() {
		return width;
	}

	// Return imageview
	public ImageView getNode() {

		return iviewPlatform;
	}

	// Return xPos
	public double getX() {
		return xPos;
	}

	// Return yPos
	public double getY() {
		return yPos;
	}
	
	// Return whether platform is on screen
	public boolean isOnScreen() {
		return isOnScreen;
	}
	
	// Set whether platform is on screen
	public void onScreen(boolean b) {
		isOnScreen = b;
	}

	// Randomize platform location based on passed in width and height
	public void setLocation(int width, int height) {
		xPos = width + rnd.nextInt(1600);
		yPos = rnd.nextInt(height) + 100;

		iviewPlatform.setX(xPos);
		iviewPlatform.setY(yPos);
	}

	// Set x of platform
	public void setX(double x) {
		xPos = x;
		iviewPlatform.setX(x);
	}

	// Set y of platform
	public void setY(double y) {
		xPos = y;
		iviewPlatform.setY(y);
	}

	// Return platform mask
	public Bounds getMask () {

		Rectangle rect;

			rect = new Rectangle(xPos - 20, yPos + 5, 270, 98);

		return rect.getBoundsInParent();

	}
	
	// Return platform spawn mask
public Bounds getSpawnMask () {
		
		Rectangle rect = new Rectangle(xPos - 50, yPos - 50, width + 100, height + 100);
		
		return rect.getBoundsInParent();
		
	}
	
}
