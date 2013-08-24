package org.tds.sgh.logic;

import java.util.*;

import org.tds.sgh.dto.*;

public interface IIdentificarClienteController
{
	List<ClienteDto> buscarCliente(String nombreRegex);
	
	ClienteDto seleccionarCliente(String nombre);
}
