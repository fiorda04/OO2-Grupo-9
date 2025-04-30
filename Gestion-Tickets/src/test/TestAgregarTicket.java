package test;

import dao.EstadoDao;
import dao.TipoDao;
import dao.UsuarioDao;
import negocio.TicketABM;
import datos.Usuario;
import datos.Estado;
import datos.Tipo;
import dao.TicketDao;

public class TestAgregarTicket {
    public static void main(String[] args) {
        TicketABM ticketABM = new TicketABM();
        UsuarioDao usuarioDao = new UsuarioDao();
        EstadoDao estadoDao = new EstadoDao();
        TipoDao tipoDao = new TipoDao();
        TicketDao ticketDao = new TicketDao();

        try {
        	
        	//Caso 1: Se agrega normalmente un ticket.
            Usuario usuario = usuarioDao.traer(1L); // ID del usuario creador del ticket
            Estado estado = estadoDao.traer(1L);    // ID del estado, ej: "Cerrado"
            Tipo tipo = tipoDao.traer(1L);          // ID del tipo de ticket, ej: "Consulta"

            if (usuario != null && estado != null && tipo != null) {
                try {
					long idTicket = ticketABM.agregar("Hola soy un ticket", "Pude ingresar correctamente.", usuario, estado, tipo);
					System.out.printf("Se agrego el ticket con ID: %d\n Del usuario: %s\n Con DNI: %d\n", idTicket, usuario.getNombreUsuario(), usuario.getDni());
					System.out.printf("Caso 1: Se agrego el ticket con titulo: %s (correcto)\n", ticketDao.traer(idTicket).getTitulo());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.err.println("El primer caso no se pudo agregar porque " + e.getMessage());
				}
            } else {
                System.out.println("No se pudo encontrar uno de los elementos necesarios (usuario, estado o tipo).");
            }
            
            //Caso 2: No se agrega el ticket porque el tipo no existe o es invalido.
            Tipo tipoInvalido = tipoDao.traer(10L);
            if (tipoInvalido != null) {
                try {
                    ticketABM.agregar("Tipo invalido", "No deberia agregarse.", usuario, estado, tipoInvalido);
                    System.out.println("Caso 2: Se agrego el ticket con tipo invalido (ERROR) - Esto no deberia verse -");
                } catch (Exception e) {
                    System.err.println("Caso 2: No se puede agregar porque " + e.getMessage() + " - Esto no deberia verse -");
                }
            } else {
                System.out.println("Caso 2: No se encontró el tipo con ID 10 (correcto)");
            }
            
            //Caso 3: No se agrega el ticket porque el estado no existe o es invalido.
            Estado estadoInvalido = estadoDao.traer(10L);
            if (estadoInvalido != null) {
                try {
                    ticketABM.agregar("Estado invalido", "No deberia agregarse.", usuario, estadoInvalido, tipo);
                    System.out.println("Caso 3: Se agrego el ticket con estado invalido (ERROR) - Esto no deberia verse -");
                } catch (Exception e) {
                    System.err.println("Caso 3: No se puede agregar porque " + e.getMessage() + " - Esto no deberia verse -");
                }
            } else {
                System.out.println("Caso 3: No se encontro el estado con ID 10 (correcto)");
            }
            
            //Caso 4: No se agrega el ticket porque el usuario no existe o es invalido.
            Usuario usuarioInvalido = usuarioDao.traer(100L);
            if (usuarioInvalido != null) {
                try {
                    ticketABM.agregar("Usuario invalido", "No deberia agregarse.", usuarioInvalido, estado, tipo);
                    System.out.println("Caso 4: Se agrego el ticket con usuario invalido (ERROR) - Esto no deberia verse -");
                } catch (Exception e) {
                    System.err.println("Caso 4: No se puede agregar porque " + e.getMessage() + " - Esto no deberia verse -");
                }
            } else {
                System.out.println("Caso 4: No se encontro el usuario con ID 100 (correcto)");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
