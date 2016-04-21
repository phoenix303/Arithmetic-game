package org.game.domain;

import javax.swing.JComboBox;

public class ComboBoxItem<E> extends JComboBox<E>{

	String label;
	E value;
	
	public ComboBoxItem(String label, E value) {
		this.label = label;
		this.value = value;
	}
	
	public String getLabel() {
		return label;
	}
	
	public E getValue() {
		return value;
	}
	
	public String toString() {
		return label;
	}
}
