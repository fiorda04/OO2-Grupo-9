package negocio;

import java.util.List;
import dao.PersonaDao;
import dao.RolDao; // Necesitas RolDao para traer el Rol por ID
import dao.UsuarioDao;
import datos.Persona;
import datos.Rol;
import datos.Usuario;

public class UsuarioABM {
    UsuarioDao usuarioDao = new UsuarioDao();
    PersonaDao personaDao = new PersonaDao();
    RolDao rolDao = new RolDao();

    public Usuario traer(long idUsuario) {
        return usuarioDao.traer(idUsuario);
    }

    public Usuario traer(String nombreUsuario) {
        return usuarioDao.traer(nombreUsuario);
    }

    public Usuario traerPorDni(int dni) {
        return usuarioDao.traerPorDni(dni);
    }

    public List<Usuario> traerPorRol(long idRol) {
        return usuarioDao.traerPorRol(idRol);
    }


    public long agregar(String nombre, String apellido, int dni, String email, String telefono,
                        String nombreUsuario, String contrase�a, Rol rol) throws Exception {
        Persona personaPorDni = personaDao.traerPorDni(dni); // veo si el DNI ya existe
        if (personaPorDni != null) {
            throw new Exception("Ya existe una persona con el DNI ingresado.");
        }

        Persona personaPorEmail = personaDao.traerPorEmail(email);// Veo si el email ya existe
        if (personaPorEmail != null) {
            throw new Exception("Ya existe una persona con el email ingresado.");
        }

        if (usuarioDao.traer(nombreUsuario) != null) {  // veo si el nombre de usuario ya existe
            throw new Exception("Ya existe un usuario con ese nombre de usuario.");
        }

        Usuario usuario = new Usuario(nombre, apellido, dni, email, telefono, nombreUsuario, contrase�a, rol);
        return usuarioDao.agregar(usuario);
    }

    public void modificar(Usuario usuario) throws Exception {
        Usuario usuarioExistente = usuarioDao.traer(usuario.getIdPersona());
        if (usuarioExistente == null) {
            throw new Exception("No existe el usuario con el ID proporcionado para modificar.");
        }

        // verifico si existe otro usuario con el mismo nombre de usuario
        Usuario existentePorNombreUsuario = usuarioDao.traer(usuario.getNombreUsuario());
        if (existentePorNombreUsuario != null && existentePorNombreUsuario.getIdPersona() != usuario.getIdPersona()) {
            throw new Exception("Ya existe otro usuario con ese nombre de usuario.");
        }

        // verifico si existe otra persona con el mismo DNI
        Persona existentePorDni = personaDao.traerPorDni(usuario.getDni());
        if (existentePorDni != null && existentePorDni.getIdPersona() != usuario.getIdPersona()) {
            throw new Exception("Ya existe otra persona con ese DNI.");
        }

        // verifico si existe otra persona con el mismo email
        Persona existentePorEmail = personaDao.traerPorEmail(usuario.getEmail());
        if (existentePorEmail != null && existentePorEmail.getIdPersona() != usuario.getIdPersona()) {
            throw new Exception("Ya existe otra persona con ese email.");
        }

        usuarioDao.actualizar(usuario);
    }

    public void eliminar(long idUsuario) throws Exception {
        Usuario usuario = usuarioDao.traer(idUsuario);
        if (usuario == null) {
            throw new Exception("No existe el usuario con ese ID.");
        }
        usuarioDao.eliminar(usuario);
    }
    
    public void eliminar(String nombreUsuario) throws Exception {
        Usuario usuario = usuarioDao.traer(nombreUsuario);
        if (usuario == null) {
            throw new Exception("No existe usuario con el nombre de usuario proporcionado.");
        }
        usuarioDao.eliminar(nombreUsuario);
    }

    

    public List<Usuario> traer() {
        return usuarioDao.traer();
    }
}