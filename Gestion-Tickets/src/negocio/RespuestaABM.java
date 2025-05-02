package negocio;

import java.time.LocalDate;
import java.util.List;
import dao.RespuestaDao;
import datos.Respuesta;
import datos.Ticket;
import datos.Usuario;

public class RespuestaABM {
	RespuestaDao respuestaDao = new RespuestaDao();

    public Respuesta traer(long idRespuesta) {
        return respuestaDao.traer(idRespuesta);
    }

    public List<Respuesta> traerPorUsuario(long idUsuario) throws Exception {
		try {
			return respuestaDao.traerPorUsuario(idUsuario);
		} catch (Exception e) {
			throw new Exception("Error al traer respuestas por usuario: " + e.getMessage());
		}
	}
    
    public List<Respuesta> traerRespuestasPorFechaCreacion(LocalDate fechaCreacion) throws Exception {
		try {
			return respuestaDao.traerRespuestasPorFechaCreacion(fechaCreacion);
		} catch (Exception e) {
			throw new Exception("Error al traer respuestas por fecha de creacion: " + e.getMessage());
		}
	}
    
    public List<Respuesta> traerRespuestasPorFechaCreacionYAutor(LocalDate fechaCreacion, long idUsuario) throws Exception {
		try {
			return respuestaDao.traerRespuestasPorFechaCreacionYAutor(fechaCreacion, idUsuario);
		} catch (Exception e) {
			throw new Exception("Error al traer respuestas por fecha de creacion y autor: " + e.getMessage());
		}
	}
    
    public List<Respuesta> traerRespuestasEntreFechas(LocalDate desde, LocalDate hasta) throws Exception {
		try {
			return respuestaDao.traerRespuestasEntreFechas(desde, hasta);
		} catch (Exception e) {
			throw new Exception("Error al traer respuestas entre fechas: " + e.getMessage());
		}
	}
    
    public long agregar(String contenido, Usuario autor, Ticket ticket) throws Exception{
    	Respuesta respuesta = new Respuesta(contenido, autor, ticket);
        return respuestaDao.agregar(respuesta);
    }

    public void modificar(Respuesta respuesta) throws Exception{
        respuestaDao.actualizar(respuesta);
    }

    public void eliminar(long idRespuesta) throws Exception {
    	Respuesta respuesta = respuestaDao.traer(idRespuesta);
        respuestaDao.eliminar(respuesta);
    }

    public List<Respuesta> traer() {
        return respuestaDao.traerTodos();
    }
}