package gui.toolbars;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JPanel;

public abstract class AbstractToolBar extends JPanel implements ToolBar {

	private static final long serialVersionUID = -5998480583258246438L;
	
	public AbstractToolBar(){
		setLayout(new BorderLayout());
		addToolBarTitleBar();
	}

	private void addToolBarTitleBar() {
		add(new ToolBarTitleBar(this), BorderLayout.NORTH);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
