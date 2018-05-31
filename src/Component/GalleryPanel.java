/*
*Author David Felley
*Created on 22 mai 2018
*For JaquetFelley
*/

package Component;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GalleryPanel extends JPanel{
	
	//Varia
	private int numImg ;
	private ArrayList<String> photos = new ArrayList<String>();
	
	private JPanel backPanel = new JPanel(new FlowLayout());
	
	private CardLayout clGallery = new CardLayout();
	private JPanel panelGallery = new JPanel(clGallery);

	private ButtonCreation ajout = new ButtonCreation("ajout", new ImageIcon("images/Icones/plus.png"));
	private MenuBarre menuGalerie = new MenuBarre(ajout, "GALERIE");
	
	ButtonCreation salut = new ButtonCreation();
		
	//FileChooser permet d'ouvrir un navigateur de fichier
	private JFileChooser fc = new JFileChooser();
	//Ici on défini le type d'extension que le browser va accepter
	private FileNameExtensionFilter ff = new FileNameExtensionFilter("Extension types : Images", "jpg", "png", "jpeg");
	
	public GalleryPanel() 
	{		
		//Paramètre principaux de la galerie (Format, et topMenu)
		setLayout(new BorderLayout());
		add(menuGalerie, BorderLayout.NORTH);
		add(backPanel);
		backPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		ajout.addActionListener(new addGalerie());
		
		//Browser d'image
		fc.setApproveButtonText("Ajouter");
		fc.setAcceptAllFileFilterUsed(false);
		fc.setFileFilter(ff);
		
		backPanel.add(panelGallery);
		panelGallery.setLayout(new GridLayout(0, 3, 7, 7));
		panelGallery.setBorder(new EmptyBorder(3, 0, 0, 0));
		display();

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
				saveToGalerie(fc.getSelectedFile());
				//update();
			}
		}
	}
	
	//Sauvegarde une image choisie par le fc dans le dossier galerie.
	private void saveToGalerie(File img)
	{			
		String path = "config\\num.txt" ;

		readNumImg(path);
		
		numImg++;
		
		try
		{			
			BufferedImage bi = ImageIO.read(img) ;
			File outputfile = new File("images/Galerie/photo"+numImg+"."+getExtension(img));
			ImageIO.write(bi, getExtension(img), outputfile);
			
			//Sauvegarde du dernier numero d'image enregistrée
			FileWriter fwrite = new FileWriter (path);
			BufferedWriter bfwrite = new BufferedWriter(fwrite);
			bfwrite.write(String.valueOf(numImg));
			bfwrite.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	//Permet de récupérer l'extension de l'image que l'on veut enregistrer.
	private String getExtension(File fichier)
	{
		String imgExtension = fichier.getName();
		
		String fileName = fichier.getName();
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return imgExtension.substring(imgExtension.lastIndexOf(".") + 1);
		else
			return "";
	}
	
	//Lis le numéro stocké dans le fichier text de la galerie
	private int readNumImg(String path) 
	{
		
	    Scanner s;
		try
		{
			s = new Scanner(new File(path));
			numImg = s.nextInt();
			s.close();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	   
	    return numImg ;
	}
	
	//AFFICHAGE DE LA GALERIE ---> Penser a mettre a jour le tableau de photos a chaque ajout et suppression d'images !
	private ArrayList<String> arrayOfPhotos()
	{
		String path = "images/Galerie";
		
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		
		    for (int i = 0; i < listOfFiles.length; i++)
		    {
		    	System.out.println(listOfFiles[i]);
		    	photos.add(listOfFiles[i].getPath());
		    }
		    
		return photos ;
	}
	
	private BufferedImage createPreview(String path)
	{
		BufferedImage img = null ;
		int newWidth = 0;
		int newHeight = 0;
		
			try 
			{
				img = ImageIO.read(new File(path));
				System.out.println("Largeur :"+img.getWidth()+" Hauteur :"+img.getHeight());
				
				if(img.getWidth() < img.getHeight())
				{
					newWidth = 140;
					newHeight = img.getHeight()*140/img.getWidth();
					System.out.println(newHeight);
				}
				else
				{
					newWidth = img.getWidth()*140/img.getHeight();
					newHeight =  140;
					System.out.println(newWidth);
				}
				
				BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, img.getType());
				Graphics2D g = resizedImage.createGraphics();
				g.setComposite(AlphaComposite.Src);
				g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
				g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				 
				g.drawImage(img, 0, 0, newWidth, newHeight, null);
				g.dispose();
				
				resizedImage = resizedImage.getSubimage(0, 0, 140, 140);
				
				return resizedImage ;				
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return null ;
			
	}
	
	private void display()
	{
		arrayOfPhotos();
		
		for (int i = 0; i < photos.size(); i++) 
		{
			System.out.println(photos.get(i));
			panelGallery.add(salut = new ButtonCreation(new ImageIcon(createPreview(photos.get(i)))));
			salut.setPreferredSize(new Dimension(140, 140));
		}
	}
	
	
	
}
