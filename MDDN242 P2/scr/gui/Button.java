package gui;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import processing.core.PApplet;

public class Button extends JButton {

	private static final long serialVersionUID = 1857049167726917102L;
	
	public Button(){
		
	}
	
	@SuppressWarnings("deprecation")
	public Button(String stnd, String over, String down){
		PApplet tpa = new PApplet();	// create a new PApplet so we can use it to load images, it can be thrown away after
		
		setIcon(new ImageIcon(tpa.loadImage(stnd).getImage()));
		setRolloverIcon(new ImageIcon(tpa.loadImage(over).getImage()));
		setPressedIcon(new ImageIcon(tpa.loadImage(down).getImage()));
		
		setBorderPainted(false);
		setContentAreaFilled(false);
		
		setPreferredSize(new Dimension(32, 32));
	}

}
