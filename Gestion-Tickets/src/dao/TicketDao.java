package dao;

import java.util.List;
import java.time.LocalDate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import datos.Estado;
import datos.Prioridad;
import datos.Ticket;
import datos.Tipo;
import datos.Usuario;

public class TicketDao {
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

	public long agregar(Ticket objeto) {
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

	public void actualizar(Ticket objeto) {
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

	public void eliminar(Ticket objeto) {
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

	public Ticket traer(long idTicket) throws HibernateException {
		Ticket objeto = null;
		try {
			iniciaOperacion();
			objeto = (Ticket) session.get(Ticket.class, idTicket);
		} catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
		} finally {
			session.close();
		}
		return objeto;
	}

	public List<Ticket> traer() {
		List<Ticket> lista = null;
		try {
			iniciaOperacion();
			Query<Ticket> query = session.createQuery("select t from Ticket t order by t.idTicket asc", Ticket.class);
			lista = query.getResultList();
		} finally {
			session.close();
		}
		return lista;
	}

	public List<Ticket> traerTicketPorTipo(Tipo t) {
		List<Ticket> lista = null;
		try {
			iniciaOperacion();
			Query<Ticket> query = session.createQuery("select t from Ticket t where t.tipo = :tipo", Ticket.class);
			query.setParameter("tipo", t);
			lista = query.getResultList();
		} finally {
			session.close();
		}
		return lista;
	}

	//trae la lista de tickets por cliente
	public List<Ticket> traerPorCliente(Usuario cliente) {
    	List<Ticket> lista = null;
    	try {
    		iniciaOperacion();
    		Query<Ticket> query = session.createQuery("from Ticket t where t.usuario = :usuario order by t.idTicket asc", Ticket.class);
    		query.setParameter("usuario", cliente);
    		lista = query.getResultList();
    	} finally {
    		session.close();
    	}
    	return lista;
	}
	
	//trae la lista de tickets por empleado
	 public List<Ticket> traerPorEmpleado(Usuario empleado){
	        List<Ticket> lista = null;
	        try {
	            iniciaOperacion();
	            Query<Ticket> query = session.createQuery("from Ticket t where t.respuesta.autor = :empleado order by t.idTicket asc", Ticket.class);
	            query.setParameter("empleado", empleado);
	            lista = query.getResultList();
	        } finally {
	            session.close();
	        }
	        return lista;
	    }

  //trae la lista de tickets por fecha
    public List<Ticket> traerPorFecha(LocalDate fecha){
    	List<Ticket> lista = null;
    	try {
    		iniciaOperacion();
    		Query<Ticket> query = session.createQuery("from Ticket t where t.fechaCreacion = :fecha order by t.idTicket asc", Ticket.class);
    		query.setParameter("fecha", fecha);
    		lista = query.getResultList();
    	} finally {
    		session.close();
    	}
    	return lista;
    }
    
  //trae la lista de ticket por prioridad
    public List<Ticket> traerTicketPorPrioridad(Prioridad prioridad){
    	List<Ticket> lista = null;
    	try {
    		iniciaOperacion();
    		Query<Ticket> query = session.createQuery("from Ticket t where t.prioridad = :prioridad order by t.idTicket asc", Ticket.class);
    		query.setParameter("prioridad", prioridad);
    		lista = query.getResultList();
    	} finally {
    		session.close();
    	}
    	return lista;
    }
    
  //trae los tickets por estado
    public List<Ticket> traerTicketPorEstado(Estado estado){
    	List<Ticket> lista = null;
    	try {
    		iniciaOperacion();
    		Query<Ticket> query = session.createQuery("from Ticket t where t.estado = :estado order by t.idTicket asc", Ticket.class);
    		query.setParameter("estado", estado);
    		lista = query.getResultList();
    	} finally {
    		session.close();
    	}
    	return lista;
    }
    
  //traer los tickets entre fechas
    public List<Ticket> traerTicketEntreFechas(LocalDate fechaInicio, LocalDate fechaFin){
    	List<Ticket> lista = null;
    	try {
    		iniciaOperacion();
    		Query<Ticket> query = session.createQuery("from Ticket t where t.fechaCreacion between :inicio and :fin order by t.fechaCreacion asc", Ticket.class);
    		query.setParameter("inicio", fechaInicio);
    		query.setParameter("fin", fechaFin);
    		lista = query.getResultList();
    	} finally {
    		session.close();
    	}
    	return lista;
    }
}
