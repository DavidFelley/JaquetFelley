/*
*PW Week *
*Exercise
*Author : Valentin Jaquet
*Creation date : 12 mai 2018
*/
package Component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class ButtonCreation extends JButton {
	String name;
	
	
	public ButtonCreation() {
		
	}
		
	public ButtonCreation(String name, ImageIcon image) {
		super(image);
		this.name = name;
		
		setName(name);
		setFocusable(false);
		//Supprime les bordures autour de chaque bouton
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentAreaFilled(false);
	}
	
	public ButtonCreation (ImageIcon image) {
		super(image);
		
		setFocusable(false);
		//Supprime les bordures autour de chaque bouton
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentAreaFilled(false);
	
	}
	
	public ButtonCreation(int width, int height) {		
		setMaximumSize(new Dimension(width, height));
		setMinimumSize(new Dimension(width, height));
		setPreferredSize(new Dimension(width, height));
		setFocusable(false);
		//Supprime les bordures autour de chaque bouton
		setBorder(new EmptyBorder(0, 0, 0, 0));
//		setContentAreaFilled(false);
	}
	
	
	public ButtonCreation(ImageIcon image, int width, int height) {
		super(image);
		
		setPreferredSize(new Dimension(width, height));
		setFocusable(false);
		//Supprime les bordures autour de chaque bouton
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentAreaFilled(false);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
