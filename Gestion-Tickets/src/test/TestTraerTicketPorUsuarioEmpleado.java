package test;

import java.util.List;

import datos.Ticket;
import datos.Usuario;
import negocio.TicketABM;
import negocio.UsuarioABM;

public class TestTraerTicketPorUsuarioEmpleado {

	public static void main(String[] args) throws Exception  {
		
		TicketABM abmTicket = new TicketABM();
		UsuarioABM abmUsuario = new UsuarioABM();
		
		Usuario empleado = abmUsuario.traerActivoEInactivo(1L);
		
		if (empleado != null) {
			try {
				List <Ticket> ticketsEmpleado = abmTicket.traerPorEmpleado(empleado);
			
				System.out.println("---Tickets del empleado---");
				for (Ticket t : ticketsEmpleado) {
					System.out.println(t);
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	} else {
			System.out.println("No se encontro el empleado con el ID proporcionado.");
		}
	}
}