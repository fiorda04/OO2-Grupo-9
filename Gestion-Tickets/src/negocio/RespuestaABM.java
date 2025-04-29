package negocio;

import java.util.List;
import dao.RespuestaDao;
import datos.Respuesta;
import datos.Ticket;
import datos.Usuario;

public class RespuestaABM {
	RespuestaDao respuestaDao = new RespuestaDao();

    public Respuesta traer(int idRespuesta) {
        return respuestaDao.traer(idRespuesta);
    }

    public long agregar(String contenido, Usuario autor, Ticket ticket){
        //si es cliente no puede agregar
    	Respuesta respuesta = new Respuesta(contenido, autor, ticket);
        return respuestaDao.agregar(respuesta);
    }

    public void modificar(Respuesta respuesta){
        respuestaDao.actualizar(respuesta);
    }

    public void eliminar(int idRespuesta) throws Exception {
    	Respuesta respuesta = respuestaDao.traer(idRespuesta);
        respuestaDao.eliminar(respuesta);
    }

    public List<Respuesta> traer() {
        return respuestaDao.traerTodos();
    }
}