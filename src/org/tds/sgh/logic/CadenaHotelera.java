package org.tds.sgh.logic;

import java.util.*;

import org.tds.sgh.dto.*;

public class CadenaHotelera implements ICadenaHotelera,
                                       IAltaHotelController,
                                       IAltaClienteController,
                                       IIdentificarClienteController
{
	// Attributes -------------------------------------------------------------
	
	private Map<String,Cliente> clientes;
	
	private Map<String,Hotel> hoteles;
	
	private Map<String,TipoHabitacion> tiposHabitacion;
	
	private Hotel hotel;
	

	// Constructors -----------------------------------------------------------

	public CadenaHotelera()
	{
		this.clientes = new HashMap<String,Cliente>();
		this.hoteles = new HashMap<String,Hotel>();
		this.tiposHabitacion = new HashMap<String,TipoHabitacion>();
	}
	

	// ICadenaHotelera --------------------------------------------------------
	
	@Override
	public Map<String,ClienteDto> getClientesDto()
	{
		HashMap<String,ClienteDto> clientesDto = new HashMap<String,ClienteDto>();
		
		for (Cliente cliente : clientes.values()) 
		{
			clientesDto.put(cliente.getNombre(), cliente.toDto());
		}
		
		return clientesDto;
	}
	
	@Override
	public Map<String,HotelDto> getHotelesDto()
	{
		HashMap<String,HotelDto> hotelesDto = new HashMap<String,HotelDto>();
		
		for (Hotel hotel : hoteles.values()) 
		{
			hotelesDto.put(hotel.getNombre(), hotel.toDto());
		}
		
		return hotelesDto;
	}
	
	@Override
	public Map<String,TipoHabitacionDto> getTiposHabitacionDto()
	{
		HashMap<String,TipoHabitacionDto> tiposHabitacionDto = new HashMap<String,TipoHabitacionDto>();
		
		for (TipoHabitacion tipoHabitacion : tiposHabitacion.values()) 
		{
			tiposHabitacionDto.put(tipoHabitacion.getNombre(), tipoHabitacion.toDto());
		}
		
		return tiposHabitacionDto;
	}
	
	@Override
	public Map<String,HabitacionDto> getHabitacionesDto(String nombreHotel)
	{
		Precondition.contains(hoteles, nombreHotel, "El hotel '" + nombreHotel + "' no está registrado");
	
		HashMap<String,HabitacionDto> habitacionesDto = new HashMap<String,HabitacionDto>();
		
		for (Habitacion habitacion : hoteles.get(nombreHotel).getHabitaciones())
		{
			habitacionesDto.put(habitacion.getNombre(), habitacion.toDto());
		}
		
		return habitacionesDto;
	}

	@Override
	public Map<Long,ReservaDto> getReservasClienteDto(String nombreCliente)
	{
		//TODO
		throw new org.tds.sgh.NotImplementedException();
	}
	
	@Override
	public Map<Long,ReservaDto> getReservasHotelDto(String nombreHotel)
	{
		//TODO
		throw new org.tds.sgh.NotImplementedException();
	}

	
	// IAltaHotel -------------------------------------------------------------
	
	@Override
	public HotelDto registrarHotel(String nombre)
	{
		Precondition.notContain(hoteles, nombre, "Ya existe un hotel con el nombre '" + nombre + "'");
		
		Hotel h = new Hotel(nombre);
		hoteles.put(nombre, h);
		return h.toDto();
	}
	
	@Override
	public TipoHabitacionDto registrarTipoHabitacion(String nombre)
	{
		Precondition.notContain(tiposHabitacion, nombre, "Ya existe un tipo de habitación con el nombre '" + nombre + "'");
		
		TipoHabitacion tipoHabitacion = new TipoHabitacion(nombre);
		tiposHabitacion.put(nombre, tipoHabitacion);
		return tipoHabitacion.toDto();
	}
	
	@Override
	public HabitacionDto registrarHabitacion(String nombreHotel, String nombreTipoHabitacion, String nombre)
	{
		Precondition.contains(hoteles, nombreHotel, "No existe un hotel con el nombre '" + nombreHotel + "'");
		Precondition.contains(tiposHabitacion, nombreTipoHabitacion, "No existe un tipo de habitación con el nombre '" + nombreTipoHabitacion + "'");
		
		Hotel hotel = hoteles.get(nombreHotel);
		TipoHabitacion tipoHabitacion = tiposHabitacion.get(nombreTipoHabitacion);
		
		Habitacion habitacion = hotel.registrarHabitacion(tipoHabitacion, nombre);
		
		return habitacion.toDto();
	}
	

	// IAltaCliente -----------------------------------------------------------
	
	@Override
	public ClienteDto registrarCliente(String nombre, String telefono, String email)
	{
		Precondition.notContain(clientes, nombre, "Ya existe un cliente con el nombre '" + nombre + "'");
		
		Cliente cliente = new Cliente(nombre, telefono, email);
		clientes.put(nombre, cliente);
		return cliente.toDto();
	}


	@Override
	public List<ClienteDto> buscarCliente(String nombreRegex)
	{
		// TODO
		/*for (ClienteDto clientes : clientes.getNombre(nombreRegex))
		{
			
		}*/
		throw new org.tds.sgh.NotImplementedException();
	}


	@Override
	public ClienteDto seleccionarCliente(String nombre)
	{
		Precondition.contains(clientes, nombre, "El cliente indicado no existe");
		
		Cliente c = clientes.get(nombre);
		
		return c.toDto();
	}
	
	public boolean confirmarDisponibilidad(String nombreHotel,
			String nombreTipoHabitacion, GregorianCalendar fechaInicio,
			GregorianCalendar fechaFin) {
		
		return hotel.confirmarDisponibilidad(nombreHotel, nombreTipoHabitacion, fechaInicio, fechaFin);
	}
	
	
	
	
}
