package org.tds.sgh.test;

import static org.junit.Assert.assertTrue;

import static org.tds.sgh.test.Constants.*;

import java.util.*;

import org.junit.*;

import org.tds.sgh.dto.*;
import org.tds.sgh.logic.*;

public class CancelarReservaTest extends BaseTest
{
	// Attributes -------------------------------------------------------------
	
	private IAltaClienteController ac;
	
	private IAltaHotelController ah;
	
	private IHacerReservaController hr;

	private ITomarReservaController tr;
	
	private ICancelarReservaController cr;
	
	
	// Setup ------------------------------------------------------------------
	
	@Before
	public void setUp() throws Exception
	{
		super.setUp();
		
		ac = ControllerFactory.AltaClienteController(ch);
		ah = ControllerFactory.AltaHotelController(ch);
		hr = ControllerFactory.HacerReservaController(ch);
		tr = ControllerFactory.TomarReservaController(ch);
		cr = ControllerFactory.CancelarReservaController(ch);
	}

	@After
	public void tearDown() throws Exception
	{
		ac = null;
		ah = null;
		hr = null;
		tr = null;
		cr = null;
	}

	
	// Tests ------------------------------------------------------------------

	@Test(expected=PreconditionException.class)
	public void testCodigoReservaInvalido()
	{
		cr.cancelarReserva(0);
	}
	
	@Test(expected=PreconditionException.class)
	public void testReservaNoEstaPendiente()
	{
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHotel(nombreHotel);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		
		ReservaDto reserva = hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha2, true);

		tr.registrarHuesped(reserva.getCodigo(), nombreHuesped, documentoHuesped);
		
		tr.tomarReserva(reserva.getCodigo());
		
		cr.cancelarReserva(reserva.getCodigo());
	}

	@Test
	public void testReservaCancelada()
	{
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);
		ah.registrarHotel(nombreHotel);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		
		ReservaDto reserva = hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha2, true);

		cr.cancelarReserva(reserva.getCodigo());
		
		Map<Long,ReservaDto> reservas = ch.getReservasClienteDto(nombreCliente);
		assertTrue(!reservas.isEmpty());
		assertTrue(reservas.get(reserva.getCodigo()) != null);
		assertTrue(reservas.get(reserva.getCodigo()).isCancelada());
	}
	
	@Test(expected=PreconditionException.class)
	public void testNoSePuedeCancelarDosVeces()
	{
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);
		ah.registrarHotel(nombreHotel);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		
		ReservaDto reserva = hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha2, fecha1, true);

		cr.cancelarReserva(reserva.getCodigo());
		
		cr.cancelarReserva(reserva.getCodigo());
	}
}
