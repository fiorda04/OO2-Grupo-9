package test;

import java.util.List;

import datos.Prioridad;
import datos.Ticket;
import negocio.TicketABM;
import dao.PrioridadDao;

public class TraerTicketPorPrioridad {

	public static void main(String[] args) {
		
		TicketABM abmTicket = new TicketABM();
		
		PrioridadDao prioridadDao = new PrioridadDao();
		
		//Listo por prioridad
		Prioridad alta = prioridadDao.traer(1L); //Prioridad alta
		Prioridad media = prioridadDao.traer(2L); //Prioridad media
		Prioridad baja = prioridadDao.traer(3L); //Prioridad baja
		
		//Tickets con prioridad alta
		System.out.println("---Tickets con prioridad ALTA---");
		List<Ticket> ticketsAlta = abmTicket.traerTicketPorPrioridad(alta);
		for (Ticket t : ticketsAlta) {
			System.out.println(t);
		}
		
		System.out.println(" ");
		
		//Tickets con prioridad media
		System.out.println("---Tickets con prioridad MEDIA---");
		List<Ticket> ticketsMedia = abmTicket.traerTicketPorPrioridad(media);
		for (Ticket t : ticketsMedia) {
			System.out.println(t);
		}
		
		System.out.println(" ");
		
		//Tickets con prioridad baja
		System.out.println("---Tickets con prioridad BAJA---");
		List<Ticket> ticketsBaja = abmTicket.traerTicketPorPrioridad(baja);
		for (Ticket t : ticketsBaja) {
			System.out.println(t);
		}
	}
}