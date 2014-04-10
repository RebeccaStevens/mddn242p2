package gui.toolbars;

import java.awt.Dimension;
import java.awt.Graphics;

public class TimeLine extends AbstractToolBar{

	private static final long serialVersionUID = -57333252299444276L;
	
	public TimeLine(){
		setPreferredSize(new Dimension(640, 250));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}