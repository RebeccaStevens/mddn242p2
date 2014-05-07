package tools;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;

import entities.Emitter;
import gui.Canvas;
import main.InputData;
import main.Main;
import main.Tool;
import processing.core.PApplet;

public class ToolEmitter extends Tool{

	public static final ToolEmitter tool = new ToolEmitter();

	private static final String CURSOR_PATH = "cursors/emitter.png";
	
	private Cursor cursor;
	
	@SuppressWarnings("deprecation")
	private ToolEmitter(){
		cursor = Toolkit.getDefaultToolkit().createCustomCursor(new PApplet().loadImage(CURSOR_PATH).getImage(), new Point(1, 1), "Emitter");
	}
	
	@Override
	public Cursor getCursor() {
		return cursor; 
	}
	
	@Override
	public void mouseReleased(InputData d){
		Canvas c = Main.getCanvas();
		if(c == null) return;
		
		if(!(d.getOriginalComponent() instanceof java.awt.Canvas)) return;
		float s = c.getScale();
		c.addEntity(new Emitter((int)(d.getMouseX()/s), (int)(d.getMouseY()/s)));
	}
}
