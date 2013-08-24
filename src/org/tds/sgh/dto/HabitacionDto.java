package org.tds.sgh.dto;

public class HabitacionDto
{
	// Attributes -------------------------------------------------------------
	
	private String nombre;
	
	private TipoHabitacionDto tipoHabitacionDto;
	
	
	// Constructors -----------------------------------------------------------
	
	public HabitacionDto(String nombre, TipoHabitacionDto tipoHabitacionDto)
	{
		this.nombre = nombre;
		this.tipoHabitacionDto = tipoHabitacionDto;
	}
	
	
	// Properties -------------------------------------------------------------
	
	public String getNombre()
	{
		return nombre;
	}
	
	public TipoHabitacionDto getTipoHabitacion()
	{
		return tipoHabitacionDto;
	}
}
