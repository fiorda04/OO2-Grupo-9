package dao;

import datos.Persona;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class PersonaDao {
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

    public Persona traer(long idPersona) {
        Persona objeto = null;
        try {
            iniciaOperacion();
            objeto = (Persona) session.get(Persona.class, idPersona);
        } finally {
            session.close();
        }
        return objeto;
    }

    public Persona traerPorDni(int dni) {
        Persona persona = null;
        try {
            iniciaOperacion();
            Query<Persona> query = session.createQuery("from Persona p where p.dni = :dni", Persona.class);
            query.setParameter("dni", dni);
            persona = query.uniqueResult();
        } finally {
            session.close();
        }
        return persona;
    }

    public Persona traerPorEmail(String email) {
        Persona persona = null;
        try {
            iniciaOperacion();
            Query<Persona> query = session.createQuery("from Persona p where p.email = :email", Persona.class);
            query.setParameter("email", email);
            persona = query.uniqueResult();
        } finally {
            session.close();
        }
        return persona;
    }

}