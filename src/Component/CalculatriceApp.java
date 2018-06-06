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
		private JPanel operateurs = new JPanel();
		private JPanel chiffres = new JPanel();
		private JPanel panEcran = new JPanel();
		
		public Calculatrice()
		{
			setLayout(new BorderLayout());
			add(menuCalculatrice, BorderLayout.NORTH);
			
			backPanel.setLayout(new BorderLayout());
			
			ecran = new JLabel("0");
			ecran.setHorizontalAlignment(JLabel.RIGHT);
			panEcran.setLayout(new BorderLayout());
			panEcran.add(ecran);
			panEcran.setBorder(BorderFactory.createLineBorder(Color.pink));
			backPanel.add(panEcran, BorderLayout.NORTH);
			
			chiffres.setLayout(new GridLayout(4, 4));
			flowPanel.setLayout(new FlowLayout());
			flowPanel.setBackground(Color.CYAN);
			flowPanel.add(chiffres);
			backPanel.add(flowPanel, BorderLayout.CENTER);
			add(backPanel);
			initButton();
		}
		
		private void initButton()
		{
			 for(int i = 0; i < tabLabelButton.length; i++){
				 
				 tabButton[i] = new JButton(tabLabelButton[i]);

			      switch(i){

			        // Ici on instancie les différents boutons qui ne sont pas des chiffres et on leur attribue un listener

			        case 11 :

			        	tabButton[i].addActionListener(new EgalListener());

			          backPanel.add(tabButton[i], BorderLayout.SOUTH);

			          break;

			        case 12 :

			        	tabButton[i].setForeground(Color.red);

			        	tabButton[i].addActionListener(new ResetListener());

			        	chiffres.add(tabButton[i]);

			          break;

			        case 13 :

			        	tabButton[i].addActionListener(new PlusListener());
			        	chiffres.add(tabButton[i]);

			          break;

			        case 14 :

			        	tabButton[i].addActionListener(new MoinsListener());
			        	chiffres.add(tabButton[i]);

			          break;    

			        case 15 :   

			        tabButton[i].addActionListener(new MultiListener());
			        chiffres.add(tabButton[i]);

			          break;

			        case 16 :

			          tabButton[i].addActionListener(new DivListener());
			          chiffres.add(tabButton[i]);

			          break;

			        default :

			          //Par défaut, ce sont les premiers éléments du tableau

			          //donc des chiffres, on affecte alors le bon listener

			          chiffres.add(tabButton[i]);

			          tabButton[i].addActionListener(new ChiffreListener());

			          break;

			      }

			    }
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
