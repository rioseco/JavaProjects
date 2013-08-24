package org.tds.sgh.test;

import static org.junit.Assert.assertTrue;

import static org.tds.sgh.test.Constants.*;

import java.util.*;

import org.junit.*;

import org.tds.sgh.dto.*;
import org.tds.sgh.logic.*;

public class IdentificarClienteTest extends BaseTest
{
	// Attributes -------------------------------------------------------------
	
	private IAltaClienteController ac;
	
	private IIdentificarClienteController ic;

	
	// Setup ------------------------------------------------------------------
	
	@Before
	public void setUp() throws Exception
	{
		super.setUp();
		
		ac = ControllerFactory.AltaClienteController(ch);
		ic = ControllerFactory.IdentificarClienteController(ch);
	}

	@After
	public void tearDown() throws Exception
	{
		ac = null;
		ic = null;
	}

	
	// Tests ------------------------------------------------------------------

	// buscarClientes -------------------------------------
	
	@Test
	public void testBuscarExacto()
	{
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);
		
		List<ClienteDto> clientes = ic.buscarCliente(nombreCliente);
		
		assertTrue(clientes.size() == 1);
		assertTrue(clientes.get(0).getNombre().equals(nombreCliente));
		assertTrue(clientes.get(0).getTelefono().equals(telefonoCliente));
		assertTrue(clientes.get(0).getEMail().equals(emailCliente));
	}
	
	@Test
	public void testBuscarExistente()
	{
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);
		
		List<ClienteDto> clientes = ic.buscarCliente(nombreClienteRegexMatch1);
		
		assertTrue(clientes.size() == 1);
		assertTrue(clientes.get(0).getNombre().equals(nombreCliente));
	}
	
	@Test
	public void testBuscarExistentes()
	{
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);
		ac.registrarCliente(nombreCliente2, telefonoCliente, emailCliente);
		
		List<ClienteDto> clientes = ic.buscarCliente(nombreClienteRegexMatch2);
		
		assertTrue(clientes.size() == 2);
		assertTrue(clientes.get(0).getNombre().equals(nombreCliente)  || clientes.get(1).getNombre().equals(nombreCliente));
		assertTrue(clientes.get(0).getNombre().equals(nombreCliente2) || clientes.get(1).getNombre().equals(nombreCliente2));
	}

	@Test
	public void testBuscarInexistenteEnVacio()
	{
		List<ClienteDto> clientes = ic.buscarCliente(nombreClienteRegexInexistente);
		assertTrue(clientes.size() == 0);
	}

	@Test
	public void testBuscarInexistente()
	{
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);
		ac.registrarCliente(nombreCliente2, telefonoCliente, emailCliente);
		
		List<ClienteDto> clientes = ic.buscarCliente(nombreClienteRegexInexistente);
		
		assertTrue(clientes.size() == 0);
	}

	
	// seleccionarCliente ---------------------------------

	@Test
	public void testSeleccionarExistente()
	{
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);
		
		ClienteDto cliente = ic.seleccionarCliente(nombreCliente);
		
		assertTrue(cliente.getNombre().equals(nombreCliente));
		assertTrue(cliente.getTelefono().equals(telefonoCliente));
		assertTrue(cliente.getEMail().equals(emailCliente));
	}

	@Test
	public void testSeleccionarExistenteEnDos()
	{
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);
		ac.registrarCliente(nombreCliente2, telefonoCliente, emailCliente);
		
		ClienteDto cliente = ic.seleccionarCliente(nombreCliente);
		
		assertTrue(cliente.getNombre().equals(nombreCliente));
		assertTrue(cliente.getTelefono().equals(telefonoCliente));
		assertTrue(cliente.getEMail().equals(emailCliente));

	
		cliente = ic.seleccionarCliente(nombreCliente2);
		
		assertTrue(cliente.getNombre().equals(nombreCliente2));
		assertTrue(cliente.getTelefono().equals(telefonoCliente));
		assertTrue(cliente.getEMail().equals(emailCliente));
	}

	@Test(expected=PreconditionException.class)
	public void testSeleccionarInexistenteEnVacio()
	{
		ic.seleccionarCliente(nombreCliente);
	}

	@Test(expected=PreconditionException.class)
	public void testSeleccionarInexistente()
	{
		ac.registrarCliente(nombreCliente, telefonoCliente, emailCliente);

		ic.seleccionarCliente(nombreCliente2);
	}
}
