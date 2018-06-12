package Component;
/**
 * ------------------------------------------------------------------------------------------------------ <br/>
 * Classe : TextfieldPerso <br/>
 * ------------------------------------------------------------------------------------------------------ <br/>
 * Auteur : David Felley et Valentin Jaquet <br/>
 * Description de la classe : Cette classe gère les textfields <br/>
 * ------------------------------------------------------------------------------------------------------ <br/>
 * Remarque : - <br/>
 * ------------------------------------------------------------------------------------------------------ <br/>
 */
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTextField;

public class TextfieldPerso extends JTextField{
	
	/**
	 * Constructeur des textfields personnalisés
	 */
	public TextfieldPerso()
	{
		setPreferredSize(new Dimension(200,10));
		setFont(new Font(null, Font.PLAIN,20));
	}
}
