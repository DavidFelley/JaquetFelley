/*
*Author David Felley
*Created on 22 mai 2018
*For JaquetFelley
*/

package Component;

import java.io.File;
import java.io.Serializable;

public class Photos implements Serializable{
	
	private String nom ;
	private String url ;
	private double width ;
	private double height ;
	
	
	public Photos(String nom, File file)
	{
		this.nom = nom ;
		this.url = file.getPath();
	}
	
	//PAS FINI MAIS AVANCE SUR GALERIE
	

}
