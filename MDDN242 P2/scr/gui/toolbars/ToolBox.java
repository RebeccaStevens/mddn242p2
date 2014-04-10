package gui.toolbars;

import java.awt.Dimension;
import java.awt.Graphics;

public class ToolBox extends AbstractToolBar{

	private static final long serialVersionUID = -57333252299444276L;
	
	public ToolBox(){
		setPreferredSize(new Dimension(70, 480));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}