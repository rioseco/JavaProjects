package org.tds.sgh.dto;

public class TipoHabitacionDto
{
	// Attributes -------------------------------------------------------------
	
	private String nombre;
	
	
	// Constructors -----------------------------------------------------------
	
	public TipoHabitacionDto(String nombre)
	{
		this.nombre = nombre;
	}
	
	
	// Properties -------------------------------------------------------------
	
	public String getNombre()
	{
		return nombre;
	}
}
