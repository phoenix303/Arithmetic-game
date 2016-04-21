package org.game.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.game.controller.GridController;
import org.game.domain.Coin;
import org.game.model.GameModel;

/**
 * Handles the game.
 * @author Divya
 */

public class GridPanel extends JPanel {
	/**
	 * Serialization ID
	 */
	private static final long serialVersionUID = 4636547472129102838L;

	GameModel model;
	
	JLabel questionlabel;
	
	public GridPanel( GameModel model) {	
		this.model = model;
		
		initUI();	
		initListeners();
	}
	
	private void initUI() {
		setLayout(new BorderLayout());
		setOpaque(true);

		// TODO Make color variable and therefore more flexible
		setBackground(Color.GRAY);
		
		setFocusable(true);
		requestFocus();
		setRequestFocusEnabled(true);
		
		// TODO make them flexible.
		setPreferredSize(new Dimension(400, 800));
		setMaximumSize(new Dimension(400, 800));
		
		// add Question panel to the game
		JPanel questionpanel = new JPanel(new BorderLayout());
		questionpanel.setOpaque(false);
		questionlabel = new JLabel("", SwingConstants.CENTER);
		questionpanel.add(questionlabel, BorderLayout.CENTER);
		
		add(questionpanel, BorderLayout.PAGE_START);
		updateQuestionLabel();
	}
	
	private void updateQuestionLabel() {
		questionlabel.setText(model.getQuestion().getText());
	}
	
	private void initListeners() {
		GridController controller = new GridController(this, model);
		controller.init();
		
		addKeyListener(controller);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        
		drawCoins(g2);
		drawCar(g2);
	}
	
	private void drawCoins(Graphics2D g2) {
		for(int i = 0; i < model.getCoins().size(); i++) {
			drawCoin(g2, model.getCoins().get(i), model.getCoins().get(i).getText());
		}
	}
	
	private void drawCar(Graphics2D g2) {
		if(model.getCar().isVisible()) {	    	
			g2.setColor(Color.GREEN);
			g2.fill(model.getCar());
		}
	}
	
	private void drawCoin(Graphics2D g2, Coin coin, String text) {
		if(coin.isVisible()) {		
			g2.setColor(Color.CYAN);
			g2.fill(coin);
			
			// add text
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("Monospaced", Font.BOLD, 10));
		    int x = (int) (coin.x  + coin.getWidth()/2);
		    int y = (int) (coin.y + coin.getHeight()/2) ;
		    g2.drawString(text, x, y);
		}
	}
	
	// update the panel
	public void update() {
		updateQuestionLabel();
	}
}