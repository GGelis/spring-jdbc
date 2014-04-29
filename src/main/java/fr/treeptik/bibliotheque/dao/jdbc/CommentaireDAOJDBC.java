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

import fr.treeptik.bibliotheque.dao.CommentaireDAO;
import fr.treeptik.bibliotheque.dao.jdbc.mapper.CommentaireRowMapper;
import fr.treeptik.bibliotheque.exception.DAOException;
import fr.treeptik.bibliotheque.pojo.Commentaire;

@Repository
public class CommentaireDAOJDBC extends JdbcDaoSupport implements CommentaireDAO
{
	@Autowired
	private DataSource dataSource;
	
	@PostConstruct
	public void init()
	{
		setDataSource(dataSource);
	}
	
	@Override
	public Commentaire save(Commentaire commentaire) throws DAOException
	{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int updateResult = -1;
		final String sql = "insert into commentaire (article, nom, email, texte, creationDate, validation) values (?,?,?,?,?,?)";
		final int articleID = commentaire.getArticle().getId();
		final String nom = commentaire.getNom();
		final String email = commentaire.getEmail();
		final String texte = commentaire.getTexte();
		final Date creationDate = commentaire.getCreationDate();
		final boolean validation = commentaire.isValidation();
		
		try
		{
			updateResult = getJdbcTemplate().update(new PreparedStatementCreator()
			{
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException 
	            {
	                    PreparedStatement ps =connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	                    ps.setInt(1, articleID);
	                    ps.setString(2, nom);
	                    ps.setString(3, email);
	                    ps.setString(4, texte);
	                    ps.setDate(5, creationDate);
	                    ps.setObject(6, validation);
	                    return ps;
	            }
	        },keyHolder);
		}
		
		catch (DataAccessException e)
		{
			throw new DAOException("Erreur de sauvegarde du commentaire", e);
		}

		if(updateResult != 1)
		{
			throw new DAOException("Erreur de sauvegarde du commentaire");
			
		}
		
		commentaire.setId(keyHolder.getKey().intValue());
		return commentaire;
	}

	@Override
	public void update(Commentaire commentaire) throws DAOException
	{
		Object[] values = new Object[7];
		values[0] = commentaire.getArticle().getId();
		values[1] = commentaire.getNom();
		values[2] = commentaire.getEmail();
		values[3] = commentaire.getTexte();
		values[4] = commentaire.getCreationDate();
		values[5] = commentaire.isValidation();
		values[6] = commentaire.getId();
		int updateResult = -1;
		
		try
		{
			updateResult = getJdbcTemplate().update("update commentaire set article = ? , nom = ? , email = ? , texte = ? , creationDate = ? , validation = ? where id = ?", values);
		}
		
		catch (DataAccessException e)
		{
			throw new DAOException("Erreur de mise à jour du commentaire", e);
		}

		if(updateResult != 1)
		{
			throw new DAOException("Erreur de mise à jour du commentaire");
		}
	}

	@Override
	public Commentaire remove(Commentaire commentaire) throws DAOException
	{
		Object[] values = new Object[1];
		values[0] = commentaire.getId();
		int updateResult = -1;
		
		try
		{
			updateResult = getJdbcTemplate().update("delete from commentaire where id = ?", values);
		}
		
		catch (DataAccessException e)
		{
			throw new DAOException("Erreur de suppression du commentaire", e);
		}

		if(updateResult != 1)
		{
			throw new DAOException("Erreur de suppression du commentaire");
		}
		
		return null;
	}

	@Override
	public Commentaire findById(int id) throws DAOException
	{
		Object[] values = new Object[1];
		values[0] = id;
		Commentaire result = null;

		try
		{
			result = getJdbcTemplate().queryForObject("select * from commentaire where id = ?", values, new CommentaireRowMapper());
		}
		
		catch (DataAccessException e)
		{
			throw new DAOException("Erreur d'obtention du commentaire", e);
		}

		return result;
	}

	@Override
	public List<Commentaire> findAll() throws DAOException
	{
		List<Commentaire> result = null;

		try
		{
			result = getJdbcTemplate().query("select * from commentaire", new CommentaireRowMapper());
		}
		
		catch (DataAccessException e)
		{
			throw new DAOException("Erreur d'obtention de la liste de commentaires", e);
		}

		return result;
	}
}
