package main;

import gui.Canvas;
import gui.MenuBar;
import gui.toolbars.CanvasSettings;
import gui.toolbars.Properties;
import gui.toolbars.TimeLine;
import gui.toolbars.ToolBox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Main extends JFrame {
	
	private static final long serialVersionUID = -4580460675035694517L;

	private static Main window;
	
	private Canvas canvas;
	
	// tool bars
	private CanvasSettings canvasSettings;
	private ToolBox toolbox;
	private Properties properties;
	private TimeLine timeLine;
	
	/**
	 * Create this (the window)
	 */
	private Main(){
		setTitle("Particles by Michael Stevens");
		setSize(1280, 900);
		setMinimumSize(new Dimension(1024, 768));
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    JPanel content = new JPanel(new BorderLayout(), true);
	    content.setBackground(new Color(40, 40, 40));
	    setContentPane(content);
	    
	    createMenuBar();
	    createCanvas();
	    createCancasSettings();
	    createToolBox();
	    craeteProperties();
	    createTimeLine();
	    
	    canvas.init();					// start the animation thread (and other such stuff)
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);	// maximize the window
	    setLocationRelativeTo(null);	// center the window on the screen
		setVisible(true);				// display the window
	}
	
	private void createMenuBar() {
		setJMenuBar(new MenuBar());
	}

	/**
	 * Create the canvas
	 */
	private void createCanvas(){
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setOpaque(false);
		canvas = new Canvas();
		panel.add(canvas);
	    getContentPane().add(panel, BorderLayout.CENTER);
	}

	private void createCancasSettings() {
		canvasSettings = new CanvasSettings();
	    getContentPane().add(canvasSettings, BorderLayout.NORTH);
	}

	/**
	 * Create the tool box
	 */
	private void createToolBox(){
		toolbox = new ToolBox();
	    getContentPane().add(toolbox, BorderLayout.WEST);
	}
	
	private void craeteProperties() {
		properties = new Properties();
	    getContentPane().add(properties, BorderLayout.EAST);
		
	}

	private void createTimeLine() {
		timeLine = new TimeLine();
	    getContentPane().add(timeLine, BorderLayout.SOUTH);
	}
	
	public static Main getMainWindow(){
		return window;
	}

	/**
	 * Run the program
	 * @param args
	 */
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				window = new Main();
			}
		});
	}
}
