package dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import datos.Usuario;

public class UsuarioDao {
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

    public long agregar(Usuario objeto) throws Exception { // Cambiamos la excepción a Exception
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
            if (he.getCause() instanceof ConstraintViolationException) {
                throw new Exception("Nombre de usuario duplicado en la base de datos.");
            }
            throw he; 
        } finally {
            session.close();
        }
        return id;
    }

    public void actualizar(Usuario objeto) throws HibernateException {
        try {
            iniciaOperacion();
            session.merge(objeto); 
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
    }

    public void bajaLogica(Usuario objeto) throws HibernateException {
        try {
            iniciaOperacion();
            objeto.setActivo(false);
            session.update(objeto);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
    }

    public void bajaLogica(String nombreUsuario) throws HibernateException {
        try {
            iniciaOperacion();
            Query query = session.createQuery("update Usuario u set u.activo = false where u.nombreUsuario = :nombreUsuario");
            query.setParameter("nombreUsuario", nombreUsuario);
            query.executeUpdate();
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
    }

    public Usuario traer(long idUsuario) throws HibernateException {
        Usuario objeto = null;
        try {
            iniciaOperacion();
            objeto = (Usuario) session.get(Usuario.class, idUsuario);
            if (objeto != null && !objeto.isActivo()) {
                objeto = null;
            }
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
        return objeto;
    }
    
    public Usuario traerActivoEInactivo(long idUsuario) throws HibernateException {
        Usuario objeto = null;
        try {
            iniciaOperacion();
            objeto = (Usuario) session.get(Usuario.class, idUsuario);
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
        return objeto;
    }

    public Usuario traer(String nombreUsuario) throws HibernateException {
        Usuario usuario = null;
        try {
            iniciaOperacion();
            usuario = (Usuario) session.createQuery("select u from Usuario u left join fetch u.rol where u.nombreUsuario = :nombreUsuario", Usuario.class)
                    .setParameter("nombreUsuario", nombreUsuario)
                    .uniqueResult();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
        return usuario;
    }

    public Usuario traerPorDni(int dni) throws HibernateException {
        Usuario usuario = null;
        try {
            iniciaOperacion();
            usuario = (Usuario) session.createQuery("select u from Usuario u left join fetch u.rol where u.dni = :dni and u.activo = true", Usuario.class)
                    .setParameter("dni", dni)
                    .uniqueResult();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
        return usuario;
    }
    
    public Usuario traerPorEmail(String email) throws HibernateException {
        Usuario usuario = null;
        try {
            iniciaOperacion();
            usuario = (Usuario) session.createQuery("select u from Usuario u left join fetch u.rol r where u.email = :email and u.activo = true", Usuario.class)
                    .setParameter("email", email)
                    .uniqueResult();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
        return usuario;
    }

    public List<Usuario> traer() throws HibernateException {
        List<Usuario> lista = null;
        try {
            iniciaOperacion();
            Query<Usuario> query = session.createQuery("select u from Usuario u left join fetch u.rol where u.activo = true order by u.nombreUsuario asc", Usuario.class);
            lista = query.getResultList();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
        return lista;
    }

    public List<Usuario> traerPorRol(long idRol) throws HibernateException {
        List<Usuario> lista = null;
        try {
            iniciaOperacion();
            Query<Usuario> query = session.createQuery("select u from Usuario u left join fetch u.rol r where r.idRol = :idRol and u.activo = true order by u.nombreUsuario asc", Usuario.class);
            query.setParameter("idRol", idRol);
            lista = query.getResultList();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
        return lista;
    }

    public List<Usuario> traerTodos() throws HibernateException {
        List<Usuario> lista = null;
        try {
            iniciaOperacion();
            Query<Usuario> query = session.createQuery("select u from Usuario u left join fetch u.rol order by u.nombreUsuario asc", Usuario.class);
            lista = query.getResultList();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
        return lista;
    }
}