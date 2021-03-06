/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman2.movement;

import bomberman2.entities.mobs.Mob;
import bomberman2.entities.mobs.Player;
import bomberman2.graphics.Graphic;

/**
 *
 * @author Liscli
 */
public class EnemyMovement extends AI{
    public static void followPlayer(Mob m, Player p){
        if(m.getTranslateX()==m.getEnityX()*Graphic.entitiesW && 
           m.getTranslateY()==m.getEnityY()*Graphic.entitiesH){
            int d = AStarSearch(m,new State(p.getEnityX(),p.getEnityY()));
            if(d != 0){
                moveByStep(m,d);
            }else{
                moveByStep(m,getRandNextDirection(m));
            }
        }else{
                moveByStep(m,m.getCurDirect());
        }
    }
}
