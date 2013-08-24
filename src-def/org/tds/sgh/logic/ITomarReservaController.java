package org.tds.sgh.logic;

import java.util.*;

import org.tds.sgh.dto.*;

public interface ITomarReservaController extends IHacerReservaController,
                                                 IIdentificarReservaClienteController
{
	List<ReservaDto> buscarReservasNoTomadas(String nombreHotel, GregorianCalendar fecha);
	
	ReservaDto seleccionarReserva(long codigoReserva);
	
	HuespedDto registrarHuesped(long codigoReserva, String nombre, String documento);
	
	HabitacionDto tomarReserva(long codigoReserva);
}
