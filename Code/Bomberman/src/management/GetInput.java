package management;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class GetInput {
	private List<String> fileNames;
	private List<String> map;
	private int mapNumber;
	private int weight;
	private int height;
	
	public GetInput(int mapNum){
		fileNames = new LinkedList<>();
		map = new LinkedList<>();
		mapNumber = mapNum;
		weight  = -1;
		height = -1;
		insertFromFile();
	}
	private void getFileName(){
	}
    private void insertFromFile(){
    	String fileName = "src/map/map"+ mapNumber+".txt";/*fileNames.get(mapNumber);*/
        try{
            FileReader fileReader = new FileReader(fileName);        
            BufferedReader bReader = new BufferedReader(fileReader);
            //
            String line = null;
            Scanner sc = new Scanner(bReader);
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
    public void printMap() {
    	System.out.println("Your GameMap");
    	for(String var : map) {
    		System.out.println(var);
    	}
    }

	public List<String> getMap() {
		return map;
	}
	public int getWeight() {
		return weight;
	}
	public int getHeight() {
		return height;
	}
}
