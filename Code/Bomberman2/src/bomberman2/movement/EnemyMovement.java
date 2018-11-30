/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman2.movement;

import bomberman2.entities.mobs.Mob;
import bomberman2.entities.mobs.Player;
import bomberman2.graphics.Graphic;
import bomberman2.util.MyPriorityQueue;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Liscli
 */
public class EnemyMovement {
    
    public static int getRandNextDirection(Mob m){
        int x = m.getEnityX();
        int y = m.getEnityY();
        List<Integer> d = new LinkedList<>();
        int currentDirection = m.getCurDirect();
        switch(currentDirection){
            case -1:
                if(Graphic.cmap[x][y-1] == ' ') d.add(1);
                if(Graphic.cmap[x-1][y] == ' ') d.add(4);
                if(Graphic.cmap[x+1][y] == ' ') d.add(3);
                if(Graphic.cmap[x][y+1] == ' ') d.add(2);
                if(d.isEmpty()) return -1;
                return d.get(new Random().nextInt(d.size()));
            case 1:
                if(Graphic.cmap[x][y-1] == ' ')
                    return currentDirection;
                if(Graphic.cmap[x-1][y] == ' ') d.add(4);
                if(Graphic.cmap[x+1][y] == ' ') d.add(3);
                if(Graphic.cmap[x][y+1] == ' ') d.add(2);
                if(d.isEmpty()) return -1;
                return d.get(new Random().nextInt(d.size()));
            case 2:
                if(Graphic.cmap[x][y+1] == ' ')
                    return currentDirection;
                if(Graphic.cmap[x-1][y] == ' ') d.add(4);
                if(Graphic.cmap[x+1][y] == ' ') d.add(3);
                if(Graphic.cmap[x][y-1] == ' ') d.add(1);
                if(d.isEmpty()) return -1;
                return d.get(new Random().nextInt(d.size()));
            case 3: 
                if(Graphic.cmap[x+1][y] == ' ')
                    return currentDirection;
                if(Graphic.cmap[x][y-1] == ' ') d.add(1);
                if(Graphic.cmap[x][y+1] == ' ') d.add(2);
                if(Graphic.cmap[x-1][y] == ' ') d.add(4);
                if(d.isEmpty()) return -1;
                return d.get(new Random().nextInt(d.size()));
            case 4: 
                if(Graphic.cmap[x-1][y] == ' ')
                    return currentDirection;
                if(Graphic.cmap[x][y-1] == ' ') d.add(1);
                if(Graphic.cmap[x][y+1] == ' ') d.add(2);
                if(Graphic.cmap[x+1][y] == ' ') d.add(3);
                if(d.isEmpty()) return -1;
                return d.get(new Random().nextInt(d.size()));
            default: break;
        }
        
        return 0;
    }    
    public static void moveByStep(Mob m,int direct){
        if(m.getTranslateX()==m.getEnityX()*Graphic.entitiesW && 
           m.getTranslateY()==m.getEnityY()*Graphic.entitiesH){
            m.setCurDirect(direct);
        }
        
        switch(m.getCurDirect()){
            case 1:
                if( m.getTranslateY() > (m.getEnityY()-1)*Graphic.entitiesH){
                    m.setTranslateY(m.getTranslateY()-m.getSpeed());
                }else {
                    m.setEnityY(m.getEnityY()-1);
                }
                break;
            case 2: 
                if(m.getTranslateY() < (m.getEnityY()+1)*Graphic.entitiesH){
                    m.setTranslateY(m.getTranslateY()+m.getSpeed());
                }else {
                    m.setEnityY(m.getEnityY()+1);
                }
                break;
            case 3: 
                if(m.getTranslateX() < (m.getEnityX()+1)*Graphic.entitiesW){
                    m.setTranslateX(m.getTranslateX()+m.getSpeed());
                }else {
                    m.setEnityX(m.getEnityX()+1);
                }
                break;
            case 4: 
                if(m.getTranslateX() > (m.getEnityX()-1)*Graphic.entitiesW){
                    m.setTranslateX(m.getTranslateX()-m.getSpeed());
                }else {
                    m.setEnityX(m.getEnityX()-1);
                }
                break;
            default: break;
        }
    }
    
    public static List<Integer> getListDirection(int x, int y){
        List<Integer> d = new LinkedList<>();
        if(Graphic.cmap[x][y-1] == ' ' || Graphic.cmap[x][y-1] == '1' || Graphic.cmap[x][y-1] == '2'|| Graphic.cmap[x][y-1] == 'p')
            d.add(1);
        if(Graphic.cmap[x-1][y] == ' ' || Graphic.cmap[x-1][y] == '1' || Graphic.cmap[x-1][y] == '2'|| Graphic.cmap[x-1][y] == 'p')
            d.add(4);
        if(Graphic.cmap[x+1][y] == ' ' || Graphic.cmap[x+1][y] == '1' || Graphic.cmap[x+1][y] == '2'|| Graphic.cmap[x+1][y] == 'p')
            d.add(3);
        if(Graphic.cmap[x][y+1] == ' ' || Graphic.cmap[x][y+1] == '1' || Graphic.cmap[x][y+1] == '2'|| Graphic.cmap[x][y+1] == 'p') 
            d.add(2);
        return d;
    }   
    public static int getGoodDirection(Mob m, Player p){
        State start = new State(m.getEnityX(),m.getEnityY());
        State end = new State(p.getEnityX(),p.getEnityY());
//        System.out.println(start.getX() + " " +start.getY());
        MyPriorityQueue<Element> pq = new MyPriorityQueue<>();
        List<Integer> actions = new LinkedList<>();
        pq.push(new Element(start,actions,0), 0);
        List<State> visited = new LinkedList<>();
        
        while (!pq.isEmpty()){
            boolean isContinue = false;
            Element e = pq.pop();            
            //
            State state = e.getState();
            
            actions.clear();
            actions.addAll(e.getActions());
            
            double cost = e.getCost();
            //
            for(State s : visited){
                if(s.equals(state)) {
                    isContinue = true;
                    break;
                }
            }
            if(isContinue) continue;
            
            if(state.equals(end)){
                if(actions.isEmpty()) return 0;
                return actions.get(0);
            }           
            if(actions.size()>20){
                return 0;
            }
            visited.add(state);
            
            List<Integer> direct = getListDirection(state.getX(),state.getY());
            direct.forEach((d) -> {
                List<Integer> act = new LinkedList<>(); 
                act.addAll(actions); act.add(d);
                State _s = new State(state,d);
                pq.push(new Element(_s,act,cost+1),cost+ 1 + heuristic(_s,end));
            });
           
        }
        return 0;
    }
    public static void followPlayer(Mob m, Player p){
        int d = getGoodDirection(m,p);
        if(d != 0){
            moveByStep(m,d);
        }else {
            moveByStep(m,getRandNextDirection(m));
        }
    }
    private static double heuristic(State m, State end){
        int x = m.getX();
        int y = m.getY();
        if(Graphic.cmap[x][y-1]=='o') return 999;
        if(Graphic.cmap[x][y+1]=='o') return 999;
        if(Graphic.cmap[x+1][y]=='o') return 999;
        if(Graphic.cmap[x-1][y]=='o') return 999;
        int x1 = end.getX();
        int y1 = end.getY();
        return Math.sqrt((x-x1)*(x-x1)+(y-y1)*(y-y1));
    }
    
}
class Element extends Object{
    public Element(State s, List<Integer> actions,double cost){
        this.state = s;
        this.actions = actions;
        this.cost = cost;
    }
    public State getState() {
        return state;
    }
    public List<Integer> getActions() {
        return actions;
    }
    public double getCost() {
        return cost;
    }   
    private final State state;
    private final List<Integer> actions;
    private final double cost;
}
class State{
    public State(int x, int y){
        this.x = x;
        this.y = y;
    }
    public State(State s, int direct){
        switch(direct){
            case 1: //up 
                this.x = s.x;
                this.y = s.y-1;
                break;
            case 2: //down
                this.x = s.x;
                this.y = s.y+1;
                break;
            case 3: //right
                this.x = s.x+1;
                this.y = s.y;
                break;
            case 4: //left
                this.x = s.x-1;
                this.y = s.y;
                break;
            default :
                this.x = 0;
                this.y = 0;
                break;
        }
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public boolean equals(State s){
        return (this.x == s.x && this.y == s.y);
    }
    private int x;
    private int y;
}