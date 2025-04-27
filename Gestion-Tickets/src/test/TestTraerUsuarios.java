package test;

import negocio.UsuarioABM;
import datos.Usuario;

import java.util.List;

public class TestTraerUsuarios {
    public static void main(String[] args) {
        UsuarioABM usuarioABM = new UsuarioABM();

        /* Traer usuario por DNI
        int dniABuscar = 12345678;
        Usuario usuarioPorDni = usuarioABM.traerPorDni(dniABuscar);
        if (usuarioPorDni != null) {
            System.out.printf("\nUsuario encontrado por DNI %d: %s (Nombre de usuario: %s)\n",
                    dniABuscar, usuarioPorDni, usuarioPorDni.getNombreUsuario());
        } else {
            System.out.printf("\nNo se encontró ningún usuario con DNI %d.\n", dniABuscar);
        }
	    */
        // Traer todos los usuarios
        List<Usuario> todosLosUsuarios = usuarioABM.traer();
        if (!todosLosUsuarios.isEmpty()) {
            System.out.println("\nListado de todos los usuarios:");
            for (Usuario usuario : todosLosUsuarios) {
                System.out.println(usuario.toString()); // Asegúrate de que Usuario tiene un toString() útil
            }
        } else {
            System.out.println("\nNo hay usuarios registrados en el sistema.");
        }

        /* Traer usuario por nombre de usuario
        String nombreUsuarioABuscar = "jPerez";
        Usuario usuarioPorNombreUsuario = usuarioABM.traer(nombreUsuarioABuscar);
        if (usuarioPorNombreUsuario != null) {
            System.out.printf("\nUsuario encontrado por nombre de usuario '%s': %s\n",
                    nombreUsuarioABuscar, usuarioPorNombreUsuario);
        } else {
            System.out.printf("\nNo se encontró ningún usuario con el nombre de usuario '%s'.\n", nombreUsuarioABuscar);
        }
        */
    }
}