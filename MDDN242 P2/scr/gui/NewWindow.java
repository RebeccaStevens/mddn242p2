package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import main.Main;

public class NewWindow extends JDialog {

	private static final long serialVersionUID = 9187751988881264097L;

	private static final Color BG_COLOR = new Color(240, 240, 240);

	private static final Font body_font = new Font("Calibri", 0, 18);
	private static final Font header_font = new java.awt.Font("Cambria", 0, 24);
	
	private final String[] presets = new String[] { "Custom...", "720p (HD)", "1080p (FHD)", "2160p (4K UHD)", "1K", "2K", "4K" };

	private JButton ok_btn;
	private JButton cancel_btn;
	private JComboBox<String> presets_cb;
	private JLabel presets_lbl;
	private JLabel width_lbl;
	private JLabel height_lbl;
	private JSpinner height_spin;
	private JSpinner width_spin;
	private JLabel background_lbl;
	private JComboBox<String> background_cb;

	private DimensionChangeListener dimensionChangeListener;

	public NewWindow(Frame owner){
		super(owner, true);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("New");
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
		createPresetOptions();
		createWidthAndHeightOprions();
		createBackGroundOption();
		createTerminalButtons();

		layoutComponents();
		presets_cb.setSelectedIndex(1);
	}

	private void createPresetOptions() {
		presets_cb = new javax.swing.JComboBox<String>();
		presets_lbl = new javax.swing.JLabel();

		presets_lbl.setFont(header_font);
		presets_lbl.setText("Presets:");

		presets_cb.setFont(body_font);
		presets_cb.setModel(new javax.swing.DefaultComboBoxModel<String>(presets));
		presets_cb.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals(presets_cb.getActionCommand())){
					String selected = (String) presets_cb.getSelectedItem();
					if(selected == presets[1]){ 		// 720
						dimensionChangeListener.preventNextChange = 2;
						width_spin.setValue(1280);
						height_spin.setValue(720);
					}
					else if(selected == presets[2]){	// 1080
						dimensionChangeListener.preventNextChange = 2;
						width_spin.setValue(1920);
						height_spin.setValue(1080);
					}
					else if(selected == presets[3]){	// 2160
						dimensionChangeListener.preventNextChange = 2;
						width_spin.setValue(3840);
						height_spin.setValue(2160);
					}
					else if(selected == presets[4]){	// 1K
						dimensionChangeListener.preventNextChange = 2;
						width_spin.setValue(1024);
						height_spin.setValue(540);
					}
					else if(selected == presets[5]){	// 2K
						dimensionChangeListener.preventNextChange = 2;
						width_spin.setValue(2048);
						height_spin.setValue(1080);
					}
					else if(selected == presets[6]){	// 4K
						dimensionChangeListener.preventNextChange = 2;
						width_spin.setValue(4096);
						height_spin.setValue(2160);
					}
				}
			}
		});
	}

	private void createWidthAndHeightOprions() {
		width_lbl = new javax.swing.JLabel();
		height_lbl = new javax.swing.JLabel();
		height_spin = new javax.swing.JSpinner();
		width_spin = new javax.swing.JSpinner();
		
		dimensionChangeListener = new DimensionChangeListener();

		width_lbl.setFont(body_font);
		width_lbl.setText("Width:");

		width_spin.setFont(body_font);
		width_spin.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));

		height_lbl.setFont(body_font);
		height_lbl.setText("Height:");

		height_spin.setFont(body_font);
		height_spin.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		
		width_spin.addChangeListener(dimensionChangeListener);
		height_spin.addChangeListener(dimensionChangeListener);
	}

	private void createBackGroundOption() {
		background_lbl = new javax.swing.JLabel();
		background_lbl.setFont(body_font);
		background_lbl.setText("Background Colour:");

		background_cb = new javax.swing.JComboBox<String>();
		background_cb.setFont(body_font);
		background_cb.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Black", "White", "Other" }));
	}

	private void createTerminalButtons() {
		ok_btn = new javax.swing.JButton();
		cancel_btn = new javax.swing.JButton();

		ok_btn.setFont(body_font);
		ok_btn.setText("OK");
		ok_btn.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				Main.createNewCanvas((int)width_spin.getValue(), (int)height_spin.getValue());
				NewWindow.this.dispose();
			}
		});

		cancel_btn.setFont(body_font);
		cancel_btn.setText("Cancel");
		cancel_btn.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				NewWindow.this.dispose();
			}
		});
	}

	private void layoutComponents() {
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addComponent(presets_lbl)
										.addGap(18, 18, 18)
										.addComponent(presets_cb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(background_lbl)
														.addComponent(width_lbl)
														.addComponent(height_lbl))
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
																.addComponent(background_cb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(width_spin)
																.addComponent(height_spin)))
																.addGroup(layout.createSequentialGroup()
																		.addComponent(ok_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																		.addComponent(cancel_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
																		.addContainerGap())
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(presets_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(presets_lbl))
								.addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(width_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(width_spin, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(height_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGroup(layout.createSequentialGroup()
														.addGap(1, 1, 1)
														.addComponent(height_spin, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(background_cb, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(background_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
																.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(cancel_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																		.addComponent(ok_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
																		.addContainerGap())
				);

		pack();
	}
	
	
	private class DimensionChangeListener implements ChangeListener {
		public int preventNextChange;

		@Override public void stateChanged(ChangeEvent e) {
			if(preventNextChange > 0){
				preventNextChange--;
				return;
			}
			presets_cb.setSelectedIndex(0);
		}
	};
}
