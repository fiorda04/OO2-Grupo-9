package datos;

import java.util.HashSet;
import java.util.Set;

public class Categoria {
	private int idCategoria;
	private String nombre;
	private Set<Ticket> tickets = new HashSet<>();

	public Categoria() {
	}

	public Categoria(String nombre) {
		this.nombre = nombre;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	protected void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}

	public boolean agregarTicket(Ticket ticket) {
		return this.tickets.add(ticket);
	}

	public boolean eliminarTicket(Ticket ticket) {
		return this.tickets.remove(ticket);
	}

	@Override
	public String toString() {
		return "Categoria [idCategoria=" + idCategoria + ", nombre=" + nombre + "]";
	}
}