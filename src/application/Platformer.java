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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Platformer extends ArcadeMap {

	// Fields
	private ImageView ivBGTwo, flagpole, bulletBill, bulletBillTwo;
	private Player player;
	private int xPosTwo, spawnPlatformCount, spawnEnemyCount, counter;
	private Timeline jumpTimer, atkTimer, hittingTimer;
	private boolean jump, atk, onPlatform, flagpoleActive, intersects, hitting, jumpCooldown, jumpFXCooldown;
	private Font f;
	private ArrayList<Platforms> platform;
	private ArrayList<PlatformerEnemy> enemy;
	private Random rnd;
	private Rectangle flagRect;

	// Constructors
	public Platformer() {

		// Initializing player and setting player location
		player = new PlatformerPlayer();
		player.setLocation(300, 440);

		// Initializing background, flagpole and bulletbill imageviews
		background = new Image("file:platformer/platforms/platformer.jpg");
		flagpole = new ImageView(new Image("file:platformer/platforms/flag.png"));

		flagpole.setPreserveRatio(true);
		flagpole.setFitHeight(500);

		bulletBill = new ImageView(new Image("file:platformer/enemies/bulletBill.png"));
		bulletBill.setPreserveRatio(true);
		bulletBill.setFitHeight(350);

		bulletBillTwo = new ImageView(new Image("file:platformer/enemies/bulletBill.png"));
		bulletBillTwo.setPreserveRatio(true);
		bulletBillTwo.setFitHeight(350);

		bulletBill.setX(-700);
		bulletBill.setY(320);

		bulletBillTwo.setX(-700);
		bulletBillTwo.setY(0);

		ivBG = new ImageView(background);
		ivBGTwo = new ImageView(background);

		ivBG.setPreserveRatio(true);
		ivBG.setFitWidth(1400);

		ivBGTwo.setPreserveRatio(true);
		ivBGTwo.setFitWidth(1400);

		// Setting width and height of screen
		width = 1400 - 50;
		height = (int)background.getHeight();

		// Initializing other fields
		xPos = 0;
		xPosTwo = width;
		yPos = 0;
		counter = 0;
		score = 0;
		speed = 7;

		ivBG.setX(xPos);
		ivBG.setY(yPos);

		ivBGTwo.setX(xPosTwo);
		ivBGTwo.setY(yPos);

		// Adding nodes to root
		root.getChildren().addAll(getBG(),getBGTwo(), bulletBill, bulletBillTwo);
		root.getChildren().add(player.getNode());

		// Initializing label and font
		f = Font.loadFont("file:fonts/SuperMario256.ttf", 40);
		lbl = new Label("SCORE: " + score);
		lbl.setTextFill(Color.WHITE);
		lbl.setFont(f);
		lbl.setPrefSize(300,  50);
		lbl.setAlignment(Pos.CENTER);
		root.getChildren().add(lbl);
		lbl.setLayoutX(1390 - lbl.getPrefWidth());
		lbl.setLayoutY(10);

		// Initializing arraylist and random
		platform = new ArrayList<Platforms>();
		enemy = new ArrayList<PlatformerEnemy>();
		scoresText = new ArrayList<String>();
		scoresNum = new ArrayList<Integer>();
		rnd = new Random();

		// Spawning platforms at beginning of the game
		spawnPlatform();

		// Initializing file, buffered reader/writer, file reader/writer
		try {
			file = new File("textFiles/platformerScores.txt");
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
				bubbleSort(scoresText, scoresNum);

				// Reversing the order to be from highest score to lowest
				Collections.reverse(scoresText);
				Collections.reverse(scoresNum);
			}
		}		
		catch (Exception e) {
			e.getMessage();
		}

		// Initializing background music
		soundFile = new File("platformer/sounds/Super Mario Bros. Theme Song.mp3");
		media = new Media(soundFile.toURI().toString());
		mPlayer = new MediaPlayer(media);
		mPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mPlayer.setVolume(0.3);

		if (Main.muteMusic == false) {
			mPlayer.play();
		}

		// Initializing sound effects
		clips = new AudioClip [4];
		for (int i = 0; i < clips.length; i++) {
			clips[i] = new AudioClip("file:platformer/sounds/effect" + i + ".wav");
		}

		clips[1].setVolume(0.2);
		clips[0].setVolume(0.08);

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

				// If they click yes then display all highscores
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

			}

		});

		// Animation timer to run game
		timer = new AnimationTimer() { 
			public void handle(long val) {

				// Moving bullet bill
				if (left) {
					bulletBill.setX(bulletBill.getX()  + 8);
					bulletBillTwo.setX(bulletBillTwo.getX()  + 8);
				}
				else if (right) {
					bulletBill.setX(bulletBill.getX()  - 0.6);
					bulletBillTwo.setX(bulletBillTwo.getX()  - 0.6);
				}
				else {
					bulletBill.setX(bulletBill.getX()  + 3);
					bulletBillTwo.setX(bulletBillTwo.getX()  + 3);
				}

				// If bullet bill and player intersects tehen player loses
				if (bulletBill.getBoundsInParent().intersects(((PlatformerPlayer) player).getRightMask())
						|| bulletBill.getBoundsInParent().intersects(((PlatformerPlayer) player).getRightMask())) {
					endGame(bulletBill);
				}

				// Checking if they intersect with any platforms on the left or right side
				if (platform.size() > 0) {

					// Turning onPlatform variable false
					onPlatform = false;
					for (int i = 0; i < platform.size(); i++) {

						// Checking right side of player
						if (((PlatformerPlayer) player).getRightMask().intersects(platform.get(i).getMask()) == true) {
							right = false;
						}

						// Checking right side of player
						if (((PlatformerPlayer) player).getLeftMask().intersects(platform.get(i).getMask()) == true) {
							left = false;
						}

						// Checking if player hits bottom of platform and pushing them down if so
						if (((PlatformerPlayer) player).getTopMask().intersects(platform.get(i).getMask()) == true) {
							jump = false;		
							jumpCooldown = true;
						}

						// Turning onPlatform boolean to true if they are on a platform
						if (((PlatformerPlayer) player).getBottomMask().intersects(platform.get(i).getMask()) == true) {
							onPlatform = true;					
						}

						// Placing player on top of platform if intersecting and not jumping
						if (((PlatformerPlayer) player).getBottomMask().intersects(platform.get(i).getMask()) == true && jump == false) {
							((PlatformerPlayer) player).setY(platform.get(i).getY() - ((PlatformerPlayer) player).getHeight() + 10);
						}

					}
				}

				// Checking if player is moving left and moving maps left if so
				if (left) {
					xPos += speed;
					xPosTwo += speed;
					((PlatformerPlayer) player).setDirection(player.LEFT);

					// If flagpole is on screen moving it with map
					if (flagpoleActive) {
						flagpole.setX(flagpole.getX() + speed);
					}

					// Checking if there are any platforms on screen and moving them with the map if so
					if (platform.size() > 0) {
						for (int i = 0; i < platform.size(); i++) {
							platform.get(i).setX(platform.get(i).getX() + speed);
						}
					}

				}

				// Checking if player is moving right and moving maps left if so
				else if (right) {
					xPos -= speed;
					xPosTwo -= speed;

					((PlatformerPlayer) player).setDirection(player.RIGHT);

					// If flagpole is on screen moving it with map
					if (flagpoleActive) {
						flagpole.setX(flagpole.getX() - speed);
					}

					// Checking if there are any platforms on screen and moving them with the map if so
					if (platform.size() > 0) {
						for (int i = 0; i < platform.size(); i++) {
							platform.get(i).setX(platform.get(i).getX() - speed);
						}
					}

				}

				// Activating gravity if not intersecting a platform
				if (jump == false && onPlatform == false) {
					((PlatformerPlayer) player).gravity(450);
				}
				// Stopping gravity if on a platform
				else {
					jumpCooldown = false;
					((PlatformerPlayer) player).stopGravity();
				}

				// Making the player jump if jump is true
				if (jump == true && jumpCooldown == false) {

					// Starting jump timer and calling on player jump method
					jumpTimer.play();
					((PlatformerPlayer) player).jump();
					((PlatformerPlayer) player).stopGravity();

					// If players yPos becomes greater than 440 or onPlatform is true then jump timer stops and player gravity is reset
					if (player.getY() >= 440 || onPlatform) {
						jumpTimer.stop();
						jump = false;
						((PlatformerPlayer) player).resetJumpGravity(0.05);
						jumpFXCooldown = false;
					}

				}
				
				// If player's yPos falls through ground put them onto ground
				if (player.getY() >= 440) {
					player.setY(440);
				}

				// Playing attack timer if player is attacking
				if (atk == true) {
					((PlatformerPlayer) player).atk(true);
					atkTimer.play();
				}

				// Loop that runs for platforms
				if (platform.size() > 0) {
					for (int i = 0; i < platform.size(); i++) {

						// Turning platform onScreen variable true if on screen
						if (platform.get(i).getX() <= 1600) {
							platform.get(i).onScreen(true);
						}

						// Checking if platform is offscreen and removing it if so
						if (platform.get(i).getX() + platform.get(i).getWidth() + 30 <= 0) {
							root.getChildren().remove(platform.get(i).getNode());
							platform.remove(platform.get(i));

							// Removing enemy from platform if the platform is on screen
							for (int j = 0; j < enemy.size(); j++) {
								if (enemy.get(j).getPlatformNumber() > 0) {
									enemy.get(j).setPlatformNumber(-1);
								}
							}

							break;
						} 

						// Checking if player is hiting platform and removing it from arraylist and root if so
						if (((PlatformerPlayer) player).getRightMask().intersects(platform.get(i).getMask()) ||
								((PlatformerPlayer) player).getLeftMask().intersects(platform.get(i).getMask())) {
							if (hitting) {

								// Increasing score
								root.getChildren().remove(platform.get(i).getNode());
								platform.remove(platform.get(i));
								onPlatform = false;
								score += 50;

								// If enemy is on a platform that was broken then make enemy.onPlatform false
								if (enemy.size() > 0) {
									for (int j = 0; j < enemy.size(); j++) {
										if (enemy.get(j).getPlatformNumber() == i) {
											enemy.get(j).onPlatform(false);

										}
									}
								}

								break;

							}
						}

					}
				}

				// Loop for enemies
				if (enemy.size() > 0) {
					for (int i = 0; i < enemy.size(); i++) {

						// Updating enemy node
						enemy.get(i).getNode();

						// Moving enemy left if dir is left
						if (left)
							enemy.get(i).setX(enemy.get(i).getX() + speed);

						// Moving enemy right if dir is right
						if (right)
							enemy.get(i).setX(enemy.get(i).getX() - speed);

						// Checking if player is hitting an enemy
						if (player.getMask().intersects(enemy.get(i).getMask()) && hitting) {

							// Playing sfx if sfx isn't muted
							if (Main.muteFX == false) {
								clips[2].play();
							}

							// Removing enemy from arraylist and root, increasing score
							root.getChildren().remove(enemy.get(i).getNode());
							enemy.remove(enemy.get(i));
							score += 100;
							break;
						}
						// Checking if player jumps on enemies head
						else if (player.getBottomMask().intersects(enemy.get(i).getMask())) {

							// Playing sfx if sfx isn't muted
							if (Main.muteFX == false) {
								clips[2].play();
							}

							// Removing enemy from arraylist and root, increasing score
							root.getChildren().remove(enemy.get(i).getNode());
							enemy.remove(enemy.get(i));
							score += 100;
							break;
						}
						// Checking if enemy hits player, ending game (player loses)
						else if (player.getMask().intersects(enemy.get(i).getMask()) && !atk) {
							endGame(enemy.get(i).getNode());
							break;
						}

						// Checking if enemy is offscreen and removing from arraylist and root if so
						if (enemy.get(i).getX() + enemy.get(i).getWidth() <= 0) {
							root.getChildren().remove(enemy.get(i).getNode());
							enemy.remove(enemy.get(i));
							break;
						}

						// Checking if enemy is on a platform and moving them from left to right on the platform
						if (enemy.get(i).getPlatformNumber() == -1) {
							enemy.get(i).activateGravity();
						}
						else if (enemy.get(i).isOnPlatform() && platform.size() > 0) {
							try {
								enemy.get(i).moveHorizontal((int)platform.get(enemy.get(i).getPlatformNumber()).getX(), (int)platform.get(enemy.get(i).getPlatformNumber()).getX() + 300);
							}
							catch (Exception e) {}
						}
						// If enemy is not on a platform then move them regularly
						else {
							enemy.get(i).move();

							// If enemy moving on ground hits a platform switch directions
							if (platform.size() > 0) {
								for (int j = 0; j < platform.size(); j++) {
									if (enemy.get(i).getMask().intersects(platform.get(j).getMask())) {
										enemy.get(i).switchDir();
									}
								}
							}

							// Switching enemy direction between a certain radius
							if (enemy.get(i).getX() >= 1500 || enemy.get(i).getX() <= 1200) {
								enemy.get(i).switchDir();
							}

							// Activating enemy gravity if their yPos is less than 600
							if (enemy.get(i).getY() <= 600) {
								enemy.get(i).activateGravity();
							}
							// Otherwise stop enemy gravity
							else {
								enemy.get(i).stopGravity();
							}

						}

					}
				}

				// Mask for flag
				flagRect = new Rectangle(flagpole.getX() + 30, 0, 10, 787);

				// Checking if player intersects the flagpole and ending the game if so (player won)
				if (flagpoleActive) {
					if (player.getMask().intersects(flagRect.getBoundsInParent()) ) {
						gameWon = true;

						// Stoping bg music if bg music is being played
						if (Main.muteMusic == false)
							mPlayer.stop();

						// Playing sfx if sfx isn't muted
						if (Main.muteFX == false) {
							clips[3].play();
						}

						endGame(player.getNode());
					}
				}

				// Setting background imageviews to match their respective xPos variables
				ivBG.setX(xPos);
				ivBGTwo.setX(xPosTwo);

				// Calling on isOffScreen method for each background respectively
				isOffScreen(ivBG, 1);
				isOffScreen(ivBGTwo, 2);

				// Updating player node
				player.getNode();

				// Updating score
				lbl.setText("SCORE:" + score);

				// Checking if they've passed the screen 15 times and adding the flagpole to the screen if so
				if (counter == 15) {
					root.getChildren().add(flagpole);
					flagpole.setX(3000);
					flagpole.setY(200);
					counter = 100;
					flagpoleActive = true;
				}

			}

		};

		// Keyframe for jump
		KeyFrame kfJump = new KeyFrame(Duration.millis(50), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				// Increasing jump gravity (accelerator to make player fall on jump)
				((PlatformerPlayer)player).setJumpGravity(.7);
			}
		});

		jumpTimer = new Timeline(kfJump);
		jumpTimer.setCycleCount(Timeline.INDEFINITE);

		// Keyframe for attack
		KeyFrame kfAttack = new KeyFrame(Duration.millis(600), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				// Runs once attack gif is finished, so turns off attack variables
				atk = false;
				hitting = true;
				hittingTimer.play();
			}
		});

		atkTimer = new Timeline(kfAttack);
		atkTimer.setCycleCount(1);

		// Keyframe to break things (platforms and enemies)
		KeyFrame kfBreak = new KeyFrame(Duration.millis(300), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				((PlatformerPlayer) player).atk(false);
				hitting = false;

			}
		});

		hittingTimer = new Timeline(kfBreak);
		atkTimer.setCycleCount(1);

	}

	// Methods
	
	// Return bgTwo
	public ImageView getBGTwo() {
		return ivBGTwo;
	}

	// Method for player jumping
	public void jump() {
		jump = true;
		
		// Moving player up
		player.setY(player.getY() - 10);

		// Playing sfx if sfx isn't muted
		if (Main.muteFX == false && jumpFXCooldown == false) {
			clips[0].play();
			jumpFXCooldown = true;
		}
	}

	// Method to activate attack
	public void attack() {
		atk = true;

		// Playing sfx if sfx isn't muted
		if (Main.muteFX == false) {
			clips[1].play();
		}
	}

	// Checks whether passed in background is offscreen and if so places it on other side
	private void isOffScreen (ImageView iv, int bg) {


		// Checking if image is out of screen (left side) and moving it to right side if  so
		if (iv.getX() + width <= 0) {
			if (bg == 1)
				xPos = width;

			else if (bg == 2) {
				xPosTwo = width;
			}

			// Increasing counter for screens passed and score
			counter++;
			score += 50;

			// Spawning enemy and platform if flagpole is not active
			if (flagpoleActive == false) {
				spawnPlatform();
				spawnEnemy();
			}

		}

		// Checking if image is out of screen (right side) and moving it to left side if  so
		if (iv.getX() >= 1400) {
			if (bg == 1)
				xPos = -1340;

			else if (bg == 2) {
				xPosTwo = -1340;
			}

			// Reducing counter for screens passed and score
			counter--;
			score -= 50;

		}

	}

	// Method to spawn platforms
	private void spawnPlatform() {
		// Randomizing number of platforms spawned
		spawnPlatformCount = rnd.nextInt(5) + 1;

		for (int i = 0; i < spawnPlatformCount; i++) {

			// Creating new index and adding to root
			platform.add(new Platforms(700, 440));
			root.getChildren().add(platform.get(platform.size() - 1).getNode());


			do {
				// Randomizing location (making sure it doesn't intersect anything else
				platform.get(platform.size() - 1).setLocation(2400, 400);

				intersects = false;
				for (int j = 0; j < platform.size() - 1; j++) {
					if (platform.get(platform.size() - 1).getMask().intersects(platform.get(j).getSpawnMask()) || 
							platform.get(platform.size() - 1).getMask().intersects(player.getMask())) {
						intersects = true;
					}

				}

			}
			while (intersects);
		}		
	}

	// Method to spawn enemies
	private void spawnEnemy() {
		// Randomzing number of enemies spawned
		spawnEnemyCount = rnd.nextInt(4) + 1;

		// Loop that spawn enemies
		for (int i = 0; i < spawnEnemyCount; i++) {

			//  Boolean saying enemies should spawn on platform
			boolean shouldSpawnOnPlatform  = true;

			// Adding enemy to arraylist and node
			enemy.add(new PlatformerEnemy());

			// If value == 0 then they spawn on platform
			if (shouldSpawnOnPlatform == true && platform.size() > 0) {

				// Raandomizing which platform
				int platform = rnd.nextInt(this.platform.size());

				// If an enemy is already on that platform spawn them on the ground instead
				for (int j = 0; j < enemy.size(); j++) {
					try {
					if (enemy.get(j).getPlatformNumber() == platform || this.platform.get(j).isOnScreen()) {
						shouldSpawnOnPlatform = false;
					}
					}
					catch (Exception e) {}
				}

				// Spawning the enemy on a platform
				if (shouldSpawnOnPlatform == true) {
					enemy.get(enemy.size() - 1).onPlatform(true);
					enemy.get(enemy.size() - 1).setPlatformNumber(platform);
					enemy.get(enemy.size() - 1).setLocation((int)this.platform.get(platform).getX(), (int)this.platform.get(platform).getY());
				}
			}

			// Spawning enemy on ground making sure they don't intersect the player
			if (shouldSpawnOnPlatform == false) {
				do {

					// Spawning player at a different yPos depending on if its a goomba or koopa
					if (enemy.get(enemy.size() - 1).getType() == 0)
						enemy.get(enemy.size() - 1).setY(590 - enemy.get(i).getHeight());

					else if (enemy.get(enemy.size() - 1).getType() == 1) 
						enemy.get(enemy.size() - 1).setY(560 - enemy.get(i).getHeight());


					// Randomizing enemy xPos
					enemy.get(enemy.size() - 1).setX((rnd.nextInt(1600)) + 1600);
				} 
				while (enemy.get(enemy.size() - 1).getMask().intersects(((PlatformerPlayer) player).getSpawnMask()));
			}

			// Adding enemy to root
			root.getChildren().add(enemy.get(enemy.size() - 1).getNode());

		}		
	}

	// Method for ending game
	private void endGame(ImageView iv) {

		// Stopping timers
		timer.stop();
		iv.setPreserveRatio(true);
		iv.setFitWidth(50);

		// If the game is won showing final score
		if (gameWon) {

			// How to do 
			Platform.runLater(new Runnable() {
				public void run() {
					alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setContentText("YOU WIN!\nYour final score was: " + score);
					alert.setGraphic(iv);
					alert.showAndWait();

					dialog = new TextInputDialog();
					dialog.setHeaderText(null);
					dialog.setGraphic(iv);

					// Asking user what name they want saved with final score
					dialog.setContentText("Input name you want saved with score: ");
					Optional<String> result = dialog.showAndWait();

					// Writing name and final score onto textfil
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

					alert.setContentText("You earned 10 tokens!");
					alert.showAndWait();

				}

			});

		}
		
		// Otherwise showing a loss alert
		else {
			// How to do 
			Platform.runLater(new Runnable() {
				public void run() {
					alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setContentText("YOU LOSE! Kirby's Kingdom is lost FOREVER.");
					bulletBill.setFitHeight(50);
					alert.setGraphic(iv);

					alert.showAndWait();
				}

			});
		}

		// Ending game
		removeFromScreen();
		mPlayer.stop();
		gameDone = true;

	}

	// Method to sort arraylist (bubbleSort)
	public void bubbleSort(ArrayList<String> string , ArrayList<Integer> num) {

		// Main loop that runs for length of array
		for (int end = num.size() - 1; end > 0; end--) {

			boolean done = true;

			for (int i = 0; i < end; i++) {

				// Checking if data at index i is greater then i + 1 and swapping if so
				if (num.get(i) > num.get(i + 1)) {
					done = false;
					int temp = num.get(i);
					String tempStr = string.get(i);

					//num.get(i).set(num.get(i + 1));
					num.set(i, num.get(i+1));
					num.set((i+1), temp); 

					string.set(i, string.get(i+1));
					string.set((i+1), tempStr); 

				}

			}

			if (done)
				break;

		}

	}

}
