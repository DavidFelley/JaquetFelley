/*
*PW Week *
*Exercise
*Author : Valentin Jaquet
*Creation date : 12 mai 2018
*/
package Component;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class ButtonCreation extends JButton {
	
	private String name;
	private ImageIcon image;
	private int id;

	public ButtonCreation()
	{
		setOpaque(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setPreferredSize(new Dimension(200,680));
	}
	
	public ButtonCreation(String name)
	{
		setBackground(Color.white);
		setBorderPainted(false);
		setFocusable(false);
		setText(name);
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
		this.image = image ;
		
		setFocusable(false);
		//Supprime les bordures autour de chaque bouton
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentAreaFilled(false);
	
	}
	
	public ButtonCreation (ImageIcon image, int id) {
		super(image);
		this.image = image ;
		this.id = id;
		
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

	public ImageIcon getImage() {
		return image;
	}	
	
	public int getId() {
		return id;
	}
	
}
