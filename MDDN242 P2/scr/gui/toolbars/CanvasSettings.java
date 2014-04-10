package gui.toolbars;

import java.awt.Dimension;
import java.awt.Graphics;

public class CanvasSettings extends AbstractToolBar{

	private static final long serialVersionUID = -57333252299444276L;
	
	public CanvasSettings(){
		super(false);
		setPreferredSize(new Dimension(640, 40));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}