/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman2.movement;

import bomberman2.entities.Entity;
import bomberman2.entities.barriers.StaticEntity;
import bomberman2.entities.items.Item;
import bomberman2.entities.mobs.Mob;
import bomberman2.graphics.Graphic;
import java.util.List;

/**
 *
 * @author Liscli
 */
public class Collision {
    public static boolean collisionDetection(double x1, double y1,double x2, double y2){
        double realx1 = x1 + 0.5*Graphic.entitiesW;
        double realy1 = y1 + 0.5*Graphic.entitiesH;
        
        double realx2 = x2 + 0.5*Graphic.entitiesW;
        double realy2 = y2 + 0.5*Graphic.entitiesH;
        
        return Math.abs(realx1-realx2) < 0.9*Graphic.entitiesW
                && Math.abs(realy1-realy2) < 0.9*Graphic.entitiesH;
    }
    
    public static boolean canMove(Mob m,int direction, List<StaticEntity> ste){
        for(StaticEntity s : ste){
            double x = m.getTranslateX();
            double y = m.getTranslateY();
            double x_ = s.getTranslateX();
            double y_ = s.getTranslateY();
            switch(direction){
                case 1: //up
                    y -= m.getSpeed();
                    if(y_<y && collisionDetection(x,y,x_,y_)){
                        if(Math.abs(x-x_) < Graphic.entitiesW && x < x_ && Math.abs(x-x_)>0.5*Graphic.entitiesW) m.setTranslateX(m.getTranslateX()-1);
                        if(Math.abs(x-x_) < Graphic.entitiesW && x > x_ && Math.abs(x-x_)>0.5*Graphic.entitiesW) m.setTranslateX(m.getTranslateX()+1);
                        return false;
                    }
                    break;
                case 2: //down
                    y += m.getSpeed();
                    if(y_>y && collisionDetection(x,y,x_,y_)){
                        if(Math.abs(x-x_) < Graphic.entitiesW && x < x_ && Math.abs(x-x_)>0.5*Graphic.entitiesW) m.setTranslateX(m.getTranslateX()-1);
                        if(Math.abs(x-x_) < Graphic.entitiesW && x > x_ && Math.abs(x-x_)>0.5*Graphic.entitiesW) m.setTranslateX(m.getTranslateX()+1);
                        return false;
                    }
                    break;
                case 3: //right
                    x += m.getSpeed();
                    if(x<x_ && collisionDetection(x,y,x_,y_)){
                        if(Math.abs(y-y_) < Graphic.entitiesH && y < y_ && Math.abs(y-y_)>0.5*Graphic.entitiesH) m.setTranslateY(m.getTranslateY()-1);
                        if(Math.abs(y-y_) < Graphic.entitiesH && y > y_ && Math.abs(y-y_)>0.5*Graphic.entitiesH) m.setTranslateY(m.getTranslateY()+1);
                        return false;
                    }
                    break;
                case 4: //left
                    x -= m.getSpeed();
                    if(x>x_ && collisionDetection(x,y,x_,y_)){
                        if(Math.abs(y-y_) < Graphic.entitiesH && y < y_ && Math.abs(y-y_)>0.5*Graphic.entitiesH) m.setTranslateY(m.getTranslateY()-1);
                        if(Math.abs(y-y_) < Graphic.entitiesH && y > y_ && Math.abs(y-y_)>0.5*Graphic.entitiesH) m.setTranslateY(m.getTranslateY()+1);
                        return false;
                    }
                    break;
                default: break;
            }
        }
        
        return true;
    }   
    
    public static boolean hitBomb(Mob m,int direction){
        int x = m.getEnityX();
        int y = m.getEnityY();
        switch(direction){
            case 1: //up
                if(Graphic.cmap[x][y-1] == 'o')
                    return (m.getTranslateY() <= y*Graphic.entitiesH);
                break;
            case 2: //down
               if(Graphic.cmap[x][y+1] == 'o')
                    return (m.getTranslateY() >= y*Graphic.entitiesH);
                break;
            case 3: //right
               if(Graphic.cmap[x+1][y] == 'o')
                    return (m.getTranslateX() >= x*Graphic.entitiesW);
                break;
            case 4: //left
               if(Graphic.cmap[x-1][y] == 'o')
                    return (m.getTranslateX() <= x*Graphic.entitiesW);
                break;
            default: break;
        }
        return false;
    }
    
    public static boolean isCollision(Entity e1,Entity e2){
        return Collision.collisionDetection(e1.getTranslateX(), e1.getTranslateY(), e2.getTranslateX(), e2.getTranslateY());
    }
    
    public static boolean canEatItem(Mob m, Item i){
        return collisionDetection(m.getTranslateX(),m.getTranslateY(),i.getTranslateX(),i.getTranslateY());
    }
    
    public static boolean canPutBom(Mob m){
        return (Graphic.cmap[m.getEnityX()][m.getEnityY()]!='o' && m.hasBomb());
    }
}
