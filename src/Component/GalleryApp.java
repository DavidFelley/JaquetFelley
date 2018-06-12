package Component;
/**
 * ------------------------------------------------------------------------------------------------------ <br/>
 * Classe : GalleryApp <br/>
 * ------------------------------------------------------------------------------------------------------ <br/>
 * Auteur : David Felley et Valentin Jaquet <br/>
 * Description de la classe : ..... <br/>
 * ------------------------------------------------------------------------------------------------------ <br/>
 * Remarque : - <br/>
 * ------------------------------------------------------------------------------------------------------ <br/>
 */
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.ImageGraphicAttribute;
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
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Main.MainFrame;

public class GalleryApp extends JPanel
{

	private CardLayout cardlayout = new CardLayout();

	private ButtonCreation element;

	private int id;
	private GalleryPanel galleryPanel = new GalleryPanel();
	private FullScreenPanel fullscreenPanel = new FullScreenPanel(galleryPanel);

	private String imagePath;

	private ContactApp contactApp;
	/**
	 * Constructeur de la Galerie principale
	 */
	public GalleryApp()
	{
		setLayout(cardlayout);
		add(galleryPanel,"GalleryPanel");
		add(fullscreenPanel,"FullScreenPanel");
	}
	/**
	 * Constructeur de la Galerie pour les contacts
	 * @param fromContact
	 * @param contactApp
	 */
	public GalleryApp(boolean fromContact, ContactApp contactApp)
	{
		this.contactApp = contactApp;
		setLayout(cardlayout);
		add(galleryPanel,"GalleryPanel");
		add(fullscreenPanel,"FullScreenPanel");
		rgallery();
	}


	/**
	 * Change les boutons de la Galerie lorsqu'on l'ouvre depuis Contact
	 * Suppression du bouton ajout d'image et ajout du bouton validation de la photo de profil
	 */
	public void rgallery()
	{
		galleryPanel.getMenuGalerie().remove(galleryPanel.getAjout());
		fullscreenPanel.getMenuImage().remove(fullscreenPanel.getTrashButton());
		fullscreenPanel.getMenuImage().add(contactApp.getValidButton(), BorderLayout.EAST);
	}

	public String getImagePath() 
	{
		return imagePath;
	}

	public GalleryPanel getGalleryPanel() {
		return galleryPanel;
	}

	public void setGalleryPanel(GalleryPanel galleryPanel) {
		this.galleryPanel = galleryPanel;
	}

	public int getId() {
		return id;
	}

	public CardLayout getCardlayout() {
		return cardlayout;
	}

	public void setCardlayout(CardLayout cardlayout) {
		this.cardlayout = cardlayout;
	}

	class GalleryPanel extends JPanel
	{
		private Color color = new Color(78,104,141);
		private ButtonCreation ajout = new ButtonCreation("ajout", new ImageIcon("images/Icones/plus.png"));
		private MenuBarre menuGalerie;

		//FileChooser permet d'ouvrir un navigateur de fichier
		private JFileChooser fc = new JFileChooser();
		//Ici on défini le type d'extension que le browser va accepter
		private FileNameExtensionFilter ff = new FileNameExtensionFilter("Extension types : Images", "jpg", "png", "jpeg");

		private JPanel backPanel = new JPanel();
		private JPanel panelGallery = new JPanel();

		private JScrollPane scrollPane = new JScrollPane(backPanel);

		private int numImg ;

		private ArrayList<String> photos = new ArrayList<String>();

		/**
		 * Constructeur du GalleryPanel affichant les miniatures des photos
		 */
		public GalleryPanel()
		{
			setLayout(new BorderLayout());

			menuGalerie = new MenuBarre("GALERIE",ajout, color);

			add(menuGalerie, BorderLayout.NORTH);
			ajout.addActionListener(new addGalerie());

			//Browser d'image
			fc.setApproveButtonText("Ajouter");
			fc.setAcceptAllFileFilterUsed(false);
			fc.setFileFilter(ff);

			backPanel.setLayout(new FlowLayout());
			backPanel.add(panelGallery);
			panelGallery.setLayout(new GridLayout(0, 3, 7, 7));
			panelGallery.setBorder(new EmptyBorder(2, 2, 2, 2));

			display();

			scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
			scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			add(scrollPane);
		}

		/**
		 * Méthode de récupération des chemins des photos dans le fichier Galerie
		 * @return
		 */
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

		/**
		 * Sauvegarde les images dans le dossier galerie et les renomme en Photo+un numero
		 * @param img
		 */
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

		/**
		 * Récupère l'extension de l'image
		 * @param fichier
		 * @return
		 */
		private String getExtension(File fichier)
		{
			String imgExtension = fichier.getName();

			String fileName = fichier.getName();
			if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
				return imgExtension.substring(imgExtension.lastIndexOf(".") + 1);
			else
				return "";
		}

		/**
		 * Lis le numéro stocké dans le fichier txt pour savoir a quel numero de photo nous sommes
		 * @param path
		 * @return
		 */
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

		/**
		 * Met à jour la Galerie 
		 */
		public void update() 
		{
			panelGallery.removeAll();
			arrayOfPhotos().clear();
			display();
			panelGallery.updateUI();
		}

		/**
		 * Crée les miniatures des photos
		 * @param path
		 * @param newFormat
		 * @return
		 */
		public BufferedImage createPreview(String path, int newFormat)
		{
			BufferedImage img = null ;
			int newWidth = newFormat;
			int newHeight = newFormat;

			try 
			{
				img = ImageIO.read(new File(path));

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

				resizedImage = resizedImage.getSubimage(0, 0, newFormat, newFormat);

				return resizedImage ;				
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}

			return null ;

		}

		/**
		 * Insère les photos dans les boutons et ajoute l'action listener pour les afficher en grand
		 */
		private void display()
		{
			arrayOfPhotos();
			ButtonCreation buttonPreview ;

			for (int i = 0; i < photos.size(); i++) 
			{
				buttonPreview = new ButtonCreation(new ImageIcon(createPreview(photos.get(i),140)),i);
				buttonPreview.addActionListener(new afficheGrandePhoto());
				panelGallery.add(buttonPreview);
			}
		}



		/**
		 * AL ouvrant le filechooser pour l'ajout d'images
		 * @author david
		 *
		 */
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
					update();
				}
			}
		}

		/**
		 * AL affichant les photos en grand
		 * @author david
		 *
		 */
		class afficheGrandePhoto implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				element = (ButtonCreation) e.getSource();
				id = element.getId();
				fullscreenPanel.refresh();
				cardlayout.show(GalleryApp.this, "FullScreenPanel");
			}
		}

		public ArrayList<String> getPhotos() {
			return photos;
		}

		public void setPhotos(ArrayList<String> photos) {
			this.photos = photos;
		}

		public ButtonCreation getAjout()
		{
			return ajout;
		}

		public void setAjout(ButtonCreation ajout)
		{
			this.ajout = ajout;
		}

		public MenuBarre getMenuGalerie()
		{
			return menuGalerie;
		}

		public void setMenuGalerie(MenuBarre menuGalerie)
		{
			this.menuGalerie = menuGalerie;
		}
	}

	class FullScreenPanel extends JPanel
	{
		private GalleryPanel galleryPanel = new GalleryPanel();

		private ButtonCreation trashButton = new ButtonCreation("trash", new ImageIcon("images/Icones/trash.png"));
		private ButtonCreation backButton = new ButtonCreation("back", new ImageIcon("images/Icones/retour.png"));
		private ButtonCreation nextPhoto = new ButtonCreation();
		private ButtonCreation backPhoto = new ButtonCreation();
		private MenuBarre menuImage;

		private JLabel imageGrande = new JLabel() ;

		/**
		 * Panel contenant l'image en grand
		 * @param galleryPanel
		 */
		public FullScreenPanel(GalleryPanel galleryPanel) 
		{
			this.galleryPanel = galleryPanel;

			setBackground(Color.black);
			setLayout(new BorderLayout());
			setBorder(new EmptyBorder(0, 0, 0, 0));

			menuImage = new MenuBarre("", backButton,trashButton, Color.BLACK);

			add(menuImage, BorderLayout.NORTH);

			backButton.addActionListener(new backGallery());
			trashButton.addActionListener(new deleteGallery());

			nextPhoto.addActionListener(new nextPhoto());
			backPhoto.addActionListener(new backPhoto());

		}

		/**
		 * Rafraichi le FullScreenPanel
		 */
		public void refresh()
		{
			imageGrande.setIcon(new ImageIcon(resizePhoto(galleryPanel.photos.get(id))));
			imageGrande.setHorizontalAlignment(JLabel.CENTER);
			imageGrande.setLayout(new BorderLayout());
			imageGrande.add(nextPhoto, BorderLayout.EAST);
			imageGrande.add(backPhoto, BorderLayout.WEST);
			this.add(imageGrande);
		}

		/**
		 * Resize l'image pour l'adapter a la taille de l'ecran
		 * @param path
		 * @return
		 */
		private BufferedImage resizePhoto(String path)
		{
			BufferedImage img = null ;
			int newWidth = 0;
			int newHeight = 0;

			try 
			{
				img = ImageIO.read(new File(path));
				double ratio = img.getHeight()/img.getWidth();

				if(ratio <= 1.51)
				{
					newWidth = 450;
					newHeight = img.getHeight()*newWidth/img.getWidth();
				}
				else
				{
					newHeight = 680;
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

				return resizedImage ;				
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}

			return null ;
		}

		/**
		 * Raffiche la galerie avec les miniatures
		 * @author david
		 *
		 */
		class backGallery implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				cardlayout.show(GalleryApp.this,"GalleryPanel");
			}
		}

		/**
		 * Supprime la photo de la Galerie
		 * @author david
		 *
		 */
		class deleteGallery implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				File file = new File(galleryPanel.photos.get(id));

				file.delete();

				galleryPanel.update();

				cardlayout.show(GalleryApp.this, "GalleryPanel");
			}
		}

		/**
		 * Affiche la photo suivante en grand
		 * @author david
		 *
		 */
		class nextPhoto implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(id == galleryPanel.photos.size()-1)
					id = 0;
				else
					id++;

				fullscreenPanel.refresh();
			}
		}

		/**
		 * Affiche la photo précédente en grand
		 * @author david
		 *
		 */
		class backPhoto implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(id == 0)
					id = galleryPanel.photos.size()-1;
				else
					id--;

				fullscreenPanel.refresh();
			}
		}

		public MenuBarre getMenuImage()
		{
			return menuImage;
		}


		public ButtonCreation getTrashButton()
		{
			return trashButton;
		}

		public void setTrashButton(ButtonCreation trashButton)
		{
			this.trashButton = trashButton;
		}
	}
}
