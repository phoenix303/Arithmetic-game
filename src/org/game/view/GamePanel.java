package org.game.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import org.game.controller.StripController;
import org.game.model.GameModel;

public class GamePanel extends JPanel {
	
	/**
	 * Serialization ID
	 */
	private static final long serialVersionUID = 5376828962362763776L;

	private GridPanel gridpanel;
	private RoadStripPane roadstrip;

	GameModel model;
	
	public GamePanel(GameModel model) {
		this.model = model;

		init();
		initUI();
	}
	
	public void initUI() {
		setLayout(new BorderLayout());
		// TODO Try to override a method getPreferredsize. With default width and height
		setPreferredSize(new Dimension(500, 800));
		setOpaque(true);
		
		roadstrip.add(gridpanel);
		add(roadstrip, BorderLayout.CENTER);
	}
	
	public void init() {
		roadstrip = new RoadStripPane(model);
		gridpanel = new GridPanel(model);
		
		StripController controller = new StripController(roadstrip, model);
		controller.animate(); 
	}

	public void requestFocus() {
		gridpanel.requestFocus();
	}
	
	public void update() {
		gridpanel.update();
	}
}