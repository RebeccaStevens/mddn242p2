package gui.toolbars;

import gui.Toolbar;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;

public class Outline extends Toolbar{

	private static final long serialVersionUID = -57333252299444276L;
	
	public Outline(Frame parentFrame){
		super(parentFrame, true);
		setPreferredSize(new Dimension(200, 480));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public boolean canDockNorth(){
		return false;
	}

	@Override
	public boolean canDockSouth(){
		return false;
	}

	@Override
	public boolean canDockEast(){
		return true;
	}

	@Override
	public boolean canDockWest(){
		return true;
	}
}