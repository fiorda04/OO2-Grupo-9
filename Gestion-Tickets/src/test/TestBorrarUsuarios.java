package test;

import negocio.UsuarioABM;

public class TestBorrarUsuarios {
	public static void main(String[] args) {
		UsuarioABM usuarioABM = new UsuarioABM();
		
		long dato = 7L;
		try {
		    // Borramos el usuario con ID 7
		    usuarioABM.eliminar(dato);
		    System.out.println("El Usuario con ID " + dato + " fue eliminado ");
		} catch (Exception e) {
		    System.err.println("Error al intentar eliminar usuario con ID " + dato + " "  + e.getMessage());
		}

		try {
			// Borramos un usuario que no existe
			usuarioABM.eliminar(1000L);
		} catch (Exception e) {
			System.err.println("Error al intentar eliminar usuario con ID 1000: " + e.getMessage());
		}
		
		// Borramos por nombre de usuario
		String nom = "LandoNorris";
        try {
            usuarioABM.eliminar(nom);
            System.out.println("Usuario "+nom+" fue eliminado");
        } catch (Exception e) {
            System.err.println("Error al intentar eliminar usuario por nombre de usuario " + nom + " " + e.getMessage());
        }
        // Borramos por nombre de usuario que no existe
        try {
            usuarioABM.eliminar("usuarioInexistente");
            System.out.println("Intento de eliminar usuario con nombre de usuario 'usuarioInexistente'.");
        } catch (Exception e) {
            System.err.println("Error al intentar eliminar usuario por nombre de usuario 'usuarioInexistente': " + e.getMessage());
        }
	}

}
