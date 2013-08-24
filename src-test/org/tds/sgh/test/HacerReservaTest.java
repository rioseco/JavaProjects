package org.tds.sgh.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static org.tds.sgh.test.Constants.*;

import java.util.*;

import org.junit.*;

import org.tds.sgh.dto.*;
import org.tds.sgh.logic.*;

public class HacerReservaTest extends BaseTest
{
	// Attributes -------------------------------------------------------------
	
	private IAltaClienteController ac;
	
	private IAltaHotelController ah;
	
	private IHacerReservaController hr;
	
	
	// Setup ------------------------------------------------------------------
	
	@Before
	public void setUp() throws Exception
	{
		super.setUp();
		
		ac = ControllerFactory.AltaClienteController(ch);
		ah = ControllerFactory.AltaHotelController(ch);
		hr = ControllerFactory.HacerReservaController(ch);
	}

	@After
	public void tearDown() throws Exception
	{
		ac = null;
		ah = null;
		hr = null;
	}
	
	
	// Tests ------------------------------------------------------------------

	// confirmarDisponibilidad

	@Test(expected=PreconditionException.class)
	public void testConfirmarDisponibilidadHotelInvalido()
	{
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		hr.confirmarDisponibilidad(nombreHotel, nombreTipoHabitacion, fecha1, fecha2);
	}

	@Test(expected=PreconditionException.class)
	public void testConfimarDisponibilidadTipoHabitacionInvalido()
	{
		ah.registrarHotel(nombreHotel);
		hr.confirmarDisponibilidad(nombreHotel, nombreTipoHabitacion, fecha1, fecha2);
	}

	@Test(expected=PreconditionException.class)
	public void testConfirmarDisponibilidadFechasInvalidas()
	{
		ah.registrarHotel(nombreHotel);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		hr.confirmarDisponibilidad(nombreHotel, nombreTipoHabitacion, fecha2, fecha1);
	}
	
	@Test
	public void testHayDisponibilidadSoloUnaHabitacion()
	{
		ah.registrarHotel(nombreHotel);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		
		assertTrue(hr.confirmarDisponibilidad(nombreHotel, nombreTipoHabitacion, fecha1, fecha2));
	}
	
	@Test
	public void testHayDisponibilidadDosHabitaciones()
	{
		ah.registrarHotel(nombreHotel);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion2);
		
		assertTrue(hr.confirmarDisponibilidad(nombreHotel, nombreTipoHabitacion, fecha1, fecha2));
	}
	
	@Test
	public void testNoHayDisponibilidadParaTipoSinHabitaciones()
	{
		ah.registrarHotel(nombreHotel);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarTipoHabitacion(nombreTipoHabitacion2);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		
		assertFalse(hr.confirmarDisponibilidad(nombreHotel, nombreTipoHabitacion2, fecha1, fecha2));
	}
	

	// registrarReserva

	@Test(expected=PreconditionException.class)
	public void testRegistrarReservaClienteInvalido()
	{
		ah.registrarHotel(nombreHotel);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha2, true);
	}

	@Test(expected=PreconditionException.class)
	public void testRegistrarReservaHotelInvalido()
	{
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha2, true);
	}

	@Test(expected=PreconditionException.class)
	public void testRegistrarReservaTipoHabitacionInvalido()
	{
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);
		ah.registrarHotel(nombreHotel);
		hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha2, true);
	}

	@Test(expected=PreconditionException.class)
	public void testRegistrarReservaFechasInvalidas()
	{
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);
		ah.registrarHotel(nombreHotel);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha2, fecha1, true);
	}
	
	@Test
	public void testRegistrarReserva()
	{
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);
		ah.registrarHotel(nombreHotel);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		
		ReservaDto reserva = hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha3, true);

		
		assertTrue(reserva.isPendiente());
		assertTrue(reserva.getTipoHabitacion().getNombre().equals(nombreTipoHabitacion));
		assertTrue(reserva.getHabitacion() == null);
		assertTrue(reserva.getFechaInicio().equals(fecha1));
		assertTrue(reserva.getFechaFin().equals(fecha3));
		assertTrue(reserva.isModificablePorHuesped());

		Map<Long,ReservaDto> reservas = ch.getReservasClienteDto(nombreCliente);
		assertTrue(reservas.size() == 1);
		assertTrue(reservas.get(reserva.getCodigo()) != null);
		
		reservas = ch.getReservasHotelDto(nombreHotel);
		assertTrue(reservas.size() == 1);
		assertTrue(reservas.get(reserva.getCodigo()) != null);
	}
	
	@Test(expected=PreconditionException.class)
	public void testRegistrarReservaSinDisponibilidadInvalido()
	{
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);
		ah.registrarHotel(nombreHotel);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha3, true);
		hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha2, fecha3, true);
	}

	@Test(expected=PreconditionException.class)
	public void testRegistrarReservaSinDisponibilidadAunqueExistanAlternativasInvalido()
	{
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);
		ah.registrarHotel(nombreHotel);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);

		ah.registrarHotel(nombreHotel2);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHabitacion(nombreHotel2, nombreTipoHabitacion, nombreHabitacion2);

		hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha2, true);
		hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha2, fecha3, true);
	}

	
	// sugerirAlternativas
	
	@Test(expected=PreconditionException.class)
	public void testSugerirAlternativasTipoHabitacionInvalido()
	{
		hr.sugerirAlternativas(nombreTipoHabitacion, fecha1, fecha2);
	}

	@Test(expected=PreconditionException.class)
	public void testFechasInvalidas()
	{
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		hr.sugerirAlternativas(nombreTipoHabitacion, fecha2, fecha1);
	}

	@Test
	public void testSugerirUno()
	{
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHotel(nombreHotel);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		ah.registrarHotel(nombreHotel2);
		ah.registrarHabitacion(nombreHotel2, nombreTipoHabitacion, nombreHabitacion2);
		
		hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha3, true);
		
		List<HotelDto> hoteles = hr.sugerirAlternativas(nombreTipoHabitacion, fecha1, fecha2);
		
		assertTrue(hoteles.size() == 1);
		assertTrue(hoteles.get(0).getNombre().equals(nombreHotel2));
	}

	@Test
	public void testSugerirUnoConDobleDisponibilidad()
	{
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHotel(nombreHotel);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		ah.registrarHotel(nombreHotel2);
		ah.registrarHabitacion(nombreHotel2, nombreTipoHabitacion, nombreHabitacion2);
		ah.registrarHabitacion(nombreHotel2, nombreTipoHabitacion, nombreHabitacion3);
		
		hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha3, true);
		hr.registrarReserva(nombreCliente, nombreHotel2, nombreTipoHabitacion, fecha2, fecha3, true);
		
		List<HotelDto> hoteles = hr.sugerirAlternativas(nombreTipoHabitacion, fecha2, fecha3);
		
		assertTrue(hoteles.size() == 1);
		assertTrue(hoteles.get(0).getNombre().equals(nombreHotel2));
	}

	@Test
	public void testSugerirDos()
	{
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);

		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarTipoHabitacion(nombreTipoHabitacion2);
		
		ah.registrarHotel(nombreHotel);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion2, nombreHabitacion2);

		ah.registrarHotel(nombreHotel2);
		ah.registrarHabitacion(nombreHotel2, nombreTipoHabitacion, nombreHabitacion2);
		ah.registrarHabitacion(nombreHotel2, nombreTipoHabitacion, nombreHabitacion3);

		ah.registrarHotel(nombreHotel3);
		ah.registrarHabitacion(nombreHotel3, nombreTipoHabitacion, nombreHabitacion3);

		hr.registrarReserva(nombreCliente, nombreHotel, nombreTipoHabitacion, fecha1, fecha3, true);
		hr.registrarReserva(nombreCliente, nombreHotel2, nombreTipoHabitacion, fecha2, fecha3, true);
		
		List<HotelDto> hoteles = hr.sugerirAlternativas(nombreTipoHabitacion, fecha2, fecha3);
		
		assertTrue(hoteles.size() == 2);
		for (HotelDto hotel : hoteles)
		{
			assertTrue(hotel.getNombre().equals(nombreHotel2) || hotel.getNombre().equals(nombreHotel3));
		}
	}
}
