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
import javafx.stage.Stage;

public class SceneController implements Initializable{
	private AnimationTimer timer;
	private GameMap map;
    public int infullX;
    public int infullY;
	@FXML
	private AnchorPane scene,start,over,play,blank1,blank2,pause;
	@FXML
	private Button playBtn,again,exit,full,exitButton,pvsp;
	@FXML
	private ImageView set;
	@FXML
	private void quitBtnAction(ActionEvent event) {
		System.exit(0);
	}
	@FXML
	private void againBtnAction(ActionEvent event) {
		map.playAgain();
		over.setVisible(false);
		timer.start();
	}
	@FXML
	private void pvspBtnAction(ActionEvent event) {
		start.setVisible(false);
		blank1.toFront();
		blank2.toFront();
		set.setVisible(true);
		
		map.pvsp();
		map.setLayoutX(0);
		map.setLayoutY(0);
		map.setTranslateX(0);
		map.setTranslateY(0);
		
		play.toFront();
		play.getChildren().add(map);
		map.setFocusTraversable(true);
		
		timer.start();
	}
	@FXML
	private void exitBtnAction(ActionEvent event) {
		over.setVisible(false);
		pause.setVisible(false);
		start.setVisible(true);
		start.toFront();
		play.getChildren().remove(map);
	}
	@FXML
	private void setBtnAction(MouseEvent event) {
		if(event.getTarget() == set) {
			pause.setVisible(true);
			timer.stop();
		}
	}
	@FXML
	private void continueBtnAction(ActionEvent event) {
		pause.setVisible(false);
		timer.start();;
	}
	@FXML
	private void fullScreenBtnAction(ActionEvent event) {
		System.out.println("full");
		Stage stage = (Stage) full.getScene().getWindow();
		if(!stage.isMaximized()) {
			stage.setMaximized(true);
			
			full.setText("Un FullScreen");
			
			scene.setPrefWidth((stage.getHeight()-20) *15/13);
			scene.setPrefHeight((stage.getHeight()-20));
			play.setPrefWidth((stage.getHeight()-20) *15/13);
			play.setPrefHeight((stage.getHeight()-20));
			start.setPrefWidth((stage.getHeight()-20) *15/13);
			start.setPrefHeight((stage.getHeight()-20));
			
			double nScale = (40*start.getPrefHeight()) / (infullY-20);
		
			double w = stage.getWidth() - 10 - (int)nScale*15;
			blank1.setPrefWidth(w);
			blank2.setPrefWidth(w);
			map.changeSize(nScale);
			
		}else{
			
			System.out.println("unfull");
			stage.setMaximized(false);
			
			scene.setPrefWidth(infullX-20);
			scene.setPrefHeight(infullY-20);
			play.setPrefWidth(infullX-20);
			play.setPrefHeight(infullY-20);
			start.setPrefWidth(infullX-20);
			start.setPrefHeight(infullY-20);

			map.setPrefWidth(infullX-20);
			map.setPrefHeight(infullY-20);
			
			map.changeSize(40);
		}
	}
	@FXML
	private void platBtnAction(ActionEvent event) {
		start.setVisible(false);
		blank1.toFront();
		blank2.toFront();
		set.setVisible(true);
		
		map.playAgain();
		map.setLayoutX(0);
		map.setLayoutY(0);
		map.setTranslateX(0);
		map.setTranslateY(0);
		
		play.toFront();
		play.getChildren().add(map);
		map.setFocusTraversable(true);
		
		timer.start();
	}
	
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	this.infullX = 620;
    	this.infullY = 540;
		timer = new AnimationTimer() {
			private long lastUpdate = 0;
			@Override
			public void handle(long now) {
                if (now - lastUpdate >= 10_000_000) {
                    lastUpdate = now ;
                    int n = map.update();
					if(n == 1) {
						over.setVisible(true);
						over.toFront();
						this.stop();
					}else if(n == 2) {
						map.nextLevel();
					}
                }
			}
		};	
		map = new GameMap(40,0);
    }
}
