package datos;

import java.util.HashSet;
import java.util.Set;

public class Rol {
	private int idRol;
	private String nombreRol;
	private Set<Usuario> usuarios = new HashSet<>(); // Agregamos la colección de Usuarios

	public Rol() {
	}

	public Rol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	public int getIdRol() {
		return idRol;
	}

	protected void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	// Getter para la colección de usuarios
	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	// Setter para la colección de usuarios (opcional, pero recomendado)
	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@Override
	public String toString() {
		return "Rol [idRol=" + idRol + ", nombreRol=" + nombreRol + "]";
	}
}