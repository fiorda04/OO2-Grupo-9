package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import datos.Rol;
import java.util.List;

public class RolDao {
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

    public int agregar(Rol objeto) {
        int id = 0;
        try {
            iniciaOperacion();
            id = (Integer) session.save(objeto);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            session.close();
        }
        return id;
    }

    public void actualizar(Rol objeto) {
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

    public void eliminar(Rol objeto) {
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

    public Rol traer(long idRol) { // ¡CAMBIAR int a long!
        Rol objeto = null;
        try {
            iniciaOperacion();
            objeto = (Rol) session.get(Rol.class, idRol);
        } finally {
            session.close();
        }
        return objeto;
    }

    public Rol traer(String nombreRol) {
        Rol objeto = null;
        try {
            iniciaOperacion();
            objeto = (Rol) session.createQuery("from Rol r where r.nombreRol = :nombreRol", Rol.class)
                    .setParameter("nombreRol", nombreRol)
                    .uniqueResult();
        } finally {
            session.close();
        }
        return objeto;
    }

    public List<Rol> traerTodos() {
        List<Rol> lista = null;
        try {
            iniciaOperacion();
            Query<Rol> query = session.createQuery("from Rol r order by r.nombreRol asc", Rol.class);
            lista = query.getResultList();
        } finally {
            session.close();
        }
        return lista;
    }
}