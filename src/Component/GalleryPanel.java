package Component;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.MainFrame;

public class GalleryPanel extends JPanel{
	
	private MainFrame mainframe;
	private CardLayout clGallery = new CardLayout();
	private JPanel panelGallery = new JPanel(clGallery);

	private ButtonCreation retour = new ButtonCreation("return",new ImageIcon("images/Icones/retour.png"));
	private ButtonCreation ajout = new ButtonCreation("ajout", new ImageIcon("images/Icones/validate.png"));
	MenuBarre menuGalerie = new MenuBarre(retour, ajout, "GALERIE");
	
	public GalleryPanel () {
		
		setLayout(new BorderLayout());
		add(panelGallery);
		add(menuGalerie, BorderLayout.NORTH);
		
		
		
		
	}
	
}
