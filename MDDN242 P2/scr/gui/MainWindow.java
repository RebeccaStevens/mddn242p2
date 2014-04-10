package gui;

import gui.toolbars.CanvasSettings;
import gui.toolbars.Properties;
import gui.toolbars.TimeLine;
import gui.toolbars.ToolBar;
import gui.toolbars.ToolBox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = -4580460675035694517L;

	private static final Color BG_COLOR = new Color(40, 40, 40);

	private MenuBar menu;
	
	private JPanel canvasContainer;
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

		createMenuBar();
		createCanvasContainer();
		createCanvasSettings();
		createToolBox();
		craeteProperties();
		createTimeLine();
		
		createCanvas(640, 480);

		resetPerspective();
		
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);	// maximize the window
		setLocationRelativeTo(null);	// center the window on the screen
		setVisible(true);				// display the window
	}
	
	public boolean dock(ToolBar toolBar, int mouseX, int mouseY) {
		String dockPosition = getDockingLocation(mouseX, mouseY);
		System.out.println(dockPosition);
		if(dockPosition == null) return false;
		dock(toolBar, dockPosition);
		return true;
	}
	
	public void dock(ToolBar toolBar, String position) {
		getContentPane().add(toolBar, position);
		revalidate();
		repaint();
	}
	
	public void resetPerspective(){
		getContentPane().add(canvasSettings, BorderLayout.NORTH);
		getContentPane().add(properties, BorderLayout.EAST);
		getContentPane().add(timeLine, BorderLayout.SOUTH);
		getContentPane().add(toolbox, BorderLayout.WEST);
		getContentPane().add(canvasContainer, BorderLayout.CENTER);
	}
	
	private String getDockingLocation(int mouseX, int mouseY){
		final int dist = 24;
		
		int menuHeight = menu.getHeight();
		
		Insets insets = getInsets();
		Point windowLocation = getLocationOnScreen();
		Dimension windowSize = getSize();
		if(mouseX > windowLocation.x + insets.left
		&& mouseX < windowLocation.x + insets.left + dist
		&& mouseY > windowLocation.y + insets.top + menuHeight + canvasContainer.getY()
		&& mouseY < windowLocation.y + insets.top + menuHeight + canvasContainer.getY() + canvasContainer.getHeight()){
			return BorderLayout.WEST;
		} else
		if(mouseX < windowLocation.x - insets.right + windowSize.width
		&& mouseX > windowLocation.x - insets.right + windowSize.width - dist
		&& mouseY > windowLocation.y + insets.top + menuHeight + canvasContainer.getY()
		&& mouseY < windowLocation.y + insets.top + menuHeight + canvasContainer.getY() + canvasContainer.getHeight()){
			return BorderLayout.EAST;
		} else
		if(mouseX > windowLocation.x + insets.left
		&& mouseX < windowLocation.x + insets.left + windowSize.width
		&& mouseY > windowLocation.y + insets.top + menuHeight
		&& mouseY < windowLocation.y + insets.top + menuHeight + dist){
			return BorderLayout.NORTH;
		} else
		if(mouseX > windowLocation.x + insets.left
		&& mouseX < windowLocation.x + insets.left + windowSize.width
		&& mouseY < windowLocation.y - insets.bottom + windowSize.height
		&& mouseY > windowLocation.y - insets.bottom + windowSize.height - dist){
			return BorderLayout.SOUTH;
		}
		return null;
	}

	private void createMenuBar() {
		setJMenuBar(menu = new MenuBar());
	}

	private void createCanvasContainer(){
		canvasContainer = new JPanel(new GridBagLayout());
		canvasContainer.setOpaque(false);
	}
	
	/**
	 * Create the canvas
	 */
	private void createCanvas(int w, int h){
		canvas = new Canvas(w, h);
		canvasContainer.add(canvas);
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
