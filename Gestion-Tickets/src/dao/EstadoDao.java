package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import datos.Estado;

public class EstadoDao {
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

    public long agregar(Estado objeto) {
        long id = 0;
        try {
            iniciaOperacion();
            Number generatedId = (Number) session.save(objeto);
            if (generatedId != null) {
                id = generatedId.longValue();
            }
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            session.close();
        }
        return id;
    }

    public void actualizar(Estado objeto) {
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

    public void eliminar(Estado objeto) {
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

    public Estado traer(long idEstado) {
        Estado objeto = null;
        try {
            iniciaOperacion();
            objeto = (Estado) session.get(Estado.class, idEstado);
        } finally {
            session.close();
        }
        return objeto;
    }

    public Estado traer(String nombreEstado) {
        Estado usuario = null;
        try {
            iniciaOperacion();
            usuario = (Estado) session.createQuery("from Estado e where e.nombreEstado = :nombreEstado", Estado.class)
                    .setParameter("nombreEstado", nombreEstado)
                    .uniqueResult();
        } finally {
            session.close();
        }
        return usuario;
    }

    public List<Estado> traer() {
        List<Estado> lista = null;
        try {
            iniciaOperacion();
            Query<Estado> query = session.createQuery("select e from Estado e order by e.idEstado asc", Estado.class);
            lista = query.getResultList();
        } finally {
            session.close();
        }
        return lista;
    }
}
