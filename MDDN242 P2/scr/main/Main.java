package main;

import gui.Canvas;
import gui.MainWindow;

import javax.swing.SwingUtilities;

public class Main {
	
	private static MainWindow window;
	
	private Main(){}
	
	public static MainWindow getMainWindow(){
		return window;
	}

	public static Canvas getCanvas() {
		return window.getCanvas();
	}

	/**
	 * Run the program
	 * @param args
	 */
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				window = new MainWindow();
			}
		});
	}

	public static void createNewCanvas(int width, int height) {
		window.createNewCanvas(width, height);
	}
}
