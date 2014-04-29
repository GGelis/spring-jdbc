package fr.treeptik.bibliotheque.dao;

import java.sql.Date;
import java.util.List;

import fr.treeptik.bibliotheque.exception.DAOException;
import fr.treeptik.bibliotheque.pojo.Article;
//
public interface ArticleDAO
{
	Article save(Article article) throws DAOException;
	void update(Article article) throws DAOException;
	Article remove(Article article) throws DAOException;
	Article findById(int id) throws DAOException;
	List<Article> findAll() throws DAOException;
	List<Article> findCommented() throws DAOException;
	List<Article> findBetweenDates(Date begin, Date end) throws DAOException;
}
