package test;

import java.util.List;
import datos.Ticket;
import datos.Estado;
import negocio.TicketABM;
import dao.EstadoDao;

public class TraerTicketPorEstado {

	public static void main(String[] args) {
		
		TicketABM abmTicket = new TicketABM();
		
		EstadoDao estadoDao = new EstadoDao();
		
		Estado abierto = estadoDao.traer(2L); //Estado abierto
		Estado cerrado = estadoDao.traer(1L); //Estado cerrado
		
		System.out.println("---Tickets con estado ABIERTO---");
		List<Ticket> ticketAbierto = abmTicket.traerTicketPorEstado(abierto);
		for (Ticket t : ticketAbierto) {
			System.out.println(t);
		}
		
		System.out.println(" ");
		
		System.out.println("---Tickets con estado CERRADO---");
		List<Ticket> ticketCerrado = abmTicket.traerTicketPorEstado(cerrado);
		for (Ticket t : ticketCerrado) {
			System.out.println(t);
		}
	}
}
