package fr.treeptik.bibliotheque.exception;

public class ServiceException extends Exception
{
	private static final long serialVersionUID = 5468422522382407592L;
	
	public ServiceException(String message)
	{
		super(message);
	}
	
	public ServiceException(String message, Throwable error)
	{
		super(message,error);
	}
}
