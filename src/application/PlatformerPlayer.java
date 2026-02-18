package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class PlatformerPlayer extends Player {

	// Fields
	private Image atkLeft, atkRight;
	private ImageView iviewPlayer;
	public boolean isDead;
	private int dir;
	private double xPos, yPos, width, height, gravity, jumpGravity, jumpSpeed;
	private Timeline gravityTimer;

	// Constructors
	public PlatformerPlayer() {
		
		// Initialize images and imageview of player
		imgLeft = new Image("file:platformer/player/imgLeft.gif");
		imgRight = new Image("file:platformer/player/imgRight.gif");

		atkLeft = new Image("file:platformer/player/atkLeft.gif");
		atkRight = new Image("file:platformer/player/atkRight.gif");

		iviewPlayer = new ImageView(imgLeft);
		iviewPlayer.setPreserveRatio(true);
		iviewPlayer.setFitHeight(250);

		// Initialize other fields
		dir = RIGHT;

		xPos = 300;
		yPos = 440;

		width = 140	;
		height = 240;

		jumpSpeed = 10;

		gravity = 0.05;
		jumpGravity = 0.005;

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
	
	// Return height
	public double getHeight() {
		return height;
	}

	// Return width
	public double getWidth() {
		return width;
	}

	// Return direction
	public int getDirection() {
		return dir;
	}

	// Set direction to passed in value
	public void setDirection(int dir) {
		this.dir = dir;
	}

	// Return node
	public ImageView getNode() {

		// Return attacking nodes (depending on dir being left or right)
		if (atk) {
			if (dir == LEFT) {
				iviewPlayer.setImage(atkLeft);
				iviewPlayer.setX(xPos - 200);
			}
			else if (dir == RIGHT){
				iviewPlayer.setImage(atkRight);
				iviewPlayer.setX(xPos - 20);
			}

			iviewPlayer.setY(yPos + 10);
			iviewPlayer.setFitHeight(300);

		}
		
		// Return idle nodes (depending on dir being left or right)
		else {
			if (dir == LEFT) {
				iviewPlayer.setImage(imgLeft);
			}
			else if (dir == RIGHT){
				iviewPlayer.setImage(imgRight);
			}

			iviewPlayer.setX(xPos);
			iviewPlayer.setY(yPos);
			iviewPlayer.setFitHeight(250);

		}

		return iviewPlayer;

	}

	// Return xPos
	public double getX() {
		return xPos;
	}

	// Return yPos
	public double getY() {
		return yPos;
	}

	// Method to make player jump
	public void jump() {
		yPos -= jumpSpeed;
		yPos += jumpGravity;
		iviewPlayer.setY(yPos);
	}

	// Set atk to passed in value
	public void atk(boolean b) {
		atk = b;
	}

	// Add to player jump gravity by passed in value
	public void setJumpGravity(double grav) {
		jumpGravity += grav;
	}

	// Reset player jump gravity to passed in value
	public void resetJumpGravity(double grav) {
		jumpGravity = grav;
	}

	// Activate graity (until they hit passed in boundary)
	public void gravity(int boundary) {
		if (yPos <= boundary) {
			yPos += gravity;
			iviewPlayer.setY(yPos);
			gravityTimer.play();
		}
	}

	// Stop player gravity and reset it
	public void stopGravity() {
		gravity = 0.05;
		gravityTimer.stop();
	}

	// Set player location to passed in x and y value
	public void setLocation(double x, double y) {
		isDead = false;
		xPos = x;
		yPos = y;

		iviewPlayer.setX(xPos);
		iviewPlayer.setY(yPos);
	}

	// Set player xPos to passed in value
	public void setX(double x) {
		xPos = x;
		iviewPlayer.setX(x);
	}

	// Set player yPos to passed in value
	public void setY(double y) {
		yPos = y;
		iviewPlayer.setY(y);
	}

	// Return mask of player
	public Bounds getMask() {

		Rectangle rect;

		// Returning masks if attacking
		if (atk) {
			if (dir == RIGHT)
				rect = new Rectangle(xPos, yPos, width + 320, height);

			else
				rect = new Rectangle(xPos - 200, yPos, width + 290, height);
		}

		// Returning regular mask otherwise
		else
			rect = new Rectangle(xPos, yPos + 100, width + 50, height - 100);


		return rect.getBoundsInParent();

	}

	// Return bottom mask of player
	public Bounds getBottomMask() {

		Rectangle rect;

		if (dir == RIGHT)
			rect = new Rectangle(xPos + 30, yPos + height - 5, width - 30, 5);
		else {
			rect = new Rectangle(xPos + 15, yPos + height - 5, width, 5);
		}

		return rect.getBoundsInParent();

	}

	// Return top mask of player
	public Bounds getTopMask() {

		Rectangle rect = new Rectangle(xPos, yPos + 130, width, 10);

		return rect.getBoundsInParent();

	}

	// Return right mask of player
	public Bounds getRightMask() {

		Rectangle rect = new Rectangle(xPos + width + 10, yPos + 110, 20, 80);

		//		if (atk)
		//			rect = new Rectangle(xPos + width, yPos, 320, 200);

		return rect.getBoundsInParent();

	}

	// Return right attacking mask
	public Bounds getRightAtkMask() {

		Rectangle rect = new Rectangle(xPos + width, yPos, 320, 200);
		rect = new Rectangle(xPos + width, yPos, width, 120);


		return rect.getBoundsInParent();

	}

	// Return left mask of player
	public Bounds getLeftMask() {

		Rectangle rect;

		if (dir == RIGHT)
			rect = new Rectangle(xPos, yPos + 110, 20, 40);
		else
			rect = new Rectangle(xPos - 20, yPos + 110, 20, 40);

		if (atk)
			rect = new Rectangle(xPos - 200, yPos, 290, 80);

		return rect.getBoundsInParent();

	}
	
	// Return left attacking mask
	public Bounds getLeftAtkMask() {

		Rectangle rect = new Rectangle(xPos - 200, yPos, 290, 120);

		return rect.getBoundsInParent();

	}

	// Return spawning mask
	public Bounds getSpawnMask() {

		Rectangle rect = new Rectangle(xPos - 100, yPos + 110, width + 100, height);


		return rect.getBoundsInParent();

	}


}
