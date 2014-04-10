package main;

import gui.Button;
import processing.core.PApplet;

public class Main extends PApplet{
	
	private static final long serialVersionUID = 4648172894076113183L;


	public Main(){
		
	}
	
	public void setup(){
		size(640, 480);
		new Button();
	}
	
	public void draw(){
		
	}

	public static void main(String[] args){
		PApplet.main(Main.class.getName());
	}
}