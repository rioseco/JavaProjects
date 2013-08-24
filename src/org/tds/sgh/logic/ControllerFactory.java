package org.tds.sgh.logic;

public class ControllerFactory
{
	public static IAltaClienteController AltaClienteController(CadenaHotelera ch)
	{
		return ch;
	}
	
	public static IAltaHotelController AltaHotelController(CadenaHotelera ch)
	{
		return ch;
	}
	
	public static IHacerReservaController HacerReservaController(CadenaHotelera ch)
	{
		// TODO
		throw new org.tds.sgh.NotImplementedException();
	}
	
	public static ITomarReservaController TomarReservaController(CadenaHotelera ch)
	{
		// TODO
		throw new org.tds.sgh.NotImplementedException();
	}

	public static ICancelarReservaController CancelarReservaController(CadenaHotelera ch)
	{
		// TODO
		throw new org.tds.sgh.NotImplementedException();
	}

	public static IIdentificarClienteController IdentificarClienteController(CadenaHotelera ch)
	{
		// TODO
		throw new org.tds.sgh.NotImplementedException();
	}

	public static IIdentificarReservaClienteController IdentificarReservaClienteController(CadenaHotelera ch)
	{
		// TODO
		throw new org.tds.sgh.NotImplementedException();
	}
}
