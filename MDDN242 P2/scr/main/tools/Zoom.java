package main.tools;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;

import main.Tool;
import processing.core.PApplet;

public class Zoom extends Tool {

	public static final Zoom tool = new Zoom();
	
	private static final String CURSOR_IN_PATH = "data/cursors/zoomIn.png";
	private static final String CURSOR_OUT_PATH = "data/cursors/zoomOut.png";
	
	private Cursor cursor_in;
	private Cursor cursor_out;
	
	@SuppressWarnings("deprecation")
	private Zoom(){
		PApplet pa = new PApplet();
		cursor_in  = Toolkit.getDefaultToolkit().createCustomCursor(pa .loadImage(CURSOR_IN_PATH).getImage(), new Point(1, 1), "Zoom In");
		cursor_out = Toolkit.getDefaultToolkit().createCustomCursor(pa.loadImage(CURSOR_OUT_PATH).getImage(), new Point(1, 1), "Zoom Out");
	}

	@Override
	public Cursor getCursor() {
		return cursor_in; 
	}
	
	public Cursor getCursorZoomIn() {
		return cursor_in; 
	}
	
	public Cursor getCursorZoomOut() {
		return cursor_out; 
	}
}
