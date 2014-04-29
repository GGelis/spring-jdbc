package fr.treeptik.bibliotheque.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.treeptik.bibliotheque.pojo.Article;
import fr.treeptik.bibliotheque.pojo.Auteur;
import fr.treeptik.bibliotheque.pojo.Categorie;

public class ArticleServiceDBTest
{
	
	private Article article;
	
	@Before
	public void init()
	{
		article = new Article();
		article.setId(0);
		article.setTitre("Titre");
		article.setChapeau("Chapeau");
		article.setCreationDate(new Date(System.currentTimeMillis()));
		article.setEnLigne(true);
		article.setCategorie(new Categorie());
		article.setAuteur(new Auteur());
	}
	
	@Test
	public void save()
	{
		System.out.println("TestArticleServiceJDBC - save() - début");
		assertEquals(article.getTitre(),"Titre");
		assertNull(article);
		System.out.println("TestArticleServiceJDBC - save() - fin");
	}
	
	@After
	public void reset()
	{
		article.setId(-1);
		article.setTitre("");
		article.setChapeau("");
		article.setCreationDate(new Date(0));
		article.setEnLigne(false);
		article.setCategorie(null);
		article.setAuteur(null);
		article = null;
	}
}
