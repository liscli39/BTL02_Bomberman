package entity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

import javafx.scene.image.Image;

public class Balloon extends Enemy{
	public Balloon(int x, int y) {
		super();
		this.setFitWidth(30);
		this.setFitHeight(30);
		this.setLayoutX(x*30);
		this.setLayoutY(y*30);
		try {
			this.setImage(new Image(new FileInputStream("src/image/balloon.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void dumpMove() {
		Random rand = new Random();
		if(rand.nextInt(10) %2 == 0) {
			this.setTranslateX(this.getTranslateX()-rand.nextInt(10));
			this.setTranslateY(this.getTranslateY()-rand.nextInt(10));
		}else {
			this.setTranslateX(this.getTranslateX()+rand.nextInt(10));
			this.setTranslateY(this.getTranslateY()+rand.nextInt(10));
		}
	}
}
