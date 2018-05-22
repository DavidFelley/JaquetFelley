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
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import Component.ButtonCreation;
import Component.ContactPanel;
import Component.GalleryPanel;
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
		private JPanel topPanel = new JPanel(new FlowLayout());
		
		//Heure Top Panel
		private JLabel heure = new JLabel();
		final private DateFormat DATEFORMAT = new SimpleDateFormat("HH:mm");
		private Timer timer = new Timer(0, new CurrentTime());
		
//Panel applications
private JPanel homepagePanel = new JPanel (new GridBagLayout());
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


private ContactPanel contactPanel = new ContactPanel();
private GalleryPanel galleryPanel = new GalleryPanel();

	public MainFrame() 
	{
		//Top Panel
		topPanel.setOpaque(false);
		topPanel.setPreferredSize(new Dimension(450, 40));
		timer.start();
		topPanel.add(heure);
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
		
		// Forme du téléphone
		setContentPane(formPanel);
		formPanel.setOpaque(false);
		formPanel.setLayout(new BorderLayout());
		formPanel.add(contentPanel, BorderLayout.CENTER);
		formPanel.add(homePanel,BorderLayout.SOUTH);
		formPanel.add(topPanel,BorderLayout.NORTH);
		
		//Gestion des panels
		contentPanel.add(backgroundPanel, "backgroundPanel");
		backgroundPanel.setLayout(new BorderLayout());
		
contentPanel.add(contactPanel, "contactPanel");	
contentPanel.add(galleryPanel, "galleryPanel");
		
		//Home Panel
		homePanel.setOpaque(false);
		homePanel.setPreferredSize(new Dimension(450, 40));
		
		homePanel.add(homeButton);
homeButton.addActionListener(new ClickHome());
		
	//Homepage panel
	homepagePanel.setOpaque(false);
	backgroundPanel.add(homepagePanel, BorderLayout.CENTER);	
	
	//APPLICATIONS
	c.insets = new Insets(7,7,7,7);
	c.fill = GridBagConstraints.BOTH;
	c.weightx = 3;
	c.weighty = 3;
	c.ipady = c.anchor = GridBagConstraints.CENTER;
	
	//BOUTON BOURSE
	c.gridx = 0;
	c.gridy = 0;
	homepagePanel.add(bourseButton,c);
	bourseButton.addMouseListener(new ChangeBourse());

	//BOUTON CALCULETTE
	c.gridx = 1;
	c.gridy = 0;
	c.gridwidth = 2;
	homepagePanel.add(calculatorButton,c);
	calculatorButton.addMouseListener(new ChangeCalculator());
	
	//BOUTON CONTACT
	c.gridx = 3;
	c.gridy = 0;
	homepagePanel.add(contactButton,c);
	contactButton.addMouseListener(new ChangeContact());
	contactButton.addActionListener(new ClickContact());
	
	//BOUTON GALERIE
	c.gridx = 0;
	c.gridy = 1;
	homepagePanel.add(galleryButton,c);
	galleryButton.addMouseListener(new ChangeGallery());
	galleryButton.addActionListener(new ClickGallery());
	
	//BOUTON MAP
	c.gridx = 1;
	c.gridy = 1;
	c.gridwidth = 2;
	homepagePanel.add(mapButton,c);
	mapButton.addMouseListener(new ChangeMap());
	
	//BOUTON MESSAGE
	c.gridx = 3;
	c.gridy = 1;
	homepagePanel.add(messageButton,c);
	messageButton.addMouseListener(new ChangeMessage());
	
	//BOUTON MUSIC
	c.gridx = 0;
	c.gridy = 2;
	c.gridwidth = 2;
	homepagePanel.add(musicButton,c);
	musicButton.addMouseListener(new ChangeMusic());

	//BOUTON PARAMETER
	c.gridx = 2;
	c.gridy = 2;
	c.gridwidth = 2;
	homepagePanel.add(parameterButton,c);
	parameterButton.addMouseListener(new ChangeParameter());
	
	//BOUTON OFF
	c.gridx = 3;
	c.gridy = 2;
	c.gridwidth = 2;
	homepagePanel.add(offButton,c);
	offButton.addActionListener(new ClickPowerOff());
	offButton.addMouseListener(new ChangeOff());

	
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
	
	class ClickPowerOff implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);
		}
		
	}
	
	class ClickContact implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			cardLayout.show(contentPanel, "contactPanel");
		}
	}
	
	class ClickGallery implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			cardLayout.show(contentPanel, "galleryPanel");
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
	
	
	
	
	
	
	// Mouse Listener changement icône 
	
	class ChangeOff extends MouseAdapter { 
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			if(offButton.getName().compareTo("off")==0) 
			offButton.setIcon(new ImageIcon("images/Icones/powerVide.png"));			
		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			if(offButton.getName().compareTo("off")==0) 
			offButton.setIcon(new ImageIcon("images/Icones/powerPlein.png"));	
		}
	}
	
	class ChangeContact extends MouseAdapter { 
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			if(contactButton.getName().compareTo("contact")==0) 
			contactButton.setIcon(new ImageIcon("images/Icones/contactVide.png"));			
		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			if(contactButton.getName().compareTo("contact")==0) 
			contactButton.setIcon(new ImageIcon("images/Icones/contactPlein.png"));	
		}
	}
	
	class ChangeGallery extends MouseAdapter { 
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			if(galleryButton.getName().compareTo("gallery")==0) 
			galleryButton.setIcon(new ImageIcon("images/Icones/galleryVide.png"));			
		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			if(galleryButton.getName().compareTo("gallery")==0) 
			galleryButton.setIcon(new ImageIcon("images/Icones/galleryPlein.png"));	
		}
	}
	
	class ChangeBourse extends MouseAdapter { 
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			if(bourseButton.getName().compareTo("bourse")==0) 
			bourseButton.setIcon(new ImageIcon("images/Icones/bourseVide.png"));			
		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			if(bourseButton.getName().compareTo("bourse")==0) 
			bourseButton.setIcon(new ImageIcon("images/Icones/boursePlein.png"));	
		}
	}

	class ChangeCalculator extends MouseAdapter { 
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			if(calculatorButton.getName().compareTo("calculator")==0) 
			calculatorButton.setIcon(new ImageIcon("images/Icones/calculatorVide.png"));			
		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			if(calculatorButton.getName().compareTo("calculator")==0) 
			calculatorButton.setIcon(new ImageIcon("images/Icones/calculatorPlein.png"));	
		}
	}
	
	class ChangeMap extends MouseAdapter { 
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			if(mapButton.getName().compareTo("map")==0) 
			mapButton.setIcon(new ImageIcon("images/Icones/mapsVide.png"));			
		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			if(mapButton.getName().compareTo("map")==0) 
			mapButton.setIcon(new ImageIcon("images/Icones/mapsPlein.png"));	
		}
	}
	
	
	class ChangeMessage extends MouseAdapter { 
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			if(messageButton.getName().compareTo("message")==0) 
				messageButton.setIcon(new ImageIcon("images/Icones/messageVide.png"));			
		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			if(messageButton.getName().compareTo("message")==0) 
			messageButton.setIcon(new ImageIcon("images/Icones/messagePlein.png"));	
		}
	}
	
	class ChangeMusic extends MouseAdapter { 
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			if(musicButton.getName().compareTo("music")==0) 
				musicButton.setIcon(new ImageIcon("images/Icones/earphonesVide.png"));			
		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			if(musicButton.getName().compareTo("music")==0) 
				musicButton.setIcon(new ImageIcon("images/Icones/earphonesPlein.png"));	
		}
	}
	
	class ChangeParameter extends MouseAdapter { 
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			if(parameterButton.getName().compareTo("parameter")==0) 
				parameterButton.setIcon(new ImageIcon("images/Icones/parametreVide.png"));			
		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			if(parameterButton.getName().compareTo("parameter")==0) 
				parameterButton.setIcon(new ImageIcon("images/Icones/parametrePlein.png"));	
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
