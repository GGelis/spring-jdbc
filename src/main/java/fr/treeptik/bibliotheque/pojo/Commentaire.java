package fr.treeptik.bibliotheque.pojo;

import java.io.Serializable;
import java.sql.Date;

public class Commentaire implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private Article article;
	private String nom;
	private String email;
	private String texte;
	private Date creationDate;
	private boolean validation;
	
	
	public Commentaire()
	{
		id = -1;
		article = new Article();
		nom = "";
		email = "";
		texte = "";
		creationDate = new Date(System.currentTimeMillis());
		validation = false;
	}
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public Article getArticle()
	{
		return article;
	}
	public void setArticle(Article article)
	{
		this.article = article;
	}
	public String getNom()
	{
		return nom;
	}
	public void setNom(String nom)
	{
		this.nom = nom;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getTexte()
	{
		return texte;
	}
	public void setTexte(String texte)
	{
		this.texte = texte;
	}
	public Date getCreationDate()
	{
		return creationDate;
	}
	public void setCreationDate(Date creationDate)
	{
		this.creationDate = creationDate;
	}
	public boolean isValidation()
	{
		return validation;
	}
	public void setValidation(boolean validation)
	{
		this.validation = validation;
	}
	
	@Override
	public String toString()
	{
		String ls = System.getProperty("line.separator");
		String result = "-- Commentaire --"+ ls;
		result += "ID : "+ id + ls;
		result += "Article ID : "+ article.getId() + ls;
		result += "Nom : " + nom + ls;
		result += "Email : " + email + ls;
		result += "Texte : " + texte + ls;
		result += "Date de création : " + creationDate + ls;
		result += "Est validé ? " + validation + ls;
		result += "-----------------------";
		return result;
	}
}
