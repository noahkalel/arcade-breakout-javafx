package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class GalaxyShooter extends ArcadeMap {

	// Fields
	private ImageView ivBGTwo;
	private Timeline backgroundTimer, spawnEnemyTimer, spawnBoss, bossTimer;
	private Player player;
	private int frameWidth, frameHeight, enemyCount;
	private ArrayList<Star> stars;
	private ArrayList<Enemy> enemy;
	private boolean fire, bossActive;
	private Label lblBoss;
	private Random rnd;
	private AlienBoss boss;
	private Canvas canvas;

	// Constructor
	public GalaxyShooter(int width, int height) {

		// Initializing image left and right for player
		Image imgLeft = new Image("file:shooter/player/flyLeft.gif");
		Image imgRight = new Image("file:shooter/player/flyRight.gif");

		// Assigning frameWidth and frameHeight to passed in values
		frameWidth = width;
		frameHeight = height;

		// Initializing fields
		player = new Player(imgLeft, imgRight);
		boss = new AlienBoss();
		stars = new ArrayList<Star>();
		enemy = new ArrayList<Enemy>();
		scoresText = new ArrayList<String>();
		scoresNum = new ArrayList<Integer>();
		rnd = new Random();
		this.width = 1000;
		score = 0;
		enemyCount = 0;

		// Initializing background imageviews
		background = new Image("file:shooter/bg.jpg");
		ivBG = new ImageView(background);
		ivBG.setPreserveRatio(true);
		ivBG.setFitWidth(1000);
		ivBG.setX(0);
		ivBG.setY(0);

		ivBGTwo = new ImageView(background);
		ivBGTwo.setPreserveRatio(true);
		ivBGTwo.setFitWidth(1000);
		ivBGTwo.setX(1000);
		ivBGTwo.setY(0);

		// Adding nodes to root
		root.getChildren().addAll(ivBG, ivBGTwo, player.getNode());

		// Intiializing label and fonts
		Font f = Font.loadFont("file:fonts/Retro Gaming.ttf", 40);
		lbl = new Label("SCORE: " + score);
		lbl.setTextFill(Color.WHITE);
		lbl.setFont(f);
		lbl.setPrefSize(400,  50);
		lbl.setAlignment(Pos.CENTER);
		root.getChildren().add(lbl);
		lbl.setLayoutX(1000 - lbl.getPrefWidth());
		lbl.setLayoutY(10);

		f = Font.loadFont("file:fonts/Retro Gaming.ttf", 60);
		lblBoss = new Label("BOSS SPAWNING");
		lblBoss.setTextFill(Color.WHITE);
		lblBoss.setFont(f);
		lblBoss.setPrefSize(600,  300);
		lblBoss.setAlignment(Pos.CENTER);
		lblBoss.setLayoutX(1000/2 - lblBoss.getPrefWidth()/2);
		lblBoss.setLayoutY(625/2 - lblBoss.getPrefHeight()/2);

		// Initializing canvas and graphics context for healthbar
		canvas = new Canvas(1000, 625);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		// Drawing healthbar under Kirby
		gc.clearRect(0, 0, 1000, 800);
		gc.setFill(Color.BLACK);
		gc.fillRect(player.getX() + 30, player.getY() + 110, 100, 12);
		gc.setFill(Color.RED);
		gc.fillRect(player.getX() + 30, player.getY() + 110, player.getHealth(), 10);
		root.getChildren().add(canvas);

		// Initializing file, buffered reader/writer, file reader/writer
		try {
			file = new File("textFiles/galaxyShooterScores.txt");
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);

			fr = new FileReader(file);
			br = new BufferedReader(fr);

			// Reading high score from text file
			String lineOfText;

			while ((lineOfText = br.readLine()) != null) {

				String fileArr []  = lineOfText.split(", ");

				// Adding name and score to arraylist
				scoresText.add(fileArr[0]);
				scoresNum.add(Integer.parseInt(fileArr[1]));

				// Calling on bubbleSort method to sort arraylists
				selectionSort(scoresText, scoresNum);

				// Reversing the order to be from highest score to lowest
				Collections.reverse(scoresText);
				Collections.reverse(scoresNum);
			}
		}
		catch (Exception e) {
			e.getMessage();
		}

		// Initializing background music
		soundFile = new File("shooter/sounds/bg.mp3");
		media = new Media(soundFile.toURI().toString());
		mPlayer = new MediaPlayer(media);
		mPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mPlayer.setVolume(0.2);

		// Playing music if muteMusic is false
		if (Main.muteMusic == false) {
			mPlayer.play();
		}

		// Initializing sound effects
		clips = new AudioClip [4];
		for (int i = 0; i < clips.length; i++) {
			clips[i] = new AudioClip("file:shooter/sounds/effects" + i + ".wav");
			clips[i].setVolume(0.2);
		}

		Platform.runLater(new Runnable() {
			public void run() {

				// Alert reading high scores
				alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText(null);

				alert.setContentText("Reading high scores...");
				alert.showAndWait();

				// Asking player if they wanna see all scores
				alert = new Alert(AlertType.CONFIRMATION);
				alert.setHeaderText(null);
				alert.getButtonTypes().clear();
				alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
				alert.setContentText("High score: " + scoresNum.get(0) + "\nHeld by: " + scoresText.get(0) + "\nWould you like to display all scores?");

				Optional<ButtonType> result = alert.showAndWait();

				alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText(null);

				// If they click yes, display all scores
				if (result.get() == ButtonType.YES) {
					String str = "";
					for (int i = 0; i < scoresNum.size(); i++) {
						str += scoresText.get(i) + ": " + scoresNum.get(i) + "\n";
					}
					alert.setContentText(str);
					alert.showAndWait();

				}

				// Starting game
				alert.setContentText("Starting game NOW.");
				alert.showAndWait();

				timer.start();
				spawnEnemyTimer.play();

			}

		});

		// Animation timer fo game
		timer = new AnimationTimer() { 
			public void handle(long val) {

				// Moving player and updating node
				if (up && player.getY() >= 0) {
					player.setDirectionY(player.UP);
					player.moveY();
				}
				if (down && player.getY() + player.getHeight() <= frameHeight + 20) {
					player.setDirectionY(player.DOWN);
					player.moveY();
				}
				if (left && player.getX() >= 0) {
					player.setDirectionX(player.LEFT);
					player.moveX();
				}
				if (right && player.getX() + player.getWidth() <= frameWidth/2) {
					player.setDirectionX(player.RIGHT);
					player.moveX();
				}

				player.getNode();

				// Checking if player's health is less than or equal to 0 and ending game if so
				if (player.getHealth() <= 0) {
					gameWon = false;
					bossTimer.stop();
					endGame();
				}

				// If fire is true then fire a star from player location
				if (fire) {
					fire = false;
					stars.add(new Star((int)player.getX(), (int)player.getY()));

					// Firing star in direction player is facing
					if (player.getDirectionX() == player.LEFT) {
						stars.get(stars.size() - 1).setX((int)(player.getX() - player.getWidth() + 120));

					}

					// Adding star to root
					stars.get(stars.size() - 1).setDir(player.getDirectionX());
					root.getChildren().add(stars.get(stars.size() - 1).getNode());
				}

				// Moving all stars
				if (stars.size() > 0) {
					for (int j = 0; j < stars.size(); j++) {
						// Moving player stars
						stars.get(j).move();

						// Checking if star is out of bounds and removing it if so
						if (stars.get(j).isOffScreen(frameWidth)) {
							root.getChildren().remove(stars.get(j).getNode());
							stars.remove(stars.get(j));
							break;
						}

						// Checking if stars intersect with boss if boss is active
						if (bossActive) {
							if (stars.get(j).getMask().intersects(boss.getMask())) {

								// Removing star from arraylist + root and increasing score
								root.getChildren().remove(stars.get(j).getNode());
								stars.remove(stars.get(j));
								score += 50;
								break;
							}
						}

					}

				}

				// Updating enemy
				if (enemy.size() > 0) {
					boolean breakOutOfEnemyLoop = false;

					for (int i = 0; i < enemy.size(); i++) {

						// Moving enemy
						enemy.get(i).move();
						enemy.get(i).getNode();

						// Moving laser if enemy has laser
						if (((EnemyAlien) enemy.get(i)).hasLaser()) {
							((EnemyAlien) enemy.get(i)).fireLaser();

							// Checking if enemy laser hits player
							if (enemy.get(i).laser.getMask().intersects(player.getMask())) {

								// Playing sfx if mute is false
								if (Main.muteFX == false) {
									clips[3].play();
								}

								// Reducing score, player health and removing enemy laser from node
								score -= 50;
								player.reduceHealth(3);
								((EnemyAlien) enemy.get(i)).setHasLaser(false);
								root.getChildren().remove(enemy.get(i).laser.getNode());
							}
						}

						// Checking if enemy is offscreen
						if (((EnemyAlien) enemy.get(i)).isOffScreen()) {

							// Reducing score
							score -= 150;

							// Playing sfx if mute is false
							if (Main.muteFX == false) {
								clips[3].play();
							}

							// Removing enemy from root and arraylist
							root.getChildren().remove(enemy.get(i).getNode());
							// Checking if enemy has a laser and removing it from the root if so
							if(((EnemyAlien) enemy.get(i)).hasLaser())
								root.getChildren().remove(enemy.get(i).laser.getNode());
							enemy.remove(enemy.get(i));
							break;
						}

						// Checking if enemy and player intersects
						if (enemy.get(i).getMask().intersects(player.getMask())) {

							// Reducing player health and score
							player.reduceHealth(5);
							score -= 150;

							// Playing sfx if mute is false
							if (Main.muteFX == false) {
								clips[3].play();
							}

							// Removing enemy from arraylist and root
							root.getChildren().remove(enemy.get(i).getNode());

							// Checking if enemy has a laser and removing it from the root if so
							if(((EnemyAlien) enemy.get(i)).hasLaser())
								root.getChildren().remove(enemy.get(i).laser.getNode());
							enemy.remove(enemy.get(i));

						}

						// Checking if enemy intersects with stars and removing enemy and star if so
						if (stars.size() > 0) {

							// Loop that runs for number of stars
							for (int j = 0; j < stars.size(); j++) {

								if (enemy.size() > 0) {
									// Checking if enemy intersects with stars and removing enemy and star if so
									if (enemy.get(i).getMask().intersects(stars.get(j).getMask())) {

										// Playing sfx if mute is false
										if (Main.muteFX == false) {
											clips[1].play();
										}

										// Updating score
										score += 50;

										// Removing enemy from arraylist and root
										root.getChildren().remove(enemy.get(i).getNode());

										// Checking if enemy has a laser and removing it from the root if so
										if(((EnemyAlien) enemy.get(i)).hasLaser())
											root.getChildren().remove(enemy.get(i).laser.getNode());

										enemy.remove(enemy.get(i));

										// Breaking out of loop
										if (i > 0)
											i--;
										else 
											breakOutOfEnemyLoop = true;


										// Removing star from arraylist and root
										root.getChildren().remove(stars.get(j).getNode());
										stars.remove(stars.get(j));

										if (j > 0)
											j--;
										else
											break;

									}
								}
							}
						}

						if (breakOutOfEnemyLoop)
							break;

					}

				}

				// Updating score
				lbl.setText("SCORE: " + score);

				// Updating healthbar
				gc.clearRect(0, 0, 1000, 800);
				gc.setFill(Color.BLACK);
				gc.fillRect(player.getX() + 30, player.getY() + 110, 100, 12);
				gc.setFill(Color.RED);
				gc.fillRect(player.getX() + 30, player.getY() + 110, player.getHealth(), 10);

				// Stopping enemy spawn once 40 enemies have spawned and adding boss to node
				if (enemyCount == 10) {
					enemyCount++;
					spawnEnemyTimer.stop();
					root.getChildren().add(lblBoss);
					spawnBoss.play();
					bossActive = true;

					// Playing sfx if mute is false
					if (Main.muteFX == false) {
						clips[2].play();
					}

					// Adding boss' laser to root
					for (int i = 0; i < boss.laser.length; i++) {
						root.getChildren().add(boss.laser[i].getNode());
					}

				}

			}
		};

		// Keyframe for spawning boss
		KeyFrame kfBossSpawn = new KeyFrame(Duration.millis(2000), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {

				// Removing boss label and adding boss to node, starting boss timer aswell
				root.getChildren().remove(lblBoss);
				root.getChildren().add(boss.getNode());
				bossTimer.play();

			}
		});

		spawnBoss = new Timeline(kfBossSpawn);

		// Keyframe for boss movement and attacking
		KeyFrame kfBoss = new KeyFrame(Duration.millis(30), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {

				// Moving boss and firing his lasers
				boss.move();
				boss.fireLaser();

				// Reducing scrore and player health if boss laser intersects player
				for (int i = 0; i < boss.laser.length; i++) {
					if (boss.laser[i].getMask().intersects(player.getMask())) {
						score -= 100;
						player.reduceHealth(3);
						boss.relocateLaser(i);
					}
				}

				// Reducing score and player health if player and boss intersect
				if (boss.getMask().intersects(player.getMask())) {
					score -= 50;
					player.reduceHealth(5);
				}

				// Checking if boss is dead then removing it after a few seconds and ending game
				if (boss.isBossDead()) {
					bossTimer.stop();
					gameWon = true;
					gc.clearRect(0, 0, 1000, 800);
					endGame();

				}

			}
		});

		bossTimer = new Timeline(kfBoss);
		bossTimer.setCycleCount(Timeline.INDEFINITE);

		// Keyframe for moving background
		KeyFrame kfBackground = new KeyFrame(Duration.millis(50), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {

				ivBG.setX(ivBG.getX() - 3);
				ivBGTwo.setX(ivBGTwo.getX() - 3);

				// Checking if each background is offscreen respectively
				isOffScreen(ivBG, 1);
				isOffScreen(ivBGTwo, 2);

			}
		});

		backgroundTimer = new Timeline(kfBackground);
		backgroundTimer.setCycleCount(Timeline.INDEFINITE);

		// Keyframe for spawning enemy
		KeyFrame kfEnemy = new KeyFrame(Duration.millis(25), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {

				int spawnEnemy = rnd.nextInt(50);

				if (spawnEnemy == 0) {
					enemy.add(new EnemyAlien());
					root.getChildren().add(enemy.get(enemy.size() - 1).getNode());

					// Checking if enemy has laser and adding to root aswell if so
					if (((EnemyAlien) enemy.get(enemy.size() - 1)).hasLaser()) {
						root.getChildren().add(enemy.get(enemy.size() - 1).laser.getNode());

						// Increasing enemy counter
						enemyCount++;

					}
				}


			}
		});

		spawnEnemyTimer = new Timeline(kfEnemy);
		spawnEnemyTimer.setCycleCount(Timeline.INDEFINITE);

		backgroundTimer.play();

	}


	// Methods
	public void endGame() {

		// Stopping timer
		timer.stop();
		spawnEnemyTimer.stop();
		backgroundTimer.stop();
		mPlayer.stop();

		// Running alert
		Platform.runLater(new Runnable() {
			public void run() {

				// Removing all nodes from screen
				removeFromScreen();
				alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setGraphic(player.getNode());

				// Changing text depending on if the game is won and which level it is
				if (gameWon) {

					// Alert to show player they won and what their final score was
					alert.setContentText("YOU WIN Kirby's Space Adventure!");

					alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setContentText("Your final score was: " + score);
					alert.setGraphic(player.getNode());
					alert.showAndWait();

					// Asking player what name they want saved
					dialog = new TextInputDialog();
					dialog.setHeaderText(null);
					dialog.setContentText("Input name you want saved with score: ");
					Optional<String> result = dialog.showAndWait();

					// Writing to file name and score
					try {

						bw.newLine();

						if (result.isPresent()) {
							if (result.get() == null || result.get() == "") {
								bw.write("UNKNOWN, " + score);
							}
							else {
								bw.write(result.get() + ", " + score);
							} 
						}
						else
							bw.write("UNKNOWN, " + score);
						bw.close();

					}
					catch (IOException e) {
						System.out.println(e.getMessage());
					}

					// Adding score to arraylist and resorting it
					scoresNum.add(score);

					Collections.sort(scoresNum);
					Collections.reverse(scoresNum);

					// Searching for the score to find players place on leaderboard
					int place = Collections.binarySearch(scoresNum, score, Collections.reverseOrder());

					// Showing player their place on leaderboard
					alert.setContentText("You are " + (place + 1) + " place on the leaderboard!");
					alert.showAndWait();

					alert.setContentText("You earned 7 tokens!");

				}

				// Changing text to loss text
				else {
					alert.setContentText("YOU LOSE! Kirby is lost in space!");
				}

				// Ending game
				alert.showAndWait();
				mPlayer.stop();
				gameDone = true;				

			}

		});

	}

	// Checks whether passed in background is offscreen and if so places it on other side
	private void isOffScreen (ImageView iv, int num) {


		// Checking if image is out of screen (left side) and moving it to right side if  so
		if (iv.getX() + width <= 0) {

			if (num == 1)
				ivBG.setX(990);
			else
				ivBGTwo.setX(990);


		}
	}

	// Method to turn fire to true and play sound effect
	public void fire() {
		fire = true;

		if (Main.muteFX == false)
			clips[0].play();
	}

	// method to sort arraylist (selectionSort)
	public void selectionSort (ArrayList<String> string , ArrayList<Integer> num) {

		// Loop that runs for one less than arrays length
		for (int i = 0; i < num.size() - 1; i++) {

			// Initializing lowest index placeholder
			int index = i;

			// Searching for lowest number
			for (int j = i + 1; j < num.size(); j++) {
				if (num.get(j) < num.get(index)) {
					index = j;
				}
			}

			// Swapping index at i with lowest index
			int temp = num.get(i);
			String tempStr = string.get(i);

			num.set(i, num.get(index));
			string.set(i, string.get(index));

			num.set(index, temp);
			string.set(index, tempStr);

		}

	}

}
