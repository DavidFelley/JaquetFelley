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

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import View.BackgroundImagePanel;

public class MainFrame extends JFrame{

	//GESTION DES PANELS 
	
	//PANEL PRINCIPAL
	private BackgroundImagePanel smartphonePanel = new BackgroundImagePanel(new ImageIcon("smartphoneBackground.png"));
	private BackgroundImagePanel wallpaperPanel = new BackgroundImagePanel(new ImageIcon("wallpaper.jpg"));
	
	//GESTION DES PANELS
	private CardLayout cardLayout = new CardLayout();
	private JPanel contentPanel = new JPanel(cardLayout);
		
	public MainFrame() {
		
		//PARAMETRES DE LA FRAME
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(480,860);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		add(smartphonePanel);
		
//		//FORME DU SMARTPHONE
//		setContentPane(smartphonePanel);
//		smartphonePanel.setOpaque(false);
//		smartphonePanel.setLayout(new BorderLayout());
//		smartphonePanel.add(contentPanel, BorderLayout.CENTER);
				
		
		
		
		
	}
}
