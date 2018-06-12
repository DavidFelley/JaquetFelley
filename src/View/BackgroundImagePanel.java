package View;
/**
* ------------------------------------------------------------------------------------------------------ <br/>
* Classe : BackgroundImagePanel <br/>
* ------------------------------------------------------------------------------------------------------ <br/>
* Auteur : David Felley et Valentin Jaquet <br/>
* Description de la classe : Cette classe gère le fond d'écran et la forme du smartphone  <br/>
* ------------------------------------------------------------------------------------------------------ <br/>
* Remarque : - <br/>
* ------------------------------------------------------------------------------------------------------ <br/>
*/
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundImagePanel extends JPanel{
	
	ImageIcon image;
	
	/**
	 * Constructeur du BackgroundImagePanel
	 * @param image
	 */
	public BackgroundImagePanel(ImageIcon image) {
		this.image = image;
	}
	
	/**
	 * Rempli le composant avec l'image insérée
	 */
	@Override
	protected void paintComponent(Graphics g) 
	{
		g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), null);
	}

}
