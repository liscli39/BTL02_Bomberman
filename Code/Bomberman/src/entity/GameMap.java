package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import entity.barrier.Barrier;
import entity.barrier.Brick;
import entity.barrier.Grass;
import entity.barrier.Wall;
import entity.bomb.Bomb;
import entity.bomb.Flame;
import entity.character.Balloon;
import entity.character.Bomber;
import entity.character.Enemy;
import entity.character.Oneal;
import entity.item.BombItem;
import entity.item.FlameItem;
import entity.item.Item;
import entity.item.Portal;
import entity.item.SpeedItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import management.Collision;
import management.GetInput;

public class GameMap extends AnchorPane{
	private int numMap;
	private GetInput get; 
	private List<String> map;
	public static Map<KeyCode, Boolean> keyBoard;
	private int scale;
	private Collision collision;
	private char[][] mapc;
	private boolean isLife;
	
	private Bomber bomber;
	private List<Enemy> enemys;
	private List<Barrier> walls;
	private List<Barrier> bricks;
	private List<Item> items;
	private List<Bomb> bombs;
	private List<Flame> flames;

	
	public GameMap(int scale, int map){
		super();
		this.scale = scale;
		this.numMap = map;
		InitializeComponent();
	}
	public void nextLevel() {
		this.numMap++;
		this.getChildren().clear();
		InitializeComponent();
	}
	public void playAgain() {
		this.getChildren().clear();
		InitializeComponent();
	}
	public int getMapNum() {
		return numMap;
	}
	private void InitializeComponent() {
		this.setFocused(true);
		
		get = new GetInput(numMap);
		keyBoard = new HashMap<>();
		collision = new Collision(); 
		
		walls = new LinkedList<>();
		bricks = new LinkedList<>();
		enemys = new LinkedList<>();
		items = new LinkedList<>();
		bomber = new Bomber(0,0,scale);
		bombs = new ArrayList<>();
		flames = new ArrayList<>();
		
		this.setPrefWidth(get.getWeight()*scale);
		this.setPrefHeight(get.getHeight()*scale);
		this.setFocusTraversable(true);
		
		map = get.getMap();
		mapc = new char[get.getHeight()][get.getWeight()];
		isLife = true;
		
		int y = 0;
		for(String line : map) {
			for(int x=0;x<line.length();x++) {
				if(line.charAt(x)== 'x' || line.charAt(x)== 'b'
				|| line.charAt(x)== 'f'	|| line.charAt(x)== 's')  mapc[y][x] = '*';
				else mapc[y][x] = line.charAt(x);
				Grass g = new Grass(x,y,scale);
				this.getChildren().add(g);
				g.toBack();
				
				switch(line.charAt(x)) {
					case '#':
						Wall w = new Wall(x,y,scale);
						walls.add(w);
						this.getChildren().add(w);
						break;
					case '*': 
						Brick b = new Brick(x,y,scale);
						bricks.add(b);
						this.getChildren().add(b);
						break;
					case 'x': 
						Portal p = new Portal(x,y,scale);
						items.add(p);
						this.getChildren().add(p);
						break;
					case 'p':
						bomber.setPosision(x, y);
						this.getChildren().add(bomber);
						break;
					case '1':
						Enemy e1 = new Balloon(x,y,scale);
						enemys.add(e1);
						this.getChildren().add(e1);
						break;
					case '2': 
						Enemy e2 = new Oneal(x,y,scale);
						enemys.add(e2);
						this.getChildren().add(e2);
						break;
					case 'b': 
						Item bi = new BombItem(x,y,scale);
						items.add(bi);
						this.getChildren().add(bi);
						break;
					case 'f': 
						Item fi = new FlameItem(x,y,scale);
						items.add(fi);
						this.getChildren().add(fi);
						break;
					case 's':
						Item si = new SpeedItem(x,y,scale);
						items.add(si);
						this.getChildren().add(si);
						break;
					default: break;
				}
			}
			y++;
		}
		bomber.setFocusTraversable(true);
	}
	
	public int update() {
		if(isLife) {	
		// get keyBoard event
		keyBoard.forEach((k,v) -> {
			List<ImageView> barriers = new LinkedList<>();
			barriers.addAll(walls);
			barriers.addAll(bricks);
			items.forEach(i->{
				if(i.isBrick()) {
					barriers.add(i);
				}
			});
			
			switch (k) {
				case W:			
					if(v && collision.canMove(bomber, "up", scale, barriers)
							&& !collision.hitBomb(bomber, mapc, scale, "up")) {
						bomber.moveUp();
					}
					break;
				case S:
					if(v && collision.canMove(bomber, "down", scale, barriers)
							&& !collision.hitBomb(bomber, mapc, scale, "down")) {
						bomber.moveDown();
					}
					break;
				case D:
					if(v && collision.canMove(bomber, "right", scale, barriers)
							&& !collision.hitBomb(bomber, mapc, scale, "right")) {
						bomber.moveRight();
						if(bomber.getTranslateX() > 150 && this.getTranslateX() >= 390-930 ) {
							this.setTranslateX(this.getTranslateX() - (int)bomber.getSpeed());
						}
					}
					break;
				case A:
					if(v && collision.canMove(bomber, "left", scale, barriers)
							&& !collision.hitBomb(bomber, mapc, scale, "left")) {
						bomber.moveLeft();
						if(bomber.getTranslateX() < 930-150 && this.getTranslateX() <= 0) {
							this.setTranslateX(this.getTranslateX()+ (int)bomber.getSpeed());
						}
					}
					break;
				case SPACE:
					if(v) {
						v = false;
						if(bomber.hasBomb()) {
							Bomb nb = bomber.putBomb();
							mapc[(int)nb.getTranslateY()/this.scale][(int)nb.getTranslateX()/this.scale]='b';
							boolean canPut = true;
							for(Bomb b : bombs) {
								if(collision.isDuplicate(b,nb)) {
									bomber.addBomb();
									canPut = false;
									break;
								}
							}
							if(canPut) {
								bombs.add(nb);
								this.getChildren().add(nb);
								bomber.toFront();
							}
						}
					}
					break;
				default:
					break;					
			}
		});
		// count down bomb
		Iterator<Bomb> ib = bombs.listIterator();
		while(ib.hasNext()){
			boolean isexplosive = false;
			Bomb b = ib.next();
			for(Flame f : flames) {
				if(collision.isDuplicate(f, b)) {
					isexplosive = true;
					break;
				}
			}
			if(b.getTimeOff()> 0 && !isexplosive) {
				b.timeDown();
			}else {
				List<Flame> nfs =b.explosive(mapc);
				mapc[(int)b.getTranslateY()/this.scale][(int)b.getTranslateX()/this.scale]=' ';
				this.getChildren().remove(b);
				ib.remove();
				bomber.addBomb();
				
				nfs.forEach(f->{
					this.getChildren().add(f);
					
					Iterator<Item> ii = items.listIterator();
					while(ii.hasNext()) {
						Item item = ii.next();
						if(collision.isDuplicate(item,f)) {						
							if(item.isBrick() && item instanceof Portal) {
								mapc[(int)item.getTranslateY()/this.scale][(int)item.getTranslateX()/this.scale] = 'x';
							}else mapc[(int)item.getTranslateY()/this.scale][(int)item.getTranslateX()/this.scale] = ' ';
							
							if(item.isBrick()  || item instanceof Portal) {
								item.showItem();
							}else {
								this.getChildren().remove(item);
								ii.remove();
							}
						}
					}
					
				});
				flames.addAll(nfs);
			}
		}
		}
		// 
		Iterator<Flame> ifl = flames.listIterator();
		while(ifl.hasNext()){
			
			Flame f = ifl.next();
			if(f.getTimeOff()> 0) {
				f.timeDown();
				
				Iterator<Barrier> ibr = bricks.listIterator();
				while(ibr.hasNext()) {
					Barrier brick = ibr.next(); 
					if(collision.isDuplicate(f, brick)) {
						mapc[(int)brick.getTranslateY()/this.scale][(int)brick.getTranslateX()/this.scale] = ' ';
						this.getChildren().remove(brick);
						ibr.remove();
					}
				}
				Iterator<Enemy> ie = enemys.listIterator();
				while(ie.hasNext()) {
					Enemy e = ie.next();
					if(collision.collisionDetection(e.getTranslateX(), e.getTranslateY(), f.getTranslateX(), f.getTranslateY(), scale)) {
						this.getChildren().remove(e);
						ie.remove();
					}
				}
				
				if(bomber.getTimeNonDie() <= 0 && bomber.isLife() && collision.collisionDetection(bomber.getTranslateX(), bomber.getTranslateY(), f.getTranslateX(), f.getTranslateY(), scale-scale/5)) {
					bomberDeadAction();
				}
				if(!bomber.isLife() && bomber.getLife() >0) bomber.riseUp();
				
			}else {
				this.getChildren().remove(f);
				ifl.remove();
			}
			
		}
		if(bomber.getTimeNonDie()>0) bomber.timeOff();
		//
		Iterator<Item> ii = items.listIterator();
		while(ii.hasNext()) {
			Item item = ii.next();
			if(!item.isBrick() && collision.canEat(bomber, scale, item)) {
				if(item instanceof SpeedItem) bomber.speedUp();
				if(item instanceof BombItem) bomber.addBomb();
				if(item instanceof FlameItem) bomber.powerUp();
				if(item instanceof Portal) return 2;
				mapc[(int)item.getTranslateY()/this.scale][(int)item.getTranslateX()/this.scale] = ' ';
				this.getChildren().remove(item);
				ii.remove();
			}
		}
		//enemy move
		enemys.forEach(e ->{
			e.dumpMove(mapc);			
			if(bomber.getTimeNonDie() <= 0 && bomber.isLife() && collision.collisionDetection(bomber.getTranslateX(), bomber.getTranslateY(), e.getTranslateX(), e.getTranslateY(), scale-scale/5)) {
				bomberDeadAction();
			}
			if(!bomber.isLife() && bomber.getLife() >0) bomber.riseUp();
		});
		if(!isLife) return 1;
		return 0;
	}
	public void changeSize(int scale) {
		bomber.setFitWidth(scale);
		bomber.setFitHeight(scale);
//		private List<Enemy> enemys;
//		private List<Barrier> walls;
//		private List<Barrier> bricks;
//		private List<Item> items;
//		private List<Bomb> bombs;
//		private List<Flame> flames;
	}
	
	public void printMapc() {
		for(int i=0;i<get.getHeight();i++){
			for(int j=0;j<get.getWeight();j++) {
				System.out.print(mapc[i][j]);
			}System.out.println();
		}
	}
	private void bomberDeadAction(){
		if(bomber.getLife() > 0) {
			this.setTranslateX(0);
			this.setTranslateY(0);
			bomber.dead();
		}
		else {
			bomber.deathly(this.getHeight(),this.getHeight());
			isLife = false;
		}
	}
}
