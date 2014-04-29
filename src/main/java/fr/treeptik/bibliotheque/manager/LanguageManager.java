package fr.treeptik.bibliotheque.manager;

import java.util.Locale;

public class LanguageManager
{
	private static Locale locale = new Locale(System.getProperty("user.language"));
	
	public static Locale getLocale()
	{
		return locale;
	}
}
