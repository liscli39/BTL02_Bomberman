/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman2.entities;

import bomberman2.graphics.Theme;
import javafx.scene.image.ImageView;

/**
 *
 * @author Liscli
 */
public abstract class Entity extends ImageView{
    public abstract void setEntityImage(Theme theme);
    public abstract int getEnityX();
    public abstract int getEnityY();
}
