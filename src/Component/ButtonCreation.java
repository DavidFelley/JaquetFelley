
package Component;
/**
* ------------------------------------------------------------------------------------------------------ <br/>
* Classe : ButtonCreation <br/>
* ------------------------------------------------------------------------------------------------------ <br/>
* Auteurs : David Felley et Valentin Jaquet <br/>
* Description de la classe : Cette classe gère la création des boutons<br/>
* ------------------------------------------------------------------------------------------------------ <br/>
* Remarque : - <br/>
* ------------------------------------------------------------------------------------------------------ <br/>
*/
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class ButtonCreation extends JButton {
	
	private String name;
	private ImageIcon image;
	private int id;
	private int width;
	private int height;

	/**
	 * Constructeur du bouton sans arguments
	 */
	public ButtonCreation()
	{
		setOpaque(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setPreferredSize(new Dimension(200,680));
	}
		
	/**
	 * Constructeur du bouton avec nom et image
	 */
	public ButtonCreation(String name, ImageIcon image) {
		super(image);
		this.name = name;
		setName(name);
		setFocusable(false);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentAreaFilled(false);
	}
	
	/**
	 * Constructeur du bouton avec image et id
	 * @param image
	 * @param id
	 */
	public ButtonCreation (ImageIcon image, int id) {
		super(image);
		this.image = image ;
		this.id = id;
		
		setFocusable(false);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentAreaFilled(false);
	
	}
	
	/**
	 * Constructeur du bouton avec largeur et hauteur
	 * @param width
	 * @param height
	 */
	public ButtonCreation(int width, int height) {		
		setMaximumSize(new Dimension(width, height));
		setMinimumSize(new Dimension(width, height));
		setPreferredSize(new Dimension(width, height));
		setFocusable(false);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentAreaFilled(false);
	}
	
	/**
	 * Constructeur du bouton avec nom, largeur et hauteur
	 * @param name
	 * @param width
	 * @param height
	 */
	public ButtonCreation(String name,int width, int height)
	{		
		setText(name);
		

		setMaximumSize(new Dimension(width, height));
		setMinimumSize(new Dimension(width, height));
		setPreferredSize(new Dimension(width, height));
		setFocusable(false);
		setContentAreaFilled(true);
		setBackground(Color.WHITE);
		
		setFont(new Font("Arial", Font.ROMAN_BASELINE, 25));
		Color color = new Color(78,104,141);
		setForeground(color);
		setBorder(BorderFactory.createMatteBorder(1, 0, 0, 1, Color.LIGHT_GRAY));
	}
			
	// Getters & Setters
	public int getId()
	{
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
