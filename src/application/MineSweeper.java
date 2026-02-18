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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MineSweeper extends ArcadeMap {

	// Fields
	private Image img, imgSweeper, imgBomb, imgFlag, imgNum [];
	private ImageView iv;
	private ImageView [][] grid;
	private boolean [][] bombs;
	private boolean [][] clicked;
	private int [][] clickCount;
	private GridPane pane;
	private Random rnd;
	private int counter, seconds;
	private Timeline endgameTimer, secondsTimer;
	private Alert alert;

	// Constructors
	public MineSweeper(Stage primaryStage, int size, int bombCount, int level, int prevSeconds) {

		// Initializing images and imageviews
		img = new Image("file:minesweeper/kirby.gif");
		iv = new ImageView(img);
		iv.setPreserveRatio(true);
		iv.setFitHeight(80);
		iv.setLayoutX(primaryStage.getWidth() - 110);

		imgSweeper = new Image("file:minesweeper/cell.png");
		imgBomb = new Image("file:minesweeper/bomb.png");
		imgFlag = new Image("file:minesweeper/numbers/flag.png");

		// Initializing 1D and 2D arrays
		bombs = new boolean[size][size];
		clicked = new boolean[size][size];
		grid = new ImageView [size][size];
		clickCount = new int[size][size];
		imgNum = new Image[9];

		// Initializing arraylists
		scoresText = new ArrayList<String>();
		scoresNum = new ArrayList<Integer>();

		// Initializing gridpane, random and other fields
		pane = new GridPane();
		rnd = new Random();
		counter = 0;
		seconds = prevSeconds;
		gameWon = false;

		// Initializing image array for numbers
		for (int i = 0; i < imgNum.length; i++) {
			imgNum[i] = new Image("file:minesweeper/numbers/num" + i + ".png");
		}

		// Assigning each index a cell image
		for (int rows = 0; rows < grid.length; rows++) {
			for (int columns = 0; columns < grid.length; columns++) {
				grid[rows][columns] = new ImageView(imgSweeper);
				grid[rows][columns].setPreserveRatio(true);
				grid[rows][columns].setFitWidth(50);
				pane.add(grid[rows][columns], columns, rows);
			}
		}

		// Randomizing location of the mines
		for (int i = 0; i < bombCount; i++) {

			// Generating random row and column
			int rndRow = rnd.nextInt(size);
			int rndColumn = rnd.nextInt(size);

			// Making sure a bomb doesn't already exist in that row, column and rerunning loop if it does
			if (bombs[rndRow][rndColumn] == false) {
				bombs[rndRow][rndColumn] = true;
				
				// Printing bomb locations
				System.out.println (rndRow + " " + rndColumn);
			}
			else {
				i--;
			}
		}

		System.out.println();

		// Adding image and gridpane to root
		root.getChildren().add(iv);
		root.getChildren().add(pane);

		// Centering gridpane on pane
		pane.setLayoutX(primaryStage.getWidth()/2 - size*50/2);
		pane.setLayoutY(primaryStage.getHeight()/2 - size*50/2);

		// Initializing label and adding to pane
		lbl = new Label("TIME: " + this.seconds);
		lbl.setTextFill(Color.WHITE);
		lbl.setFont(Font.loadFont("file:fonts/Retro Gaming.ttf", 20));
		lbl.setTextFill(Color.WHITE);
		lbl.setPrefSize(400,  50);
		lbl.setAlignment(Pos.CENTER_LEFT);
		root.getChildren().add(lbl);
		lbl.setLayoutX(15);
		lbl.setLayoutY(15);

		// Initializing file, buffered reader/writer, file reader/writer
		try {
			file = new File("textFiles/bombDetectorScores.txt");
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
				insertionSort(scoresText, scoresNum);

			}
		}
		catch (Exception e) {
			e.getMessage();
		}


		// Animation timer to check whether player presses on cell
		timer = new AnimationTimer() { 
			public void handle(long val) {

				// Loop that runs for number of rows and columns
				for (int rows = 0; rows < grid.length; rows++) {
					for (int columns = 0; columns < grid.length; columns++) {

						// Intializing final row/column variable to access in setOnMouseClicked
						int row = rows;
						int column = columns;

						// Mouseclicked method for each cell
						grid[rows][columns].setOnMouseClicked(new EventHandler<MouseEvent>() {
							public void handle (MouseEvent e) {

								// Increasing clicked counter
								clickCount[row][column]++;
								
								// Playing clicked sound effect if muteFX is false
								if (muteFX == false)
								clips[0].play();

								// If the cell is being clicked for the first time change it to a flag
								if (clickCount[row][column] == 1) {
									grid[row][column].setImage(imgFlag);
									grid[row][column].setFitWidth(50);
								}

								// If the cell is being clicked for the second time reveal it
								if (clickCount[row][column] == 2) {
									// If the cell has a bomb in it reveal the bomb image and end game
									if (bombs[row][column] == true) {
										grid[row][column].setImage(imgBomb);
										grid[row][column].setFitWidth(50);
										
										// Playing bomb effect if muteFX is false
										if (muteFX == false)
										clips[9].play();

										for (int rows = 0; rows < grid.length; rows++) {
											for (int columns = 0; columns < grid.length; columns++) {
												// Reveal all bombs
												if (bombs[rows][columns] == true) {
													grid[rows][columns].setImage(imgBomb);
													grid[rows][columns].setFitWidth(50);
												}
											}
										}

										gameWon = false;
										endgameTimer.play();
									}

									// Otherwise count how many bombs are around it
									else if (clicked [row][column] == false){

										// Increasing counter (on how many cells have been clicked)
										clicked[row][column] = true;
										counter++;

										// Initializing local count variable
										int count = 0;

										// Checking the three cells above clicked cell if its a bomb
										if (row - 1 >= 0) {

											if (column - 1 >= 0) {
												if (bombs[row-1][column -1] == true) {
													count++;
												}
											}

											if (bombs[row-1][column] == true) {
												count++;
											}
											if (column + 1 < size) {
												if (bombs[row-1][column + 1] == true) {
													count++;
												}
											}									

										}

										// Checking the cells beside clicked cell if its a bomb
										if (row + 1 < size) {

											if (column - 1 >= 0) {
												if (bombs[row+1][column -1] == true) {
													count++;
												}
											}

											if (bombs[row+1][column] == true) {
												count++;
											}

											if (column + 1 < size) {
												if (bombs[row+1][column + 1] == true) {
													count++;
												}
											}
										}

										// Checking the three cells below clicked cell if its a comb
										if (column - 1 >= 0) {
											if (bombs[row][column - 1] == true) {
												count++;
											}
										}

										if (column + 1 < size) {
											if (bombs[row][column + 1] == true) {
												count++;
											}
										}

										// Setting image of clicked cell to the number of bombs around it
										grid[row][column].setImage(imgNum[count]);
										grid[row][column].setFitWidth(50);
										grid[row][column].setFitHeight(50);
										
										if (count >= 1 && muteFX == false) {
											clips[count].play();
										}

										// Checking if they've clicked all non-bomb cells and ending game if so
										if (counter == size*size - bombCount) {
											gameWon = true;
											endgameTimer.play();
										}

									}
								}
							}
						});

					}
				}

			}

		};


		// Keyframe for ending game
		KeyFrame kfgameEnd = new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {

				// If the game is won passing in kirby image
				if (gameWon) {
					endGame(iv, level);
				}

				// If the game is lost passing in the bomb image
				if (gameWon == false)
					endGame(new ImageView(imgBomb), level);
			}
		});

		endgameTimer = new Timeline(kfgameEnd);

		// Keyframe for counting seconds
		KeyFrame kfSeconds = new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {

				// Increasing seconds counter and updating label
				seconds++;
				lbl.setText("TIME: " + seconds);

			}
		});

		secondsTimer = new Timeline(kfSeconds);
		secondsTimer.setCycleCount(Timeline.INDEFINITE);

		// If on the first level read the previous high scores for the level
		if (level == 1) {
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
					alert.setContentText("Lowest time: " + scoresNum.get(0) + "\nHeld by: " + scoresText.get(0) + "\nWould you like to display all scores?");

					Optional<ButtonType> result = alert.showAndWait();

					alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText(null);

					// If they click yes display all scores
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

					// Starting animation timer
					timer.start();
					secondsTimer.play();

				}

			});
		}
		else {
			// Starting animation timer
			timer.start();
			secondsTimer.play();
		}

		// Initializing sound effects
		clips = new AudioClip [10];
		for (int i = 0; i < clips.length; i++) {
			clips[i] = new AudioClip("file:minesweeper/sounds/effect" + i + ".wav");
		}

	}

	// Methods

	// Method to end game
	private void endGame(ImageView iv, int level) {

		// Stopping timer and changing size of passed in imageview
		timer.stop();
		secondsTimer.stop();
		iv.setPreserveRatio(true);
		iv.setFitWidth(50);

		// Running alert
		Platform.runLater(new Runnable() {
			public void run() {
				alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setGraphic(iv);

				// Changing text depending on if the game is won and which level it is
				if (gameWon) {

					if (level == 1) {
						alert.setContentText("YOU WIN! Kirby's falling asleep...\nTransferring to level two.");
					}
					if (level == 2) {
						alert.setContentText("YOU WIN! Kirby's fast asleep...\n Transferring to level three.");
					}
					if (level == 3) {
						alert.setContentText("YOU WIN! Kirby slept like a baby.");

						// Alert to show final score
						alert = new Alert(AlertType.INFORMATION);
						alert.setHeaderText(null);
						alert.setContentText("Your final score was: " + seconds);
						alert.setGraphic(iv);
						alert.showAndWait();

						// TextInputDialog to ask for player's name
						dialog = new TextInputDialog();
						dialog.setHeaderText(null);
						alert.setGraphic(new ImageView(imgFlag));
						dialog.setContentText("Input name you want saved with score: ");
						Optional<String> result = dialog.showAndWait();

						// Writing name and seconds onto bomb detector text file
						try {
							bw.newLine();
							
							if (result.isPresent()) {
								if (result.get() == null || result.get() == "") {
									bw.write("UNKNOWN, " + seconds);
								}
								else {
									bw.write(result.get() + ", " + seconds);
								} 
							}
							else
								bw.write("UNKNOWN, " + seconds);
						
							bw.close();
						}
						catch (IOException e) {
							System.out.println(e.getMessage());
						}

						// Adding score to arraylist and resorting it
						if (result.isPresent())
							scoresText.add(result.get());
						else
							scoresText.add("UNKNOWN");
						scoresNum.add(seconds);

						Collections.sort(scoresNum);
						
						// Searching for the score to find players place on leaderboard
						int place = searchArray(scoresNum, seconds);

						// Showing player their place on leaderboard
						alert.setContentText("You are " + (place + 1) + " place on the leaderboard!");
						alert.showAndWait();

						alert.setContentText("You earned 5 tokens!");


					}
				}

				// Changing text to loss text
				else {
					alert.setContentText("YOU LOSE! The bomb's woke Kirby up.");
				}

				// Ending game
				alert.showAndWait();
				gameDone = true;
				removeFromScreen();
			}

		});

	}

	// Method to remove all nodes from root

	public int getSeconds() {
		return seconds;
	}

	// Method to sort arraylist (insertionSort)
	public void insertionSort (ArrayList<String> string , ArrayList<Integer> num) {

		// Loop to go through arrays length
		for (int index = 1; index < num.size(); index++) {

			// Storing element
			int temp = num.get(index);
			String tempStr = string.get(index);

			int placeholder = index;

			while (placeholder > 0 && temp < num.get(placeholder - 1)) {
				num.set(placeholder, num.get(placeholder - 1));
				string.set(placeholder, string.get(placeholder - 1));

				placeholder--;
			}

			num.set(placeholder, temp);
			string.set(placeholder, tempStr);

		}

	}


}
