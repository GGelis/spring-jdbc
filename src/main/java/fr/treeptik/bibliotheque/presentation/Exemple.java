package fr.treeptik.bibliotheque.presentation;

import java.sql.Date;
import java.util.List;

import fr.treeptik.bibliotheque.exception.ServiceException;
import fr.treeptik.bibliotheque.manager.ContextManager;
import fr.treeptik.bibliotheque.manager.LanguageManager;
import fr.treeptik.bibliotheque.manager.PropertiesManager;
import fr.treeptik.bibliotheque.pojo.Article;
import fr.treeptik.bibliotheque.pojo.Auteur;
import fr.treeptik.bibliotheque.pojo.Categorie;
import fr.treeptik.bibliotheque.pojo.Commentaire;
import fr.treeptik.bibliotheque.service.ArticleService;
import fr.treeptik.bibliotheque.service.AuteurService;
import fr.treeptik.bibliotheque.service.CategorieService;
import fr.treeptik.bibliotheque.service.CommentaireService;
import fr.treeptik.bibliotheque.service.FactoryService;

public class Exemple
{
	@SuppressWarnings("deprecation")
	public static void main(String[] args)
	{
		// Initialisation du programme
		PropertiesManager.launchWatcher();

		// Initialisation : Catégorie
		Categorie categorie = new Categorie();
		categorie.setNom("Informatique");
		categorie.setUrl("www.informatique.com");
		CategorieService categorieService = FactoryService.getCategorieService();
		List<Categorie> categoriesList = null;
		
		// Initialisation : Auteur
		Auteur auteur = new Auteur();
		auteur.setNom("Tu");
		auteur.setPrenom("Tu");
		auteur.setUrl("www.tutu.com");
		auteur.setEmail("tutu@tututu.com");
		AuteurService auteurService = FactoryService.getAuteurService();
		List<Auteur> auteursList = null;
		
		// Initialisation : Article
		Article article = new Article();
		article.setTitre("Troll");
		article.setChapeau("Casquette");
		article.setContenu("Bla bla bla");
		article.setCreationDate(new Date(System.currentTimeMillis()));
		article.setEnLigne(true);
		article.setCategorie(categorie);
		article.setAuteur(auteur);
		ArticleService articleService = FactoryService.getArticleService();
		List<Article> articlesList = null;
		List<Article> commentedList = null;
		List<Article> betweenDateList = null;
		
		// Initialisation : Commentaire
		Commentaire commentaire = new Commentaire();
		commentaire.setArticle(article);
		commentaire.setNom("Inutile");
		commentaire.setEmail("troll@troll.fr");
		commentaire.setTexte("Bla bla bla bla bla");
		commentaire.setCreationDate(new Date(System.currentTimeMillis()));
		commentaire.setValidation(false);
		CommentaireService commentaireService = FactoryService.getCommentaireService();
		List<Commentaire> commentairesList = null;
		
		// Test de logs
		String test = ContextManager.getContext().getMessage("test",null,LanguageManager.getLocale());
		System.out.println(test);
		
		
		try
		{
			// Appel des fonctions DAO
			categorie = categorieService.save(categorie);
			categorie.setNom("modifié");
			categorieService.update(categorie);
			categorie = categorieService.findById(categorie.getId());
			categoriesList = categorieService.findAll();
			
			auteur = auteurService.save(auteur);
			auteur.setNom("To");
			auteurService.update(auteur);
			auteur = auteurService.findById(auteur.getId());
			auteursList = auteurService.findAll();
			
			article = articleService.save(article);
			article.setTitre("Tu Troll Tu");
			articleService.update(article);
			article = articleService.findById(article.getId());
			articlesList = articleService.findAll();
			
			commentaire = commentaireService.save(commentaire);
			commentaire.setNom("Utile");
			commentaireService.update(commentaire);
			commentaire = commentaireService.findById(commentaire.getId());
			commentairesList = commentaireService.findAll();
			
			// Requetes
			commentedList = articleService.findCommented();
			Date begin = new Date(114,3,1);
			Date end = new Date(114,5,1);
			betweenDateList = articleService.findBetweenDates(begin, end);
		
			// Suppression des lignes insérées pour conserver l'état de la base
			commentaire = commentaireService.remove(commentaire);
			article = articleService.remove(article);
			categorie = categorieService.remove(categorie);
			auteur = auteurService.remove(auteur);
		}
		
		catch (ServiceException e)
		{
			e.printStackTrace();
			System.exit(1);
		}

		// Affichage des tables
		
		System.out.println(System.getProperty("line.separator") + "---- Affichage des tables ----" + System.getProperty("line.separator"));
		
		for(Categorie c : categoriesList)
		{
			System.out.println(c.toString()+System.getProperty("line.separator"));
		}
		
		for(Auteur a : auteursList)
		{
			System.out.println(a.toString()+System.getProperty("line.separator"));
		}
		
		for(Article a : articlesList)
		{
			System.out.println(a.toString()+System.getProperty("line.separator"));
		}
		
		for(Commentaire c : commentairesList)
		{
			System.out.println(c.toString()+System.getProperty("line.separator"));
		}
		
		// Affichage des requetes
		
		System.out.println(System.getProperty("line.separator") + "---- Requetes ----" + System.getProperty("line.separator"));
		
		System.out.println(System.getProperty("line.separator") + "---- Articles commentés ----" + System.getProperty("line.separator"));

		for(Article a : commentedList)
		{
			System.out.println(a.toString()+System.getProperty("line.separator"));
		}
		
		System.out.println(System.getProperty("line.separator") + "---- Articles entre deux dates ----" + System.getProperty("line.separator"));

		for(Article a : betweenDateList)
		{
			System.out.println(a.toString()+System.getProperty("line.separator"));
		}
		
		// Test du Watcher
		int cpt = 0;
		
		do
		{
			try
			{
				Thread.sleep(500);
				
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			cpt++;
			
		} while (cpt < 1);
		
		// Fermeture du contexte et arret du watcher
		PropertiesManager.stopWatcher();
		ContextManager.close();
	}
}
