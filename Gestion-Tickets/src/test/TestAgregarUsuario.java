package test;

import dao.RolDao;
import negocio.UsuarioABM;
import datos.Rol;

public class TestAgregarUsuario {
	public static void main(String[] args) {
		UsuarioABM usuarioABM = new UsuarioABM();
		RolDao rolDao = new RolDao();

		// Hacemos una prueba cargando un usuario que se pueda cargar en la BBDD.
		try {
			Rol rolAdmin = rolDao.traer(3L);
			if (rolAdmin != null) {
				long usuarioNuevo = usuarioABM.agregar("Checo", "Perez", 11122333, "checo.perez@gmail.com",
						"11-1111-2222", "ChecoP", "Elcheco", rolAdmin);
				System.out.printf("Se agrego el usuario con ID: %d y rol: %s\n", usuarioNuevo, rolAdmin);
			} else {
				System.out.println("No se encontró el rol con ID 3.");
			}
		} catch (Exception e) {
			System.err.println("No se puede agregar porque " + e.getMessage());
		}

		// Hacemos una prueba agregando un usuario con un DNI ya existente en la BBDD
		try {
			Rol otroRol = rolDao.traer(3L);
			if (otroRol != null) {
				try {
					usuarioABM.agregar("Lewis", "Hamilton", 11122333, "lewis.Hamilton@gmail.com", "11-3333-4444", "lewisHamil",
							"Ellewis", otroRol);
				} catch (Exception e) {
					System.err.println("No se puede agregar porque " + e.getMessage());
				}
			} else {
				System.out.println("No se encontró el rol con ID 3.");
			}
		} catch (Exception e) {
			// Este catch es para el try que envuelve el bloque if completo
			e.printStackTrace();
		}

		// Hacemos una prueba agregando un usuario con un mail ya existente en la BBDD
		try {
			Rol tercerRol = rolDao.traer(3L);
			if (tercerRol != null) {
				try {
					usuarioABM.agregar("George", "Russell", 20001111, "checo.perez@gmail.com", "11-4567-9874", "GeorgeR",
							"Georgis", tercerRol);
				} catch (Exception e) {
					System.err.println("No se puede agregar porque " + e.getMessage());
				}
			} else {
				System.out.println("No se encontró el rol con ID 3.");
			}
		} catch (Exception e) {
			// Este catch es para el try que envuelve el bloque if completo
			e.printStackTrace();
		}

		// Hacemos una prueba agregando un usuario con un nombre de usuario ya existente en la BBDD
		try {
			Rol cuartoRol = rolDao.traer(3L);
			if (cuartoRol != null) {
				try {
					usuarioABM.agregar("Esteban", "Ocon", 33221144, "Esteban.Ocon@gmail.com", "11-0011-2233",
							"ChecoP", "Estebanquito", cuartoRol);
				} catch (Exception e) {
					System.err.println("No se puede agregar porque " + e.getMessage());
				}
			} else {
				System.out.println("No se encontró el rol con ID 3.");
			}
		} catch (Exception e) {
			// Este catch es para el try que envuelve el bloque if completo
			e.printStackTrace();
		}
	}
}