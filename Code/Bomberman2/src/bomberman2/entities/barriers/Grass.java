/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman2.entities.barriers;

import bomberman2.graphics.Theme;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;

/**
 *
 * @author Liscli
 */
public class Grass extends StaticEntity{
    public Grass(int x, int y){
        this.setLayoutX(0);
        this.setLayoutY(0);
        this.x = x;
        this.y = y;
        this.setStyle("-fx-background-radius: 10");
    }
    @Override
    public void setEntityImage(Theme theme){
        try {
            this.setImage(new Image(new FileInputStream("src/bomberman2/images/" + theme.toString() + "/grass.png")));
            
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
    private int x;
    private int y;
}
