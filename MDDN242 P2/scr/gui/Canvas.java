package gui;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

import main.Input;
import main.Main;
import processing.core.PApplet;
import processing.core.PGraphics;

public class Canvas extends PApplet{
	
	private static final long serialVersionUID = 4648172894076113183L;

	private PGraphics image;
	private int imageWidth, imageHeight;

	private float scale = 1;

	private int background_color;

	public Canvas(int width, int height, int background_color){
		this.imageWidth = width;
		this.imageHeight = height;
		this.background_color = background_color;
	}
	
	@Override
	public void setup(){
		// remove the default input listeners (for efficiency)
		for(MouseListener l : getMouseListeners()) removeMouseListener(l);
		for(MouseMotionListener l : getMouseMotionListeners()) removeMouseMotionListener(l);
		for(MouseWheelListener l : getMouseWheelListeners()) removeMouseWheelListener(l);
		for(KeyListener l : getKeyListeners()) removeKeyListener(l);
		
		Input input = Main.getInput();
		addMouseListener(input);
		addMouseMotionListener(input);
		addMouseWheelListener(input);
		addKeyListener(input);
		
		createNewCanvas(imageWidth, imageHeight, background_color);
		noLoop();
	}
	
	@Override
	public void draw(){
		System.out.println("draw");
		image.beginDraw();
		image.background(background_color);
		//TODO
		image.endDraw();
		
		scale(scale);
		image(image.get(), 0, 0);
	}
	
	public void createNewCanvas(int w, int h, int background_color){
		image = createGraphics(w, h);
		imageWidth = image.width;
		imageHeight = image.height;
		this.background_color = background_color;
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