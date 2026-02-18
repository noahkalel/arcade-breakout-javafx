package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Star extends Projectile {

	// Fields
	private int dir;
	public final int LEFT = 0, RIGHT = 1, UP = 3, DOWN = 4;
	
	// Constructor
	public Star(int xPos, int yPos) {

		// Initializing fields
		
		// Initializing xPos and yPos to the passed in values from constructor
		this.xPos = xPos + 150;
		this.yPos = yPos;

		// Initializing image and imageview of fireball
		img = new Image("file:shooter/player/star.png");
		iview = new ImageView(img);
		iview.setPreserveRatio(true);
		iview.setFitWidth(50);
		
		// Setting the imageview x and y positions to xPos and yPos variables
		iview.setX(this.xPos);
		iview.setY(this.yPos);

		// Setting width and height variables to the width and height of star image
		width = (int)iview.getFitWidth();
		height = (int)iview.getFitWidth();

		// Setting star speed
		speed = 6;

	}

	// Methods
	public void move() {

		// Moving the star depending on the direction
		if (dir == LEFT) {
			xPos -= speed;
		}
		
		if (dir == RIGHT) {
			xPos += speed;
		}
		
		// Updating imageview and rotating star
		iview.setX(this.xPos);
		iview.setY(this.yPos);
		iview.setRotate(iview.getRotate() + 15);

	}
	
	// Set dir to passed in value
	public void setDir(int dir) {
		this.dir = dir;
	}

}
