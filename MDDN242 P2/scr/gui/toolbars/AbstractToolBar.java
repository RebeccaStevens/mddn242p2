package gui.toolbars;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public abstract class AbstractToolBar extends JPanel implements ToolBar {

	private static final long serialVersionUID = -5998480583258246438L;
	
	private static final Color BG_COLOR = new Color(83, 83, 83);
	
	public AbstractToolBar(){
		this(false);
	}
	
	public AbstractToolBar(boolean titlebar){
		setLayout(new BorderLayout());
		setBackground(BG_COLOR);
		
		if(titlebar){
			addToolBarTitleBar();
		}
	}

	private void addToolBarTitleBar() {
		add(new ToolBarTitleBar(this), BorderLayout.NORTH);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
