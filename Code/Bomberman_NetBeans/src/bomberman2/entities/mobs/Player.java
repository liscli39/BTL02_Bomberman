/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman2.entities.mobs;

import bomberman2.entities.bombs.Bomb;
import bomberman2.graphics.Graphic;
import bomberman2.graphics.Theme;
import bomberman2.movement.Coordinates;
import bomberman2.movement.PlayerMovement;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;

/**
 *
 * @author Liscli
 */
public class Player extends Mob{
    public Player(int x, int y){
        this.id = currentId++;
        this.setLayoutX(0);
        this.setLayoutY(0);
        this.x = x;
        this.y = y;
        this.startX = x;
        this.startY = y;
        this.speed = 1;
        this.numBomb = 2;
        this.bombLength = 2;
        this.numLife = 3;
        this.undeadTime = 30;
        this.death = false;
        this.fakeDeath = false;
    }
    public int getPlayerId() {
        return id;
    }
    public void riseUp(){
        if(this.numLife>0){
            this.undeadTime = 20;
            this.setTranslateX(this.startX*Graphic.entitiesW);
            this.setTranslateY(this.startY*Graphic.entitiesH);
            this.fakeDeath = true;
            this.numLife --;
        }else {
            
        }
    }
    public void undead(){
        if(this.undeadTime>0){
            ColorAdjust ca = new ColorAdjust();	
            this.setEffect(ca);
                    if(undeadTime % 10 > 5)
                            ca.setBrightness(0.8);
                    else ca.setBrightness(0);
            this.undeadTime -= 1;
        }
    }
    public void moveRight(boolean canMove){
        if(canMove){
            this.setTranslateX(this.getTranslateX()+this.speed);
            try{
                if(this.getTranslateX()%Graphic.entitiesW/2 < Graphic.entitiesW/4)
                    this.setImage(new Image(new FileInputStream("src/bomberman2/images/" + theme + "/player"+this.id +"/1.png")));
                else this.setImage(new Image(new FileInputStream("src/bomberman2/images/" + theme + "/player"+this.id +"/2.png")));
            }catch(FileNotFoundException e){}
        }
        Coordinates.MapRecalculation(this);
    }
    public void moveLeft(boolean canMove){
        if(canMove){
            this.setTranslateX(this.getTranslateX()-this.speed);
            try{
                if(this.getTranslateX()%Graphic.entitiesW/2< Graphic.entitiesW/4)
                    this.setImage(new Image(new FileInputStream("src/bomberman2/images/" + theme + "/player"+this.id +"/7.png")));
                else this.setImage(new Image(new FileInputStream("src/bomberman2/images/" + theme + "/player"+this.id +"/8.png")));
            }catch(FileNotFoundException e){}
        }
        Coordinates.MapRecalculation(this);
    }
    public void moveUp(boolean canMove){
        if(canMove){
            this.setTranslateY(this.getTranslateY()-this.speed);
            try{
                if(this.getTranslateY()%Graphic.entitiesH/2 < Graphic.entitiesH/4)
                    this.setImage(new Image(new FileInputStream("src/bomberman2/images/" + theme + "/player"+this.id +"/3.png")));
                else this.setImage(new Image(new FileInputStream("src/bomberman2/images/" + theme + "/player"+this.id +"/4.png")));
            }catch(FileNotFoundException e){}
        }
        Coordinates.MapRecalculation(this);
    }
    public void moveDown(boolean canMove){
        if(canMove){
            this.setTranslateY(this.getTranslateY()+this.speed);
            try{
                if(this.getTranslateY()%Graphic.entitiesH/2 < Graphic.entitiesH/4)
                    this.setImage(new Image(new FileInputStream("src/bomberman2/images/" + theme + "/player"+this.id +"/5.png")));
                else this.setImage(new Image(new FileInputStream("src/bomberman2/images/" + theme + "/player"+this.id +"/6.png")));
            }catch(FileNotFoundException e){}
        }
        Coordinates.MapRecalculation(this);
    }
    public Bomb putBomb() {
        if(numBomb > 0) {
            this.numBomb--;
            int x_ = Coordinates.xInMap(this.getTranslateX());
            int y_ = Coordinates.yInMap(this.getTranslateY());
            
            Graphic.cmap[x_][y_] = 'o';
            return new Bomb(x_,y_,this.id,this.bombLength);
	}else return null;
    }
    public void bombUp(){
        this.numBomb++;
    }
    public void powerUp(){
        this.bombLength++;
    }
    public void speedUp(){
        this.speed+=0.3;
    }
    public int getNumLife(){
        return this.numLife;
    }
    
//    public int autoPlay(){
//        return PlayerMovement.destroyBrick(this);
//    }
//    
    @Override
    public void setEntityImage(Theme theme){
        this.theme = theme.toString();
        try {
            this.setImage(new Image(new FileInputStream("src/bomberman2/images/" + theme.toString() + "/player"+this.id +"/1.png")));
 	} catch (FileNotFoundException e) {
            System.out.print(e);
        }
    }
    @Override
    public boolean hasBomb(){
        return this.numBomb>0;
    }
    @Override
    public int getEnityX() {
        return x;
    }
    @Override
    public int getEnityY() {
        return y;
    }
    @Override
    public void setEnityX(int x){
        this.x = x;
    }
    @Override
    public void setEnityY(int y){
        this.y = y;
    }   
    @Override
    public double getSpeed(){
        return this.speed;
    }
    @Override
    public int getCurDirect(){return this.curDirect;}
    @Override
    public void setCurDirect(int direct){
        this.curDirect = direct;
    }

    public boolean isDeath() {
        return death;
    }

    public void setDeath(boolean death) {
        this.death = death;
    }

    public int getUndeadTime() {
        return undeadTime;
    }

    public boolean isFakeDeath() {
        return fakeDeath;
    }

    public void setFakeDeath(boolean fakeDeath) {
        this.fakeDeath = fakeDeath;
    }

    public void setNumLife(int numLife) {
        this.numLife = numLife;
    }
    
    
    private String theme;
    public static int currentId = 1;
    private final int id;
    private int bombLength;
    private int numLife;
    private final int startX;
    private final int startY;
    private int undeadTime;
    private boolean death;
    private boolean fakeDeath;
}
