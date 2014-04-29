package fr.treeptik.bibliotheque.service;

import java.util.List;

import fr.treeptik.bibliotheque.exception.ServiceException;
import fr.treeptik.bibliotheque.pojo.Commentaire;

public interface CommentaireService
{
	Commentaire save(Commentaire commentaire) throws ServiceException;
	void update(Commentaire commentaire) throws ServiceException;
	Commentaire remove(Commentaire commentaire) throws ServiceException;
	Commentaire findById(int id) throws ServiceException;
	List<Commentaire> findAll() throws ServiceException;
}
