package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;
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
	private JLabel frameRate_lbl;
	private JSpinner height_spin;
	private JSpinner width_spin;
	private JSpinner frameRate_spin;
	private JLabel background_lbl;
	private JPanel bgColorChooser;
	private JComboBox<ColorOption> background_cb;
	private JPanel background_preview;

	private DimensionChangeListener dimensionChangeListener;

	
	public NewWindow(Frame owner){
		super(owner, true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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
		createWidthAndHeightOptions();
		createFrameRateOptions();
		createBackGroundOption();
		createTerminalButtons();

		layoutComponents();
		presets_cb.setSelectedIndex(1);
	}

	private void createPresetOptions() {
		presets_cb = new JComboBox<String>();
		presets_lbl = new JLabel();

		presets_lbl.setFont(header_font);
		presets_lbl.setText("Presets:");

		presets_cb.setFont(body_font);
		presets_cb.setModel(new DefaultComboBoxModel<String>(presets));
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

	private void createWidthAndHeightOptions() {
		width_lbl = new JLabel();
		height_lbl = new JLabel();
		height_spin = new JSpinner();
		width_spin = new JSpinner();
		
		dimensionChangeListener = new DimensionChangeListener();

		width_lbl.setFont(body_font);
		width_lbl.setText("Width:");

		width_spin.setFont(body_font);
		width_spin.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));

		height_lbl.setFont(body_font);
		height_lbl.setText("Height:");

		height_spin.setFont(body_font);
		height_spin.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		
		width_spin.addChangeListener(dimensionChangeListener);
		height_spin.addChangeListener(dimensionChangeListener);
	}

	private void createFrameRateOptions() {
		frameRate_lbl = new JLabel();
		frameRate_lbl.setFont(body_font);
		frameRate_lbl.setText("Frame Rate:");

		frameRate_spin = new JSpinner();
		frameRate_spin.setFont(body_font);
		frameRate_spin.setModel(new SpinnerNumberModel(Integer.valueOf(24), Integer.valueOf(1), null, Integer.valueOf(1)));
	}

	private void createBackGroundOption() {
		background_lbl = new JLabel();
		background_lbl.setFont(body_font);
		background_lbl.setText("Background Colour:");
	
		background_cb = new JComboBox<ColorOption>();
		background_cb.setFont(body_font);
		background_cb.setPreferredSize(new Dimension(100, 26));
		background_cb.setModel(new DefaultComboBoxModel<ColorOption>(new ColorOption[] {
				new ColorOption("Black", 0xFF000000),
				new ColorOption("White", 0xFFFFFFFF),
				new ColorOption("Other", 0xFF000000)
				}));
		background_cb.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				ColorOption chose = (ColorOption)(background_cb.getSelectedItem());
				Color color = null;
				if(chose.getName().equals("Other")){
					color = JColorChooser.showDialog(NewWindow.this, "Background Colour", background_preview.getBackground());
					if(color == null){
						background_cb.setSelectedIndex(0);//(lastSelectedIndex);
					}
					chose.setRGB(0xFF000000 | color.getRGB());
				}
				else{
					color = ((ColorOption)(background_cb.getSelectedItem())).getColor();
				}
				background_preview.setBackground(color);
			}
		});
		
		background_preview = new JPanel();
		background_preview.setBackground(Color.BLACK);
		background_preview.setPreferredSize(new Dimension(24, 24));
		background_preview.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
		
		bgColorChooser = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		bgColorChooser.setPreferredSize(new Dimension(135, 26));
		bgColorChooser.add(background_preview);
		bgColorChooser.add(Box.createHorizontalStrut(9));
		bgColorChooser.add(background_cb);
	}

	private void createTerminalButtons() {
		ok_btn = new JButton();
		cancel_btn = new JButton();

		ok_btn.setFont(body_font);
		ok_btn.setText("OK");
		ok_btn.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				Main.createNewCanvas(getCanvasWidth(), getCanvasHeight(), getCanvasBackgroundColor());
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

	private int getCanvasWidth() {
		return (int)width_spin.getValue();
	}

	private int getCanvasHeight() {
		return (int)height_spin.getValue();
	}

	private int getCanvasBackgroundColor() {
		return ((ColorOption)(background_cb.getSelectedItem())).getRGB();
	}

	private void layoutComponents() {
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addComponent(presets_lbl)
										.addGap(18, 18, 18)
										.addComponent(presets_cb, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
														.addComponent(background_lbl)
														.addComponent(width_lbl)
														.addComponent(height_lbl)
														.addComponent(frameRate_lbl))
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
																.addComponent(bgColorChooser, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(width_spin)
																.addComponent(height_spin)
																.addComponent(frameRate_spin)))
																.addGroup(layout.createSequentialGroup()
																		.addComponent(ok_btn, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																		.addComponent(cancel_btn, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
																		.addContainerGap())
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(presets_cb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(presets_lbl))
								.addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(width_lbl, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
										.addComponent(width_spin, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(height_lbl, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
												.addComponent(height_spin, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
														.addComponent(frameRate_lbl, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
														.addComponent(frameRate_spin, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
														.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
															.addComponent(bgColorChooser, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
															.addComponent(background_lbl, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
															.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
															.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																	.addComponent(cancel_btn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																	.addComponent(ok_btn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
																	.addContainerGap())
				);
		pack();
	}

	/**
	 * Hack to get around listener loop
	 * @author Mike Stevens
	 */
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
	
	private class ColorOption{
		private String name;
		private int rgb;
		
		public ColorOption(String name, int argb){
			this.name = name;
			this.rgb = argb;
		}
		
		public String getName() {
			return name;
		}
		
		public int getRGB() {
			return rgb;
		}
		
		public void setRGB(int rgb) {
			this.rgb = 0xFF000000 | rgb;
		}
		
		public Color getColor() {
			return new Color(rgb);
		}
		
		@Override
		public String toString() {
			return getName();
		}
	}
}
