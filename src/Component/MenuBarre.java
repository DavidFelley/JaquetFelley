package Component;
/**
 * ------------------------------------------------------------------------------------------------------ <br/>
 * Classe : MenuBarre <br/>
 * ------------------------------------------------------------------------------------------------------ <br/>
 * Auteur : David Felley et Valentin Jaquet <br/>
 * Description de la classe : Cette classe gère les menus de toutes les applications <br/>
 * ------------------------------------------------------------------------------------------------------ <br/>
 * Remarque : - <br/>
 * ------------------------------------------------------------------------------------------------------ <br/>
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MenuBarre extends JPanel
{

	ButtonCreation boutonWest ;
	ButtonCreation boutonEast ;
	Color color ;
	String titre ;
	
	/**
	 * Constrcuteur de la menu barre avec titre et couleur de fond
	 * @param titre
	 * @param color
	 */
	public MenuBarre(String titre, Color color )
	{
		this.titre = titre ;
		this.color = color;
		//Paramètre fixe à la barre de menu
		setPreferredSize(new Dimension(480, 40));
		setBackground(color);
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(0, 0, 0, 0));

		JLabel entete = new JLabel(titre);
		entete.setFont(new Font(null, Font.BOLD, 20));
		entete.setHorizontalAlignment(JLabel.CENTER);
		entete.setForeground(Color.WHITE);
		add(entete, BorderLayout.CENTER);
	}

	/**
	 * Constructeur de la menu barre avec titre, couleur et un bouton à droite
	 * @param titre
	 * @param boutonEast
	 * @param color
	 */
	public MenuBarre(String titre, ButtonCreation boutonEast, Color color )
	{
		this.titre = titre ;
		this.boutonEast = boutonEast;
		this.color = color;
		
		//Paramètre fixe à la barre de menu
		setPreferredSize(new Dimension(480, 40));
		setBackground(color);
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(0, 40, 0, 5));

		JLabel entete = new JLabel(titre);
		entete.setFont(new Font(null, Font.BOLD, 20));
		entete.setHorizontalAlignment(JLabel.CENTER);
		entete.setForeground(Color.WHITE);
		add(entete, BorderLayout.CENTER);

		add(boutonEast, BorderLayout.EAST);
	}

	/**
	 * Constructeur de la menu barre avec couleur, un bouton à gauche et un bouton à droite
	 * @param boutonWest
	 * @param boutonEast
	 * @param color
	 */
	public MenuBarre(ButtonCreation boutonWest, ButtonCreation boutonEast, Color color )
	{
		this.boutonWest = boutonWest ;
		this.boutonEast = boutonEast ;
		this.color = color;

		setPreferredSize(new Dimension(480, 40));
		setBackground(color);

		add(boutonWest, BorderLayout.WEST);
		add(boutonEast, BorderLayout.EAST);
	}


	/**
	 * Consctructeur de la menu barre avec un titre, une couleur, un bouton à droite et à gauche
	 * @param titre
	 * @param boutonWest
	 * @param boutonEast
	 * @param color
	 */
	public MenuBarre(String titre, ButtonCreation boutonWest, ButtonCreation boutonEast, Color color)
	{
		this.titre = titre ;
		this.boutonWest = boutonWest ;
		this.boutonEast = boutonEast;
		this.color = color;

		//Paramètre fixe à la barre de menu
		setPreferredSize(new Dimension(480, 40));
		setBackground(color);
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(0, 5, 0, 5));

		JLabel entete = new JLabel(titre);
		entete.setFont(new Font(null, Font.BOLD, 20));
		entete.setHorizontalAlignment(JLabel.CENTER);
		entete.setForeground(Color.WHITE);
		add(entete, BorderLayout.CENTER);

		add(boutonWest, BorderLayout.WEST);
		add(boutonEast, BorderLayout.EAST);
	}


	// Getters &  Setters
	public ButtonCreation getBoutonEast()
	{
		return boutonEast;
	}

	public ButtonCreation getBoutonWest() 
	{
		return boutonWest;
	}



}
