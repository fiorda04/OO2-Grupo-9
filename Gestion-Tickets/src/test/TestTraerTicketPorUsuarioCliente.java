package test;

import java.util.List;

import datos.Ticket;
import datos.Usuario;
import negocio.TicketABM;
import negocio.UsuarioABM;

public class TestTraerTicketPorUsuarioCliente {
	
	public static void main(String[] args) {
		
		TicketABM abmTicket = new TicketABM();
		UsuarioABM abmUsuario = new UsuarioABM();
		
		Usuario cliente = abmUsuario.traer(1L);
		
		if (cliente != null ) {
			List<Ticket> ticketsCliente = abmTicket.traerPorCliente(cliente);
			
			System.out.println("Tickets del cliente: ");
			for (Ticket t : ticketsCliente) {
				System.out.println(t);
			}
		} else {
			System.out.println("No se encontro el cliente con el ID 1.");
		}
	}
}