package Component;
/**
 * ------------------------------------------------------------------------------------------------------ <br/>
 * Classe : ContactApp <br/>
 * ------------------------------------------------------------------------------------------------------ <br/>
 * Auteur : David Felley et Valentin Jaquet <br/>
 * Description de la classe : Cette classe gère l'application contact <br/>
 * ------------------------------------------------------------------------------------------------------ <br/>
 * Remarque : - <br/>
 * ------------------------------------------------------------------------------------------------------ <br/>
 */
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Main.MainFrame;

public class ContactApp extends JPanel
{
	private CardLayout cardLayoutContact = new CardLayout();
	private JPanel contentContact = new JPanel(cardLayoutContact);

	private ContactAdd contactAdd ;
	private ContactList contactList ;
	private ContactModify contactModify;

	public ArrayList <Contact> contacts = new ArrayList<Contact>();

	private MainFrame mainframe;

	private ButtonCreation validButton = new ButtonCreation("valid",new ImageIcon("images/Icones/validate.png"));

	private GalleryApp galleryApp = new GalleryApp(true, this);

	private boolean isFromContactAdd ;

	private int id;

	private String pathIcon;
	
	
	/**
	 * Constructeur de l'application contact
	 * @param mainframe
	 */
	public ContactApp (MainFrame mainframe) 
	{

		this.mainframe = mainframe;

		deserializeContact();

		setLayout(new BorderLayout());

		add(contentContact, BorderLayout.CENTER);

		contactList = new ContactList();

		contentContact.add("contactList", contactList);

		contentContact.add("galleryApp", galleryApp);

		cardLayoutContact.show(contentContact, "contactList");

		validButton.addActionListener(new SaveImageContact());
	}


	/**
	 * Sauvegarde des contacts
	 */
	public void serializeContact() 
	{
		try 
		{
			FileOutputStream file = new FileOutputStream ("serialization/contacts.ser");
			ObjectOutputStream objectos = new ObjectOutputStream (file);
			objectos.writeObject(contacts);
			objectos.flush();
			objectos.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * Chargement des contacts
	 */
	@SuppressWarnings("unchecked")
	public void deserializeContact() 
	{
		try {
			FileInputStream file = new FileInputStream ("serialization/contacts.ser");
			ObjectInputStream objectis = new ObjectInputStream (file);
			contacts = (ArrayList<Contact>) objectis.readObject();
			objectis.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	// Getters & Setters
	public MainFrame getMainframe() 
	{
		return mainframe;
	}

	public ArrayList<Contact> getContacts() 
	{

		return contacts;
	}

	public void setContacts(ArrayList<Contact> contacts) 
	{
		this.contacts = contacts;
	}

	public CardLayout getCardLayoutContact() 
	{
		return cardLayoutContact;
	}


	public JPanel getContentContact() 
	{
		return contentContact;
	}


	public GalleryApp getGalleryApp() 
	{
		return galleryApp;
	}


	public void setGalleryApp(GalleryApp galleryApp) 
	{
		this.galleryApp = galleryApp;
	}

	public ContactModify getContactModify() 
	{
		return contactModify;
	}


	public void setContactModify(ContactModify contactModify) 
	{
		this.contactModify = contactModify;
	}

	public ButtonCreation getValidButton() 
	{
		return validButton;
	}


	public void setValidButton(ButtonCreation validButton) 
	{
		this.validButton = validButton;
	}


	public boolean isFromContactAdd() 
	{
		return isFromContactAdd;
	}


	public void setFromContactAdd(boolean isFromContactAdd) 
	{
		this.isFromContactAdd = isFromContactAdd;
	}



	/**
	 * ActionListener qui permet de sauver l'image du contact choisie dans la galerie
	 *
	 */
	class SaveImageContact implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			galleryApp.getCardlayout().show(galleryApp, "GalleryPanel");
			
			pathIcon = galleryApp.getGalleryPanel().getPhotos().get(galleryApp.getId());
			
			if(isFromContactAdd == false) {
				contactModify.getContactPhoto().setIcon(new ImageIcon(contactModify.createContactIcon(pathIcon)));	
				contactModify.getContactPhoto().setName(pathIcon);	
				cardLayoutContact.show(contentContact, "contactModify");
			}else{
				contactAdd.getContactPhoto().setIcon(new ImageIcon(contactAdd.createContactIcon(pathIcon)));
				contactAdd.getContactPhoto().setName(pathIcon);
				contentContact.add("contactAdd",contactAdd);
				cardLayoutContact.show(contentContact, "contactAdd");
			}
		}
	}


	/**
	 * Liste des contacts
	 *
	 */
	class ContactList extends JPanel
	{

		private JPanel panelList = new JPanel();
		private Color color = new Color(78,104,141);
		private ButtonCreation buttonPlus = new ButtonCreation("plus",new ImageIcon("images/Icones/plus.png"));
		private MenuBarre menuBarreList = new MenuBarre("CONTACTS", buttonPlus, color);
		private JScrollPane scroll = new JScrollPane(panelList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		/**
		 * Constructeur de la liste des contacts
		 */
		public ContactList() 
		{
			setLayout(new BorderLayout());
			add(menuBarreList, BorderLayout.NORTH);
			scroll.setPreferredSize(new Dimension(450, 680));
			panelList.setLayout(new GridLayout(0,1));
			panelList.setBackground(Color.WHITE);
			buttonPlus.addActionListener(new ClickAddContact());
			scroll.setBorder(new EmptyBorder(0,0,0,0));
			add(scroll);
			updateListContact();
		}

		/**
		 * Affiche la liste des contacts
		 */
		public void displayListContacts() 
		{

			ButtonCreation temp;

			for (int i = 0; i < contacts.size(); i++) {
				temp = new ButtonCreation(450,60);
				temp.setText(contacts.get(i).texteBoutonContact());
				temp.setFont(new Font(null, Font.PLAIN,20));
				temp.setBackground(Color.white);
				panelList.add(temp);
				temp.addActionListener(new ClickShowContact(contacts.get(i)));
			}  
			updateUI();
		}

		/**
		 * Met à jour la liste des contacts
		 */
		public void updateListContact() 
		{
			panelList.removeAll();
			displayListContacts();
		}

		/**
		 * Ouvre le formulaire d'ajout d'un contact
		 *
		 */
		class ClickAddContact implements ActionListener 
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setFromContactAdd(true);
				contactAdd = new ContactAdd(false, cardLayoutContact, contentContact, mainframe, contacts, contactList);
				contactAdd.eraseInfos();
				contentContact.add("contactAdd",contactAdd);
				cardLayoutContact.show(contentContact, "contactAdd");
			}
		}	

		/**
		 * Ouvre le contact sélectionné
		 *
		 */
		class ClickShowContact implements ActionListener 
		{
			Contact contact;

			public ClickShowContact(Contact contact) 
			{
				this.contact = contact;
			}

			@Override
			public void actionPerformed(ActionEvent e)
			{
				setFromContactAdd(false);
				contactModify = new ContactModify(contact, true, cardLayoutContact, contentContact, mainframe, contacts, contactList);
				contentContact.add("contactModify",contactModify);
				cardLayoutContact.show(contentContact, "contactModify");
			}
		}	
	}
}
