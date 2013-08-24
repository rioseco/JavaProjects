package org.tds.sgh.logic;

public class PreconditionException extends RuntimeException
{
	private static final long serialVersionUID = -329322383713618288L;

	public PreconditionException(String message)
	{
		super(message);
	}
}