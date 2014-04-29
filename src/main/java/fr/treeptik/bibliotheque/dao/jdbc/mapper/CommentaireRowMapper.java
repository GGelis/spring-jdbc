package fr.treeptik.bibliotheque.dao.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.treeptik.bibliotheque.dao.FactoryDAO;
import fr.treeptik.bibliotheque.exception.DAOException;
import fr.treeptik.bibliotheque.pojo.Commentaire;

public class CommentaireRowMapper implements RowMapper<Commentaire>
{
	@Override
	public Commentaire mapRow(ResultSet rs, int rowIndex) throws SQLException
	{
		Commentaire result = new Commentaire();
		
		result.setId(rs.getInt("id"));
		result.setNom(rs.getString("nom"));
		result.setEmail(rs.getString("email"));
		result.setTexte(rs.getString("texte"));
		result.setCreationDate(rs.getDate("creationDate"));
		result.setValidation(rs.getBoolean("validation"));
		
		int articleID = rs.getInt("article");
		
		try
		{
			result.setArticle(FactoryDAO.getArticleDAO().findById(articleID));
		}
		
		catch (DAOException e)
		{
			e.printStackTrace();
		}
		
		return result;
	}
}
