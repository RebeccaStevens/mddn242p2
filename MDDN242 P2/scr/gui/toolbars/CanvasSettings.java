package gui.toolbars;

import gui.Toolbar;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;

public class CanvasSettings extends Toolbar{

	private static final long serialVersionUID = -57333252299444276L;
	
	public CanvasSettings(Frame parentFrame){
		super(parentFrame, false);
		setPreferredSize(new Dimension(640, 40));
	}

	@Override
	public void reset() {
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public boolean canDockNorth(){
		return true;
	}

	@Override
	public boolean canDockSouth(){
		return false;
	}

	@Override
	public boolean canDockEast(){
		return false;
	}

	@Override
	public boolean canDockWest(){
		return false;
	}
}