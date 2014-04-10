package gui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ToolBox extends JPanel{

	private static final long serialVersionUID = -57333252299444276L;
	
	public ToolBox(){
		setPreferredSize(new Dimension(70, 200));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}