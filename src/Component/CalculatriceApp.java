/*
*Author David Felley
*Created on 3 juin 2018
*For JaquetFelley
*/

package Component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CalculatriceApp extends JPanel
{	
	private Calculatrice calculette = new Calculatrice();
	
	public CalculatriceApp() 
	{
		setLayout(new BorderLayout());
		add(calculette);
	}
	
	class Calculatrice extends JPanel
	{
		private ButtonCreation ajout = new ButtonCreation("ajout", new ImageIcon("images/Icones/plus.png")); // A VIRER
		private MenuBarre menuCalculatrice = new MenuBarre("CALCULATRICE",ajout, Color.BLACK); // CREER LE CONSTRUCTEUR SANS BOUTONS
		
		private String[] tabLabelButton = {"1","2","3","/","4","5","6","*","7","8","9","-",".","0","C","+","="};
		private JButton [] tabButton = new JButton[tabLabelButton.length];
		private JLabel ecran = new JLabel() ;
		private double chiffre ;
		private boolean clicOperator = false, update = false ;
		private String operateur = "" ;
		private JPanel panCalculatrice = new JPanel();
		private JPanel panEcran = new JPanel();
		private JPanel panelButton = new JPanel(new GridBagLayout());
		private GridBagConstraints c = new GridBagConstraints();
		
		public Calculatrice()
		{
			setLayout(new BorderLayout());
			add(panCalculatrice, BorderLayout.NORTH);
			panCalculatrice.setLayout(new BoxLayout(panCalculatrice, BoxLayout.Y_AXIS));
			panCalculatrice.add(menuCalculatrice);
			
			ecran = new JLabel("0");
			ecran.setHorizontalAlignment(JLabel.RIGHT);
			ecran.setFont(new Font(null, Font.PLAIN,20));
			ecran.setPreferredSize(new Dimension(450,100));
			panCalculatrice.add(panEcran);
			panEcran.add(ecran);
			panEcran.setBorder(BorderFactory.createLineBorder(Color.pink));
			panEcran.setBackground(Color.WHITE);
	
			
			//Paramètre du panel de bouton
			c.insets = new Insets(5,5,5,5);
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 5;
			c.weighty = 4;
			c.ipady = c.anchor = GridBagConstraints.CENTER;
			
			generateButton();
//			displayButton();
			panCalculatrice.add(panelButton);
			
			c.gridx = 0;
			c.gridy = 0;
			
			panelButton.add(tabButton[0]);
			
			c.gridx = 1;
			c.gridy = 0;
			
			panelButton.add(tabButton[1]);
			
			c.gridx = 2;
			c.gridy = 0;
			
			panelButton.add(tabButton[2]);
			
		
		}
		
		private void generateButton()
		{
			for (int i = 0; i < tabButton.length; i++)
			{
				switch (i) {
				case 3:
					
					tabButton[i] = new JButton(tabLabelButton[i]);
					tabButton[i].addActionListener(new DivListener());
					break;
					
				case 7:
					tabButton[i] = new JButton(tabLabelButton[i]);
					tabButton[i].addActionListener(new MultiListener());
					break;
					
				case 11:
					tabButton[i] = new JButton(tabLabelButton[i]);
					tabButton[i].addActionListener(new MoinsListener());
					break;
					
				case 14:
					tabButton[i] = new JButton(tabLabelButton[i]);
					tabButton[i].addActionListener(new ResetListener());
					break;
					
				case 15:
					tabButton[i] = new JButton(tabLabelButton[i]);
					tabButton[i].addActionListener(new PlusListener());
					break;
					
				case 16:
					tabButton[i] = new JButton(tabLabelButton[i]);
					tabButton[i].addActionListener(new EgalListener());
					break;
					
				default:
					
					tabButton[i] = new JButton(tabLabelButton[i]);
					tabButton[i].addActionListener(new ChiffreListener());
					
					break;
				}
				
			}
		}
		
//		private void displayButton()
//		{
//			int numBtn = 0;
//			
//			for (int i = 0; i < 3; i++)
//			{
//				for (int j = 0; j < 4 ; j++)
//				{
//					c.gridx = i;
//					c.gridy = j;
//					
//					panelButton.add(tabButton[numBtn]);
//					numBtn++;
//					
//				}
//			}
//		}

		  //Méthode permettant d'effectuer un calcul selon l'opérateur sélectionné

		  private void calcul(){

		    if(operateur.equals("+")){

		      chiffre = chiffre + 

		            Double.valueOf(ecran.getText()).doubleValue();

		      ecran.setText(String.valueOf(chiffre));

		    }

		    if(operateur.equals("-")){

		      chiffre = chiffre - 

		            Double.valueOf(ecran.getText()).doubleValue();

		      ecran.setText(String.valueOf(chiffre));

		    }          

		    if(operateur.equals("*")){

		      chiffre = chiffre * 

		            Double.valueOf(ecran.getText()).doubleValue();

		      ecran.setText(String.valueOf(chiffre));

		    }     

		    if(operateur.equals("/")){

		      try{

		        chiffre = chiffre / 

		              Double.valueOf(ecran.getText()).doubleValue();

		        ecran.setText(String.valueOf(chiffre));

		      } catch(ArithmeticException e) {

		        ecran.setText("0");

		      }

		    }

		  }


		  //Listener utilisé pour les chiffres

		  //Permet de stocker les chiffres et de les afficher

		  class ChiffreListener implements ActionListener {

		    public void actionPerformed(ActionEvent e){

		      //On affiche le chiffre additionnel dans le label

		      String str = ((JButton)e.getSource()).getText();

		      if(update){

		        update = false;

		      }

		      else{

		        if(!ecran.getText().equals("0"))

		          str = ecran.getText() + str;

		      }

		      ecran.setText(str);

		    }

		  }


		  //Listener affecté au bouton =

		  class EgalListener implements ActionListener {

		    public void actionPerformed(ActionEvent arg0){

		      calcul();

		      update = true;

		      clicOperator = false;

		    }

		  }


		  //Listener affecté au bouton +

		  class PlusListener implements ActionListener {

		    public void actionPerformed(ActionEvent arg0){

		      if(clicOperator){

		        calcul();

		        ecran.setText(String.valueOf(chiffre));

		      }

		      else{

		        chiffre = Double.valueOf(ecran.getText()).doubleValue();

		        clicOperator = true;

		      }

		      operateur = "+";

		      update = true;

		    }

		  }


		  //Listener affecté au bouton -

		  class MoinsListener implements ActionListener {

		    public void actionPerformed(ActionEvent arg0){

		      if(clicOperator){

		        calcul();

		        ecran.setText(String.valueOf(chiffre));

		      }

		      else{

		        chiffre = Double.valueOf(ecran.getText()).doubleValue();

		        clicOperator = true;

		      }

		      operateur = "-";

		      update = true;

		    }

		  }


		  //Listener affecté au bouton *

		  class MultiListener implements ActionListener {

		    public void actionPerformed(ActionEvent arg0){

		      if(clicOperator){

		        calcul();

		        ecran.setText(String.valueOf(chiffre));

		      }

		      else{

		        chiffre = Double.valueOf(ecran.getText()).doubleValue();

		        clicOperator = true;

		      }

		      operateur = "*";

		      update = true;

		    }

		  }


		  //Listener affecté au bouton /

		  class DivListener implements ActionListener {

		    public void actionPerformed(ActionEvent arg0){

		      if(clicOperator){

		        calcul();

		        ecran.setText(String.valueOf(chiffre));

		      }

		      else{

		        chiffre = Double.valueOf(ecran.getText()).doubleValue();

		        clicOperator = true;

		      }

		      operateur = "/";

		      update = true;

		    }

		  }
		  
		  //Listener affecté au bouton de remise à zéro

		  class ResetListener implements ActionListener {

		    public void actionPerformed(ActionEvent arg0){

		      clicOperator = false;

		      update = true;

		      chiffre = 0;

		      operateur = "";

		      ecran.setText("");

		    }

		  }      
	}
	

}
