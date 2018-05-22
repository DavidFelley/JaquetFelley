package Component;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.MainFrame;

public class ContactPanel extends JPanel{
	
	private MainFrame mainframe;
	private CardLayout clContact = new CardLayout();
	private JPanel panelContact = new JPanel(clContact);
	
private JLabel testLabel = new JLabel ("test Contact");

	public ContactPanel (MainFrame mainframe) {
		
		this.mainframe = mainframe ;
		setLayout(new BorderLayout());
		panelContact.add(testLabel);
		add(panelContact);
		
		
		panelContact.setLayout(new BorderLayout());
		
		
	}
	
	class listContact extends JPanel{
		
	}
	
	class addContact{
		
	}
	
}
