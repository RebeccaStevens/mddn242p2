package gui.toolbars;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Properties extends JPanel{

	private static final long serialVersionUID = -57333252299444276L;
	
	public Properties(){
		setPreferredSize(new Dimension(300, 480));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}