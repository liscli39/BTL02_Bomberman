/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman2.entities.bombs;

import bomberman2.entities.Entity;
import bomberman2.graphics.Graphic;
import bomberman2.graphics.Theme;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;

/**
 *
 * @author Liscli
 */
public final class Flame extends Entity{
    public Flame(int x, int y){
        this.setLayoutX(0);
        this.setLayoutY(0);
        this.x = x;
        this.y = y;
        this.setTranslateX(this.x*Graphic.entitiesW);
        this.setTranslateY(this.y*Graphic.entitiesH);
        this.setFitHeight(Graphic.entitiesH);
        this.setFitWidth(Graphic.entitiesW);
        this.setEntityImage(Graphic.theme);
        this.burnTime = 1;
    }
    public boolean burning(){
        if(this.burnTime>0){
            this.burnTime -= 0.1;
            return true;
        }else return false;
    }
    @Override
    public void setEntityImage(Theme theme){
        try {
            this.setImage(new Image(new FileInputStream("src/bomberman2/images/" + theme.toString() + "/flame.png")));
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
    private final int x;
    private final int y;
    private double burnTime;
}
