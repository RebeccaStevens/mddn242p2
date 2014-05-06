package gui.toolbars;

import gui.Toolbar;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

import main.Tool;
import processing.core.PApplet;
import tools.ToolEmitter;
import tools.ToolMove;
import tools.ToolPan;
import tools.ToolZoom;

public class ToolBox extends Toolbar{

	private static final long serialVersionUID = -57333252299444276L;
	
	private static final List<ToolButton> toolButtons = new ArrayList<ToolButton>();
	private static final int MOVE_TOOL_INDEX = 0;
	private static final int EMITTER_TOOL_INDEX = 1;
	private static final int PAN_TOOL_INDEX = 2;
	private static final int ZOOM_TOOL_INDEX = 3;
	
	public ToolBox(Frame parentFrame){
		super(parentFrame, true);
		setPreferredSize(new Dimension(78, 400));
		addButtons();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	private void addButtons(){
		Container content = getContentContainer();
		content.setLayout(new FlowLayout(FlowLayout.LEFT, 4, 4));
		content.add(new ToolButton(ToolMove.tool, "data/buttons/toolbox_move0.png", "data/buttons/toolbox_move1.png", "data/buttons/toolbox_move2.png"));
		content.add(new ToolButton(ToolEmitter.tool, "data/buttons/placeholder0.png",  "data/buttons/placeholder1.png",  "data/buttons/placeholder2.png"));
		content.add(new ToolButton(ToolPan.tool, "data/buttons/toolbox_pan0.png",  "data/buttons/toolbox_pan1.png",  "data/buttons/toolbox_pan2.png"));
		content.add(new ToolButton(ToolZoom.tool, "data/buttons/placeholder0.png",  "data/buttons/placeholder1.png",  "data/buttons/placeholder2.png"));
		
		toolButtons.get(MOVE_TOOL_INDEX).setSelected(true);
	}

	@Override
	public boolean canDockNorth(){
		return false;
	}

	@Override
	public boolean canDockSouth(){
		return false;
	}

	@Override
	public boolean canDockEast(){
		return true;
	}

	@Override
	public boolean canDockWest(){
		return true;
	}
	
	private class ToolButton extends JToggleButton {

		private static final long serialVersionUID = 1857049167726917102L;
		
		private Tool tool;
		
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
}