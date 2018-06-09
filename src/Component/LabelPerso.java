package Component;

import java.awt.Font;

import javax.swing.JLabel;

public class LabelPerso extends JLabel{

	String title;
	
	public LabelPerso(String title) {
		super(title);
		setFont(new Font(null, Font.BOLD, 20));
	}
	
}
