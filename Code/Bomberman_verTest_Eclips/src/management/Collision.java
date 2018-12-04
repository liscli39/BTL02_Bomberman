package management;

import java.util.List;

import entity.character.Bomber;
import entity.character.Character;
import entity.item.Item;
import javafx.scene.image.ImageView;

public class Collision{
	
	public boolean collisionDetection(double x1, double y1,double x2, double y2, double scale) {
		double x01= x1 + scale/2;
		double y01= y1 + scale/2;
		
		double x02=x2 + scale/2;
		double y02=y2 + scale/2;
		
		if(Math.abs(x01-x02)< scale - 3 &&
				Math.abs(y01-y02)< scale-3) {

			return true;
		}
		return false;
	}
	
	public boolean canMove(Character c,String direction,double scale, List<ImageView> objs) {		
		for(ImageView obj : objs) {			
				double x1 = c.getNextX(direction);
				double y1 = c.getNextY(direction);
				double x2 = obj.getTranslateX();
				double y2 = obj.getTranslateY();
				
				switch(direction) {
					case "up":
						if(y2<y1 && collisionDetection(x1,y1,x2,y2,scale)) {
							if(Math.abs(x1-x2) < scale && x1 < x2 && Math.abs(x1-x2)>2*scale/3) c.setTranslateX(c.getTranslateX()-1);
							if(Math.abs(x1-x2) < scale && x1 > x2 && Math.abs(x1-x2)>2*scale/3) c.setTranslateX(c.getTranslateX()+1);
							return false;
						}
						break;
					case "down":
						if(y2>y1 && collisionDetection(x1,y1,x2,y2,scale)) {
							if(Math.abs(x1-x2) < scale && x1 < x2 && Math.abs(x1-x2)>2*scale/3) c.setTranslateX(c.getTranslateX()-1);
							if(Math.abs(x1-x2) < scale && x1 > x2 && Math.abs(x1-x2)>2*scale/3) c.setTranslateX(c.getTranslateX()+1);
							return false;
						}
						break;						
					case "right":
						if(x2>x1 && collisionDetection(x1,y1,x2,y2,scale)) {
							if(Math.abs(y1-y2) < scale && y1 < y2 && Math.abs(y1-y2)>2*scale/3) c.setTranslateY(c.getTranslateY()-1);
							if(Math.abs(y1-y2) < scale && y1 > y2 && Math.abs(y1-y2)>2*scale/3) c.setTranslateY(c.getTranslateY()+1);
							return false;
						}
						break;					
					case "left":
						if(x1>x2 && collisionDetection(x1,y1,x2,y2,scale)) {
							if(Math.abs(y1-y2) < scale && y1 < y2 && Math.abs(y1-y2)>2*scale/3) c.setTranslateY(c.getTranslateY()-1);
							if(Math.abs(y1-y2) < scale && y1 > y2 && Math.abs(y1-y2)>2*scale/3) c.setTranslateY(c.getTranslateY()+1);
							return false;
						}
						break;
					default:
				}
		}
		return true;
	}
	public boolean isDuplicate(ImageView i1,ImageView i2) { 
		return i1.getTranslateX()==i2.getTranslateX() &&
				i1.getTranslateY()== i2.getTranslateY();
	}
	public boolean canEat(Character c,int scale, Item item) {
		if(item instanceof Item) {
			double x1 = c.getTranslateX();
			double y1 = c.getTranslateY();
			double x2 = item.getTranslateX();
			double y2 = item.getTranslateY();				
			return this.collisionDetection(x1, y1, x2, y2, scale/2);
		}
		return false;
	}
	public boolean hitBomb(Bomber b,char[][] map,int scale,String direction){
		int x,y;
		if((int)b.getTranslateX()%scale < scale/2) x = (int)b.getTranslateX()/scale;
		else  x = (int)b.getTranslateX()/scale + 1;
		
		if((int)b.getTranslateY()%scale < scale/2) y = (int)b.getTranslateY()/scale;
		else  y = (int)b.getTranslateY()/scale + 1;
//		System.out.println(x + " " + y);
		switch(direction) {
		case "up":
				if(map[y-1][x] == 'b') {
					if(b.getTranslateY() <= y*scale)
//					System.out.println(x + " " + (y-1));
					return true;
				}
			break;
		case "down":
			if(map[y+1][x] == 'b') {
				if(b.getTranslateY() >= y*scale)
//				System.out.println(x + " " + (y+1));
				return true;
			}
		break;
		case "left":
			if(map[y][x-1] == 'b') {
				if(b.getTranslateX() <= x*scale)
//				System.out.println((x-1) + " " + y);
				return true;
			}
		break;
		case "right":
			if(map[y][x+1] == 'b') {
				if(b.getTranslateX() >= x*scale)
//				System.out.println((x+1) + " " + y);
				return true;
			}
		break;
		
		}
		return false;
	}
	
}
