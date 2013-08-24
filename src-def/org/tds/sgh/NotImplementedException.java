package org.tds.sgh;

public class NotImplementedException extends RuntimeException
{
	private static final long serialVersionUID = -2925980211706573003L;

	public NotImplementedException()
	{
		super("La operaci�n no est� implementada.");
	}
	
	public NotImplementedException(String message)
	{
		super(message);
	}
}