package fr.treeptik.bibliotheque.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import fr.treeptik.bibliotheque.dao.CategorieDAO;
import fr.treeptik.bibliotheque.exception.DAOException;
import fr.treeptik.bibliotheque.pojo.Categorie;

@Repository
public class CategorieDAOJDBC extends JdbcDaoSupport implements CategorieDAO
{
	@Autowired
	private DataSource dataSource;
	
	@PostConstruct
	public void init()
	{
		setDataSource(dataSource);
	}
	
	@Override
	public Categorie save(Categorie categorie) throws DAOException
	{	
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int updateResult = -1;
		final String sql = "insert into categorie (nom, url) values (?,?)";
		final String nom = categorie.getNom();
		final String url = categorie.getUrl();
		
		try
		{
			updateResult = getJdbcTemplate().update(new PreparedStatementCreator()
			{
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException 
	            {
	                    PreparedStatement ps =connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	                    ps.setString(1,nom);
	                    ps.setString(2,url);
	                    return ps;
	            }
	        },keyHolder);
		}
		
		catch (DataAccessException e)
		{
			throw new DAOException("Erreur de sauvegarde de la catégorie", e);
		}

		if(updateResult != 1)
		{
			throw new DAOException("Erreur de sauvegarde de la catégorie");
		}
		
		categorie.setId(keyHolder.getKey().intValue());
		return categorie;
	}

	@Override
	public void update(Categorie categorie) throws DAOException
	{	
		Object[] values = new Object[3];
		values[0] = categorie.getNom();
		values[1] = categorie.getUrl();
		values[2] = categorie.getId();
		int updateResult = -1;
		
		try
		{
			updateResult = getJdbcTemplate().update("update categorie set nom = ? , url = ? where id = ?", values);
		}
		
		catch (DataAccessException e)
		{
			throw new DAOException("Erreur de mise à jour de la catégorie", e);
		}

		if(updateResult != 1)
		{
			throw new DAOException("Erreur de mise à jour de la catégorie");
		}
	}

	@Override
	public Categorie remove(Categorie categorie) throws DAOException
	{
		Object[] values = new Object[1];
		values[0] = categorie.getId();
		int updateResult = -1;
		
		try
		{
			updateResult = getJdbcTemplate().update("delete from categorie where id = ?", values);
		}
		
		catch (DataAccessException e)
		{
			throw new DAOException("Erreur de suppression de la catégorie", e);
		}

		if(updateResult != 1)
		{
			throw new DAOException("Erreur de suppression de la catégorie");
		}
		
		return null;
	}

	@Override
	public Categorie findById(int id) throws DAOException
	{	
		Object[] values = new Object[1];
		values[0] = id;
		Categorie result = null;

		try
		{
			result = getJdbcTemplate().queryForObject("select * from categorie where id = ?", values, new BeanPropertyRowMapper<Categorie>(Categorie.class));
		}
		
		catch (DataAccessException e)
		{
			throw new DAOException("Erreur d'obtention de la catégorie", e);
		}

		return result;
	}

	@Override
	public List<Categorie> findAll() throws DAOException
	{
		List<Categorie> result = null;

		try
		{
			result = getJdbcTemplate().query("select * from categorie", new BeanPropertyRowMapper<Categorie>(Categorie.class));
		}
		
		catch (DataAccessException e)
		{
			throw new DAOException("Erreur d'obtention de la liste de catégories", e);
		}

		return result;
	}
}