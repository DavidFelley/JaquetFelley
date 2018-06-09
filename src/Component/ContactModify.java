package Component;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Component.ContactApp.ContactList;
import Component.ContactForm.ClickPhotoContact;
import Main.MainFrame;

public class ContactModify extends ContactForm{
	
	private MenuBarre menuModify = new MenuBarre ("CONTACT", buttonReturn, buttonModify, color);
	private CardLayout cl ;
	private JPanel jp;
	ArrayList <Contact> contacts;
	private ContactList contactList;
	
	
	public ContactModify(Contact contact, boolean modification, CardLayout cl, JPanel jp, MainFrame mainframe, ArrayList<Contact> contacts, ContactList contactList) {
		super(contact, modification, cl, jp, mainframe);
		this.contacts = contacts;
		this.contactList = contactList;
		this.cl = cl;
		this.jp = jp;
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
					contacts.set(contacts.indexOf(contact), modifiedContact());
//					contacts.add(modifiedContact());
					contactList.updateListContact();
					cl.show(jp, "contactList");
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
//					contactList.updateListContact();
					cl.show(jp, "contactList");
				}
			}
			

}
