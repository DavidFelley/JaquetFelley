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
	
	//Varia
	private int numImg ;
	
	private CardLayout clGallery = new CardLayout();
	private JPanel panelGallery = new JPanel(clGallery);

	private ButtonCreation ajout = new ButtonCreation("ajout", new ImageIcon("images/Icones/plus.png"));
	private MenuBarre menuGalerie = new MenuBarre(ajout, "GALERIE");
	
	//FileChooser permet d'ouvrir un navigateur de fichier
	private JFileChooser fc = new JFileChooser();
	//Ici on défini le type d'extension que le browser va accepter
	private FileNameExtensionFilter ff = new FileNameExtensionFilter("Extension types : Images", "jpg", "png", "jpeg");
	
	public GalleryPanel () 
	{		
		//Paramètre principaux de la galerie (Format, et topMenu)
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
	
	
//	public void saveToGalerie(File img)
//	{
//		setNumImg(numImg++);
//		
//		try
//		{
//			File outputfile = new File("image/Galerie/photo"+numImg+getExtension(img));
//			ImageIO.write(newimg, "png", outputfile);
//		}
//		catch (IOException e) 
//		{
//			e.printStackTrace();
//		}
//	}
	
	//Permet de récupérer l'extension de l'image que l'on veut enregistrer.
	private String getExtension(File img)
	{
		String imgExtension = img.getName();
		
		String fileName = img.getName();
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return imgExtension.substring(imgExtension.lastIndexOf(".") + 1);
		else
			return "";
	}

	public void setNumImg(int numImg)
	{
		this.numImg = numImg;
	}
	
//	//Récupérer le numéro de l'image enregistrée
//		public int setNumImg()
//		{
//				File galerie = new File("images/Galerie");
//				File[] f = galerie.listFiles();
//				numImg = f.length;
//				
//				if("images/Galerie/photo"+numImg)
//				
//				return numImg ;
//		}
	
	

	
	
	
	
	
}
