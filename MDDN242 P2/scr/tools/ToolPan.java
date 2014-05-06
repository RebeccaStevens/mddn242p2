package tools;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;

import main.Input;
import main.Main;
import main.Tool;
import processing.core.PApplet;

public class ToolPan extends Tool {

	public static final ToolPan tool = new ToolPan();
	
	private static final String CURSOR_STND_PATH = "data/cursors/pan0.png";
	private static final String CURSOR_DOWN_PATH = "data/cursors/pan1.png";
	
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
	public void mouseDragged(MouseEvent e, Component c) {
		if(ingoreNextEvent){
			ingoreNextEvent = false;
			return;
		}
		
		Input input = Main.getInput();
		if(input.isMouseButtonDown(MouseEvent.BUTTON1)){
			Main.getMainWindow().addPan(input.getMouseLocationDelta());
			
			if(c == Main.getCanvas()) ingoreNextEvent = true;
		}
	}
}
