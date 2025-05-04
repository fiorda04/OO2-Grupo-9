package dao;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import datos.Categoria;

public class CategoriaDao {
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

    public long agregar(Categoria objeto) {
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

    public void actualizar(Categoria objeto) {
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

    public void eliminar(Categoria objeto) {
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

    public Categoria traer(long idCategoria) {
        Categoria objeto = null;
        try {
            iniciaOperacion();
            objeto = (Categoria) session.get(Categoria.class, idCategoria);
        } finally {
            session.close();
        }
        return objeto;
    }


    public List<Categoria> traer() {
        List<Categoria> lista = null;
        try {
            iniciaOperacion();
            Query<Categoria> query = session.createQuery("select c from Categoria c order by e.idCategoria asc", Categoria.class);
            lista = query.getResultList();
        } finally {
            session.close();
        }
        return lista;
    }

}
