package gui.toolbars;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class TimeLine extends JPanel{

	private static final long serialVersionUID = -57333252299444276L;
	
	public TimeLine(){
		setPreferredSize(new Dimension(640, 200));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}