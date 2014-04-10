package gui;

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
import javax.swing.WindowConstants;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = -4580460675035694517L;

	private static final Color BG_COLOR = new Color(40, 40, 40);

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
		createCanvas();
		createCanvasSettings();
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
		canvas = new Canvas(640, 480);
		panel.add(canvas);
		getContentPane().add(panel, BorderLayout.CENTER);
	}

	private void createCanvasSettings() {
		canvasSettings = new CanvasSettings(this);
		getContentPane().add(canvasSettings, BorderLayout.NORTH);
	}

	/**
	 * Create the tool box
	 */
	private void createToolBox(){
		toolbox = new ToolBox(this);
		getContentPane().add(toolbox, BorderLayout.WEST);
	}

	private void craeteProperties() {
		properties = new Properties(this);
		getContentPane().add(properties, BorderLayout.EAST);
	}

	private void createTimeLine() {
		timeLine = new TimeLine(this);
		getContentPane().add(timeLine, BorderLayout.SOUTH);
	}
}
