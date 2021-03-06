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
public class Brick extends StaticEntity{    
    public Brick(int x, int y){
        this.setLayoutX(0);
        this.setLayoutY(0);
        this.x = x;
        this.y = y;
    }
    @Override
    public void setEntityImage(Theme theme){
        try {
            if((x+y) % 2==1)
            this.setImage(new Image(new FileInputStream("src/bomberman2/images/" + theme.toString() + "/brick.png")));
            else this.setImage(new Image(new FileInputStream("src/bomberman2/images/" + theme.toString() + "/brick1.png")));
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
}
