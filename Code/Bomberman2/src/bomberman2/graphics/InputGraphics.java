/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman2.graphics;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Liscli
 */
public class InputGraphics{
    
    public InputGraphics(){
        map = new LinkedList<>();
        weight  = -1;
        height = -1;
        mapNumber = -1;
    }
    
    private void insertFromFile(){
    	String fileName = "src/bomberman2/map/map"+ mapNumber+".txt";/*fileNames.get(mapNumber);*/
        try(FileReader fileReader = new FileReader(fileName);        
            BufferedReader bReader = new BufferedReader(fileReader);
            Scanner sc = new Scanner(bReader)){
            //
            String line;
            sc.nextInt();
            height = sc.nextInt();
            weight = sc.nextInt();
            sc.nextLine();
            for(int i=0;i<height;i++) {
            	line = sc.nextLine();
            	map.add(line);
            }
            sc.close();
            bReader.close();
            fileReader.close();
        }catch (FileNotFoundException ex) {
            //
            System.out.println("Unable to open file '" + fileName + "'");          
        }catch (IOException ex){
            //
            System.out.println("Error reading file '" + fileName + "'");          
        }
    }
        
    public List<String> getStringMap(int level){
        if(level != this.mapNumber){
            this.map.clear();
            this.mapNumber = level;
            this.insertFromFile();
        }
        return map;
    }
    public char[][] getCharMap(){
        char[][] mapc = new char[weight][height];
        int j = 0;
        for(String r : map){
            for(int i=0;i<r.length();i++){
                mapc[i][j] = r.charAt(i);
            }
            j++;
        }
        return mapc;
    }
    
    public void printMap() {
    	System.out.println("GameMap number " + this.mapNumber);
        map.forEach((var) -> {
            System.out.println(var);
        });
        System.out.println("_____________________");
    }
    public int getLevel() {
        return mapNumber;
    }

    public static int getWeight() {
        return weight;
    }

    public static int getHeight() {
        return height;
    }
    
    private final List<String> map;
    private int mapNumber;
    private static int weight;
    private static int height;
}
