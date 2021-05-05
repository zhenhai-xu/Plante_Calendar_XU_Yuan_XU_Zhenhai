package View;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("mainui.fxml"));
			primaryStage.setTitle("zhiwu");
		    primaryStage.setScene(new Scene(root, 1049, 750));
		    primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
       
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
