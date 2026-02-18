package application;

import java.io.File;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class KirbyBall extends ArcadeMap {

	// Fields
	private Ball ball;
	private KirbyBallRacket player;
	private KirbyBallEnemy enemy;
	private Random rnd;
	private ImageView ivBG;
	private int tokens;

	// Constructors
	public KirbyBall(int tokens) {

		// Initializing fields
		ball = new Ball(1000, 600);
		player = new KirbyBallRacket(600);
		enemy = new KirbyBallEnemy(1000, 600);
		rnd = new Random();
		this.tokens = tokens;

		// Initializing imageviews
		ivBG = new ImageView(new Image("file:ping/bg.png"));
		ivBG.setPreserveRatio(true);
		ivBG.setFitWidth(1000);
		ivBG.setLayoutX(0);

		// Adding nodes to root
		root.getChildren().addAll(ivBG, ball.getNode(), player.getNode(), enemy.getNode());

		// Initializing background music
		soundFile = new File("ping/sounds/bg.mp3");
		media = new Media(soundFile.toURI().toString());
		mPlayer = new MediaPlayer(media);
		mPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mPlayer.setVolume(0.15);

		// Playing music if muteMusic is false
		if (Main.muteMusic == false) {
			mPlayer.play();
		}

		// Initializing sound effects
		clips = new AudioClip[1];
		clips[0] = new AudioClip("file:ping/sounds/effect.wav");

		// Animation timer to run game
		timer = new AnimationTimer() { 
			public void handle(long val) {

				// Moving ball and getting node
				ball.move();
				ball.getNode();

				// Moving player and enemy wall
				player.move(up, down);
				enemy.move(ball.getY(), ball.getX(), 1000);

				// Checking if ball intersects player wall and switching dir of ball if so
				if (ball.getNode().getBoundsInParent().intersects(player.getMask())) {
					ball.setDir(ball.RIGHT);
					
					// Playing sound effect if muteFX is false
					if (Main.muteFX == false) {
						clips[0].play();
					}
					
				}

				// Checking if ball intersects enemy wall and switching dir of ball if so
				if (ball.getNode().getBoundsInParent().intersects(enemy.getMask())) {
					ball.setDir(ball.LEFT);
					
					// Playing sound effect if muteFX is false
					if (Main.muteFX == false) {
						clips[0].play();
					}
					
				}

				// Checking if ball leaves screen on player side (game lost)
				if (ball.getX() + 50 <= 0) {
					gameWon = false;
					endGame();
				}

				// Checking if ball leaves screen on NPC side (game won)
				if (ball.getX() >= 1000) {
					gameWon = true;
					endGame();
				}

			}
		};

		// Keyframe for increasing speed
		KeyFrame kfIncreaseSpeed = new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {

				// Generating random number between 0-49
				int chance = rnd.nextInt(50);

				// If random number is 0 increase xSpeed of ball
				if (chance == 0) {
					ball.increaseSpeedX(1);
				}

				// If random number is 1 increase ySpeed of ball
				if (chance == 1) {
					ball.increaseSpeedY(1);
				}
			}
		});

		Timeline speedTimer = new Timeline(kfIncreaseSpeed);
		speedTimer.setCycleCount(Timeline.INDEFINITE);


		// Starting timers
		timer.start();
		speedTimer.play();


	}

	// Methods

	// Method to end game
	private void endGame() {

		// Stopping timer
		timer.stop();

		// Running alert
		Platform.runLater(new Runnable() {
			public void run() {
				alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setGraphic(ball.getNode());

				// Changing text depending on if the game is won and if player has 
				if (gameWon) {

					if (tokens < 5)
						alert.setContentText("YOU WIN! You earned 1 (one) token!");
					else 
						alert.setContentText("YOU WIN KirbyBall!");


				}

				// Changing text to loss text
				else {
					alert.setContentText("YOU LOSE! The KirbyBall is lost!");
				}

				alert.showAndWait();
			}

		});

		mPlayer.stop();
		gameDone = true;
		removeFromScreen();

	}


}
