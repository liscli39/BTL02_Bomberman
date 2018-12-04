package entity.item;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class BombItem extends Item {
	public BombItem(int x, int y, int scale) {
		super();
		this.setFitWidth(scale);
		this.setFitHeight(scale);
		this.setLayoutX(0);
		this.setLayoutY(0);
		this.setTranslateX(x*scale);
		this.setTranslateY(y*scale);
		try {
			this.setImage(new Image(new FileInputStream("src/image/brick.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.brick = true;
	}
	
	public boolean isBrick() {
		return this.brick;
	}
	public void showItem() {
		this.brick = false;
		try {
			this.setImage(new Image(new FileInputStream("src/image/bombItem.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
