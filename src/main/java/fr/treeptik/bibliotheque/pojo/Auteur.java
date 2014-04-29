package fr.treeptik.bibliotheque.pojo;

import java.io.Serializable;

public class Auteur implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nom;
	private String prenom;
	private String url;
	private String email;
	
	public Auteur()
	{
		id = -1;
		nom = "";
		prenom = "";
		url = "";
		email = "";
	}
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
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
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	@Override
	public String toString()
	{
		String ls = System.getProperty("line.separator");
		String result = "-- Auteur --"+ ls;
		result += "ID : "+ id + ls;
		result += "Nom : "+ nom + ls;
		result += "Prenom : " + prenom + ls;
		result += "Url : " + url + ls;
		result += "Email : " + email + ls;
		result += "-----------------------";
		return result;
	}
}
