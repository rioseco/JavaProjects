package org.tds.sgh.test;

import static org.junit.Assert.assertTrue;

import static org.tds.sgh.test.Constants.*;

import java.util.*;

import org.junit.*;

import org.tds.sgh.dto.*;
import org.tds.sgh.logic.*;

public class IdentificarReservaClienteTest extends BaseTest
{
	/*
	
	@SuppressWarnings("unused")
	private static final String nombreHotel3 = "Ajedrezzi";

	private static final String nombreTipoHabitacion = "Queen";

	@SuppressWarnings("unused")
	private static final String nombreTipoHabitacion2 = "Kind";

	
	
	
	@SuppressWarnings("unused")
	private static final String nombreHabitacion3 = "73";

	*/
	

	// Attributes -------------------------------------------------------------
	
	private IAltaClienteController ac;
	
	private IAltaHotelController ah;
	
	private IHacerReservaController hr;
	
	private IIdentificarReservaClienteController irc;
	
	
	// Setup ------------------------------------------------------------------
	
	@Before
	public void setUp() throws Exception
	{
		super.setUp();
		
		ac = ControllerFactory.AltaClienteController(ch);
		ah = ControllerFactory.AltaHotelController(ch);
		hr = ControllerFactory.HacerReservaController(ch);
		irc = ControllerFactory.IdentificarReservaClienteController(ch);
	}

	@After
	public void tearDown() throws Exception
	{
		ac = null;
		ah = null;
		hr = null;
		irc = null;
	}
	
	
	// Tests ------------------------------------------------------------------

	// buscarReservasActivas
	
	@Test(expected=PreconditionException.class)
	public void testClienteInexistente()
	{
		irc.buscarReservasPendientes(nombreCliente);
	}
	
	@Test
	public void testClienteSinReservasNoTieneActivas()
	{
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);
		
		assertTrue(irc.buscarReservasPendientes(nombreCliente).isEmpty());
	}

	@Test
	public void testClienteConUnaNuevaReservaTieneActiva()
	{
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);
		
		ah.registrarTipoHabitacion(nombreTipoHabitacion);

		ah.registrarHotel(nombreHotel);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		
		ReservaDto reserva = hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha2, true);

		List<ReservaDto> reservas = irc.buscarReservasPendientes(nombreCliente);
		assertTrue(reservas.size() == 1);
		assertTrue(reservas.get(0).getCodigo() == reserva.getCodigo());
	}
	
	public void testClienteConDosReservasNuevas()
	{
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);
		
		ah.registrarTipoHabitacion(nombreTipoHabitacion);

		ah.registrarHotel(nombreHotel);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		
		ah.registrarHotel(nombreHotel2);
		ah.registrarHabitacion(nombreHotel2, nombreTipoHabitacion, nombreHabitacion2);

		ReservaDto reserva1 = hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha2, true);
		ReservaDto reserva2 = hr.registrarReserva(nombreCliente, nombreHotel2, nombreTipoHabitacion, fecha2, fecha3, true);

		List<ReservaDto> reservas = irc.buscarReservasPendientes(nombreCliente);
		assertTrue(reservas.size() == 2);
		assertTrue(reservas.get(0).getCodigo() == reserva1.getCodigo() || reservas.get(0).getCodigo() == reserva2.getCodigo());
		assertTrue(reservas.get(1).getCodigo() == reserva1.getCodigo() || reservas.get(1).getCodigo() == reserva2.getCodigo());
	}
	

	// seleccionarReserva ---------------------------------
	
	@Test(expected=PreconditionException.class)
	public void testCodigoReservaInvalido()
	{
		irc.seleccionarReserva(0);
	}

	@Test
	public void testRegistrarReservaHotelInvalido()
	{
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);
		
		ah.registrarTipoHabitacion(nombreTipoHabitacion);

		ah.registrarHotel(nombreHotel);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		
		ReservaDto reserva = hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha2, true);

		ReservaDto reservaSeleccionada = irc.seleccionarReserva(reserva.getCodigo());
		
		assertTrue(reservaSeleccionada != null);
	}
}
