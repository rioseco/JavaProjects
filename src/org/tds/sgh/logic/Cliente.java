package org.tds.sgh.logic;

import org.tds.sgh.dto.*;

public class Cliente
{
	// Attributes -------------------------------------------------------------
	
	private String nombre;
	
	private String telefono;
	
	private String email;

	
	// Constructors -----------------------------------------------------------
	
	public Cliente(String nombre, String telefono, String email)
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

	
	// Operations -------------------------------------------------------------
	
	public ClienteDto toDto()
	{
		return new ClienteDto(nombre, telefono, email);
	}
}
