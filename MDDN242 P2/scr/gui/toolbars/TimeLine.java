package gui.toolbars;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;

public class TimeLine extends ToolBar{

	private static final long serialVersionUID = -57333252299444276L;
	
	public TimeLine(Frame parentFrame){
		super(parentFrame, true);
		setPreferredSize(new Dimension(640, 250));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}