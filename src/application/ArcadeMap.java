package application;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class ArcadeMap extends Main {

	// Fields
	public int xPos, yPos, width, height, speed, score;
	private ImageView ivArcade [], ivSign, ivStar;
	private Player player;
	private int arcadeXPos;
	public Image background, arcade, sign, star;
	public ImageView ivBG;
	public TextInputDialog dialog;
	public boolean gameWon;


	// Constructors
	public ArcadeMap() {

		// Initializing fields
		player = new Player();
		speed = 5;
		xPos = - 80;
		yPos = - 530;

		// Initialize imageviews
		background = new Image("file:background/background.png");
		ivBG = new ImageView(background);

		arcade = new Image("file:background/arcade.png");

		ivArcade = new ImageView [3];

		for (int i = 0; i < ivArcade.length; i++) {
			ivArcade[i] = new ImageView(arcade);

			ivArcade[i].setPreserveRatio(true);
			ivArcade[i].setFitWidth(120);
		}

		sign = new Image("file:background/outOfOrder.png");
		ivSign = new ImageView(sign);
		ivSign.setPreserveRatio(true);
		ivSign.setFitWidth(130);
		ivSign.setRotate(-15);
		
		star = new Image("file:shooter/player/star.png");
		ivStar = new ImageView(star);
		ivStar.setPreserveRatio(true);
		ivStar.setFitWidth(75);

		width = (int)background.getWidth();
		height = (int)background.getHeight();

		ivBG.setX(xPos);
		ivBG.setY(yPos);

		// Animation timer to move map
		timer = new AnimationTimer() { 
			public void handle(long val) {

				// Moving up if up is true and player is not intersecting top bound
				if (up && !player.getMask().intersects(getTopBound())) {
					yPos += speed;
				}
				
				// Moving down if down is true and player is not intersecting bottom bound
				if (down && !player.getMask().intersects(getBottomBound())) {
					yPos -= speed;
				}
				
				// Moving left if left is true and player is not intersecting left bound
				if (left && !player.getMask().intersects(getLeftBound())) {
					xPos += speed;
					player.setDirectionX(player.LEFT);
				}
				
				// Moving right if right is true and player is not intersecting right bound
				if (right && !player.getMask().intersects(getRightBound())) {
					xPos -= speed;
					player.setDirectionX(player.RIGHT);
				}

				// Setting background's x and y pos to xPos
				ivBG.setX(xPos);
				ivBG.setY(yPos);

				
				// Setting arcade machine imageviews onto map
				arcadeXPos = 250;
				for (int i = 0; i < ivArcade.length; i++) {
					ivArcade[i].setX(xPos + arcadeXPos);
					ivArcade[i].setY(yPos + 480);
					arcadeXPos += 190;
				}

				// Updating star and sign imageviews
				ivSign.setX(xPos - 10);
				ivSign.setY(yPos + 530);
				
				ivStar.setX(xPos + 820);
				ivStar.setY(yPos + 680);

				// Getting player mask and node (to update direction)
				player.getNode();
				player.getMask();

			}
		};


	}

	// Methods

	// Returns whether the game is done or not
	public boolean gameDone() {
		return gameDone;
	}

	// Returns whether the game is won
	public boolean isGameWon() {
		return gameWon;
	}

	// Sets xPos to passed in xPos
	public void setX(int xPos) {
		this.xPos = xPos;
	}
	
	// Sets yPos to passed in yPos
	public void setY(int yPos) {
		this.yPos = yPos;
	}

	// Returning xPos
	public int getX() {
		return xPos;
	}

	// Returning yPos
	public int getY() {
		return yPos;
	}

	// Retuning background imageview
	public ImageView getBG() {
		return ivBG;
	}

	// Returning arcade imageview (at passed in index)
	private ImageView getArcade(int i) {
		return ivArcade[i];
	}
	
	// Returning star imageview
	public ImageView getStar() {
		return ivStar;
	}

	// Returning arcade at passed in value's mask
	public Bounds getArcadeBound (int i) {

		Rectangle rect;

		if (i == 0) {
			rect = new Rectangle(xPos + 255, yPos + 360, 20, 210);
		}
		else if (i == 1) {
			rect = new Rectangle(xPos + 445, yPos + 360, 20, 210);
		}
		else {
			rect = new Rectangle(xPos + 635, yPos + 360, 20, 210);

		}

		return rect.getBoundsInParent();

	}

	// Returning top map boundary
	public Bounds getTopBound() {

		Rectangle rect = new Rectangle(xPos, yPos + 550, width, 10);
		return rect.getBoundsInParent();


	}

	// Returning bottom map boundary
	public Bounds getBottomBound() {

		Rectangle rect = new Rectangle(xPos, yPos + height - 15, width, 10);
		return rect.getBoundsInParent();

	}

	// Returning left map boundary
	public Bounds getLeftBound() {

		Rectangle rect = new Rectangle(xPos - 10, yPos, 10, height);
		return rect.getBoundsInParent();

	}

	// Returning right map boundary
	public Bounds getRightBound() {

		Rectangle rect = new Rectangle(xPos + width, yPos, 10, height);
		return rect.getBoundsInParent();

	}

	// Checking if player mask intersects with arcade mask at passed in value
	public boolean checkArcadeIntersect(int i) {

		// Checking if player intersects with game machine
		if (player.getMask().intersects(getArcadeBound(i))) {

			return true;
		}
		else {
			return false;
		}

	}
	
	// Checks if player is intersecting with star
	public boolean checkStarIntersect() {
		if (player.getMask().intersects(ivStar.getBoundsInParent())) {
			return true;
		}
		else 
			return false;
	}

	// Checking if player intersects with secrret kirbyBall game
	public boolean checkSecretGame() {

		Rectangle rect = new Rectangle(ivSign.getX(), ivSign.getY(), 50, 50);

		// Checking if player is intersecting with out of order sign
		if (player.getMask().intersects(rect.getBoundsInParent())) {
			return true;
		}
		else
			return false;

	}

	// Removing all nodes from root
	public void removeFromScreen() {

		// Removing nodes from root
		root.getChildren().clear();
		
	}

	// Adding all nodes from arcade map back onto screen
	public void addToScreen(boolean gameOverShown) {

		// Adding background to root
		root.getChildren().add(ivBG);

		// Adding arcade machines
		for (int i = 0; i < ivArcade.length; i++) {
			root.getChildren().add(getArcade(i));
		}

		// Adding player and sign
		root.getChildren().addAll(ivSign, player.getNode());
		
		// If gameOverShown is true add star
		if (gameOverShown == true) {
			root.getChildren().add(getStar());
		}

	}

	// Return score
	public int getScore() {
		return score;
	}

	// Method to search for index number of an arraylist
	public int searchArray(ArrayList<Integer> num, int search) {

		int start = 0;
		int end = num.size() - 1;
		int middle = 0;
		int location = -1; 
		boolean found = false;

		while (start <= end && found == false) {

			middle = (start + end)/2;

			if (num.get(middle) == search) {
				found = true;
				location = middle;
			}
			else if (num.get(middle) < search)
				start = middle + 1;
			else
				end = middle - 1;

		}

		return location;

	}

}
