package fr.treeptik.bibliotheque.pojo;

import java.io.Serializable;

public class Categorie implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nom;
	private String url;
	
	public Categorie()
	{
		id = -1;
		nom = "";
		url = "";
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
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	
	@Override
	public String toString()
	{
		String ls = System.getProperty("line.separator");
		String result = "-- Catégorie --"+ ls;
		result += "ID : "+ id + ls;
		result += "Nom : "+ nom + ls;
		result += "Url : " + url + ls;
		result += "-----------------------";
		return result;
	}
}
