package management;

import java.util.LinkedList;
import java.util.List;

public class Successor implements Comparable<Successor>{
	private State state;
	private List<Integer> action;
	private double cost;
	private double heuristic;
	public Successor(int x, int y, int action,double cost,double heuristic){
		state = new State(x,y);
		this.action = new LinkedList<>();
		this.action.add(action);
		this.cost = cost;
		this.heuristic = heuristic;
	}
	public Successor(State s, List<Integer> action,double cost,double heuristic){
		state = s;
		this.action = action;
		this.cost = cost;
		this.heuristic = heuristic;
	}
	/**
	 * @return the state
	 */
	public State getState() {
		return state;
	}
	/**
	 * @return the action
	 */
	public List<Integer> getAction() {
		return action;
	}
	/**
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}
	/**
	 * @return the heuristic
	 */
	public double getHeuristic() {
		return heuristic;
	}
	@Override
	public int compareTo(Successor sc) {
		if(this.cost+this.heuristic > sc.cost + sc.heuristic) {
			return 1;
		}else if(this.cost+this.heuristic < sc.cost + sc.heuristic) {
			return -1;
		}else return 0;
	}
}
