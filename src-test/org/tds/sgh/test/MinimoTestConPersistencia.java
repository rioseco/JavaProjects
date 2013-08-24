package org.tds.sgh.test;

import static org.junit.Assert.assertTrue;

import static org.tds.sgh.test.Constants.*;

import java.util.*;

import org.junit.*;

import org.tds.sgh.dto.*;
import org.tds.sgh.infrastructure.*;
import org.tds.sgh.logic.*;

public class MinimoTestConPersistencia extends BaseTest
{
	// Attributes -------------------------------------------------------------

	private DataAccessConnection cnx;

	private IHacerReservaController hr;
	
	private ITomarReservaController tr;
	
	
	// Setup ------------------------------------------------------------------
	
	@Before
	public void setUp() throws Exception
	{		
		cnx = DataAccess.getInstance().createConnection();
		
		cnx.beginTx();

		super.setUp();
		
		hr = ControllerFactory.HacerReservaController(ch);
		tr = ControllerFactory.TomarReservaController(ch);
	}

	@After
	public void tearDown() throws Exception
	{
		cnx.commitTx();

		hr = null;
		tr = null;
		
		super.tearDown();
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
		ReservaDto reserva1 = ch.getReservasClienteDto(nombreCliente).values().iterator().next();
		assertTrue(reserva1 != null);
		
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

	
	// BaseTest ---------------------------------------------------------------
	
	@Override
	protected CadenaHotelera createCadenaHotelera()
	{
		CadenaHotelera CH = (CadenaHotelera)cnx.get("CadenaHotelera");
		
		if (CH == null)
		{
			CadenaHotelera newCH = new CadenaHotelera();
			
			newCH.registrarCliente(nombreCliente, telefonoCliente, emailCliente);
			newCH.registrarCliente(nombreCliente2, telefonoCliente, emailCliente);
			
			newCH.registrarTipoHabitacion(nombreTipoHabitacion);
			newCH.registrarTipoHabitacion(nombreTipoHabitacion2);
			newCH.registrarTipoHabitacion(nombreTipoHabitacion3);
			
			newCH.registrarHotel(nombreHotel);
			newCH.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
			newCH.registrarHabitacion(nombreHotel, nombreTipoHabitacion2, nombreHabitacion2);
			
			newCH.registrarHotel(nombreHotel2);
			newCH.registrarHabitacion(nombreHotel2, nombreTipoHabitacion, nombreHabitacion);
			newCH.registrarHabitacion(nombreHotel2, nombreTipoHabitacion3, nombreHabitacion3);
			newCH.registrarHabitacion(nombreHotel2, nombreTipoHabitacion3, nombreHabitacion4);
			
			cnx.save(newCH);
			
			cnx.commitTx();

			
			cnx.beginTx();
			
			CH = (CadenaHotelera)cnx.get("CadenaHotelera");
		}
		
		return CH; 
	}
}
