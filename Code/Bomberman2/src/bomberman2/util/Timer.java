/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman2.util;

/**
 *
 * @author Liscli
 */
public class Timer{
    public Timer(int m, int s){
        this.time = 60*m;
        this.second = time;
        this.curMinute = m;
        this.curSecond = s;
        this.isStart = false;
    }

    public void start(){
        this.isStart = true;
    }
    public void pause(){
        this.isStart = false;
    }
    public void reTime(){
        this.time = this.second;
    }
    public void tick(){
        if(this.isStart){
            this.time --;
            this.curSecond = this.time % 60;
            this.curMinute = this.time /60;
        }
    }

    public int getCurMinute() {
        return curMinute;
    }

    public int getCurSecond() {
        return curSecond;
    }
    @Override
    public String toString(){
        String s = this.curSecond < 10 ? "0" + this.curSecond : this.curSecond.toString();
        String m = this.curMinute < 10 ? "0" + this.curMinute : this.curMinute.toString();
        return m + ":" + s;
    }
    private int time;
    private final int second;
    private Integer curMinute;
    private Integer curSecond;
    private boolean isStart;
}
