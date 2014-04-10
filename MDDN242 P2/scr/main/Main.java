package main;

import gui.Canvas;
import gui.ToolBox;

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
	
	private Canvas canvas;
	private ToolBox toolbox;
	
	/**
	 * Create this (the window)
	 */
	public Main(){
		setTitle("Particles by Michael Stevens");
		setSize(1024, 768);
		setMinimumSize(new Dimension(1024, 768));
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    setContentPane(new JPanel(new BorderLayout(), true));
	    
	    createCanvas();
	    createToolBox();
	    
	    canvas.init();					// start the animation thread (and other such stuff)
	    setLocationRelativeTo(null);	// center the window on the screen
		setVisible(true);				// display the window
	}
	
	/**
	 * Create the tool box
	 */
	private void createToolBox(){
		toolbox = new ToolBox();
	    getContentPane().add(toolbox, BorderLayout.WEST);
	}
	
	/**
	 * Create the canvas
	 */
	private void createCanvas(){
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(new Color(40, 40, 40));
		canvas = new Canvas();
		panel.add(canvas);
	    getContentPane().add(panel, BorderLayout.CENTER);
	}

	/**
	 * Run the program
	 * @param args
	 */
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Main();
			}
		});
	}
}
