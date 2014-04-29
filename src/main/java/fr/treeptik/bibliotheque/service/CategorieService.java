package fr.treeptik.bibliotheque.service;

import java.util.List;

import fr.treeptik.bibliotheque.exception.ServiceException;
import fr.treeptik.bibliotheque.pojo.Categorie;

public interface CategorieService
{
	Categorie save(Categorie categorie) throws ServiceException;
	void update(Categorie categorie) throws ServiceException;
	Categorie remove(Categorie categorie) throws ServiceException;
	Categorie findById(int id) throws ServiceException;
	List<Categorie> findAll() throws ServiceException;
}