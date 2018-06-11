/*
*PW Week *
*Exercise
*Author : Valentin Jaquet
*Creation date : 12 mai 2018
*/
package Component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class ButtonCreation extends JButton {
	
	private String name;
	private ImageIcon image;
	private int id;
	private int width;
	private int height;

	public ButtonCreation()
	{
		setOpaque(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setPreferredSize(new Dimension(200,680));
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
		setContentAreaFilled(false);
	}
	
	public ButtonCreation(String name,int width, int height)
	{		
		setText(name);
		
		//Paramètre esthètique du bouton
		setMaximumSize(new Dimension(width, height));
		setMinimumSize(new Dimension(width, height));
		setPreferredSize(new Dimension(width, height));
		setFocusable(false);
		setContentAreaFilled(true);
		setBackground(Color.WHITE);
		
		//Font
		setFont(new Font("Arial", Font.ROMAN_BASELINE, 25));
		Color color = new Color(78,104,141);
		setForeground(color);
		setBorder(BorderFactory.createMatteBorder(1, 0, 0, 1, Color.LIGHT_GRAY));
		//Supprime les bordures autour de chaque bouton	
	}
			
	public int getId()
	{
		return id;
	}
	
}
