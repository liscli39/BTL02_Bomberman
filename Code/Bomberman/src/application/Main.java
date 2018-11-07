package application;
	
import entity.GameMap;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import management.GetInput;
	
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			GameMap root = new GameMap();/*(AnchorPane)FXMLLoader.load(getClass().getResource("GameMap.fxml"));*/
			Scene scene = new Scene(root,930,390);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			AnimationTimer timer = new AnimationTimer() {
				@Override
				public void handle(long now) {
					root.update();	
				}
			};		
			timer.start();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		GetInput get = new GetInput(1);
		get.printMap();
		System.out.println(get.getHeight());
		System.out.println(get.getWeight());
		launch(args);
	}
}
