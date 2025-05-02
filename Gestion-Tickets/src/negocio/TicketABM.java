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
	    
	  //trae los ticket del usuario cliente y valida que sean del cliente
	    public List<Ticket> traerPorCliente(Usuario cliente) throws Exception {
	    	if (!cliente.getRol().getNombreRol().equalsIgnoreCase("cliente")) {
	    		throw new Exception("El usuario no tiene rol cliente.");
	    	}
	    	return ticketDao.traerPorCliente(cliente);
		}
	    
	    //trae los tickets del usuario empleado y valida que sean del empleado
	    public List<Ticket> traerPorEmpleado(Usuario empleado) throws Exception {
	    	if (!empleado.getRol().getNombreRol().equalsIgnoreCase("empleado")) {
	    		throw new Exception ("El usuario no tiene rol empleado");
	    	}
	    	return ticketDao.traerPorEmpleado(empleado);
	    }
	    
	  //trae los tickets por una fecha en especifico
	    public List<Ticket> traerPorFecha(LocalDate fecha){
	    	return ticketDao.traerPorFecha(fecha);
	    }
	    
	    //trae todos los tickets de un usuario por prioridad alta, media o baja
	    public List<Ticket> traerTicketPorPrioridad(Prioridad prioridad){
	    	return ticketDao.traerTicketPorPrioridad(prioridad);
	    }
	    
	  //trae los tickets por estado
	    public List<Ticket> traerTicketPorEstado(Estado estado){
	    	return ticketDao.traerTicketPorEstado(estado);
	    }
	    
	    //trae los tickets entre fechas
	    public List<Ticket> traerTicketEntreFechas(LocalDate fechaInicio, LocalDate fechaFin){
	    	return ticketDao.traerTicketEntreFechas(fechaInicio, fechaFin);
	    }
}

