package entity.item;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class Portal extends Item{
	public Portal(int x, int y,int scale) {
		super();
		this.setFitHeight(scale);
		this.setFitWidth(scale);
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
			this.setImage(new Image(new FileInputStream("src/image/portal.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
