package entities;

import processing.core.PGraphics;

public abstract class Entity {
	
	private int x, y;

	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	public abstract void draw(PGraphics g);

	public abstract void update();
}
