package fr.treeptik.bibliotheque.dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import fr.treeptik.bibliotheque.dao.ArticleDAO;
import fr.treeptik.bibliotheque.dao.jdbc.mapper.ArticleRowMapper;
import fr.treeptik.bibliotheque.exception.DAOException;
import fr.treeptik.bibliotheque.pojo.Article;

@Repository
public class ArticleDAOJDBC extends JdbcDaoSupport implements ArticleDAO
{
	@Autowired
	private DataSource dataSource;
	
	@PostConstruct
	public void init()
	{
		setDataSource(dataSource);
	}
	
	@Override
	public Article save(Article article) throws DAOException
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int updateResult = -1;
		final String sql = "insert into article (titre, chapeau, contenu, creationDate, enLigne, categorie, auteur) values (?,?,?,?,?,?,?)";
		final String titre = article.getTitre();
		final String chapeau = article.getChapeau();
		final String contenu = article.getContenu();
		final Date creationDate = article.getCreationDate();
		final boolean enLigne = article.isEnLigne();
		final int categorieID = article.getCategorie().getId();
		final int auteurID = article.getAuteur().getId();
		
		try
		{
			updateResult = getJdbcTemplate().update(new PreparedStatementCreator()
			{
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException 
	            {
	                    PreparedStatement ps =connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	                    ps.setString(1, titre);
	                    ps.setString(2, chapeau);
	                    ps.setString(3, contenu);
	                    ps.setDate(4, creationDate);
	                    ps.setObject(5, enLigne);
	                    ps.setInt(6, categorieID);
	                    ps.setInt(7, auteurID);
	                    return ps;
	            }
	        },keyHolder);
		}
		
		catch (DataAccessException e)
		{
			throw new DAOException("Erreur de sauvegarde de l'article", e);
		}

		if(updateResult != 1)
		{
			throw new DAOException("Erreur de sauvegarde de l'article");
		}
		
		article.setId(keyHolder.getKey().intValue());
		return article;
	}

	@Override
	public void update(Article article) throws DAOException
	{
		Object[] values = new Object[8];
		values[0] = article.getTitre();
		values[1] = article.getChapeau();
		values[2] = article.getContenu();
		values[3] = article.getCreationDate();
		values[4] = article.isEnLigne();
		values[5] = article.getCategorie().getId();
		values[6] = article.getAuteur().getId();
		values[7] = article.getId();
		int updateResult = -1;
		
		try
		{
			updateResult = getJdbcTemplate().update("update article set titre = ? , chapeau = ? , contenu = ? , creationDate = ? , enLigne = ? , categorie = ? , auteur = ? where id = ?", values);
		}
		
		catch (DataAccessException e)
		{
			throw new DAOException("Erreur de mise à jour de l'article", e);
		}

		if(updateResult != 1)
		{
			throw new DAOException("Erreur de mise à jour de l'article");
		}
	}

	@Override
	public Article remove(Article article) throws DAOException
	{
		Object[] values = new Object[1];
		values[0] = article.getId();
		int updateResult = -1;
		
		try
		{
			updateResult = getJdbcTemplate().update("delete from article where id = ?", values);
		}
		
		catch (DataAccessException e)
		{
			throw new DAOException("Erreur de suppression de l'article", e);
		}

		if(updateResult != 1)
		{
			throw new DAOException("Erreur de suppression de l'article");
		}
		
		return null;
	}

	@Override
	public Article findById(int id) throws DAOException
	{
		Object[] values = new Object[1];
		values[0] = id;
		Article result = null;

		try
		{
			result = getJdbcTemplate().queryForObject("select * from article where id = ?", values, new ArticleRowMapper());
		}
		
		catch (DataAccessException e)
		{
			throw new DAOException("Erreur d'obtention de l'article", e);
		}

		return result;
	}

	@Override
	public List<Article> findAll() throws DAOException
	{
		List<Article> result = null;

		try
		{
			result = getJdbcTemplate().query("select * from article", new ArticleRowMapper());
		}
		
		catch (DataAccessException e)
		{
			throw new DAOException("Erreur d'obtention de la liste d'articles", e);
		}

		return result;
	}

	@Override
	public List<Article> findCommented() throws DAOException
	{
		List<Article> result = null;

		try
		{
			result = getJdbcTemplate().query("select distinct a.id,a.titre,a.chapeau,a.contenu,a.creationDate,a.enLigne,a.categorie,a.auteur from article a join commentaire c on a.id = c.article", new ArticleRowMapper());
		}
		
		catch (DataAccessException e)
		{
			throw new DAOException("Erreur d'obtention de la liste d'articles", e);
		}

		return result;
	}

	@Override
	public List<Article> findBetweenDates(Date begin, Date end) throws DAOException
	{
		List<Article> result = null;
		Object[] values = new Object[2];
		values[0] = begin;
		values[1] = end;
		
		try
		{
			result = getJdbcTemplate().query("select a.id,a.titre,a.chapeau,a.contenu,a.creationDate,a.enLigne,a.categorie,a.auteur from article a where a.creationDate between ? and ?", values, new ArticleRowMapper());
		}
		
		catch (DataAccessException e)
		{
			throw new DAOException("Erreur d'obtention de la liste d'articles", e);
		}

		return result;
	}

}
