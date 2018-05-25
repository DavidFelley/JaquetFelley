/*
*Author David Felley
*Created on 22 mai 2018
*For JaquetFelley
*/

package Component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MenuBarre extends JPanel{

	ButtonCreation boutonWest ;
	ButtonCreation boutonEast ;
	String titre ;
	
	//Constructeur MenuBarre avec le boutonEast
	public MenuBarre(ButtonCreation boutonEast, String titre)
	{
	
		this.boutonEast = boutonEast;
		this.titre = titre ;
		
		//Paramètre fixe à la barre de menu
		setPreferredSize(new Dimension(480, 40));
		setBackground(new Color(78, 104, 141));
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(0, 5, 0, 5));
		
		JLabel entete = new JLabel(titre);
		entete.setFont(new Font(null, Font.BOLD, 20));
		entete.setHorizontalAlignment(JLabel.CENTER);
		entete.setForeground(Color.WHITE);
		add(entete, BorderLayout.CENTER);
		
		add(boutonEast, BorderLayout.EAST);
	}
	
	//Constructeur MenuBarre avec les boutonEast et boutonWest
	public MenuBarre(ButtonCreation boutonWest, ButtonCreation boutonEast, String titre)
	{
		this.boutonWest = boutonWest ;
		this.boutonEast = boutonEast;
		this.titre = titre ;
		
		//Paramètre fixe à la barre de menu
		setPreferredSize(new Dimension(480, 40));
		setBackground(new Color(78, 104, 141));
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(0, 5, 0, 5));
		
		add(boutonWest, BorderLayout.WEST);
		
		JLabel entete = new JLabel(titre);
		entete.setFont(new Font(null, Font.BOLD, 20));
		entete.setHorizontalAlignment(JLabel.CENTER);
		entete.setForeground(Color.WHITE);
		add(entete, BorderLayout.CENTER);
		
		add(boutonEast, BorderLayout.EAST);
	}

	
	
	
	public ButtonCreation getBoutonEast() {
		return boutonEast;
	}
	
	public ButtonCreation getBoutonWest() {
		return boutonWest;
	}
	
	

}
