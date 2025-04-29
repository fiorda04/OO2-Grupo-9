package dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
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

    public long agregar(Usuario objeto) {
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

    public void actualizar(Usuario objeto) {
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

    public void eliminar(Usuario objeto) {
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
    
    public void eliminar(String nombreUsuario) throws HibernateException {
        try {
            iniciaOperacion();
            Query query = session.createQuery("delete Usuario u where u.nombreUsuario = :nombreUsuario");
            query.setParameter("nombreUsuario", nombreUsuario);
            int result = query.executeUpdate();
            tx.commit();
            if (result > 0) {
                System.out.println("Usuario con nombre de usuario '" + nombreUsuario + "' eliminado.");
            } else {
                System.out.println("No se encontró usuario con nombre de usuario '" + nombreUsuario + "' para eliminar.");
            }
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            session.close();
        }
    }


    public Usuario traer(long idUsuario) {
        Usuario objeto = null;
        try {
            iniciaOperacion();
            objeto = (Usuario) session.get(Usuario.class, idUsuario);
        } finally {
            session.close();
        }
        return objeto;
    }

    public Usuario traer(String nombreUsuario) {
        Usuario usuario = null;
        try {
            iniciaOperacion();
            // Consulta HQL con fetch join para cargar el rol de forma eager
            usuario = (Usuario) session.createQuery("select u from Usuario u left join fetch u.rol where u.nombreUsuario = :nombreUsuario", Usuario.class)
                    .setParameter("nombreUsuario", nombreUsuario)
                    .uniqueResult();
        } finally {
            session.close();
        }
        return usuario;
    }

    public Usuario traerPorDni(int dni) {
        Usuario usuario = null;
        try {
            iniciaOperacion();
            // Consulta HQL con fetch join para cargar el rol de forma eager
            usuario = (Usuario) session.createQuery("select u from Usuario u left join fetch u.rol where u.dni = :dni", Usuario.class)
                    .setParameter("dni", dni)
                    .uniqueResult();
        } finally {
            session.close();
        }
        return usuario;
    }

    public List<Usuario> traer() {
        List<Usuario> lista = null;
        try {
            iniciaOperacion();
            Query<Usuario> query = session.createQuery("select u from Usuario u left join fetch u.rol order by u.nombreUsuario asc", Usuario.class);
            lista = query.getResultList();
        } finally {
            session.close();
        }
        return lista;
    }
    
    public List<Usuario> traerPorRol(long idRol) {
        List<Usuario> lista = null;
        try {
            iniciaOperacion();
            // Consulta HQL con fetch join para cargar el rol y filtrar por idRol
            Query<Usuario> query = session.createQuery("select u from Usuario u left join fetch u.rol r where r.idRol = :idRol order by u.nombreUsuario asc", Usuario.class);
            query.setParameter("idRol", idRol);
            lista = query.getResultList();
        } finally {
            session.close();
        }
        return lista;
    }
}