package management;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class AI{
	public List<Integer> getPath(int x, int y,char[][]map,int w, int h) {
		return this.aStarSearch(x,y,map, w, h);
	}
	
	private List<Integer> aStarSearch(int x, int y,char[][]map,int w, int h) {
		PriorityQueue<Successor> pq = new PriorityQueue<>();
		pq.add(getStartState(x,y));
		List<State> visted = new LinkedList<>();
		boolean isContinue = false;
		while(!pq.isEmpty()) {
			isContinue = false;
			Successor sc = pq.poll();
			State s = sc.getState();
			List<Integer> a = sc.getAction();
			double c = sc.getCost();
			for(State v : visted) {
				if(v.getX()==s.getX() && v.getY()==s.getY()) {
					isContinue = true;
					break;
				}
			}
			if(isContinue) continue;
			if(a.size()>15 || this.isGoalState(s, map)) return a;
			visted.add(s);
			List<Successor> nscs = getSuccessors(s.getX(),s.getY(),map);
			for(Successor nsc : nscs) {
				State nst = nsc.getState();
				a.addAll(nsc.getAction());
				c += nsc.getCost();
				double heu = heuristic(nst,map,w,h);
				pq.add(new Successor(nst,a,c,heu));
			}
		}
		return null;
	}
	
	private List<Successor> getSuccessors(int x, int y, char[][]map){
		List<Successor> scs = new LinkedList<>();
		if(map[y][x+1] !='#' &&  map[y][x+1] !='*'&& map[y][x+1]!='b') {
			scs.add(new Successor(x+1,y,1,1,0));
		}
		if(map[y][x-1] !='#' &&  map[y][x-1] !='*'&& map[y][x-1]!='b') {		
			scs.add(new Successor(x-1,y,2,1,0));
		}
		if(map[y+1][x] !='#' &&  map[y+1][x] !='*'&& map[y+1][x]!='b') {
			scs.add(new Successor(x,y+1,3,1,0));
		}
		if(map[y-1][x] !='#' &&  map[y-1][x] !='*'&& map[y-1][x]!='b') {
			scs.add(new Successor(x,y-1,4,1,0));
		}
		return scs;
	}
	private double heuristic(State s,char[][]map, int w, int h){
		for(int i=0;i<h;i++) {
			for(int j=0;j<w;j++) {
				if(map[i][j]=='p') {
					return Math.sqrt((s.getY()-i)*(s.getY()-i) +(s.getX()-j)*(s.getX()-j));
				}
			}
		}
		return 0;
	}
	private boolean isGoalState(State s,char[][] map){
		return map[s.getY()][s.getX()] == 'p';
	}
	private Successor getStartState(int x, int y){
		return new Successor(x,y,0,0,0);
	}
}
