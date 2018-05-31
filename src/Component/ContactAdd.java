package Component;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;



public class ContactAdd extends ContactForm{
	
	private ContactApp contactApp;

	private CardLayout cl ;
	private JPanel jp;
	
	
	
	public ContactAdd(boolean modification, CardLayout cl, JPanel jp) {
		super(modification);
		this.cl = cl;
		this.jp = jp;
		buttonReturn.addActionListener(new ClickBack());
		buttonValidate.addActionListener(new ClickSaveContact());
	}
	
	
	
	//ActionListener sur le bouton save (appel � la m�thode d'ajout d'un contact � la liste des contacts)
			class ClickSaveContact implements ActionListener 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
				
//					contacts.add(getInfos(id));
//					System.out.println(contacts.get(id).toString());
//					eraseInfos();
//					System.out.println("valeur de l'id: " + id);
//					id++;
				}
			}
	
	//ActionListener sur le bouton retour afin d'afficher la liste des contacts
			class ClickBack implements ActionListener 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					cl.show(jp, "contactList");
				}
			}
			
			
			
			
	

}
