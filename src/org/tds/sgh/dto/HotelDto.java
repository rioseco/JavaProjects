package org.tds.sgh.dto;

public class HotelDto
{
	// Attributes -------------------------------------------------------------
	
	private String nombre;
	
	
	// Constructors -----------------------------------------------------------
	
	public HotelDto(String nombre)
	{
		this.nombre = nombre;
	}
	
	
	// Properties -------------------------------------------------------------
	
	public String getNombre()
	{
		return nombre;
	}
}
