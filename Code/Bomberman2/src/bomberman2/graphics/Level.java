/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman2.graphics;

import bomberman2.entities.Entity;
import bomberman2.entities.barriers.Brick;
import bomberman2.entities.barriers.Grass;
import bomberman2.entities.barriers.Portal;
import bomberman2.entities.barriers.StaticEntity;
import bomberman2.entities.barriers.Wall;
import bomberman2.entities.bombs.Bomb;
import bomberman2.entities.bombs.Flame;
import bomberman2.entities.items.BombItem;
import bomberman2.entities.items.FlameItem;
import bomberman2.entities.items.Item;
import bomberman2.entities.items.SpeedItem;
import bomberman2.entities.mobs.Player;
import bomberman2.entities.mobs.enemys.Balloom;
import bomberman2.entities.mobs.enemys.Enemy;
import bomberman2.entities.mobs.enemys.Oneal;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Liscli
 */
public class Level {
    protected final List<Entity> entities;
    
    protected final List<StaticEntity> staticEntities;
    protected final List<Grass> grass;
    protected final List<Brick> bricks;
    protected final List<Wall> walls;
    protected final List<Portal> portals;
    
    protected final List<Bomb> bombs;
    protected final List<Flame> flames;
    
    protected final List<Item>items;
    protected final List<BombItem> bombItems;
    protected final List<FlameItem> flameItems;
    protected final List<SpeedItem> speedItems;
    
    protected final List<Player> players;
    
    protected final List<Enemy> enemys;
    protected final List<Balloom> ballooms;
    protected final List<Oneal> oneals;
    
    protected int level;

    public Level(int level) {
        this.level = level;
        this.entities = new LinkedList<>();
        
        this.staticEntities = new LinkedList<>();
        this.grass = new LinkedList<>();
        this.bricks = new LinkedList<>();
        this.walls = new LinkedList<>();
        this.portals = new LinkedList<>();
        
        this.bombs = new LinkedList<>();
        this.flames = new LinkedList<>();
        
        this.items = new LinkedList<>();
        this.bombItems = new LinkedList<>();
        this.flameItems = new LinkedList<>();
        this.speedItems = new LinkedList<>();
        
        this.players = new LinkedList<>();
        
        this.enemys = new LinkedList<>();
        this.ballooms = new LinkedList<>();
        this.oneals = new LinkedList<>();
    }
    
    public void InitializeComponent(List<String> map){
        int y = 0;
        for(String r : map){
            for(int x=0;x<r.length();x++){
                Grass g = new Grass(x,y);
                grass.add(g);
                switch(r.charAt(x)){
                    case'#':
                        Wall w = new Wall(x,y);
                        walls.add(w);
                        break;
                    case'*':
                        Brick b = new Brick(x,y);
                        bricks.add(b);
                        break;
                    case'x':
                        Portal p = new Portal(x,y);
                        portals.add(p);
                        break;
                    case'p':
                        Player pl = new Player(x,y);
                        players.add(pl);
                        break;
                    case'1':
                        Balloom e1 = new Balloom(x,y);
                        ballooms.add(e1);
                        break;
                    case'2':
                        Oneal e2 = new Oneal(x,y);
                        oneals.add(e2);
                        break;
                    case'b':
                        BombItem bi = new BombItem(x,y);
                        bombItems.add(bi);
                        break;
                    case'f':
                        FlameItem fi = new FlameItem(x,y);
                        flameItems.add(fi);
                        break;
                    case's':
                        SpeedItem si = new SpeedItem(x,y);
                        speedItems.add(si);
                        break;
                    default: 
                        break;
                }
            }
            y++;
        }
        enemys.addAll(this.oneals);
        enemys.addAll(this.ballooms);

        items.addAll(this.bombItems);
        items.addAll(this.flameItems);
        items.addAll(this.speedItems);
        
        staticEntities.addAll(this.bricks);
        staticEntities.addAll(this.walls);
        staticEntities.addAll(this.portals);
        staticEntities.addAll(this.items);
        
        entities.addAll(grass);
        entities.addAll(this.staticEntities);
        entities.addAll(this.enemys);
        entities.addAll(this.players);
       
    }

    public List<Grass> getGrass() {
        return grass;
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public List<Portal> getPortals() {
        return portals;
    }

    public List<Bomb> getBombs() {
        return bombs;
    }

    public List<Flame> getFlames() {
        return flames;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<BombItem> getBombItems() {
        return bombItems;
    }

    public List<FlameItem> getFlameItems() {
        return flameItems;
    }

    public List<SpeedItem> getSpeedItems() {
        return speedItems;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Enemy> getEnemys() {
        return enemys;
    }

    public List<Balloom> getBallooms() {
        return ballooms;
    }

    public List<Oneal> getOneals() {
        return oneals;
    }

    public List<StaticEntity> getStaticEntities() {
        return staticEntities;
    }

    public int getLevel() {
        return level;
    }    

    public void setLevel(int level) {
        this.level = level;
    }
}
