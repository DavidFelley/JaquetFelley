package Component;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.MainFrame;

public class GalleryPanel extends JPanel{
	
	private MainFrame mainframe;
	private CardLayout clGallery = new CardLayout();
	private JPanel panelGallery = new JPanel(clGallery);

	private JLabel testLabel = new JLabel ("test Gallery");	
	
	public GalleryPanel () {
		
		setLayout(new BorderLayout());
		panelGallery.add(testLabel);
		add(panelGallery);
		
	}
	
}
