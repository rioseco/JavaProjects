package org.tds.sgh.infrastructure;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class DataAccessConnection
{
	// Attributes -------------------------------------------------------------
	
	private Session session;
	
	private Transaction transaction;
	
	
	// Constructor ------------------------------------------------------------

	public DataAccessConnection(Session session)
	{
		this.session = session;
	}
	
	
	// Operations -------------------------------------------------------------
	
	public void beginTx()
	{
		transaction = session.beginTransaction();				
	}
	
	public Object get(String clazz)
	{
		@SuppressWarnings("rawtypes")
		List objlist = session.createQuery("from " + clazz + " o").list();
		
		if (objlist.isEmpty())
		{
			return null;
		}
		else
		{
			return objlist.get(0);
		}
	}
	
	public void save(Object o)
	{
		session.save(o);
	}
	
	public void commitTx() 
	{
		transaction.commit();
	}
	
	public void rollbackTx()
	{
		transaction.rollback();
	}
	
	public void close()
	{
		session.close();
	}
}
