package fr.treeptik.bibliotheque.dao.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.treeptik.bibliotheque.dao.FactoryDAO;
import fr.treeptik.bibliotheque.exception.DAOException;
import fr.treeptik.bibliotheque.pojo.Article;

public class ArticleRowMapper implements RowMapper<Article>
{
	@Override
	public Article mapRow(ResultSet rs, int rowIndex) throws SQLException
	{
		Article result = new Article();
		
		result.setId(rs.getInt("id"));
		result.setTitre(rs.getString("titre"));
		result.setChapeau(rs.getString("chapeau"));
		result.setContenu(rs.getString("contenu"));
		result.setCreationDate(rs.getDate("creationDate"));
		result.setEnLigne(rs.getBoolean("enLigne"));
		
		int categorieID = rs.getInt("categorie");
		int auteurID = rs.getInt("auteur");
		
		try
		{
			result.setCategorie(FactoryDAO.getCategorieDAO().findById(categorieID));
			result.setAuteur(FactoryDAO.getAuteurDAO().findById(auteurID));
		}
		
		catch (DAOException e)
		{
			e.printStackTrace();
		}
		
		return result;
	}
}
