package gui.toolbars;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class ToolBarTitleBar extends JPanel {

	private static final long serialVersionUID = 6643322416059228945L;
	
	private static final Color BG_COLOR = new Color(53, 53, 53);

	private ToolBar toolbar;

	public ToolBarTitleBar(ToolBar parent){
		this.toolbar = parent;
		setPreferredSize(new Dimension(32, 8));
		setBackground(BG_COLOR);
		
		DragEventListener dl = new DragEventListener();
		addMouseListener(dl);
		addMouseMotionListener(dl);
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
	}
	
	private class DragEventListener implements MouseListener, MouseMotionListener{
		@Override public void mouseClicked(MouseEvent e) {}
		@Override public void mouseEntered(MouseEvent e) {}
		@Override public void mouseExited(MouseEvent e) {}		
		@Override public void mouseMoved(MouseEvent e) {}
		
		@Override
		public void mousePressed(MouseEvent e) {
			toolbar.dragStart(e.getXOnScreen(), e.getYOnScreen());
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			toolbar.dragEnd(e.getXOnScreen(), e.getYOnScreen());
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			toolbar.dragUpdate(e.getXOnScreen(), e.getYOnScreen());
		}
	}
}
