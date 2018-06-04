package Component;

import java.io.Serializable;

import javax.swing.ImageIcon;

public class Contact implements Serializable  {
	
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private int id;
	private ImageIcon contactPhoto;
	
	
	// Constructeur sans photo
	public Contact (String nom, String prenom, String email, String telephone, int id) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.id = id;
	}
	
	// Constructeur avec photo
	public Contact (String nom, String prenom, String email, String telephone, int id, ImageIcon contactPhoto) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.id = id;
		setContactPhoto(contactPhoto);
	}

	private ImageIcon getContactPhoto() {
		return contactPhoto;
	}

	private void setContactPhoto(ImageIcon contactPhoto) {
		this.contactPhoto = contactPhoto;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return "Contact inséré: " + getNom() + " " + getPrenom() + " " + getEmail() + " " + getTelephone();
	}
	
	public String infosContact() {
		// TODO Auto-generated method stub
		return  getId() + " " + getNom() + " " + getPrenom() + " " + getEmail() + " " + getTelephone();
	}
	
	public String texteBoutonContact() {
		return getNom() + " " + getPrenom();
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
