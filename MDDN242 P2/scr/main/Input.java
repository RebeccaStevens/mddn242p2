package main;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.event.MouseInputListener;

public class Input implements MouseInputListener, MouseWheelListener, KeyListener{
	
	private Point mouseLocation, lastMouseLocation;
	private boolean[] mouseButtonDown;
	
	public Input(){
		mouseLocation = new Point();
		lastMouseLocation = new Point();
		mouseButtonDown = new boolean[3];
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Component c = process(e);
		Tool.getCurrentTool().mouseClicked(e, c);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Component c = process(e);
		Tool.getCurrentTool().mouseEntered(e, c);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Component c = process(e);
		Tool.getCurrentTool().mouseExited(e, c);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Component c = process(e);
		
		int i = e.getButton() - 1;
		if(i >= 0 && i < mouseButtonDown.length){
			mouseButtonDown[i] = true;
		}
		
		Tool.getCurrentTool().mousePressed(e, c);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Component c = process(e);
		
		int i = e.getButton() - 1;
		if(i >= 0 && i < mouseButtonDown.length){
			mouseButtonDown[i] = false;
		}
		
		Tool.getCurrentTool().mouseReleased(e, c);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Component c = process(e);
		updateMouseLocation(e);
		Tool.getCurrentTool().mouseDragged(e, c);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Component c = process(e);
		updateMouseLocation(e);
		Tool.getCurrentTool().mouseMoved(e, c);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		Component c = process(e);
		Tool.getCurrentTool().mouseWheelMoved(e, c);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Component c = process(e);
		Tool.getCurrentTool().keyPressed(e, c);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Component c = process(e);
		Tool.getCurrentTool().keyReleased(e, c);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		Component c = process(e);
		Tool.getCurrentTool().keyTyped(e, c);
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
	
	private Component process(InputEvent e){
		Component c = e.getComponent();
		e.setSource(Main.getCanvas());
		return c;
	}

	private void updateMouseLocation(MouseEvent e) {
		lastMouseLocation.setLocation(mouseLocation);
		mouseLocation.setLocation(e.getX(), e.getY());
	}

}
