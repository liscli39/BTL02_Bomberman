package entity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Flame extends ImageView {
	private double timeLive;
	
	public Flame(double x, double y,String type) {
		super();
		this.setFitWidth(30);
		this.setFitHeight(30);
		this.setTranslateX(x);
		this.setTranslateY(y);
		try {
			this.setImage(new Image(new FileInputStream("src/image/flame.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		timeLive = 1;
	}
	
	public void timeDown(){
		if(timeLive>0) timeLive -= 0.2;
	}
	public double getTimeOff() {
		return timeLive;
	}
}
