package datos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Ticket {
    private long idTicket;
    private String titulo;
    private String descripcion;
    private LocalDate fechaCreacion;
    private LocalDate fechaModEst;
    private Usuario usuario; // Relaci�n uno a uno
    private Estado estado;   // Relaci�n muchos a uno
    private Prioridad prioridad; // Relaci�n muchos a uno
    private Tipo tipo;       // Relaci�n muchos a uno
    private Set<Categoria> categorias = new HashSet<>(); // Relaci�n muchos a muchos
    private Respuesta respuesta; // Relaci�n uno a uno

    public Ticket() {
    }

    public Ticket(String titulo, String descripcion, Usuario usuario, Estado estado, Tipo tipo, Set<Categoria> categorias) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.usuario = usuario;
        this.estado = estado; //Se inicializa en estado id = 1. Abierto
        this.tipo = tipo;
        this.fechaCreacion = LocalDate.now();
        this.fechaModEst = LocalDate.now();
        this.prioridad = null; // Se inicializa como null
        this.respuesta = null; // Se inicializa como nulls
        this.categorias = categorias;
    }

    public long getIdTicket() {
        return idTicket;
    }

    protected void setIdTicket(long idTicket) {
        this.idTicket = idTicket;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaModEst() {
        return fechaModEst;
    }

    public void setFechaModEst(LocalDate fechaModEst) {
        this.fechaModEst = fechaModEst;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Set<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<Categoria> categorias) {
        this.categorias = categorias;
    }

    public boolean agregarCategoria(Categoria categoria) {
        if (categorias.add(categoria)) {
            categoria.getTickets().add(this);
            return true;
        }
        return false;
    }

    public boolean eliminarCategoria(Categoria categoria) {
        if (categorias.remove(categoria)) {
            categoria.getTickets().remove(this);
            return true;
        }
        return false;
    }

    public Respuesta getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta;
    }

	@Override
	public String toString() {
		return "Ticket [idTicket=" + idTicket + ", titulo=" + titulo + ", descripcion=" + descripcion
				+ ", fechaCreacion=" + fechaCreacion + ", fechaModEst=" + fechaModEst + ", usuario=" + usuario
				+ ", estado=" + estado + ", prioridad=" + prioridad + ", tipo=" + tipo + ", categorias=" + categorias
				+ ", respuesta=" + respuesta + "]";
	}

}