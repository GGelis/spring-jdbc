package fr.treeptik.bibliotheque.service;

import java.sql.Date;
import java.util.List;

import fr.treeptik.bibliotheque.exception.ServiceException;
import fr.treeptik.bibliotheque.pojo.Article;

public interface ArticleService
{
	Article save(Article article) throws ServiceException;
	void update(Article article) throws ServiceException;
	Article remove(Article article) throws ServiceException;
	Article findById(int id) throws ServiceException;
	List<Article> findAll() throws ServiceException;
	List<Article> findCommented() throws ServiceException;
	List<Article> findBetweenDates(Date begin, Date end) throws ServiceException;
}
