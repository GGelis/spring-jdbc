package fr.treeptik.bibliotheque.service;

import org.springframework.stereotype.Component;

import fr.treeptik.bibliotheque.manager.ContextManager;
import fr.treeptik.bibliotheque.manager.PropertiesManager;

@Component
public class FactoryService
{	
	public static CategorieService getCategorieService()
	{
		String daoMode = PropertiesManager.getProperties().getProperty("daoMode");
		
		switch (daoMode)
		{
			case "db":
				return ContextManager.getContext().getBean("categorieServiceDB",CategorieService.class);
			default:
				return null;
		}
	}
	
	public static AuteurService getAuteurService()
	{
		String daoMode = PropertiesManager.getProperties().getProperty("daoMode");
		
		switch (daoMode)
		{
			case "db":
				return ContextManager.getContext().getBean("auteurServiceDB",AuteurService.class);
			default:
				return null;
		}
	}
	
	public static ArticleService getArticleService()
	{
		String daoMode = PropertiesManager.getProperties().getProperty("daoMode");
		
		switch (daoMode)
		{
			case "db":
				return ContextManager.getContext().getBean("articleServiceDB",ArticleService.class);
			default:
				return null;
		}
	}
	
	public static CommentaireService getCommentaireService()
	{
		String daoMode = PropertiesManager.getProperties().getProperty("daoMode");
		
		switch (daoMode)
		{
			case "db":
				return ContextManager.getContext().getBean("commentaireServiceDB",CommentaireService.class);
			default:
				return null;
		}
	}
}
