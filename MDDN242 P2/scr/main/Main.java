package main;

import gui.MainWindow;

import javax.swing.SwingUtilities;

public class Main {
	
	private static MainWindow window;
	
	private Main(){}
	
	public static MainWindow getMainWindow(){
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
				window = new MainWindow();
			}
		});
	}
}
