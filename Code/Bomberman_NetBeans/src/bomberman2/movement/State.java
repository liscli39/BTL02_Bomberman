/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman2.movement;

/**
 *
 * @author Liscli
 */
public class State{
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