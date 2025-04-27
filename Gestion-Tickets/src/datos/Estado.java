package datos;

import java.util.HashSet;
import java.util.Set;

public class Estado {
	private int idEstado;
	private String nombreEstado;
	private Set<Ticket> tickets = new HashSet<>();

	public Estado() {
	}

	public Estado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}

	public int getIdEstado() {
		return idEstado;
	}

	protected void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public String getNombreEstado() {
		return nombreEstado;
	}

	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
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
		return "Estado [idEstado=" + idEstado + ", nombreEstado=" + nombreEstado + "]";
	}
}