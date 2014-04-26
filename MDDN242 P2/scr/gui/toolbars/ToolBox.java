package gui.toolbars;

import gui.ToolButton;
import gui.Toolbar;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;

import main.tools.Emitter;
import main.tools.Move;

public class ToolBox extends Toolbar{

	private static final long serialVersionUID = -57333252299444276L;
	private ToolButton moveTool;
	private ToolButton emitterTool;
	
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
		content.add(moveTool	= new ToolButton(Move.tool, "data/buttons/toolbox_move0.png", "data/buttons/toolbox_move1.png", "data/buttons/toolbox_move2.png"));
		content.add(emitterTool	= new ToolButton(Emitter.tool, "data/buttons/placeholder0.png",  "data/buttons/placeholder1.png",  "data/buttons/placeholder2.png"));
		content.add(new ToolButton(null, "data/buttons/placeholder0.png",  "data/buttons/placeholder1.png",  "data/buttons/placeholder2.png"));
		
		moveTool.setSelected(true);
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
}