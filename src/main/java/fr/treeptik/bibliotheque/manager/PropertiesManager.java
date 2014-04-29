package fr.treeptik.bibliotheque.manager;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import fr.treeptik.bibliotheque.utils.PropertiesWatcher;

public class PropertiesManager
{
	private static Properties properties = new Properties();
	private static PropertiesWatcher propertiesWatcher = new PropertiesWatcher();
	
	public static void launchWatcher()
	{
		loadMode();
		propertiesWatcher.start();
	}
	
	public static void stopWatcher()
	{
		propertiesWatcher.interrupt();
	}
	
	public static void loadMode()
	{
		try
		{
			properties.load(new FileReader("src/main/resources/conf/conf.properties"));
		}
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static Properties getProperties()
	{
		return properties;
	}
}
