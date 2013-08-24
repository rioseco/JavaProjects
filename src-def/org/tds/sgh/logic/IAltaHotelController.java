package org.tds.sgh.logic;

import org.tds.sgh.dto.*;

public interface IAltaHotelController
{
	HotelDto registrarHotel(String nombre);
	
	TipoHabitacionDto registrarTipoHabitacion(String nombre);
	
	HabitacionDto registrarHabitacion(String hotel, String tipo, String nombre);
}
