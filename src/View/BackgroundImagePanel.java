/*
*PW Week *
*Exercise
*Author : Valentin Jaquet
*Creation date : 7 mai 2018
*/
package View;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundImagePanel extends JPanel{
	ImageIcon image;
	
	public BackgroundImagePanel(ImageIcon Image) {
		this.image = image;
	}
	
	@Override
	protected void paintComponent(Graphics g) 
	{
		g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), null);
	}

}
