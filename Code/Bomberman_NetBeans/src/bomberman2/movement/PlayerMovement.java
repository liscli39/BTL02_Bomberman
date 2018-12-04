/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman2.movement;

import bomberman2.entities.mobs.Player;
import bomberman2.graphics.Graphic;
import bomberman2.graphics.InputGraphics;
import java.util.List;

/**
 *
 * @author Liscli
 */
public class PlayerMovement extends AI{
//    public static int destroyBrick(Player p){
//        
//        if(p.getTranslateX()==p.getEnityX()*Graphic.entitiesW && 
//           p.getTranslateY()==p.getEnityY()*Graphic.entitiesH){
//            State brick = getNearestBrick(new State(p.getEnityX(),p.getEnityY()));
//            List<Integer> actions = AStarSearch(p,brick);
//            System.out.println(actions.size());
//          /*  if(actions.size()==1){
//                Graphic.cmap[brick.getX()][brick.getY()] = ' ';
//                return 1;
//            }else */if(actions.isEmpty()){
//                moveByStep(p,getRandNextDirection(p));
//            }else{
//                moveByStep(p,actions.get(0));
//            }
//        }else {
//            moveByStep(p,p.getCurDirect());
//        }
//        return 0;
//    }
//    private static State getNearestBrick(State p){
//        int x = 0;
//        int y = 0;
//        double min = Math.sqrt(InputGraphics.getWeight()*InputGraphics.getWeight() + InputGraphics.getHeight()*InputGraphics.getHeight());
//        for(int i =0;i<InputGraphics.getWeight();i++)
//            for(int j=0;j<InputGraphics.getHeight();j++){
//                if(Graphic.cmap[i][j]=='*'){
//                    double d = Math.sqrt((p.getX()-i)*(p.getX()-i)+(p.getY()-j)*(p.getY()-j));
//                    if(d<min){
//                        min = d;
//                        x = i;
//                        y = j;
//                    }
//                }
//            }
//        
//        return new State(x,y);
//    }
    
}
