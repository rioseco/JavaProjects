package org.tds.sgh.logic;

import java.util.GregorianCalendar;
import java.util.List;

import org.tds.sgh.dto.ClienteDto;
import org.tds.sgh.dto.HabitacionDto;
import org.tds.sgh.dto.HotelDto;
import org.tds.sgh.dto.HuespedDto;
import org.tds.sgh.dto.ReservaDto;

public class Reserva implements IHacerReservaController, ITomarReservaController, ICancelarReservaController, IIdentificarReservaClienteController
								

{

	@Override
	public ClienteDto registrarCliente(String nombre, String telefono,
			String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClienteDto> buscarCliente(String nombreRegex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClienteDto seleccionarCliente(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void confirmarReserva(long codigoReserva) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean confirmarDisponibilidad(String nombreHotel,
			String nombreTipoHabitacion, GregorianCalendar fechaInicio,
			GregorianCalendar fechaFin) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<HotelDto> sugerirAlternativas(String nombreTipoHabitacion,
			GregorianCalendar fechaInicio, GregorianCalendar fechaFin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReservaDto registrarReserva(String nombreCliente,
			String nombreHotel, String nombreTipoHabitacion,
			GregorianCalendar fechaInicio, GregorianCalendar fechaFin,
			boolean modificablePorHuesped) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReservaDto> buscarReservasPendientes(String nombreCliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReservaDto> buscarReservasNoTomadas(String nombreHotel,
			GregorianCalendar fecha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReservaDto seleccionarReserva(long codigoReserva) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HuespedDto registrarHuesped(long codigoReserva, String nombre,
			String documento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HabitacionDto tomarReserva(long codigoReserva) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancelarReserva(long codigoReserva) {
		// TODO Auto-generated method stub
		
	}

	
}
