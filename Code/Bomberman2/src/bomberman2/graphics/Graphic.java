/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman2.graphics;

import bomberman2.entities.Entity;
import java.util.List;

/**
 * 
 * @author Liscli
 */
public class Graphic extends Level{
    public Graphic(int level){
        super(level);
        Graphic.entitiesH = 40;
        Graphic.entitiesW = 40;
        init(level);
    }
    public Graphic(int level, double w, double h){
        super(level);
        Graphic.entitiesH = w;
        Graphic.entitiesW = h;
        init(level);
    }
    public Graphic(int level, double w, double h,String theme){
        super(level);
        Graphic.entitiesH = w;
        Graphic.entitiesW = h;
        init(level);
        Graphic.theme.setTheme(theme);
    }
    private void init(int level){
        this.input = new InputGraphics();
        this.InitializeComponent(input.getStringMap(level));
        Graphic.cmap = this.input.getCharMap();
        this.themeId = 0;
//        this.input.printMap();
        Graphic.theme = new Theme();
        Graphic.theme.setTheme("noel");  
        Graphic.weight = InputGraphics.getWeight() * Graphic.entitiesW;
        Graphic.height = InputGraphics.getHeight() * Graphic.entitiesH;
        
        this.entities.forEach(e->{
            e.setFitWidth(entitiesW);
            e.setFitHeight(entitiesH);
            e.setEntityImage(theme);
            e.setTranslateX(e.getEnityX()*Graphic.entitiesW);
            e.setTranslateY(e.getEnityY()*Graphic.entitiesH);
        });
    }
    public void changeEntitiesSize(double w, double h){
        Graphic.entitiesW = w;
        Graphic.entitiesH = h;
        
        this.entities.forEach(e->{
            e.setFitWidth(entitiesW);
            e.setFitHeight(entitiesH);
            e.setTranslateX(e.getEnityX()*Graphic.entitiesW);
            e.setTranslateX(e.getEnityY()*Graphic.entitiesH);
        });
    }
    
    public void setTheme(String theme){
        Graphic.theme.setTheme(theme);
    }

    public List<Entity> getEntities(){
        return this.entities;
    }
    public static void printCharMap(){
        for(int i=0;i<InputGraphics.getHeight();i++){
            for(int j=0;j<InputGraphics.getWeight();j++){
                System.out.print(cmap[j][i]);
            }System.out.println();
        }
        System.out.println("------------------");
    }
    public static void changeTheme(){
        if(Graphic.themeId < 1){
            Graphic.theme.setTheme(themes[++Graphic.themeId]);
        }else {
            Graphic.themeId = 0;
            Graphic.theme.setTheme(themes[Graphic.themeId]);
        }
    }
    private InputGraphics input;
    public static double entitiesW;
    public static double entitiesH;
    public static double weight;
    public static double height;
    public static Theme theme;
    public static char[][] cmap;
    public static String[] themes = {"classic","noel"};
    private static int themeId;
}