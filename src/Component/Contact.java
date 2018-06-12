package Component;

/**
 * ------------------------------------------------------------------------------------------------------ <br/>
 * Classe : Contact <br/>
 * ------------------------------------------------------------------------------------------------------ <br/>
 * Auteur : David Felley et Valentin Jaquet <br/>
 * Description de la classe : Cette classe gère un contact <br/>
 * ------------------------------------------------------------------------------------------------------ <br/>
 * Remarque : - <br/>
 * ------------------------------------------------------------------------------------------------------ <br/>
 */


import java.io.Serializable;

public class Contact implements Serializable  
{

	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String imageContactPath;


	/**
	 * Constructeur de contact
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param telephone
	 * @param imageContactPath
	 */
	public Contact (String nom, String prenom, String email, String telephone, String imageContactPath) 
	{
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.imageContactPath = imageContactPath;
	}

	/**
	 * Retourne les informations du contact inséré
	 * @return
	 */
	public String infosContact() 
	{
		// TODO Auto-generated method stub
		return  getNom() + " " + getPrenom() + " " + getEmail() + " " + getTelephone();
	}

	/**
	 * Retourne les informations qui vont figurer sur le bouton contact
	 * @return
	 */
	public String texteBoutonContact() 
	{
		return getPrenom() + " " + getNom();
	}

	// Getters & Setters 

	public String getNom() 
	{
		return nom;
	}

	public void setNom(String nom) 
	{
		this.nom = nom;
	}

	public String getPrenom() 
	{
		return prenom;
	}

	public void setPrenom(String prenom) 
	{
		this.prenom = prenom;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getTelephone() 
	{
		return telephone;
	}

	public void setTelephone(String telephone) 
	{
		this.telephone = telephone;
	}

	public String getImageContactPath()
	{
		return imageContactPath;
	}

	public void setImageContactPath(String imageContactPath)
	{
		this.imageContactPath = imageContactPath;
	}



}
