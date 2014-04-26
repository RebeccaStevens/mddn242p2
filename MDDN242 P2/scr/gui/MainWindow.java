package gui;

import gui.toolbars.CanvasSettings;
import gui.toolbars.Properties;
import gui.toolbars.TimeLine;
import gui.toolbars.ToolBox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = -4580460675035694517L;

	private static final Color BG_COLOR = new Color(40, 40, 40);
	
	private static final int CENTER = 0;
	private static final int NORTH = 1;
	private static final int EAST = 2;
	private static final int SOUTH = 3;
	private static final int WEST = 4;

	private MenuBar menu;
	
	private JPanel[] containers;
	private Canvas canvas;

	// toolbars
	private CanvasSettings canvasSettings;
	private ToolBox toolbox;
	private Properties properties;
	private TimeLine timeLine;

	/**
	 * Create this (the window)
	 */
	public MainWindow(){
		setTitle("Particle Creator");
		setSize(1280, 900);
		setMinimumSize(new Dimension(1024, 768));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		// create content pane
		JPanel content = new JPanel(new BorderLayout(-1, -1), true);
		content.setBackground(BG_COLOR);
		setContentPane(content);

		addContainers();
		
		createMenuBar();
		createCanvasSettings();
		createToolBox();
		craeteProperties();
		createTimeLine();
		
		createCanvas(640, 480);

		addComponents();
		
		setExtendedState(getExtendedState() | Frame.MAXIMIZED_BOTH);	// maximize the window
		setLocationRelativeTo(null);	// center the window on the screen
		setVisible(true);				// display the window
	}
	
	/**
	 * Dock the given toolbar into is window based on the given mouse x and y location given.
	 * The toolbar will not be docked if the given mouse location is not within a docking area.
	 * @param toolbar The toolbar to dock
	 * @param mouseX The x location of the mouse
	 * @param mouseY The y location of the mouse
	 * @return true if the toolbar was docked, otherwise false
	 */
	public boolean dock(Toolbar toolbar, int mouseX, int mouseY) {
		String dockPosition = getDockingLocation(mouseX, mouseY);
		if(dockPosition == null) return false;
		return dock(toolbar, dockPosition);
	}
	
	/**
	 * Dock the given toolbar to the given position.
	 * @param toolbar The toolbar to dock
	 * @param position The position to dock the toolbar in.
	 * Valid options: BorderLayout.NORTH, BorderLayout.EAST, BorderLayout.SOUTH, BorderLayout.WEST
	 * @return true if the toolbar was docked, otherwise false
	 */
	public boolean dock(Toolbar toolbar, String position) {
		if(toolbar.canDockNorth() && position.equals(BorderLayout.NORTH)){
			containers[NORTH].add(Box.createVerticalStrut(-1));
			containers[NORTH].add(toolbar, position);
		}
		else if(toolbar.canDockEast() && position.equals(BorderLayout.EAST)){
			containers[EAST].add(Box.createHorizontalStrut(-1));
			containers[EAST].add(toolbar, position);
		}
		else if(toolbar.canDockSouth() && position.equals(BorderLayout.SOUTH)){
			containers[SOUTH].add(Box.createVerticalStrut(-1));
			containers[SOUTH].add(toolbar, position);
		}
		else if(toolbar.canDockWest() && position.equals(BorderLayout.WEST)){
			containers[WEST].add(Box.createHorizontalStrut(-1));
			containers[WEST].add(toolbar, position);
		}
		else{
			return false;
		}
		revalidate();
		repaint();
		return true;
	}
	
	/**
	 * Undock the given toolbar from the window.
	 * This method should be called from the toolbar class when undocking the toolbar
	 * in order for this class to perform needed cleanups
	 * @param toolbar The toolbar being undocked
	 */
	void undock(Toolbar toolbar){
		removeUnneedStrut(toolbar);
	}
	
	/**
	 * Returns the dock location that the (x, y) point is next to. 
	 * @param x The x position of the point to test
	 * @param y The y position of the point to test
	 * @return BorderLayout.NORTH, BorderLayout.EAST, BorderLayout.SOUTH, BorderLayout.WEST or null
	 */
	private String getDockingLocation(int x, int y){
		final int dist = 24;
		
		int menuHeight = menu.getHeight();
		Insets insets = getInsets();
		Point windowLocation = getLocationOnScreen();
		Dimension windowSize = getSize();
		
		if(x >= windowLocation.x + insets.left + containers[WEST].getWidth()
		&& x <= windowLocation.x + insets.left + containers[WEST].getWidth() + dist
		&& y >= windowLocation.y + insets.top + menuHeight + containers[CENTER].getY()
		&& y <= windowLocation.y + insets.top + menuHeight + containers[CENTER].getY() + containers[CENTER].getHeight()){
			return BorderLayout.WEST;
		} else
		if(x <= windowLocation.x - insets.right - containers[EAST].getWidth() + windowSize.width
		&& x >= windowLocation.x - insets.right - containers[EAST].getWidth() + windowSize.width - dist
		&& y >= windowLocation.y + insets.top + menuHeight + containers[CENTER].getY()
		&& y <= windowLocation.y + insets.top + menuHeight + containers[CENTER].getY() + containers[CENTER].getHeight()){
			return BorderLayout.EAST;
		} else
		if(x >= windowLocation.x + insets.left
		&& x <= windowLocation.x + insets.left + windowSize.width
		&& y >= windowLocation.y + insets.top + containers[NORTH].getHeight() + menuHeight
		&& y <= windowLocation.y + insets.top + containers[NORTH].getHeight() + menuHeight + dist){
			return BorderLayout.NORTH;
		} else
		if(x >= windowLocation.x + insets.left - containers[SOUTH].getHeight()
		&& x <= windowLocation.x + insets.left - containers[SOUTH].getHeight() + windowSize.width
		&& y <= windowLocation.y - insets.bottom + windowSize.height
		&& y >= windowLocation.y - insets.bottom + windowSize.height - dist){
			return BorderLayout.SOUTH;
		}
		return null;
	}

	private void removeUnneedStrut(Toolbar toolbar) {
		Container p = toolbar.getParent();
		if(p == null) return;
		
		Component[] comps = p.getComponents();
		int i = 0;
		for(; i<comps.length; i++){
			if(comps[i] == toolbar) break;
		}

		if(i < 1) return;
		if(comps[i-1].getClass() != Box.Filler.class) return;
		
		p.remove(i-1);
	}

	/**
	 * Add the components to their default containers
	 */
	private void addComponents(){
		containers[NORTH].add(canvasSettings);
		containers[SOUTH].add(timeLine);
		containers[EAST].add(properties);
		containers[WEST].add(toolbox);
	}
	
	/**
	 * Reset the window's perspective.
	 * All tool bars are put back to their default positions
	 */
	public void resetPerspective(){
		canvasSettings.close();
		properties.close();
		timeLine.close();
		toolbox.close();
		
		addComponents();
		
		revalidate();
		repaint();
	}
	
	/**
	 * Create the menu bar
	 */
	private void createMenuBar() {
		setJMenuBar(menu = new MenuBar());
	}

	/**
	 * Create the containers for the components and add them to the window
	 */
	private void addContainers(){
		containers = new JPanel[5];
		
		containers[CENTER]	= new JPanel(new GridBagLayout());
		containers[NORTH]	= new JPanel();
		containers[EAST]	= new JPanel();
		containers[SOUTH]	= new JPanel();
		containers[WEST]	= new JPanel();
		
		containers[CENTER].setOpaque(false);
		containers[NORTH].setLayout(new BoxLayout(containers[NORTH], BoxLayout.Y_AXIS));
		containers[SOUTH].setLayout(new BoxLayout(containers[SOUTH], BoxLayout.Y_AXIS));
		containers[EAST].setLayout(new BoxLayout(containers[EAST], BoxLayout.X_AXIS));
		containers[WEST].setLayout(new BoxLayout(containers[WEST], BoxLayout.X_AXIS));
		
		containers[CENTER].setBorder(BorderFactory.createLineBorder(Toolbar.BORDER_COLOR, 1));
		
		Container pane = getContentPane();
		pane.add(containers[CENTER], BorderLayout.CENTER);
		pane.add(containers[NORTH],  BorderLayout.NORTH);
		pane.add(containers[EAST],   BorderLayout.EAST);
		pane.add(containers[SOUTH],  BorderLayout.SOUTH);
		pane.add(containers[WEST],   BorderLayout.WEST);
	}
	
	/**
	 * Create the canvas
	 */
	private void createCanvas(int w, int h){
		canvas = new Canvas(w, h);
		// TODO remove any old canvas
		containers[CENTER].add(canvas);
		canvas.init();
	}

	/**
	 * Create the canvas settings window (toolbar)
	 */
	private void createCanvasSettings() {
		canvasSettings = new CanvasSettings(this);
	}

	/**
	 * Create the tool box window (toolbar)
	 */
	private void createToolBox(){
		toolbox = new ToolBox(this);
	}

	/**
	 * Create the properties window (toolbar)
	 */
	private void craeteProperties() {
		properties = new Properties(this);
	}

	/**
	 * Create the time line window (toolbar)
	 */
	private void createTimeLine() {
		timeLine = new TimeLine(this);
	}
}
