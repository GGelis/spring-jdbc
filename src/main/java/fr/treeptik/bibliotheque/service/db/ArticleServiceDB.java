package fr.treeptik.bibliotheque.service.db;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.treeptik.bibliotheque.dao.FactoryDAO;
import fr.treeptik.bibliotheque.exception.DAOException;
import fr.treeptik.bibliotheque.exception.ServiceException;
import fr.treeptik.bibliotheque.pojo.Article;
import fr.treeptik.bibliotheque.pojo.Auteur;
import fr.treeptik.bibliotheque.pojo.Categorie;
import fr.treeptik.bibliotheque.service.ArticleService;
import fr.treeptik.bibliotheque.service.FactoryService;

@Service
public class ArticleServiceDB implements ArticleService
{
	@Override
	public Article save(Article article) throws ServiceException
	{
		if (article == null)
			throw new NullPointerException("Article nul");

		Categorie categorie = article.getCategorie();
		Auteur auteur = article.getAuteur();

		try
		{
			if (categorie.getId() == -1)
				FactoryService.getCategorieService().save(categorie);

			if (auteur.getId() == -1)
				FactoryService.getAuteurService().save(auteur);

			article = FactoryDAO.getArticleDAO().save(article);
		}

		catch (DAOException e)
		{
			throw new ServiceException("Erreur de sauvegarde de l'article", e);
		}

		return article;
	}

	@Override
	public void update(Article article) throws ServiceException
	{
		if (article == null)
			throw new NullPointerException("Article nul");

		try
		{
			FactoryDAO.getArticleDAO().update(article);
		}

		catch (DAOException e)
		{
			throw new ServiceException("Erreur de mise à jour de l'article", e);
		}
	}

	@Override
	public Article remove(Article article) throws ServiceException
	{
		if (article == null)
			throw new NullPointerException("Article nul");

		try
		{
			article = FactoryDAO.getArticleDAO().remove(article);
		}

		catch (DAOException e)
		{
			throw new ServiceException("Erreur de suppression de l'article", e);
		}

		return article;
	}

	@Override
	public Article findById(int id) throws ServiceException
	{
		if (id < 1)
			throw new IllegalArgumentException("Valeur du paramètre 'id' incorrecte");

		Article result = null;

		try
		{
			result = FactoryDAO.getArticleDAO().findById(id);
		}

		catch (DAOException e)
		{
			throw new ServiceException("Erreur d'obtention de l'article", e);
		}

		return result;
	}

	@Override
	public List<Article> findAll() throws ServiceException
	{
		List<Article> result = null;

		try
		{
			result = FactoryDAO.getArticleDAO().findAll();
		}

		catch (DAOException e)
		{
			throw new ServiceException("Erreur d'obtention de la liste d'auteurs", e);
		}

		return result;
	}

	@Override
	public List<Article> findCommented() throws ServiceException
	{
		List<Article> result = null;

		try
		{
			result = FactoryDAO.getArticleDAO().findCommented();
		}

		catch (DAOException e)
		{
			throw new ServiceException("Erreur d'obtention de la liste d'auteurs", e);
		}

		return result;
	}

	@Override
	public List<Article> findBetweenDates(Date begin, Date end) throws ServiceException
	{
		List<Article> result = null;

		try
		{
			result = FactoryDAO.getArticleDAO().findBetweenDates(begin, end);
		}

		catch (DAOException e)
		{
			throw new ServiceException("Erreur d'obtention de la liste d'auteurs", e);
		}

		return result;
	}
}
