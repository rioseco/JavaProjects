package org.tds.sgh.dto;

public class ClienteDto
{
	// Attributes -------------------------------------------------------------
	
	private String nombre;
	
	private String telefono;
	
	private String email;
	
	
	// Constructors -----------------------------------------------------------
	
	public ClienteDto(String nombre, String telefono, String email)
	{
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
	}
	
	
	// Properties -------------------------------------------------------------
	
	public String getNombre()
	{
		return nombre;
	}

	public String getTelefono()
	{
		return telefono;
	}

	public String getEMail()
	{
		return email;
	}
}
