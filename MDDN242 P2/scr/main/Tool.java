package main;

import java.awt.Cursor;

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
	
	@Override public void mouseClicked(InputData d) {}
	@Override public void mouseEntered(InputData d) {}
	@Override public void mouseExited(InputData d) {}
	@Override public void mousePressed(InputData d) {}
	@Override public void mouseReleased(InputData d) {}
	@Override public void mouseDragged(InputData d) {}
	@Override public void mouseMoved(InputData d) {}
	@Override public void mouseWheelMoved(InputData d) {}
	@Override public void keyPressed(InputData d) {}
	@Override public void keyReleased(InputData d) {}
	@Override public void keyTyped(InputData d) {}
}
