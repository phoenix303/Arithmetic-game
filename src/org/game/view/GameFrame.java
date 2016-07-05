package org.game.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.game.common.GameObserver;
import org.game.model.GameModel;

/*
 * Main frame of the application.
 */
//public class GameFrame extends JFrameView implements ScoreObserver , GameObserver {
public class GameFrame extends JFrameView implements GameObserver {

	/**
	 * Serialization ID
	 */
	private static final long serialVersionUID = 3746587467263746787L;

	// TODO Use CardLayout to add GamePanel and OptionPanel
	GamePanel gamepanel;
	private OptionPanel optionpanel;

	JLabel scorelabel;

	private GameModel model;
	
	public GameFrame(GameModel model) {
		this.model = model;
		
		initUI();
		
		this.model.addObserver(this);
	}
	
	private void initUI() {
		setTitle("Car Game");
		//setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setSize(new Dimension(300, 400));
		panel.setOpaque(true);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
		setContentPane(panel);

		// Add option panel
		optionpanel = new OptionPanel(this, model);

		updateBackgroundColor(model.getBackgoundColor());
		add(optionpanel);

		// set resizable to false
		setResizable(false);
		makeFrameVisible();
	}
	
	private void remove(JComponent component) {
		getContentPane().remove(component);
	}
	
	private void add(JComponent component) {
		getContentPane().add(component);
	}
	
	private void addGamepanel() {		
		// TODO Try to separate this from the main panel
		JPanel scorepanel = new JPanel(new BorderLayout());
		scorepanel.setOpaque(false);
		
		// TODO create a custom score JLabel so that it can be used like "Score: " + score.
		scorelabel = new JLabel(String.valueOf(model.getScore()), SwingConstants.CENTER);
		scorelabel.setOpaque(false);
		scorelabel.setPreferredSize(new Dimension(100, 100));
		scorepanel.add(scorelabel, BorderLayout.CENTER);
		add(scorelabel);
		
		gamepanel = new GamePanel(model);
		add(gamepanel);

		// enable resizing
		setResizable(true);
		makeFrameVisible();
	}
	
	protected void makeFrameVisible() {
		pack();
		setVisible(true);
	}
	
	public void updateBackgroundColor(Color color) {
		getContentPane().setBackground(color);
	}
	
	private void setScoreLabel() {
		scorelabel.setText(String.valueOf(model.getScore()));
	}

	@Override
	public void start() {
		remove(optionpanel);
		addGamepanel();
		gamepanel.requestFocus();
	}

	@Override
	public void exit() {
		dispose();
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
	
	@Override
	public void update() {
		gamepanel.update();
	}
	
	// It is an observer which is notified when the coins hit
	@Override
	public void updateScore() {
		setScoreLabel();
	}

	@Override
	public void updateEnd() {
		// Remove game panel when the game has been ended
		remove(gamepanel);
		
		// Another another panel that contains the username(with other details) and score.
		JPanel panel = new JPanel(new GridLayout(0, 1));
		
		JLabel name = new JLabel("Hi " + model.getName(), SwingConstants.CENTER);
		panel.add(name);
		panel.add(scorelabel);
		
		add(panel);
		
		makeFrameVisible();
	}
}
