package negocio;

import java.util.List;

import dao.TicketDao;
import datos.Estado;
import datos.Tipo;
import datos.Prioridad;
import datos.Usuario;
import datos.Categoria;
import datos.Respuesta;
import datos.Ticket;
import datos.Rol;

public class TicketABM {
	public class UsuarioABM {
	    TicketDao ticketDao = new TicketDao();

	    public Ticket traer(long idTicket) {
	        return ticketDao.traer(idTicket);
	    }

	    public long agregar(String titulo, String descripcion, Usuario Usuario, Estado estado, Tipo tipo) {
	    	//No tiene validacion.
	        Ticket ticket = new Ticket(titulo, descripcion, Usuario, estado, tipo);
	        return ticketDao.agregar(ticket);
	    }

	    public void modificar(Ticket ticket){
	    	//Pendiente a validar
	        ticketDao.actualizar(ticket);
	    }

	    public void eliminar(long idTicket) throws Exception {
	        Ticket ticket = ticketDao.traer(idTicket);
	        if (ticket == null) {
	            throw new Exception("No existe el ticket con el ID proporcionado.");
	        }
	        // Aqu� podr�as agregar validaciones si el ticket tiene dependencias (caracteristicas asignadas, etc.)
	        ticketDao.eliminar(ticket);
	    }

	    public List<Ticket> traer() {
	        return ticketDao.traer();
	    }
	    
}
}
