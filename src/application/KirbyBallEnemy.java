package application;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class KirbyBallEnemy extends KirbyBallRacket {

	// Fields
	private int dir;
	private final int UP = 1, DOWN = 2;

	// Constructor
	public KirbyBallEnemy(int width, int height) {

		// Calling on super constructor which passes in height
		super(height);

		// Initializing fields
		frameHeight = height;
		img = new Image("file:ping/bot.png");
		iv = new ImageView(img);
		iv.setPreserveRatio(true);
		iv.setFitHeight(100);

		xPos = width - 35;
		speed = 10;
		yPos = (int) (height/2 - iv.getFitHeight()/2);

		iv.setX(xPos);
		iv.setY(yPos);

	}

	// Methods
	public void move(int y, int x, int width) {

		// Moving enemy racket up is dir is up
		if (dir == UP && yPos >= 0) {
			yPos -= speed;
		}

		// Moving enemy racket down if dir is down
		if (dir == DOWN && yPos + 140 <= frameHeight) {
			yPos += speed;
		}

		// If ball's xPos is on enemy's half of screen
		if (x >= width/2) {
			// If ball's yPos is less than rackets move racket up
			if (y <= yPos + iv.getFitHeight()/2) {
				dir = UP;
			}

			// If ball's yPos is greater than rackets move racket down
			else if (y >= yPos + iv.getFitHeight()/2) {
				dir = DOWN;
			}
		}
		// If racket isn't on enemy's side don't move it
		else {
			dir = 3;
		}

		iv.setY(yPos);

	}

	// Return mask for enemy rackets
	public Bounds getMask() {

		Rectangle rect = new Rectangle(xPos - 5, yPos, 5, 100);

		return rect.getBoundsInParent();

	}

}
