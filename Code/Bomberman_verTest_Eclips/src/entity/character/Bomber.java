package entity.character;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import entity.bomb.Bomb;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;

public class Bomber extends Character {
	private int numBomb;
	private int bombWasSet;
	private double speed;
	private int scale;
	private int flameLength;
	private boolean isDeath;
	private int numLife;
	private int nonDieTime;
	public static int ID = 0;
	private int realID;
	private int pos[];
	
	public Bomber(int x, int y, int scale) {
		super();
		realID = Bomber.ID ++;
		
		this.setFitWidth(scale);
		this.setFitHeight(scale);
		
		this.setLayoutX(x);
		this.setLayoutY(y);
		try {
		//	this.setImage(new Image(new FileInputStream("src/image/bomber.png")));
			this.setImage(new Image(new FileInputStream("src/image/main/1.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.scale = scale;
		numBomb = 3;
		bombWasSet = 0;
		speed = (double)scale/30;
		flameLength = 2;
		isDeath = false;
		numLife = 2;
		nonDieTime = 100;
		pos = new int[2];
		pos[0] = x;
		pos[1] = y;
	}
	public void setFirstPosision(int x, int y) {
		System.out.println(this.realID + " " +x+ " " + y);
		this.setTranslateX(x*scale);
		this.setTranslateY(y*scale);
		System.out.println(this.realID + " " +this.getTranslateX() + " " + this.getTranslateY());
	}
	public void setPosision(int x, int y) {
		pos[0] = x;
		pos[1] = y;
	}
	public boolean isCurrentPos(int x,int y) {
		return pos[0]==x && pos[1]==y;
	}
	public int[] getPos() {
		return pos;
	}
	//
	public void moveUp() {
		this.setTranslateY(this.getTranslateY()-this.speed);
		try {
			if(this.getTranslateY()%10<5)
			this.setImage(new Image(new FileInputStream("src/image/main/3.png")));
			else this.setImage(new Image(new FileInputStream("src/image/main/4.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void moveDown() {
		this.setTranslateY(this.getTranslateY()+this.speed);
		try {
			if(this.getTranslateY()%10<5)
			this.setImage(new Image(new FileInputStream("src/image/main/5.png")));
			else this.setImage(new Image(new FileInputStream("src/image/main/6.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void moveRight() {
		this.setTranslateX(this.getTranslateX()+this.speed);
		try {
			if(this.getTranslateX()%10<5)
			this.setImage(new Image(new FileInputStream("src/image/main/1.png")));
			else this.setImage(new Image(new FileInputStream("src/image/main/2.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void moveLeft() {
		this.setTranslateX(this.getTranslateX()-this.speed);
		try {
			if(this.getTranslateX()%10<5)
			this.setImage(new Image(new FileInputStream("src/image/main/7.png")));
			else this.setImage(new Image(new FileInputStream("src/image/main/8.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public double getNextX(String direction) {
		switch(direction) {
		case "left":
			return this.getTranslateX() - this.speed;
		case "right":
			return this.getTranslateX() + this.speed;
		default:
			return this.getTranslateX();
		}
	}
	public double getNextY(String direction) {
		switch(direction) {
		case "up":
			return this.getTranslateY() - this.speed;
		case "down":
			return this.getTranslateY() + this.speed;
		default:
			return this.getTranslateY();
		}
	}
	
	public Bomb putBomb() {
		if(numBomb > bombWasSet) {
			bombWasSet++;
			int x,y;
			if((int)this.getTranslateX()%this.scale < this.scale/2) x = (int)(this.getTranslateX()/this.scale);
			else  x = (int)(this.getTranslateX()/this.scale) + 1;
			
			if((int)this.getTranslateY()%this.scale < this.scale/2) y = (int)(this.getTranslateY()/this.scale);
			else  y = (int)(this.getTranslateY()/this.scale) + 1;
			
			Bomb b = new Bomb(x*scale,y*scale,scale,flameLength,realID);
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
		this.speed += (double)this.scale/120;
	}
	public boolean hasBomb() {
		return (numBomb > bombWasSet);
	}
	public void powerUp() {
		this.flameLength ++;
	}
	public void riseUp() {
		this.nonDieTime = 100;
		numLife --;
		this.isDeath = false;
		this.setTranslateX(this.scale);
		this.setTranslateY(this.scale);
		try {
			this.setImage(new Image(new FileInputStream("src/image/main/1.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deathly(double x, double y) {
		try {
			this.setImage(new Image(new FileInputStream("src/image/main/dead.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.isDeath = true;
	}
	public int getLife() {
		return numLife;
	}
	public boolean isLife() {
		return !isDeath;
	}
	public void dead() {
		this.isDeath = true;
	}
	public void timeOff() {
		this.nonDieTime -= 1;
		ColorAdjust ca = new ColorAdjust();	
		this.setEffect(ca);
			if(nonDieTime % 10 > 5)
				ca.setBrightness(0.8);
			else ca.setBrightness(0);
	}
	public int getTimeNonDie() {
		return this.nonDieTime;
	}
	public int getPlayerId() {
		return this.realID;
	}
}
