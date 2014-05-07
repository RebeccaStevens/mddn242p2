package gui.toolbars;

import gui.Canvas;
import gui.Toolbar;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import main.Main;

public class TimeLine extends Toolbar{

	private static final long serialVersionUID = -57333252299444276L;
	
	private static final Font body_font = new Font("Calibri", 0, 18);
	private JSpinner frame_spin;
	
	public TimeLine(Frame parentFrame){
		super(parentFrame, true);
		setPreferredSize(new Dimension(640, 250));
		
		initComponents();
	}

	private void initComponents() {
		JPanel pan = new JPanel();
		pan.setOpaque(false);
		
		JLabel lbl = new JLabel();
		lbl.setText("Frame:");
		lbl.setFont(body_font);
		
		frame_spin = new JSpinner();
		frame_spin.setFont(body_font);
		frame_spin.setPreferredSize(new Dimension(80,  32));
		frame_spin.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		frame_spin.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				Canvas c = Main.getCanvas();
				if(c == null) return;
				Main.getTime().setCurrentFrame((int) frame_spin.getValue());
				c.redraw();
			}
		});
		
		pan.add(lbl);
		pan.add(frame_spin);
		getContentContainer().add(pan);
	}
	
	public void reset(){
		frame_spin.setValue(1);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public boolean canDockNorth(){
		return true;
	}

	@Override
	public boolean canDockSouth(){
		return true;
	}

	@Override
	public boolean canDockEast(){
		return false;
	}

	@Override
	public boolean canDockWest(){
		return false;
	}
}