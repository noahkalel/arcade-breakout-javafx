package application;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class KirbyBallRacket {

	// Fields
	public int xPos, yPos, speed, frameHeight;
	public Image img;
	public ImageView iv;
	
	// Constructors
	public KirbyBallRacket(int height) {
		
		// Initializing fields
		frameHeight = height;
		img = new Image("file:ping/player.png");
		iv = new ImageView(img);
		iv.setPreserveRatio(true);
		iv.setFitHeight(100);
		
		xPos = 10;
		speed = 10;
		yPos = (int) (height/2 - iv.getFitHeight()/2);
		
		iv.setX(xPos);
		iv.setY(yPos);
		
	}
	
	// Methods
	public void move(boolean up, boolean down) {
		
		// If up is true and racket isn't hitting the top bound move up
		if (up && yPos >= 0) {
			yPos -= speed;
		}
		
		// If down is true and racket isn't hitting the bottom bound move down
		if (down && yPos  + 140 <= frameHeight) {
			yPos += speed;
		}
		
		// Updating imageview yPos
		iv.setY(yPos);
		
	}
	
	// Return imageview of racket
	public ImageView getNode() {
		return iv;
	}
	
	// Return racket mask
	public Bounds getMask() {
		
		Rectangle rect = new Rectangle(xPos + 20, yPos, 5, 100);
		
		return rect.getBoundsInParent();
		
	}
}
