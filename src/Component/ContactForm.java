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
	protected JLabel nom = new LabelPerso("Nom: ");
	protected JLabel prenom = new LabelPerso("Prénom: ");
	protected JLabel email = new LabelPerso("E-mail: ");
	protected JLabel telephone = new LabelPerso("Téléphone: ");

	// TextField infos contacts
	protected JTextField tfNom = new TextfieldPerso();
	protected JTextField tfPrenom = new TextfieldPerso();
	protected JTextField tfEmail = new TextfieldPerso();
	protected JTextField tfTelephone = new TextfieldPerso();

	// Icone de base  du champ contact 
	protected ButtonCreation contactPhoto = new ButtonCreation("contactPhoto", new ImageIcon("images/Icones/ContactVideNoir.png"));

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
	
	protected boolean modification;
	
	protected int id = 0;

	protected ButtonCreation tempIcon;

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
setBackground(Color.WHITE);
setOpaque(true);
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
photoPanel.setBackground(Color.WHITE);
		
		//formPanel contenant les informations du contact
		panelBase.add(formPanel);
		formPanel.setBackground(Color.WHITE);
		formPanel.setPreferredSize(new Dimension(450,305));
		formPanel.setBorder(new EmptyBorder(10,10,10,10));
		
		formPanel.add(prenom);
		formPanel.add(tfPrenom);
		formPanel.add(nom);
		formPanel.add(tfNom);
		formPanel.add(email);
		formPanel.add(tfEmail);
		formPanel.add(telephone);
		formPanel.add(tfTelephone);
		
		//BottomPanel contenant le bouton delete et le bouton save
		panelBase.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.add(buttonDelete, BorderLayout.EAST);
		bottomPanel.add(buttonValidate, BorderLayout.WEST);
		bottomPanel.setBackground(Color.RED);
		bottomPanel.setBorder(new EmptyBorder(0,100,0,100));
		bottomPanel.setPreferredSize(new Dimension(450,50));
	}
	
	// Méthode qui retourne les informations d'un nouveau contact
	public Contact getInfos() {
		return new Contact (tfNom.getText(), tfPrenom.getText(),tfEmail.getText(), tfTelephone.getText(), id, contactPhoto.getIcon().toString());
	}
	
	public Contact modifiedContact() {
		contact.setPrenom(tfPrenom.getText());
		contact.setNom(tfNom.getText());
		contact.setEmail(tfEmail.getText());
		contact.setTelephone(tfTelephone.getText());
		contact.setImageContactPath(contactPhoto.getIcon().toString());
		return contact;
	}
	
	
	//Méthode permettant de vider les textFields
	protected void eraseInfos() {
		tfNom.setText(null);
		tfPrenom.setText(null);
		tfEmail.setText(null);
		tfTelephone.setText(null);
		contactPhoto.setIcon(new ImageIcon("images/Icones/ContactVideNoir.png"));
	}
	
	//Méthode permettant d'écrire les infos contacts dans les textFields
	protected void writeInfos(Contact contact) {
			tfNom.setText(contact.getNom());
			tfPrenom.setText(contact.getPrenom());
			tfEmail.setText(contact.getEmail());
			tfTelephone.setText(contact.getTelephone());
			contactPhoto.setIcon(new ImageIcon(contact.getImageContactPath()));
	}
	
	public ButtonCreation getContactPhoto() {
		return contactPhoto;
	}

	public void setContactPhoto(ButtonCreation contactPhoto) {
		this.contactPhoto = contactPhoto;
	}

	public ButtonCreation getTempIcon() {
		return tempIcon;
	}

	public void setTempIcon(ButtonCreation tempIcon) {
		this.tempIcon = tempIcon;
	}
	
	public void changeModification() {
		
		modification =! modification;
		
		if(modification == false) {
			bottomPanel.setVisible(modification);
			tfNom.setEditable(modification);
			tfPrenom.setEditable(modification);
			tfEmail.setEditable(modification);
			tfTelephone.setEditable(modification);
			contactPhoto.removeActionListener(new ClickPhotoContact());
		} else {
			bottomPanel.setVisible(modification);
			bottomPanel.setVisible(modification);
			tfNom.setEditable(modification);
			tfPrenom.setEditable(modification);
			tfEmail.setEditable(modification);
			tfTelephone.setEditable(modification);
			contactPhoto.addActionListener(new ClickPhotoContact());
		}
	}

	//ActionListener sur l'icône contact qui ouvre la galerie
			class ClickPhotoContact implements ActionListener 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{													
					mainframe.getContactApp().getCardLayoutContact().show(mainframe.getContactApp().getContentContact(), "galleryApp");
				}
			}
}
