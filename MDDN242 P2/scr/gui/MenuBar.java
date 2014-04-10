package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

public class MenuBar extends JMenuBar {

	private static final long serialVersionUID = 7902340495914576436L;

	public MenuBar(){
		add(new FileMenu());
		add(new EditMenu());
		add(new WindowMenu());
		add(new HelpMenu());
	}
	
	private abstract class Menu extends JMenu{
		private static final long serialVersionUID = -3107449466076134996L;

		protected void addSeperator(){
			add(new JSeparator());
		}
	}
	
	private class FileMenu extends Menu{
		private static final long serialVersionUID = 6968007230090303537L;

		public FileMenu(){
			setText("File");
			setMnemonic('f');
			
			addNewItem();
			addOpenItem();
			addSeperator();
			addSaveItem();
			addSaveAsItem();
			addSeperator();
			addExportItem();
			addSeperator();
			addExitItem();
		}

		private void addNewItem() {
			JMenuItem item = new JMenuItem("New", KeyEvent.VK_N);
			item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
			add(item);
		}

		private void addOpenItem() {
			JMenuItem item = new JMenuItem("Open", KeyEvent.VK_O);
			item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
			add(item);
		}

		private void addSaveItem() {
			JMenuItem item = new JMenuItem("Save", KeyEvent.VK_S);
			item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
			add(item);
		}

		private void addSaveAsItem() {
			JMenuItem item = new JMenuItem("Save As...", KeyEvent.VK_A);
			item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));
			add(item);
		}
		
		private void addExportItem() {
			JMenuItem item = new JMenuItem("Export", KeyEvent.VK_E);
			item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK | ActionEvent.ALT_MASK | ActionEvent.SHIFT_MASK));
			add(item);
		}

		private void addExitItem() {
			JMenuItem item = new JMenuItem("Exit", KeyEvent.VK_X);
			item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			add(item);
		}
	}
	
	private class EditMenu extends Menu{
		private static final long serialVersionUID = -9060878878890264176L;

		public EditMenu(){
			setText("Edit");
			setMnemonic('e');
		}
	}
	
	private class WindowMenu extends Menu{
		private static final long serialVersionUID = 1064812318261781590L;

		public WindowMenu(){
			setText("Window");
			setMnemonic('w');
		}
	}
	
	private class HelpMenu extends Menu{
		private static final long serialVersionUID = 6886920979182077805L;

		public HelpMenu(){
			setText("Help");
			setMnemonic('h');
		}
	}
}
