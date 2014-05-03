package main.tools;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;

import main.Tool;
import processing.core.PApplet;

public class Move extends Tool {

	public static final Move tool = new Move();
	
	private static final String CURSOR_PATH = "data/cursors/move.png";
	
	private Cursor cursor;
	
	@SuppressWarnings("deprecation")
	private Move(){
		cursor = Toolkit.getDefaultToolkit().createCustomCursor(new PApplet().loadImage(CURSOR_PATH).getImage(), new Point(1, 1), "Move");
	}

	@Override
	public Cursor getCursor() {
		return cursor; 
	}
}
