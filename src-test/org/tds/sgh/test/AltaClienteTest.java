package org.tds.sgh.test;

import static org.junit.Assert.assertTrue;

import static org.tds.sgh.test.Constants.*;

import java.util.*;

import org.junit.*;

import org.tds.sgh.dto.*;
import org.tds.sgh.logic.*;

public class AltaClienteTest extends BaseTest
{
	// Attributes -------------------------------------------------------------
	
	private IAltaClienteController ac;

	
	// Setup ------------------------------------------------------------------
	
	@Before
	public void setUp() throws Exception
	{
		super.setUp();
		
		ac = ControllerFactory.AltaClienteController(ch);
	}

	@After
	public void tearDown() throws Exception
	{
		ac = null;
	}

	
	// Tests ------------------------------------------------------------------
	
	@Test
	public void testClientesNoNull()
	{
		assertTrue("Clientes es null", ch.getClientesDto() != null);
	}
	
	@Test
	public void testNoHayClientes()
	{
		assertTrue("Ya existen clientes registrados", ch.getClientesDto().isEmpty());
	}
	
	@Test
	public void testAgregarCliente()
	{
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);
		
		Map<String,ClienteDto> clientes = ch.getClientesDto();
		
		assertTrue("El cliente no fue registrado", !clientes.isEmpty());
		
		ClienteDto cliente = clientes.get(nombreCliente);
		assertTrue("El cliente no fue resgistrado", cliente != null);
		
		assertTrue("El nombre del cliente es incorrecto", cliente.getNombre().equals(nombreCliente));
		assertTrue("El teléfono del cliente es incorrecto", cliente.getTelefono().equals(telefonoCliente));
		assertTrue("El email del cliente es incorrecto", cliente.getEMail().equals(emailCliente));
	}
	
	@Test
	public void testClienteNuevoSinReservas()
	{
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);
		
		Map<Long,ReservaDto> reservasCliente = ch.getReservasClienteDto(nombreCliente);
		
		assertTrue("Las reservas de un cliente no debe ser null", reservasCliente != null);
		
		assertTrue("Existen reservas para el cliente", reservasCliente.isEmpty());
	}
	
	@Test(expected=PreconditionException.class)
	public void testNoSePuedeAgregarDosClientesConIgualNombre()
	{
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);
	}
}
