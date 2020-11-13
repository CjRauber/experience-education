// Main application that laods and displays the Painter's GUI
// Cody Rauber 11/9/20

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Painter extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		Parent root =
				FXMLLoader.load(getClass().getResource("Painter.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Painter"); // displayed in windows title bar
		stage.setScene(scene);
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
