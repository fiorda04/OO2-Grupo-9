package datos;

public class Usuario extends Persona {
	private String nombreUsuario;
	private String contrase�a;
	private Rol rol;

	public Usuario() {
	}

	public Usuario(String nombre, String apellido, int dni, String email, String telefono, String nombreUsuario,
			String contrase�a, Rol rol) {
		super(nombre, apellido, dni, email, telefono);
		this.nombreUsuario = nombreUsuario;
		this.contrase�a = contrase�a;
		this.rol = rol;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Usuario [" + super.toString() + ", nombreUsuario=" + nombreUsuario + ", contrase�a=" + contrase�a
				+ ", rol=" + rol + "]";
	}
}