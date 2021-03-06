// PainterController.java controller file for Painter
// Cody Rauber 11/9/20

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class PainterController {
	// enum representing pen sizes

	private enum PenSize {
		SMALL(2),
		MEDIUM(4),
		LARGE(6),
		EXTRALARGE(8);

		private final int radius;

		PenSize(int radius) {this.radius = radius;} // constructor

		public int getRadius() {return radius;}
	};

    @FXML
    private RadioButton blackRadioButton;

    @FXML
    private ToggleGroup colorToggleGroup;

    @FXML
    private RadioButton redRadioButton;

    @FXML
    private RadioButton greenRadioButton;

    @FXML
    private RadioButton blueRadioButton;

    @FXML
    private RadioButton yellowRadioButton;

    @FXML
    private RadioButton orangeRadioButton;

    @FXML
    private RadioButton smallRadioButton;

    @FXML
    private ToggleGroup sizeToggleGroup;

    @FXML
    private RadioButton mediumRadioButton;

    @FXML
    private RadioButton largeRadioButton;

    @FXML
    private RadioButton extralargeRadioButton;

    @FXML
    private Button undoButton;

    @FXML
    private Button clearButton;

    @FXML
    private Pane drawingAreaPane;

    // instance variables for managing Painter state
    private PenSize radius = PenSize.MEDIUM; // radius of circle
    private Paint brushColor = Color.BLACK; // drawing color

    // set user data for the RadioButtons
    public void initialize() {
    	// user data on a control can be any Object
    	blackRadioButton.setUserData(Color.BLACK);
    	redRadioButton.setUserData(Color.RED);
    	greenRadioButton.setUserData(Color.GREEN);
    	blueRadioButton.setUserData(Color.BLUE);
    	yellowRadioButton.setUserData(Color.YELLOW);
    	orangeRadioButton.setUserData(Color.ORANGE);
    	smallRadioButton.setUserData(PenSize.SMALL);
    	mediumRadioButton.setUserData(PenSize.MEDIUM);
    	largeRadioButton.setUserData(PenSize.LARGE);
    	extralargeRadioButton.setUserData(PenSize.EXTRALARGE);
    }

    // handles Clear Buttons ActionEvents
    @FXML
    private void clearButtonPressed(ActionEvent event) {
    	drawingAreaPane.getChildren().clear(); // clear the canvas

    }

    // handles color RadioButtons ActionEvents
    @FXML
    private void colorRadioButtonSelected(ActionEvent e) {
    	// user data for each color RadioButton is the corresponding Color
    	brushColor =
    			(Color) colorToggleGroup.getSelectedToggle().getUserData();
    }

    // handles drawingAreas onMouseDragged MouseEvent
    @FXML
    private void drawingAreaMouseDragged(MouseEvent e) {
    	Circle newCircle = new Circle(e.getX(), e.getY(),
    			radius.getRadius(), brushColor);
    	drawingAreaPane.getChildren().add(newCircle);
    }

    // handles size RadioButtons ActionEvents
    @FXML
    private void sizeRadioButtonSelected(ActionEvent e) {
    	// user data for each size RadioButton is the corresponding PenSize
    	radius =
    			(PenSize) sizeToggleGroup.getSelectedToggle().getUserData();
    }

    // handles Undo Buttons ActionEvents
    @FXML
    private void undoButtonPressed(ActionEvent event) {
    	int count = drawingAreaPane.getChildren().size();

    	// if there are any shapes remove the last one added
    	if (count > 0) {
    		drawingAreaPane.getChildren().remove(count - 1);
    	}

    }

}
