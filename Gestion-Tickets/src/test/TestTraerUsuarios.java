package test;

import negocio.UsuarioABM;
import datos.Usuario;

public class TestTraerUsuarios {
    public static void main(String[] args) {
        UsuarioABM usuarioABM = new UsuarioABM();

        // traer usuario por ID
        Usuario usuarioPorId = usuarioABM.traer(1L);
        if (usuarioPorId != null) {
            System.out.printf("Usuario encontrado por ID 1: %s\n", usuarioPorId);
        } else {
            System.out.println("No se encontró usuario con ID 1.");
        }

        // traer usuario por una ID que no existe
        Usuario usuarioPorIdNoExiste = usuarioABM.traer(9999L);
        if (usuarioPorIdNoExiste != null) {
            System.out.printf("Usuario encontrado por ID 9999 %s\n", usuarioPorIdNoExiste);
        } else {
            System.out.println("No se encontró usuario con ID 9999 (esperado).");
        }

        // Prueba de traer usuario por DNI existente
        Usuario usuarioPorDni = usuarioABM.traerPorDni(11122333); // 
        if (usuarioPorDni != null) {
            System.out.printf("Usuario encontrado por DNI 11122333: %s\n", usuarioPorDni);
        } else {
            System.out.println("No se encontró usuario con DNI 11122333.");
        }

        // Prueba de traer usuario por DNI no existente
        Usuario usuarioPorDniNoExiste = usuarioABM.traerPorDni(55555555);
        if (usuarioPorDniNoExiste != null) {
            System.out.printf("Usuario encontrado por DNI 55555555 (inesperado): %s\n", usuarioPorDniNoExiste);
        } else {
            System.out.println("No se encontró usuario con DNI 55555555 (esperado).");
        }

        // Prueba de traer usuario por nombre de usuario existente
        Usuario usuarioPorNombre = usuarioABM.traer("carlosp"); // Suponemos que existe un usuario con este nombre
        if (usuarioPorNombre != null) {
            System.out.printf("Usuario encontrado por nombre de usuario 'carlosp': %s\n", usuarioPorNombre);
        } else {
            System.out.println("No se encontró usuario con nombre de usuario 'carlosp'.");
        }

        // Prueba de traer usuario por nombre de usuario no existente
        Usuario usuarioPorNombreNoExiste = usuarioABM.traer("usuarioInexistente");
        if (usuarioPorNombreNoExiste != null) {
            System.out.printf("Usuario encontrado por nombre de usuario 'usuarioInexistente' : %s\n", usuarioPorNombreNoExiste);
        } else {
            System.out.println("No se encontró usuario con nombre de usuario 'usuarioInexistente' .");
        }
        
        System.out.println("\n Traer todos los usuarios");
        java.util.List<Usuario> todosLosUsuarios = usuarioABM.traer();
        if (todosLosUsuarios != null && !todosLosUsuarios.isEmpty()) {
            System.out.println("Listado de todos los usuarios:");
            for (Usuario usuario : todosLosUsuarios) {
                System.out.println(usuario);
            }
        } else {
            System.out.println("No se encontraron usuarios en la base de datos.");
        }
        
        // Prueba de traer todos los usuarios por número de rol
        System.out.println("\n--- Prueba de traer usuarios por número de rol (ID 3) ---");
        long idRolABuscar = 1L; //
        java.util.List<Usuario> usuariosPorRol = usuarioABM.traerPorRol(idRolABuscar);
        if (usuariosPorRol != null && !usuariosPorRol.isEmpty()) {
            System.out.printf("Listado de usuarios con Rol ID %d:\n", idRolABuscar);
            for (Usuario usuario : usuariosPorRol) {
                System.out.println(usuario);
            }
        } else {
            System.out.printf("No se encontraron usuarios con Rol ID %d en la base de datos.\n", idRolABuscar);
        }
    }
}