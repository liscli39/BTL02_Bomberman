package entity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bomb extends ImageView {
	private double timeOff;
	private boolean isBoom;
	
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
	}
	public double getTimeOff() {
		return timeOff;
	}
	public void timeDown() {
		if(timeOff>0)
			timeOff -= 0.02;
	}
	public void explosive() {
		isBoom = true;
		System.out.println("KaBooommm!!!!!");
	}
	public boolean isExplosive(){
		return isBoom;
	}
}
