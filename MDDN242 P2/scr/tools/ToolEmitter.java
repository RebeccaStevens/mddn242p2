package tools;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;

import main.Main;
import main.Tool;
import processing.core.PApplet;
import entities.Emitter;

public class ToolEmitter extends Tool{

	public static final ToolEmitter tool = new ToolEmitter();

	private static final String CURSOR_PATH = "data/cursors/emitter.png";
	
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
	public void mouseReleased(MouseEvent e, Component c){
		System.out.println("R");
		Main.getCanvas().addEntity(new Emitter(e.getX(), e.getY()));
	}
}
