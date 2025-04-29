package datos;

import java.util.HashSet;
import java.util.Set;

public class Prioridad {
	private long idPrioridad;
	private String nombrePrioridad;
	private Set<Ticket> tickets = new HashSet<>();

	public Prioridad() {
	}

	public Prioridad(String nombrePrioridad) {
		this.nombrePrioridad = nombrePrioridad;
	}

	public long getIdPrioridad() {
		return idPrioridad;
	}

	protected void setIdPrioridad(long idPrioridad) {
		this.idPrioridad = idPrioridad;
	}

	public String getNombrePrioridad() {
		return nombrePrioridad;
	}

	public void setNombrePrioridad(String nombrePrioridad) {
		this.nombrePrioridad = nombrePrioridad;
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
		return "Prioridad [idPrioridad=" + idPrioridad + ", nombrePrioridad=" + nombrePrioridad + "]";
	}
}