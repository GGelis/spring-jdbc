package fr.treeptik.bibliotheque.test;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.treeptik.bibliotheque.dao.jdbc.CategorieDAOJDBC;
import fr.treeptik.bibliotheque.service.db.CategorieServiceDB;

public class CategorieServiceDBTest
{
	CategorieDAOJDBC categorieDAOJDBC;
	CategorieServiceDB categorieServiceJDBC;
	
	@Before
	public void init()
	{
		categorieDAOJDBC = EasyMock.createMock(CategorieDAOJDBC.class);
		categorieServiceJDBC = new CategorieServiceDB();
	}
	
	@Test
	public void save()
	{
	
	}
	
	@After
	public void reset()
	{
		
	}
}
