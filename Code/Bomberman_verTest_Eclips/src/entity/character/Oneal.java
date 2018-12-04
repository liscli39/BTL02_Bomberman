package entity.character;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import management.AI;

public class Oneal extends Enemy{
	private int scale;
	private double speed;
	private int x;
	private int y;
//	private boolean isMoveRight;
	private AI ai;
	
	public Oneal(int x, int y,int scale) {
		super();
		this.setFitWidth(scale);
		this.setFitHeight(scale);
		this.setLayoutX(0);
		this.setLayoutY(0);
		this.setTranslateX(x*scale);
		this.setTranslateY(y*scale);
		try {
			this.setImage(new Image(new FileInputStream("src/image/oneal.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		speed = (double)scale/30;
//		isMoveRight = true;
		ai = new AI();
		this.scale = scale;
		this.x=x;
		this.y=y;
	}
	public void dumpMove(char[][] map,int w, int h) {
		basicAI(map,w,h);
//		if(isMoveRight && map[y][x+1] !='#' &&  map[y][x+1] !='*'&& map[y][x+1]!='b'){
//			if(this.getTranslateX()<(x+1)*this.scale) this.moveRight();
//			else x++;
//		}else isMoveRight =false;
//		
//		if(!isMoveRight && map[y][x-1] !='#' &&  map[y][x-1] !='*'&& map[y][x-1]!='b'){
//			if(this.getTranslateX()>(x-1)*this.scale) this.moveLeft();
//			else x--;
//		}else isMoveRight =true;
	}
	public double getNextX(String direction) {
		return 0;
	}
	public double getNextY(String direction) {
		return 0;
	}
	// AI
	public void basicAI(char[][] map,int w, int h) {
		int direct = ai.getPath(this.x,this.y,map, w, h).get(1);
		System.out.println(direct);
		switch(direct) {
			case 1:
				if(this.getTranslateX()<(x+1)*this.scale) this.moveRight();
				else x++;
				break;
			case 2:
				if(this.getTranslateX()<(x-1)*this.scale) this.moveLeft();
				else x--;
				break;
			case 3:
				if(this.getTranslateY()<(y+1)*this.scale) this.moveDown();
				else y++;
				break;
			case 4:
				if(this.getTranslateY()<(y-1)*this.scale) this.moveUp();
				else y--;
				break;
			default: break;
		}
	}
	//
	public void moveUp() {
		this.setTranslateY(this.getTranslateY()-this.speed);
	}
	public void moveDown() {
		this.setTranslateY(this.getTranslateY()+this.speed);
	}
	public void moveLeft() {
		this.setTranslateX(this.getTranslateX()-this.speed);
	}
	public void moveRight() {
		this.setTranslateX(this.getTranslateX()+this.speed);
	}
}
