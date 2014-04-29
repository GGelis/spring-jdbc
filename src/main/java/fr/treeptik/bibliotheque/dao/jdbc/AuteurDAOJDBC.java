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

import fr.treeptik.bibliotheque.dao.AuteurDAO;
import fr.treeptik.bibliotheque.exception.DAOException;
import fr.treeptik.bibliotheque.pojo.Auteur;

@Repository
public class AuteurDAOJDBC extends JdbcDaoSupport implements AuteurDAO
{	
	@Autowired
	private DataSource dataSource;
	
	@PostConstruct
	public void init()
	{
		setDataSource(dataSource);
	}
	
	@Override
	public Auteur save(Auteur auteur) throws DAOException
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int updateResult = -1;
		final String sql = "insert into auteur (nom, prenom, url, email) values (?,?,?,?)";
		final String nom = auteur.getNom();
		final String prenom = auteur.getPrenom();
		final String url = auteur.getUrl();
		final String email = auteur.getEmail();
		
		try
		{
			updateResult = getJdbcTemplate().update(new PreparedStatementCreator()
			{
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException 
	            {
	                    PreparedStatement ps =connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	                    ps.setString(1,nom);
	                    ps.setString(2,prenom);
	                    ps.setString(3,url);
	                    ps.setString(4,email);
	                    return ps;
	            }
	        },keyHolder);
		}
		
		catch (DataAccessException e)
		{
			throw new DAOException("Erreur de sauvegarde de l'auteur", e);
		}

		if(updateResult != 1)
		{
			throw new DAOException("Erreur de sauvegarde de l'auteur");
		}
		
		auteur.setId(keyHolder.getKey().intValue());
		return auteur;
	}

	@Override
	public void update(Auteur auteur) throws DAOException
	{ 
		Object[] values = new Object[5];
		values[0] = auteur.getNom();
		values[1] = auteur.getPrenom();
		values[2] = auteur.getUrl();
		values[3] = auteur.getEmail();
		values[4] = auteur.getId();
		int updateResult = -1;
		
		try
		{
			updateResult = getJdbcTemplate().update("update auteur set nom = ? , prenom = ?,  url = ? , email = ? where id = ?", values);
		}
		
		catch (DataAccessException e)
		{
			throw new DAOException("Erreur de mise à jour de l'auteur", e);
		}

		if(updateResult != 1)
		{
			throw new DAOException("Erreur de mise à jour de l'auteur");
		}
	}

	@Override
	public Auteur remove(Auteur auteur) throws DAOException
	{
		Object[] values = new Object[1];
		values[0] = auteur.getId();
		int updateResult = -1;
		
		try
		{
			updateResult = getJdbcTemplate().update("delete from auteur where id = ?", values);
		}
		
		catch (DataAccessException e)
		{
			throw new DAOException("Erreur de suppression de l'auteur", e);
		}

		if(updateResult != 1)
		{
			throw new DAOException("Erreur de suppression de l'auteur");
		}
		
		return null;
	}

	@Override
	public Auteur findById(int id) throws DAOException
	{
		Object[] values = new Object[1];
		values[0] = id;
		Auteur result = null;

		try
		{
			result = getJdbcTemplate().queryForObject("select * from auteur where id = ?", values, new BeanPropertyRowMapper<Auteur>(Auteur.class));
		}
		
		catch (DataAccessException e)
		{
			throw new DAOException("Erreur d'obtention de l'auteur", e);
		}

		return result;
	}

	@Override
	public List<Auteur> findAll() throws DAOException
	{
		List<Auteur> result = null;

		try
		{
			result = getJdbcTemplate().query("select * from auteur", new BeanPropertyRowMapper<Auteur>(Auteur.class));
		}
		
		catch (DataAccessException e)
		{
			throw new DAOException("Erreur d'obtention de la liste d'auteurs", e);
		}

		return result;
	}
}