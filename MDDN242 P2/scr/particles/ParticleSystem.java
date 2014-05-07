package particles;

import entities.Emitter;
import gui.Canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.Main;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PShape;
import processing.core.PVector;

public class ParticleSystem {
	
	Canvas canvas;
	Emitter emitter;
	List<Particle> particles;
	PShape particleShape;
	PVector pPosition;
	PVector pVelocity;
	
	double startTime;
	double stopTime;
	
	PVector pGravity;
	double pLifeSpan;
	PImage pSprite;
	int pSize;
//	float pFriction;
	
	private Random random;
	private final long seed;

	public ParticleSystem(Emitter emitter) {
		this.canvas = Main.getCanvas();
		this.emitter = emitter;
		seed = (long)(Math.random() * Long.MAX_VALUE) + 1L;
		random = new Random(seed);
		
		particles = new ArrayList<Particle>();
		pSprite = canvas.loadImage("data/particle.png");
		
		pGravity = new PVector(0, 0);
		pPosition = new PVector(emitter.getX(), emitter.getY());
		pVelocity = new PVector(0, 0);
	}

	public void draw(PGraphics g) {
		if(particleShape == null) return;
		if(Main.getTime().getCurrentTime() < startTime) return;
		if(Main.getTime().getCurrentTime() >= stopTime) return;
		g.shape(particleShape);
	}

	public void update() {
		startTime = emitter.getStartEmitFrame() / (double)Main.getTime().getFramesPerSecond();
		if(Main.getTime().getCurrentTime() < startTime) return;
		stopTime = emitter.getStopEmitFrame() / (double)Main.getTime().getFramesPerSecond();
		if(Main.getTime().getCurrentTime() >= stopTime) return;
		
		random.setSeed(seed);
		particles.clear();
		pLifeSpan = emitter.getParticleLifeSpan();
		pVelocity.set(emitter.getVelocityX(), emitter.getVelocityY());
		pGravity.set(emitter.getGravityX(), emitter.getGravityY());
		pSize = emitter.getParticleSize();
		
		int n = (int) (emitter.getEmissionRate() * (Main.getTime().getCurrentTime() - startTime) + 1);
		for(int i=0; i<n; i++){
			double birth = random.nextDouble() * (Main.getTime().getCurrentTime() - startTime) + startTime;
			Particle p = new Particle(this, birth, pLifeSpan);
			
			PVector rand;
			rand = new PVector(random.nextFloat() * 10, 0);
			rand.rotate(PConstants.TAU * random.nextFloat());
			p.setPosition(rand);
			
			rand = new PVector(random.nextFloat() * 100, 0);
			rand.rotate(PConstants.TAU * random.nextFloat());
			p.setVelocity(PVector.add(pVelocity, rand));
			
			particles.add(p);
		}
		
		List<Particle> dead = new ArrayList<Particle>();
		for (Particle p : particles) {
			if(!p.update()){
				dead.add(p);
			}
		}
		particles.removeAll(dead);
		
		particleShape = canvas.createShape(PShape.GROUP);
		for(Particle p : particles){
			particleShape.addChild(p.getShape());
		}
	}
}