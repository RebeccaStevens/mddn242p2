package main;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import tools.ToolMove;

public abstract class Tool implements InputListener {

	private static Tool currentTool;
	
	private static final Tool defaultTool = ToolMove.tool;
	
	public static final Tool getCurrentTool(){
		if(currentTool == null){
			currentTool = defaultTool;
		}
		return currentTool;
	}
	
	public static final void setCurrentTool(Tool tool){
		currentTool = tool;
		Main.getMainWindow().setCanvasCursor(tool == null ? Cursor.getDefaultCursor() : tool.getCursor());
	}

	public abstract Cursor getCursor();
	
	@Override public void mouseClicked(MouseEvent e, Component c) {}
	@Override public void mouseEntered(MouseEvent e, Component c) {}
	@Override public void mouseExited(MouseEvent e, Component c) {}
	@Override public void mousePressed(MouseEvent e, Component c) {}
	@Override public void mouseReleased(MouseEvent e, Component c) {}
	@Override public void mouseDragged(MouseEvent e, Component c) {}
	@Override public void mouseMoved(MouseEvent e, Component c) {}
	@Override public void mouseWheelMoved(MouseWheelEvent e, Component c) {}
	@Override public void keyPressed(KeyEvent e, Component c) {}
	@Override public void keyReleased(KeyEvent e, Component c) {}
	@Override public void keyTyped(KeyEvent e, Component c) {}
}
