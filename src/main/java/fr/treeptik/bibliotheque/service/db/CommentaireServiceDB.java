package fr.treeptik.bibliotheque.service.db;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.treeptik.bibliotheque.dao.FactoryDAO;
import fr.treeptik.bibliotheque.exception.DAOException;
import fr.treeptik.bibliotheque.exception.ServiceException;
import fr.treeptik.bibliotheque.pojo.Article;
import fr.treeptik.bibliotheque.pojo.Commentaire;
import fr.treeptik.bibliotheque.service.CommentaireService;
import fr.treeptik.bibliotheque.service.FactoryService;

@Service
public class CommentaireServiceDB implements CommentaireService
{
	@Override
	public Commentaire save(Commentaire commentaire) throws ServiceException
	{
		if(commentaire == null)
			throw new NullPointerException("Commentaire nul");
		
		Article article = commentaire.getArticle();
		
		try
		{
			if(article.getId() == -1)
				FactoryService.getArticleService().save(article);
				
			commentaire = FactoryDAO.getCommentaireDAO().save(commentaire);
		}
		
		catch (DAOException e)
		{
			throw new ServiceException("Erreur de sauvegarde du commentaire", e);
		}
		
		return commentaire;
	}

	@Override
	public void update(Commentaire commentaire) throws ServiceException
	{
		if(commentaire == null)
			throw new NullPointerException("Commentaire nul");
		
		try
		{
			FactoryDAO.getCommentaireDAO().update(commentaire);
		}
		
		catch (DAOException e)
		{
			throw new ServiceException("Erreur de mise à jour du commentaire", e);
		}
	}

	@Override
	public Commentaire remove(Commentaire commentaire) throws ServiceException
	{
		if(commentaire == null)
			throw new NullPointerException("Commentaire nul");
		
		try
		{
			commentaire = FactoryDAO.getCommentaireDAO().remove(commentaire);
		}
		
		catch (DAOException e)
		{
			throw new ServiceException("Erreur de suppression du commentaire", e);
		}
		
		return commentaire;
	}

	@Override
	public Commentaire findById(int id) throws ServiceException
	{
		if(id < 1)
			throw new IllegalArgumentException("Valeur du paramètre 'id' incorrecte");
		
		Commentaire result = null;
		
		try
		{
			result = FactoryDAO.getCommentaireDAO().findById(id);
		}
		
		catch (DAOException e)
		{
			throw new ServiceException("Erreur d'obtention du commentaire", e);
		}
		
		return result;
	}

	@Override
	public List<Commentaire> findAll() throws ServiceException
	{
		List<Commentaire> result = null;
		
		try
		{
			result = FactoryDAO.getCommentaireDAO().findAll();
		}
		
		catch (DAOException e)
		{
			throw new ServiceException("Erreur d'obtention de la liste de commentaires", e);
		}
		
		return result;
	}
}
