package application;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Player {

	// Fields
	public Image imgLeft, imgRight;
	public ImageView iviewPlayer;
	public boolean isDead, atk;
	public final int LEFT = 0, RIGHT = 1, UP = 3, DOWN = 4;
	public int dirX, dirY, speed;
	public double xPos, yPos, width, height;
	private int health;

	// Constructors
	public Player(Image left, Image right) {

		// Setting img left and right to passed in values
		imgLeft = left;
		imgRight = right;

		// Initialize fields
		iviewPlayer = new ImageView(imgLeft);
		iviewPlayer.setPreserveRatio(true);
		iviewPlayer.setFitWidth(150);

		xPos = 325;
		yPos = 225;

		width = iviewPlayer.getFitWidth();
		height = 329;

		speed = 7;
		dirX = RIGHT;
		
		health = 100;
		
	}

	public Player() {

		// Initialize fields
		imgLeft = new Image("file:player/idleLeft.gif");
		imgRight = new Image("file:player/idleRight.gif");


		iviewPlayer = new ImageView(imgLeft);
		iviewPlayer.setPreserveRatio(true);
		iviewPlayer.setFitWidth(150);

		xPos = 325;
		yPos = 225;

		width = iviewPlayer.getFitWidth();
		height = 80;

		speed = 7;
	}

	// Methods

	// Return xPos
	public double getX() {
		return xPos;
	}

	// Return yPos
	public double getY() {
		return yPos;
	}

	// Set x to passed in value
	public void setX(double x) {
		xPos = x;
		iviewPlayer.setX(x);
	}

	// Set y to passed in value
	public void setY(double y) {
		yPos = y;
		iviewPlayer.setY(y);
	}

	// return heigiht
	public double getHeight() {
		return height;
	}

	// Return width
	public double getWidth() {
		return width;
	}

	// return direction in x axis
	public int getDirectionX() {
		return dirX;
	}

	// Return direction in y axis
	public int getDirectionY() {
		return dirY;
	}

	// Set direction in x axis
	public void setDirectionX(int dir) {
		dirX = dir;
	}

	// Set direction in y axis
	public void setDirectionY(int dir) {
		dirY = dir;
	}

	// Return imageview
	public ImageView getNode() {

		// Changing imageview image to left if dir is left
		if (dirX == LEFT) {
			iviewPlayer.setImage(imgLeft);
		}
		
		// Changing imageview image to right if dir is right
		else if (dirX == RIGHT) {
			iviewPlayer.setImage(imgRight);
		}

		// Setting x and y of imageview and returning it
		iviewPlayer.setPreserveRatio(true);
		iviewPlayer.setFitWidth(150);

		width = iviewPlayer.getFitWidth();
		height = 150;

		iviewPlayer.setX(xPos);
		iviewPlayer.setY(yPos);

		return iviewPlayer;
	}

	// Moving player in the x axis
	public void moveX() {

		// Moving player left if dir is left
		if (dirX == LEFT) {
			xPos -= speed;
		}

		// Moving player right if dir is right
		else if (dirX == RIGHT) {
			xPos += speed;
		}

		iviewPlayer.setX(xPos);

	}

	// Moving player in the y axis
	public void moveY() {

		// Moving player up if dir is up
		if (dirY == UP) {
			yPos -= speed;
		}
		// Moving player down if dir is down
		else if (dirY == DOWN) {
			yPos += speed;
		}

		iviewPlayer.setY(yPos);

	}
	
	// Returning player health
	public int getHealth() {
		return health;
	}
	
	// Subtracting passed in number from player health
	public void reduceHealth (int damage) {
		health -= damage;
	}

	// Setting player location to passed in x and y
	public void setLocation(double x, double y) {
		isDead = false;
		xPos = x;
		yPos = y;

		iviewPlayer.setX(xPos);
		iviewPlayer.setY(yPos);
	}

	// Return player mask
	public Bounds getMask () {

		Rectangle rect = new Rectangle(xPos, yPos, width, height);

		return rect.getBoundsInParent();

	}

	// Return player bottom mask
	public Bounds getBottomMask() {

		Rectangle rect = new Rectangle(xPos, yPos + height - 5, width, 5);

		return rect.getBoundsInParent();

	}

	// Return player top mask
	public Bounds getTopMask() {

		Rectangle rect = new Rectangle(xPos, yPos, width, 10);

		return rect.getBoundsInParent();

	}

}
