package gui.toolbars;

import gui.Button;
import gui.Toolbar;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;

public class ToolBox extends Toolbar{

	private static final long serialVersionUID = -57333252299444276L;
	
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
		content.add(new Button("data/buttons/toolbox_move0.png", "data/buttons/toolbox_move1.png", "data/buttons/toolbox_move2.png"));
		content.add(new Button("data/buttons/toolbox_move0.png", "data/buttons/toolbox_move1.png", "data/buttons/toolbox_move2.png"));
		content.add(new Button("data/buttons/toolbox_move0.png", "data/buttons/toolbox_move1.png", "data/buttons/toolbox_move2.png"));
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