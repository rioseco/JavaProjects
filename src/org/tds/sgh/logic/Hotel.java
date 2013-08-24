package org.tds.sgh.logic;

import java.util.*;

import org.tds.sgh.dto.*;

public class Hotel
{
	// Attributes -------------------------------------------------------------
	
	private String nombre;
	
	private Map<String,Habitacion> habitaciones;
	
	
	// Constructors -----------------------------------------------------------
	
	public Hotel(String nombre)
	{
		this.nombre = nombre;
		
		this.habitaciones = new HashMap<String,Habitacion>();
	}
	
	
	// Properties -------------------------------------------------------------
	
	public String getNombre()
	{
		return nombre;
	}
	
	
	// Operations -------------------------------------------------------------
	
	public HotelDto toDto()
	{
		return new HotelDto(nombre);
	}
	
	public Habitacion registrarHabitacion(TipoHabitacion tipoHabitacion, String nombre)
	{
		Precondition.notContain(habitaciones, nombre, "En el hotel '" + this.nombre + "' ya existe una habitación con el nombre '" + nombre + "'");
		
		Habitacion habitacion = new Habitacion(tipoHabitacion, nombre);
		habitaciones.put(nombre, habitacion);
		return habitacion;
	}
	
	public Collection<Habitacion> getHabitaciones()
	{
		return habitaciones.values();
	}
	
	public boolean confirmarDisponibilidad(String nombreHotel,
			String nombreTipoHabitacion, GregorianCalendar fechaInicio,
			GregorianCalendar fechaFin) {
		// TODO Auto-generated method stub
		int x = 1;
		int y = 2;
		if(x>y){
			return true;	
		}
		
		else{
			return false;
		}
	}	
	
	
}
