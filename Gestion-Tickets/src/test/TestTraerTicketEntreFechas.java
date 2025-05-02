package test;

import java.util.List;
import java.time.LocalDate;

import datos.Ticket;
import negocio.TicketABM;

public class TestTraerTicketEntreFechas {

	public static void main(String[] args) {

		TicketABM abmTicket = new TicketABM();
		
		LocalDate desde = LocalDate.of(2020, 06, 20);
		LocalDate hasta = LocalDate.of(2025, 05, 02);
		
		List<Ticket> tickets = abmTicket.traerTicketEntreFechas(desde, hasta);
		
		System.out.println("---Ticket entre fecha " + desde + " y fecha " + hasta + "---");
		for (Ticket t : tickets) {
			System.out.println(t);
		}
	}
}
