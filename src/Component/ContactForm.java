package Component;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Main.MainFrame;


public class ContactForm extends JPanel{

	// Label infos contacts
	protected JLabel nom = new JLabel("Nom: ");
	protected JLabel prenom = new JLabel("Prénom: ");
	protected JLabel email = new JLabel("E-mail: ");
	protected JLabel telephone = new JLabel("Téléphone: ");

	// TextField infos contacts
	protected JTextField tfNom = new JTextField();
	protected JTextField tfPrenom = new JTextField();
	protected JTextField tfEmail = new JTextField();
	protected JTextField tfTelephone = new JTextField();

	// Icone de base  du champ contact 
	protected ButtonCreation contactPhoto = new ButtonCreation("contactPhoto", new ImageIcon("images/Icones/contactVide.png"));

	// Bouton delete, valider, modifier, retour
	protected ButtonCreation buttonDelete = new ButtonCreation("delete", new ImageIcon("images/Icones/trash.png"));
	protected ButtonCreation buttonValidate = new ButtonCreation("validate", new ImageIcon("images/Icones/Validate.png"));
	protected ButtonCreation buttonModify = new ButtonCreation("modify", new ImageIcon("images/Icones/modify.png"));
	protected ButtonCreation buttonReturn = new ButtonCreation ("return", new ImageIcon("images/Icones/retour.png"));

	
	// Liste des différentes panels 
	private JPanel panelBase = new JPanel();
	protected JPanel menuPanel = new JPanel(new BorderLayout());
	private JPanel photoPanel = new JPanel(new BorderLayout());
	private JPanel formPanel = new JPanel(new GridLayout(4,2,5,5));
	protected JPanel bottomPanel = new JPanel(new BorderLayout());
	
	protected CardLayout cl;
	protected JPanel jp;
	
	protected MainFrame mainframe ;
	
	protected Color color = new Color(78,104,141);

	protected Contact contact;
	protected ContactApp contactApp = new ContactApp(mainframe);
	protected boolean modification;
	
	protected int id = 0;
	protected boolean fromContact = false ;

	// Constructeur pour le formulaire vide
	public ContactForm(boolean modification, CardLayout cl, JPanel jp, MainFrame mainframe) {
		this.modification = modification;
		this.cl = cl;
		this.jp = jp;
		this.mainframe = mainframe;
		createFormPanel();
	}
	
	// Constructeur pour le formulaire rempli
	public ContactForm(Contact contact, boolean modification, CardLayout cl, JPanel jp, MainFrame mainframe) {
		this.contact = contact;
		this.modification = modification;
		this.cl = cl;
		this.jp = jp;
		this.mainframe = mainframe;
		createFormPanel();
		writeInfos(contact);
		

	}

	public void createFormPanel() {

		setLayout(new BorderLayout());
		add(panelBase, BorderLayout.NORTH);
		panelBase.setLayout(new BoxLayout(panelBase, BoxLayout.Y_AXIS));

		// menuPanel contenant la menu barre
		panelBase.add(menuPanel);
		menuPanel.setPreferredSize(new Dimension(450,40));
		menuPanel.setBorder(new EmptyBorder(0,0,0,0));
		menuPanel.setOpaque(false);

		// PhotoPanel contenant la photo du contact
		panelBase.add(photoPanel);
		photoPanel.setPreferredSize(new Dimension(450,325));
		photoPanel.add(contactPhoto);

		//formPanel contenant les informations du contact
		panelBase.add(formPanel);
		formPanel.setPreferredSize(new Dimension(450,305));
		formPanel.add(prenom);
		formPanel.setBorder(new EmptyBorder(10,10,10,10));

		prenom.setFont(new Font(null, Font.BOLD, 20));
		formPanel.add(tfPrenom);
		tfPrenom.setFont(new Font(null, Font.PLAIN, 20));

		formPanel.add(nom);
		nom.setFont(new Font(null, Font.BOLD, 20));
		formPanel.add(tfNom);
		tfNom.setPreferredSize(new Dimension(200,10));
		tfNom.setFont(new Font(null, Font.PLAIN, 20));

		formPanel.add(email);
		email.setFont(new Font(null, Font.BOLD, 20));
		formPanel.add(tfEmail);
		tfEmail.setFont(new Font(null, Font.PLAIN,20));

		formPanel.add(telephone);
		telephone.setFont(new Font(null, Font.BOLD, 20));
		formPanel.add(tfTelephone);
		tfTelephone.setFont(new Font(null, Font.PLAIN,20));
		
		//BottomPanel contenant le bouton delete et le bouton save
		panelBase.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.add(buttonDelete, BorderLayout.EAST);
		bottomPanel.add(buttonValidate, BorderLayout.WEST);
		bottomPanel.setBackground(Color.RED);
		bottomPanel.setBorder(new EmptyBorder(0,100,0,100));
		bottomPanel.setPreferredSize(new Dimension(450,50));
	}
	
	// Méthode qui retourne les informations d'un nouveau contact
	public Contact getInfos(int  id) {
		return new Contact (tfNom.getText(), tfPrenom.getText(),tfEmail.getText(), tfTelephone.getText(), id);
	}
	
	public Contact modifiedContact() {
		contact.setPrenom(tfPrenom.getText());
		contact.setNom(tfNom.getText());
		contact.setEmail(tfEmail.getText());
		contact.setTelephone(tfTelephone.getText());
		
		return contact;
		
	}
	
	
	//Méthode permettant de vider les textFields
	protected void eraseInfos() {
		tfNom.setText(null);
		tfPrenom.setText(null);
		tfEmail.setText(null);
		tfTelephone.setText(null);
	}
	
	//Méthode permettant d'écrire les infos contacts dans les textFields
	protected void writeInfos(Contact contact) {
	
			tfNom.setText(contact.getNom());
			tfPrenom.setText(contact.getPrenom());
			tfEmail.setText(contact.getEmail());
			tfTelephone.setText(contact.getTelephone());
		
	}
	
	public boolean isFromContact() {
		return fromContact;
	}

	public void setFromContact(boolean fromContact) {
		this.fromContact = fromContact;
	}
	

	
	public void changeModification() {
		
		modification =! modification;
		
		if(modification == false) {
			bottomPanel.setVisible(modification);
			tfNom.setEditable(modification);
			tfPrenom.setEditable(modification);
			tfEmail.setEditable(modification);
			tfTelephone.setEditable(modification);
		} else {
			bottomPanel.setVisible(modification);
			bottomPanel.setVisible(modification);
			tfNom.setEditable(modification);
			tfPrenom.setEditable(modification);
			tfEmail.setEditable(modification);
			tfTelephone.setEditable(modification);
		}
	}


	//ActionListener sur le bouton save (appel à la méthode d'ajout d'un contact à la liste des contacts)
			class ClickPhotoContact implements ActionListener 
			{
						
				@Override
				public void actionPerformed(ActionEvent e) 
				{								
				
					System.out.println("je clique sur l'icône du contact");
					mainframe.getGalleryApp().rgallery();
					mainframe.getCardLayout().show(mainframe.getContentPanel(), "galleryPanel");
					mainframe.getGalleryApp().setFromContact(1);
					
				
//					contactApp.getMainframe().getCardLayout().show(contactApp.getMainframe().getContentPanel(), "galleryPanel");
//					cardLayout.show(contentPanel, "galleryPanel");
				}
			}

}
