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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Main.MainFrame;

public class ContactApp extends JPanel{
	

	
	private CardLayout cardLayoutContact = new CardLayout();
	private JPanel contentContact = new JPanel(cardLayoutContact);
	private ContactAppAdd contactAppAdd	;
	private ContactAppList contactAppList = new ContactAppList();
	private ArrayList <Contact> contacts = new ArrayList<>();
		
	public ContactApp (){
	
		add(contentContact);
		
		contentContact.add("contactAppList", contactAppList);

		cardLayoutContact.show(contentContact, "contactAppList");
		
	}
	
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
		private MenuBarre menuAddContact = new MenuBarre(buttonReturn, buttonValidate, "Add contact");
		
		// Liste des différentes panels 
		private JPanel panelAdd = new JPanel();
		private JPanel topPanel = new JPanel(new BorderLayout());
		private JPanel photoPanel = new JPanel(new BorderLayout());
		private JPanel infoPanel = new JPanel(new GridLayout(4,2,5,5));
		private JPanel bottomPanel = new JPanel(new BorderLayout());
		
		public ContactAppAdd() {
			
			add(panelAdd);
//			setOpaque(true);
			panelAdd.setLayout(new BoxLayout(panelAdd, BoxLayout.Y_AXIS));
			panelAdd.setBackground(Color.RED);
		
//			panelAdd.setPreferredSize(new Dimension(450,800));
			
			// TopPanel contenant les boutons retour et valider
			panelAdd.add(topPanel);
			topPanel.setPreferredSize(new Dimension(450,40));
			topPanel.add(menuAddContact, BorderLayout.NORTH);
			buttonValidate.addActionListener(new ClickSaveContact());  
			menuAddContact.getBoutonWest().addActionListener(new ClickBack());
			

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
			bottomPanel.add(buttonDelete);
			bottomPanel.setBackground(Color.RED);
			bottomPanel.setPreferredSize(new Dimension(450,40));
		}
		
		public void eraseInfos() {
			tfNom.setText("");
			tfPrenom.setText("");
			tfEmail.setText("");
			tfTelephone.setText("");
		}
		
		public Contact getInfos() {
			
			return new Contact(tfNom.getText(), tfPrenom.getText(), tfEmail.getText(), tfTelephone.getText());
			
		}
		
		public void addContactList() {
			ButtonCreation temp = new ButtonCreation();
			temp.setText(tfNom.getText());
			temp.setBackground(Color.white);
			temp.setFont(new Font(null, Font.PLAIN,20));
			contactAppList.getPanelList().add(temp);
		}
		
		class ClickSaveContact implements ActionListener 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				contacts.add(getInfos());
				System.out.println(contacts.get(0).toString());
				addContactList();
			}
		}
		
		class ClickBack implements ActionListener 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				cardLayoutContact.show(contentContact, "contactAppList");
			}
		}
		
	}	
	
	class ContactAppList extends JPanel {
		
		private JPanel panelList = new JPanel();
		private ButtonCreation buttonPlus = new ButtonCreation("plus",new ImageIcon("images/Icones/plus.png"));
		private MenuBarre menuList = new MenuBarre(buttonPlus, "Liste des contacts");
		
		public ContactAppList() {
			
			add(panelList);
			panelList.setLayout(new BoxLayout(panelList, BoxLayout.Y_AXIS));
			
			panelList.add(menuList);
			menuList.setPreferredSize(new Dimension(450,40));
			buttonPlus.addActionListener(new ClickAddContact());

		}
		
		class ClickAddContact implements ActionListener 
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				contactAppAdd = new ContactAppAdd();
				contactAppAdd.eraseInfos();
				contentContact.add("contactAppAdd",contactAppAdd);
				cardLayoutContact.show(contentContact, "contactAppAdd");
//				System.out.println("je clique sur ce putain de bouton de merde");
			}
		}

		public JPanel getPanelList() {
			return panelList;
		}
		
		
		
	}
		
}
