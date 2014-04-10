package gui.toolbars;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ToolBarTitleBar extends JPanel {

	private static final long serialVersionUID = 6643322416059228945L;
	
	private static final Color BG_COLOR = new Color(53, 53, 53);

	public ToolBarTitleBar(ToolBar parent){
		setPreferredSize(new Dimension(32, 8));
		setBackground(BG_COLOR);
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
	}
}
