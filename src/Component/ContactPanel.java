package Component;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Main.MainFrame;

public class ContactPanel extends JPanel{

	private CardLayout cardLayout = new CardLayout();
	private JPanel contentContact = new JPanel(cardLayout);
	private AddContact addContact = new AddContact();	


	public ContactPanel () {
		add(contentContact);
		contentContact.add(addContact);
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
		private ButtonCreation contactEmpty = new ButtonCreation("contactEmpty", new ImageIcon("images/Icones/contactVide.png"));
		
		// Bouton delete 
		private ButtonCreation buttonDelete = new ButtonCreation("validate", new ImageIcon("images/Icones/trash.png"));
		
		
		// Liste des différentes panels 
		private JPanel fullPanel = new JPanel(new GridLayout(4,1));
		private JPanel topPanel = new JPanel(new BorderLayout());
		private JPanel photoPanel = new JPanel(new BorderLayout());
		private JPanel infoPanel = new JPanel(new GridLayout(4,2,5,5));
		private JPanel bottomPanel = new JPanel(new BorderLayout());
		
		public AddContact() {
			
			add(fullPanel);
			fullPanel.setPreferredSize(new Dimension(450,800));
			fullPanel.setBorder(new EmptyBorder(20,20,20,20));
			
		
			// TopPanel contenant les boutons retour et valider
			fullPanel.add(topPanel);
			
			topPanel.setBackground(Color.WHITE);
			topPanel.add(new JLabel ("BOUTON RETOUR ET BOUTON VALIDER"));

			
			// PhotoPanel contenant la photo du contact
			fullPanel.add(photoPanel);
			photoPanel.add(contactEmpty);
		
			//InfoPanel contenant les informations du contact
			fullPanel.add(infoPanel);
		
			infoPanel.add(prenom);
			prenom.setForeground(Color.WHITE);
			prenom.setFont(new Font(null, Font.BOLD, 20));
			infoPanel.add(tfPrenom);
			
			infoPanel.add(nom);
			nom.setForeground(Color.WHITE);
			nom.setFont(new Font(null, Font.BOLD, 20));
			infoPanel.add(tfNom);
			tfNom.setPreferredSize(new Dimension(200,10));
			
			infoPanel.add(email);
			email.setForeground(Color.WHITE);
			email.setFont(new Font(null, Font.BOLD, 20));
			infoPanel.add(tfEmail);
			
			infoPanel.add(telephone);
			telephone.setForeground(Color.WHITE);
			telephone.setFont(new Font(null, Font.BOLD, 20));
			infoPanel.add(tfTelephone);
			
			//BottomPanel contenant le button delete
			fullPanel.add(bottomPanel);
			bottomPanel.add(buttonDelete);	
			buttonDelete.setPreferredSize(new Dimension());
			bottomPanel.setBackground(Color.RED);
			
		}
	}
	
	class listContact extends JPanel{
		
	}
	
	
}
