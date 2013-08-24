package org.tds.sgh.test;

import static org.junit.Assert.assertTrue;

import static org.tds.sgh.test.Constants.*;

import java.util.*;

import org.junit.*;

import org.tds.sgh.dto.*;
import org.tds.sgh.logic.*;

public class TomarReservaTest extends BaseTest
{
	// Constantes -------------------------------------------------------------
/*
	private static final String nombreCliente = "Roberto Mart�nez";
	
	private static final String telefono = "555 5555";
	
	private static final String email = "roberto.martinez@mail.com";
	
	private static final String nombreHotel = "Orient";
	
	private static final String nombreTipoHabitacion = "Queen";

	private static final String nombreHabitacion = "33";

	private static final String nombreHabitacion2 = "37";

	private static final GregorianCalendar fecha1 = new GregorianCalendar(2020, 10, 17);
	
	private static final GregorianCalendar fecha2 = new GregorianCalendar(2020, 10, 27);

	private static final String nombreHuesped = "Gonzalo Gonz�lez";
	
	private static final String documentoHuesped = "55.555.555-5";
	*/

	// Attributes -------------------------------------------------------------
	
	private IAltaClienteController ac;
	
	private IAltaHotelController ah;
	
	private IHacerReservaController hr;

	private ICancelarReservaController cr;
	
	private ITomarReservaController tr;
	
	
	// Setup ------------------------------------------------------------------
	
	@Before
	public void setUp() throws Exception
	{
		super.setUp();
		
		ac = ControllerFactory.AltaClienteController(ch);
		ah = ControllerFactory.AltaHotelController(ch);
		hr = ControllerFactory.HacerReservaController(ch);
		cr = ControllerFactory.CancelarReservaController(ch);
		tr = ControllerFactory.TomarReservaController(ch);
	}

	@After
	public void tearDown() throws Exception
	{
		ac = null;
		ah = null;
		hr = null;
		cr = null;
		tr = null;
	}

	
	// Tests ------------------------------------------------------------------

	// buscarReservasNoTomadas ----------------------------
	
	@Test(expected=PreconditionException.class)
	public void testHotelReservaBuscadaInvalido()
	{
		tr.buscarReservasNoTomadas(nombreHotel, fecha1);
	}
	
	@Test
	public void textNoExistenReservasNoTomadas()
	{
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);
		ah.registrarHotel(nombreHotel);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		
		List<ReservaDto> reservas = tr.buscarReservasPendientes(nombreCliente);
		assertTrue(reservas.isEmpty());
	}

	@Test
	public void testExisteReservaNoTomada()
	{
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);
		ah.registrarHotel(nombreHotel);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		
		ReservaDto reserva = hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha2, true);
		
		List<ReservaDto> reservas = tr.buscarReservasPendientes(nombreCliente);
		assertTrue(reservas.size() == 1);
		assertTrue(reservas.get(0).getCodigo() == reserva.getCodigo());
	}

	
	// seleccionarReserva
	
	@Test
	public void testSeleccionarReserva()
	{
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);
		ah.registrarHotel(nombreHotel);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		
		hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha2, true);
		
		List<ReservaDto> reservas = tr.buscarReservasPendientes(nombreCliente);
		
		ReservaDto reserva = tr.seleccionarReserva(reservas.get(0).getCodigo());
		assertTrue(reserva != null);
		assertTrue(reserva.getCodigo() == reservas.get(0).getCodigo());
	}

	@Test(expected=PreconditionException.class)
	public void testSeleccionarReservaInexistente()
	{
		tr.seleccionarReserva(0);
	}
	
	
	// registrarHuesped
	
	@Test
	public void testHuespedCorrecto()
	{
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);
		ah.registrarHotel(nombreHotel);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		
		hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha2, true);

		long codigoReserva = tr.buscarReservasNoTomadas(nombreHotel, fecha1).get(0).getCodigo();
		
		HuespedDto huesped = tr.registrarHuesped(codigoReserva, nombreHuesped, documentoHuesped);
		assertTrue(huesped != null);
		assertTrue(huesped.getNombre().equals(nombreHuesped));
		assertTrue(huesped.getDocumento().equals(documentoHuesped));
	}
	
	
	// tomarReserva
	
	@Test(expected=PreconditionException.class)
	public void testNoSePuedeTomarReservaCancelada()
	{
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);
		ah.registrarHotel(nombreHotel);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		
		ReservaDto reserva = hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha2, true);
		
		cr.cancelarReserva(reserva.getCodigo());
		
		tr.registrarHuesped(reserva.getCodigo(), nombreHuesped, documentoHuesped);
		tr.tomarReserva(reserva.getCodigo());
	}
	
	@Test(expected=PreconditionException.class)
	public void testNoSePuedeTomarReservaEnCurso()
	{
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);
		ah.registrarHotel(nombreHotel);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		
		ReservaDto reserva = hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha2, true);
		
		tr.registrarHuesped(reserva.getCodigo(), nombreHuesped, documentoHuesped);
		
		tr.tomarReserva(reserva.getCodigo());
		
		tr.tomarReserva(reserva.getCodigo());
	}
	
	@Test(expected=PreconditionException.class)
	public void testNoSePuedeTomarReservaSinHuesped()
	{
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);
		ah.registrarHotel(nombreHotel);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		
		ReservaDto reserva = hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha2, true);
		
		tr.tomarReserva(reserva.getCodigo());
	}
	
	@Test
	public void testReservaEstadoFinalCorrecto()
	{
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);
		ah.registrarHotel(nombreHotel);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		
		ReservaDto reserva = hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha2, true);
		
		tr.registrarHuesped(reserva.getCodigo(), nombreHuesped, documentoHuesped);
		
		tr.tomarReserva(reserva.getCodigo());
		
		assertTrue(tr.seleccionarReserva(reserva.getCodigo()).isTomada());
	}
	
	@Test
	public void testHabitacionAsignadaCorrecta()
	{
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);

		ah.registrarTipoHabitacion(nombreTipoHabitacion);

		ah.registrarHotel(nombreHotel);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion2);
		
		ReservaDto reserva1 = hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha2, true);

		ReservaDto reserva2 = hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha2, true);

		tr.registrarHuesped(reserva1.getCodigo(), nombreHuesped, documentoHuesped);
		HabitacionDto habitacion1 = tr.tomarReserva(reserva1.getCodigo());
		assertTrue(habitacion1.getNombre().equals(nombreHabitacion) || habitacion1.getNombre().equals(nombreHabitacion2));

		tr.registrarHuesped(reserva2.getCodigo(), nombreHuesped, documentoHuesped);
		HabitacionDto habitacion2 = tr.tomarReserva(reserva2.getCodigo());
		assertTrue(habitacion2.getNombre().equals(nombreHabitacion) || habitacion2.getNombre().equals(nombreHabitacion2));
		
		
		assertTrue(habitacion1.getTipoHabitacion().getNombre().equals(nombreTipoHabitacion));
		assertTrue(habitacion2.getTipoHabitacion().getNombre().equals(nombreTipoHabitacion));
	}
}
