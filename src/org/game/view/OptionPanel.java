package org.game.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.game.controller.ExitButtonListener;
import org.game.controller.OptionChangeControl;
import org.game.controller.OptionController;
import org.game.controller.StartButtonListener;
import org.game.domain.ComboBoxItem;
import org.game.model.GameModel;


public class OptionPanel extends JPanel implements ViewInterface {
	
	/**
	 * Serialization ID
	 */
	private static final long serialVersionUID = 7876237128462364878L;

	GameModel model;

	JFrameView frame;
	
	HashMap<String, Object> valueset;
	
	// TODO replace this set with their values
	JTextField name;
	JComboBox<ComboBoxItem<Color>> colors;
	JComboBox<String> gametype;
	
	JButton start;
	JButton exit;
	
	// Set the commands
	public static final String COLORCHANGE = "COLORCHANGE";
	public static final String TIMESET = "TIMESET";
	public static final String GAMETYPE = "GAMETYPE";
	
	public enum COMMANDS { COLORCHANGE, TIMESET, GAMETYPE };
	
	public OptionPanel(JFrameView frame, GameModel model) {
		this.frame = frame;
		this.model = model;

		init();
		initListeners();
		
		// hashmap
		valueset = new HashMap<>();
	}
	
	private void init() {	
		setLayout(new BorderLayout());
		setBackground(new Color(255, 228, 225));
		// TODO calculate the dimension based on the number of options.
		setPreferredSize(new Dimension(500, 150));	
		
		JPanel settingspanel = new JPanel();
		settingspanel.setLayout(new GridLayout(0, 2, 50, 10));
		settingspanel.setOpaque(true);
		
		// add name label
		JLabel nameLabel = new JLabel("What is your name? :");
		//nameLabel.setPreferredSize(new Dimension(100, 10));
		nameLabel.setMaximumSize(new Dimension(100, 50));
		
		name = new JTextField();
		name.setMaximumSize(new Dimension(100, 50));
		
		settingspanel.add(nameLabel);
		settingspanel.add(name);

		JLabel label = new JLabel("Select a background color :");
		//label.setPreferredSize(new Dimension(400, 30));
		label.setMaximumSize(new Dimension(100, 50));
		
		colors = new JComboBox<>();
		colors.addItem(new ComboBoxItem<Color>("Dawn", new Color(255, 235, 205)));
		colors.addItem(new ComboBoxItem<Color>("Pink", Color.PINK));
		colors.addItem(new ComboBoxItem<Color>("Green", Color.GREEN));
		
		
		colors.setAlignmentX(Component.CENTER_ALIGNMENT);
		colors.setMaximumSize(new Dimension(100, 50));
		colors.setPreferredSize(new Dimension(50, 50));
		
		settingspanel.add(label);
		settingspanel.add(colors);

		// create a label
		label = new JLabel("Select a game type :");
		label.setMaximumSize(new Dimension(100, 50));
		
		gametype = new JComboBox<>(new String[] {"MULTIPLY", "ADD"});
		gametype.setMaximumSize(new Dimension(100, 50));
		
		settingspanel.add(label);
		settingspanel.add(gametype);
		
		// Add Control buttons
		start = new JButton("Start");
		start.setOpaque(true);
		
		start.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		exit = new JButton("Exit");
		exit.setOpaque(true);
		
		exit.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// Add another flow layout to layout buttons
		JPanel flowpanel = new JPanel();
		flowpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

		flowpanel.add(start);
		flowpanel.add(exit);
		
		add(settingspanel, BorderLayout.CENTER);
		add(flowpanel, BorderLayout.PAGE_END);
	}
	
	private void initListeners() {
		// create option controller here
		OptionController optioncontroller = new OptionController(this, model);
		// initialize controller
		optioncontroller.init();
		
		OptionChangeControl combochange = new OptionChangeControl(this, model);
		colors.setActionCommand(COLORCHANGE);
		colors.addActionListener(combochange);
		
		gametype.setActionCommand(GAMETYPE);
		gametype.addActionListener(combochange);
		
		start.addActionListener(new StartButtonListener(this, model));
		exit.addActionListener(new ExitButtonListener(this));
	}
	
	// For the text component
	public String getValue() {
		return name.getText();
	}
	
	// get combo box reference
	public Color getColor() {
		// set the color in the controller using model
		return ((ComboBoxItem<Color>) colors.getSelectedItem()).getValue();
	}
	
	// On selecting combo box
	public void updateBackgroundColor(Color color) {
		frame.getContentPane().setBackground(color);
	}

	/**
	 * It depends on the frame that contains this.
	 */
	@Override
	public void start() {
		frame.start();	
	}

	@Override
	public void exit() {
		frame.exit();
	}
}