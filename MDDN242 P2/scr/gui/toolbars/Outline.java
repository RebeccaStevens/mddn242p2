package gui.toolbars;

import entities.Entity;
import gui.Canvas;
import gui.MainWindow;
import gui.Toolbar;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Main;

public class Outline extends Toolbar{

	private static final long serialVersionUID = -57333252299444276L;
	
	private static final Font body_font = new Font("Calibri", 0, 12);
	
	private Map<Entity, EntComponent> entityComps;
	
	public Outline(Frame parentFrame){
		super(parentFrame, true);
		setPreferredSize(new Dimension(200, 480));
		entityComps = new HashMap<Entity, EntComponent>();
		
		Container c = getContentContainer();
		c.setLayout(new FlowLayout(FlowLayout.CENTER, 0, -2));
	}
	
	public void add(Entity entity) {
		EntComponent ec = new EntComponent(entity);
		EntComponent old = entityComps.put(entity, ec);
		
		Container c = getContentContainer();
		if(old != null) remove(old);
		c.add(ec);
		revalidate();
		repaint();
	}
	
	public void remove(Entity entity){
		getContentContainer().remove(entityComps.remove(entity));
		revalidate();
		repaint();
	}
	
	public void reset() {
		getContentContainer().removeAll();
		entityComps.clear();
	}

	public void setSelectedEntity(Entity entity) {
		for(EntComponent ec : entityComps.values()){
			ec.setSelected(ec.entity == entity);
		}
	}
	
	public void update() {
		for(EntComponent ec : entityComps.values()){
			ec.update();
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
	
	private class EntComponent extends JPanel{
		private static final long serialVersionUID = -2835108056923907818L;
		
		private Entity entity;
		private JLabel lbl;

		public EntComponent(Entity ent) {
			this.entity = ent;
			setPreferredSize(new Dimension(198, 28));
			
			setBackground(Toolbar.BG_COLOR.brighter());
			setBorder(BorderFactory.createLineBorder(MainWindow.BG_COLOR, 2));
			
			lbl = new JLabel();
			lbl.setText(ent.getName());
			add(lbl);
			
			setSelected(true);
			
			addMouseListener(new MouseListener() {
				@Override public void mouseReleased(MouseEvent e) {
					Canvas c = Main.getCanvas();
					c.setSelectedEntity(entity);
					c.redraw();
				}
				
				@Override public void mousePressed(MouseEvent e) {}
				@Override public void mouseExited(MouseEvent e) {}
				@Override public void mouseEntered(MouseEvent e) {}
				
				@Override public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() >= 2 && !e.isConsumed()) {
					     e.consume();
					     // rename option
					}
				}
			});
		}
		
		public void setSelected(boolean b) {
			if(b){
				lbl.setFont(lbl.getFont().deriveFont(Font.BOLD));
				lbl.setForeground(Color.BLACK);
			}
			else{
				lbl.setFont(body_font);
				lbl.setForeground(new Color(30, 30, 30));
			}
		}

		public void update() {
			if(lbl != null && entity != null){
				lbl.setText(entity.getName());
			}
		}
	}
}