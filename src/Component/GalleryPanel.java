/*
*Author David Felley
*Created on 22 mai 2018
*For JaquetFelley
*/

package Component;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.jar.JarFile;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import Main.MainFrame;

public class GalleryPanel extends JPanel{
	
	private CardLayout clGallery = new CardLayout();
	private JPanel panelGallery = new JPanel(clGallery);

	private ButtonCreation ajout = new ButtonCreation("ajout", new ImageIcon("images/Icones/plus.png"));
	private MenuBarre menuGalerie = new MenuBarre(ajout, "GALERIE");
	
	//FileChooser permet d'ouvrir un navigateur de fichier
	private JFileChooser fc = new JFileChooser();
	//Ici on d�fini le type d'extension que le browser va accepter
	private FileNameExtensionFilter ff = new FileNameExtensionFilter("Extension types : Images", "jpg", "png", "jpeg");
	
	public GalleryPanel () {
		
		//Param�tre principaux de la galerie (Format, et topMenu)
		setLayout(new BorderLayout());
		add(panelGallery);
		add(menuGalerie, BorderLayout.NORTH);
		
		ajout.addActionListener(new addGalerie());
		
		//Browser d'image
		fc.setApproveButtonText("Ajouter");
		fc.setAcceptAllFileFilterUsed(false);
		fc.setFileFilter(ff);
		
	
		
		
		
		
		
	}
	
	class addGalerie implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			int resultat = fc.showOpenDialog(panelGallery);
			
			if(resultat == fc.CANCEL_OPTION)
			{
				fc.cancelSelection();
				return;
			}
			
			if (resultat == fc.APPROVE_OPTION) 
			{
				//copyFiles(fc.getSelectedFile());
				//update();
			}
		}
	}
	
	
	
}
