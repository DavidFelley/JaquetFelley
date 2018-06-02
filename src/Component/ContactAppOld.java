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
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Main.MainFrame;

public class ContactAppOld extends JPanel{

	private CardLayout cardLayoutContact = new CardLayout();
	private JPanel contentContact = new JPanel(cardLayoutContact);
	private ContactAppAdd contactAppAdd	;
	private ContactAppList contactAppList = new ContactAppList();
	private ArrayList <Contact> contacts = new ArrayList<>();

	public ContactAppOld (){

		setLayout(new BorderLayout());

		add(contentContact, BorderLayout.NORTH);

		contentContact.add("contactAppList", contactAppList);

		cardLayoutContact.show(contentContact, "contactAppList");
	}

	/*
	 * 
	 * 
	 * PANEL AJOUT D'UN CONTACT
	 * 
	 * 
	 */

	class ContactAppAdd extends JPanel {

		// Label infos contacts
		private JLabel nom = new JLabel("Nom: ");
		private JLabel prenom = new JLabel("Prénom: ");
		private JLabel email = new JLabel("E-mail: ");
		private JLabel telephone = new JLabel("Téléphone: ");

		// TextField infos contacts
		private JTextField tfNom = new JTextField();
		private JTextField tfPrenom = new JTextField();
		private JTextField tfEmail = new JTextField();
		private JTextField tfTelephone = new JTextField();

		// Icone de base  du champ contact 
		private ButtonCreation contactEmpty = new ButtonCreation("contactEmpty", new ImageIcon("images/Galerie/canada.jpg"));

		// Bouton delete, valider, modifier, retour
		private ButtonCreation buttonDelete = new ButtonCreation("delete", new ImageIcon("images/Icones/trash.png"));
		private ButtonCreation buttonValidate = new ButtonCreation("validate", new ImageIcon("images/Icones/Validate.png"));
		private ButtonCreation buttonModify = new ButtonCreation("modify", new ImageIcon("images/Icones/modify.png"));
		private ButtonCreation buttonReturn = new ButtonCreation ("return", new ImageIcon("images/Icones/retour.png"));
//		private MenuBarre menuAddContact = new MenuBarre(buttonReturn, buttonValidate, "AJOUTER UN CONTACT");

		// Liste des différentes panels 
		private JPanel panelAdd = new JPanel();
		private JPanel topPanel = new JPanel(new BorderLayout());
		private JPanel photoPanel = new JPanel(new BorderLayout());
		private JPanel infoPanel = new JPanel(new GridLayout(4,2,5,5));
		private JPanel bottomPanel = new JPanel(new BorderLayout());

		// ID des contacts
		private int id = 0;

		// Constructeur du panel Ajouter un contact
		public ContactAppAdd() {


			setLayout(new BorderLayout());
			add(panelAdd, BorderLayout.NORTH);
			panelAdd.setLayout(new BoxLayout(panelAdd, BoxLayout.Y_AXIS));

			// MenuPanel contenant 
			panelAdd.add(topPanel);
			topPanel.setPreferredSize(new Dimension(450,40));
//			topPanel.add(menuAddContact, BorderLayout.NORTH);
			topPanel.setBorder(new EmptyBorder(0,0,0,0));
			buttonValidate.addActionListener(new ClickSaveContact());  
//			menuAddContact.getBoutonWest().addActionListener(new ClickBack());

			topPanel.setOpaque(false);

			// PhotoPanel contenant la photo du contact
			panelAdd.add(photoPanel);
			photoPanel.setPreferredSize(new Dimension(450,325));
			photoPanel.add(contactEmpty);

			//InfoPanel contenant les informations du contact
			panelAdd.add(infoPanel);
			infoPanel.setPreferredSize(new Dimension(450,305));
			infoPanel.add(prenom);
			infoPanel.setBorder(new EmptyBorder(10,10,10,10));

			prenom.setFont(new Font(null, Font.BOLD, 20));
			infoPanel.add(tfPrenom);
			tfPrenom.setFont(new Font(null, Font.PLAIN, 20));

			infoPanel.add(nom);
			nom.setFont(new Font(null, Font.BOLD, 20));
			infoPanel.add(tfNom);
			tfNom.setPreferredSize(new Dimension(200,10));
			tfNom.setFont(new Font(null, Font.PLAIN, 20));

			infoPanel.add(email);
			email.setFont(new Font(null, Font.BOLD, 20));
			infoPanel.add(tfEmail);
			tfEmail.setFont(new Font(null, Font.PLAIN,20));

			infoPanel.add(telephone);
			telephone.setFont(new Font(null, Font.BOLD, 20));
			infoPanel.add(tfTelephone);
			tfTelephone.setFont(new Font(null, Font.PLAIN,20));

			//BottomPanel contenant le button delete
			panelAdd.add(bottomPanel);
			bottomPanel.add(buttonDelete, BorderLayout.CENTER);
			bottomPanel.setBackground(Color.RED);
			bottomPanel.setPreferredSize(new Dimension(450,40));


		}

		//Méthode permettant de vider les textFields
		public void eraseInfos() {
			tfNom.setText("");
			tfPrenom.setText("");
			tfEmail.setText("");
			tfTelephone.setText("");
		}

		//Méthode permettant de récupérer les infos d'un contact
		public Contact getInfos(int id) {

			return new Contact(tfNom.getText(), tfPrenom.getText(), tfEmail.getText(), tfTelephone.getText(), id);

		}

		//Méthode qui ajoute un bouton avec les infos du contact à la liste des contacts
		public void addContactList() {

			ButtonCreation temp = new ButtonCreation(450,60);
			contactAppList.getPanelList().add(temp);
			temp.setText(tfNom.getText() + " " + tfPrenom.getText());
			temp.setFont(new Font(null, Font.PLAIN,20));
			temp.setBackground(Color.white);
			System.out.println("j'ajoute un nouveau bouton");
		}

		//ActionListener sur le bouton save (appel à la méthode d'ajout d'un contact à la liste des contacts)
		class ClickSaveContact implements ActionListener 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				contacts.add(getInfos(id));
				System.out.println(contacts.get(id).toString());
				addContactList();
				eraseInfos();
				System.out.println("valeur de l'id: " + id);
				id++;

			}
		}


		//ActionListener sur le bouton retour afin d'afficher la liste des contacts
		class ClickBack implements ActionListener 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				cardLayoutContact.show(contentContact, "contactAppList");
			}
		}
	}	


	/*
	 * 
	 * 
	 * PANEL LISTE DES CONTACTS
	 * 
	 * 
	 */
	class ContactAppList extends JPanel {

		private JPanel panelList = new JPanel();
		private ButtonCreation buttonPlus = new ButtonCreation("plus",new ImageIcon("images/Icones/plus.png"));
//		private MenuBarre menuBarreList = new MenuBarre(buttonPlus, "CONTACTS");
		private JScrollPane scroll = new JScrollPane(panelList);

		// Constructeur de la liste des contacts
		public ContactAppList() {

			setLayout(new BorderLayout());
//			add(menuBarreList, BorderLayout.NORTH);

			panelList.setLayout(new BoxLayout(panelList, BoxLayout.Y_AXIS));
			panelList.setPreferredSize(new Dimension(450, 680));
//			menuBarreList.setPreferredSize(new Dimension(450,40));
			buttonPlus.addActionListener(new ClickAddContact());

			add(scroll, BorderLayout.CENTER);

		}

		// Getter pour le panel "panelList"
		public JPanel getPanelList() {
			return panelList;
		}


		// ActionListener bouton ajouter
		class ClickAddContact implements ActionListener 
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				contactAppAdd = new ContactAppAdd();
				contactAppAdd.eraseInfos();
				contentContact.add("contactAppAdd",contactAppAdd);
				cardLayoutContact.show(contentContact, "contactAppAdd");
				//liste boutons en boxlayout dans le borderlayout en center 
			}
		}

		// ActionListener lors du clic sur un bouton contact
		class ClickShowContact implements ActionListener{
			Contact contact;
			@Override
			public void actionPerformed(ActionEvent arg0) {

				cardLayoutContact.show(contentContact,"contactAppAdd");
				//				cardLayoutContact.show(contentContact,"contactAppAdd" + contact.getId());
			}	
		}




	}

}
