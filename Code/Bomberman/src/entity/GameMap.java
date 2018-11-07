package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import management.GetInput;

public class GameMap extends AnchorPane{
	
	private static GetInput get; 
	private static List<String> map;
	private Map<KeyCode, Boolean> key;
	
	private Bomber bomber;
	private List<Wall> walls;
	private List<Brick> bricks;
	private List<Portal> portals;
	private List<Enemy> enemys;
	private List<Item> items;
	private List<Bomb> bombs;
	private List<Flame> flames;
	
	public GameMap(){
		super();
		InitializeComponent();
	}
	
	private void InitializeComponent() {
		this.setFocused(true);
		
		get = new GetInput(1);
		key = new HashMap<>();
		walls = new LinkedList<>();
		bricks = new LinkedList<>();
		portals = new LinkedList<>();
		enemys = new LinkedList<>();
		items = new LinkedList<>();
		bomber = new Bomber(30,30);
		bombs = new ArrayList<>();
		flames = new ArrayList<>();
		
		this.setPrefWidth(get.getWeight()*30);
		this.setPrefHeight(get.getHeight()*30);
		
		map = get.getMap();
		
		int y = 0;
		for(String line : map) {
			for(int x=0;x<line.length();x++) {
				Grass g = new Grass(x,y);
				this.getChildren().add(g);
				g.toBack();
				switch(line.charAt(x)) {
					case '#':
						Wall w = new Wall(x,y);
						walls.add(w);
						this.getChildren().add(w);
						break;
					case '*': 
						Brick b = new Brick(x,y);
						bricks.add(b);
						this.getChildren().add(b);
						break;
					case 'x': 
						Portal p = new Portal(x,y);
						portals.add(p);
						this.getChildren().add(p);
						break;
					case 'p':
						bomber = new Bomber(x,y);
						this.getChildren().add(bomber);
						break;
					case '1':
						Enemy e1 = new Balloon(x,y);
						enemys.add(e1);
						this.getChildren().add(e1);
						break;
					case '2': 
						Enemy e2 = new Oneal(x,y);
						enemys.add(e2);
						this.getChildren().add(e2);
						break;
					case 'b': 
						Item bi = new BombItem(x,y);
						items.add(bi);
						this.getChildren().add(bi);
						break;
					case 'f': 
						Item fi = new FlameItem(x,y);
						items.add(fi);
						this.getChildren().add(fi);
						break;
					case 's':
						Item si = new SpeedItem(x,y);
						items.add(si);
						this.getChildren().add(si);
						break;
				}
			}
			y++;
		}
		
		key.put(KeyCode.UP, false);
		key.put(KeyCode.DOWN, false);
		key.put(KeyCode.RIGHT, false);
		key.put(KeyCode.LEFT, false);

		this.setOnKeyPressed(event->{
			switch (event.getCode()) {
				case UP:
					key.put(KeyCode.UP, true);
					break;
				case DOWN:
					key.put(KeyCode.DOWN, true);
					break;
				case RIGHT:
					key.put(KeyCode.RIGHT, true);
					break;
				case LEFT:
					key.put(KeyCode.LEFT, true);
					break;
				case SPACE:
					if(bomber.hasBomb()) {
						Bomb b = bomber.putBomb();
						bombs.add(b);
						this.getChildren().add(b);
					}
					break;
				default:
					break;
						
			}
		});
		this.setOnKeyReleased(event->{
			switch (event.getCode()) {
				case UP:
					key.put(KeyCode.UP, false);
					break;
				case DOWN:
					key.put(KeyCode.DOWN, false);
					break;
				case RIGHT:
					key.put(KeyCode.RIGHT, false);
					break;
				case LEFT:
					key.put(KeyCode.LEFT, false);
					break;
				default:
					break;					
			}
		});
		bomber.setFocusTraversable(true);
	}
	
	public void update() {
		// get key event
		key.forEach((k,v) -> {
			switch (k) {
				case UP:
					
					if(v && checkImpact(bomber)) bomber.moveUp();
					break;
				case DOWN:
					if(v) bomber.moveDown();
					break;
				case RIGHT:
					if(v) {
						bomber.moveRight();
//						if(bomber.getTranslateX() > 150 && this.getTranslateX() > 0 ) {
//							this.setTranslateX(this.getTranslateX()- bomber.getSpeed());
//						}
					}
					break;
				case LEFT:
					if(v) {
						bomber.moveLeft();
//						if(bomber.getTranslateX() < 930-150 && this.getTranslateX() < 0) {
//							this.setTranslateX(this.getTranslateX()+ bomber.getSpeed());
//						}
					}
					break;
				default:
					break;					
			}
		});
		// count down bomb
		Iterator<Bomb> ib = bombs.listIterator();
		while(ib.hasNext()){
			Bomb b = ib.next();
			if(b.getTimeOff()> 0) {
				b.timeDown();
			}else {
				flames = b.explosive();
				bomber.addBomb();
				this.getChildren().remove(b);
				flames.forEach(f->{
					this.getChildren().add(f);
				});
				ib.remove();
			}
		}
		// 
		Iterator<Flame> ifl = flames.listIterator();
		while(ifl.hasNext()){
			Flame f = ifl.next();
			if(f.getTimeOff()> 0) {
				f.timeDown();
			}else {
				this.getChildren().remove(f);
				ifl.remove();
			}
		}
		//enemy move
		enemys.forEach(e ->{
			e.dumpMove();
		});
	}

	public static void getMap() {
		
	}
	
	private boolean checkImpact(Object obj) {
		if(obj instanceof Bomber) {
			walls.forEach(w->{

			});
			bricks.forEach(b->{
				
			});
		}
		return true;
	}
}
