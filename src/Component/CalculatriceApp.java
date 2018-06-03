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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CalculatriceApp extends JPanel
{	
	private Calculatrice calculette = new Calculatrice();
	
	public CalculatriceApp() 
	{
		add(calculette);
	}
	
	class Calculatrice extends JPanel
	{
		private String[] tabLabelButton = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".", "=", "C", "+", "-", "*", "/"};
		private JButton[] tabButton = new JButton[tabLabelButton.length];
		private JLabel ecran = new JLabel() ;
		private double chiffre1 ;
		private boolean clicOperator = false, update = false ;
		private String operateur = "" ;
		
		public Calculatrice()
		{
			 initComposant();

		     //On ajoute le conteneur

		    //this.setContentPane(container);

		    this.setVisible(true);
			
		}
		
		private void initComposant(){

		    //On définit la police d'écriture à utiliser

		    Font police = new Font("Arial", Font.BOLD, 20);

		    ecran = new JLabel("0");

		    ecran.setFont(police);

		    //On aligne les informations à droite dans le JLabel

		    ecran.setHorizontalAlignment(JLabel.RIGHT);

		    ecran.setPreferredSize(new Dimension(220, 20));

		    JPanel operateur = new JPanel();      

		    operateur.setPreferredSize(new Dimension(55, 225));

		    JPanel chiffre = new JPanel();

		    chiffre.setPreferredSize(new Dimension(165, 225));

		    JPanel panEcran = new JPanel();

		    panEcran.setPreferredSize(new Dimension(220, 30));


		    //On parcourt le tableau initialisé

		    //afin de créer nos boutons

		    for(int i = 0; i < tabLabelButton.length; i++){

		    	tabButton[i] = new JButton(tabLabelButton[i]);

//		      tabButton[i].setPreferredSize(dim);

		      switch(i){

		        //Pour chaque élément situé à la fin du tableau

		        //et qui n'est pas un chiffre

		        //on définit le comportement à avoir grâce à un listener

		        case 11 :

		        	tabButton[i].addActionListener(new EgalListener());

		          chiffre.add(tabButton[i]);

		          break;

		        case 12 :

		        	tabButton[i].setForeground(Color.red);

		        	tabButton[i].addActionListener(new ResetListener());

		          operateur.add(tabButton[i]);

		          break;

		        case 13 :

		        	tabButton[i].addActionListener(new PlusListener());

		       //   tabButton[i].setPreferredSize(dim2);

		          operateur.add(tabButton[i]);

		          break;

		        case 14 :

		        	tabButton[i].addActionListener(new MoinsListener());

		         // tabButton[i].setPreferredSize(dim2);

		          operateur.add(tabButton[i]);

		          break;    

		        case 15 :   

		        tabButton[i].addActionListener(new MultiListener());

		       // tabButton[i].setPreferredSize(dim2);

		          operateur.add(tabButton[i]);

		          break;

		        case 16 :

		          tabButton[i].addActionListener(new DivListener());

		         // tabButton[i].setPreferredSize(dim2);

		          operateur.add(tabButton[i]);

		          break;

		        default :

		          //Par défaut, ce sont les premiers éléments du tableau

		          //donc des chiffres, on affecte alors le bon listener

		          chiffre.add(tabButton[i]);

		          tabButton[i].addActionListener(new ChiffreListener());

		          break;

		      }

		    }

		    panEcran.add(ecran);

		    panEcran.setBorder(BorderFactory.createLineBorder(Color.black));

		    add(panEcran, BorderLayout.NORTH);

		    add(chiffre, BorderLayout.CENTER);

		    add(operateur, BorderLayout.EAST);

		  }


		  //Méthode permettant d'effectuer un calcul selon l'opérateur sélectionné

		  private void calcul(){

		    if(operateur.equals("+")){

		      chiffre1 = chiffre1 + 

		            Double.valueOf(ecran.getText()).doubleValue();

		      ecran.setText(String.valueOf(chiffre1));

		    }

		    if(operateur.equals("-")){

		      chiffre1 = chiffre1 - 

		            Double.valueOf(ecran.getText()).doubleValue();

		      ecran.setText(String.valueOf(chiffre1));

		    }          

		    if(operateur.equals("*")){

		      chiffre1 = chiffre1 * 

		            Double.valueOf(ecran.getText()).doubleValue();

		      ecran.setText(String.valueOf(chiffre1));

		    }     

		    if(operateur.equals("/")){

		      try{

		        chiffre1 = chiffre1 / 

		              Double.valueOf(ecran.getText()).doubleValue();

		        ecran.setText(String.valueOf(chiffre1));

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

		        ecran.setText(String.valueOf(chiffre1));

		      }

		      else{

		        chiffre1 = Double.valueOf(ecran.getText()).doubleValue();

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

		        ecran.setText(String.valueOf(chiffre1));

		      }

		      else{

		        chiffre1 = Double.valueOf(ecran.getText()).doubleValue();

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

		        ecran.setText(String.valueOf(chiffre1));

		      }

		      else{

		        chiffre1 = Double.valueOf(ecran.getText()).doubleValue();

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

		        ecran.setText(String.valueOf(chiffre1));

		      }

		      else{

		        chiffre1 = Double.valueOf(ecran.getText()).doubleValue();

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

		      chiffre1 = 0;

		      operateur = "";

		      ecran.setText("");

		    }

		  }      
	}
	

}
