package Component;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ContactModify extends ContactForm{
	
	private MenuBarre menuModify = new MenuBarre ("CONTACT", buttonReturn, buttonModify, color);
	
	public ContactModify(Contact contact, boolean modification) {
		super(contact, modification);
		menuPanel.add(menuModify, BorderLayout.NORTH);
		changeModification();
		buttonModify.addActionListener(new ClickModifyContact());
	}

	//ActionListener sur le bouton modifier 
			class ClickModifyContact implements ActionListener 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
				changeModification();
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
