package Component;
/**
 * ------------------------------------------------------------------------------------------------------ <br/>
 * Classe : CalculatriceApp <br/>
 * ------------------------------------------------------------------------------------------------------ <br/>
 * Auteur : David Felley et Valentin Jaquet <br/>
 * Description de la classe : Cette classe gère l'application calculatrice<br/>
 * ------------------------------------------------------------------------------------------------------ <br/>
 * Remarque : Source du code de base de la calculatrice 
 * 			  https://openclassrooms.com/courses/apprenez-a-programmer-en-java/tp-une-calculatrice <br/>
 * ------------------------------------------------------------------------------------------------------ <br/>
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CalculatriceApp extends JPanel
{	
	private Calculatrice calculette = new Calculatrice();

	/**
	 * Constructeur de l'application Calculatrice
	 */
	public CalculatriceApp() 
	{
		setLayout(new BorderLayout());
		add(calculette);
	}

	public Calculatrice getCalculette()
	{
		return calculette;
	}

	public class Calculatrice extends JPanel
	{
		private Color color = new Color(78,104,141);
		private MenuBarre menuCalculatrice = new MenuBarre("CALCULATRICE", color); // CREER LE CONSTRUCTEUR SANS BOUTONS

		private String[] tabLabelButton = {"1","2","3","/","4","5","6","*","7","8","9","-",".","0","C","+","="};
		private ButtonCreation [] tabButton = new ButtonCreation[tabLabelButton.length];
		private JLabel ecran = new JLabel() ;
		private double chiffre ;
		private boolean clicOperator = false, update = false, chiffre2 = false ;
		private String operateur = "" ;
		private JPanel panCalculatrice = new JPanel();
		private JPanel panEcran = new JPanel();
		private JPanel panelButton = new JPanel(new GridBagLayout());
		private GridBagConstraints c = new GridBagConstraints();

		/**
		 * Constructeur de la calculatrice
		 */
		public Calculatrice()
		{
			setLayout(new BorderLayout());
			setBackground(Color.WHITE);
			add(panCalculatrice, BorderLayout.NORTH);
			panCalculatrice.setLayout(new BoxLayout(panCalculatrice, BoxLayout.Y_AXIS));
			panCalculatrice.add(menuCalculatrice);

			ecran = new JLabel("0");
			ecran.setHorizontalAlignment(JLabel.RIGHT);
			ecran.setFont(new Font(null, Font.PLAIN,40));
			ecran.setPreferredSize(new Dimension(450,245));
			panCalculatrice.add(panEcran);
			panEcran.add(ecran);
			panEcran.setBackground(new Color(250,250,250));

			//Paramètre du panel de bouton
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 4;
			c.weighty = 5;
			c.ipady = c.anchor = GridBagConstraints.CENTER;

			generateButton();
			displayButton();

			c.gridx = 0 ;
			c.gridy = 4 ;
			c.gridwidth = 4 ;
			panelButton.add(tabButton[16],c);
			panelButton.setBackground(Color.WHITE);
			panelButton.setBorder(new EmptyBorder(0,0,0,0));
			panCalculatrice.add(panelButton);
		}

		/**
		 * Constructeur des boutons
		 */
		private void generateButton()
		{
			for (int i = 0; i < tabButton.length; i++)
			{
				switch (i)
				{
				case 3:
					tabButton[i] = new ButtonCreation(tabLabelButton[i],106,75);
					tabButton[i].addActionListener(new DivListener());
					tabButton[i].setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));
					break;

				case 7:
					tabButton[i] = new ButtonCreation(tabLabelButton[i],106,75);
					tabButton[i].addActionListener(new MultiListener());
					tabButton[i].setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));
					break;

				case 11:
					tabButton[i] = new ButtonCreation(tabLabelButton[i],106,75);
					tabButton[i].addActionListener(new MoinsListener());
					tabButton[i].setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));
					break;

				case 14:
					tabButton[i] = new ButtonCreation(tabLabelButton[i],106,75);
					tabButton[i].addActionListener(new ResetListener());
					tabButton[i].setForeground(Color.RED);
					break;

				case 15:
					tabButton[i] = new ButtonCreation(tabLabelButton[i],106,75);
					tabButton[i].addActionListener(new PlusListener());
					tabButton[i].setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));
					break;

				case 16:
					tabButton[i] = new ButtonCreation(tabLabelButton[i],106,75);
					tabButton[i].setBackground(color);
					tabButton[i].setForeground(Color.WHITE);
					tabButton[i].addActionListener(new EgalListener());
					break;

				default:	
					tabButton[i] = new ButtonCreation(tabLabelButton[i],106,75);
					tabButton[i].addActionListener(new ChiffreListener());					
					break;
				}	
			}
		}

		/**
		 * Ajout des boutons dans le panelButton
		 */
		private void displayButton()
		{
			int numBtn = 0;

			for (int i = 0; i < 4 ; i++)
			{
				for (int j = 0; j < 4 ; j++)
				{
					c.gridx = j;
					c.gridy = i;

					panelButton.add(tabButton[numBtn],c);
					numBtn++;	
				}
			}
		}

		/**
		 * Méthode de calcul en fonction de l'opérateur
		 */
		public void calcul()
		{
			if(operateur.equals("+"))
			{
				chiffre = chiffre + Double.valueOf(ecran.getText()).doubleValue();
				ecran.setText(String.valueOf(chiffre));
			}
			if(operateur.equals("-"))
			{
				chiffre = chiffre - Double.valueOf(ecran.getText()).doubleValue();
				ecran.setText(String.valueOf(chiffre));
			}          

			if(operateur.equals("*"))
			{
				chiffre = chiffre * Double.valueOf(ecran.getText()).doubleValue();
				ecran.setText(String.valueOf(chiffre));
			}     
			if(operateur.equals("/"))
			{
				try
				{
					if(Double.valueOf(ecran.getText()).doubleValue() == 0)
						ecran.setText("Not a number");
					else
					{
						chiffre = chiffre / Double.valueOf(ecran.getText()).doubleValue();
						ecran.setText(String.valueOf(chiffre));
					}
				} 
				catch(ArithmeticException e)
				{
					ecran.setText("0");
				}
			}
		}

		/**
		 * AL pour les chiffres et le point
		 *
		 */
		class ChiffreListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				//On affiche le chiffre additionnel dans le label
				
				String str = ((JButton)e.getSource()).getText();
				
				if(update)
				{
					update = false;
					chiffre2 = true;
				}
				else if(str.equals(".") && ecran.getText().contains("."))  
					str = ecran.getText();
				else if(!ecran.getText().equals("0"))
					str = ecran.getText() + str;

				ecran.setText(str);
			}
		}


		/**
		 * AL pour le bouton egal
		 *
		 */
		class EgalListener implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if (chiffre2)
				{
					calcul();
					update = false;
					clicOperator = false;
					chiffre2 = false;
				}
			}
		}

		/**
		 * AL pour le bouton plus
		 *
		 */
		class PlusListener implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(!clicOperator)
				{
					chiffre = Double.valueOf(ecran.getText()).doubleValue();
					clicOperator = true;
				}
				operateur = "+";
				update = true;
			}
		}


		/**
		 * AL pour le bouton moins
		 *
		 */
		class MoinsListener implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(!clicOperator)
				{
					chiffre = Double.valueOf(ecran.getText()).doubleValue();
					clicOperator = true;
				}
				operateur = "-";
				update = true;
			}
		}

		/**
		 * AL pour le bouton multiplier
		 *
		 */
		class MultiListener implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(!clicOperator)
				{
					chiffre = Double.valueOf(ecran.getText()).doubleValue();
					clicOperator = true;
				}
				operateur = "*";
				update = true;
			}
		}

		/**
		 * AL pour le bouton diviser
		 *
		 */
		class DivListener implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(!clicOperator)
				{
					chiffre = Double.valueOf(ecran.getText()).doubleValue();
					clicOperator = true;
				}
				operateur = "/";
				update = true;
			}
		}

		/**
		 * AL pour le bouton de reset
		 *
		 */
		class ResetListener implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				clicOperator = false;
				update = false;
				chiffre = 0;
				operateur = "";
				ecran.setText("0");
			}
		}

		// Getter nécessaire aux test JUnit
		public void setEcran(JLabel ecran)
		{
			this.ecran = ecran;
		}

		public JLabel getEcran()
		{
			return ecran;
		}

		public ButtonCreation[] getTabButton()
		{
			return tabButton;
		}

		public String getOperateur()
		{
			return operateur;
		}

		public void setChiffre(double chiffre)
		{
			this.chiffre = chiffre;
		}

		public double getChiffre()
		{
			return chiffre;
		}
	}


}
