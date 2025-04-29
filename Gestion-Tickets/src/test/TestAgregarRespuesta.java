package test;


import dao.TicketDao;
import dao.UsuarioDao;
import negocio.RespuestaABM;
import datos.Ticket;
import datos.Usuario;

public class TestAgregarRespuesta {
    public static void main(String[] args) {
    	
    	RespuestaABM respuestaABM = new RespuestaABM();
    	
    	UsuarioDao usuarioDao = new UsuarioDao();
    	TicketDao ticketDao = new TicketDao();

        try {
        	Usuario usuario = usuarioDao.traer(1L);
        	Ticket ticket = ticketDao.traer(1L);
        	
        	if (usuario != null) {
        		System.out.println("Usuario: " + usuario);
        		System.out.println("Ticket ID: " + ticket.getIdTicket());
        		//Solo el empleado puede dar una respuesta
        		long ultimoIdRespuesta = respuestaABM.agregar("Contenido de la respuesta", usuario, ticket);
        		System.out.printf("Id usuario agregado la respusta: %d, del usuario: %s, del ticket: %d\n", ultimoIdRespuesta, usuario.getNombreUsuario(), ticket.getIdTicket());
        	} else {
                System.out.printf("No se encontro el usuario o ticket");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}