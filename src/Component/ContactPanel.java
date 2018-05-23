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

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Main.MainFrame;

public class ContactPanel extends JPanel{

//	private CardLayout cardLayout = new CardLayout();
//	private JPanel contentContact = new JPanel(cardLayout);
	private AddContact addContact = new AddContact();		
	
	public ContactPanel () {
		
//		add(contentContact);
//		contentContact.add(addContact);
//		contentContact.setBackground(Color.PINK);
		add(addContact);
		
	}
	
	class AddContact extends JPanel {
		
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
		private ButtonCreation buttonReturn = new ButtonCreation("return", new ImageIcon("images/Icones/retour.png"));
		private ButtonCreation buttonValidate = new ButtonCreation("validate", new ImageIcon("images/Icones/Validate.png"));
		private ButtonCreation buttonModify = new ButtonCreation("modify", new ImageIcon("images/Icones/modify.png"));
		
		// Liste des différentes panels 
		private JPanel fullPanel = new JPanel();
		private JPanel topPanel = new JPanel(new BorderLayout());
		private JPanel photoPanel = new JPanel(new BorderLayout());
		private JPanel infoPanel = new JPanel(new GridLayout(4,2,5,5));
		private JPanel bottomPanel = new JPanel(new BorderLayout());
		
		public AddContact() {
			add(fullPanel);
			setOpaque(true);
			fullPanel.setLayout(new BoxLayout(fullPanel, BoxLayout.Y_AXIS));
			fullPanel.setBackground(Color.RED);
		
//			fullPanel.setPreferredSize(new Dimension(450,800));
			
			// TopPanel contenant les boutons retour et valider
			fullPanel.add(topPanel);
			topPanel.setPreferredSize(new Dimension(450,40));
			topPanel.add(new MenuBarre(buttonValidate, "Add contact"), BorderLayout.NORTH);
//			topPanel.setBorder(new EmptyBorder(0,0,0,0));
			topPanel.setOpaque(false);
			
			// PhotoPanel contenant la photo du contact
			fullPanel.add(photoPanel);
			photoPanel.setPreferredSize(new Dimension(450,325));
			photoPanel.add(contactEmpty);
		
			//InfoPanel contenant les informations du contact
			fullPanel.add(infoPanel);
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
			fullPanel.add(bottomPanel);
			bottomPanel.add(buttonDelete);
			bottomPanel.setBackground(Color.RED);
			bottomPanel.setPreferredSize(new Dimension(450,40));
						
		}
	}	
}
