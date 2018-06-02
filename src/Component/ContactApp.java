package Component;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;



public class ContactApp extends JPanel{
	
	private CardLayout cardLayoutContact = new CardLayout();
	private JPanel contentContact = new JPanel(cardLayoutContact);
	private ContactAdd contactAdd ;
	private ContactList contactList ;
	private ContactModify contactModify;
//	private ContactModify contactModify = new ContactModify(null, autoscrolls);
	public ArrayList <Contact> contacts;// = new ArrayList<Contact>();
		

	public ContactApp () {
		
		deserializeContact();
		
		setLayout(new BorderLayout());
		
		add(contentContact, BorderLayout.CENTER);
			
		contactList = new ContactList();
		
//		contactList.updateListContact();
		
		contentContact.add("contactList", contactList);
	
		cardLayoutContact.show(contentContact, "contactList");
		
		contenuList();
	}
		
	
	public void serializeContact() {
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
	
	@SuppressWarnings("unchecked")
	public void deserializeContact() {
		try {
			FileInputStream file = new FileInputStream ("serialization/contacts.ser");
			ObjectInputStream objectis = new ObjectInputStream (file);
			contacts = (ArrayList<Contact>) objectis.readObject();
			objectis.close();
			System.out.println("les contacts sont désérialisés");
		}
		catch (IOException e)
		{
			contacts = new ArrayList<Contact>();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<Contact> getContacts() {
		
		return contacts;
	}

	public void setContacts(ArrayList<Contact> contacts) {
		this.contacts = contacts;
	}
	
	public void contenuList() {
		for(int i = 0 ; i < contacts.size(); i++) {
			System.out.println("CONTENU DE MON ARRAY LIST" + contacts.get(i).toString());
		}
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
		private JScrollPane scroll = new JScrollPane(panelList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		// Constructeur de la liste des contacts
				public ContactList() {
					
					setLayout(new BorderLayout());
					add(menuBarreList, BorderLayout.NORTH);
					displayListContacts();
					scroll.setPreferredSize(new Dimension(450, 680));
					panelList.setLayout(new GridLayout(0,1));
					buttonPlus.addActionListener(new ClickAddContact());
//					scroll.setBorder(new EmptyBorder(0,0,0,0));
					add(scroll);
					this.addComponentListener(new UpdateListContact());
					updateListContact();
				}
			
				
				// Affiche la liste des boutons contacts dans le panel liste des contacts
				public void displayListContacts() {
					
					ButtonCreation temp;
				
						for (int i = 0; i < contacts.size(); i++) {
							temp = new ButtonCreation(450,60);
							temp.setText(contacts.get(i).texteBoutonContact());
							panelList.add(temp);
							temp.setFont(new Font(null, Font.PLAIN,20));
							temp.setBackground(Color.white);
							temp.addActionListener(new ClickShowContact());
						}  
				}
				
				public void updateListContact() {
					panelList.removeAll();
					displayListContacts();
				}

				// ActionListener bouton ajouter
				class ClickAddContact implements ActionListener 
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						contactAdd = new ContactAdd(true, cardLayoutContact, contentContact,contacts);
						contactAdd.eraseInfos();
						contentContact.add("contactAdd",contactAdd);
						cardLayoutContact.show(contentContact, "contactAdd");
					}
				}	
				
				// ActionListener bouton ajouter
				class ClickShowContact implements ActionListener 
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						contactModify = new ContactModify(null, true);
						contentContact.add("contactModify",contactModify);
						cardLayoutContact.show(contentContact, "contactModify");
					}
				}	
				
				
				
				
				// Componenent Lister qui met à jour la liste de contacts
				class UpdateListContact implements ComponentListener{

					@Override
					public void componentHidden(ComponentEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void componentMoved(ComponentEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void componentResized(ComponentEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void componentShown(ComponentEvent arg0) {
						// TODO Auto-generated method stub
						updateListContact();
					}
					
				}
				
	}
}
