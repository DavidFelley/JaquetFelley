package Component;
/**
* ------------------------------------------------------------------------------------------------------ <br/>
* Classe : ContactForm <br/>
* ------------------------------------------------------------------------------------------------------ <br/>
* Auteur : David Felley et Valentin Jaquet <br/>
* Description de la classe : ..... <br/>
* ------------------------------------------------------------------------------------------------------ <br/>
* Remarque : - <br/>
* ------------------------------------------------------------------------------------------------------ <br/>
*/
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import Main.MainFrame;


public class ContactForm extends JPanel{

	// Label infos contacts
	protected JLabel nom = new LabelPerso("Nom: ");
	protected JLabel prenom = new LabelPerso("Prénom: ");
	protected JLabel email = new LabelPerso("E-mail: ");
	protected JLabel telephone = new LabelPerso("Téléphone: ");

	// TextField infos contacts
	protected TextfieldPerso tfNom = new TextfieldPerso();
	protected TextfieldPerso tfPrenom = new TextfieldPerso();
	protected TextfieldPerso tfEmail = new TextfieldPerso();
	protected TextfieldPerso tfTelephone = new TextfieldPerso();

	// Icone de base  du champ contact 
	protected ButtonCreation contactPhoto = new ButtonCreation("images/Icones/ContactVideNoir.png", new ImageIcon("images/Icones/ContactVideNoir.png"));

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
	
	protected String pathIcon;

	private boolean checkFormular;
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
		photoPanel.setPreferredSize(new Dimension(325,325));
		photoPanel.add(contactPhoto);
		photoPanel.setBackground(Color.WHITE);
		
		//formPanel contenant les informations du contact
		panelBase.add(formPanel);
		formPanel.setBackground(Color.WHITE);
		formPanel.setPreferredSize(new Dimension(450,305));
		formPanel.setBorder(new EmptyBorder(10,10,10,10));
		
		formPanel.add(prenom);
		formPanel.add(tfPrenom);
		tfPrenom.setForeground(Color.BLACK);
		tfPrenom.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
					tfPrenom.setText("");
					tfPrenom.setForeground(Color.BLACK);
					tfPrenom.setBorder(new LineBorder(Color.GRAY));
			}
		});
	
		formPanel.add(nom);
		formPanel.add(tfNom);
		tfNom.setForeground(Color.BLACK);
		tfNom.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
					tfNom.setText("");
					tfNom.setForeground(Color.BLACK);
					tfNom.setBorder(new LineBorder(Color.GRAY));
			}
		});
		formPanel.add(email);
		formPanel.add(tfEmail);
		formPanel.add(telephone);
		formPanel.add(tfTelephone);
		tfTelephone.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
					tfTelephone.setText("");
					tfTelephone.setForeground(Color.BLACK);
					tfTelephone.setBorder(new LineBorder(Color.GRAY));
			}
		});
		
		
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
		return new Contact (tfNom.getText(), tfPrenom.getText(),tfEmail.getText(), tfTelephone.getText(), id, contactPhoto.getName());
	}
	
	public Contact modifiedContact() {
		contact.setPrenom(tfPrenom.getText());
		contact.setNom(tfNom.getText());
		contact.setEmail(tfEmail.getText());
		contact.setTelephone(tfTelephone.getText());
		contact.setImageContactPath(contactPhoto.getName());
		return contact;
	}
	
	
	//Méthode permettant de vider les textFields
	protected void eraseInfos() {
		tfNom.setText(null);
	
		tfPrenom.setText(null);
	
		
		tfEmail.setText(null);
		tfTelephone.setText(null);
		tfTelephone.setForeground(Color.BLACK);

		
		contactPhoto.setIcon(new ImageIcon("images/Icones/ContactVideNoir.png"));
	}
	
	//Méthode permettant d'écrire les infos contacts dans les textFields et photo associées aux contacts
	protected void writeInfos(Contact contact) {
			tfNom.setText(contact.getNom());
			tfPrenom.setText(contact.getPrenom());
			tfEmail.setText(contact.getEmail());
			tfTelephone.setText(contact.getTelephone());
			contactPhoto.setIcon(new ImageIcon(createContactIcon(contact.getImageContactPath())));
			
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
	
	public boolean checkInfosContact() 
	{
		boolean check;
		CheckFormular checkFormular = new CheckFormular();
		check = checkFormular.isNotEmpty(tfPrenom);
		if(!check)
			return check;
		check = checkFormular.isNotEmpty(tfNom);
		if(!check)
			return check;
		check = checkFormular.patternNumbers(tfTelephone);
		if(!check)
			return check;
		return check;
	}
	
	public BufferedImage createContactIcon(String path)
	{
		BufferedImage img = null ;
		int newWidth = 450;
		int newHeight = 325;
		try 
		{
			img = ImageIO.read(new File(path));
			if(path.equals("images/Icones/ContactVideNoir.png"))
			return img;
				if(img.getWidth() < img.getHeight())
				{
					newHeight = img.getHeight()*newWidth/img.getWidth();
				}
				else
				{
					newWidth = img.getWidth()*newHeight/img.getHeight();
				}

				BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, img.getType());
				Graphics2D g = resizedImage.createGraphics();
				g.setComposite(AlphaComposite.Src);
				g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
				g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				g.drawImage(img, 0, 0, newWidth, newHeight, null);
				g.dispose();

				resizedImage = resizedImage.getSubimage(0, 0, 450, 325);

				return resizedImage ;				
			
		}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		return null ;
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
