package org.tds.sgh.logic;

import org.tds.sgh.dto.*;

public class TipoHabitacion
{
	// Attributes -------------------------------------------------------------
	
	private String nombre;
	
	
	// Constructors -----------------------------------------------------------
	
	public TipoHabitacion(String nombre)
	{
		this.nombre = nombre;
	}
	
	
	// Properties -------------------------------------------------------------
	
	public String getNombre()
	{
		return nombre;
	}
	
	
	// Operations -------------------------------------------------------------
	
	TipoHabitacionDto toDto()
	{
		return new TipoHabitacionDto(nombre);
	}
}
