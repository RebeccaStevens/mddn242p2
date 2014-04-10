package gui.toolbars;

import gui.MainWindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JDialog;
import javax.swing.JPanel;

import main.Main;

public abstract class ToolBar extends JPanel {

	private static final long serialVersionUID = -5998480583258246438L;
	
	private static final Color BG_COLOR = new Color(83, 83, 83);

	private JDialog popup;
	private Point dragStart;

	
	public ToolBar(){
		this(null, false);
	}
	
	public ToolBar(Frame parentFrame, boolean popupable){
		setLayout(new BorderLayout());
		setBackground(BG_COLOR);
		
		if(popupable){
			createPopup(parentFrame);
		}
	}

	private void createPopup(Frame parentFrame) {
		add(new ToolBarTitleBar(this), BorderLayout.NORTH);
		
		popup = new JDialog(parentFrame);
		popup.setUndecorated(true);
		popup.setLayout(new BorderLayout());
		dragStart = new Point();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	public void displayInPopup(){
		popup.setLocation(this.getLocationOnScreen());
		popup.setContentPane(this);
		popup.pack();
		popup.setVisible(true);
		
		MainWindow window = Main.getMainWindow();
		window.revalidate();
		window.repaint();
	}

	void dragStart(int x, int y) {
		dragStart.x = x;
		dragStart.y = y;
		displayInPopup();
	}

	void dragUpdate(int x, int y) {
		Point current = popup.getLocation();
		popup.setLocation(new Point(current.x+x-dragStart.x, current.y+y-dragStart.y));
	}
}
