package fr.treeptik.bibliotheque.dao;

import java.util.List;

import fr.treeptik.bibliotheque.exception.DAOException;
import fr.treeptik.bibliotheque.pojo.Commentaire;

public interface CommentaireDAO
{
	Commentaire save(Commentaire commentaire) throws DAOException;
	void update(Commentaire commentaire) throws DAOException;
	Commentaire remove(Commentaire commentaire) throws DAOException;
	Commentaire findById(int id) throws DAOException;
	List<Commentaire> findAll() throws DAOException;
}
