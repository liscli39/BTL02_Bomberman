/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman2.entities;

import bomberman2.entities.barriers.Brick;
import bomberman2.entities.barriers.Portal;
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
import bomberman2.graphics.Graphic;
import bomberman2.movement.Collision;
import bomberman2.movement.InputKey;
import java.util.Iterator;
import java.util.List;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Liscli
 */
public class GameMap extends AnchorPane{
    public GameMap(double sceneW, double sceneH){
        super();
        this.setLayoutX(0);
        this.setLayoutY(0);
        this.level = 1;
        this.isPvsP = false;
        this.sceneH = sceneH;
        this.sceneW = sceneW;
        this.isMusic = true;
        this.isSound = true;
        this.playerLife = 0;
        this.reset();
        this.setFocusTraversable(true);
    }
    public final void reset(){
        this.setTranslateX(0);
        this.setTranslateY(0);      
        this.getChildren().clear();
        Player.currentId = 1;
        
        this.graphic = new Graphic(this.level);
        this.setWidth(Graphic.weight);
        this.setHeight(Graphic.height);
        this.graphic.getEntities().forEach((Entity e)->{
            this.getChildren().add(e);
        });
 
        this.enemy = this.graphic.getEnemys().size();
        if (this.playerLife == 0) this.playerLife = this.graphic.getPlayers().get(0).getNumLife();
        else this.graphic.getPlayers().get(0).setNumLife(this.playerLife);
        this.setStyle("-fx-background-color: #FFC107; -fx-background-radius: 10");
       
    }
    
    public int update(){
//         Graphic.printCharMap();

        if(this.isPvsP){
            boolean p1 = (this.graphic.getPlayers().get(0).isDeath());
            boolean p2 = (this.graphic.getPlayers().get(1).isDeath());
            if(p1 && p2) return 10;
            else if(p1) return 12;
            else if(p2) return 11;
            this.playerLife = 0;
        }else {
            if(this.graphic.getPlayers().get(0).isDeath()) {
                this.playerLife = 0;
                return 3;
            }
            else if(this.graphic.getPlayers().get(0).isFakeDeath()) return 2;
        }
        
        this.playerMovement();
        this.playerEatItem();
        this.bombExplosion();      
        this.flameDestroy();
        this.enemyMovement();
        
        this.openPortal();
        
        this.playerLife = this.graphic.getPlayers().get(0).getNumLife();
        this.enemy = this.graphic.getEnemys().size();

        return this.nextLevel();
    }

    private void enemyMovement(){
        this.graphic.getBallooms().forEach(e->{
            e.dumpMove();
        });
        this.graphic.getOneals().forEach(o->{
            o.basicAIMove(this.graphic.getPlayers().get(0));
        });
    }
    private void playerMovement(){
        this.graphic.getPlayers().forEach(p->{
            p.undead();
            InputKey.keyBoard.forEach((k,v)->{
                boolean canMove,canPut;
                switch(k){
                    case W:
                        canMove = v && p.getPlayerId()==1 &&
                        Collision.canMove(p, 1, this.graphic.getStaticEntities()) &&
                        !Collision.hitBomb(p, 1);
                        p.moveUp(canMove);
                        this.dragScreen(1,p,canMove);
                        break;
                    case S:
                        canMove = v && p.getPlayerId()==1 &&
                        Collision.canMove(p, 2, this.graphic.getStaticEntities())&&
                        !Collision.hitBomb(p, 2);
                        p.moveDown(canMove);
                        this.dragScreen(2,p,canMove);
                        break;
                    case D: 
                        canMove = v && p.getPlayerId()==1 &&
                        Collision.canMove(p, 3, this.graphic.getStaticEntities())&&
                        !Collision.hitBomb(p, 3);
                        p.moveRight(canMove);
                        this.dragScreen(3,p,canMove);
                        break;
                    case A: 
                        canMove = v && p.getPlayerId()==1 &&
                        Collision.canMove(p, 4, this.graphic.getStaticEntities())&&
                        !Collision.hitBomb(p, 4);
                        p.moveLeft(canMove);
                        this.dragScreen(4,p,canMove);
                        break;
                    case SPACE:
                        canPut = v && p.getPlayerId()==1 &&
                        Collision.canPutBom(p);
                        if(canPut){
                            Bomb b = p.putBomb();
                            graphic.getBombs().add(b);
                            this.getChildren().add(b);
                            p.toFront();
                        }
                        break;
                        
                    case UP:
                        canMove = (isPvsP ? p.getPlayerId()==2 : p.getPlayerId()==1) && v &&
                        Collision.canMove(p, 1, this.graphic.getStaticEntities())&&
                        !Collision.hitBomb(p,1);
                        p.moveUp(canMove);
                        this.dragScreen(1,p,canMove);
                        break;
                    case DOWN:
                        canMove = (isPvsP ? p.getPlayerId()==2 : p.getPlayerId()==1) && v &&
                        Collision.canMove(p, 2, this.graphic.getStaticEntities())&&
                        !Collision.hitBomb(p, 2);
                        p.moveDown(canMove);
                        this.dragScreen(2,p,canMove);
                        break;
                    case RIGHT:
                        canMove = (isPvsP ? p.getPlayerId()==2 : p.getPlayerId()==1) && v &&
                        Collision.canMove(p, 3, this.graphic.getStaticEntities())&&
                        !Collision.hitBomb(p, 3);
                        p.moveRight(canMove);
                        this.dragScreen(3,p,canMove);
                        break;
                    case LEFT:
                        canMove = (isPvsP ? p.getPlayerId()==2 : p.getPlayerId()==1) && v &&
                        Collision.canMove(p, 4, this.graphic.getStaticEntities())&&
                        !Collision.hitBomb(p, 4);
                        p.moveLeft(canMove);
                        this.dragScreen(4,p,canMove);
                        break;
                    case ENTER:
                        canPut = v && p.getPlayerId()==2&&
                        Collision.canPutBom(p);
                        if(canPut){
                            Bomb b = p.putBomb();
                            graphic.getBombs().add(b);
                            this.getChildren().add(b);
                            p.toFront();
                        }
                        break;
                    default: break;
                }
            });
        });
    }
    private void dragScreen(int direction, Player p, boolean move){
        if(!move) return;
        
        double pX = p.getTranslateX();
        double pY = p.getTranslateY();
        
        if(direction == 3 && pX > this.sceneW/2 && this.getTranslateX()>this.sceneW-Graphic.weight){
            this.setTranslateX(this.getTranslateX()-p.getSpeed());        
        }//go right
        if(direction == 4 && pX < Graphic.weight - this.sceneW/2 && this.getTranslateX()<0){
            this.setTranslateX(this.getTranslateX()+p.getSpeed());
        }//go left
        if(direction == 2 && pY > this.sceneH/2 && this.getTranslateY()> this.sceneH-Graphic.height){
            this.setTranslateY(this.getTranslateY()-p.getSpeed());
        }//go down
        if(direction == 1 && pY < Graphic.height - this.sceneH/2 && this.getTranslateY()<0){
            this.setTranslateY(this.getTranslateY()+p.getSpeed());
        }//go up
    }
    private void playerEatItem(){
        Iterator<Item> ii = graphic.getItems().listIterator();
        while(ii.hasNext()){
            Item i = ii.next();
            if(i.isItemVisible()){
                for(Player p : graphic.getPlayers()){
                    if(Collision.canEatItem(p, i)){
                        if(i instanceof BombItem){
                            p.bombUp();
                        }else if(i instanceof FlameItem){
                            p.powerUp();
                        }else if(i instanceof SpeedItem){
                            p.speedUp();
                        }
                        Graphic.cmap[i.getEnityX()][i.getEnityY()]=' ';
                        this.getChildren().remove(i);
                        ii.remove();
                        break;
                    }
                }
            }
        }
    }
    private void bombExplosion(){
        // Explosion
        Iterator<Bomb> ib = graphic.getBombs().listIterator();
        while(ib.hasNext()){
            boolean isExplosive = false;
            Bomb b = ib.next();   
            for(Flame f: graphic.getFlames()){
                if(Collision.isCollision(b, f)){
                    isExplosive = true;
                    break;
                }
            }
            if(!b.countDown() || isExplosive){
                List<Flame> burn = b.explosive();
                // burn
                graphic.getFlames().addAll(burn);
                burn.forEach(f->{this.getChildren().add(f);});
                // return bomb
                graphic.getPlayers().forEach(p->{
                    if(p.getPlayerId()==b.getpId()){
                        p.bombUp();
                    }
                });
                // remove
                Graphic.cmap[b.getEnityX()][b.getEnityY()] = ' ';
                this.getChildren().remove(b);
                ib.remove();
            }
        }
    }
    private void flameDestroy(){
        Iterator<Flame> ifl = graphic.getFlames().listIterator();
        while(ifl.hasNext()){
            Flame f = ifl.next();
            if(!f.burning()){
                // destroy item
                Iterator<Item> ii = graphic.getItems().listIterator();
                while(ii.hasNext()){
                    Item i = ii.next();
                    if(Collision.isCollision(f, i)){
                        if(i.isItemVisible()){
                            this.getChildren().remove(i);
                            ii.remove();
                        }else {
                            i.show();
                            Graphic.cmap[i.getEnityX()][i.getEnityY()]=' ';
                            this.graphic.getStaticEntities().remove(i);
                        }
                    }
                }
                // destroy brick
                Iterator<Brick> ibr = graphic.getBricks().listIterator();
                while(ibr.hasNext()){
                    Brick br = ibr.next();
                    if(Collision.isCollision(br, f)){
                        Graphic.cmap[br.getEnityX()][br.getEnityY()]=' ';

                        this.graphic.getStaticEntities().remove(br);
                        this.getChildren().remove(br);
                        ibr.remove();
                    }
                }
                //visible portal
                graphic.getPortals().forEach(p->{
                    if(Collision.isCollision(p, f) && !p.isIsVisible()){
                        p.show();
                        this.graphic.getStaticEntities().remove(p);
                    }
                }); 
                //kill enemy
                Iterator<Enemy> ie = graphic.getEnemys().listIterator();
                while(ie.hasNext()){
                    Enemy e = ie.next();
                    if(Collision.isCollision(e, f)){
                        Graphic.cmap[e.getEnityX()][e.getEnityY()]=' ';
                        
                        if(e instanceof Balloom) {
                            Balloom bl = (Balloom)e;
                            graphic.getBallooms().remove(bl);
                        }
                        if(e instanceof Oneal) {
                            Oneal onl = (Oneal)e;
                            graphic.getOneals().remove(onl);
                        }
                        this.getChildren().remove(e);
                        ie.remove();
                    }
                }
                //kill player
                Iterator<Player> ipl = graphic.getPlayers().listIterator();
                while(ipl.hasNext()){
                    Player pl = ipl.next();
                    if(Collision.isCollision(pl, f)){
                        if(this.isPvsP){
                            pl.setDeath(true);
                        }else
                        if(pl.getNumLife()>1){
                            pl.riseUp();
                        }else{
                            pl.setDeath(true);
                        }
                    }
                }
                // turn off the fire
                this.getChildren().remove(f);
                ifl.remove();
            }
        }
    }
    private void openPortal(){
        if(this.graphic.getEnemys().isEmpty()){
            this.graphic.getPortals().forEach(p->{if(p.isIsVisible())p.open();});
        }
    }
    
    public int nextLevel(){
        if(!isPvsP){
            for(Portal p : this.graphic.getPortals()){
                if(Collision.isCollision(this.graphic.getPlayers().get(0),p) && p.isIsOpen()){
                    this.level ++;
                    this.reset();
                    this.graphic.getPlayers().get(0).setNumLife(playerLife );
                    return 1;
                }
            }
        }
        return 0;
    }
    public void reSize(double sceneW, double sceneH){
          this.sceneW = sceneW;
          this.sceneH = sceneH;
    }
    
    public Integer getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    
    public boolean isIsPvsP() {
        return isPvsP;
    }
    public void setIsPvsP(boolean isPvsP) {
        this.isPvsP = isPvsP;
        if(isPvsP){
            this.level = 11;
            this.reset();
        }
    }
    
    public boolean isIsGameOver() {
        return isGameOver;
    }
    public void setIsGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }
    public Integer getEnemy() {
        return enemy;
    }

    public Integer getPlayerLife() {
        return playerLife;
    }

    public boolean isIsSound() {
        return isSound;
    }

    public boolean isIsMusic() {
        return isMusic;
    }

    public void setIsSound(boolean isSound) {
        this.isSound = isSound;
    }

    public void setIsMusic(boolean isMusic) {
        this.isMusic = isMusic;
    }
    public void playerRiseUp(){
        this.graphic.getPlayers().get(0).setFakeDeath(false);
    }
    
    private Graphic graphic;
    private Integer level;
    private boolean isPvsP;
    private boolean isGameOver;
    private double sceneW;
    private double sceneH;
    private Integer enemy;
    private Integer playerLife;
    private boolean isSound;
    private boolean isMusic;
}
