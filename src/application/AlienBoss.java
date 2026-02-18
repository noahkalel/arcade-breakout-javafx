package application;

import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class AlienBoss extends Enemy {

	// Fields
	public Laser laser [];
	private Timeline cooldown;
	private AudioClip clip;
	private boolean isDead;

	// Constructors
	public AlienBoss() {

		// Initializing fields
		rnd = new Random();
		xPos = 1200;
		yPos = 0;
		speed = 0.5;

		// Initializing images for alien boss
		imgLeft = new Image("file:shooter/enemies/ufo.png");
		iv = new ImageView (imgLeft);
		iv.setPreserveRatio(true);
		iv.setFitHeight(625);

		iv.setX(xPos);
		iv.setY(yPos);

		// Initializing laser and laser sound effect
		laser = new Laser[6];
		for (int i = 0; i < laser.length; i++) {
			laser[i] = new Laser(1);
		}
		clip = new AudioClip("file:shooter/sounds/effects.wav");
		clip.setVolume(0.2);


		// Keyframe for laser cooldown
		KeyFrame kfCooldown = new KeyFrame(Duration.millis(rnd.nextInt(1000) + 1000), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {

				// Checking if laser is off screen and resetting it to the boss if so (and playing sound effect)
				for (int i = 0; i < laser.length; i++) {
					if (laser[i].isOffScreen(1000)) {
						laser[i].setX(xPos + 30);
						laser[i].setY((int) (rnd.nextInt(625)));
						laser[i].setSpeed(rnd.nextInt(3) + 1);
						
						if (Main.muteFX == false) {
							clip.play();
						}
						
					}
				}

			}
		});

		cooldown = new Timeline(kfCooldown);
		cooldown.setCycleCount(Timeline.INDEFINITE);

	}

	// Methods

	// Firing boss' lasers (in for loop because its an array of lasers)
	public void fireLaser() {
		for (int i = 0; i < laser.length; i++) {
			laser[i].move();
			if (laser[i].isOffScreen(1000)) {
				cooldown.play();
			}
		}
	}

	// Method to relocate the laser to the alien boss (passing in which laser to relocate)
	public void relocateLaser(int i) {
		laser[i].setX(xPos + 30);
		laser[i].setY((int) (rnd.nextInt(625)));
	}

	// Method to move alien boss left
	public void move() {
		if (xPos > 200) {
			xPos -= speed;
			iv.setX(xPos);
		}
		// If it reaches a certain co-ordinantes  kill it
		else {
			isDead = true;
		}
	}
	
	// Returns whether the boss is dead
	public boolean isBossDead() {
		return isDead;
	}

	// Returns imageview
	public ImageView getNode() {
		return iv;
	}
	
	// Returns boss' mask
	public Bounds getMask() {
		
		Rectangle rect = new Rectangle(xPos + 200, yPos, 50, 625);
		
		return rect.getBoundsInParent();
		
	}

	// Sets boss location to passed in values
	public void setLocation(int x, int y) {
		xPos = x;
		yPos = y;

	}

}
