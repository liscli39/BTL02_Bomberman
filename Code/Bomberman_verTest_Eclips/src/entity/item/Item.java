package entity.item;

import javafx.scene.image.ImageView;

public abstract class Item extends ImageView {
	public boolean brick = true;
	public abstract boolean isBrick();
	public abstract void showItem();
}
