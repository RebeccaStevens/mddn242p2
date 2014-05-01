package gui;

import processing.core.PApplet;
import processing.core.PGraphics;

public class Canvas extends PApplet{
	
	private static final long serialVersionUID = 4648172894076113183L;

	private PGraphics image;
	private int imageWidth, imageHeight;

	private float scale = 1;

	public Canvas(int width, int height){
		this.imageWidth = width;
		this.imageHeight = height;
	}
	
	@Override
	public void setup(){
		createNewCanvas(imageWidth, imageHeight);
	}
	
	@Override
	public void draw(){
		image.beginDraw();
		//TODO
		image.endDraw();
		
		scale(scale);
		image(image.get(), 0, 0);
	}
	
	public void createNewCanvas(int w, int h){
		image = createGraphics(w, h);
		imageWidth = image.width;
		imageHeight = image.height;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}
}