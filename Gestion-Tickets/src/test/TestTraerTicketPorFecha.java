package test;

import java.time.LocalDate;
import java.util.List;
import datos.Ticket;
import negocio.TicketABM;

public class TestTraerTicketPorFecha {

	public static void main(String[] args) {
		
		TicketABM abmTicket = new TicketABM();
		
		LocalDate fecha = LocalDate.of(2025, 4, 30);

		List<Ticket> ticketsPorFecha = abmTicket.traerPorFecha(fecha);
		
		if (ticketsPorFecha != null && !ticketsPorFecha.isEmpty()) {
			System.out.println("Tickets encontrados para la fecha" + fecha + ":");
			for (Ticket t : ticketsPorFecha) {
				System.out.println(t);
			}
		} else {
			System.out.println("No se encontraron tickets para la fecha" + fecha + ".");
		}
	}
}