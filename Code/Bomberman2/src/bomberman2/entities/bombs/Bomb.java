/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman2.entities.bombs;

import bomberman2.entities.barriers.StaticEntity;
import bomberman2.graphics.Graphic;
import bomberman2.graphics.Theme;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.image.Image;

/**
 *
 * @author Liscli
 */
public final class Bomb extends StaticEntity{
    public Bomb(int x, int y,int pId, int bombLength){
        this.setLayoutX(0);
        this.setLayoutY(0);
        this.x = x;
        this.y = y;
        this.setTranslateX(this.x*Graphic.entitiesW);
        this.setTranslateY(this.y*Graphic.entitiesH);
        this.setFitHeight(Graphic.entitiesH);
        this.setFitWidth(Graphic.entitiesW);
        this.pId = pId;
        this.setEntityImage(Graphic.theme);
        this.burn = new LinkedList<>();
        this.flameLength = bombLength;
        this.lifeTime = 2;
    }
    @Override
    public void setEntityImage(Theme theme){
        try {
            this.setImage(new Image(new FileInputStream("src/bomberman2/images/" + theme.toString() + "/bomb.png")));
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
    public int getpId() {
        return pId;
    }
    
    public boolean countDown(){
        if(this.lifeTime>0) {
            this.lifeTime-= 0.01;
            return true;
        }else return false; 
    }
    public List<Flame> explosive(){
        burn.add(new Flame(x,y));
        for(int i=0;i<this.flameLength;i++){
            if(Graphic.cmap[x][y-i-1]=='#') break;
            if(Graphic.cmap[x][y-i-1]!=' '){
                burn.add(new Flame(x,y-i-1));
                Graphic.cmap[x][y-i-1] =' ';
                break;
            }
            burn.add(new Flame(x,y-i-1));
        }
        for(int i=0;i<this.flameLength;i++){
            if(Graphic.cmap[x][y+i+1]=='#') break;
            if(Graphic.cmap[x][y+i+1]!=' '){
                burn.add(new Flame(x,y+i+1));
                Graphic.cmap[x][y+i+1] =' ';
                break;
            }
            burn.add(new Flame(x,y+i+1));
        }
        for(int i=0;i<this.flameLength;i++){
            if(Graphic.cmap[x-i-1][y]=='#') break;
            if(Graphic.cmap[x-i-1][y]!=' '){
                burn.add(new Flame(x-i-1,y));
                Graphic.cmap[x-i-1][y] =' ';
                break;
            }
            burn.add(new Flame(x-i-1,y));
        }
        for(int i=0;i<this.flameLength;i++){
            if(Graphic.cmap[x+i+1][y]=='#') break;
            if(Graphic.cmap[x+i+1][y]!=' '){
                burn.add(new Flame(x+i+1,y));
                Graphic.cmap[x+i+1][y] =' ';
                break;
            }
            burn.add(new Flame(x+i+1,y));
        }
        return burn;
    }
    
    private final int x;
    private final int y;
    private final int pId;
    private double lifeTime;
    private final int flameLength;
    private final List<Flame> burn;
}
