package fr.treeptik.bibliotheque.dao;

import java.util.List;

import fr.treeptik.bibliotheque.exception.DAOException;
import fr.treeptik.bibliotheque.pojo.Categorie;

public interface CategorieDAO
{
	Categorie save(Categorie categorie)throws DAOException;
	void update(Categorie categorie)throws DAOException;
	Categorie remove(Categorie categorie)throws DAOException;
	Categorie findById(int id)throws DAOException;
	List<Categorie> findAll()throws DAOException;
}
