/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman2.entities.mobs.enemys;

import bomberman2.graphics.Theme;
import bomberman2.movement.Coordinates;
import bomberman2.movement.EnemyMovement;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;
import javafx.scene.image.Image;

/**
 *
 * @author Liscli
 */
public class Balloom extends Enemy{
    public Balloom(int x, int y){
        this.setLayoutX(0);
        this.setLayoutY(0);
        this.x = x;
        this.y = y;
        this.curDirect = new Random().nextInt(4)+1;
        this.speed = 1;
    }
    @Override
    public void dumpMove(){
        EnemyMovement.moveByStep(this,EnemyMovement.getRandNextDirection(this));
        Coordinates.MapRecalculation(this);
    }
    @Override
    public int getCurDirect(){
        return this.curDirect;
    }
    @Override
    public void setCurDirect(int direct){
        this.curDirect = direct;
    }
    @Override
    public void setEntityImage(Theme theme){
        try {
            this.setImage(new Image(new FileInputStream("src/bomberman2/images/" + theme.toString() + "/balloom.png")));
 	} catch (FileNotFoundException e) {
            System.out.print(e);
        }
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
    public boolean hasBomb(){return false;}
}
