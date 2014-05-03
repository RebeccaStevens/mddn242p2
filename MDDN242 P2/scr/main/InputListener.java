package main;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public interface InputListener {

	public void mouseClicked(MouseEvent e, Component c);
	public void mouseEntered(MouseEvent e, Component c);
	public void mouseExited(MouseEvent e, Component c);
	public void mousePressed(MouseEvent e, Component c);
	public void mouseReleased(MouseEvent e, Component c);
	public void mouseDragged(MouseEvent e, Component c);
	public void mouseMoved(MouseEvent e, Component c);
	public void mouseWheelMoved(MouseWheelEvent e, Component c);
	public void keyPressed(KeyEvent e, Component c);
	public void keyReleased(KeyEvent e, Component c);
	public void keyTyped(KeyEvent e, Component c);
}
