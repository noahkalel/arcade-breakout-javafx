package application;

import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class PlatformerEnemy extends Enemy{

	// Fields
	private Image imgRight;
	private int enemy;
	private boolean onPlatform;
	private int platform;
	private double gravity;
	private Timeline gravityTimer;

	// Constructors
	public PlatformerEnemy() {

		// Initializing fields
		yPos = 0;
		xPos = 0;
		speed = 3;
		width = 90;
		platform = -1;
		gravity = 0.05;

		// Randomizing image of enemy
		rnd = new Random();
		enemy = rnd.nextInt(2);

		if (enemy == 0) {
			imgLeft= new Image("file:platformer/enemies/goomba.gif");
		}
		else {
			imgLeft= new Image("file:platformer/enemies/koopaLeft.gif");
			imgRight = new Image("file:platformer/enemies/koopaRight.gif");
		}

		iv = new ImageView(imgLeft);
		iv.setPreserveRatio(true);

		if (enemy == 0) {
			iv.setFitHeight(90);
		}
		else {
			iv.setFitHeight(110);
		}

		// Randomizing initial direction
		if (enemy == 0) {
			dir = RIGHT;
		}
		else
			dir = LEFT;

		// Keyframe for Gravity
		KeyFrame kfGravity = new KeyFrame(Duration.millis(50), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				// Increasing jump gravity (accelerator to make player fall on jump
				gravity += 0.7;
			}
		});

		gravityTimer = new Timeline(kfGravity);
		gravityTimer.setCycleCount(Timeline.INDEFINITE);

	}

	// Methods
	
	// Method to activate gravity
	public void activateGravity() {
		// Activating gravity if enemy is not on the floor
		if (yPos <= 600) {
			yPos += gravity;
			iv.setY(yPos);
			gravityTimer.play();
		}
		// Otherwise stopping gravity
		else if (yPos >= 600) {
			stopGravity();
			platform = -2;
			onPlatform = false;
			
		}

	}

	// Reset and stop gravity
	public void stopGravity() {
		gravity = 0.05;
		gravityTimer.stop();
	}

	// Return type of enemy
	public int getType() {
		return enemy;
	}

	// REturn node of enemy
	public ImageView getNode() {

		if (enemy == 1) {
			if (dir == LEFT) {
				iv.setImage(imgLeft);
			}
			else if (dir == RIGHT) {
				iv.setImage(imgRight);
			}

			iv.setFitHeight(110);
		}


		return iv;
	}

	// Set whether enemy is on a platform
	public void onPlatform(boolean b) {
		onPlatform = b;
	}

	// Return whether enemy is on a platform
	public boolean isOnPlatform() {
		return onPlatform;
	}

	// Set platform number of enemy
	public void setPlatformNumber(int i) {
		platform = i;
	}

	// Return enemy platform number
	public int getPlatformNumber() {
		return platform;
	}

	// Set enemy location to passed in x and y value
	public void setLocation(int x, int y) {

		xPos = x;
		yPos = y - width;

		iv.setX(xPos);
		iv.setY(yPos);

	}

	// Move player depending on direction
	public void move() {

		if (dir == LEFT) {
			xPos -= speed;
		}
		else if (dir == RIGHT) {
			xPos += speed;
		}

		iv.setX(xPos);
		iv.setY(yPos);

	}

	// Switch the player's direction
	public void switchDir() {
		if (dir == LEFT) {
			dir = RIGHT;
		}
		if (dir == RIGHT) {
			dir = LEFT;
		}
	}

	// Move enemy horizontally on a platform
	public void moveHorizontal(int leftWall, int rightWall) {

		if (dir == LEFT) {
			xPos -= speed;
		}

		else if (dir == RIGHT) {
			xPos += speed;
		}

		// If their xPos hits left wall change dir to right
		if (xPos <= leftWall) {
			dir = RIGHT;
		}

		// If their xPos hits right wall change dir to left
		if (xPos + width >= rightWall) {
			dir = LEFT;
		}

		iv.setX(xPos);
	}

}
