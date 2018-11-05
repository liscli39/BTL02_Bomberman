package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class GetInput {
	private List<String> fileNames;
	private List<String> map;
	private int mapNumber;
	
	public GetInput(int mapNum){
		fileNames = new LinkedList<>();
		map = new LinkedList<>();
		mapNumber = mapNum;
		insertFromFile();
	}
	private void getFileName(){
	}
    private void insertFromFile(){
    	String fileName = "src/map/map0.txt";/*fileNames.get(mapNumber);*/
        try{
            FileReader fileReader = new FileReader(fileName);        
            BufferedReader bReader = new BufferedReader(fileReader);
            //
            String line = null;
            while((line = bReader.readLine()) != null){
            	map.add(line);
            }
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
    	System.out.println("Your Map");
    	for(String var : map) {
    		System.out.println(var);
    	}
    }
	/**
	 * @return the map
	 */
	public List<String> getMap() {
		return map;
	}
	
    
}
