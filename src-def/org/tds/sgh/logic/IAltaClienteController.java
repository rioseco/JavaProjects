package org.tds.sgh.logic;

import org.tds.sgh.dto.*;

public interface IAltaClienteController
{
	ClienteDto registrarCliente(String nombre, String telefono, String email);
}
