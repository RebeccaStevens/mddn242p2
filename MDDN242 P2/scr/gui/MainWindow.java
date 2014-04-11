package gui;

import gui.toolbars.CanvasSettings;
import gui.toolbars.Properties;
import gui.toolbars.TimeLine;
import gui.toolbars.ToolBar;
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
	
	private JPanel[] containers = new JPanel[5];
	private Canvas canvas;

	// tool bars
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

		addComonents();
		
		setExtendedState(getExtendedState() | Frame.MAXIMIZED_BOTH);	// maximize the window
		setLocationRelativeTo(null);	// center the window on the screen
		setVisible(true);				// display the window
	}
	
	public boolean dock(ToolBar toolBar, int mouseX, int mouseY) {
		String dockPosition = getDockingLocation(mouseX, mouseY);
		if(dockPosition == null) return false;
		dock(toolBar, dockPosition);
		return true;
	}
	
	public void dock(ToolBar toolBar, String position) {
		if(position == BorderLayout.NORTH){
			containers[NORTH].add(Box.createVerticalStrut(-1));
			containers[NORTH].add(toolBar, position);
		}
		else if(position == BorderLayout.EAST){
			containers[EAST].add(Box.createHorizontalStrut(-1));
			containers[EAST].add(toolBar, position);
		}
		else if(position == BorderLayout.SOUTH){
			containers[SOUTH].add(Box.createVerticalStrut(-1));
			containers[SOUTH].add(toolBar, position);
		}
		else if(position == BorderLayout.WEST){
			containers[WEST].add(Box.createHorizontalStrut(-1));
			containers[WEST].add(toolBar, position);
		}
		revalidate();
		repaint();
	}
	
	public void undock(ToolBar toolBar){
		removeUnneedStrut(toolBar);
	}
	
	private void removeUnneedStrut(ToolBar toolBar) {
		Container p = toolBar.getParent();
		if(p == null) return;
		
		Component[] comps = p.getComponents();
		int i = 0;
		for(; i<comps.length; i++){
			if(comps[i] == toolBar) break;
		}

		if(i < 1) return;
		if(comps[i-1].getClass() != Box.Filler.class) return;
		
		p.remove(i-1);
	}

	public void addComonents(){
		containers[NORTH].add(canvasSettings);
		containers[SOUTH].add(timeLine);
		containers[EAST].add(properties);
		containers[WEST].add(toolbox);
	}
	
	public void resetPerspective(){
		addComonents();
		canvasSettings.close();
		properties.close();
		timeLine.close();
		toolbox.close();
		
		revalidate();
		repaint();
	}
	
	private String getDockingLocation(int mouseX, int mouseY){
		final int dist = 24;
		
		int menuHeight = menu.getHeight();
		Insets insets = getInsets();
		Point windowLocation = getLocationOnScreen();
		Dimension windowSize = getSize();
		
		if(mouseX > windowLocation.x + insets.left + containers[WEST].getWidth()
		&& mouseX < windowLocation.x + insets.left + containers[WEST].getWidth() + dist
		&& mouseY > windowLocation.y + insets.top + menuHeight + containers[CENTER].getY()
		&& mouseY < windowLocation.y + insets.top + menuHeight + containers[CENTER].getY() + containers[CENTER].getHeight()){
			return BorderLayout.WEST;
		} else
		if(mouseX < windowLocation.x - insets.right - containers[EAST].getWidth() + windowSize.width
		&& mouseX > windowLocation.x - insets.right - containers[EAST].getWidth() + windowSize.width - dist
		&& mouseY > windowLocation.y + insets.top + menuHeight + containers[CENTER].getY()
		&& mouseY < windowLocation.y + insets.top + menuHeight + containers[CENTER].getY() + containers[CENTER].getHeight()){
			return BorderLayout.EAST;
		} else
		if(mouseX > windowLocation.x + insets.left
		&& mouseX < windowLocation.x + insets.left + windowSize.width
		&& mouseY > windowLocation.y + insets.top + containers[NORTH].getHeight() + menuHeight
		&& mouseY < windowLocation.y + insets.top + containers[NORTH].getHeight() + menuHeight + dist){
			return BorderLayout.NORTH;
		} else
		if(mouseX > windowLocation.x + insets.left - containers[SOUTH].getHeight()
		&& mouseX < windowLocation.x + insets.left - containers[SOUTH].getHeight() + windowSize.width
		&& mouseY < windowLocation.y - insets.bottom + windowSize.height
		&& mouseY > windowLocation.y - insets.bottom + windowSize.height - dist){
			return BorderLayout.SOUTH;
		}
		return null;
	}

	private void createMenuBar() {
		setJMenuBar(menu = new MenuBar());
	}

	private void addContainers(){
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
		
		containers[CENTER].setBorder(BorderFactory.createLineBorder(ToolBar.BORDER_COLOR, 1));
		
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

	private void createCanvasSettings() {
		canvasSettings = new CanvasSettings(this);
	}

	/**
	 * Create the tool box
	 */
	private void createToolBox(){
		toolbox = new ToolBox(this);
	}

	private void craeteProperties() {
		properties = new Properties(this);
	}

	private void createTimeLine() {
		timeLine = new TimeLine(this);
	}
}
