package application;

import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

public class EnemyAlien extends Enemy {

	// Fields
	private Image img [];
	private int moveType, shouldHaveLaser;
	private boolean hasLaser;
	private Timeline cooldown;
	private AudioClip clip;

	// Constructor
	public EnemyAlien() {

		// Initialize Fields

		// Randomizing speed
		rnd = new Random();
		speed = rnd.nextInt(5) + 1;

		// Initializing image array of aliens
		img = new Image[3];
		for (int i = 0; i < img.length; i++) {
			img[i] = new Image("file:shooter/enemies/ufo" + i + ".png");
		}

		// Randomizing image of alien
		iv = new ImageView(img[rnd.nextInt(3)]);
		iv.setPreserveRatio(true);
		iv.setFitHeight(100);

		// Randomizing xPos and yPos
		xPos = rnd.nextInt(400) + 1000;
		yPos = (int) (rnd.nextInt((int) (625 - iv.getFitHeight())));

		// Randomizing move type (either just horziontal or diagonal
		moveType = rnd.nextInt(2);

		// Randomizing initial direction
		if (moveType == 0) {
			dir = UP;
		}

		if (moveType == 1) {
			dir = DOWN;
		}

		// Randomizing whether the enemy should have a laser
		shouldHaveLaser = rnd.nextInt(2);

		if (shouldHaveLaser == 0) {
			hasLaser = true;
		}
		else {
			hasLaser = false;
		}

		// Initializing laser and sound efect for laser
		laser = new Laser((int) (speed + 2));
		clip = new AudioClip("file:shooter/sounds/effects.wav");
		clip.setVolume(0.2);


		// Keyframe for laser cooldown
		KeyFrame kfCooldown = new KeyFrame(Duration.millis(2000), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {

				// Resetting laser location and playing fire sound
				laser.setX(xPos);
				laser.setY((int) (yPos + iv.getFitHeight()/2));
				
				if (Main.muteFX == false)
				clip.play();

			}
		});

		cooldown = new Timeline(kfCooldown);

	}

	// Methods
	
	// Returns whether alien has a laser
	public boolean hasLaser() {
		return hasLaser;
	}

	// Sets whether alien has laser
	public void setHasLaser(boolean b) {
		hasLaser = b;
	}

	// Move enemy's laser and starting cooldown timeline if laser goes offscreen
	public void fireLaser() {
		laser.move();

		if (laser.isOffScreen(1000)) {
			cooldown.play();
		}
	}

	// Moving alien
	public void move() {

		// If move type = 0 then they move vertically
		if (moveType == 0) {
			xPos -= speed;
		}

		// If move type == 1 then they move diagonally
		if (moveType == 1) {
			xPos -= speed;

			if (dir == UP)
				yPos -= speed;
			if (dir == DOWN)
				yPos += speed;

			if (yPos <= 0)
				dir = DOWN;
			if (yPos + iv.getFitHeight() >= 625)
				dir = UP;
		}

		// Updating imageview
		iv.setX(xPos);
		iv.setY(yPos);

	}

	// Return alien imageview
	public ImageView getNode() {
		return iv;
	}

	// Setting enemy location to a random number within the width and height
	public void setLocation(int width, int height) {
		xPos = rnd.nextInt(400) + width;
		yPos = (int) (rnd.nextInt(height) - iv.getFitHeight());
	}

	// Returns whether the alien is offscreen or not
	public boolean isOffScreen() {
		if (xPos + 75 <= 0) {
			return true;
		}
		else
			return false;
	}

}
