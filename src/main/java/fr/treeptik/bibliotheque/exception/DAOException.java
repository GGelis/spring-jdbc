package fr.treeptik.bibliotheque.exception;

public class DAOException extends Exception
{
	private static final long serialVersionUID = 5468422522382407592L;
	
	public DAOException(String message)
	{
		super(message);
	}
	public DAOException(String message, Throwable error)
	{
		super(message,error);
	}
}
