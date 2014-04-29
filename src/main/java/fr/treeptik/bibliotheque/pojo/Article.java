package fr.treeptik.bibliotheque.pojo;

import java.io.Serializable;
import java.sql.Date;

public class Article implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String titre;
	private String chapeau;
	private String contenu;
	private Date creationDate;
	private boolean enLigne;
	private Categorie categorie;
	private Auteur auteur;
	
	public Article()
	{
		id = -1;
		titre = "";
		chapeau = "";
		contenu = "";
		creationDate = new Date(System.currentTimeMillis());
		enLigne = false;
		categorie = new Categorie();
		auteur = new Auteur();
	}
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getTitre()
	{
		return titre;
	}
	public void setTitre(String titre)
	{
		this.titre = titre;
	}
	public String getChapeau()
	{
		return chapeau;
	}
	public void setChapeau(String chapeau)
	{
		this.chapeau = chapeau;
	}
	public Date getCreationDate()
	{
		return creationDate;
	}
	public void setCreationDate(Date creationDate)
	{
		this.creationDate = creationDate;
	}
	public boolean isEnLigne()
	{
		return enLigne;
	}
	public void setEnLigne(boolean enLigne)
	{
		this.enLigne = enLigne;
	}
	public Categorie getCategorie()
	{
		return categorie;
	}
	public void setCategorie(Categorie categorie)
	{
		this.categorie = categorie;
	}
	public Auteur getAuteur()
	{
		return auteur;
	}
	public void setAuteur(Auteur auteur)
	{
		this.auteur = auteur;
	}
	public String getContenu()
	{
		return contenu;
	}
	public void setContenu(String contenu)
	{
		this.contenu = contenu;
	}

	@Override
	public String toString()
	{
		String ls = System.getProperty("line.separator");
		String result = "-- Article --"+ ls;
		result += "ID : "+ id + ls;
		result += "Titre : "+ titre + ls;
		result += "Chapeau : " + chapeau + ls;
		result += "Contenu : " + contenu + ls;
		result += "Date de création : " + creationDate + ls;
		result += "Est en ligne ? " + enLigne + ls;
		result += "Categorie ID : "+ categorie.getId() + ls;
		result += "Auteur ID : "+ auteur.getId() + ls;
		result += "-----------------------";
		return result;
	}
}
