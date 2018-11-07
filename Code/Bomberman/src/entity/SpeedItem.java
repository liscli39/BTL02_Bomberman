package entity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class SpeedItem extends Item{
	public SpeedItem(int x, int y) {
		super();
		this.setFitWidth(30);
		this.setFitHeight(30);
		this.setLayoutX(x*30);
		this.setLayoutY(y*30);
		try {
			this.setImage(new Image(new FileInputStream("src/image/speedItem.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
