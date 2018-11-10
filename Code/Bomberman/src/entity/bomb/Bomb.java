package entity.bomb;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bomb extends ImageView {
	private double timeOff;
	private boolean isBoom;
	private int flameLength;
	private List<Flame> flames;
	private int scale;
	
	public Bomb(double x, double y,int scale,int flameLength) {
		super();
		this.setFitWidth(scale);
		this.setFitHeight(scale);
		this.setTranslateX(x);
		this.setTranslateY(y);
		try {
			this.setImage(new Image(new FileInputStream("src/image/bomb.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.scale = scale;
		isBoom = false;
		timeOff = 4;
		this.flameLength = flameLength;
		flames = new LinkedList<>();
	}
	public double getTimeOff() {
		return timeOff;
	}
	public void timeDown() {
		if(timeOff>0)
			timeOff -= 0.02;
	}
	public List<Flame> explosive(char[][] map) {
		isBoom = true;
		flames.clear();
		int x = (int)this.getTranslateX()/this.scale;
		int y = (int)this.getTranslateY()/this.scale;
		Flame f = new Flame(this.getTranslateX(),this.getTranslateY(),"center",scale);
		flames.add(f);
		for(int i=0;i<flameLength;i++) {
			if(map[y-i-1][x] == '#') break;
			if(map[y-i-1][x]== '*' ) {
				f = new Flame(this.getTranslateX(),this.getTranslateY()-(i+1)*scale,"up",scale);
				flames.add(f);
				break;
			} 
			f = new Flame(this.getTranslateX(),this.getTranslateY()-(i+1)*scale,"up",scale);
			flames.add(f);
		}
		for(int i=0;i<flameLength;i++) {
			if(map[y+i+1][x] == '#') break;
			if(map[y+i+1][x]== '*') {
				f = new Flame(this.getTranslateX(),this.getTranslateY()+(i+1)*scale,"down",scale);
				flames.add(f);
				break;
			} 
			f = new Flame(this.getTranslateX(),this.getTranslateY()+(i+1)*scale,"down",scale);
			flames.add(f);
		}
		for(int i=0;i<flameLength;i++) {
			if(map[y][x-1-i] == '#') break;
			if(map[y][x-i-1]== '*') {
				f = new Flame(this.getTranslateX()-(i+1)*scale,this.getTranslateY(),"up",scale);
				flames.add(f);				
				break;
			}
			f = new Flame(this.getTranslateX()-(i+1)*scale,this.getTranslateY(),"up",scale);
			flames.add(f);
		}
		for(int i=0;i<flameLength;i++) {
			if(map[y][x+i+1] == '#') break;
			if(map[y][x+i+1]== '*') {
				f = new Flame(this.getTranslateX()+(i+1)*scale,this.getTranslateY(),"up",scale);
				flames.add(f);
				break;
			}
			f = new Flame(this.getTranslateX()+(i+1)*scale,this.getTranslateY(),"up",scale);
			flames.add(f);
		}
//		System.out.println("KaBooommm!!!!!");
		return flames;
	}
	public boolean isExplosive(){
		return isBoom;
	}
	public void flameUp() {
		flameLength++;
	}
}
