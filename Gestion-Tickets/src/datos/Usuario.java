package datos;

public class Usuario extends Persona {
	private String nombreUsuario;
	private String contrasenia;
	private Rol rol;
	private boolean activo;

	public Usuario() {
	}

	public Usuario(String nombre, String apellido, int dni, String email, String telefono, String nombreUsuario,
			String contrasenia, Rol rol) {
		super(nombre, apellido, dni, email, telefono);
		this.nombreUsuario = nombreUsuario;
		this.contrasenia = contrasenia;
		this.rol = rol;
		this.setActivo(true);
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "Usuario [" + super.toString() + ", nombreUsuario=" + nombreUsuario + ", contrasenia=" + contrasenia
				+ ", rol=" + rol + "]";
	}
}