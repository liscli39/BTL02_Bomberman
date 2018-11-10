package entity.barrier;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class Wall extends Barrier {
	public Wall(int x, int y,int scale){
		super();
		this.setFitWidth(scale);
		this.setFitHeight(scale);
		this.setLayoutX(0);
		this.setLayoutY(0);
		this.setTranslateX(x*scale);
		this.setTranslateY(y*scale);
		
		try {
//			Random rand = new Random();
//
//			int  n = rand.nextInt(50) + 1;
//			if(n%3==1)
				this.setImage(new Image(new FileInputStream("src/image/wall.png")));
//				else if(n%3==2) this.setImage(new Image(new FileInputStream("src/image/wall2.png")));
//				else this.setImage(new Image(new FileInputStream("src/image/wall.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
