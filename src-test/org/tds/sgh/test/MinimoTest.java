package org.tds.sgh.test;

import static org.junit.Assert.assertTrue;

import static org.tds.sgh.test.Constants.*;

import java.util.*;

import org.junit.*;

import org.tds.sgh.dto.*;
import org.tds.sgh.logic.*;

public class MinimoTest extends BaseTest
{
	// Attributes -------------------------------------------------------------
	
	private IAltaClienteController ac;
	
	private IAltaHotelController ah;
	
	private IHacerReservaController hr;
	
	private ITomarReservaController tr;
	
	
	// Setup ------------------------------------------------------------------
	
	@Before
	public void setUp() throws Exception
	{
		super.setUp();
		
		ac = ControllerFactory.AltaClienteController(ch);
		ah = ControllerFactory.AltaHotelController(ch);
		hr = ControllerFactory.HacerReservaController(ch);
		tr = ControllerFactory.TomarReservaController(ch);
		
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);
		ac.registrarCliente(nombreCliente2, telefonoCliente, emailCliente);
		
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarTipoHabitacion(nombreTipoHabitacion2);
		ah.registrarTipoHabitacion(nombreTipoHabitacion3);
		
		ah.registrarHotel(nombreHotel);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion2, nombreHabitacion2);
		
		ah.registrarHotel(nombreHotel2);
		ah.registrarHabitacion(nombreHotel2, nombreTipoHabitacion, nombreHabitacion);
		ah.registrarHabitacion(nombreHotel2, nombreTipoHabitacion3, nombreHabitacion3);
		ah.registrarHabitacion(nombreHotel2, nombreTipoHabitacion3, nombreHabitacion4);
	}

	@After
	public void tearDown() throws Exception
	{
		ac = null;
		ah = null;
		hr = null;
		tr = null;
	}
	
	
	// Tests ------------------------------------------------------------------
	
	@Test
	public void testHacerReservaEscenarioTipico()
	{
		System.out.println("\n\nHacerReservaEscenarioTipico");
		
		ClienteDto cliente = hr.seleccionarCliente(nombreCliente);
		assertTrue(cliente != null);
		assertTrue(cliente.getNombre().equals(nombreCliente));

		boolean disp = hr.confirmarDisponibilidad(nombreHotel, nombreTipoHabitacion, fecha1, fecha2);
		assertTrue(disp);
		
		ReservaDto reserva = hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha2, true);
		assertTrue(reserva != null);
		assertTrue(reserva.isPendiente());
		
		hr.confirmarReserva(reserva.getCodigo());
	}
	
	@Test
	public void testHacerReservaEscenarioAlternativo()
	{
		ReservaDto reserva1 = hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha2, true);
		assertTrue(reserva1 != null);
		assertTrue(reserva1.isPendiente());
		
		
		System.out.println("\n\nHacerReservaEscenarioAlternativo");
		
		ClienteDto cliente = hr.seleccionarCliente(nombreCliente2);
		assertTrue(cliente != null);
		assertTrue(cliente.getNombre().equals(nombreCliente2));

		boolean disp = hr.confirmarDisponibilidad(nombreHotel, nombreTipoHabitacion, fecha1, fecha2);
		assertTrue(!disp);

		List<HotelDto> hoteles = hr.sugerirAlternativas(nombreTipoHabitacion, fecha1, fecha2);
		assertTrue(hoteles != null);
		assertTrue(hoteles.size() == 1);
		assertTrue(hoteles.get(0).getNombre().equals(nombreHotel2));
		
		ReservaDto reserva2 = hr.registrarReserva(nombreCliente2, nombreHotel2, nombreTipoHabitacion, fecha1, fecha2, true);
		assertTrue(reserva2 != null);
		assertTrue(reserva2.isPendiente());

		hr.confirmarReserva(reserva2.getCodigo());
	}

	@Test
	public void testTomarReserva()
	{
		ReservaDto reserva1 = hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha2, true);
		assertTrue(reserva1 != null);
		assertTrue(reserva1.isPendiente());

		
		System.out.println("\n\nTomarReserva");
		
		List<ReservaDto> reservasNoTomadas = tr.buscarReservasNoTomadas(nombreHotel, fecha1);
		assertTrue(reservasNoTomadas != null);
		assertTrue(reservasNoTomadas.size() == 1);
		assertTrue(reservasNoTomadas.get(0).getCodigo() == reserva1.getCodigo());
		
		
		ReservaDto reserva = tr.seleccionarReserva(reservasNoTomadas.get(0).getCodigo());
		assertTrue(reserva != null);
		assertTrue(reserva.getCodigo() == reserva1.getCodigo());
		
		
		HuespedDto huesped = tr.registrarHuesped(reserva.getCodigo(), nombreHuesped, documentoHuesped);
		assertTrue(huesped != null);
		
		
		HabitacionDto habitacion = tr.tomarReserva(reserva.getCodigo());
		assertTrue(habitacion != null);
		assertTrue(habitacion.getNombre().equals(nombreHabitacion));
		
		
		tr.confirmarReserva(reserva.getCodigo());
	}
}
