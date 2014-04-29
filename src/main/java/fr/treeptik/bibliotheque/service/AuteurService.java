package fr.treeptik.bibliotheque.service;

import java.util.List;

import fr.treeptik.bibliotheque.exception.ServiceException;
import fr.treeptik.bibliotheque.pojo.Auteur;

public interface AuteurService
{
	Auteur save(Auteur auteur) throws ServiceException;
	void update(Auteur auteur) throws ServiceException;
	Auteur remove(Auteur auteur) throws ServiceException;
	Auteur findById(int id) throws ServiceException;
	List<Auteur> findAll() throws ServiceException;
}
