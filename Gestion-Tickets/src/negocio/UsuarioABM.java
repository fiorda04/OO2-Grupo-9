package negocio;

import java.util.List;
import dao.PersonaDao;
import dao.RolDao;
import dao.UsuarioDao;
import datos.Persona;
import datos.Rol;
import datos.Usuario;

public class UsuarioABM {
	UsuarioDao usuarioDao = new UsuarioDao();
	PersonaDao personaDao = new PersonaDao();
	RolDao rolDao = new RolDao();

	public Usuario traer(long idUsuario) throws Exception {
		try {
			return usuarioDao.traer(idUsuario);
		} catch (Exception e) {
			throw new Exception("Error al traer usuario por ID: " + e.getMessage());
		}
	}

	public Usuario traerActivoEInactivo(long idUsuario) throws Exception {
		try {
			return usuarioDao.traerActivoEInactivo(idUsuario);
		} catch (Exception e) {
			throw new Exception("Error al traer usuario por ID: " + e.getMessage());
		}
	}

	public Usuario traer(String nombreUsuario) throws Exception {
		try {
			return usuarioDao.traer(nombreUsuario);
		} catch (Exception e) {
			throw new Exception("Error al traer usuario por nombre de usuario: " + e.getMessage());
		}
	}

	public Usuario traerPorDni(int dni) throws Exception {
		try {
			return usuarioDao.traerPorDni(dni);
		} catch (Exception e) {
			throw new Exception("Error al traer usuario por DNI: " + e.getMessage());
		}
	}

	public List<Usuario> traerPorRol(long idRol) throws Exception {
		try {
			return usuarioDao.traerPorRol(idRol);
		} catch (Exception e) {
			throw new Exception("Error al traer usuarios por rol: " + e.getMessage());
		}
	}

	public Usuario traerPorEmail(String email) throws Exception {
		try {
			return usuarioDao.traerPorEmail(email);
		} catch (Exception e) {
			throw new Exception("Error al traer usuario por Email: " + e.getMessage());
		}
	}

	public long agregar(String nombre, String apellido, int dni, String email, String telefono, String nombreUsuario,
			String contrasenia, Rol rol) throws Exception {
		Persona personaPorDni = personaDao.traerPorDni(dni);

		if (personaPorDni != null) {
			Usuario usuarioActivoConDni = usuarioDao.traerPorDni(dni);
			if (usuarioActivoConDni != null) {
				throw new Exception("Ya existe una persona con el DNI ingresado.");
			} else {
				throw new Exception("Ya existe una persona con el DNI ingresado y esta dada de baja. "
						+ "Si desea darla de alta nuevamente, por favor contacte al administrador.");
			}
		}

		Persona personaPorEmail = personaDao.traerPorEmail(email);
		if (personaPorEmail != null) {
			// Verificamos si existe un usuario ACTIVO asociado a esta persona por su email
			Usuario usuarioActivoConEmailDePersona = usuarioDao.traerPorEmail(email);
			if (usuarioActivoConEmailDePersona != null) {
				throw new Exception("Ya existe una persona con el email ingresado.");
			} else {
				throw new Exception("Ya existe una persona con el email ingresado y esta dada de baja. "
						+ "Si desea darla de alta nuevamente, por favor contacte al administrador.");
			}
		}

		// Verificamos si existe un usuario con el mismo nombre de usuario
		Usuario usuarioExistenteConNombreUsuario = usuarioDao.traer(nombreUsuario);
		if (usuarioExistenteConNombreUsuario != null) {
			if (usuarioExistenteConNombreUsuario.isActivo()) {
				throw new Exception("Ya existe un usuario con ese nombre de usuario.");
			} else {
				throw new Exception(
						"Ya existe un usuario con el nombre de usuario ingresado y está dado de baja. Si desea darlo de alta nuevamente, por favor contacte al administrador.");
			}
		}

		Usuario nuevoUsuario = new Usuario(nombre, apellido, dni, email, telefono, nombreUsuario, contrasenia, rol);

		try {
			return usuarioDao.agregar(nuevoUsuario);
		} catch (Exception e) {
			if (e.getMessage().equals("Nombre de usuario duplicado en la base de datos.")) {
				throw new Exception("Ya existe un usuario con ese nombre de usuario."); // Mensaje final para el usuario
			}
			throw new Exception("Error al agregar usuario: " + e.getMessage());
		}
	}

	public void modificar(Usuario usuario) throws Exception {
		Usuario usuarioExistente = null;
		try {
			usuarioExistente = usuarioDao.traer(usuario.getIdPersona());
		} catch (Exception e) {
			throw new Exception("Error al verificar existencia del usuario para modificar: " + e.getMessage());
		}
		if (usuarioExistente == null) {
			throw new Exception("No existe el usuario con el ID proporcionado para modificar.");
		}

		Usuario existentePorNombreUsuario = null;
		try {
			existentePorNombreUsuario = usuarioDao.traer(usuario.getNombreUsuario());
		} catch (Exception e) {
			throw new Exception("Error al verificar nombre de usuario durante modificación: " + e.getMessage());
		}
		if (existentePorNombreUsuario != null && existentePorNombreUsuario.getIdPersona() != usuario.getIdPersona()) {
			throw new Exception("Ya existe otro usuario con ese nombre de usuario.");
		}

		Persona existentePorDni = personaDao.traerPorDni(usuario.getDni());
		if (existentePorDni != null && existentePorDni.getIdPersona() != usuario.getIdPersona()) {
			throw new Exception("Ya existe otra persona con ese DNI.");
		}

		Persona existentePorEmail = personaDao.traerPorEmail(usuario.getEmail());
		if (existentePorEmail != null && existentePorEmail.getIdPersona() != usuario.getIdPersona()) {
			throw new Exception("Ya existe otra persona con ese email.");
		}

		try {
			usuarioDao.actualizar(usuario);
			System.out.println("Usuario con ID " + usuario.getIdPersona() + " modificado exitosamente.");
		} catch (Exception e) {
			throw new Exception("Error al actualizar usuario: " + e.getMessage());
		}
	}

	public void eliminar(long idUsuario) throws Exception {
		Usuario usuario = null;
		try {
			usuario = usuarioDao.traer(idUsuario);
		} catch (Exception e) {
			throw new Exception("Error al verificar existencia del usuario para eliminar: " + e.getMessage());
		}
		if (usuario == null) {
			throw new Exception("No existe el usuario con ese ID.");
		}
		try {
			usuarioDao.bajaLogica(usuario);
		} catch (Exception e) {
			throw new Exception("Error al dar de baja lógicamente al usuario: " + e.getMessage());
		}
	}

	public void eliminar(String nombreUsuario) throws Exception {
		Usuario usuario = null;
		try {
			usuario = usuarioDao.traer(nombreUsuario);
		} catch (Exception e) {
			throw new Exception("Error al verificar existencia del usuario para eliminar: " + e.getMessage());
		}
		if (usuario == null) {
			throw new Exception("No existe usuario con el nombre de usuario proporcionado.");
		}
		if (!usuario.isActivo()) {
			throw new Exception("El usuario con nombre de usuario '" + nombreUsuario + "' no existe");
		}
		try {
			usuarioDao.bajaLogica(nombreUsuario);
		} catch (Exception e) {
			throw new Exception("Error al dar de baja lógicamente al usuario con nombre de usuario '" + nombreUsuario
					+ "': " + e.getMessage());
		}
	}

	public List<Usuario> traer() throws Exception {
		try {
			return usuarioDao.traer();
		} catch (Exception e) {
			throw new Exception("Error al traer todos los usuarios activos: " + e.getMessage());
		}
	}

	public List<Usuario> traerTodos() throws Exception {
		try {
			return usuarioDao.traerTodos();
		} catch (Exception e) {
			throw new Exception("Error al traer todos los usuarios: " + e.getMessage());
		}
	}
}