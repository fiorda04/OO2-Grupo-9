package test;

import negocio.TicketABM;

public class TestBorrarTicket {

	public static void main(String[] args) {
		TicketABM ticketABM = new TicketABM();

		try {
			// Borramos el ticket con ID 10
			ticketABM.eliminar(10L);
			System.out.println("Ticket ID 10 eliminado ");
		} catch (Exception e) {
			System.err.println("Error al intentar eliminar ticket con ID 10: " + e.getMessage());
		}

		try {
			// Borramos un ticket que no existe
			ticketABM.eliminar(1000L);
		} catch (Exception e) {
			System.err.println("Error al intentar eliminar ticket con ID 1000: " + e.getMessage());
		}
		

	}

}
