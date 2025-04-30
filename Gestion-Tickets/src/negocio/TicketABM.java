package negocio;
import java.util.List;
import java.time.LocalDate;

import dao.TicketDao;
import datos.Estado;
import datos.Prioridad;
import datos.Tipo;
import datos.Usuario;
import datos.Ticket;

public class TicketABM {
	    TicketDao ticketDao = new TicketDao();

	    public Ticket traer(long idTicket) {
	        return ticketDao.traer(idTicket);
	    }

	    public long agregar(String titulo, String descripcion, Usuario usuarioCliente, Estado estado, Tipo tipo) {
	    	//No tiene validacion.
	        Ticket ticket = new Ticket(titulo, descripcion, usuarioCliente, estado, tipo);
	        return ticketDao.agregar(ticket);
	    }

	    public void modificar(Ticket ticket){
	    	//Pendiente a validar.
	        ticketDao.actualizar(ticket);
	    }

	    public void eliminar(long idTicket) throws Exception {
	        if (ticketDao.traer(idTicket) == null)throw new Exception("No existe el ticket con el ID proporcionado.");
	        // Aqui podroas agregar validaciones si el ticket tiene dependencias (caracteristicas asignadas, etc.)
	        ticketDao.eliminar(ticketDao.traer(idTicket));
	    }
	    
	    public Ticket traerTicket(int idTicket) {
	    	return ticketDao.traer(idTicket);
	    }
	    
	    public List<Ticket> traerTicketPorTipo(Tipo t){
	    	return ticketDao.traerTicketPorTipo(t);
	    }

	    public List<Ticket> traer() {
	        return ticketDao.traer();
	    }
	    public List<Ticket> traerPorCliente(Usuario cliente) {
	    	return ticketDao.traerPorCliente(cliente);
	    }
	    
	    public List<Ticket> traerPorEmpleado(Usuario empleado) {
	    	return ticketDao.traerPorEmpleado(empleado);
	    }
	    
	    public List<Ticket> traerPorFecha(LocalDate fecha){
	    	return ticketDao.traerPorFecha(fecha);
	    }
	    
	    public List<Ticket> traerTicketPorPrioridad(Prioridad prioridad){
	    	return ticketDao.traerTicketPorPrioridad(prioridad);
	    }
}

