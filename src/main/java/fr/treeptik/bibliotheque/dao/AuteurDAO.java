package fr.treeptik.bibliotheque.dao;

import java.util.List;

import fr.treeptik.bibliotheque.exception.DAOException;
import fr.treeptik.bibliotheque.pojo.Auteur;

public interface AuteurDAO
{
	Auteur save(Auteur auteur) throws DAOException;
	void update(Auteur auteur) throws DAOException;
	Auteur remove(Auteur auteur) throws DAOException;
	Auteur findById(int id) throws DAOException;
	List<Auteur> findAll() throws DAOException;
}