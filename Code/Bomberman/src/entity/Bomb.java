package entity;

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
	public Bomb(double x, double y) {
		super();
		this.setFitWidth(30);
		this.setFitHeight(30);
		this.setTranslateX(x+30);
		this.setTranslateY(y+30);
		try {
			this.setImage(new Image(new FileInputStream("src/image/bomb.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		isBoom = false;
		timeOff = 3;
		flameLength = 2;
		flames = new LinkedList<>();
	}
	public double getTimeOff() {
		return timeOff;
	}
	public void timeDown() {
		if(timeOff>0)
			timeOff -= 0.02;
	}
	public List<Flame> explosive() {
		isBoom = true;
		flames.clear();
		double x = this.getTranslateX();
		double y = this.getTranslateY();
		Flame f = new Flame(x,y,"center");
		flames.add(f);
		for(int i=0;i<flameLength;i++) {
			f = new Flame(x,y-(i+1)*30,"up");
			flames.add(f);
			f = new Flame(x,y+(i+1)*30,"down");
			flames.add(f);
			f = new Flame(x+(i+1)*30,y,"right");
			flames.add(f);
			f = new Flame(x-(i+1)*30,y,"left");
			flames.add(f);
		}
		System.out.println("KaBooommm!!!!!");
		return flames;
	}
	public boolean isExplosive(){
		return isBoom;
	}
	public void flameUp() {
		flameLength++;
	}
}
