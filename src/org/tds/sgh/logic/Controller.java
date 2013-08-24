package org.tds.sgh.logic;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.tds.sgh.dto.ClienteDto;
import org.tds.sgh.dto.HabitacionDto;
import org.tds.sgh.dto.HotelDto;
import org.tds.sgh.dto.HuespedDto;
import org.tds.sgh.dto.ReservaDto;
import org.tds.sgh.dto.TipoHabitacionDto;



public class Controller implements IAltaClienteController, IAltaHotelController, ICadenaHotelera, ICancelarReservaController, ICliente, IConfirmarReservaController,
IDatosHotel, IDatosHuesped, IDatosReserva, IDatosTipoHabitacion, IEstadoReserva, IHabitacion, IHacerReservaController, IIdentificarClienteController, 
IIdentificarReservaClienteController, ITomarReservaController

{
	private CadenaHotelera ch;
	private Reserva reserva;

	@Override
	public List<ReservaDto> buscarReservasNoTomadas(String nombreHotel,
			GregorianCalendar fecha) {
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
	public List<ReservaDto> buscarReservasPendientes(String nombreCliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReservaDto seleccionarReserva(long codigoReserva) {
		
		return reserva.seleccionarReserva(codigoReserva);
	}

	@Override
	public List<ClienteDto> buscarCliente(String nombreRegex) {
		
		return ch.buscarCliente(nombreRegex);
	}

	@Override
	public ClienteDto seleccionarCliente(String nombre) {
				
		return ch.seleccionarCliente(nombre);
	}

	@Override
	public boolean confirmarDisponibilidad(String nombreHotel,
			String nombreTipoHabitacion, GregorianCalendar fechaInicio,
			GregorianCalendar fechaFin) {
		
		return ch.confirmarDisponibilidad(nombreHotel, nombreTipoHabitacion, fechaInicio, fechaFin);
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
	public long getCodigo() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public GregorianCalendar getFechaInicio() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GregorianCalendar getFechaFin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isModificablePorHuesped() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPendiente() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTomada() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCancelada() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getDocumento() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void confirmarReserva(long codigoReserva) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTelefono() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEMail() {
		
		//ch.getClientesDto().
		return null;
	}

	@Override
	public void cancelarReserva(long codigoReserva) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, ClienteDto> getClientesDto() {
		
		return ch.getClientesDto();
	}

	@Override
	public Map<String, HotelDto> getHotelesDto() {
		
		return ch.getHotelesDto();
	}

	@Override
	public Map<String, TipoHabitacionDto> getTiposHabitacionDto() {
		
		return ch.getTiposHabitacionDto();
	}

	@Override
	public Map<String, HabitacionDto> getHabitacionesDto(String nombreHotel) {
		
		return ch.getHabitacionesDto(nombreHotel);
	}

	@Override
	public Map<Long, ReservaDto> getReservasClienteDto(String nombreCliente) {
		
		return ch.getReservasClienteDto(nombreCliente);
	}

	@Override
	public Map<Long, ReservaDto> getReservasHotelDto(String nombreHotel) {
		
		return ch.getReservasHotelDto(nombreHotel);
	}

	@Override
	public HotelDto registrarHotel(String nombre) {
		
		return ch.registrarHotel(nombre);
	}

	@Override
	public TipoHabitacionDto registrarTipoHabitacion(String nombre) {
		
		return ch.registrarTipoHabitacion(nombre);
	}

	@Override
	public HabitacionDto registrarHabitacion(String hotel, String tipo,
			String nombre) {
		
		return ch.registrarHabitacion(hotel, tipo, nombre);
	}

	@Override
	public ClienteDto registrarCliente(String nombre, String telefono,
			String email) {
		
		return ch.registrarCliente(nombre, telefono, email);
	}

}
