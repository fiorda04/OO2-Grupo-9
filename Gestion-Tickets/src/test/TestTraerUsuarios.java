package test;

import negocio.UsuarioABM;
import datos.Usuario;

public class TestTraerUsuarios {
    public static void main(String[] args) {
        UsuarioABM usuarioABM = new UsuarioABM();

        // traer usuario por ID
        Usuario usuarioPorId = null;
        long numIdExistente = 1L;
        System.out.println("------Prueba traer usuario por ID existente-------");
        try {
            usuarioPorId = usuarioABM.traer(numIdExistente);
            if (usuarioPorId != null) {
                System.out.printf("Usuario encontrado por ID %d: %s\n", numIdExistente, usuarioPorId);
            } else {
                System.out.printf("No se encontro usuario con ID %d.\n", numIdExistente);
            }
        } catch (Exception e) {
            System.err.println("Error al traer usuario por ID: " + e.getMessage());
        }

        // traer usuario por una ID que no existe
        Usuario usuarioPorIdNoExiste = null;
        long numIdInexistente = 9999L;
        System.out.println("\n--------Prueba traer usuario por ID que no existe---------");
        try {
            usuarioPorIdNoExiste = usuarioABM.traer(numIdInexistente);
            if (usuarioPorIdNoExiste != null) {
                System.out.printf("Usuario encontrado por ID %d: %s\n", numIdInexistente, usuarioPorIdNoExiste);
            } else {
                System.out.printf("No se encontro usuario con ID %d.\n", numIdInexistente);
            }
        } catch (Exception e) {
            System.err.println("Error al traer usuario por ID inexistente: " + e.getMessage());
        }

        // Prueba de traer usuario por DNI existente
        Usuario usuarioPorDni = null;
        int numDniExistente = 23456789;
        System.out.println("\n--------Prueba traer usuario por DNI existente---------");
        try {
            usuarioPorDni = usuarioABM.traerPorDni(numDniExistente);
            if (usuarioPorDni != null) {
                System.out.printf("Usuario encontrado por DNI %d: %s\n", numDniExistente, usuarioPorDni);
            } else {
                System.out.printf("No se encontro usuario con DNI %d.\n", numDniExistente);
            }
        } catch (Exception e) {
            System.err.println("Error al traer usuario por DNI: " + e.getMessage());
        }

        // Prueba de traer usuario por DNI no existente
        Usuario usuarioPorDniNoExiste = null;
        int numDniInexistente = 55555555;
        System.out.println("\n--------Prueba traer usuario por DNI que no existe---------");
        try {
            usuarioPorDniNoExiste = usuarioABM.traerPorDni(numDniInexistente);
            if (usuarioPorDniNoExiste != null) {
                System.out.printf("Usuario encontrado por DNI %d: %s\n", numDniInexistente, usuarioPorDniNoExiste);
            } else {
                System.out.printf("No se encontro usuario con DNI %d.\n", numDniInexistente);
            }
        } catch (Exception e) {
            System.err.println("Error al traer usuario por DNI inexistente: " + e.getMessage());
        }

        // Prueba de traer usuario por nombre de usuario existente
        Usuario usuarioPorNombre = null;
        String nombreUsuarioExistente = "GeorgeRussell";
        System.out.println("\n--------Prueba traer usuario por nombre de usuario existente---------");
        try {
            usuarioPorNombre = usuarioABM.traer(nombreUsuarioExistente);
            if (usuarioPorNombre != null) {
                System.out.printf("Usuario encontrado por nombre de usuario '%s': %s\n", nombreUsuarioExistente, usuarioPorNombre);
            } else {
                System.out.printf("No se encontro usuario con nombre de usuario '%s'.\n", nombreUsuarioExistente);
            }
        } catch (Exception e) {
            System.err.println("Error al traer usuario por nombre de usuario: " + e.getMessage());
        }

        System.out.println("\n--------Prueba traer usuario por nombre de usuario que no existe---------");
        Usuario usuarioPorNombreNoExiste = null;
        String nombreUsuarioInexistente = "usuarioInexistente";
        try {
            usuarioPorNombreNoExiste = usuarioABM.traer(nombreUsuarioInexistente);
            if (usuarioPorNombreNoExiste != null) {
                System.out.printf("Usuario encontrado por nombre de usuario '%s': %s\n", nombreUsuarioInexistente, usuarioPorNombreNoExiste);
            } else {
                System.out.printf("No se encontro usuario con nombre de usuario '%s'.\n", nombreUsuarioInexistente);
            }
        } catch (Exception e) {
            System.err.println("Error al traer usuario por nombre de usuario inexistente: " + e.getMessage());
        }

        System.out.println("\n--- Traer todos los usuarios ---");
        java.util.List<Usuario> todosLosUsuarios = null;
        try {
            todosLosUsuarios = usuarioABM.traer();
            if (todosLosUsuarios != null && !todosLosUsuarios.isEmpty()) {
                System.out.println("Listado de todos los usuarios:");
                for (Usuario usuario : todosLosUsuarios) {
                    System.out.println(usuario);
                }
            } else {
                System.out.println("No se encontraron usuarios en la base de datos.");
            }
        } catch (Exception e) {
            System.err.println("Error al traer todos los usuarios: " + e.getMessage());
        }

        // Prueba de traer todos los usuarios por número de rol
        System.out.println("\n--- Prueba de traer usuarios por número de rol (ID 1) ---");
        long idRolABuscar = 1L;
        java.util.List<Usuario> usuariosPorRol = null;
        try {
            usuariosPorRol = usuarioABM.traerPorRol(idRolABuscar);
            if (usuariosPorRol != null && !usuariosPorRol.isEmpty()) {
                System.out.printf("Listado de usuarios con Rol ID %d:\n", idRolABuscar);
                for (Usuario usuario : usuariosPorRol) {
                    System.out.println(usuario);
                }
            } else {
                System.out.printf("No se encontraron usuarios con Rol ID %d en la base de datos.\n", idRolABuscar);
            }
        } catch (Exception e) {
            System.err.println("Error al traer usuarios por rol: " + e.getMessage());
        }

        // Prueba de traer usuario por Email existente
        System.out.println("\n--- Prueba de traer usuario por Email existente ('admin@example.com') ---");
        String emailExistente = "max.verstappen@gmail.com"; 
        Usuario usuarioPorEmail = null;
        try {
            usuarioPorEmail = usuarioABM.traerPorEmail(emailExistente);
            if (usuarioPorEmail != null) {
                System.out.printf("Usuario encontrado por Email '%s': %s\n", emailExistente, usuarioPorEmail);
            } else {
                System.out.printf("No se encontro usuario con Email '%s'.\n", emailExistente);
            }
        } catch (Exception e) {
            System.err.println("Error al traer usuario por Email: " + e.getMessage());
        }

        // Prueba de traer usuario por Email no existente
        System.out.println("\n--- Prueba de traer usuario por Email no existente ('inexistente@example.com') ---");
        String emailNoExistente = "inexistente@example.com";
        Usuario usuarioPorEmailNoExiste = null;
        try {
            usuarioPorEmailNoExiste = usuarioABM.traerPorEmail(emailNoExistente);
            if (usuarioPorEmailNoExiste != null) {
                System.out.printf("Usuario encontrado por Email '%s': %s\n", emailNoExistente, usuarioPorEmailNoExiste);
            } else {
                System.out.printf("No se encontro usuario con Email '%s'.\n", emailNoExistente);
            }
        } catch (Exception e) {
            System.err.println("Error al traer usuario por Email inexistente: " + e.getMessage());
        }
    }
}