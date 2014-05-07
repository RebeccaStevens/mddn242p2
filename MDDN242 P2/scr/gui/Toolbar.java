package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;

import main.Main;

public abstract class Toolbar extends JPanel {

	private static final long serialVersionUID = -5998480583258246438L;
	
	private static final int titleBarHeight = 8;
	
	public static final Color BG_COLOR = new Color(83, 83, 83);
	public static final Color BORDER_COLOR = new Color(20, 20, 20);
	public static final Color TITLEBAR_BG_COLOR = new Color(53, 53, 53);

	/**
	 * The independent window the toolbar can be displayed in.
	 */
	private JDialog popup;
	
	/**
	 * The last position of the mouse when dragging the window.
	 */
	private Point lastDrag;

	private JPanel content;

	public Toolbar(Frame parentFrame, boolean popupEnable){
		setLayout(new BorderLayout(0, 0));
		setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1));
		setBackground(BG_COLOR);
		
		if(popupEnable){
			createPopup(parentFrame);
		}
		content = new JPanel();
		content.setOpaque(false);
		add(content, BorderLayout.CENTER);
	}
	
	public boolean canDockNorth(){
		return true;
	}
	
	public boolean canDockSouth(){
		return true;
	}
	
	public boolean canDockEast(){
		return true;
	}
	
	public boolean canDockWest(){
		return true;
	}
	
	protected Container getContentContainer(){
		return content;
	}

	/**
	 * Close the toolbar.
	 */
	public void close(){
		if(popup == null) return;
		popup.setVisible(false);
	}

	/**
	 * Create the popup window.
	 * @param parentFrame
	 */
	private void createPopup(Frame parentFrame) {
		add(new TitleBar(), BorderLayout.NORTH);	// if the window has a popup then it should have a title bar
		
		popup = new JDialog(parentFrame);
		popup.setUndecorated(true);
		popup.setLayout(new BorderLayout());
		lastDrag = new Point();
	}
	
	/**
	 * Try and dock me.
	 * @param mouseX The mouse X position
	 * @param mouseY The mouse Y position
	 */
	private void dock(int mouseX, int mouseY){
		// if docking was successful then close the independent window.
		if(Main.getMainWindow().dock(this, mouseX, mouseY)){
			close();
		}
	}
	
	/**
	 * Undock me.
	 */
	private void undock(){
		MainWindow window = Main.getMainWindow();
		window.undock(this);
		
		popup.setLocation(this.getLocationOnScreen());
		popup.setContentPane(this);
		popup.pack();
		popup.setVisible(true);
		
		window.revalidate();
		window.repaint();
	}
	
	@Override
	public void setPreferredSize(Dimension preferredSize) {
		preferredSize.height += titleBarHeight;
		super.setPreferredSize(preferredSize);
	}

	public abstract void reset();
	
	/**
	 * Call this when the mouse starts to drag the popup window.
	 * @param mouseX
	 * @param mouseY
	 */
	private void dragStart(int mouseX, int mouseY) {
		lastDrag.x = mouseX;
		lastDrag.y = mouseY;
		
		Point oldLoc = this.getLocationOnScreen();
		Dimension oldSize = this.getSize();
		undock();
		Dimension newSize = popup.getSize();
		Point loc = popup.getLocationOnScreen();
		loc.x += (oldSize.width - newSize.width) * (double)(mouseX - oldLoc.x) / oldSize.width;
		popup.setLocation(loc);
	}

	/**
	 * Call this on each update as the mouse drags the popup.
	 * @param mouseX
	 * @param mouseY
	 */
	private void dragUpdate(int mouseX, int mouseY) {
		Point current = popup.getLocationOnScreen();
		popup.setLocation(current.x + mouseX - lastDrag.x, current.y + mouseY - lastDrag.y);
		lastDrag.x = mouseX;
		lastDrag.y = mouseY;
	}

	/**
	 * Call this when the mouse stops dragging the popup window.
	 * @param xOnScreen The mouse's x position on the screen
	 * @param yOnScreen The mouse's y position on the screen
	 */
	private void dragEnd(int xOnScreen, int yOnScreen) {
		dock(xOnScreen, yOnScreen);	// try and dock the toolbar
	}
	
	
	
	/**
	 * The bit of the toolbar that can be dragged.
	 * 
	 * @author Mike Stevens
	 */
	private class TitleBar extends JPanel {

		private static final long serialVersionUID = 6643322416059228945L;

		public TitleBar(){
			setPreferredSize(new Dimension(32, titleBarHeight));
			setBackground(TITLEBAR_BG_COLOR);
			
			DragEventListener dl = new DragEventListener();
			addMouseListener(dl);
			addMouseMotionListener(dl);
		}
		
		private class DragEventListener implements MouseListener, MouseMotionListener{
			@Override public void mouseClicked(MouseEvent e) {}
			@Override public void mouseEntered(MouseEvent e) {}
			@Override public void mouseExited(MouseEvent e) {}		
			@Override public void mouseMoved(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {
				dragStart(e.getXOnScreen(), e.getYOnScreen());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				dragEnd(e.getXOnScreen(), e.getYOnScreen());
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				dragUpdate(e.getXOnScreen(), e.getYOnScreen());
			}
		}
	}
}
