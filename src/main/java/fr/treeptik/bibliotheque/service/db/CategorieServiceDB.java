package fr.treeptik.bibliotheque.service.db;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.treeptik.bibliotheque.dao.FactoryDAO;
import fr.treeptik.bibliotheque.exception.DAOException;
import fr.treeptik.bibliotheque.exception.ServiceException;
import fr.treeptik.bibliotheque.pojo.Categorie;
import fr.treeptik.bibliotheque.service.CategorieService;

@Service
public class CategorieServiceDB implements CategorieService
{	
	@Override
	public Categorie save(Categorie categorie) throws ServiceException
	{
		if(categorie == null)
			throw new NullPointerException("Catégorie nulle");
		
		try
		{
			categorie = FactoryDAO.getCategorieDAO().save(categorie);
		}
		
		catch (DAOException e)
		{
			throw new ServiceException("Erreur de sauvegarde de la catégorie", e);
		}
		
		return categorie;
	}

	@Override
	public void update(Categorie categorie) throws ServiceException
	{
		if(categorie == null)
			throw new NullPointerException("Catégorie nulle");
		
		try
		{
			FactoryDAO.getCategorieDAO().update(categorie);
		}
		
		catch (DAOException e)
		{
			throw new ServiceException("Erreur de mise à jour de la catégorie", e);
		}
	}

	@Override
	public Categorie remove(Categorie categorie) throws ServiceException
	{
		if(categorie == null)
			throw new NullPointerException("Catégorie nulle");
		
		try
		{
			categorie = FactoryDAO.getCategorieDAO().remove(categorie);
		}
		
		catch (DAOException e)
		{
			throw new ServiceException("Erreur de suppression de la catégorie", e);
		}
		
		return categorie;
	}

	@Override
	public Categorie findById(int id) throws ServiceException
	{
		if(id < 1)
			throw new IllegalArgumentException("Valeur du paramètre 'id' incorrecte");
		
		Categorie result = null;
		
		try
		{
			result = FactoryDAO.getCategorieDAO().findById(id);
		}
		
		catch (DAOException e)
		{
			throw new ServiceException("Erreur d'obtention de la catégorie", e);
		}
		
		return result;
	}

	@Override
	public List<Categorie> findAll() throws ServiceException
	{
		List<Categorie> result = null;
		
		try
		{
			result = FactoryDAO.getCategorieDAO().findAll();
		}
		
		catch (DAOException e)
		{
			throw new ServiceException("Erreur d'obtention de la liste de catégories", e);
		}
		
		return result;
	}
}
