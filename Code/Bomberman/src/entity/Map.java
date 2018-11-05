package entity;

import java.util.LinkedList;
import java.util.List;

import application.GetInput;
import entity.*;

import javafx.scene.layout.AnchorPane;

public class Map extends AnchorPane{
	private GetInput get;
	private List<Wall> walls;
	
	public Map(){
		super();
		InitializeComponent();
	}
	
	private void InitializeComponent() {
		get = new GetInput(1);
		walls = new LinkedList<>();
		
		List<String> map = get.getMap();
		int l = 1;
		for(String line : map) {
			for(int i=0;i<line.length();i++) {
				switch(line.charAt(i)) {
					case '#':
						Wall w = new Wall();
						walls.add(w);
						break;
					case '*': 
						break;
					case 'x': 
						break;
					case 'p': 
						break;
					case '1': 
						break;
					case '2': 
						break;
					case 'b': 
						break;
					case 'f': 
						break;
					case 's': 
						break;
				}
			}
		}
		
	}
	
	private void addWall() {
		
	}
}
