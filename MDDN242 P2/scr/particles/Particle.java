package particles;

import main.Main;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PShape;
import processing.core.PVector;

class Particle {
	private PShape part;
	private PVector position;
	private PVector velocity;
	private double lifeSpan;
	private double timeOfBirth;
	
	private ParticleSystem sys;

	Particle(ParticleSystem ps, double timeOfBirth, double lifeSpan) {
		sys = ps;
		this.timeOfBirth = timeOfBirth;
		this.lifeSpan = lifeSpan;
		
		position = new PVector();
		velocity = new PVector();
//		part = sys.canvas.createShape(PConstants.ELLIPSE, 0, 0, 10, 10);
		
		int partSize = sys.pSize;
		
		part = sys.canvas.createShape();
		part.beginShape(PConstants.QUAD);
		part.noStroke();
		part.texture(sys.pSprite);
		part.normal(0, 0, 1);
		part.vertex(-partSize/2, -partSize/2, 0, 0);
		part.vertex(+partSize/2, -partSize/2, sys.pSprite.width, 0);
		part.vertex(+partSize/2, +partSize/2, sys.pSprite.width, sys.pSprite.height);
		part.vertex(-partSize/2, +partSize/2, 0, sys.pSprite.height);
		part.endShape();
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
		position.set(PVector.add(PVector.mult(velocity, (float) time), PVector.mult(sys.pGravity, (float) (time * time * 0.5))));
		
		double timeLeft = lifeSpan - (Main.getTime().getCurrentTime() - timeOfBirth);
		int v = ((int)(PApplet.map((float) timeLeft, (float) sys.pLifeSpan, 0, 0xFF, 0)) << 24) |
				sys.emitter.getParticleRed() << 16|
				sys.emitter.getParticleGreen() << 8|
				sys.emitter.getParticleBlue();
		
		part.setTint(v);
		part.translate(position.x, position.y);
		
		return true;
	}
}