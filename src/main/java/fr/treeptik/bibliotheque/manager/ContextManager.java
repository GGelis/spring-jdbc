package fr.treeptik.bibliotheque.manager;

import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ContextManager
{
	private static FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("src/main/resources/conf/applicationcontext.xml");

	public static void close()
	{
		context.close();
	}
	
	public static FileSystemXmlApplicationContext getContext()
	{
		return context;
	}
}
