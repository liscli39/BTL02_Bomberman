package entity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bomber extends ImageView {
	private int numBomb;
	private int bombWasSet;
	private double speed;
	
	public Bomber(int x, int y) {
		super();
		this.setFitWidth(30);
		this.setFitHeight(30);
		this.setLayoutX(x*30);
		this.setLayoutY(y*30);
		try {
			this.setImage(new Image(new FileInputStream("src/image/bomber.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		numBomb = 3;
		bombWasSet = 0;
		speed = 1;
	}
	
	public void moveUp() {
		this.setTranslateY(this.getTranslateY()-this.speed);
	}
	public void moveDown() {
		this.setTranslateY(this.getTranslateY()+this.speed);
	}
	public void moveRight() {
		this.setTranslateX(this.getTranslateX()+this.speed);
	}
	public void moveLeft() {
		this.setTranslateX(this.getTranslateX()-this.speed);
	}
	public Bomb putBomb() {
		if(numBomb > bombWasSet) {
			bombWasSet++;
			Bomb b = new Bomb(this.getTranslateX(),this.getTranslateY());
			return b;
		}else return null;
	}
	public int getNumBomb() {
		return numBomb;
	}
	public void addBomb() {
		this.numBomb++;
	}
	public void returnBomb(){
		if(this.bombWasSet>0)
		this.bombWasSet--;
	}
	public double getSpeed() {
		return speed;
	}
	public void speedUp() {
		this.speed += 2;
	}
	public boolean hasBomb() {
		return (numBomb > bombWasSet);
	}
}
