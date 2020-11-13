/* Cody Rauber JavaFX Project 11/7/20
 * TicTacToe
 * This program will display a tic tac toe board using JavaFX that
 * the user can use to play a game. Left click puts an X and right click
 * puts a O. Once 3 are in a row a line is drawn and the game is over.
 */

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TicTacToeApp extends Application{

	private boolean playable = true; // determines if the game is over or not
	private boolean turnX = true; // sets the first turn to X
	private Tile[][] board = new Tile[3][3]; // creates 3x3 tile board for the game
	private List<Combo> combos = new ArrayList<>();

	private Pane root = new Pane(); // creates the pane window

	private Parent createContent() {
		root.setPrefSize(600,  600); // sets window pane size

		for (int i = 0; i < 3; i++) { // for loop to create the 3x3 grid
			for (int j = 0; j < 3; j++) {
				Tile tile = new Tile();
				tile.setTranslateX(j * 200); // sets size
				tile.setTranslateY(i * 200);

				root.getChildren().add(tile);

				board[j][i] = tile; // sets the tile to create the grid
			}
		}

		// horizontal, checks for horizontal win con
		for (int y = 0; y < 3; y++){
			combos.add(new Combo(board[0][y], board[1][y], board[2][y]));
		}

		//vertical, checks for vertical win con
		for (int x = 0; x < 3; x++){
			combos.add(new Combo(board[x][0], board[x][1], board[x][2]));
		}

		// diagonals, checks for diagonal win con
		for (int y = 0; y < 3; y++){
			combos.add(new Combo(board[0][0], board[1][1], board[2][2]));
			combos.add(new Combo(board[2][0], board[1][1], board[0][2]));
				}

		return root;
	}

	@Override
	public void start(Stage primaryStage) throws Exception { // sets the stage to open the pane
		primaryStage.setScene(new Scene(createContent())); // scene is set for the stage
		primaryStage.show();
	}

	// check to see what state the game is in playable/not playable, and if it isnt play the animation
	private void checkState() {
		for (Combo combo : combos) {
			if (combo.isComplete()) {
				playable = false;
				playWinAnimation(combo);
				break;
			}
		}
	}

	// creates the line that draws when won
	private void playWinAnimation(Combo combo) { // sets the line over the tiles
		Line line = new Line();
		line.setStartX(combo.tiles[0].getCenterX());
		line.setStartY(combo.tiles[0].getCenterY());
		line.setEndX(combo.tiles[0].getCenterX());
		line.setEndY(combo.tiles[0].getCenterY());

		root.getChildren().add(line);

		Timeline timeline = new Timeline(); // create the animation for the line drawing
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1),
				new KeyValue(line.endXProperty(), combo.tiles[2].getCenterX()),
				new KeyValue(line.endYProperty(), combo.tiles[2].getCenterY())));
		timeline.play();
	}
	// Tests the different combinations of win conditions
	private class Combo {
		private Tile[] tiles;
		public Combo(Tile...tiles) { // passes in 3 tiles to check winner
			this.tiles = tiles;
		}
		public boolean isComplete() { // If the first tile is empty then go to next check
			if (tiles[0].getValue().isEmpty())
				return false;

			// check if the 3 tiles have the same value
			return tiles[0].getValue().equals(tiles[1].getValue())
					&& tiles[0].getValue().equals(tiles[2].getValue());
		}
	}

	private class Tile extends StackPane { // creates stackpane tile that goes on the pane that can have text on it with the 3x3 grid
		private Text text = new Text();

		public Tile() { // sets each section on the board with lines and blank fill
			Rectangle border = new Rectangle(200, 200);
			border.setFill(null);
			border.setStroke(Color.BLACK);

			text.setFont(Font.font(72)); // sets font size

			setAlignment(Pos.CENTER); // centers the letter
			getChildren().addAll(border, text);

			setOnMouseClicked(event -> { // left and right click mouse button to play
				if (!playable) // if it is not playable then return
					return;

				if (event.getButton() == MouseButton.PRIMARY) { // left click to play X
					if (!turnX) // if it is not X's turn then return otherwise draw an X and check if the game is over
						return;
					drawX();
					turnX = false;
					checkState();

				} else if (event.getButton() == MouseButton.SECONDARY) { // right click to play O
					if (turnX)
						return;
					drawO(); // draw O when right clicking and set it to X's turn and check if the game is over
					turnX = true;
					checkState();
				}
			});
		}

		public double getCenterX() { // X coordinate to center in the square
			return getTranslateX() + 100;
		}

		public double getCenterY() { // Y coordinate to center in the square
			return getTranslateY() + 100;
		}

		public String getValue() { // get the value of x or o
			return text.getText();
		}

		private void drawX() { // sets text to X for the left click
			text.setText("X");
		}
		private void drawO() {
			text.setText("O"); // sets text to O for the right click
		}

	public void main(String[] args) {
		launch(args);
		}
	} // end of tile class

} // end of tictactoeapp class
