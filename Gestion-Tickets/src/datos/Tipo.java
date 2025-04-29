package datos;

import java.util.HashSet;
import java.util.Set;

public class Tipo {
	private long idTipo;
	private String nombreTipo;
	private Set<Ticket> tickets = new HashSet<>();

	public Tipo() {
	}

	public Tipo(String nombreTipo) {
		this.nombreTipo = nombreTipo;
	}

	public long getIdTipo() {
		return idTipo;
	}

	protected void setIdTipo(long idTipo) {
		this.idTipo = idTipo;
	}

	public String getNombreTipo() {
		return nombreTipo;
	}

	public void setNombreTipo(String nombreTipo) {
		this.nombreTipo = nombreTipo;
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
		return "Tipo [idTipo=" + idTipo + ", nombreTipo=" + nombreTipo + "]";
	}
}