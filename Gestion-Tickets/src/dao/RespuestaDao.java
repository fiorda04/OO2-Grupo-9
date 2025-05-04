package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import datos.Estado;
import datos.Respuesta;
import datos.Ticket;

import java.time.LocalDate;
import java.util.List;

public class RespuestaDao {
    private static Session session;
    private Transaction tx;

    private void iniciaOperacion() throws HibernateException {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
    }

    private void manejaExcepcion(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("ERROR en la capa de acceso a datos", he);
    }

    public int agregar(Respuesta objeto) throws Exception {
        int id = 0;
        try {
            iniciaOperacion();
            
            //Actualiza el ticket (fechaModEst y estado)
            Ticket ticket = objeto.getTicket();
            if (ticket.getEstado().getIdEstado() != 1L){
            	throw new Exception("El Id del Estado del Ticket es diferente a 1 (Abierto)");
            }
            if (objeto.getAutor().getRol().getIdRol() == 1L) {
            	throw new Exception("El autor tiene Rol de Cliente el cual no tiene acceso a crear Respuestas");
            }
            ticket.setFechaModEst(LocalDate.now()); // o LocalDate.now()
        	Estado nuevoEstado = (Estado) session.get(Estado.class, 2L);
        	objeto.getTicket().setEstado(nuevoEstado);
        	session.update(ticket);
            
        	
            Number generatedId = (Number) session.save(objeto);
            if (generatedId != null) {
                id = generatedId.intValue();
            }
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            session.close();
        }
        return id;
    }

    public void actualizar(Respuesta objeto) throws Exception {
        try {
            iniciaOperacion();
            
            if (objeto.getAutor().getRol().getIdRol() == 1L) {
            	throw new Exception("El autor tiene Rol de Cliente el cual no tiene acceso a modificar Respuestas");
            }
            
            Ticket ticket = objeto.getTicket();
            ticket.setFechaModEst(LocalDate.now());
            session.update(ticket);
            
            session.update(objeto);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            session.close();
        }
    }

    public void eliminar(Respuesta objeto) {
        try {
            iniciaOperacion();
            session.delete(objeto);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            session.close();
        }
    }

    public Respuesta traer(long idRespuesta) {
    	Respuesta objeto = null;
        try {
            iniciaOperacion();
            objeto = (Respuesta) session.get(Respuesta.class, idRespuesta);
        } finally {
            session.close();
        }
        return objeto;
    }
    
    //Traer Respuestas por id de Usuario
    public List<Respuesta> traerPorUsuario(long idUsuario) throws HibernateException {
        List<Respuesta> lista = null;
        try {
            iniciaOperacion();
            Query<Respuesta> query = session.createQuery("select r from Respuesta r join fetch r.autor a join fetch a.rol where a.idPersona = :idUsuario and a.activo = true", Respuesta.class);
            query.setParameter("idUsuario", idUsuario);
            lista = query.getResultList();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
        return lista;
    }

    //Traer Respuestas por fecha de creacion.
    public List<Respuesta> traerRespuestasPorFechaCreacion(LocalDate fechaCreacion) throws HibernateException {
        List<Respuesta> lista = null;
        try {
            iniciaOperacion();
            Query<Respuesta> query = session.createQuery("select r from Respuesta r where r.fechaResp = :fechaCreacion", Respuesta.class);
            query.setParameter("fechaCreacion", fechaCreacion);
            lista = query.getResultList();
        }catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
        return lista;
    }
    
  //Traer Respuestas por fecha de creacion y autor.
    public List<Respuesta> traerRespuestasPorFechaCreacionYAutor(LocalDate fechaCreacion, long idUsuario) throws HibernateException {
        List<Respuesta> lista = null;
        try {
            iniciaOperacion();
            Query<Respuesta> query = session.createQuery("select r from Respuesta r join fetch r.autor a join fetch a.rol join fetch r.ticket t where r.fechaResp = :fechaCreacion and a.idPersona = :idUsuario", Respuesta.class);
            query.setParameter("fechaCreacion", fechaCreacion);
            query.setParameter("idUsuario", idUsuario);
            lista = query.getResultList();
        }catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
        return lista;
    }
    
    
    //Traer respuestas entre dos fechas
    public List<Respuesta> traerRespuestasEntreFechas(LocalDate desde, LocalDate hasta) {
        List<Respuesta> lista = null;
        try {
            iniciaOperacion();
            Query<Respuesta> query = session.createQuery("from Respuesta r where r.fechaResp between :desde and :hasta", Respuesta.class
            );
            query.setParameter("desde", desde);
            query.setParameter("hasta", hasta);
            lista = query.getResultList();
        } finally {
            session.close();
        }
        return lista;
    }
    
    
    public List<Respuesta> traerTodos() {
        List<Respuesta> lista = null;
        try {
            iniciaOperacion();
            Query<Respuesta> query = session.createQuery("from Respuesta r order by r.idRespuesta asc", Respuesta.class);
            lista = query.getResultList();
        } finally {
            session.close();
        }
        return lista;
    }
}