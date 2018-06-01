/*
*Author David Felley
*Created on 1 juin 2018
*For JaquetFelley
*/

package Component;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GalleryApp extends JPanel
{
	private CardLayout cardlayout = new CardLayout();
	private ButtonCreation element;
	private FullScreenPanel fullscreenPanel = new FullScreenPanel();
	
	public GalleryApp() 
	{
		setLayout(cardlayout);
		add(new GalleryPanel(),"GalleryPanel");
		add(fullscreenPanel,"FullScreenPanel");
	}
	
	class GalleryPanel extends JPanel
	{
		private ButtonCreation ajout = new ButtonCreation("ajout", new ImageIcon("images/Icones/plus.png"));
		private MenuBarre menuGalerie = new MenuBarre(ajout, "GALERIE");
		
		//FileChooser permet d'ouvrir un navigateur de fichier
		private JFileChooser fc = new JFileChooser();
		//Ici on défini le type d'extension que le browser va accepter
		private FileNameExtensionFilter ff = new FileNameExtensionFilter("Extension types : Images", "jpg", "png", "jpeg");
		
		private JPanel backPanel = new JPanel(new FlowLayout());
		private JPanel panelGallery = new JPanel();
		
		private JScrollPane scrollPane = new JScrollPane(backPanel);
		
		private int numImg ;
		private ArrayList<String> photos = new ArrayList<String>();
		
		public GalleryPanel()
		{
			setLayout(new BorderLayout());
			add(menuGalerie, BorderLayout.NORTH);
			ajout.addActionListener(new addGalerie());
					
			//Browser d'image
			fc.setApproveButtonText("Ajouter");
			fc.setAcceptAllFileFilterUsed(false);
			fc.setFileFilter(ff);
			
			backPanel.add(panelGallery);
			panelGallery.setLayout(new GridLayout(0, 3, 7, 7));
			panelGallery.setBorder(new EmptyBorder(3, 0, 0, 0));
			
			display();
			
			scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
			scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			add(scrollPane);
		}
		
		private ArrayList<String> arrayOfPhotos()
		{
			String path = "images/Galerie";
			
			File folder = new File(path);
			File[] listOfFiles = folder.listFiles();
			
			    for (int i = 0; i < listOfFiles.length; i++)
			    {
			    	photos.add(listOfFiles[i].getPath());
			    }
			    
			return photos ;
		}
		
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
			} 
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
		   
		    return numImg ;
		}
		
		public void refresh() 
		{
			panelGallery.removeAll();
			arrayOfPhotos().clear();
			display();
			panelGallery.updateUI();
		}
		
		private BufferedImage createPreview(String path)
		{
			BufferedImage img = null ;
			int newWidth = 0;
			int newHeight = 0;
			
				try 
				{
					img = ImageIO.read(new File(path));
					
					if(img.getWidth() < img.getHeight())
					{
						newWidth = 140;
						newHeight = img.getHeight()*140/img.getWidth();
					}
					else
					{
						newHeight =  140;
						newWidth = img.getWidth()*140/img.getHeight();
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
					e.printStackTrace();
				}
				
			return null ;
				
		}
		
		private void display()
		{
			arrayOfPhotos();
			ButtonCreation buttonPreview ;
			
			for (int i = 0; i < photos.size(); i++) 
			{
				buttonPreview = new ButtonCreation(photos.get(i),new ImageIcon(createPreview(photos.get(i))));
				buttonPreview.addActionListener(new afficheGrandePhoto());
				panelGallery.add(buttonPreview);
			}
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
					refresh();
				}
			}
		}
		
		class afficheGrandePhoto implements ActionListener
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				element = (ButtonCreation) e.getSource();
				fullscreenPanel.refresh();
				cardlayout.show(GalleryApp.this, "FullScreenPanel");
			}
			
		}
	}
	
	class FullScreenPanel extends JPanel
	{
		public FullScreenPanel() 
		{
			setBackground(Color.black);
		}
		
		public void refresh()
		{
			this.removeAll();
			JLabel imageGrande = new JLabel(new ImageIcon(element.getName()));
			this.add(imageGrande, BorderLayout.CENTER);
		}
	}
	
	

}
