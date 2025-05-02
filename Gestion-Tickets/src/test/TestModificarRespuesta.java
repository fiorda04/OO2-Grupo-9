package test;

import negocio.RespuestaABM;
import negocio.TicketABM;
import java.time.LocalDate;
import datos.Respuesta;
import datos.Ticket;
import datos.Usuario;
import negocio.UsuarioABM;

public class TestModificarRespuesta {
	public static void main(String[] args) throws Exception {
		RespuestaABM respuestaABM = new RespuestaABM();
		
		UsuarioABM usuario = new UsuarioABM();
		Usuario autorNuevo = usuario.traer(2L);
		//TicketABM ticket = new TicketABM();
		//Ticket ticketNuevo = ticket.traer(2);
		
		long num = 9L;
		try {
			Respuesta respuestaParaModificar = respuestaABM.traer(num);
			if (respuestaParaModificar != null) {
				respuestaParaModificar.setAutor(autorNuevo);
				respuestaParaModificar.setContenido("Contenido Modificado");
				respuestaParaModificar.setFechaResp(LocalDate.now());
				//respuestaParaModificar.setTicket(ticketNuevo);
				
				respuestaABM.modificar(respuestaParaModificar);
				System.out.println("Respuesta: " + num + "Modificada correctamente");
			} else {
				System.out.println(
						"No se encontro la respuesta con ID " + num + " para la prueba de nombre de usuario duplicado.");
			}
		} catch (Exception e) {
			System.err.println("No se puede modificar: " + e.getMessage());
		}
		
	}
}