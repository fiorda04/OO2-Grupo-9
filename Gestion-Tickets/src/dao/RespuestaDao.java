package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import datos.Respuesta;
import datos.Usuario;

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

    public int agregar(Respuesta objeto) {
        int id = 0;
        try {
            iniciaOperacion();
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

    public void actualizar(Respuesta objeto) {
        try {
            iniciaOperacion();
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

    public Respuesta traer(int idRespuesta) {
    	Respuesta objeto = null;
        try {
            iniciaOperacion();
            objeto = (Respuesta) session.get(Respuesta.class, idRespuesta);
        } finally {
            session.close();
        }
        return objeto;
    }

    //Traer por idTicket y idAutor(Usuario id)
    //Traer despues las respuestas entre determinadas fechas?
    
/*
    public Respuesta traer(String nombreRol) {
    	Respuesta objeto = null;
        try {
            iniciaOperacion();
            objeto = (Rol) session.createQuery("from Respuesta r where r.nombreRol = :nombreRol", Respuesta.class)
                    .setParameter("nombreRol", nombreRol)
                    .uniqueResult();
        } finally {
            session.close();
        }
        return objeto;
    }
*/
    //Traer Respuestas por fecha de creacion.
    public List<Respuesta> traerRespuestasPorFechaCreacion(LocalDate fechaCreacion) {
        List<Respuesta> lista = null;
        try {
            iniciaOperacion();
            Query<Respuesta> query = session.createQuery("select r from Respuesta r where r.fechaCreacion = :fechaCreacion", Respuesta.class);
            query.setParameter("fechaCreacion", fechaCreacion);
            lista = query.getResultList();
        } finally {
            session.close();
        }
        return lista;
    }
    
  //Traer Respuestas por fecha de creacion y autor.
    public List<Respuesta> traerRespuestasPorFechaCreacionYAutor(LocalDate fechaCreacion, Usuario autor) {
        List<Respuesta> lista = null;
        try {
            iniciaOperacion();
            Query<Respuesta> query = session.createQuery("select r from Respuesta r where r.fechaCreacion = :fechaCreacion and r.autor = :autor", Respuesta.class);
            query.setParameter("fechaCreacion", fechaCreacion);
            query.setParameter("autor", autor);
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