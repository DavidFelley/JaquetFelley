package Component;
/**
* ------------------------------------------------------------------------------------------------------ <br/>
* Classe : LabelPerso <br/>
* ------------------------------------------------------------------------------------------------------ <br/>
* Auteur : David Felley et Valentin Jaquet <br/>
* Description de la classe : ..... <br/>
* ------------------------------------------------------------------------------------------------------ <br/>
* Remarque : - <br/>
* ------------------------------------------------------------------------------------------------------ <br/>
*/
import java.awt.Font;

import javax.swing.JLabel;

public class LabelPerso extends JLabel{

	String title;

	public LabelPerso(String title) {
		super(title);
		setFont(new Font(null, Font.BOLD, 20));
	}
	
}
