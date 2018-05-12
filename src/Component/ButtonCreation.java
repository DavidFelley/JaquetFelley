/*
*PW Week *
*Exercise
*Author : Valentin Jaquet
*Creation date : 12 mai 2018
*/
package Component;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ButtonCreation extends JButton {
	
	public ButtonCreation() {
		setLayout(new BorderLayout());  
		
	}
	
	public ButtonCreation(String name, ImageIcon image) {
		super(image);
		
		setLayout(new BorderLayout());
		setName(name);
	}
	
	public ButtonCreation (ImageIcon image) {
		super(image);
		
		setLayout(new BorderLayout());		
	}
	
	public ButtonCreation(ImageIcon image, int width, int height) {
		super(image);
		
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(width, height));
	}
	
	
	
	
}
