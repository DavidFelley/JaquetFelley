package Component;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import Component.ContactApp.ContactList;

public class ContactModify extends ContactForm{
	
	private MenuBarre menuModify = new MenuBarre ("CONTACT", buttonReturn, buttonModify, color);
	private CardLayout cl ;
	private JPanel jp;
	ArrayList <Contact> contacts;
	private ContactList contactList;
	
	
	public ContactModify(Contact contact, boolean modification, CardLayout cl, JPanel jp, ArrayList<Contact> contacts, ContactList contactList) {
		super(contact, modification);
		this.cl = cl;
		this.jp = jp;
		this.contacts = contacts;
		this.contactList = contactList;
		menuPanel.add(menuModify, BorderLayout.NORTH);
		changeModification();
		buttonModify.addActionListener(new ClickModifyContact());
		buttonReturn.addActionListener(new ClickBack());
		buttonValidate.addActionListener(new ClickSaveContact());
		buttonDelete.addActionListener(new ClickDeleteContact(contact));
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
					contacts.add(getInfos(id));
					System.out.println(contactApp.getContacts().get(id).toString());
					eraseInfos();
					System.out.println("valeur de l'id: " + id);
					id++;	
				}
			}
			
	//ActionListener sur le bouton delete 
			class ClickDeleteContact implements ActionListener 
			{
				Contact contact;
				
				public ClickDeleteContact(Contact contact) {
					this.contact = contact;
				}
					
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					contacts.remove(contact);
					contactList.updateListContact();
					cl.show(jp, "contactList");
				}
			}		
			
	//ActionListener sur le bouton retour afin d'afficher la liste des contacts
			class ClickBack implements ActionListener 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					cl.show(jp, "contactList");
				}
			}
			

}
