package org.tds.sgh.test;

import org.junit.*;

import org.tds.sgh.logic.*;

public abstract class BaseTest
{
	// Attributes -------------------------------------------------------------
	
	protected CadenaHotelera ch = null;
	
	
	// Templates --------------------------------------------------------------
	
	protected CadenaHotelera createCadenaHotelera()
	{
		return new CadenaHotelera();
	}
	
	
	// Setup ------------------------------------------------------------------
	
	@Before
	public void setUp() throws Exception
	{
		ch = createCadenaHotelera();
	}

	@After
	public void tearDown() throws Exception
	{
		ch = null;
	}
}
