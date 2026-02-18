package application;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Laser extends Projectile {
	
	// Constructor
	public Laser (int speed) {
		
		// Initializing fields
		xPos = -100;
		yPos = -100;
		this.speed = speed;
		
		// Initializng array of laser images
		imgArr = new Image[3];
		rnd = new Random();
		width = 80;
		
		for (int i = 0; i < imgArr.length; i++) {
			imgArr[i] = new Image("file:shooter/enemies/laser" + i + ".png");
		}
		
		// Assigning imageview a random laser
		iview = new ImageView(imgArr[rnd.nextInt(3)]);
		iview.setPreserveRatio(true);
		iview.setFitHeight(25);
		
		// Updating imageview xPos and yPos
		iview.setX(xPos);
		iview.setY(yPos);
		
	}
	
	// Methods
	
	// Moving laser left and updating imageview
	public void move() {
		xPos -= 15;
		iview.setX(xPos);
	}
	
	
}
