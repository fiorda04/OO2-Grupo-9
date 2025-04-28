package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import datos.Prioridad;

public class PrioridadDao {
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

    public long agregar(Prioridad objeto) {
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

    public void actualizar(Prioridad objeto) {
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

    public void eliminar(Prioridad objeto) {
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

    public Prioridad traer(long idPrioridad) {
        Prioridad objeto = null;
        try {
            iniciaOperacion();
            objeto = (Prioridad) session.get(Prioridad.class, idPrioridad);
        } finally {
            session.close();
        }
        return objeto;
    }

    public Prioridad traer(String nombrePrioridad) {
        Prioridad usuario = null;
        try {
            iniciaOperacion();
            usuario = (Prioridad) session.createQuery("from Prioridad p where p.nombrePrioridad = :nombrePrioridad", Prioridad.class)
                    .setParameter("nombrePrioridad", nombrePrioridad)
                    .uniqueResult();
        } finally {
            session.close();
        }
        return usuario;
    }

    public List<Prioridad> traer() {
        List<Prioridad> lista = null;
        try {
            iniciaOperacion();
            Query<Prioridad> query = session.createQuery("select p from Prioridad p order by p.idPrioridad asc", Prioridad.class);
            lista = query.getResultList();
        } finally {
            session.close();
        }
        return lista;
    }
}
