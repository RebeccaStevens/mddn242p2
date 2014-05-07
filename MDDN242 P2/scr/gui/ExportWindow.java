package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import main.Main;

public class ExportWindow extends JDialog {

	private static final long serialVersionUID = 9187751988881264097L;

	private static final Color BG_COLOR = new Color(240, 240, 240);

	private static final Font body_font = new Font("Calibri", 0, 18);
	private static final Font header_font = new java.awt.Font("Cambria", 0, 24);

	private JButton frame_btn;
	private JButton sequence_btn;
	private JButton cancel_btn;

	
	public ExportWindow(Frame owner){
		super(owner, true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Export");
		setSize(300, 400);
		setResizable(false);
		setType(java.awt.Window.Type.POPUP);

		// create content pane
		JPanel content = new JPanel();
		content.setBackground(BG_COLOR);
		setContentPane(content);

		initComponents();

		setLocationRelativeTo(null);	// center the window on the screen
		setVisible(true);				// display the window
	}

	private void initComponents() {
		createTerminalButtons();
		add(frame_btn);
		add(sequence_btn);
		add(cancel_btn);
	}

	private void createTerminalButtons() {
		frame_btn = new JButton();
		sequence_btn = new JButton();
		cancel_btn = new JButton();

		frame_btn.setFont(body_font);
		frame_btn.setText("Export Current Frame");
		frame_btn.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				JFileChooser jc = new JFileChooser();
				jc.setDialogTitle("Export Current Frame...");
				if(jc.showSaveDialog(ExportWindow.this) == JFileChooser.APPROVE_OPTION){
					Main.getCanvas().exportFrame(jc.getSelectedFile().getAbsolutePath(), Main.getTime().getCurrentFrame());
				}
				ExportWindow.this.dispose();
			}
		});
		
		sequence_btn.setFont(body_font);
		sequence_btn.setText("Export Image Sequence");
		sequence_btn.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				JFileChooser jc = new JFileChooser();
				jc.setDialogTitle("Export Image Sequence...");
				if(jc.showSaveDialog(ExportWindow.this) == JFileChooser.APPROVE_OPTION){
					String p = jc.getSelectedFile().getAbsolutePath().trim();
					int a = p.lastIndexOf('.');
					int b = p.lastIndexOf('\\');
					String ext = "";
					if(a > b){
						ext = p.substring(a, p.length());
						p = p.substring(0, a);
					}
					
					int lastFrame = Main.getTime().getCurrentFrame();
					for(int i=1; i<=lastFrame; i++){
						String path = String.format("%s%05d%s", p, i, ext);
						Main.getCanvas().exportFrame(path, i);
					}
				}
				ExportWindow.this.dispose();
			}
		});

		cancel_btn.setFont(body_font);
		cancel_btn.setText("Cancel");
		cancel_btn.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				ExportWindow.this.dispose();
			}
		});
	}
}
