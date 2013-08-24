package org.tds.sgh.test;

import static org.junit.Assert.assertTrue;
import static org.tds.sgh.test.Constants.*;

import java.util.*;

import org.junit.*;
import org.tds.sgh.dto.*;
import org.tds.sgh.logic.*;

public class AltaHotelTest extends BaseTest
{
	// Attributes -------------------------------------------------------------
	
	private IAltaHotelController ah;

	
	// Setup ------------------------------------------------------------------
	
	@Before
	public void setUp() throws Exception
	{
		super.setUp();
		
		ah = ControllerFactory.AltaHotelController(ch);
	}

	@After
	public void tearDown() throws Exception
	{
		ah = null;
	}

	
	// Tests ------------------------------------------------------------------
	
	// Hotel ----------------------------------------------
	
	@Test
	public void testHotelesNoNull()
	{
		assertTrue("Hoteles es null", ch.getHotelesDto() != null);
	}

	@Test
	public void testNoHayHoteles()
	{
		assertTrue("Ya existen hoteles registrados", ch.getHotelesDto().isEmpty());
	}
	
	@Test
	public void testAgregarHoteles()
	{
		ah.registrarHotel(nombreHotel);
		
		Map<String,HotelDto> hoteles = ch.getHotelesDto();
		
		assertTrue("El hotel no fue registrado", !hoteles.isEmpty());
		
		HotelDto hotel = hoteles.get(nombreHotel);
		assertTrue("El hotel no fue resgistrado", hotel != null);
		
		assertTrue("El nombre del hotel es incorrecto", hotel.getNombre().equals(nombreHotel));
		
		assertTrue("Existen habitaciones en el hotel", ch.getHabitacionesDto(nombreHotel).isEmpty());
		
		Map<Long,ReservaDto> reservasHotel = ch.getReservasHotelDto(nombreHotel);
		
		assertTrue("Las reservas de un hotel no debe ser null", reservasHotel != null);
		
		assertTrue("Existen reservas en el hotel", reservasHotel.isEmpty());
	}
	
	@Test(expected=PreconditionException.class)
	public void testNoSePuedeAgregarDosHotelesConIgualNombre()
	{
		ah.registrarHotel(nombreHotel);
		ah.registrarHotel(nombreHotel);
	}
	
	
	// TipoHabitacion -------------------------------------

	@Test
	public void testTiposHabitacionNoNull()
	{
		assertTrue("TiposHabitacion es null", ch.getTiposHabitacionDto() != null);
	}

	@Test
	public void testNoHayTiposHabitacion()
	{
		assertTrue("Ya existen tipos de habitacion registrados", ch.getTiposHabitacionDto().isEmpty());
	}
	
	@Test
	public void testAgregarTipoHabitacion()
	{
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		
		Map<String,TipoHabitacionDto> tiposHabitacion = ch.getTiposHabitacionDto();
		
		assertTrue("El tipo de habitación no fue registrado", !tiposHabitacion.isEmpty());
		
		TipoHabitacionDto tipoHabitacion = tiposHabitacion.get(nombreTipoHabitacion);
		assertTrue("El tipo de habitación no fue resgistrado", tipoHabitacion != null);
		
		assertTrue("El nombre del tipo de habitación es incorrecto", tipoHabitacion.getNombre().equals(nombreTipoHabitacion));
	}

	@Test(expected=PreconditionException.class)
	public void testNoSePuedeAgregarDosTiposHabitacionConIgualNombre()
	{
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
	}

	
	// Habitacion -----------------------------------------

	@Test(expected=PreconditionException.class)
	public void testHabitacionRequireHotel()
	{
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
	}
	
	@Test(expected=PreconditionException.class)
	public void testHabitacionRequireTipoHabitacion()
	{
		ah.registrarHotel(nombreHotel);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
	}
	
	@Test
	public void testRegistrarHabitacion()
	{
		ah.registrarHotel(nombreHotel);
		
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		
		Map<String,HabitacionDto> habitaciones = ch.getHabitacionesDto(nombreHotel);
		
		assertTrue("La lista de habitaciones es null", habitaciones != null);
		
		assertTrue("La lista de habitaciones está vacía", !habitaciones.isEmpty());
		
		HabitacionDto habitacion = habitaciones.get(nombreHabitacion);
		
		assertTrue("La habitación no fue resgistrada en el hotel", habitacion != null);
		
		assertTrue("El nombre de la habitación es incorrecto", habitacion.getNombre().equals(nombreHabitacion));

		assertTrue("La habitación no tiene un tipo de habitación", habitacion.getTipoHabitacion() != null);
		
		assertTrue("El tipo de la habitación es incorrecto", habitacion.getTipoHabitacion().getNombre().equals(nombreTipoHabitacion));
	}
	
	@Test(expected=PreconditionException.class)
	public void testNoSePuedeAgregarDosHabitacionesConIgualNombre()
	{
		ah.registrarHotel(nombreHotel);
		ah.registrarTipoHabitacion(nombreTipoHabitacion);
		
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
		ah.registrarHabitacion(nombreHotel, nombreTipoHabitacion, nombreHabitacion);
	}
}
