package entities;

import java.util.ArrayList;
import java.util.List;

import main.Main;
import particles.ParticleSystem;
import processing.core.PGraphics;

public class Emitter extends Entity{

	private static int num = 1;
	private ParticleSystem system;
	
	private int startEmitFrame = 1;
	private int stopEmitFrame = 1000;
	private float emissionRate = 24;
	
	private int particleSize = 10;
	private int particleLifeSpan = Main.getTime().getFramesPerSecond();
	private int particleVX;
	private int particleVY;
	private int particleGX;
	private int particleGY;
	private int particleRed = 0xFF;
	private int particleGreen = 0xFF;
	private int particleBlue = 0xFF;
	
	public Emitter(int x, int y) {
		super(x, y, "Emitter "+num++);
		system = new ParticleSystem(this);
	}

	public int getStartEmitFrame() {
		return startEmitFrame;
	}
	
	public void setStartEmitFrame(int startEmitFrame) {
		this.startEmitFrame = startEmitFrame;
	}

	public int getStopEmitFrame() {
		return stopEmitFrame;
	}
	
	public void setStopEmitFrame(int stopEmitFrame) {
		this.stopEmitFrame = stopEmitFrame;
	}

	public float getEmissionRate() {
		return emissionRate;
	}
	
	public void setEmissionRate(float emissionRate) {
		this.emissionRate = emissionRate;
	}
	
	public int getParticleSize() {
		return particleSize;
	}
	
	public int getParticleRed() {
		return particleRed;
	}
	
	public int getParticleGreen() {
		return particleGreen;
	}
	
	public int getParticleBlue() {
		return particleBlue;
	}
	
	public int getParticleLifeSpan() {
		return particleLifeSpan / Main.getTime().getFramesPerSecond();
	}

	public float getVelocityX() {
		return particleVX;
	}

	public float getVelocityY() {
		return particleVY;
	}

	public float getGravityX() {
		return particleGX;
	}

	public float getGravityY() {
		return particleGY;
	}
	
	public List<Attribute<?>> getAttributes(){
		List<Attribute<?>> attributes = new ArrayList<Attribute<?>>();
		attributes.add(new Attribute<String>(){
			@Override public String getKey() { return "Name"; }
			@Override public String getValue() { return getName(); }
			@Override public void valueChange(String newValue) {
				setName(newValue);
			}});
		attributes.add(new Attribute<Integer>(){
			@Override public String getKey() { return "X"; }
			@Override public Integer getValue() { return getX(); }
			@Override public void valueChange(String newValue) {
				try{
					setX(Integer.parseInt(newValue));
				}catch(NumberFormatException e){
					
				}
			}});
		attributes.add(new Attribute<Integer>(){
			@Override public String getKey() { return "Y"; }
			@Override public Integer getValue() { return getY(); }
			@Override public void valueChange(String newValue) {
				try{
					setY(Integer.parseInt(newValue));
				}catch(NumberFormatException e){
					
				}
			}});
		attributes.add(new Attribute<Integer>(){
			@Override public String getKey() { return "Start Emit Frame"; }
			@Override public Integer getValue() { return new Integer((int) getStartEmitFrame()); }
			@Override public void valueChange(String newValue) {
				try{
					setStartEmitFrame(Integer.parseInt(newValue));
				}catch(NumberFormatException e){
					
				}
			}});
		attributes.add(new Attribute<Integer>(){
			@Override public String getKey() { return "Stop Emit Frame"; }
			@Override public Integer getValue() { return new Integer((int) getStopEmitFrame()); }
			@Override public void valueChange(String newValue) {
				try{
					setStopEmitFrame(Integer.parseInt(newValue));
				}catch(NumberFormatException e){
					
				}
			}});
		attributes.add(new Attribute<Integer>(){
			@Override public String getKey() { return "Emission Rate"; }
			@Override public Integer getValue() { return new Integer((int) getEmissionRate()); }
			@Override public void valueChange(String newValue) {
				try{
					setEmissionRate(Math.max(Math.min(Integer.parseInt(newValue), 1000), 1));
				}catch(NumberFormatException e){
					
				}
			}});
		attributes.add(new Attribute<Integer>(){
			@Override public String getKey() { return "Particle Life Span"; }
			@Override public Integer getValue() { return particleLifeSpan; }
			@Override public void valueChange(String newValue) {
				try{
					particleLifeSpan = Math.max(Integer.parseInt(newValue), 1);
				}catch(NumberFormatException e){
					
				}
			}});
		attributes.add(new Attribute<Integer>(){
			@Override public String getKey() { return "Particle Velocity X"; }
			@Override public Integer getValue() { return particleVX; }
			@Override public void valueChange(String newValue) {
				try{
					particleVX = Integer.parseInt(newValue);
				}catch(NumberFormatException e){
					
				}
			}});
		attributes.add(new Attribute<Integer>(){
			@Override public String getKey() { return "Particle Velocity Y"; }
			@Override public Integer getValue() { return particleVY; }
			@Override public void valueChange(String newValue) {
				try{
					particleVY = Integer.parseInt(newValue);
				}catch(NumberFormatException e){
					
				}
			}});
		attributes.add(new Attribute<Integer>(){
			@Override public String getKey() { return "Particle Gravity X"; }
			@Override public Integer getValue() { return particleGX; }
			@Override public void valueChange(String newValue) {
				try{
					particleGX = Integer.parseInt(newValue);
				}catch(NumberFormatException e){
					
				}
			}});
		attributes.add(new Attribute<Integer>(){
			@Override public String getKey() { return "Particle Gravity Y"; }
			@Override public Integer getValue() { return particleGY; }
			@Override public void valueChange(String newValue) {
				try{
					particleGY = Integer.parseInt(newValue);
				}catch(NumberFormatException e){
					
				}
			}});
		attributes.add(new Attribute<Integer>(){
			@Override public String getKey() { return "Particle Size"; }
			@Override public Integer getValue() { return particleSize; }
			@Override public void valueChange(String newValue) {
				try{
					particleSize = Math.max(Integer.parseInt(newValue), 1);
				}catch(NumberFormatException e){
					
				}
			}});
		attributes.add(new Attribute<Integer>(){
			@Override public String getKey() { return "Particle Red"; }
			@Override public Integer getValue() { return particleRed; }
			@Override public void valueChange(String newValue) {
				try{
					particleRed = Math.max(Math.min(Integer.parseInt(newValue), 0xFF), 0);
				}catch(NumberFormatException e){
					
				}
			}});
		attributes.add(new Attribute<Integer>(){
			@Override public String getKey() { return "Particle Green"; }
			@Override public Integer getValue() { return particleGreen; }
			@Override public void valueChange(String newValue) {
				try{
					particleGreen = Math.max(Math.min(Integer.parseInt(newValue), 0xFF), 0);
				}catch(NumberFormatException e){
					
				}
			}});
		attributes.add(new Attribute<Integer>(){
			@Override public String getKey() { return "Particle Blue"; }
			@Override public Integer getValue() { return particleBlue; }
			@Override public void valueChange(String newValue) {
				try{
					particleBlue = Math.max(Math.min(Integer.parseInt(newValue), 0xFF), 0);
				}catch(NumberFormatException e){
					
				}
			}});
		return attributes;
	}

	@Override
	public void drawGraphics(PGraphics g) {
		g.pushMatrix();
		g.translate(getX(), getY());
		system.draw(g);
		g.popMatrix();
	}

	@Override
	public void update() {
		system.update();
	}

	@Override
	public void drawEntityImpl(PGraphics g) {
		g.rect(getX(), getY(), 20, 20);
	}

	public static void reset() {
		num = 1;
	}
}
