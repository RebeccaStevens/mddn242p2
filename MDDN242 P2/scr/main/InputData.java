package main;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

public class InputData {

	InputEvent event;
	Component oc;
	Point mouseLocation, lastMouseLocation;
	boolean[] mouseButtonDown;
	boolean[] modifiersDown;
	
	static final int CTRL = 0;
	static final int SHIFT = 1;
	static final int ALT = 2;
	
	InputData() {
		mouseLocation = new Point();
		lastMouseLocation = new Point();
		mouseButtonDown = new boolean[3];
		modifiersDown = new boolean[3];
	}
	
	void updateMouseLocation(MouseEvent e) {
		lastMouseLocation.setLocation(mouseLocation);
		mouseLocation.setLocation(e.getX(), e.getY());
	}

	public InputEvent getEvent(){
		return event;
	}
	
	public Component getOriginalComponent(){
		return oc;
	}
	
	public Point getMouseLocation() {
		return new Point(mouseLocation);
	}

	public Point getMouseLocationDelta() {
		return new Point(mouseLocation.x - lastMouseLocation.x, mouseLocation.y - lastMouseLocation.y);
	}

	public boolean isMouseButtonDown(int button){
		button = button - 1;
		if(button < 0 || button >= mouseButtonDown.length){
			throw new IllegalArgumentException("Button value is out of range.");
		}
		return mouseButtonDown[button];
	}

	public int getMouseX() {
		return mouseLocation.x;
	}

	public int getMouseY() {
		return mouseLocation.y;
	}

	public boolean isAltDown() {
		return modifiersDown[ALT];
	}
	
	public boolean isCtrlDown() {
		return modifiersDown[CTRL];
	}
	
	public boolean isShiftDown() {
		return modifiersDown[SHIFT];
	}
}
