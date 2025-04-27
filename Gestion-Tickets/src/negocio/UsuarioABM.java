package negocio;

import java.util.List;
import dao.UsuarioDao;
import datos.Rol;
import datos.Usuario;

public class UsuarioABM {
    UsuarioDao usuarioDao = new UsuarioDao();

    public Usuario traer(long idUsuario) {
        return usuarioDao.traer(idUsuario);
    }

    public Usuario traer(String nombreUsuario) {
        return usuarioDao.traer(nombreUsuario);
    }

    public Usuario traerPorDni(int dni) {
        return usuarioDao.traerPorDni(dni);
    }

    public long agregar(String nombre, String apellido, int dni, String email, String telefono,
                        String nombreUsuario, String contraseña, Rol rol) throws Exception {
        // Validar si existe un usuario con el mismo nombre de usuario
        if (usuarioDao.traer(nombreUsuario) != null) {
            throw new Exception("Ya existe un usuario con ese nombre de usuario.");
        }
        // Aquí podrías agregar validación si ya existe una persona con ese DNI
        Usuario usuario = new Usuario(nombre, apellido, dni, email, telefono, nombreUsuario, contraseña, rol);
        return usuarioDao.agregar(usuario);
    }

    public void modificar(Usuario usuario) throws Exception {
        // En caso de editar el nombre de usuario, validar que no exista otro usuario con el mismo nombre
        Usuario existente = usuarioDao.traer(usuario.getNombreUsuario());
        if (existente != null && existente.getIdPersona() != usuario.getIdPersona()) {
            throw new Exception("Ya existe otro usuario con ese nombre de usuario.");
        }
        usuarioDao.actualizar(usuario);
    }

    public void eliminar(long idUsuario) throws Exception {
        Usuario usuario = usuarioDao.traer(idUsuario);
        if (usuario == null) {
            throw new Exception("No existe el usuario con el ID proporcionado.");
        }
        // Aquí podrías agregar validaciones si el usuario tiene dependencias (tickets asignados, etc.)
        usuarioDao.eliminar(usuario);
    }

    public List<Usuario> traer() {
        return usuarioDao.traer();
    }
}