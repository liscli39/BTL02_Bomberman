package application;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javafx.scene.layout.AnchorPane;
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = new AnchorPane();/*(AnchorPane)FXMLLoader.load(getClass().getResource("Map.fxml"));*/
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		AnchorPane a = new AnchorPane();
		
	}
}
