/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman2.util;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author Liscli
 * @param <T>
 */
class Pair implements Comparable<Pair>{
    private final double key;
    private final Object value;
    public Pair(double key,Object value){
        this.key = key;
        this.value = value;
    }
    @Override
    public int compareTo(Pair o) {
        if(this.key > o.key) return 1;
        else if (this.key < o.key) return -1;
        else return 0;
    }
    
    public double getKey() {
        return key;
    }
    public Object getValue() {
        return value;
    }
}
public class MyPriorityQueue<T>{
    public MyPriorityQueue(){
        arr = new PriorityQueue<>();
    }
    public void push(T t, double priority){
        Pair p = new Pair(priority,t);
        arr.add(p);
//        arr.forEach((_item) -> {
//            System.out.print(_item.getKey() + "/");
//        });
//        System.out.println();
    }
    public T pop(){
        Pair p = arr.poll();
        return (T)p.getValue();
    }
    public boolean isEmpty(){
        return arr.isEmpty();
    }
    public int size(){
        return arr.size();
    }
    private final Queue<Pair> arr;
}
