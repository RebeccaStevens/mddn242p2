package tools;

import gui.Canvas;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;

import main.InputData;
import main.Main;
import main.Tool;
import processing.core.PApplet;

public class ToolPan extends Tool {

	public static final ToolPan tool = new ToolPan();
	
	private static final String CURSOR_STND_PATH = "cursors/pan0.png";
	private static final String CURSOR_DOWN_PATH = "cursors/pan1.png";
	
	private Cursor cursor_stnd;
	private Cursor cursor_down;

	/**
	 * To fix double event issue
	 */
	private boolean ingoreNextEvent;
	
	@SuppressWarnings("deprecation")
	private ToolPan(){
		PApplet pa = new PApplet();
		cursor_stnd = Toolkit.getDefaultToolkit().createCustomCursor(pa .loadImage(CURSOR_STND_PATH).getImage(), new Point(16, 16), "Pan0");
		cursor_down = Toolkit.getDefaultToolkit().createCustomCursor(pa.loadImage(CURSOR_DOWN_PATH).getImage(), new Point(16, 16), "Pan1");
	}

	@Override
	public Cursor getCursor() {
		return cursor_stnd; 
	}
	
	public Cursor getCursorDown() {
		return cursor_down; 
	}
	
	@Override
	public void mouseDragged(InputData d) {
		Canvas c = Main.getCanvas();
		if(c == null) return;
		
		if(ingoreNextEvent){
			ingoreNextEvent = false;
			return;
		}
		
		if(d.isMouseButtonDown(MouseEvent.BUTTON1)){
			Main.getMainWindow().addPan(d.getMouseLocationDelta());
			
			if(d.getOriginalComponent() instanceof java.awt.Canvas) ingoreNextEvent = true;
		}
	}
}
