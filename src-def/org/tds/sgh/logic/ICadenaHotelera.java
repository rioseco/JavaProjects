package org.tds.sgh.logic;

import java.util.*;

import org.tds.sgh.dto.*;

public interface ICadenaHotelera
{
	Map<String,ClienteDto> getClientesDto();

	Map<String,HotelDto> getHotelesDto();

	Map<String,TipoHabitacionDto> getTiposHabitacionDto();
	
	Map<String,HabitacionDto> getHabitacionesDto(String nombreHotel);
	
	Map<Long,ReservaDto> getReservasClienteDto(String nombreCliente);

	Map<Long,ReservaDto> getReservasHotelDto(String nombreHotel);
}
