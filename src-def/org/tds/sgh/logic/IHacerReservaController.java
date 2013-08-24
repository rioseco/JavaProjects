package org.tds.sgh.logic;

import java.util.*;

import org.tds.sgh.dto.*;

public interface IHacerReservaController extends IAltaClienteController,
                                                 IIdentificarClienteController,
                                                 IConfirmarReservaController
{
	boolean confirmarDisponibilidad(String nombreHotel, String nombreTipoHabitacion, GregorianCalendar fechaInicio, GregorianCalendar fechaFin);
	
	List<HotelDto> sugerirAlternativas(String nombreTipoHabitacion, GregorianCalendar fechaInicio, GregorianCalendar fechaFin);
	
	ReservaDto registrarReserva(String nombreCliente, String nombreHotel, String nombreTipoHabitacion, GregorianCalendar fechaInicio, GregorianCalendar fechaFin, boolean modificablePorHuesped);
}
