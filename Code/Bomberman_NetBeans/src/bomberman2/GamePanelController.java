/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman2;

import bomberman2.entities.GameMap;
import bomberman2.graphics.Graphic;
import bomberman2.sound.Sound;
import bomberman2.sound.Soundtrack;
import bomberman2.util.Timer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
/**
 *
 * @author Liscli
 */
public class GamePanelController implements Initializable {
    @FXML
    private AnchorPane play,start,property,pause,black,
            pane,blank1,blank2,blank3,blank31;
    @FXML
    private Label message,enemy,life,time,mapN;
    @FXML
    private ImageView pauseBtn,sound,music,sound1,music1;
    @FXML
    private Button themes;
    @FXML
    private void clickWithMusic(MouseEvent e){
        if(GamePanelController.isSound) Sound.playSound("src/bomberman2/sound/click.wav");
    }
    @FXML
    private void handleAction(MouseEvent e){
        if(e.getTarget() == pauseBtn){
            pause.setVisible(true);
            pause.toFront();
            timer.stop();
        }if(e.getTarget() == sound || e.getTarget() == sound1){
           if(map.isIsSound()){
                map.setIsSound(false);
                isSound =false;
                try{                
                    sound.setImage(new Image(new FileInputStream("src/bomberman2/images/mute.png")));
                    sound1.setImage(new Image(new FileInputStream("src/bomberman2/images/mute.png")));
                }catch(FileNotFoundException ex){}
           }else{
                map.setIsSound(true);
                isSound = true;
                try{
                    sound.setImage(new Image(new FileInputStream("src/bomberman2/images/speaker.png")));
                    sound1.setImage(new Image(new FileInputStream("src/bomberman2/images/speaker.png")));
                }catch(FileNotFoundException ex){}
           }
        }if(e.getTarget() == music || e.getTarget() == music1){
            if(map.isIsMusic()){
                map.setIsMusic(false);
                Soundtrack.pause();
                isMusic = false;
                try{
                    music.setImage(new Image(new FileInputStream("src/bomberman2/images/noSound.png")));
                    music1.setImage(new Image(new FileInputStream("src/bomberman2/images/noSound.png")));
                }catch(FileNotFoundException ex){}
            }else{
                map.setIsMusic(true);
                Soundtrack.start();
                isMusic = true;
                try{
                    music.setImage(new Image(new FileInputStream("src/bomberman2/images/sound.png")));
                    music1.setImage(new Image(new FileInputStream("src/bomberman2/images/sound.png")));
                }catch(FileNotFoundException ex){}
            }
        }
    }
    @FXML
    private void changeThemeBtnAction(ActionEvent e){
        Graphic.changeTheme();
        themes.setText("Theme: " + Graphic.theme.toString());
    }
    @FXML
    private void playBtnAction(ActionEvent e){
        end = false;
        start.setVisible(false);
        play.setVisible(true);
        
        property.setVisible(true);
        play.toFront();
        
        pane.setStyle("-fx-background-radius: 10; -fx-background-color: #C92D44;");
        blank1.setStyle("-fx-background-color: #C92D44; -fx-background-radius: 10;");
        blank2.setStyle("-fx-background-color: #C92D44; -fx-background-radius: 10;");
        blank3.setStyle("-fx-background-color: #C92D44; -fx-background-radius: 10;");
        blank31.setStyle("-fx-background-color: #C92D44; -fx-background-radius: 10;");
        
        map.setLevel(3);
        map.setIsPvsP(false);
        map.reset();

        clock.start();
        timer.start();
    }
    @FXML
    private void playPvspAction(ActionEvent e){
        end = false;
        start.setVisible(false);
        play.setVisible(true);
                
        pane.setStyle("-fx-background-radius: 10; -fx-background-color: #DB7382;");
        blank1.setStyle("-fx-background-color: #DB7382; -fx-background-radius: 10;");
        blank2.setStyle("-fx-background-color: #DB7382; -fx-background-radius: 10;");
        blank3.setStyle("-fx-background-color: #DB7382; -fx-background-radius: 10;");
        blank31.setStyle("-fx-background-color: #DB7382; -fx-background-radius: 10;");

        property.setVisible(true);
        map.setIsPvsP(true);
        play.toFront();
        
        clock.pause();
        timer.start();
    }
    @FXML
    private void exitBtnAction(ActionEvent e){
        System.exit(0);
    }
    @FXML
    private void resumeBtnAction(ActionEvent e){
        pause.setVisible(false);
        pause.toBack();
        
        timer.start();
    }
    @FXML
    private void menuBtnAction(ActionEvent e){
        backToMenu();
    }
    
    private void backToMenu(){
        pause.setVisible(false);        
        play.setVisible(false);
        property.setVisible(false);
        
        pane.setStyle("-fx-background-radius: 10; -fx-background-color: #85CBF8;");
        blank1.setStyle("-fx-background-color: #85CBF8; -fx-background-radius: 10;");
        blank2.setStyle("-fx-background-color: #85CBF8; -fx-background-radius: 10;");
        blank3.setStyle("-fx-background-color: #85CBF8; -fx-background-radius: 10;");
        blank31.setStyle("-fx-background-color: #85CBF8; -fx-background-radius: 10;");
        
        start.setVisible(true);

        clock.reTime();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Soundtrack.playSound("src/bomberman2/sound/soundtrack.wav");
        map = new GameMap(play.getPrefWidth(),play.getPrefHeight());
        clock = new Timer(3,0);
        play.getChildren().add(map);
        themes.setText("Theme: "+Graphic.theme.toString());
        timer = new AnimationTimer() {
            private long lastUpdate = 0;
            private long lastTime = 0;
            private long timePause = 0;
            private boolean pause = false;
            @Override
            public void handle(long now) {
                if(isMusic && !Soundtrack.isRunning()){
                    Soundtrack.playSound("src/bomberman2/sound/soundtrack.wav");
                }
                if (now - lastUpdate >= 10_000_000) {
                    lastUpdate = now ;
                    if(clock.getTime() <= 0){
                        getReturn(3);
                        end = true;
                    }
                    if(!pause){
                        int choise = map.update();
                        if(choise != 0) {
                            end = getReturn(choise);
                            timePause = 200;
                            pause = true;
                        } 
                    }else{
                        if(timePause>0)
                        timePause-= 0.1;
                        else {
                            if(end) {
                                getReturn(0);
                                backToMenu();
                                this.stop();
                            }else{
                                getReturn(0);
                                pause = false;
                            }
                        }
                    }
                    life.setText(map.getPlayerLife().toString());
                    enemy.setText(map.getEnemy().toString());
                    mapN.setText(map.getLevel().toString());
                }
                if(now - lastTime >= 1_000_000_000){
                    lastTime = now;
                    clock.tick();
                    time.setText(clock.toString());
                }
            }
	};
    }
    private boolean getReturn(int ch){
        switch (ch){
            case 1: //next Level
                clock.reTime();
                black.setVisible(true);
                property.setVisible(false);
                black.toFront();
                message.setText("Level: " + map.getLevel());
                if(isSound)Sound.playSound("src/bomberman2/sound/nextLevel.wav");
                clock.pause();
                break;
            case 2: //death
                black.setVisible(true);
                property.setVisible(false);
                black.toFront();
                message.setText("You die!\nLife:" + map.getPlayerLife());
                map.setTranslateX(0);
                map.setTranslateY(0);
                clock.pause();
                break;
            case 3: // GameOver
                if(isSound)Sound.playSound("src/bomberman2/sound/gameOver.wav");
                clock.reTime();
                black.setVisible(true);
                black.toFront();
                message.setText("Game Over!");
                return true;
            case 10: 
                black.setVisible(true);
                property.setVisible(false);
                black.toFront();
                message.setText("Draw!\nBest Freind!");
                return true;
            case 11:
                black.setVisible(true);
                if(isSound)Sound.playSound("src/bomberman2/sound/win.wav");
                property.setVisible(false);
                black.toFront();
                message.setText("Player 1 win!");
                return true;
            case 12:
                black.setVisible(true);
                if(isSound)Sound.playSound("src/bomberman2/sound/win.wav");
                property.setVisible(false);
                black.toFront();
                message.setText("Player 2 win!");
                return true;
            case 0:
                if(black.isVisible()) {
                    property.setVisible(true);
                    clock.start();
                    black.setVisible(false);
                    black.toBack();
                    map.playerRiseUp();
                }
            default:
                break;
        }
        return false;
    }
    private AnimationTimer timer;
    private GameMap map;
    private Timer clock;
    private boolean end = false;
    private boolean isMusic = true;
    public static boolean isSound = true;
}
