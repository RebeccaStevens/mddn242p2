package tools;

import entities.Entity;
import gui.Canvas;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;

import main.InputData;
import main.Main;
import main.Tool;
import processing.core.PApplet;

public class ToolMove extends Tool {

	public static final ToolMove tool = new ToolMove();
	
	private static final String CURSOR_PATH = "cursors/move.png";
	
	private Cursor cursor;
	
	@SuppressWarnings("deprecation")
	private ToolMove(){
		cursor = Toolkit.getDefaultToolkit().createCustomCursor(new PApplet().loadImage(CURSOR_PATH).getImage(), new Point(1, 1), "Move");
	}

	@Override
	public Cursor getCursor() {
		return cursor; 
	}
	
	@Override
	public void mousePressed(InputData d) {
		Canvas c = Main.getCanvas();
		if(c == null) return;
		
		c.selectEntityAt((int)(d.getMouseX()/c.getScale()), (int)(d.getMouseY()/c.getScale()));
		c.redraw();
	}
	
	@Override
	public void mouseDragged(InputData d) {
		Canvas c = Main.getCanvas();
		if(c == null) return;
		Entity e = c.getSelectedEntity();
		if(e == null) return;
		
		Point p = d.getMouseLocationDelta();
		e.move((int)(p.x / c.getScale()), (int)(p.y / c.getScale()));
		c.redraw();
	}
}
