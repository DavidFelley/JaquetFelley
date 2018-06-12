package Component;
/**
* ------------------------------------------------------------------------------------------------------ <br/>
* Classe : ContactAdd <br/>
* ------------------------------------------------------------------------------------------------------ <br/>
* Auteur : David Felley et Valentin Jaquet <br/>
* Description de la classe : Cette classe gère la panel lors de l'ajout d'un contact <br/>
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

public class ContactAdd extends ContactForm
{
	
	private CardLayout cl ;
	private JPanel jp;
	private MenuBarre menuAdd = new MenuBarre("AJOUTER UN CONTACT", buttonReturn, buttonValidate, color);
	private ContactList contactList;
	
	/**
	 * Constructeur du panel d'ajout d'un nouveau contact
	 * @param modification
	 * @param cl
	 * @param jp
	 * @param mainframe
	 * @param contacts
	 * @param contactList
	 */
	public ContactAdd(boolean modification, CardLayout cl, JPanel jp, MainFrame mainframe,  ArrayList <Contact> contacts, ContactList contactList) 
	{
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
	
	/**
	 * ActionListener qui permet de sauver un contact nouvellement créer
	 * @author Valentin Jaquet
	 *
	 */
			class ClickSaveContact implements ActionListener 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{							
					if(checkInfosContact()) {
					contacts.add(getInfos());
					eraseInfos();		 	
					contactList.updateListContact();
					return;
					}
					
				}
			}
			
	/**
	 * ActionListener qui permet de retourner à la liste des contacts
	 * @author Valentin Jaquet
	 *
	 */
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
