package gui.toolbars;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class CanvasSettings extends JPanel{

	private static final long serialVersionUID = -57333252299444276L;
	
	public CanvasSettings(){
		setPreferredSize(new Dimension(640, 40));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}