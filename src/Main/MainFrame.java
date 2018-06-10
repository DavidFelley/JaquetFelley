/*
 *PW Week *
 *Exercise
 *Author : Valentin Jaquet
 *Creation date : 7 mai 2018
 */
package Main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import Component.ButtonCreation;
import Component.CalculatriceApp;
import Component.ContactApp;
import Component.ContactForm;
import Component.GalleryApp;
import View.BackgroundImagePanel;

public class MainFrame extends JFrame{

	// Panel principal
	private BackgroundImagePanel backgroundPanel = new BackgroundImagePanel(new ImageIcon("images/Fond/wallpaper3.jpg"));

	// Panel de forme du smartphone
	private BackgroundImagePanel formPanel = new BackgroundImagePanel(new ImageIcon("images/Fond/smartphone.png"));

	// Gestion des panels
	private CardLayout cardLayout = new CardLayout();
	private JPanel contentPanel = new JPanel(cardLayout);

	//Panel Home
	private JPanel homePanel = new JPanel(new FlowLayout());

	//Top Panel
	private JPanel topPanel = new JPanel(new BorderLayout());

	//Heure Top Panel
	private JLabel heure = new JLabel();
	final private DateFormat DATEFORMAT = new SimpleDateFormat("HH:mm");
	private Timer timer = new Timer(0, new CurrentTime());

	//Panel applications
	//private JPanel homepagePanel = new JPanel (new GridBagLayout());
	private JPanel homepagePanel = new JPanel (new GridLayout(3,3));
	private GridBagConstraints c = new GridBagConstraints();

	//Boutons et applications
	private ButtonCreation offButton = new ButtonCreation("off", new ImageIcon("images/Icones/powerVide.png"));
	private ButtonCreation contactButton = new ButtonCreation("contact", new ImageIcon("images/Icones/contactVide.png"));
	private ButtonCreation galleryButton = new ButtonCreation("gallery", new ImageIcon("images/Icones/galleryVide.png"));
	private ButtonCreation bourseButton = new ButtonCreation("bourse", new ImageIcon("images/Icones/bourseVide.png"));
	private ButtonCreation calculatorButton = new ButtonCreation("calculator", new ImageIcon("images/Icones/calculatorVide.png"));
	private ButtonCreation mapButton = new ButtonCreation("map", new ImageIcon("images/Icones/mapsVide.png"));
	private ButtonCreation messageButton = new ButtonCreation("message", new ImageIcon("images/Icones/messageVide.png"));
	private ButtonCreation musicButton = new ButtonCreation("music", new ImageIcon("images/Icones/earphonesVide.png"));
	private ButtonCreation parameterButton = new ButtonCreation("parameter", new ImageIcon("images/Icones/parametreVide.png"));
	private ButtonCreation homeButton = new ButtonCreation ("home", new ImageIcon("images/Icones/home.png"));

	private ContactApp contactApp = new ContactApp(this);
	private GalleryApp galleryApp = new GalleryApp();
	private CalculatriceApp calculatriceApp = new CalculatriceApp();

	public MainFrame() 
	{
		//Top Panel
		topPanel.setOpaque(false);
		topPanel.setPreferredSize(new Dimension(450, 40));
		timer.start();
		topPanel.add(heure, BorderLayout.CENTER);
		heure.setHorizontalAlignment(JLabel.CENTER);
		heure.setVerticalAlignment(JLabel.CENTER);
		heure.setForeground(Color.WHITE);
		heure.setPreferredSize(new Dimension(50, 40));
		heure.setFont(new Font(null, Font.BOLD, 18));

		//PARAMETRES DE LA FRAME
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(450,800);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setBackground(new Color(0, 0, 0, 0)); // Masque le fond gris de base pour les coins

		// Forme du t�l�phone
		setContentPane(formPanel);
		formPanel.setOpaque(false);
		formPanel.setLayout(new BorderLayout());
		formPanel.add(contentPanel, BorderLayout.CENTER);
		formPanel.add(homePanel,BorderLayout.SOUTH);
		formPanel.add(topPanel,BorderLayout.NORTH);

		//Gestion des panels
		contentPanel.add(backgroundPanel, "backgroundPanel");
		backgroundPanel.setLayout(new BorderLayout());
		contentPanel.add(contactApp, "contactApp");	
		contentPanel.add(galleryApp, "galleryPanel");
		contentPanel.add(calculatriceApp, "calculatriceApp");

		//Home Panel
		homePanel.setOpaque(false);
		homePanel.setPreferredSize(new Dimension(450, 40));

		homePanel.add(homeButton);
		homeButton.addActionListener(new ClickHome());

		//Homepage panel
		homepagePanel.setOpaque(false);
		backgroundPanel.add(homepagePanel, BorderLayout.CENTER);	

		//BOUTON BOURSE
		homepagePanel.add(bourseButton);
		bourseButton.addMouseListener(new ChangeLayoutButton());

		//BOUTON CALCULETTE
		homepagePanel.add(calculatorButton);
		calculatorButton.addMouseListener(new ChangeLayoutButton());
		calculatorButton.addActionListener(new ClickApp());

		//BOUTON CONTACT
		homepagePanel.add(contactButton);
		contactButton.addMouseListener(new ChangeLayoutButton());
		contactButton.addActionListener(new ClickApp());

		//BOUTON GALERIE
		homepagePanel.add(galleryButton);
		galleryButton.addMouseListener(new ChangeLayoutButton());
		galleryButton.addActionListener(new ClickApp());

		//BOUTON MAP
		homepagePanel.add(mapButton);
		mapButton.addMouseListener(new ChangeLayoutButton());

		//BOUTON MESSAGE
		homepagePanel.add(messageButton);
		messageButton.addMouseListener(new ChangeLayoutButton());

		//BOUTON MUSIC
		homepagePanel.add(musicButton);
		musicButton.addMouseListener(new ChangeLayoutButton());

		//BOUTON PARAMETER
		homepagePanel.add(parameterButton);
		parameterButton.addMouseListener(new ChangeLayoutButton());

		//BOUTON OFF
		homepagePanel.add(offButton);
		offButton.addActionListener(new ClickApp());
		offButton.addMouseListener(new ChangeLayoutButton());
	}

	public GalleryApp getGalleryApp() {
		return galleryApp;
	}

	public ContactApp getContactApp() {
		return contactApp;
	}

	class CurrentTime implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Calendar now = Calendar.getInstance();
			heure.setText(DATEFORMAT.format(now.getTime()));
		}
	}

	class ClickHome implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			cardLayout.show(contentPanel, "backgroundPanel");
		}
	}

	class ClickApp implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			ButtonCreation element = (ButtonCreation) arg0.getSource();
			String app = element.getName();
			switch(app) {
			case "contact" : 
				cardLayout.show(contentPanel, "contactApp");
				break;
			case "calculator" :
				cardLayout.show(contentPanel, "calculatriceApp");
				break;
			case "gallery" :
				cardLayout.show(contentPanel, "galleryPanel");
				break;
			case "off" : 
				contactApp.serializeContact();
				System.exit(0);
				break;
			}
		}
	}

	// Mouse Listener changement ic�ne 

	class ChangeLayoutButton extends MouseAdapter { 
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			switch(arg0.getComponent().getName()) {
			case "off" :
				offButton.setIcon(new ImageIcon("images/Icones/powerVide.png"));	
				break;
			case "contact" : 
				contactButton.setIcon(new ImageIcon("images/Icones/contactVide.png"));	
				break;
			case "gallery" :
				galleryButton.setIcon(new ImageIcon("images/Icones/galleryVide.png"));
				break;
			case "bourse" :
				bourseButton.setIcon(new ImageIcon("images/Icones/bourseVide.png"));	
				break;
			case "calculator" :
				calculatorButton.setIcon(new ImageIcon("images/Icones/calculatorVide.png"));	
				break;
			case "map" :
				mapButton.setIcon(new ImageIcon("images/Icones/mapsVide.png"));	
				break;
			case "message" :
				messageButton.setIcon(new ImageIcon("images/Icones/messageVide.png"));	
				break;
			case "music" :
				musicButton.setIcon(new ImageIcon("images/Icones/earphonesVide.png"));	
				break;
			case "parameter" :
				parameterButton.setIcon(new ImageIcon("images/Icones/parametreVide.png"));
				break;
			}
		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			switch(e.getComponent().getName()) {
			case "off" :
				offButton.setIcon(new ImageIcon("images/Icones/powerPlein.png"));	
				break;
			case "contact" : 
				contactButton.setIcon(new ImageIcon("images/Icones/contactPlein.png"));	
				break;
			case "gallery" :
				galleryButton.setIcon(new ImageIcon("images/Icones/galleryPlein.png"));	
				break;
			case "bourse" :
				bourseButton.setIcon(new ImageIcon("images/Icones/boursePlein.png"));	
				break;
			case  "calculator" :
				calculatorButton.setIcon(new ImageIcon("images/Icones/calculatorPlein.png"));	
				break;
			case "map" :
				mapButton.setIcon(new ImageIcon("images/Icones/mapsPlein.png"));	
				break;
			case "message" :
				messageButton.setIcon(new ImageIcon("images/Icones/messagePlein.png"));		
				break;
			case "music" :
				musicButton.setIcon(new ImageIcon("images/Icones/earphonesPlein.png"));	
				break;
			case "parameter" :
				parameterButton.setIcon(new ImageIcon("images/Icones/parametrePlein.png"));		
				break;
			}
		}
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}	

	public BackgroundImagePanel getBackgroundPanel() {
		return backgroundPanel;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public JPanel getHomepagePanel() {
		return homepagePanel;
	}
}