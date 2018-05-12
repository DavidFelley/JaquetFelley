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
		private JPanel homePanel = new JPanel();
		
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
private ButtonCreation offButton = new ButtonCreation(new ImageIcon("images/Icones/powerVide.png"));
private ButtonCreation contactButton = new ButtonCreation(new ImageIcon("images/Icones/contact.png"));
private ButtonCreation galleryButton = new ButtonCreation(new ImageIcon("images/Icones/gallery.png"));
private ButtonCreation bourseButton = new ButtonCreation(new ImageIcon("images/Icones/bourse.png"));
private ButtonCreation calculatorButton = new ButtonCreation(new ImageIcon("images/Icones/calculator.png"));
private ButtonCreation MapButton = new ButtonCreation(new ImageIcon("images/Icones/maps.png"));
private ButtonCreation messageButton = new ButtonCreation(new ImageIcon("images/Icones/message.png"));
private ButtonCreation musicButton = new ButtonCreation(new ImageIcon("images/Icones/earphones.png"));
private ButtonCreation parameterButton = new ButtonCreation(new ImageIcon("images/Icones/parametre.png"));

	public MainFrame() 
	{
		//Top Panel
		topPanel.setOpaque(false);
		topPanel.setPreferredSize(new Dimension(450, 50));
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
		
		//Home Panel
		homePanel.setOpaque(false);
		homePanel.setPreferredSize(new Dimension(450, 50));
		
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

	//BOUTON CALCULETTE
	c.gridx = 1;
	c.gridy = 0;
	c.gridwidth = 2;
	homepagePanel.add(calculatorButton,c);
	
	//BOUTON CONTACT
	c.gridx = 3;
	c.gridy = 0;
	homepagePanel.add(contactButton,c);
	
	//BOUTON GALERIE
	c.gridx = 0;
	c.gridy = 1;
	homepagePanel.add(galleryButton,c);
	
	//BOUTON MAP
	c.gridx = 1;
	c.gridy = 1;
	c.gridwidth = 2;
	homepagePanel.add(MapButton,c);
	
	//BOUTON MESSAGE
	c.gridx = 3;
	c.gridy = 1;
	homepagePanel.add(messageButton,c);
	
	//BOUTON MUSIC
	c.gridx = 0;
	c.gridy = 2;
	c.gridwidth = 2;
	homepagePanel.add(musicButton,c);

	//BOUTON MUSIC
	c.gridx = 2;
	c.gridy = 2;
	c.gridwidth = 2;
	homepagePanel.add(parameterButton,c);

	//BOUTON OFF
	c.gridx = 3;
	c.gridy = 2;
	c.gridwidth = 2;
	homepagePanel.add(offButton,c);
	offButton.addActionListener(new ClickPowerOff());
	offButton.addMouseListener(new MouseBackground());


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
	
	class MouseBackground implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

	

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			offButton.setIcon(new ImageIcon("images/Icones/powerVide.png"));
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
		
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			offButton.setIcon(new ImageIcon("images/Icones/power.png"));			
		}
		
	}
	
	
	
}
