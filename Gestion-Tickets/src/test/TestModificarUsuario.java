package test;

import negocio.UsuarioABM;
import datos.Usuario;

public class TestModificarUsuario {
	public static void main(String[] args) {
		UsuarioABM usuarioABM = new UsuarioABM();
		long num = 7L;
		try {
			Usuario usuarioParaModificar = usuarioABM.traer(num);
			if (usuarioParaModificar != null) {
				usuarioParaModificar.setNombre("Checo");
				usuarioParaModificar.setApellido("Perez");
				usuarioParaModificar.setDni(44455566);
				usuarioParaModificar.setEmail("checo.perez@gmail.com");
				usuarioParaModificar.setTelefono("22-3333-4444");
				usuarioParaModificar.setNombreUsuario("Checo12");
				usuarioParaModificar.setContrasenia("otraClave");
				usuarioABM.modificar(usuarioParaModificar);
			} else {
				System.out.println(
						"No se encontro el usuario con ID " + num + " para la prueba de nombre de usuario duplicado.");
			}
		} catch (Exception e) {
			System.err.println("No se puede modificar: " + e.getMessage());
		}
		// Prueba de modificar el nombre de usuario a uno ya existente
		// ('CharlesLeclerc')
		System.out.println("\n--- Intento de modificar nombre de usuario a uno existente ('CharlesLeclerc') ---");
		try {
			Usuario usuarioParaModificar = usuarioABM.traer(num);
			if (usuarioParaModificar != null) {
				usuarioParaModificar.setNombre("OtroNombre");
				usuarioParaModificar.setApellido("OtroApellido");
				usuarioParaModificar.setDni(44455566);
				usuarioParaModificar.setEmail("otro.email@gmail.com");
				usuarioParaModificar.setTelefono("22-3333-4444");
				usuarioParaModificar.setNombreUsuario("CharlesLeclerc");
				usuarioParaModificar.setContrasenia("otraClave");
				usuarioABM.modificar(usuarioParaModificar);
			} else {
				System.out.println(
						"No se encontro el usuario con ID " + num + " para la prueba de nombre de usuario duplicado.");
			}
		} catch (Exception e) {
			System.err.println("No se puede modificar: " + e.getMessage());
		}

		// Prueba de modificar el DNI a uno ya existente (25123456)
		System.out.println("\n--- Intento de modificar DNI a uno existente (25123456) ---");
		try {
			Usuario usuarioParaModificarDni = usuarioABM.traer(num);
			if (usuarioParaModificarDni != null) {
				usuarioParaModificarDni.setNombre("OtroNombreDni");
				usuarioParaModificarDni.setApellido("OtroApellidoDni");
				usuarioParaModificarDni.setDni(25123456);
				usuarioParaModificarDni.setEmail("otro.email.dni@gmail.com");
				usuarioParaModificarDni.setTelefono("33-4444-5555");
				usuarioParaModificarDni.setNombreUsuario("OtroUsuarioDni");
				usuarioParaModificarDni.setContrasenia("otraClaveDni");
				usuarioABM.modificar(usuarioParaModificarDni);
			} else {
				System.out.println("No se encontro el usuario con ID " + num + " para la prueba de DNI duplicado.");
			}
		} catch (Exception e) {
			System.err.println("No se puede modificar " + e.getMessage());
		}

		// Prueba de modificar el mail a uno ya existente (charles.leclerc@gmail.com)
		System.out.println("\n--- Intento de modificar mail a uno existente (charles.leclerc@gmail.com) ---");
		try {
			Usuario usuarioParaModificarDni = usuarioABM.traer(num);
			if (usuarioParaModificarDni != null) {
				usuarioParaModificarDni.setNombre("OtroNombreDni");
				usuarioParaModificarDni.setApellido("OtroApellidoDni");
				usuarioParaModificarDni.setDni(11111111);
				usuarioParaModificarDni.setEmail("charles.leclerc@gmail.com");
				usuarioParaModificarDni.setTelefono("33-4444-5555");
				usuarioParaModificarDni.setNombreUsuario("OtroUsuarioDni");
				usuarioParaModificarDni.setContrasenia("otraClaveDni");
				usuarioABM.modificar(usuarioParaModificarDni);
			} else {
				System.out.println("No se encontro el usuario con ID " + num + " para la prueba de DNI duplicado.");
			}
		} catch (Exception e) {
			System.err.println("No se puede modificar " + e.getMessage());
		}

		// Prueba de modificar usuario no existente ()
		System.out.println("\n--- Intento de modificar Usuario no existente ---");
		long numinexistente = 999L;
		try {
			Usuario usuarioParaModificarDni = usuarioABM.traer(numinexistente);
			if (usuarioParaModificarDni != null) {
				usuarioParaModificarDni.setNombre("OtroNombreDni");
				usuarioParaModificarDni.setApellido("OtroApellidoDni");
				usuarioParaModificarDni.setDni(1111);
				usuarioParaModificarDni.setEmail("charles.lec@gmail.com");
				usuarioParaModificarDni.setTelefono("33-4444-5555");
				usuarioParaModificarDni.setNombreUsuario("OtuarioDni");
				usuarioParaModificarDni.setContrasenia("otraClaveDni");
				usuarioABM.modificar(usuarioParaModificarDni);
			} else {
				System.out.println("No se encontro el usuario con ID " + numinexistente + " para la prueba de DNI duplicado.");
			}
		} catch (Exception e) {
			System.err.println("No se puede modificar " + e.getMessage());
		}
	}
}