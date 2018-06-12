package Component;
/**
 * ------------------------------------------------------------------------------------------------------ <br/>
 * Classe : CheckFormular <br/>
 * ------------------------------------------------------------------------------------------------------ <br/>
 * Auteur : Valentin Jaquet <br/>
 * Description de la classe : Cette classe contrôle la saisie dans les textfields du formulaire <br/>
 * ------------------------------------------------------------------------------------------------------ <br/>
 * Remarque : - <br/>
 * ------------------------------------------------------------------------------------------------------ <br/>
 */
import java.awt.Color;

import javax.swing.border.LineBorder;


public class CheckFormular 
{

	/**
	 * Permet de contrôler si un textfield est rempli
	 * @param textField
	 * @return
	 */
	public boolean isNotEmpty(TextfieldPerso textField)
	{
		if (textField.getText().isEmpty()) 
		{
			textField.setText("Champ obligatoire");
			textField.setBorder(new LineBorder(Color.RED));
			textField.setForeground(Color.LIGHT_GRAY);
			return false;
		}
		return true;
	}

	/**
	 * Permet de contrôler si le numéro de téléphone correspond au pattern
	 * @param textField
	 * @return
	 */
	public boolean patternNumbers(TextfieldPerso textField) 
	{
		String testPattern = "(07)\\d{8}";
		if (textField.getText().matches(testPattern)==false) 
		{
			textField.setText("Numéro invalide");
			textField.setBorder(new LineBorder(Color.RED));
			textField.setForeground(Color.LIGHT_GRAY);
			return false;
		}
		return true;
	}

}
