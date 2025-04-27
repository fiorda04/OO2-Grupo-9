package datos;

import java.time.LocalDate;

public class Respuesta {
	private int idRespuesta;
	private String contenido;
	private LocalDate fechaResp;
	private Usuario autor; // Relación muchos a uno
	private Ticket ticket; // Relación uno a uno

	public Respuesta() {
	}

	public Respuesta(String contenido, Usuario autor, Ticket ticket) {
        this.contenido = contenido;
        this.autor = autor;
        this.ticket = ticket;
        this.fechaResp = LocalDate.now();
    }

	public int getIdRespuesta() {
		return idRespuesta;
	}

	protected void setIdRespuesta(int idRespuesta) {
		this.idRespuesta = idRespuesta;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public LocalDate getFechaResp() {
		return fechaResp;
	}

	public void setFechaResp(LocalDate fechaResp) {
		this.fechaResp = fechaResp;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	@Override
	public String toString() {
		return "Respuesta [idRespuesta=" + idRespuesta + ", contenido=" + contenido + ", fechaResp=" + fechaResp
				+ ", autor=" + autor + ", ticket=" + ticket + "]";
	}
}