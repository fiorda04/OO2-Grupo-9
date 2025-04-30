package test;

import negocio.UsuarioABM;

public class TestBorrarUsuarios {
	public static void main(String[] args) {
		UsuarioABM usuarioABM = new UsuarioABM();

		try {
			// Borramos el usuario con ID 1
			usuarioABM.eliminar(5L);
			System.out.println("Usuario ID 5 eliminado ");
		} catch (Exception e) {
			System.err.println("Error al intentar eliminar usuario con ID 5: " + e.getMessage());
		}

		try {
			// Borramos un usuario que no existe
			usuarioABM.eliminar(1000L);
		} catch (Exception e) {
			System.err.println("Error al intentar eliminar usuario con ID 1000: " + e.getMessage());
		}
		
		// Borramos por nombre de usuario
        try {
            usuarioABM.eliminar("ChecoP");
        } catch (Exception e) {
            System.err.println("Error al intentar eliminar usuario por nombre de usuario 'ChecoP': " + e.getMessage());
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
