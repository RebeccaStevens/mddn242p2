package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

import main.Tool;
import processing.core.PApplet;

public class ToolButton extends JToggleButton {

	private static final long serialVersionUID = 1857049167726917102L;
	
	private static final Set<ToolButton> toolButtons = new HashSet<ToolButton>();
	
	private Tool tool;
	
	public ToolButton(Tool tool){
		setup(tool);
	}
	
	@SuppressWarnings("deprecation")
	public ToolButton(Tool tool, String stnd, String over, String down){
		PApplet tpa = new PApplet();	// create a new PApplet so we can use it to load images, it can be thrown away after
		
		ImageIcon img_stnd = new ImageIcon(tpa.loadImage(stnd).getImage());
		ImageIcon img_over = new ImageIcon(tpa.loadImage(over).getImage());
		ImageIcon img_down = new ImageIcon(tpa.loadImage(down).getImage());
		
		setIcon(img_stnd);
		setRolloverIcon(img_over);
		setPressedIcon(img_down);
		setSelectedIcon(img_down);
		
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		
		setPreferredSize(new Dimension(32, 32));
		
		setup(tool);
	}

	private void setup(Tool tool) {
		this.tool = tool;
		addActionListener(new ToolButtonListener());
		toolButtons.add(this);
	}
	
	public void delete(){
		toolButtons.remove(this);
	}
	
	private class ToolButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			for(ToolButton tb : toolButtons){
				tb.setSelected(false);
			}
			ToolButton.this.setSelected(true);
			Tool.setCurrentTool(tool);
		}
	}

}
