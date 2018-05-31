package Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactModify extends ContactForm{

	public ContactModify(Contact contact, boolean modification) {
		super(contact, modification);
		menuBarre = new MenuBarre(buttonReturn, buttonModify, "CONTACT");
		
	}

	//ActionListener sur le bouton modifier 
			class ClickModifyContact implements ActionListener 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
				// change la valeur de modification
				}
			}
	
	//ActionListener sur le bouton save 
			class ClickSaveContact implements ActionListener 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
				// même action que le bouton sauver d'ajouter un contact
				}
			}
			
	//ActionListener sur le bouton delete 
			class ClickDeleteContact implements ActionListener 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
				// delete le contact
				}
			}		
}
