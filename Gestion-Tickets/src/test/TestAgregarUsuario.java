package test;

import dao.RolDao;
import negocio.UsuarioABM;
import datos.Rol;

public class TestAgregarUsuario {
    public static void main(String[] args) {
        UsuarioABM usuarioABM = new UsuarioABM();
        RolDao rolDao = new RolDao(); // Instanciamos directamente el RolDao

        try {
            // Intentamos traer el rol con ID 1 directamente desde el DAO
            Rol rolAdmin = rolDao.traer(3);

            if (rolAdmin != null) {
                long ultimoIdUsuario = usuarioABM.agregar("Santiago", "Fiordalisi", 1234, "santi.fiordaaaa@example.com", "123-456-7890",
                        "Fiorda01|", "password123", rolAdmin);
                System.out.printf("Id usuario agregado: %d con rol: %s\n", ultimoIdUsuario, rolAdmin);
            } else {
                System.out.println("No se encontró el rol con ID 3 para asignar al usuario.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}