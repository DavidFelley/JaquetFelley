/*
*Author David Felley
*Created on 3 juin 2018
*For JaquetFelley
*/

package Component;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

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
		private String[] tabLabelButton = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".", "=", "C", "+", "-", "*", "/"};
		private JButton[] tabButton = new JButton[tabLabelButton.length];
		private JLabel ecran = new JLabel() ;
		private double chiffre ;
		private boolean clicOperator = false, update = false ;
		private String operateur = "" ;
		private JPanel flowPanel =  new JPanel();
		private JPanel backPanel = new JPanel();
		private JPanel chiffres = new JPanel();
		private JPanel panEcran = new JPanel();
		
		public Calculatrice()
		{
			setLayout(new BorderLayout());
			add(menuCalculatrice, BorderLayout.NORTH);
			
			backPanel.setLayout(new BorderLayout());
			
			ecran = new JLabel("0");
			ecran.setHorizontalAlignment(JLabel.RIGHT);
			ecran.setFont(new Font(null, Font.PLAIN,20));
		
			panEcran.setLayout(new BorderLayout());
			panEcran.add(ecran);
			panEcran.setBorder(BorderFactory.createLineBorder(Color.pink));
			panEcran.setPreferredSize(new Dimension(450,200));
			panEcran.setBackground(Color.WHITE);
			backPanel.add(panEcran, BorderLayout.NORTH);
			
			chiffres.setLayout(new GridLayout(4, 4));
			flowPanel.setLayout(new FlowLayout());
			flowPanel.setBackground(Color.CYAN);
			flowPanel.add(chiffres);
			backPanel.add(flowPanel, BorderLayout.CENTER);
			add(backPanel);
		}

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
