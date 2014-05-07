package entities;

import java.awt.Point;
import java.util.List;

import main.Main;
import processing.core.PConstants;
import processing.core.PGraphics;

public abstract class Entity {
	
	private int x, y;
	private String name;

	public Entity(int x, int y, String defaultName) {
		this.x = x;
		this.y = y;
		this.name = defaultName;
		
		Main.getCanvas().setSelectedEntity(this);
		Main.getMainWindow().getOutline().add(this);
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void move(Point delta) {
		move(delta.x, delta.y);
	}
	
	public void move(int x, int y) {
		this.x += x;
		this.y += y;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public abstract List<Attribute<?>> getAttributes();

	public final void drawEntity(PGraphics g){
		g.pushStyle();
		g.rectMode(PConstants.CENTER);
		g.noFill();
		int s = (this == Main.getCanvas().getSelectedEntity()) ? g.color(255, 0, 0) : g.color(0, 220, 240);
		g.stroke(s);
		g.strokeWeight(1/Main.getCanvas().getScale());
		
		drawEntityImpl(g);
		
		g.popStyle();
	}
	
	protected abstract void drawEntityImpl(PGraphics g);
	
	public abstract void drawGraphics(PGraphics g);

	public abstract void update();
}
