package main;

public class Time {
	
	private int currentFrame;
	private int fps;
	
	public Time(){
		currentFrame = 1;
		fps = 24;
	}
	
	public int getCurrentFrame(){
		return currentFrame;
	}
	
	public double getCurrentTime(){
		return (currentFrame-1) / (double)fps;
	}
	
	public int getFramesPerSecond(){
		return fps;
	}
	
	public void setCurrentFrame(int frame){
		currentFrame = frame;
	}

	public int goToNextFrame() {
		return ++currentFrame;
	}
	
	public int goToPreviousFrame() {
		return --currentFrame;
	}
}
