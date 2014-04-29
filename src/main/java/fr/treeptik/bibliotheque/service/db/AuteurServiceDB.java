package fr.treeptik.bibliotheque.service.db;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.treeptik.bibliotheque.dao.FactoryDAO;
import fr.treeptik.bibliotheque.exception.DAOException;
import fr.treeptik.bibliotheque.exception.ServiceException;
import fr.treeptik.bibliotheque.pojo.Auteur;
import fr.treeptik.bibliotheque.service.AuteurService;

@Service
public class AuteurServiceDB implements AuteurService
{
	@Override
	public Auteur save(Auteur auteur) throws ServiceException
	{
		if(auteur == null)
			throw new NullPointerException("Auteur nul");
		
		try
		{
			auteur = FactoryDAO.getAuteurDAO().save(auteur);
		}
		
		catch (DAOException e)
		{
			throw new ServiceException("Erreur de sauvegarde de l'auteur", e);
		}
		
		return auteur;
	}

	@Override
	public void update(Auteur auteur) throws ServiceException
	{
		if(auteur == null)
			throw new NullPointerException("Auteur nul");
		
		try
		{
			FactoryDAO.getAuteurDAO().update(auteur);
		}
		
		catch (DAOException e)
		{
			throw new ServiceException("Erreur de mise à jour de l'auteur", e);
		}
	}

	@Override
	public Auteur remove(Auteur auteur) throws ServiceException
	{
		if(auteur == null)
			throw new NullPointerException("Auteur nul");
		
		try
		{
			auteur = FactoryDAO.getAuteurDAO().remove(auteur);
		}
		
		catch (DAOException e)
		{
			throw new ServiceException("Erreur de suppression de l'auteur", e);
		}
		
		return auteur;
	}

	@Override
	public Auteur findById(int id) throws ServiceException
	{
		if(id < 1)
			throw new IllegalArgumentException("Valeur du paramètre 'id' incorrecte");
		
		Auteur result = null;
		
		try
		{
			result = FactoryDAO.getAuteurDAO().findById(id);
		}
		
		catch (DAOException e)
		{
			throw new ServiceException("Erreur d'obtention de l'auteur", e);
		}
		
		return result;
	}

	@Override
	public List<Auteur> findAll() throws ServiceException
	{
		List<Auteur> result = null;
		
		try
		{
			result = FactoryDAO.getAuteurDAO().findAll();
		}
		
		catch (DAOException e)
		{
			throw new ServiceException("Erreur d'obtention de la liste d'auteurs", e);
		}
		
		return result;
	}
}
