package entity.character;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class Balloon extends Enemy{
	private int scale;
	private double speed;
	private int x;
	private int y;
	private boolean isMoveRight;
	
	public Balloon(int x, int y,int scale) {
		super();
		this.setFitWidth(scale);
		this.setFitHeight(scale);
		this.setLayoutX(0);
		this.setLayoutY(0);
		this.setTranslateX(x*scale);
		this.setTranslateY(y*scale);
		try {
			this.setImage(new Image(new FileInputStream("src/image/balloon.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		speed = (double)scale/30;
		isMoveRight = true;
		this.scale = scale;
		this.x=x;
		this.y=y;
	}
	public void dumpMove(char[][] map,int w, int h) {
		if(isMoveRight && map[y][x+1] !='#' &&  map[y][x+1] !='*'&& map[y][x+1]!='b'){
			if(this.getTranslateX()<(x+1)*this.scale) this.moveRight();
			else x++;
		}else isMoveRight =false;
		if(!isMoveRight && map[y][x-1] !='#' &&  map[y][x-1] !='*'&& map[y][x-1]!='b'){
			if(this.getTranslateX()>(x-1)*this.scale) this.moveLeft();
			else x--;
		}else isMoveRight =true;
	}
	public double getNextX(String direction) {
		return 0;
	}
	public double getNextY(String direction) {
		return 0;
	}
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
