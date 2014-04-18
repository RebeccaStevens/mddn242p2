package gui.toolbars;

import gui.Toolbar;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;

public class ToolBox extends Toolbar{

	private static final long serialVersionUID = -57333252299444276L;
	
	public ToolBox(Frame parentFrame){
		super(parentFrame, true);
		setPreferredSize(new Dimension(70, 480));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}