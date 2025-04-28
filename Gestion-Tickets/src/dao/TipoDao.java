package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import datos.Tipo;

public class TipoDao {
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

	    public long agregar(Tipo objeto) {
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

	    public void actualizar(Tipo objeto) {
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

	    public void eliminar(Tipo objeto) {
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

	    public Tipo traer(long idTipo) {
	        Tipo objeto = null;
	        try {
	            iniciaOperacion();
	            objeto = (Tipo) session.get(Tipo.class, idTipo);
	        } finally {
	            session.close();
	        }
	        return objeto;
	    }

	    public Tipo traer(String nombreTipo) {
	        Tipo usuario = null;
	        try {
	            iniciaOperacion();
	            usuario = (Tipo) session.createQuery("from Tipo t where t.nombreTipo = :nombreTipo", Tipo.class)
	                    .setParameter("nombreTipo", nombreTipo)
	                    .uniqueResult();
	        } finally {
	            session.close();
	        }
	        return usuario;
	    }

	    public List<Tipo> traer() {
	        List<Tipo> lista = null;
	        try {
	            iniciaOperacion();
	            Query<Tipo> query = session.createQuery("select t from Tipo t order by t.idTipo asc", Tipo.class);
	            lista = query.getResultList();
	        } finally {
	            session.close();
	        }
	        return lista;
	    }
	
}
