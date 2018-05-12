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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class ButtonCreation extends JButton {
	
	public ButtonCreation() {
//		setLayout(new BorderLayout());  
		
	}
		
	public ButtonCreation(String name, ImageIcon image) {
		super(image);
		
//		setLayout(new BorderLayout());
		setName(name);
	}
	
	public ButtonCreation (ImageIcon image) {
		super(image);
		setPreferredSize(new Dimension (100,100)); // à supprimer
		setBackground(new Color(200,0,0));
//		setLayout(new BorderLayout());		
		setFocusable(false);
		setBorder(new EmptyBorder(0, 0, 0, 0));
//		setContentAreaFilled(false);
	}
	
	public ButtonCreation(ImageIcon image, int width, int height) {
		super(image);
		
//		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(width, height));
	}
	
	
	
	
}
