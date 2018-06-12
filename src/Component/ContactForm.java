package Component;
/**
 * ------------------------------------------------------------------------------------------------------ <br/>
 * Classe : ContactForm <br/>
 * ------------------------------------------------------------------------------------------------------ <br/>
 * Auteur : David Felley et Valentin Jaquet <br/>
 * Description de la classe : Cette classe gère la formulaire de contact de base <br/>
 * ------------------------------------------------------------------------------------------------------ <br/>
 * Remarque : Source de la méthode ajustant la taille des images
 * 			  https://www.mkyong.com/java/how-to-resize-an-image-in-java/ <br/>
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
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import Main.MainFrame;


public class ContactForm extends JPanel
{

	// Label infos contacts
	private JLabel nom = new LabelPerso("Nom: ");
	private JLabel prenom = new LabelPerso("Prénom: ");
	private JLabel email = new LabelPerso("E-mail: ");
	private JLabel telephone = new LabelPerso("Téléphone: ");

	// TextField infos contacts
	private TextfieldPerso tfNom = new TextfieldPerso();
	private TextfieldPerso tfPrenom = new TextfieldPerso();
	private TextfieldPerso tfEmail = new TextfieldPerso();
	private TextfieldPerso tfTelephone = new TextfieldPerso();

	// Icone de base du champ contact 
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

	protected ArrayList <Contact> contacts;
	private CardLayout cl;
	private JPanel jp;
	protected MainFrame mainframe ;
	protected Color color = new Color(78,104,141);
	protected Contact contact;
	private boolean modification;
	private int id = 0;
	private ButtonCreation tempIcon;
	private String pathIcon;
	private boolean checkFormular;

	/**
	 * Constructeur du formulaire de contact vide
	 * @param modification
	 * @param cl
	 * @param jp
	 * @param mainframe
	 */
	public ContactForm(boolean modification, CardLayout cl, JPanel jp, MainFrame mainframe) 
	{
		this.modification = modification;
		this.cl = cl;
		this.jp = jp;
		this.mainframe = mainframe;
		createFormPanel();
	}

	/**
	 * Constructeur du formulaire pour un contact déjà existant
	 * @param contact
	 * @param modification
	 * @param cl
	 * @param jp
	 * @param mainframe
	 */
	public ContactForm(Contact contact, boolean modification, CardLayout cl, JPanel jp, MainFrame mainframe) 
	{
		this.contact = contact;
		this.modification = modification;
		this.cl = cl;
		this.jp = jp;
		this.mainframe = mainframe;
		createFormPanel();
		writeInfos(contact);
	}

	/**
	 * Permet de créer le panel formulaire de base (barre de menus, photo du contact, champs du formulaire, bouton de sauvegarde et suppression)
	 */
	public void createFormPanel() 
	{

		setLayout(new BorderLayout());
		setBackground(Color.WHITE);

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
		tfPrenom.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				tfPrenom.setText("");
				tfPrenom.setForeground(Color.BLACK);
				tfPrenom.setBorder(new LineBorder(Color.GRAY));
			}
		});

		formPanel.add(nom);
		formPanel.add(tfNom);
		tfNom.setForeground(Color.BLACK);
		tfNom.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
				tfNom.setText("");
				tfNom.setForeground(Color.BLACK);
				tfNom.setBorder(new LineBorder(Color.GRAY));
			}
		});

		formPanel.add(email);
		formPanel.add(tfEmail);

		formPanel.add(telephone);
		formPanel.add(tfTelephone);
		tfTelephone.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
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

	/**
	 * Retourne les informations d'un nouveau contact
	 * @return
	 */
	public Contact getInfos() 
	{
		return new Contact (tfNom.getText(), tfPrenom.getText(),tfEmail.getText(), tfTelephone.getText(),contactPhoto.getName());
	}

	/**
	 * Retourne les informations d'un contact déjà existant
	 * @return
	 */
	public Contact modifiedContact() 
	{
		contact.setPrenom(tfPrenom.getText());
		contact.setNom(tfNom.getText());
		contact.setEmail(tfEmail.getText());
		contact.setTelephone(tfTelephone.getText());
		contact.setImageContactPath(contactPhoto.getName());
		return contact;
	}

	/**
	 * Permet de vider les textFields et de mettre l'icône de contact de base
	 */
	protected void eraseInfos() 
	{
		tfNom.setText(null);
		tfPrenom.setText(null);
		tfEmail.setText(null);
		tfTelephone.setText(null);
		tfTelephone.setForeground(Color.BLACK);
		contactPhoto.setIcon(new ImageIcon("images/Icones/ContactVideNoir.png"));
	}

	/**
	 * Permet d'écrire les informations du contacts dans les champs textfields ainsi que la photo associée aux contacts
	 * @param contact
	 */
	protected void writeInfos(Contact contact) 
	{
		tfNom.setText(contact.getNom());
		tfPrenom.setText(contact.getPrenom());
		tfEmail.setText(contact.getEmail());
		tfTelephone.setText(contact.getTelephone());
		contactPhoto.setIcon(new ImageIcon(createContactIcon(contact.getImageContactPath())));
	}

	/**
	 * Permet de rendre les textfields éditables et la photo du contact cliquable si l'on vient de la classe ContactAdd ou ContactModify
	 */
	public void changeModification() 
	{

		modification =! modification;

		if(modification == false) 
		{
			bottomPanel.setVisible(modification);
			tfNom.setEditable(modification);
			tfPrenom.setEditable(modification);
			tfEmail.setEditable(modification);
			tfTelephone.setEditable(modification);
			contactPhoto.removeActionListener(new ClickPhotoContact());
		} 
		else 
		{
			bottomPanel.setVisible(modification);
			bottomPanel.setVisible(modification);
			tfNom.setEditable(modification);
			tfPrenom.setEditable(modification);
			tfEmail.setEditable(modification);
			tfTelephone.setEditable(modification);
			contactPhoto.addActionListener(new ClickPhotoContact());
		}
	}

	/**
	 * Permet d'ajuster la taille de l'icône contact
	 * @param path
	 * @return
	 */
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

	/**
	 * Permet de vérifier les informations saisies par l'utilisateur
	 * @return
	 */
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

	/**
	 * ActionListner qui permet d'ouvrir la galerie lorsque l'on clique sur l'icône du contact
	 */
	class ClickPhotoContact implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{													
			mainframe.getContactApp().getCardLayoutContact().show(mainframe.getContactApp().getContentContact(), "galleryApp");
		}
	}
	
	// Getters & Setters
		public ButtonCreation getContactPhoto() 
		{
			return contactPhoto;
		}

		public void setContactPhoto(ButtonCreation contactPhoto) 
		{
			this.contactPhoto = contactPhoto;
		}

		public ButtonCreation getTempIcon() 
		{
			return tempIcon;
		}

		public void setTempIcon(ButtonCreation tempIcon) 
		{
			this.tempIcon = tempIcon;
		}
}
