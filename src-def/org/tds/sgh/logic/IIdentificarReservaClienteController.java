package org.tds.sgh.logic;

import java.util.*;

import org.tds.sgh.dto.*;

public interface IIdentificarReservaClienteController
{
	List<ReservaDto> buscarReservasPendientes(String nombreCliente);
	
	ReservaDto seleccionarReserva(long codigoReserva);
}
