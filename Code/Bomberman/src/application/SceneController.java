package application;

import java.net.URL;
import java.util.ResourceBundle;

import entity.GameMap;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class SceneController implements Initializable{
	AnimationTimer timer;
	GameMap map;
	@FXML
    private ImageView exitButton;
	@FXML
	private AnchorPane scene,start,pause;
	@FXML
	private Button playBtn,again,exit;
	@FXML
	private void onMouseClickedEvent(MouseEvent event) {
        if(event.getTarget() == exitButton){
            System.exit(0); 
        }
	}
	@FXML
	private void againBtnAction(ActionEvent event) {
		map.playAgain();
		pause.setVisible(false);
		timer.start();
	}
	@FXML
	private void exitBtnAction(ActionEvent event) {
		pause.setVisible(false);
		start.setVisible(true);
		scene.getChildren().remove(map);
	}
	@FXML
	private void platBtnAction(ActionEvent event) {
		start.setVisible(false);
		map.playAgain();
		map.setLayoutX(0);
		map.setLayoutY(0);
		map.setTranslateX(0);
		map.setTranslateY(0);
		scene.getChildren().add(map);
		map.setFocusTraversable(true);
		
		timer = new AnimationTimer() {
			private long lastUpdate = 0;
			@Override
			public void handle(long now) {
                if (now - lastUpdate >= 10_000_000) {
                    lastUpdate = now ;
					if(map.update() == 1) {
						pause.setVisible(true);
						pause.toFront();
						this.stop();
					}else if(map.update() == 2) {
						map.nextLevel();
					}
                }
			}
		};		
		timer.start();
	}
	
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
		map = new GameMap(30,0);
    }  
}
