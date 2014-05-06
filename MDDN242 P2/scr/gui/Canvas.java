package gui;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import main.Input;
import main.Main;
import processing.core.PApplet;
import processing.core.PGraphics;
import entities.Entity;

public class Canvas extends PApplet{

	private static final long serialVersionUID = 4648172894076113183L;

	private PGraphics image;
	private int imageWidth, imageHeight;

	private float scale = 1;

	private int background_color;

	private List<Entity> entities;

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
		image.beginDraw();
		image.background(background_color);
		for(Entity e : entities){
			e.update();
			e.draw(image);
			System.out.println(1);
		}
		image.rect(50, 50, 300, 300);
		image.endDraw();

		scale(scale);
		image(image.get(), 0, 0);
	}

	public void createNewCanvas(int w, int h, int background_color){
		image = createGraphics(w, h);
		imageWidth = image.width;
		imageHeight = image.height;
		this.background_color = background_color;
		entities = new ArrayList<Entity>();
		randomSeed(1);
	}

	@Override
	public void size(int w, int h) {
		super.size(w, h, sketchRenderer());
	}

	@Override
	public String sketchRenderer() {
		return P2D;
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

	public void addEntity(Entity ent) {
		System.out.println("E");
		entities.add(ent);
		if(!redraw){
			redraw();
		}
	}

	public void removeEntity(Entity ent) {
		entities.remove(ent);
	}
}