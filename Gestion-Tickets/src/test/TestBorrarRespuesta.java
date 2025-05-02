package test;

import negocio.RespuestaABM;

public class TestBorrarRespuesta {

	public static void main(String[] args) {
		RespuestaABM respuestaABM = new RespuestaABM();

		try {
			// Borramos la respuesta con ID 10
			respuestaABM.eliminar(8L);
			System.out.println("Respuesta ID 8 eliminada");
		} catch (Exception e) {
			System.err.println("Error al intentar eliminar respuesta con ID 10: " + e.getMessage());
		}

		try {
			// Borramos un ticket que no existe
			respuestaABM.eliminar(1000L);
		} catch (Exception e) {
			System.err.println("Error al intentar eliminar ticket con ID 1000: " + e.getMessage());
		}
		

	}

}
