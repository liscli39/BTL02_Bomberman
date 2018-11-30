/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman2.movement;

import bomberman2.entities.mobs.Mob;
import bomberman2.entities.mobs.Player;
import bomberman2.entities.mobs.enemys.Balloom;
import bomberman2.entities.mobs.enemys.Oneal;
import bomberman2.graphics.Graphic;

/**
 *
 * @author Liscli
 */
public class Coordinates {
    public static void MapRecalculation(Mob m){
        int x = Coordinates.xInMap(m.getTranslateX());
        int y = Coordinates.yInMap(m.getTranslateY());
        
        int x_ = m.getEnityX();
        int y_ = m.getEnityY();
        
        if(x!=x_ || y!=y_){
//            System.out.println(Graphic.cmap[x_][y_]);
            if(Graphic.cmap[x][y] !='#' &&  Graphic.cmap[x][y] !='*'&& Graphic.cmap[x][y]!='o'){
                if(m instanceof Player)Graphic.cmap[x][y] = 'p';
                else if (m instanceof Oneal)Graphic.cmap[x][y] = '2';
                else if (m instanceof Balloom)Graphic.cmap[x][y] = '1';
            }
            if(Graphic.cmap[x_][y_] !='#' &&  Graphic.cmap[x_][y_] !='*'&& Graphic.cmap[x_][y_]!='o') Graphic.cmap[x_][y_] = ' ';
            m.setEnityX(x);
            m.setEnityY(y);
        }
    }
    public static int xInMap(double translateX){
        if((int)translateX%Graphic.entitiesW < Graphic.entitiesW/2) return (int)(translateX/Graphic.entitiesW);
        else  return (int)(translateX/Graphic.entitiesW) + 1;
    }
    public static int yInMap(double translateY){
        if((int)translateY%Graphic.entitiesW < Graphic.entitiesW/2) return (int)(translateY/Graphic.entitiesW);
	else  return (int)(translateY/Graphic.entitiesW) + 1;
    }
}
