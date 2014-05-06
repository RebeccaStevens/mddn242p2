package particles;

import entities.Emitter;
import gui.Canvas;

import java.util.ArrayList;
import java.util.List;

import main.Main;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PShape;
import processing.core.PVector;

public class ParticleSystem {
	
	private Canvas canvas;
	
	private Emitter emitter;
	
	private List<Particle> particles;
	private PShape particleShape;
	private PVector pPosition;
	private PVector pVelocity;
	private PVector pGravity;
	private float pLifeSpan;
//	private float pFriction;

	public ParticleSystem(Emitter emitter) {
		this.canvas = Main.getCanvas();
		this.emitter = emitter;
		
		particles = new ArrayList<Particle>();
		particleShape = canvas.createShape(PShape.GROUP);
		
		pGravity = new PVector(0, 0);
		pPosition = new PVector(emitter.getX(), emitter.getY());
		pVelocity = new PVector(0, 0);
		pLifeSpan = 1000;
	}

	public void draw(PGraphics g) {
		g.shape(particleShape);
	}

	public void update() {
		int n = emitter.getEmitRate() / Main.getTime().getFramesPerSecond();
		for(int i=0; i<n; i++){
			Particle p = new Particle(this, Main.getTime().getCurrentTime(), pLifeSpan);
			p.setPosition(pPosition);
			PVector rand = new PVector(canvas.random(100), 0);
			rand.rotate(PConstants.TAU * canvas.random(0, 1));
			p.setVelocity(PVector.add(pVelocity, rand));
			
			particles.add(p);
			particleShape.addChild(p.getShape());
		}
		
		List<Particle> dead = new ArrayList<Particle>();
		for (Particle p : particles) {
			if(!p.update()){
				dead.add(p);
				particleShape.removeChild(particleShape.getChildIndex(p.getShape()));
			}
		}
		particles.removeAll(dead);
	}

	PVector getGravity() {
		return pGravity.get();
	}

	float getPLiftSpan() {
		return pLifeSpan;
	}

	Canvas getCanvas() {
		return canvas;
	}
}