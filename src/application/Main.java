/* Arcade Breakout
 * Noah Balatbat
 * Program Description: This program is a game in which the player must escape an arcade, playing as Nintendo's Kirby. The game opens with a retro 
 * style menu with two buttons: Start and settings. If they press settings then they are brought to a little menu with a 
 * "mute sound effect" and a "mute background music" checkbox. The player can press the "back" button to return to the main 
 * menu, then start the game. Once they have pressed the "start" button they are brought to a blank screen in which they see
 * letters appear, being the story screen. The story screen plays and informs the player about the goal of the arcade. Once 
 * they have gone through all story screens they are brought to the main arcade map. The player can move using the WASD keys,
 * and has 1 token. The tokens can be used to play the games within the arcade, with each game requiring and rewarding a 
 * different amount of tokens. Once they intersect with an arcade machine if they have enough tokens to play the game they are
 * shown an alert with different menu options for the specific game. The options include a story, controls, enter and exit button.
 * By pressing the story button, the player is brought through the backstory for that specific arcade machine. By pressing the 
 * controls button, the player is taught both the controls and objective to the specific arcade game. Each game contains an
 * individual story and controls. At the beginning of each main game, the program reads a text file containing previous
 * scores and reads the highest score to the player. They are given the option to display all scores. If they choose otherwise, 
 * the game begins. Likewise, at the end of the game (if they have won) players are prompted for their name from a 
 * textInputDialog. The code then writes to the text file for the individual game, writing the player's name and score separated
 * by a comma. It also tells the player what place their score was on the current leaderboard. Although they are technically 
 * allowed to play any game they would like, that is only if they have enough tokens for it. At initial spawn, the player may 
 * only play one main game: Bomb Detector. Bomb detector costs 1 token and rewards 5. Once they have pressed enter from the 
 * bomb detector alert they are brought into a smaller screen with a grid on it. On the top left is a timer counting each 
 * second that passes and opposite to it is Kirby sleeping (which is important to the story). Bomb detector contains 3 levels. 
 * In each level the grid gets slightly bigger (being of size 4x4, 6x6 and 7x7) and contains more bombs (being 4, 7 and 10 
 * bombs). To win minesweeper the player must click every non-bomb square. Using their mouse/trackpad they can click on any 
 * square within the grid. Clicking on a square once will "flag" it, leaving a marker on the grid. This is useful to mark where
 * players think are bombs. Clicking a second time will reveal whether or not the square is a bomb. If it isn't a bomb, then a 
 * number will be displayed indicating how many bombs surround that tile. If it is blank there are no bombs around the tile. 
 * If the tile is a bomb, the game will be over and the player will be sent back to the arcade. The arcade remembers what level 
 * the player is on, so if they lose on the third bomb detector level they will be brought back to that one for the price of 
 * one token. The second main arcade game is Kirby: In Space. Kirby: in Space requires 5 tokens and rewards 7. 
 * Once the player enters the level, they are shown Kirby riding a star with a a space themed background. 
 * The goal of this game is to gain the most amount of points without dying. Kirby gains points by hitting enemies with his 
 * stars using the "J" key, and loses points and health by intersecting with enemies, being hit by their lasers or having
 * the enemies pass his side. Enemies move in either a diagonal or straight pattern and have a randomized chance to have 
 * a laser. After a certain amount of enemies have appeared, the boss will spawn. The boss will fire multiple bullets and keep
 * moving towards Kirby until it reaches the halfway mark on the screen. The boss will either help to boost player score or
 * reduce health to the point that the player loses, ruining the player's attempt on the level. The third main game is Kirby's
 * Kingdom, which requires 7 tokens and rewards 10. Kirby's kingdom is a Mario style platformer, in which the player uses the 
 * 'A' and 'D' keys to move left and right, the 'J' key to swing Kirby's sword the and the spacebar to jump. Once the player
 * enters the level they will see two large Bullet Bill's coming towards them on the left, indicating they must run away from
 * them. The player will face obstacles such as spawning blocks and enemies such as goombas and koopa troopas. Players may
 * swing their sword or jump on both obstacles and enemies. By jumping on obstacles they will be put on top of them, and by 
 * jumping/hitting enemies they can destroy them. Blocks may also be broken with using Kirby's sword. The level is won once
 * they reach the end of the level, denoted by a flag. The flag appears after the screen is refreshed 15 times. The player may
 * lose from either intersecting an enemy or being caught by the Bullet Bill's. Once the player has reached 10 or more tokens,
 * they have earned their freedom and the right to leave the arcade, making their Arcade Breakout! They may choose to leave by
 * intersecting the star on the right side of the arcade map, or continue playing the games as many times as they want (NOTE: 
 * If they choose not to end the game, they must still have a minimum of 10 tokens to leave the arcade). Once they intersect
 * the star, the program saves the highest scores which the player achieved from each game and the name they inputted in the 
 * beginning and writes them on a  text file, indicating to future players that they have succeeded in the arcade breakout. 
 * Because it is possible to have zero tokens by losing games, there is a secret game located on the left side of the arcade map
 * (marked by a "out of order" sign) known as Kirby Ball. Kirby Ball requires no tokens to play and is a simple game in which
 * the player uses their 'W' and 'S' keys to move a paddle up and down their side. To win, they must keep hitting the ball with
 * the paddle until it gets out on the opponents side of the screen. Kirby Ball rewards up to 5 tokens.
 * Program Details: Many JavaFX components are used throughout the program, with the main components being labels, buttons and
 * checkboxes. Labels are used in the program to show score, tokens, time and text to the player. This happens during each 
 * level and during the main menu screen. Buttons are used very heavily within the main menu screen, as they are used to traverse
 * the menu, the settings and the story screens. Checkboxes are used in the settings menu to toggle muting sound effects and
 * muting background music. Alerts are also used in almost every aspect of speaking to the player that is not in the story
 * screen. Informing the player of high scores, controls, game specific stories and error messages all come from alerts with
 * custom images and buttons. A specific example is with the enter level alert, which features custom images depending on
 * the game and custom buttons. Multiple layouts are mainly used for the title screen, although they are used aswell for 
 * bomb detector. The title screen is made up of a pane, gridpane and label within a borderpane which sits on top of the main
 * root pane. Within the pane are labels and within the gridpane is a vBox and an imageview. The settings menu and story screens
 * are also their own panes so that they can be grouped together and easily tracked. A gridpane is also used in the bomb 
 * detector level to format the cells together. This also ties in to the use of 2D arrays. 2D arrays are used to track
 * the grid of bombs, where the user clicks and the value stores in the cell. 1D arrays are used throughout the program, 
 * including for audioclips, the story strings, and final scores to name a few. Other places include the alien boss' lasers 
 * and the aliens random image. Sorting and searching methods are used in tandem with the file class and use of files. Files
 * are used within the game to store previous scores, being read to get the previous scores and written to to add new scores.
 * Each sorting method (insertion, bubble, selection) is implemented (one for each main game) to sort the high scores once
 * they are read from the file. This is used to display the list of scores in order to the player at the beginnning of the 
 * level. A searching algorithm is used once the level is over and if the player has won to search for where the player's place
 * would be on the scoreboard. This is done by adding the player's score to the arraylist of scores, sorting it, then searching
 * for the score of the player. Object oriented programming, animation and collision detection is the root of the program. 
 * Animation timers and animation is used to move the imageviews around the screen for the main level, while collision detection
 * is used between nodes for the score deduction and increasing within levels like Kirby's Kingdom and Kirby: in Space. Sounds
 * are used both as background music in the menu and in games and for sound effects, with some sound effects being for button 
 * clicking, attacking, jumping, firing, intersection and clicking. Arraylists are used throughout the program to keep track
 * of a number of things. This is useful to create a nonspecfic number of objects for the games, like for the number of platforms
 * or enemies wanted in Kirby's Kingdom. Other uses were for the projectiles and enemies within Kirby: in Space, so that a seemingly
 * unlimited amount may spawn. Finally, abstract classes are used are used in combination with inheritance to create more 
 * efficient and shorter code. Abstract classes are used both or projectiles and enemies. This is useful because the program
 * needed both two types of enemies and projectiles, which shared very similar code. by creating an abstract class the code was
 * made much shorter and more efficient to read. Polymorphism was used between all the levels, as the variable was redefined in 
 * main for the level which the user was playing. This allowed the program so save memory and be much shorter. Inheritance was
 * used between all the player classes aswell, being the player classes for the platformer and main menu.
 */

package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {

	// Fields
	public static Pane root = new Pane();
	public static boolean muteFX, muteMusic;

	private ArcadeMap lvl;
	private int tokens, minesweeperSeconds;
	public boolean up, down, left, right, jump, cooldown, travelLevelOne, travelLevelTwo, travelLevelThree, 
	playKirbyBall, cooldownStar, gameDone, gameOverShown, changed;
	private int level, minesweeperlvl, storyPage, stringCounter;
	private String [] storyStrings;
	private String str, name;
	private String finalScores [];
	private Timeline cooldownTimer, cooldownStarTimer, storyTimer;
	private ButtonType btnObjective, btnControls, btnEnter, btnExit;
	private ImageView ivCoin;
	private Scanner scan;
	private Font titleFont, textFont, buttonFont, settingsFont, storyFont;
	private Label lblTitle, lblTitleOutline, lblPlayer, lblHighscore, lblStory, lblCredits, lblHighscoreNum;
	private Button btnStart, btnBack, btnSettings, btnStoryBack, btnSkip, btnNext;
	private VBox vbox;
	private GridPane grid;
	private BorderPane border;
	private Pane pane, settingsPane, storyPane;
	private Timeline titleScreenTimer;
	private CheckBox chkSoundEffects, chkBackgroundMusic;
	private TextField txtInput;
	public Label lbl;
	public BufferedReader br;
	public FileReader fr;
	public BufferedWriter bw;
	public FileWriter fw;
	public File file, soundFile;
	public ArrayList<String> scoresText;
	public ArrayList<Integer> scoresNum;
	public Alert alert;
	public Media media;
	public MediaPlayer mPlayer;
	public AudioClip clips [];
	public AnimationTimer timer;


	public void start(Stage primaryStage) {
		try {

			Scene scene = new Scene(root, 900, 700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();


			// Initialize fields
			lvl = new ArcadeMap();
			finalScores = new String[4];
			scan = new Scanner(System.in);
			level = 0;
			minesweeperlvl = 1;
			minesweeperSeconds = 0;
			tokens = 100;
			changed = false;
			gameDone = false;
			gameOverShown = false;
			str = "";

			// Intializing story string
			storyStrings = new String [] {"Welcome to the arcade. \nEver since new multiplayer games have come out, \npeople "
					+ "have stopped coming to arcades. But not you, \nmy loyal friend. I know you truly enjoy the classics. "
					+ "\nTell me your name.", "Welcome .\nI have brought you here to let you \nexperience real gaming once "
							+ "again. \nWithin these walls contains many \nbeloved, classic games featuring \na character special in "
							+ "many hearts...\nKirby!", "You can play as many games as you want... forever. \nOfcourse, if you ever "
									+ "want to leave, you must earn \nthat right. If you can gain 10 tokens then you are \ntruly a game master. ",
									"I give you this token to begin your arcade breakout. \nGood luck. Oh, and make sure to play every game and \nbeat the highscore!"

			};

			// Initializing sounds
			// Initializing background music
			soundFile = new File("sounds/lobby.mp3");
			media = new Media(soundFile.toURI().toString());
			mPlayer = new MediaPlayer(media);
			mPlayer.setCycleCount(MediaPlayer.INDEFINITE);
			mPlayer.setVolume(0.2);

			// Playng background music
			mPlayer.play();

			// Initializing sound effects
			clips = new AudioClip [2];

			for (int i = 0; i < clips.length; i++) {
				clips[i] = new AudioClip("file:sounds/effect" + i + ".wav");
			}

			clips[0].setVolume(0.8);
			clips[1].setCycleCount(AudioClip.INDEFINITE);

			// Initializing fonts
			titleFont = Font.loadFont("file:fonts/Retro Gaming.ttf", 65);
			textFont = Font.loadFont("file:fonts/Retro Gaming.ttf", 25);
			buttonFont = Font.loadFont("file:fonts/Retro Gaming.ttf", 35);
			settingsFont = Font.loadFont("file:fonts/Retro Gaming.ttf", 15);
			storyFont = Font.loadFont("file:fonts/Retro Gaming.ttf", 20);

			// Initiializing finalScores array
			finalScores = new String [] {"", "0", "0", "0"};
			root.setStyle("-fx-background-color: black");


			// Initializing file, buffered reader/writer, file reader/writer
			file = new File("textFiles/finalScores.txt");
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);

			// Initializing buttontype variables
			btnObjective = new ButtonType("Objective");
			btnControls = new ButtonType("Controls");
			btnEnter = new ButtonType("Enter");
			btnExit = new ButtonType("Exit");

			// Initializing coin counter imageview
			Image coin = new Image("file:background/coin.png");
			ivCoin = new ImageView(coin);
			ivCoin.setPreserveRatio(true);
			ivCoin.setFitWidth(100);

			// Initializing label with number of coins
			lbl = new Label("x" + tokens);
			lbl.setTextFill(Color.WHITE);
			lbl.setFont(Font.loadFont("file:fonts/Retro Gaming.ttf", 30));
			lbl.setTextFill(Color.WHITE);
			lbl.setPrefSize(150,  50);
			lbl.setAlignment(Pos.CENTER);
			lbl.setLayoutX(45);
			lbl.setLayoutY(15);

			timer = new AnimationTimer() {
				public void handle(long val) {

					// If on arcade map
					if (level == 0) {

						// Checking if they have more than 10 tokens, meaning they can leave the arcade
						if (tokens >= 10 && gameDone == false && gameOverShown == false) {
							// Telling them they can leave through an alert and displaying star to leave
							runInformationAlert("Congragulations! You have 10 or more tokens, meaning you may now leave the arcade.\n Just run towards the star to leave.");
							root.getChildren().add(lvl.getStar());
							gameOverShown = true;

						}

						// Alert that runs if they intersect with star and have ten or more tokens
						if (tokens >= 10 && lvl.checkStarIntersect()) {
							gameDone = true;
							runInformationAlert("Thanks for playing!");
						}
						// Alert that runs if they intersect with star and have less than ten tokens
						else if (tokens < 10 && lvl.checkStarIntersect()){
							runInformationAlert("You have less than 10 tokens again! Must have 10 tokens to leave");
						}

						// Checking if they intersect with bomb detector arcade machine
						if(lvl.checkArcadeIntersect(0)) {

							if (muteFX == false)
								clips[0].play();

							// Checking if they have enough tokens to play (minimum 1)
							if (tokens < 1) {
								// If not, telling them they need more tokens
								runInformationAlert("You must have at least 1 (one) token to play Bomb Detector.");
							}
							// Otherwise run alert to enter bomb detector
							else {
								// Displaying score if they've played this game already
								if (finalScores[1] != "0")
									runInformationAlert("You have already beat this level! your score was: " + finalScores[1]);

								runConfirmationAlert(primaryStage);
								travelLevelOne = true;
							}
						}

						// Checking if they intersect with Kirby: In space arcade machine
						if(lvl.checkArcadeIntersect(1)) {

							if (muteFX == false)
								clips[0].play();

							// Checking if they have enough tokens to play (minimum 5)
							if (tokens < 5) {
								runInformationAlert("You must have at least 5 (five) token to play Kirby: In Space.");
							}
							// Otherwise run alert to enter Kirby: in Space
							else {

								// Displaying score if they've played this game already
								if (finalScores[2] != "0")
									runInformationAlert("You have already beat this level! your score was: " + finalScores[1]);

								runConfirmationAlert(primaryStage);
								travelLevelTwo = true;
							}
						}

						// Checking if they intersect with Kirby's Kingdom arcade machine
						if(lvl.checkArcadeIntersect(2)) {

							if (muteFX == false)
								clips[0].play();

							// Checking if they have enough tokens to play (minimum 7)
							if (tokens < 7) {
								runInformationAlert("You must have at least 7 (seven) token to play Kirby's Kingdom.");
							}
							// Otherwise run alert to enter Kirby's Kingdom
							else {
								// Displaying score if they've played this game already
								if (finalScores[3] != "0")
									runInformationAlert("You have already beat this level! your score was: " + finalScores[1]);

								runConfirmationAlert(primaryStage);
								travelLevelThree = true;
							}

						}

						// Checking if they intersect with KirbyBall poster
						if (lvl.checkSecretGame()) {
							playKirbyBall = true;
							runConfirmationAlert(primaryStage);
						}

					}

					// If they are in kirbyBall
					if (level == -1) {

						// Checking if the level is done, if so stop timers and go back to arcade
						if (lvl.gameDone()) {
							playKirbyBall = false;
							stopTimers();

							// If they won then increase token count
							if (((KirbyBall) lvl).isGameWon() && tokens < 4) {
								tokens++;
							}

							goToArcade(primaryStage);

						}
					}

					// If they are in bomb detector
					if (level == 1) {

						// Checking if the level is done, if so stop timers and go back to arcade
						if (lvl.gameDone()) {
							travelLevelOne = false;
							stopTimers();

							// If they won increase their minesweeper level until they beat level 3
							if (lvl.isGameWon()) {
								minesweeperlvl++;
								minesweeperSeconds = ((MineSweeper) lvl).getSeconds();
								level = 1;

								if (minesweeperlvl < 4)
									goToMinesweeper(primaryStage);
								else
									goToArcade(primaryStage);
								
							}

							// If they lost bring them back to arcade
							else if (lvl.isGameWon() == false){
								goToArcade(primaryStage);
							}

							// If they beat the third minesweeper level then increase tokens and go back to arcade
							else if (minesweeperlvl == 3) {

								// Checking if their current score is better than their old score and replacing that as new high score
								if (Integer.parseInt(finalScores[0]) > ((MineSweeper) lvl).getSeconds()) {
									finalScores[0] = Integer.toString(((MineSweeper) lvl).getSeconds());
								}

								// Resetting minesweeper level
								tokens += 5;
								minesweeperlvl = 1;

								goToArcade(primaryStage);
							}
							else
								goToArcade(primaryStage);

						}
					}

					// If they are in Kirby: in Space
					if (level == 2) {

						// Checking if game is done, stopping timers and going to arcade if so
						if (((GalaxyShooter) lvl).gameDone()) {
							travelLevelTwo = false;
							stopTimers();

							// If they won the level the increase tokens
							if (lvl.isGameWon()) {

								// Checking if their current score is better than their old score and replacing that as new high score
								if (Integer.parseInt(finalScores[2]) < lvl.getScore()) {
									finalScores[2] = Integer.toString(lvl.getScore());
								}

								level = 0;
								tokens += 7;
							}

							goToArcade(primaryStage);


						}
					}

					// If in Kirby's Kingdom
					if (level == 3) {

						// If game is done stop timers and go back to arcade
						if (((Platformer) lvl).gameDone()) {
							travelLevelThree = false;
							lvl.up = false;
							lvl.down = false; 
							lvl.left = false;
							lvl.right = false;
							stopTimers();

							// If they won game increase token count
							if (lvl.isGameWon()) {

								// Checking if their current score is better than their old score and replacing that as new high score
								if (Integer.parseInt(finalScores[3]) < lvl.getScore()) {
									finalScores[3] = Integer.toString(lvl.getScore());
								}
								tokens += 10;
							}

							goToArcade(primaryStage);

						}
					}

					// Updating label
					lbl.setText("x " + tokens);

				}
			};

			// Keyframe for attack cooldown (Kirby's Kingdom)
			KeyFrame kfCooldown = new KeyFrame(Duration.millis(1500), new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					cooldown = false;
				}
			});

			cooldownTimer = new Timeline(kfCooldown);

			// Keyframe for star cooldown (Kirby: In Space)
			KeyFrame kfCooldownStar = new KeyFrame(Duration.millis(250), new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					cooldownStar = false;
				}
			});

			cooldownStarTimer = new Timeline(kfCooldownStar);

			// Creating keyboard listener to check to move player
			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
				public void handle(KeyEvent e) {

					// Moving player up, down, left, right if they press WASD (respectively)

					if (e.getCode() == KeyCode.A) {
						lvl.left = true;
					}

					if (e.getCode() == KeyCode.D) {

						lvl.right = true;
					}

					if (e.getCode() == KeyCode.W) {
						lvl.up = true;
					}

					if (e.getCode() == KeyCode.S) {
						lvl.down = true;
					}

					if (level == 3) {
						// If they're playing Kirby's Kingdom then activate jump on space
						if (e.getCode() == KeyCode.SPACE) {
							((Platformer) lvl).jump();
						}

						// If they're playing Kirby's Kingdom then activate attack when they press J
						if (cooldown == false)
							if (e.getCode() == KeyCode.J) {
								((Platformer) lvl).attack();

								// Starting cooldown for attack
								cooldown = true;
								cooldownTimer.play();
							}
					}

					if (level == 2 && cooldownStar == false) {
						// If they're playing Kirby: in Space then activate star fire on J
						if (e.getCode() == KeyCode.J) {
							((GalaxyShooter) lvl).fire();

							// Starting cooldown for attack
							cooldownStar = true;
							cooldownStarTimer.play();
						}
					}

					// Cheat to get tokens if they press C
					if (level == 0) {
						if (e.getCode() == KeyCode.C) {

							int num = 0;

							// Scanning for input on number of tokens to add
							while (true) {
								try {
									num = scan.nextInt();
									break;

								}
								catch(Exception ex) {
									System.out.println("ERROR: Must be a number.");
									scan.nextLine();
								}
							}

							// Adding number inputted to tokens
							tokens += num;
							System.out.print(num + " tokens added!");

						}
					}

				}
			});;


			scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
				public void handle(KeyEvent e) {

					// Stopping moving the player up, down, left, right if they release WASD (respectively)
					if (e.getCode() == KeyCode.A) {
						lvl.left = false;
					}

					if (e.getCode() == KeyCode.D) {
						lvl.right = false;
					}

					if (e.getCode() == KeyCode.W) {
						lvl.up = false;
					}

					if (e.getCode() == KeyCode.S) {
						lvl.down = false;
					}
				}
			});

			// Instantiating label for main title
			lblTitleOutline = new Label("ARCADE BREAKOUT");
			lblTitleOutline.setFont(titleFont);
			lblTitleOutline.setPrefSize(750, 100);
			lblTitleOutline.setTextFill(Color.RED);
			lblTitleOutline.setAlignment(Pos.CENTER);
			lblTitleOutline.setLayoutX(primaryStage.getWidth()/2 - lblTitleOutline.getPrefWidth()/2 - 3);
			lblTitleOutline.setLayoutY(77);

			lblTitle = new Label("ARCADE BREAKOUT");
			lblTitle.setFont(titleFont);
			lblTitle.setPrefSize(750, 100);
			lblTitle.setTextFill(Color.WHITE);
			lblTitle.setAlignment(Pos.CENTER);
			lblTitle.setLayoutX(primaryStage.getWidth()/2 - lblTitle.getPrefWidth()/2);
			lblTitle.setLayoutY(80);

			// Instantiating labels for top of screen
			lblPlayer = new Label("PLAYER 1");
			lblPlayer.setFont(textFont);
			lblPlayer.setTextFill(Color.YELLOW);
			lblPlayer.setLayoutX(10);
			lblPlayer.setLayoutY(10);

			lblHighscore = new Label("TOP SCORE:");
			lblHighscore.setFont(textFont);
			lblHighscore.setTextFill(Color.RED);
			lblHighscore.setLayoutX(primaryStage.getWidth()/2 - 100);
			lblHighscore.setLayoutY(10);

			lblHighscoreNum = new Label("10000");
			lblHighscoreNum.setFont(textFont);
			lblHighscoreNum.setTextFill(Color.WHITE);
			lblHighscoreNum.setLayoutX(primaryStage.getWidth()/2 - 100);
			lblHighscoreNum.setLayoutY(40);

			// Adding labels to a pane
			pane = new Pane();
			pane.getChildren().addAll(lblTitleOutline, lblTitle, lblPlayer, lblHighscore, lblHighscoreNum);

			// Instantiating buttons and adding them to an vBox
			btnStart = new Button("START");
			btnStart.setFont(buttonFont);
			btnStart.setStyle("-fx-background-color: black");
			btnStart.setTextFill(Color.CORNFLOWERBLUE);
			btnStart.setAlignment(Pos.CENTER_LEFT);
			btnStart.setOnAction(e ->  clicked(btnStart.getText(), primaryStage));

			btnSettings = new Button("SETTINGS");
			btnSettings.setFont(buttonFont);
			btnSettings.setStyle("-fx-background-color: black");
			btnSettings.setTextFill(Color.CORNFLOWERBLUE);
			btnSettings.setAlignment(Pos.CENTER_LEFT);
			btnSettings.setOnAction(e ->  clicked(btnSettings.getText(), primaryStage));

			vbox = new VBox(10);
			vbox.getChildren().addAll(btnStart, btnSettings);
			vbox.setAlignment(Pos.CENTER_LEFT);
			vbox.setPadding(new Insets(0, 0, 20, 50));

			// Instantiating imageview of arcade logo
			ImageView iv = new ImageView(new Image("file:background/logoAracde.png"));
			iv.setPreserveRatio(true);
			iv.setFitHeight(445);

			// Instantiating label for bottom of screen
			lblCredits = new Label("\t\t@ 2024 RKF.INC\t\tLICENSED BY ME");
			lblCredits.setFont(textFont);
			lblCredits.setTextFill(Color.PURPLE);
			lblCredits.setAlignment(Pos.CENTER);
			lblCredits.setLayoutX(300);
			lblCredits.setPadding(new Insets(0, 0, 20, 0));

			// Adding arcade image and vbox to gridpane
			grid = new GridPane();
			grid.setAlignment(Pos.CENTER);
			grid.add(vbox, 1, 0);
			grid.add(iv, 0, 0);
			grid.setVgap(40);

			// Creating + Displaying title screen on borderpane
			border = new BorderPane();
			border.setTop(pane);
			border.setCenter(grid);
			border.setBottom(lblCredits);

			// Adding borderpane to root
			root.getChildren().add(border);

			// Screen for settings
			btnBack = new Button("<- BACK");
			btnBack.setFont(settingsFont);
			btnBack.setTextFill(Color.CORNFLOWERBLUE);
			btnBack.setStyle("-fx-background-color: black");
			btnBack.setAlignment(Pos.CENTER);
			btnBack.setOnAction(e ->  clicked(btnBack.getText(), primaryStage));
			btnBack.setLayoutX(5);
			btnBack.setLayoutY(5);

			// Checkboxes for muting music and sound fx
			chkSoundEffects = new CheckBox("MUTE SOUND EFFECTS");
			chkSoundEffects.setFont(settingsFont);
			chkSoundEffects.setTextFill(Color.CORNFLOWERBLUE);
			chkSoundEffects.setLayoutX(20);
			chkSoundEffects.setLayoutY(60);
			chkSoundEffects.setOnAction(e -> checkboxSettings(chkSoundEffects, chkSoundEffects.getText()));

			chkBackgroundMusic = new CheckBox("MUTE BACKGROUND MUSIC");
			chkBackgroundMusic.setFont(settingsFont);
			chkBackgroundMusic.setTextFill(Color.CORNFLOWERBLUE);
			chkBackgroundMusic.setLayoutX(20);
			chkBackgroundMusic.setLayoutY(90);
			chkBackgroundMusic.setOnAction(e -> checkboxSettings(chkBackgroundMusic, chkBackgroundMusic.getText()));

			// Adding nodes to settting pane
			settingsPane = new Pane();
			settingsPane.getChildren().addAll(btnBack, chkSoundEffects, chkBackgroundMusic);

			// Keyframe for title screen button blinking
			KeyFrame kfTitleScreen = new KeyFrame(Duration.millis(800), new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {

					// Setting button colors to blue
					if (changed == true) {
						changed = false;
						btnSettings.setTextFill(Color.CORNFLOWERBLUE);
						btnStart.setTextFill(Color.CORNFLOWERBLUE);
					}

					// Setting button colors to black
					else {
						changed = true;
						btnSettings.setTextFill(Color.BLACK);
						btnStart.setTextFill(Color.BLACK);
					}
				}
			});

			titleScreenTimer = new Timeline(kfTitleScreen);
			titleScreenTimer.setCycleCount(Timeline.INDEFINITE);

			titleScreenTimer.play();

			// Story Screens

			// Buttons for next, back and skip
			btnStoryBack = new Button("<- BACK");
			btnStoryBack.setFont(settingsFont);
			btnStoryBack.setTextFill(Color.WHITE);
			btnStoryBack.setStyle("-fx-background-color: black");
			btnStoryBack.setAlignment(Pos.CENTER);
			btnStoryBack.setOnAction(e ->  clicked(btnStoryBack.getText(), primaryStage));
			btnStoryBack.setLayoutY(240);
			btnStoryBack.setLayoutX(10);

			btnNext = new Button("NEXT ->");
			btnNext.setFont(settingsFont);
			btnNext.setTextFill(Color.WHITE);
			btnNext.setStyle("-fx-background-color: black");
			btnNext.setAlignment(Pos.CENTER);
			btnNext.setOnAction(e ->  clicked(btnNext.getText(), primaryStage));
			btnNext.setLayoutX(595);
			btnNext.setLayoutY(240);

			btnSkip = new Button("SKIP");
			btnSkip.setFont(settingsFont);
			btnSkip.setTextFill(Color.WHITE);
			btnSkip.setStyle("-fx-background-color: black");
			btnSkip.setAlignment(Pos.CENTER);
			btnSkip.setOnAction(e ->  clicked(btnSkip.getText(), primaryStage));
			btnSkip.setLayoutX(primaryStage.getWidth() - btnSkip.getWidth());
			btnSkip.setLayoutX(320);
			btnSkip.setLayoutY(240);

			// Label for story and textfield
			lblStory = new Label("");
			lblStory.setFont(storyFont);
			lblStory.setTextFill(Color.WHITE);
			lblStory.setLayoutX(15);
			lblStory.setLayoutY(10);

			txtInput = new TextField();
			txtInput.selectAll();
			txtInput.setLayoutX(15);
			txtInput.setLayoutY(160);
			txtInput.setPrefWidth(200);
			txtInput.setFont(settingsFont);
			txtInput.setOnAction(e -> getTextInput());

			// Image for story
			Image img = new Image("file:player/idleLeft.gif");
			ImageView ivKirby = new ImageView(img);
			ivKirby.setPreserveRatio(true);
			ivKirby.setFitWidth(150);
			ivKirby.setLayoutX(520);
			ivKirby.setLayoutY(55);

			storyPane = new Pane();
			storyPane.getChildren().addAll(lblStory, btnStoryBack, btnNext, btnSkip);

			// Keyframe for keyboard story effect
			KeyFrame kfStory = new KeyFrame(Duration.millis(50), new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {

					// Checking if story page >= 4, indicating that they either skipped or are done going thru the story
					if (storyPage >= 4) {
						storyTimer.stop();
						// Starting lvl animation timer, making level = 0 and adding player + arcade to background
						root.getChildren().remove(storyPane);
						goToArcade(primaryStage);
					}

					// Otherwise, show the story
					else {

						//adding a letter to string based on timer to give a typing effect
						if(stringCounter < storyStrings[storyPage].length()) { 
							str += storyStrings[storyPage].charAt(stringCounter);
							stringCounter++;

							// Checking if on second story page and adding name if the word displayed is welcome
							if (storyPage == 1) {
								if (stringCounter == 8) {
									str += name;
								}
							}
						}

						// If text is done and still on first story page, add textfield to pane
						else if (storyPage == 0) {
							try {
								storyPane.getChildren().add(txtInput);
							}
							catch (Exception ex) {}
						}

						// If text is done and still on second story page, add kirby image to pane
						else if (storyPage == 1) {
							try {
								storyPane.getChildren().add(ivKirby);
							}
							catch (Exception ex) {}
						}

						// If text is anything but second story page remove kirby 
						if (storyPage != 1) {
							try {
								storyPane.getChildren().remove(ivKirby);
							}
							catch (Exception ex) {}
						}

						// Updating label
						lblStory.setText(str);

						// Stopping sound effect if letters are done typing
						if (stringCounter == storyStrings[storyPage].length()) {
							clips[1].stop();
						}
					}
				}
			});

			storyTimer = new Timeline(kfStory);
			storyTimer.setCycleCount(Timeline.INDEFINITE);


		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	// Methods

	// Method to get text input
	private void getTextInput() {

		alert = new Alert(AlertType.ERROR);
		alert.setHeaderText(null);

		// Showing alert if textfield is empty
		if (txtInput.getText().equals("")) {
			alert.setContentText("ERROR: Textfield is empty.");
			alert.setTitle("ERROR");
			alert.showAndWait();
		}
		// Assigning name to textfield
		else {
			name = txtInput.getText();
			finalScores[0] = name;
		}

	}

	// Method for buttons being clicked
	public void clicked(String str, Stage primaryStage) {

		// Playing button sound effect if muteFX is false
		if (muteFX == false)
			clips[0].play();

		// Button for starting game
		if (str.equalsIgnoreCase("START")) {
			primaryStage.setWidth(700);
			primaryStage.setHeight(300);
			primaryStage.centerOnScreen();
			root.getChildren().remove(border);
			titleScreenTimer.stop();
			root.getChildren().add(storyPane);
			storyTimer.play();

			// Playing key typing sound effect if muteFX is false
			if (muteFX == false)
				clips[1].play();
		}

		// Button for going to settings 
		if (str.equalsIgnoreCase("SETTINGS")) {
			primaryStage.setWidth(300);
			primaryStage.setHeight(170);
			primaryStage.centerOnScreen();
			root.getChildren().remove(border);
			root.getChildren().add(settingsPane);
		}
		// Button for going back to title (from settings) and story screen
		if (str.equalsIgnoreCase("<- BACK")) {

			// If they press back on first story screen go back to title screen
			if (storyPage == 0) {
				primaryStage.setWidth(900);
				primaryStage.setHeight(700);
				primaryStage.centerOnScreen();
				root.getChildren().add(border);
				root.getChildren().removeAll(settingsPane, storyPane);
			}
			// Otherwise go back one story page
			else if (str.equalsIgnoreCase("<- BACK")) {
				if (storyPage > 0) {
					storyPage--;

					// Getting name if they go back to second story page
					if (storyPage == 1) {
						this.str = storyStrings[storyPage].substring(0, 8) + name + storyStrings[storyPage].substring(8);
					}
					// Otherwise just get the string
					else 
						this.str = storyStrings[storyPage];

					stringCounter = storyStrings[storyPage].length();
				}
			}

		}

		// Checking the "next" button in story screen
		if (str.equalsIgnoreCase("NEXT ->")) {

			// If they press next in the middle of keyboard typing then change text to whole string
			if (stringCounter < storyStrings[storyPage].length()) {
				// If on the second story page then add the name aswell
				if (storyPage == 1) {
					this.str = storyStrings[storyPage].substring(0, 8) + name + storyStrings[storyPage].substring(8);
				}
				// Otherwise just add the string
				else 
					this.str = storyStrings[storyPage];

				stringCounter = storyStrings[storyPage].length();
			}
			// Otherwise skip to next story string
			else {
				// Making sure that the textfield isn't null if they press next
				if (name != null) {
					storyPane.getChildren().remove(txtInput);
					txtInput.setEditable(false);
					storyPage++;
					stringCounter = 0;
					this.str = "";
				}
			}

			// Playing key typing sound if muteFX is false and not on last story page
			if (muteFX == false && storyPage < 4)
				clips[1].play();
			// Otherwise stop music
			else 
				mPlayer.stop();

		}

		// Checking the skip button in story screen
		if (str.equalsIgnoreCase("SKIP")) {
			// Skipping to arcade and stopping sound effects nad music
			storyPage = 4;
			name = "UNKNOWN";
			finalScores[0] = name;
			clips[1].stop();
			mPlayer.stop();
		}

	}

	// Method for checkboxes
	public void checkboxSettings(CheckBox chk, String str) {

		// Checking if the checkbox is to mute sound effects
		if (str.equalsIgnoreCase("MUTE SOUND EFFECTS")) {
			// If it's selected mute sound effects
			if (chk.isSelected()) {
				muteFX = true;
			}
			else {
				muteFX = false;
			}
		}

		// Checking if the checkbox is to mute background music
		if (str.equalsIgnoreCase("MUTE BACKGROUND MUSIC")) {
			// If it's selected mute background music and stop bg music
			if (chk.isSelected()) {
				muteMusic = true;
				mPlayer.pause();
			}
			else {
				muteMusic = false;
				mPlayer.play();
			}
		}

	}

	// Method to run information alert 
	private void runInformationAlert(String str) {

		// Stopping all timers and moving player away from intersected object
		stopTimers();

		lvl.setY(lvl.getY() - 75);
		lvl.up = false;
		lvl.down = false; 
		lvl.left = false;
		lvl.right = false;

		Platform.runLater(new Runnable() {
			public void run() {

				// Creating alert with contentText of passed in string
				alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setContentText(str);
				alert.showAndWait();
				startTimers();

				// If the game is done then write name and final scores to text file
				if (gameDone) {

					try {
						bw.write(finalScores[0]);
						bw.newLine();

						bw.write("Bomb Detector: " + finalScores[1] + " seconds\n");
						bw.write("Kirby: In Space: " + finalScores[2] + " points\n");
						bw.write("Kirby's Kingdom: " + finalScores[3] + " points\n");

						bw.close();
					}
					catch (IOException e) {}

					System.exit(0);
				}

			}

		});

	}

	// Method to run confirmation alert
	private void runConfirmationAlert(Stage primaryStage) {

		// Stop all timers
		stopTimers();

		Platform.runLater(new Runnable() {
			public void run() {

				// Checking if they are in the arcade
				if (level == 0) {
					alert = new Alert(AlertType.CONFIRMATION);
					alert.setHeaderText(null);

					// Checking which game node player intersected with and displaying appropriate method depending on the game

					// Method for kirbyBall
					if (playKirbyBall) {
						alert.setContentText("Secret game FOUND!\nReady to play: Kirby Ball?\nWill reward 1 (one) token, if you have less than 5 tokens.");
					}

					// Method for bomb detector
					else if (travelLevelOne) 
						alert.setContentText("Ready to play: Bomb Detector?\nRewards 5 (three) tokens, costs 1 (one) token.");

					// Method for Kirby: in Space
					else if (travelLevelTwo)
						alert.setContentText("Ready to play: Kirby: In Space?\nRewards 7 (seven) tokens, costs 5 (five) tokens.");

					// Method for Kirby's Kingdom
					else if (travelLevelThree) 
						alert.setContentText("Ready to play: Kirby's Kingdom?\nRewards 10 (ten) tokens, costs 7 (seven) tokens.");


					// Clearing original buttons and adding custom buttons
					alert.getButtonTypes().clear();
					alert.getButtonTypes().addAll(btnObjective, btnControls, btnExit, btnEnter);

					Optional<ButtonType> result = alert.showAndWait();

					// If they press enter then put them into level (depending on what node they intersected with)
					if (result.get() == btnEnter) {

						// Traveling to kirbyBall
						if (playKirbyBall) {
							level = -1;
							lvl.removeFromScreen();
							root.getChildren().removeAll(ivCoin, lbl);
							lvl = new KirbyBall(tokens);
							primaryStage.setWidth(1000);
							primaryStage.setHeight(600);
							primaryStage.centerOnScreen();
						}

						// Traveling to bomb detector
						if (travelLevelOne) {
							level = 1;
							tokens -= 1;
							lvl.removeFromScreen();
							root.getChildren().removeAll(ivCoin, lbl);
							goToMinesweeper(primaryStage);

						}

						// Traveling to Kirby: In Space
						if (travelLevelTwo) {
							level = 2;
							tokens -= 5;
							lvl.removeFromScreen();
							root.getChildren().removeAll(ivCoin, lbl);
							lvl = new GalaxyShooter(1000, 625);
							primaryStage.setWidth(1000);
							primaryStage.setHeight(625);
							primaryStage.centerOnScreen();

						}

						// Travelling to Kirby's Kingdom
						if (travelLevelThree) {
							level = 3;
							tokens -= 7;
							lvl.removeFromScreen();
							root.getChildren().removeAll(ivCoin, lbl);
							lvl = new Platformer();
							primaryStage.setWidth(1400);
							primaryStage.setHeight(787);
							primaryStage.centerOnScreen();
						}

						// Starting timers
						timer.start();

					}

					// If they press exit button then move them away from intersected node and turn all travel variables false
					else if (result.get() == btnExit){
						startTimers();
						level = 0;
						lvl.setY(lvl.getY() - 75);
						lvl.up = false;
						lvl.down = false; 
						lvl.left = false;
						lvl.right = false;

						travelLevelOne = false;
						travelLevelTwo = false;
						travelLevelThree = false;
						playKirbyBall = false;

					}

					// If they press objective button go to storyAlerts method
					else if (result.get() == btnObjective) {
						storyAlerts(primaryStage);
					}

					// If they press controls button go to controlAlerts method
					else if (result.get() == btnControls) {
						controlAlerts(primaryStage);
					}
				}
			}
		});
	}

	// Alert to tell story for each game
	private void storyAlerts(Stage primaryStage) {

		ImageView iv;
		Alert alert;

		alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);

		// Story alerts for kirbyBall
		if (playKirbyBall) {
			iv = new ImageView(new Image("file:ping/ballRight.png"));
			iv.setPreserveRatio(true);
			iv.setFitHeight(50);
			alert.setGraphic(iv);

			alert.setContentText("Kirby's ready to play ball!");
			alert.showAndWait();

			alert.setContentText("All you have to do is make sure he doesn't leave the screen and is gone forever.");
			alert.showAndWait();

			alert.setContentText("Simple, right?\nGood luck!");
			alert.showAndWait();
		}

		// Story alerts for bomb detector
		else if (travelLevelOne) {
			iv = new ImageView(new Image("file:minesweeper/kirby.gif"));
			iv.setPreserveRatio(true);
			iv.setFitHeight(50);
			alert.setGraphic(iv);

			alert.setContentText("Kirby's tough; he can't be hurt by bombs. (Ofcourse not, he's Kirby!)\nBut... Kirby needs his rest!");
			alert.showAndWait();

			alert.setContentText("So, its your job to make sure he can sleep.");
			alert.showAndWait();

			alert.setContentText("Make sure he gets his rest by finding all the bombs while Kirby sleeps!");
			alert.showAndWait();


		}

		// Story alerts for Kirby: In Space
		else if (travelLevelTwo) {
			iv = new ImageView(new Image("file:shooter/player/flyRight.gif"));
			iv.setPreserveRatio(true);
			iv.setFitHeight(50);
			alert.setGraphic(iv);

			alert.setContentText("Kirby jumped on a star he found and now he's in space!");
			alert.showAndWait();

			alert.setContentText("Make sure he's able to explore space and stay safe.");
			alert.showAndWait();

			alert.setContentText("Do this by dodging unknown aliens and sharing Kirby's star power with them!\nGood luck!");
			alert.showAndWait();
		}

		// Story alert for Kirby's Kingdom
		else if (travelLevelThree) {
			iv = new ImageView(new Image("file:platformer/player/imgRight.gif"));
			iv.setPreserveRatio(true);
			iv.setFitHeight(50);
			alert.setGraphic(iv);

			alert.setContentText("Kirby's ready to venture for his kingdom!");
			alert.showAndWait();

			alert.setContentText("He must make it to Mario's flag before the bullet bills catch him.");
			alert.showAndWait();

			alert.setContentText("Help Kirby reach the flag defeating koopas, goombas and breaking blocks along the way!");
			alert.showAndWait();

		}

		// Going back to main game alert
		runConfirmationAlert(primaryStage);

	}

	// Alert to tell controls for each game
	private void controlAlerts(Stage primaryStage) {

		ImageView iv;
		Alert alert;

		alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);

		// Controls alerts for kirby ball
		if (playKirbyBall) {
			iv = new ImageView(new Image("file:ping/ballRight.png"));
			iv.setPreserveRatio(true);
			iv.setFitHeight(75);
			alert.setGraphic(iv);

			alert.setContentText("Once you have entered the level, you'll see the kirbyBall in the middle and two walls on either side.");
			alert.showAndWait();

			alert.setContentText("Use your 'W' key to move the red wall up.\nUse your 'S' key to move the red wall down");
			alert.showAndWait();

			alert.setContentText("Hit the kirbyball to make it bounce to the other side.");
			alert.showAndWait();

			alert.setContentText("Outlast the purple wall to win!");
			alert.showAndWait();

		}

		// Controls alerts for bomb detector
		else if (travelLevelOne) {

			iv = new ImageView(new Image("file:minesweeper/cell.png"));
			iv.setPreserveRatio(true);
			iv.setFitHeight(75);
			alert.setGraphic(iv);

			alert.setContentText("There are three levels in bomb detector.");
			alert.showAndWait();

			alert.setContentText("Once you have entered the level, you will see a grid of cells.\n(the cell look like this!)");
			alert.showAndWait();

			iv.setImage(new Image("file:minesweeper/cursor.jpg"));
			iv.setFitHeight(60);
			alert.setContentText("Use your mouse to click on a cell in the grid.");
			alert.showAndWait();

			iv.setImage(new Image("file:alerts/numbers/flag.png"));
			iv.setFitHeight(60);
			alert.setContentText("Clicking once will change the cell to a flag.\n(This is useful to mark where you think there's a bomb)");
			alert.showAndWait();

			iv.setImage(new Image("file:minesweeper/bomb.png"));
			iv.setFitHeight(60);
			alert.setContentText("Clicking a second time will reveal what the cell is.\nIf you clicked on a bomb, the cell will show a bomb and you will have lost the game!");
			alert.showAndWait();

			iv.setImage(new Image("file:minesweeper/numbers/num1.png"));
			iv.setFitHeight(60);
			alert.setContentText("Otherwise, you'll see a number indicating how many bombs are around that cell.\nIf blank, there are no bombs around that cell.");
			alert.showAndWait();

			iv.setImage(new Image("file:minesweeper/kirby.gif"));
			iv.setFitHeight(60);
			alert.setContentText("The level will be over once all non-bomb cells have been clicked.\nGood luck! Make sure Kirby gets his sleep!");
			alert.showAndWait();

			iv.setImage(new Image("file:minesweeper/kirby.gif"));
			iv.setFitHeight(60);
			alert.setContentText("If you lose a level, you will be brought back to the same grid if you pay another token.");
			alert.showAndWait();

		}

		// Controls alerts for Kirby: in Space
		else if (travelLevelTwo) {
			iv = new ImageView(new Image("file:shooter/player/flyRight.gif"));
			iv.setPreserveRatio(true);
			iv.setFitHeight(75);
			alert.setGraphic(iv);

			alert.setContentText("Once you have entered the level, you will see Kirby riding a star in space.");
			alert.showAndWait();

			alert.setContentText("Use your 'D' key to move Kirby right.\n Use your 'A' key to move Kirby left.");
			alert.showAndWait();

			iv.setImage(new Image("file:shooter/player/star.png"));
			iv.setFitHeight(60);
			alert.setContentText("Press the 'J' key to shoot out a star.\nThe star will destroy enemy UFOs.");
			alert.showAndWait();

			iv.setImage(new Image("file:shooter/enemies/ufo.png"));
			iv.setFitHeight(60);
			alert.setContentText("The level will be won once Kirby defeats the boss UFO.");
			alert.showAndWait();
		}

		// Controls alert for Kirby's Kingdom
		else if (travelLevelThree) {

			iv = new ImageView(new Image("file:platformer/player/imgRight.gif"));
			iv.setPreserveRatio(true);
			iv.setFitHeight(75);
			alert.setGraphic(iv);

			alert.setContentText("Once you have entered the level, you will see Kirby with his sword.");
			alert.showAndWait();

			alert.setContentText("Use your 'D' key to move Kirby right.\n Use your 'A' key to move Kirby left.");
			alert.showAndWait();

			iv.setImage(new Image("file:platformer/player/atkRight.gif"));
			iv.setFitHeight(60);
			alert.setContentText("Press the 'J' key to swing Kirby's sword!\nThe sword can be used to hit koopa troopas, goombas and destroy blocks.");
			alert.showAndWait();

			iv.setImage(new Image("file:alerts/spacebar.jpg"));
			iv.setFitHeight(60);
			alert.setContentText("Press the spacebar to make Kirby jump.");
			alert.showAndWait();

			iv.setImage(new Image("file:platformer/platforms/flag.png"));
			iv.setFitHeight(60);
			alert.setContentText("The level will be won once Kirby reaches the flagpole.");
			alert.showAndWait();

		}

		// Going back to main alert
		runConfirmationAlert(primaryStage);

	}

	// Method to go to minesweeper level depending on which level player is on
	private void goToMinesweeper(Stage primaryStage) {

		// If on level one go to 4x4 grid with 4 bombs
		if (minesweeperlvl == 1) {
			primaryStage.setWidth(350);
			primaryStage.setHeight(350);
			lvl = new MineSweeper(primaryStage, 4, 4, minesweeperlvl, minesweeperSeconds);
			lvl.timer.start();
		}

		// If on level two go to 6x6 grid with 7 bombs
		if (minesweeperlvl == 2) {
			primaryStage.setWidth(450);
			primaryStage.setHeight(450);
			lvl = new MineSweeper(primaryStage, 6, 7, minesweeperlvl, minesweeperSeconds);
		}

		// If on level three go to 7x7 grid with 10 bombs
		if (minesweeperlvl == 3) {
			primaryStage.setWidth(550);
			primaryStage.setHeight(550);
			lvl = new MineSweeper(primaryStage, 7, 10, minesweeperlvl, minesweeperSeconds);
		}

		// Centering screen on moniter and setting background color to gray, starting timers
		primaryStage.centerOnScreen();
		root.setStyle("-fx-background-color: gray");
		timer.start();

	}

	// Method to go back to arcade
	private void goToArcade(Stage primaryStage) {

		// Adding nodes to screen and setting leve to 0
		lvl = new ArcadeMap();
		lvl.addToScreen(gameOverShown);
		level = 0;
		root.getChildren().addAll(ivCoin, lbl);

		// Changing size and background, starting timers
		primaryStage.setWidth(800);
		primaryStage.setHeight(600);
		primaryStage.centerOnScreen();
		root.setStyle("-fx-background-color: black");
		startTimers();
	}

	// Method to stop all timers
	private void stopTimers() {
		lvl.timer.stop();
		timer.stop();
	}

	// Method to start all timers
	private void startTimers() {
		lvl.timer.start();
		timer.start();
	}

}
