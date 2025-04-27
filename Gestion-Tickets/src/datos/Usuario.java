package datos;

public class Usuario extends Persona {
	private String nombreUsuario;
	private String contraseña;
	private Rol rol;

	public Usuario() {
	}

	public Usuario(String nombre, String apellido, int dni, String email, String telefono, String nombreUsuario,
			String contraseña, Rol rol) {
		super(nombre, apellido, dni, email, telefono);
		this.nombreUsuario = nombreUsuario;
		this.contraseña = contraseña;
		this.rol = rol;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Usuario [" + super.toString() + ", nombreUsuario=" + nombreUsuario + ", contraseña=" + contraseña
				+ ", rol=" + rol + "]";
	}
}