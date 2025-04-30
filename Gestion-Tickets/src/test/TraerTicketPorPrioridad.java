package test;

import java.util.List;

import datos.Prioridad;
import datos.Ticket;
import negocio.TicketABM;

public class TraerTicketPorPrioridad {

	public static void main(String[] args) {
		
		TicketABM abmTicket = new TicketABM();
		
		Prioridad prioridad = new Prioridad();
		
		List<Ticket> ticketsPorPrioridad = abmTicket.traerTicketPorPrioridad(prioridad);
		
		if (ticketsPorPrioridad != null && !ticketsPorPrioridad.isEmpty()) {
			
		}
	}
}