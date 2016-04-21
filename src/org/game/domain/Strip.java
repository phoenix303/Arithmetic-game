package org.game.domain;

import java.util.ArrayList;

import javax.swing.Timer;

public class Strip {
    
	// NOT static because each strip could have different strip size
    public int strip_height = 186;
    public int scroll_size = 1;

    ArrayList<int[]> list;

    Timer timer ;
    
    public Strip() {	
    	this(4); // By default, the no. of strips is 4
    }
    
    public Strip(int size) {
    	list = new ArrayList<>();
    	create(size);
    }
    
    public void create(int size) {
    	boolean blue = true;
    	int[] row ;
    	
    	for(int i = 0; i < size ; i++) {
    		row = new int[2];
    		row[0] = 186;
    		if(blue) {
    			row[1] = 0;
    			blue = false;
    		} else {
    			row[1] = 1;
    			blue = true;    			
    		}
    		list.add(row);
    	}
    }
    
    public ArrayList<int[]> getList() {
    	return this.list;
    }

    public void scroll() {
    	int[] first = list.get(0);
      	int scroll = scroll_size;
      	while( first[0] <= scroll ) {
      		scroll = scroll - first[0];
      		list.remove(0);
      		first = list.get(0);
      	}    	
      	
      	first[0] = first[0] - scroll;
      	
      	int[] end = list.get(list.size() - 1);
      	scroll = scroll_size;
   
      	if( end[0] + scroll > strip_height ) {
      		scroll = end[0] - (strip_height - scroll);
      		end[0] = strip_height;
      	}

      	if(end[0] + scroll <= strip_height) {
      		end[0] = end[0] + scroll;
      	} else if( scroll > 0) {
      		if(end[1] == 0) {
      			list.add(new int[] {scroll, 1});
      		} else {
      			list.add(new int[] {scroll, 0});
      		}
      	}
    }
    
    // Getter and setter methods for a strip height
    public void setStripHeight(int height) {
    	this.strip_height = height;
    }
    
    public int getStripHeight() {
    	return strip_height;
    }
    
    // Getter and setter methods for timer
    public void setTimer(Timer timer) {
    	this.timer = timer;
    }
    
    public void start() {
		timer.start();
	}
	
	public void stop() {
		timer.stop();
	}
}