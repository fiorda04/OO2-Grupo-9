package test;

import java.util.List;

import datos.Ticket;
import datos.Usuario;
import negocio.TicketABM;
import negocio.UsuarioABM;

public class TestTraerTicketPorUsuarioCliente {
	
	public static void main(String[] args) throws Exception {
		
		TicketABM abmTicket = new TicketABM();
		UsuarioABM abmUsuario = new UsuarioABM();
		
		Usuario cliente = abmUsuario.traerActivoEInactivo(1L);
		
		if (cliente != null ) {
			try {
				List<Ticket> ticketsCliente = abmTicket.traerPorCliente(cliente);
			
				System.out.println("---Tickets del cliente---");
				for (Ticket t : ticketsCliente) {
					System.out.println(t);
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	} else {
			System.out.println("No se encontro el cliente con el ID proporcionado.");
		}
	}
}