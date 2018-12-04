/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman2.movement;

import java.util.List;

/**
 *
 * @author Liscli
 */
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
