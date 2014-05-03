package main;

import gui.Canvas;
import gui.MainWindow;

import javax.swing.SwingUtilities;

public class Main {
	
	private static MainWindow window;
	private static Input input;
	
	private Main(){}
	
	public static MainWindow getMainWindow(){
		return window;
	}

	public static Canvas getCanvas() {
		return window.getCanvas();
	}
	
	public static Input getInput(){
		return input;
	}

	/**
	 * Run the program
	 * @param args
	 */
	public static void main(String[] args){
		input = new Input();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				window = new MainWindow();
			}
		});
	}

	public static void createNewCanvas(int width, int height, int background_color) {
		window.createNewCanvas(width, height, background_color);
	}
}
