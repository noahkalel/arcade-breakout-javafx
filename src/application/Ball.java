package application;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ball extends Enemy {

	// Fields
	private Random rnd;
	private int dirY, ySpeed;
	private int frameWidth, frameHeight;

	// Constructors
	public Ball(int width, int height) {

		// Setting frame width and height to passed in values
		frameWidth = width;
		frameHeight = height;
		
		// Initializing images
		imgLeft = new Image("file:ping/ballLeft.png");
		imgRight = new Image("file:ping/ballRight.png");

		// Randomizing starting direction (to left or right)
		rnd = new Random();
		int rndDir = rnd.nextInt(2);

		// If random generated number is 0 then direction is left and up
		if (rndDir == 0) {
			dir = LEFT;
			dirY = UP;
			iv = new ImageView(imgLeft);
		}
		// If randoml generated number is 1 then direction is right and down
		else {
			dir = RIGHT;
			dirY = DOWN;
			iv = new ImageView(imgRight);
		}

		// Setting size of imageview to 50
		iv.setPreserveRatio(true);
		iv.setFitWidth(50);

		// Initializing speeds
		speed = 3;
		ySpeed = 5;

		// Setting ball to center of screen
		xPos = (int) (frameWidth/2 - iv.getFitWidth()/2);
		yPos = (int) frameHeight/2 - 25;
		
		iv.setX(xPos);
		iv.setY(yPos);

	}

	// Methods

	// Move method for ball
	public void move() {

		// Moving ball up if dirY is up
		if (dirY == UP) {
			yPos -= ySpeed;
			// If ball hits top bound then switch dir to down
			if (yPos <= 0) {
				dirY = DOWN;
			}
		}

		// Moving ball down if dirY is down
		if (dirY == DOWN) {
			yPos += ySpeed;
			// If ball hits bottom bound then switch dir to up
			if (yPos + 80 >= frameHeight) {
				dirY = UP;
			}
		}

		// If dir is left move ball left
		if (dir == LEFT) {
			xPos -= speed;
		}
		// If dir is right move ball right
		if (dir == RIGHT) {
			xPos += speed;
		}

		// Updating imageviews xPos and yPos
		iv.setX(xPos);
		iv.setY(yPos);

	}

	// Setting direction to passed in value
	public void setDir(int dir) {

		this.dir = dir;

	}

	// Returning imageview
	public ImageView getNode() {

		// Set imageview to left faced img if dir is left
		if (dir == LEFT) {
			iv.setImage(imgLeft);
		}
		// Set imageview to right faced img if dir is right
		else if (dir == RIGHT) {
			iv.setImage(imgRight);
		}

		return iv;

	}

	// Setting ball's location to passed in x and y value
	public void setLocation(int x, int y) {

		xPos = x;
		yPos = y;

	}

	// Increasing ball's xSpeed by passed in value
	public void increaseSpeedX(int speed) {
		this.speed += speed;
	}

	// Increasing ball's ySpeed by passed in value
	public void increaseSpeedY(int speed) {
		ySpeed += speed;
	}

}
