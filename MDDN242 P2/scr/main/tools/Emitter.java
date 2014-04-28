package main.tools;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;

import processing.core.PApplet;
import main.Tool;

public class Emitter extends Tool{

	public static final Emitter tool = new Emitter();

	private static final String CURSOR_PATH = "data/cursors/emitter.png";
	
	private Cursor cursor;
	
	@SuppressWarnings("deprecation")
	private Emitter(){
		cursor = Toolkit.getDefaultToolkit().createCustomCursor(new PApplet().loadImage(CURSOR_PATH).getImage(), new Point(1, 1), "Emitter");
	}
	
	@Override
	public Cursor getCursor() {
		return cursor; 
	}
}
