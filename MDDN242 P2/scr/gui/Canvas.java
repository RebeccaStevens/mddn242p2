package gui;

import processing.core.PApplet;

public class Canvas extends PApplet{
	
	private static final long serialVersionUID = 4648172894076113183L;
	
	private final int w, h;
	
	public Canvas(){
		this(640, 480);
	}

	public Canvas(int width, int height){
		this.w = width;
		this.h = height;
	}
	
	@Override
	public void setup(){
		size(w, h);
		background(0xFFFFFFFF);
	}
	
	@Override
	public void draw(){
		
	}
}