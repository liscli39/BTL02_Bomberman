package application;
	
import entity.GameMap;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
	
public class Main extends Application {
    private double xOffset = 0;
    private double yOffset = 0;
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.initStyle(StageStyle.UNDECORATED);
			
			Parent root = FXMLLoader.load(getClass().getResource("scene.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			
	        root.setOnMousePressed(new EventHandler<MouseEvent>(){
	            @Override
	            public void handle(MouseEvent event){
	                xOffset = event.getSceneX();
	                yOffset = event.getSceneY();
	            }
	        });
	        root.setOnMouseDragged(new EventHandler<MouseEvent>(){
	            @Override
	            public void handle(MouseEvent event){
	            	primaryStage.setX(event.getScreenX() - xOffset);
	            	primaryStage.setY(event.getScreenY() - yOffset);            }
	        });
			root.setOnKeyPressed(new EventHandler<KeyEvent>(){
				@Override
				public void handle(KeyEvent event) {
					if(event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP) {
						GameMap.keyBoard.put(KeyCode.W, true);
					}
					if(event.getCode() == KeyCode.S|| event.getCode() == KeyCode.DOWN) {
						GameMap.keyBoard.put(KeyCode.S, true);
					}
					if(event.getCode() == KeyCode.D|| event.getCode() == KeyCode.RIGHT) {
						GameMap.keyBoard.put(KeyCode.D, true);
					}
					if(event.getCode() == KeyCode.A|| event.getCode() == KeyCode.LEFT) {
						GameMap.keyBoard.put(KeyCode.A, true);
					}
					if(event.getCode() == KeyCode.SPACE) {
						GameMap.keyBoard.put(KeyCode.SPACE, true);
					}
				}
			});
			root.setOnKeyReleased(new EventHandler<KeyEvent>(){
				@Override
				public void handle(KeyEvent event) {
					if(event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP) {
						GameMap.keyBoard.put(KeyCode.W, false);
					}
					if(event.getCode() == KeyCode.S|| event.getCode() == KeyCode.DOWN) {
						GameMap.keyBoard.put(KeyCode.S, false);
					}
					if(event.getCode() == KeyCode.D|| event.getCode() == KeyCode.RIGHT) {
						GameMap.keyBoard.put(KeyCode.D, false);
					}
					if(event.getCode() == KeyCode.A|| event.getCode() == KeyCode.LEFT) {
						GameMap.keyBoard.put(KeyCode.A, false);
					}
					if(event.getCode() == KeyCode.SPACE) {
						GameMap.keyBoard.put(KeyCode.SPACE, false);
					}
				}
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
