package Component;

import java.awt.Color;

import javax.swing.border.LineBorder;


public class CheckFormular {
	
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
	
	public boolean patternNumbers(TextfieldPerso textField) {
		 String testPattern = "(07)\\d{8}";
			 if (textField.getText().matches(testPattern)==false) {
				textField.setText("Numéro invalide");
				textField.setBorder(new LineBorder(Color.RED));
				textField.setForeground(Color.LIGHT_GRAY);
				return false;
		 }
		 return true;
	}
	
}
