/*
*Author David Felley
*Created on 11 juin 2018
*For JaquetFelley
*/

package Junit;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JLabel;

import org.junit.jupiter.api.Test;

import Component.CalculatriceApp;

class TestCalculatrice
{

	@Test
	void testCalculMethod()
	{
		CalculatriceApp calculatrice = new CalculatriceApp();
		
		//Test de l'addition
		JLabel ecranTest = new JLabel("10");
		calculatrice.getCalculette().setEcran(ecranTest);
		
//		//On simule un click sur le bouton plus
//		calculatrice.getCalculette().getTabButton()[15].doClick();
//		//V�rification de l'affectation de la variable Chiffre
//		if(calculatrice.getCalculette().getChiffre() != 10)
//			fail("Le bouton + n'a pas r�cup�r� la bonne valeur !");
//		//V�rification de l'affectation de la variable operateur
//		if(!calculatrice.getCalculette().getOperateur().equals("+"))
//			fail("Le bouton + n'a pas affect� le bon op�rateur !");
//		//On v�rifie que la m�thode addition fonctionne correctement
//		ecranTest.setText("13");
//		calculatrice.getCalculette().setEcran(ecranTest);
//		calculatrice.getCalculette().calcul();
//		if(!calculatrice.getCalculette().getEcran().getText().equals("23.0"))
//			fail("La m�thode calcul n'a pas r�alis� l'addition correctement");
		
//		//Test de la soustraction
//		ecranTest = new JLabel("10");
//		calculatrice.getCalculette().setEcran(ecranTest);
//		//On simule un click sur le bouton moins
//		calculatrice.getCalculette().getTabButton()[11].doClick();
//		//V�rification de l'affectation de la variable Chiffre
//		if(calculatrice.getCalculette().getChiffre() != 10)
//			fail("Le bouton - n'a pas r�cup�r� la bonne valeur !");
//		//V�rification de l'affectation de la variable operateur
//		if(!calculatrice.getCalculette().getOperateur().equals("-"))
//			fail("Le bouton - n'a pas affect� le bon op�rateur !");
//		//On v�rifie que la m�thode addition fonctionne correctement
//		ecranTest.setText("13");
//		calculatrice.getCalculette().setEcran(ecranTest);
//		calculatrice.getCalculette().calcul();
//		if(!calculatrice.getCalculette().getEcran().getText().equals("-3.0"))
//			fail("La m�thode calcul n'a pas r�alis� la soustraction correctement");
		
//		//Test de la multiplication
//		ecranTest = new JLabel("10");
//		calculatrice.getCalculette().setEcran(ecranTest);
//		//On simule un click sur le bouton moins
//		calculatrice.getCalculette().getTabButton()[7].doClick();
//		//V�rification de l'affectation de la variable Chiffre
//		if(calculatrice.getCalculette().getChiffre() != 10)
//			fail("Le bouton * n'a pas r�cup�r� la bonne valeur !");
//		//V�rification de l'affectation de la variable operateur
//		if(!calculatrice.getCalculette().getOperateur().equals("*"))
//			fail("Le bouton * n'a pas affect� le bon op�rateur !");
//		//On v�rifie que la m�thode addition fonctionne correctement
//		ecranTest.setText("13");
//		calculatrice.getCalculette().setEcran(ecranTest);
//		calculatrice.getCalculette().calcul();
//		if(!calculatrice.getCalculette().getEcran().getText().equals("130.0"))
//			fail("La m�thode calcul n'a pas r�alis� la multiplication correctement");
		
		//Test de la division
		ecranTest = new JLabel("15");
		calculatrice.getCalculette().setEcran(ecranTest);
		//On simule un click sur le bouton moins
		calculatrice.getCalculette().getTabButton()[3].doClick();
		//V�rification de l'affectation de la variable Chiffre
		if(calculatrice.getCalculette().getChiffre() != 15)
			fail("Le bouton / n'a pas r�cup�r� la bonne valeur !");
		//V�rification de l'affectation de la variable operateur
		if(!calculatrice.getCalculette().getOperateur().equals("/"))
			fail("Le bouton / n'a pas affect� le bon op�rateur !");
		//On v�rifie que la m�thode addition fonctionne correctement
		ecranTest.setText("3");
		calculatrice.getCalculette().setEcran(ecranTest);
		calculatrice.getCalculette().calcul();
		if(!calculatrice.getCalculette().getEcran().getText().equals("5.0"))
			fail("La m�thode calcul n'a pas r�alis� la divison correctement");
		
//		//Test de la division par 0
//		ecranTest = new JLabel("15");
//		calculatrice.getCalculette().setEcran(ecranTest);
//		//On simule un click sur le bouton moins
//		calculatrice.getCalculette().getTabButton()[3].doClick();
//		//V�rification de l'affectation de la variable Chiffre
//		if(calculatrice.getCalculette().getChiffre() != 15)
//			fail("Le bouton / n'a pas r�cup�r� la bonne valeur !");
//		//V�rification de l'affectation de la variable operateur
//		if(!calculatrice.getCalculette().getOperateur().equals("/"))
//			fail("Le bouton / n'a pas affect� le bon op�rateur !");
//		//On v�rifie que la m�thode addition fonctionne correctement
//		ecranTest.setText("0");
//		calculatrice.getCalculette().setEcran(ecranTest);
//		calculatrice.getCalculette().calcul();
//		if(!calculatrice.getCalculette().getEcran().getText().equals("Not a number"))
//			fail("La m�thode calcul n'a pas pris en compte la division par 0");
		
	}

}
