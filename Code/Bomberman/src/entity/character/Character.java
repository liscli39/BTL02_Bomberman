package entity.character;

import javafx.scene.image.ImageView;

public abstract class Character extends ImageView {
	public abstract void moveUp();
	public abstract void moveDown();
	public abstract void moveLeft();
	public abstract void moveRight();
	
	
	public abstract double getNextX(String direction);
	public abstract double getNextY(String direction);
}
