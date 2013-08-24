package org.tds.sgh.logic;

import org.tds.sgh.dto.*;

public class Habitacion
{
	// Attributes -------------------------------------------------------------
	
	private String nombre;
	
	private TipoHabitacion tipoHabitacion;
	
	
	// Constructors -----------------------------------------------------------
	
	public Habitacion(TipoHabitacion tipoHabitacion, String nombre)
	{
		this.nombre = nombre;
		this.tipoHabitacion = tipoHabitacion;
	}
	

	// Properties -------------------------------------------------------------

	public String getNombre()
	{
		return nombre;
	}

	TipoHabitacion getTipoHabitacion()
	{
		return tipoHabitacion;
	}
	
	
	// Operations -------------------------------------------------------------
	
	public HabitacionDto toDto()
	{
		return new HabitacionDto(nombre, tipoHabitacion.toDto());
	}
}
