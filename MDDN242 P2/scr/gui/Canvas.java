package gui;

import java.util.ArrayList;
import java.util.List;

import main.Main;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import entities.Emitter;
import entities.Entity;

public class Canvas extends PApplet{

	private static final long serialVersionUID = 4648172894076113183L;

	private PGraphics image;
	private int imageWidth, imageHeight;

	private float scale = 1;

	private int background_color;

	private List<Entity> entities;
	private Entity selectedEntity;

	public Canvas(int width, int height, int background_color){
		this.imageWidth = width;
		this.imageHeight = height;
		this.background_color = background_color;
	}

	@Override
	public void setup(){
		createNewCanvas(imageWidth, imageHeight, background_color);
		noLoop();
	}

	@Override
	public void draw(){
		updateImage();
		Main.getMainWindow().getProperties().update();
		
		scale(scale);
		image(image.get(), 0, 0);
		drawEnitities();
	}

	private void drawEnitities() {
		for(Entity ent : entities){
			ent.drawEntity(g);
		}
	}

	private void updateImage() {
		image = createGraphics(imageWidth, imageHeight, P2D);
		image.beginDraw();
		image.background(background_color);
		for(Entity e : entities){
			e.update();
			e.drawGraphics(image);
		}
		image.endDraw();
	}

	public void createNewCanvas(int w, int h, int background_color){
		imageWidth = w;
		imageHeight = h;
		this.background_color = background_color;
		entities = new ArrayList<Entity>();
		selectedEntity = null;
		hint(PConstants.DISABLE_DEPTH_MASK);
		randomSeed(1);
		Emitter.reset();
		if(!redraw) redraw();
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
		entities.add(ent);
		setSelectedEntity(ent);
		if(!redraw){
			redraw();
		}
	}

	public void removeEntity(Entity ent) {
		entities.remove(ent);
	}
	
	public Entity getSelectedEntity() {
		return selectedEntity;
	}
	
	public void setSelectedEntity(Entity entity) {
		this.selectedEntity = entity;
		Main.getMainWindow().getOutline().setSelectedEntity(entity);
		Main.getMainWindow().getProperties().setSelectedEntity(entity);
	}
	
	public void deleteSelectedEntity() {
		if(selectedEntity == null) return;
		entities.remove(selectedEntity);
		Main.getMainWindow().getOutline().remove(selectedEntity);
		setSelectedEntity(null);
		if(!redraw){
			redraw();
		}
	}

	public void selectEntityAt(int x, int y){
		for(Entity e : entities){
			if(Math.abs(e.getX() - x) <= 10 && Math.abs(e.getY() - y) <= 10){
				setSelectedEntity(e);
				return;
			}
		}
		setSelectedEntity(null);
	}

	public void exportFrame(String path, int currentFrame) {
		Main.getTime().setCurrentFrame(currentFrame);
		// TODO update image
		image.save(path);
	}
}