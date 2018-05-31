package Component;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;




public class ContactApp extends JPanel{
	
	private CardLayout cardLayoutContact = new CardLayout();
	private JPanel contentContact = new JPanel(cardLayoutContact);
	private ContactAdd contactAdd ;
	private ContactList contactList = new ContactList();
//	private ContactModify contactModify = new ContactModify(null, autoscrolls);

	public ContactApp () {
		setLayout(new BorderLayout());

		add(contentContact, BorderLayout.NORTH);

		contentContact.add("contactList", contactList);

		cardLayoutContact.show(contentContact, "contactList");
	}
	

	
	/*
	 * 
	 * 
	 * LISTE DES CONTACTS
	 * 
	 * 
	 */
	
	class ContactList extends JPanel{
		
		private JPanel panelList = new JPanel();
		private ButtonCreation buttonPlus = new ButtonCreation("plus",new ImageIcon("images/Icones/plus.png"));
		private MenuBarre menuBarreList = new MenuBarre(buttonPlus, "CONTACTS");
		private JScrollPane scroll = new JScrollPane(panelList);
		
		// Constructeur de la liste des contacts
				public ContactList() {

					setLayout(new BorderLayout());
					add(menuBarreList, BorderLayout.NORTH);

					panelList.setLayout(new BoxLayout(panelList, BoxLayout.Y_AXIS));
					panelList.setPreferredSize(new Dimension(450, 680));
					menuBarreList.setPreferredSize(new Dimension(450,40));
					buttonPlus.addActionListener(new ClickAddContact());

					add(scroll, BorderLayout.CENTER);
				}
			
				public void displayContacts() {
					
				}
				
				// ActionListener bouton ajouter
				class ClickAddContact implements ActionListener 
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						contactAdd = new ContactAdd(true, cardLayoutContact, contentContact );
						contactAdd.eraseInfos();
						contentContact.add("contactAdd",contactAdd);
						cardLayoutContact.show(contentContact, "contactAdd");
						//liste boutons en boxlayout dans le borderlayout en center 
					}
				}	
				
	}
}
