package Component;
/**
* ------------------------------------------------------------------------------------------------------ <br/>
* Classe : ContactAdd <br/>
* ------------------------------------------------------------------------------------------------------ <br/>
* Auteur : David Felley et Valentin Jaquet <br/>
* Description de la classe : ..... <br/>
* ------------------------------------------------------------------------------------------------------ <br/>
* Remarque : - <br/>
* ------------------------------------------------------------------------------------------------------ <br/>
*/
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import Component.ContactApp.ContactList;
import Main.MainFrame;

public class ContactAdd extends ContactForm{
	
	private CardLayout cl ;
	private JPanel jp;
	ArrayList <Contact> contacts;
	private MenuBarre menuAdd = new MenuBarre("AJOUTER UN CONTACT", buttonReturn, buttonValidate, color);

	private ContactList contactList;
	
	public ContactAdd(boolean modification, CardLayout cl, JPanel jp, MainFrame mainframe,  ArrayList <Contact> contacts, ContactList contactList) {
		super(modification, cl, jp, mainframe);
		this.contacts=contacts;
		this.contactList = contactList;
		this.cl = cl;
		this.jp = jp;
		menuPanel.add(menuAdd, BorderLayout.NORTH);
		buttonReturn.addActionListener(new ClickBack());
		buttonValidate.addActionListener(new ClickSaveContact());
		contactPhoto.addActionListener(new ClickPhotoContact());
		bottomPanel.setVisible(false);
	}
	
	
	
	//ActionListener sur le bouton save (appel à la méthode d'ajout d'un contact à la liste des contacts)
			class ClickSaveContact implements ActionListener 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{							
					if(checkInfosContact()) {
					contacts.add(getInfos());
					System.out.println(mainframe.getContactApp().getContacts().toString());
					eraseInfos();		 	
					contactList.updateListContact();
					return;
					}
					
				}
			}
			
	//ActionListener sur le bouton retour afin d'afficher la liste des contacts
			class ClickBack implements ActionListener 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					cl.show(jp, "contactList");
					contactList.updateListContact();
				}
			}
			
			
			
			
	

}
