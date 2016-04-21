package org.game.view;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JLayeredPane;

import org.game.model.GameModel;

/*
 * This class handles the road media strip animation
 */
public class RoadStripPane  extends JLayeredPane {
	
	/**
	 * Serialization ID
	 */
	private static final long serialVersionUID = 236472364562319124L;
	
	GameModel model;
	
	public RoadStripPane(GameModel model) {
		this.model = model;
		
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		setOpaque(true);
	}
	
	 // Paints all the components inside
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	
    	Graphics2D g2 = (Graphics2D) g;
    	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    	
    	drawRoadSide(g2);
    }
    
	// also repaint a roadside
	public void drawRoadSide(Graphics2D g2) {
		ArrayList<int[]> list = model.getRoadSide().getList();
		
		// Define a clip area   	
    	Rectangle rect = new Rectangle(0, 0, getWidth(), getHeight());
    	g2.setClip(rect);

    	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    	int y = 0;
        for(int i = list.size() - 1; i >= 0; i--) {
        	int[] value = list.get(i);
	    	 if(value[1] == 0) {
	    		 g2.setColor(Color.BLUE);
	    	     g2.fillRect(0, y, getWidth(), model.getRoadSide().getStripHeight());
	    	 } else {
	    		 g2.setColor(Color.RED);
	    	     g2.fillRect(0, y, getWidth(), model.getRoadSide().getStripHeight());
	    	 }
	         y = y + value[0];
        }
	}
}