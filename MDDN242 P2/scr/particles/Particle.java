package particles;

import main.Main;
import gui.Canvas;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PShape;
import processing.core.PVector;

class Particle {

	private Canvas canvas;

	private PShape part;
	private PVector position;
	private PVector velocity;
	private float lifeSpan;
	private double timeOfBirth;
	
	private ParticleSystem sys;

	Particle(ParticleSystem ps, double timeOfBirth, float lifeSpan) {
		sys = ps;
		canvas = sys.getCanvas();
		this.timeOfBirth = timeOfBirth;
		this.lifeSpan = canvas.random(0, lifeSpan);
		
		position = new PVector();
		velocity = new PVector();
		part = canvas.createShape(PConstants.ELLIPSE, 0, 0, 10, 10);
//		part = canvas.createShape();
//		part.beginShape(PConstants.QUAD);
//		part.noStroke();
//		part.texture(sprite);
//		part.normal(0, 0, 1);
//		part.vertex(-partSize/2, -partSize/2, 0, 0);
//		part.vertex(+partSize/2, -partSize/2, sprite.width, 0);
//		part.vertex(+partSize/2, +partSize/2, sprite.width, sprite.height);
//		part.vertex(-partSize/2, +partSize/2, 0, sprite.height);
//		part.endShape();
	}
	
	void setPosition(PVector pos){
		this.position = pos.get();
	}
	
	void setVelocity(PVector veloc){
		this.velocity = veloc.get();
	}

	PShape getShape() {
		return part;
	}

	boolean isAlive() {
		double time = Main.getTime().getCurrentTime();
		return		time >= timeOfBirth
				&&	time <  timeOfBirth + lifeSpan;
	}

	boolean update() {
		if(!isAlive()){
			return false;
		}

		double time = Main.getTime().getCurrentTime() - timeOfBirth;
		position.set(PVector.add(PVector.mult(velocity, (float) time), PVector.mult(sys.getGravity(), (float) (time * time * 0.5))));

		part.setTint(0x00FFFFFF | ((int)(PApplet.map(lifeSpan, sys.getPLiftSpan(), 0, 0xFF, 0)) << 24));
		part.translate(velocity.x, velocity.y);
		
		return true;
	}
}