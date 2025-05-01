package test;

import java.util.List;

import datos.Ticket;
import datos.Usuario;
import negocio.TicketABM;
import negocio.UsuarioABM;

public class TestTraerTicketPorUsuarioEmpleado {

    public static void main(String[] args) {

        TicketABM abmTicket = new TicketABM();
        UsuarioABM abmUsuario = new UsuarioABM();

        Usuario empleado = null; 
        try {
            empleado = abmUsuario.traer(2L);

            if (empleado != null) {
                List<Ticket> ticketsEmpleado = abmTicket.traerPorEmpleado(empleado);

                System.out.println("Tickets del empleado:");
                for (Ticket t : ticketsEmpleado) {
                    System.out.println(t);
                }
            } else {
                System.out.println("No se encontro el empleado con el ID 2.");
            }
        } catch (Exception e) {
            System.err.println("Error al traer el empleado con ID 2: " + e.getMessage());
        }
    }
}