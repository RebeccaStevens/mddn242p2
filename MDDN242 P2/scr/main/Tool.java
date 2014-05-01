package main;

import java.awt.Cursor;

import main.tools.Move;

public abstract class Tool {

	private static Tool currentTool;
	
	private static final Tool defaultTool = Move.tool;
	
	
	
	public static Tool getCurrentTool(){
		if(currentTool == null){
			currentTool = defaultTool;
		}
		return currentTool;
	}
	
	public static void setCurrentTool(Tool tool){
		currentTool = tool;
		Main.getMainWindow().setCanvasCursor(tool == null ? Cursor.getDefaultCursor() : tool.getCursor());
	}

	public abstract Cursor getCursor();
	
}
