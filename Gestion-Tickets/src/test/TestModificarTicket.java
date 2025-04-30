package test;

import java.time.LocalDate;

import datos.Estado;
import datos.Prioridad;
import datos.Ticket;
import datos.Tipo;
import datos.Usuario;
import negocio.TicketABM;
import negocio.UsuarioABM;
import dao.EstadoDao;
import dao.PrioridadDao;
import dao.TipoDao;


public class TestModificarTicket {

	public static void main(String[] args) {
		 TicketABM ticketABM = new TicketABM();
		 UsuarioABM usuarioABM = new UsuarioABM();
		 EstadoDao estadoDao = new EstadoDao();
		 TipoDao tipoDao = new TipoDao();
		 PrioridadDao prioridadDao = new PrioridadDao();

	        try {
	            // Modificacion del ticket con ID 1
	            Ticket ticketOriginal = ticketABM.traer(1L);
	            Usuario usuarioACambiar = usuarioABM.traer(1L);
	            Estado estadoACambiar = estadoDao.traer(2L);
	            Tipo tipoACambiar = tipoDao.traer(2L);
	            Prioridad prioridadACambiar = prioridadDao.traer(2L);
	            if (ticketOriginal != null && usuarioACambiar != null && estadoACambiar != null && tipoACambiar != null && prioridadACambiar != null) {
	                System.out.println("\n--- Se modifico el Ticket (ID 1) ---");
	                ticketOriginal.setTitulo("Hola soy un titulo modificado");
	                ticketOriginal.setDescripcion("Hola soy una descripcion modificada");
	                ticketOriginal.setFechaCreacion(LocalDate.of(2030, 1, 1));
	                ticketOriginal.setFechaModEst(LocalDate.of(2030, 1, 1)); 
	                ticketOriginal.setUsuario(usuarioACambiar); 
	                ticketOriginal.setEstado(estadoACambiar);
	                ticketOriginal.setTipo(tipoACambiar); 
	                ticketOriginal.setPrioridad(prioridadACambiar);
	                ticketABM.modificar(ticketOriginal);
	                System.out.println("Se modifico Ticket con ID 1");
	            } else {
	                System.out.println("No se encontro el ticket con ID 1 y los atributos a cambiar para modificar.");
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	}

}
