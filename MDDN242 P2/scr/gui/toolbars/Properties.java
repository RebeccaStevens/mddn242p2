package gui.toolbars;

import entities.Attribute;
import entities.Entity;
import gui.MainWindow;
import gui.Toolbar;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import main.Main;

public class Properties extends Toolbar{

	private static final long serialVersionUID = -57333252299444276L;
	private List<AttrComponent<?>> attrComps;
	
	public Properties(Frame parentFrame){
		super(parentFrame, true);
		setPreferredSize(new Dimension(300, 480));
		
		attrComps = new ArrayList<AttrComponent<?>>();
		Container c = getContentContainer();
		c.setLayout(new FlowLayout(FlowLayout.CENTER, 0, -2));
	}
	
	public void setSelectedEntity(Entity entity) {
		if(entity == null){
			reset(true);
			return;
		}
		reset(false);
		for(Attribute<?> attr : entity.getAttributes()){
			AttrComponent<?> ac = new AttrComponent(attr);
			attrComps.add(ac);
			getContentContainer().add(ac);
		}
	}

	@Override
	public void reset() {
		reset(true);
	}
	
	public void reset(boolean redraw){
		getContentContainer().removeAll();
		attrComps.clear();
		if(redraw){
			revalidate();
			repaint();
		}
	}
	
	public void update() {
		for(AttrComponent<?> ac : attrComps){
			ac.update();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
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
	
	private class AttrComponent<T> extends JPanel{
		private static final long serialVersionUID = -7857747367310441214L;
		
		private JLabel key_lbl;
		private JTextField value_txt;

		private Attribute<T> attr;

		public AttrComponent(Attribute<T> attr) {
			this.attr = attr;
			setPreferredSize(new Dimension(298, 28));
			setBackground(Toolbar.BG_COLOR.brighter().brighter());
			setBorder(BorderFactory.createLineBorder(MainWindow.BG_COLOR, 2));
			setLayout(new FlowLayout(FlowLayout.CENTER, 0, 2));
			
			key_lbl = new JLabel();
			value_txt = new JTextField();
			
			key_lbl.setPreferredSize(new Dimension(95, 20));
			value_txt.setPreferredSize(new Dimension(195, 20));
			
			value_txt.getDocument().addDocumentListener(new DocumentListener() {
				@Override public void removeUpdate(DocumentEvent e) { action(); }
				@Override public void insertUpdate(DocumentEvent e) { action(); }
				@Override public void changedUpdate(DocumentEvent e) { action(); }
				
				private void action(){
					AttrComponent.this.attr.valueChange(value_txt.getText());
					Main.getCanvas().redraw();
					Main.getMainWindow().getOutline().update();
				}
			});
			
			value_txt.addFocusListener(new FocusListener() {
				@Override public void focusLost(FocusEvent e) {}
				@Override public void focusGained(FocusEvent e) {
					value_txt.selectAll();
				}
			});
			
			update();
			
			add(key_lbl);
			add(value_txt);
		}

		public void update() {
			key_lbl.setText(attr.getKey()+":");
			value_txt.setText(attr.getValue().toString());
			
		}
	}
}