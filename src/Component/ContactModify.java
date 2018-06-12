package Component;
/**
 * ------------------------------------------------------------------------------------------------------ <br/>
 * Classe : ContactModify <br/>
 * ------------------------------------------------------------------------------------------------------ <br/>
 * Auteur : David Felley et Valentin Jaquet <br/>
 * Description de la classe : Cette classe gère la panel lors de la modification d'un contact  <br/>
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

public class ContactModify extends ContactForm{

	private MenuBarre menuModify = new MenuBarre ("CONTACT", buttonReturn, buttonModify, color);
	private CardLayout cl ;
	private JPanel jp;
	ArrayList <Contact> contacts;
	private ContactList contactList;

	/**
	 * Constructeur du panel ContactModify
	 * @param contact
	 * @param modification
	 * @param cl
	 * @param jp
	 * @param mainframe
	 * @param contacts
	 * @param contactList
	 */
	public ContactModify(Contact contact, boolean modification, CardLayout cl, JPanel jp, MainFrame mainframe, ArrayList<Contact> contacts, ContactList contactList) {
		super(contact, modification, cl, jp, mainframe);
		this.contacts = contacts;
		this.contactList = contactList;
		this.cl = cl;
		this.jp = jp;
		changeModification();
		menuPanel.add(menuModify, BorderLayout.NORTH);		
		buttonModify.addActionListener(new ClickModifyContact());
		buttonReturn.addActionListener(new ClickBack());
		buttonValidate.addActionListener(new ClickSaveContact());
		buttonDelete.addActionListener(new ClickDeleteContact(contact));
	}

	/**
	 * ActionListner qui permet de rendre les textfields éditables, l'icône contact clickable et affiche les boutons sauvegarder et supprimer
	 * @author Valentin Jaquet
	 *
	 */
	class ClickModifyContact implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			changeModification();
		}
	}

	/**
	 * ActionListner qui permet de sauver un contact modifié 
	 * @author Valentin Jaquet
	 *
	 */
	class ClickSaveContact implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(checkInfosContact()) {
				contacts.set(contacts.indexOf(contact), modifiedContact());
				contactList.updateListContact();
				cl.show(jp, "contactList");
				return;
			}
		}
	}

	/**
	 * ActionListener qui permet de supprimer un contact
	 * @author Valentin Jaquet
	 *
	 */
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

	/**
	 * Action Listener qui permet de retourner à la liste des contacts
	 * @author Valentin Jaquet
	 *
	 */
	class ClickBack implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			contactList.updateListContact();
			cl.show(jp, "contactList");
		}
	}


}
