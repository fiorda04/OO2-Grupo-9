package test;

import negocio.UsuarioABM;
import datos.Usuario;

public class TestModificarUsuario {
    public static void main(String[] args) {
        UsuarioABM usuarioABM = new UsuarioABM();

        try {
            // Modificacion del usuario con ID 1
            Usuario usuarioOriginal = usuarioABM.traer(1L);
            if (usuarioOriginal != null) {
                System.out.println("\n--- Se modifico el usuario (ID 1) ---");
                usuarioOriginal.setNombre("ChecoModificado");
                usuarioOriginal.setApellido("PerezModificado");
                usuarioOriginal.setDni(33322111); 
                usuarioOriginal.setEmail("checo.modificado@gmail.com"); 
                usuarioOriginal.setTelefono("11-5555-4444");
                usuarioOriginal.setNombreUsuario("ChecoPModificado"); 
                usuarioOriginal.setContrasenia("nuevaClave123");
                usuarioABM.modificar(usuarioOriginal);
                System.out.println("Se modifico Usuario con ID 1");
            } else {
                System.out.println("No se encontro el usuario con ID 1 para modificar.");
            }

            // Prueba de modificar el nombre de usuario a uno ya existente ('asdads' del usuario con ID 2)
            System.out.println("\n--- modifico nombre de usuario a uno existente ('asdads') ---");
            Usuario usuarioParaModificarNombreDuplicado = usuarioABM.traer(1L);
            Usuario otroUsuarioConMismoNombre = usuarioABM.traer(2L);
            if (usuarioParaModificarNombreDuplicado != null && otroUsuarioConMismoNombre != null) {
                usuarioParaModificarNombreDuplicado.setNombreUsuario(otroUsuarioConMismoNombre.getNombreUsuario());
                try {
                    usuarioABM.modificar(usuarioParaModificarNombreDuplicado);
                } catch (Exception e) {
                    System.err.println("No se puede modificar: " + e.getMessage());
                }
            } else {
                System.out.println("No se encontraron usuarios con ID 1 o 2 para la prueba de nombre de usuario duplicado.");
            }
            
            // Prueba de modificar el DNI a uno ya existente (1234567 del usuario con ID 2)
            System.out.println("\n--- Intento de modificar DNI a uno existente (1234567) ---");
            Usuario usuarioParaModificarDniDuplicado = usuarioABM.traer(1L);
            Usuario otroUsuarioConMismoDni = usuarioABM.traer(2L);
            if (usuarioParaModificarDniDuplicado != null && otroUsuarioConMismoDni != null) {
                usuarioParaModificarDniDuplicado.setDni(otroUsuarioConMismoDni.getDni());
                try {
                    usuarioABM.modificar(usuarioParaModificarDniDuplicado);
                } catch (Exception e) {
                    System.err.println("No se puede modificar:  " + e.getMessage());
                }
            } else {
                System.out.println("No se encontraron usuarios con ID 1 o 2.");
            }

            // Prueba de modificar el email a uno ya existente ('sss@asaa' del usuario con ID 2)
            System.out.println("\n--- Intento de modificar email a uno existente ('sss@asaa') ---");
            Usuario usuarioParaModificarEmailDuplicado = usuarioABM.traer(1L);
            Usuario otroUsuarioConMismoEmail = usuarioABM.traer(2L);
            if (usuarioParaModificarEmailDuplicado != null && otroUsuarioConMismoEmail != null) {
                usuarioParaModificarEmailDuplicado.setEmail(otroUsuarioConMismoEmail.getEmail());
                try {
                    usuarioABM.modificar(usuarioParaModificarEmailDuplicado);
                } catch (Exception e) {
                    System.err.println("No se puede modificar: " + e.getMessage());
                }
            } else {
                System.out.println("No se encontraron usuarios con ID 1 o 2 para la prueba de email duplicado.");
            }

            // Prueba de intentar modificar un usuario que no existe (ID 999)
            System.out.println("\n--- Intento de modificar un usuario inexistente (ID 999) ---");
            Usuario usuarioInexistente = new Usuario();
            usuarioInexistente.setNombre("Inexistente");
            try {
                usuarioABM.modificar(usuarioInexistente);
            } catch (Exception e) {
                System.err.println("No se puede modificar:  " + e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}