package fr.treeptik.bibliotheque.dao;

import fr.treeptik.bibliotheque.manager.ContextManager;
import fr.treeptik.bibliotheque.manager.PropertiesManager;

public class FactoryDAO
{
	public static CategorieDAO getCategorieDAO()
	{
		String daoMode = PropertiesManager.getProperties().getProperty("daoMode");

		switch (daoMode)
		{
			case "db":
				return ContextManager.getContext().getBean("categorieDAOJDBC", CategorieDAO.class);
			default:
				return null;
		}
	}
	
	public static AuteurDAO getAuteurDAO()
	{
		String daoMode = PropertiesManager.getProperties().getProperty("daoMode");

		switch (daoMode)
		{
			case "db":
				return ContextManager.getContext().getBean("auteurDAOJDBC", AuteurDAO.class);
			default:
				return null;
		}
	}
	
	public static ArticleDAO getArticleDAO()
	{
		String daoMode = PropertiesManager.getProperties().getProperty("daoMode");

		switch (daoMode)
		{
			case "db":
				return ContextManager.getContext().getBean("articleDAOJDBC", ArticleDAO.class);
			default:
				return null;
		}
	}
	
	public static CommentaireDAO getCommentaireDAO()
	{
		String daoMode = PropertiesManager.getProperties().getProperty("daoMode");

		switch (daoMode)
		{
			case "db":
				return ContextManager.getContext().getBean("commentaireDAOJDBC", CommentaireDAO.class);
			default:
				return null;
		}
	}
}
