package entities;

import particles.ParticleSystem;
import processing.core.PGraphics;

public class Emitter extends Entity{

	private ParticleSystem system;
	
	public Emitter(int x, int y) {
		super(x, y);
		system = new ParticleSystem(this);
	}

	public int getEmitRate() {
		return 24;
	}

	@Override
	public void draw(PGraphics g) {
		system.draw(g);
	}

	@Override
	public void update() {
		system.update();
	}
}
