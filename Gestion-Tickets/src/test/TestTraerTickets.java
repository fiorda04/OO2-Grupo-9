package test;

import java.util.List;

import dao.TipoDao;
import datos.Ticket;
import datos.Tipo;
import negocio.TicketABM;

public class TestTraerTickets {

	public static void main(String[] args) {
		TicketABM ticketABM = new TicketABM();
		TipoDao tipoDao = new TipoDao();

        // traer ticket por ID
        Ticket ticketPorId = ticketABM.traer(1L);
        if (ticketPorId != null) {
            System.out.printf("Ticket encontrado por ID 1: %s\n", ticketPorId);
        } else {
            System.out.println("No se encontro ticket con ID 1.");
        }

        // traer ticket por una ID que no existe
        Ticket ticketPorIdNoExiste = ticketABM.traer(9999L);
        if (ticketPorIdNoExiste != null) {
            System.out.printf("Ticket encontrado por ID 9999 %s\n", ticketPorIdNoExiste);
        } else {
            System.out.println("No se encontro usuario con ID 9999 (esperado).");
        }

        // Prueba de traer tickets por tipo existente
        System.out.println("\n--- Prueba de traer tickets por tipo (ID 2) ---");
        Tipo tipoExistente = tipoDao.traer(2L);
        List<Ticket> listaTicketsPorTipo = ticketABM.traerTicketPorTipo(tipoExistente); // 
        if (tipoExistente != null && !listaTicketsPorTipo.isEmpty()) {
        	System.out.println("Listado de tickets con tipo ID 2: (correcto)");
        	for (Ticket ticket : listaTicketsPorTipo) {
				System.out.println(ticket);
			}
        } else {
            System.out.println("No se encontraron tickets con tipo ID 2 o no se encontro el tipo en la base de datos.\n");
        }

        // Prueba de traer tickets por tipo no existente
        System.out.println("\n--- Prueba de traer tickets por tipo (ID 999) ---");
        Tipo tipoNoExistente = tipoDao.traer(999L);
        List<Ticket> listaTicketsPorTipoNoExistente = ticketABM.traerTicketPorTipo(tipoNoExistente); // 
        if (tipoExistente != null && !listaTicketsPorTipoNoExistente.isEmpty()) {
        	System.out.println("Listado de tickets con tipo ID 999:");
        	for (Ticket ticket : listaTicketsPorTipoNoExistente) {
				System.out.println(ticket);
			}
        } else {
            System.out.println("No se encontraron tickets con tipo ID 999 o no se encontro el tipo en la base de datos. (correcto)\n");
        }
        
        System.out.println("\n Traer todos los tickets:");
        List<Ticket> todosLosTickets = ticketABM.traer();
        if (todosLosTickets != null && !todosLosTickets.isEmpty()) {
            System.out.println("Listado de todos los tickets:");
            for (Ticket ticket : todosLosTickets) {
                System.out.println(ticket);
            }
        } else {
            System.out.println("No se encontraron tickets en la base de datos.");
        }
        

	}

}
