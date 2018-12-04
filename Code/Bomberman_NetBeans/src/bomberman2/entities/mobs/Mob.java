/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman2.entities.mobs;

import bomberman2.entities.Entity;

/**
 *
 * @author Liscli
 */
public abstract class Mob extends Entity{
    public abstract void setEnityX(int x);
    public abstract void setEnityY(int y);
    public abstract double getSpeed();
    public abstract boolean hasBomb();
    public abstract int getCurDirect();
    public abstract void setCurDirect(int direct);
    
    protected int x;
    protected int y;
    protected double speed;
    protected int numBomb;
    protected int curDirect;
}
