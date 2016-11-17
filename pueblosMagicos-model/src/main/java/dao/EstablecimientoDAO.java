package dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.Establecimiento;

@Repository
public class EstablecimientoDAO {

	private static final Log log = LogFactory.getLog(EstablecimientoDAO.class);

	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	public boolean create(Establecimiento transientInstance) {
		log.debug("creating Establecimiento instance");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		boolean conf = false; 
		try {
			tx = session.beginTransaction();
			System.out.println("val " + transientInstance.getVIdUsuario());
			session.save(transientInstance);
			tx.commit();
			conf = true;
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return conf;
	}
	
	public Establecimiento read(Integer id) {
		log.debug("reading Establecimiento instance");
		Establecimiento u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (Establecimiento)session.get(Establecimiento.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}

	public List<Establecimiento> readAll() {
		List<Establecimiento> result = null;
		Session session = sessionFactory.openSession();
		try
		{
			result = session.createCriteria(Establecimiento.class).list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}
	
	public boolean update(Establecimiento transientInstance) {
		log.debug("updating Establecimiento instance");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		boolean conf = false; 
		try {
			tx = session.beginTransaction();
			session.update(transientInstance);
			tx.commit();
			conf = true;
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return conf;
	}

	public boolean delete(Establecimiento transientInstance) {
		log.debug("deleting Establecimiento instance");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		boolean conf = false;
		try {
			tx = session.beginTransaction();
			session.delete(transientInstance);
			tx.commit();
			conf = true;
			log.debug("persist successful");
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return conf;
	}


	public Establecimiento findById(java.lang.Integer id) {
		log.debug("getting Establecimiento instance with id: " + id);
		Establecimiento u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			 u = (Establecimiento) session.get(Establecimiento.class, id);
			tx.commit();
			if (u == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}

	public Establecimiento findByNombreEstablecimiento(String n) {
		log.debug("finding Establecimiento instance by example");
		Session session = sessionFactory.openSession();
		List<Establecimiento> results = null;
		Establecimiento result = null;
		try {
			results = session.createCriteria(Establecimiento.class).add( Restrictions.like("nombreEstablecimiento", n) ).list();
			result = results.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		session.close();
		return result;
	
	}
	
	
	public List<Establecimiento> findByIdPST(Integer id) {
		log.debug("finding Establecimiento instance by example");
		Session session = sessionFactory.openSession();
		List<Establecimiento> results = null;
		try 
		{
			results = session.createCriteria(Establecimiento.class).add( Restrictions.like("pstIdUsuario", id) ).list();
			log.debug("find by example successful, result size: " + results.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return results;
	}
	
	public List<Establecimiento> findByIdEstablecimiento(Integer id, Integer first, Integer numRegistros) {
		log.debug("finding Establecimiento instance by example");
		Session session = sessionFactory.openSession();
		List<Establecimiento> results = null;
		try 
		{
			results = session.createCriteria(Establecimiento.class)
					.add( Restrictions.like("erIdEstadoRegistro", id) ).setMaxResults(numRegistros).setFirstResult(first).list();
			log.debug("find by example successful, result size: " + results.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return results;
	}
	
	public String getDireccion(Integer idEstablecimiento)
	{
		String direccion = null;
		Session session = sessionFactory.openSession();
		try
		{
			
			
			Query query = session.createSQLQuery("select ta.nombre "
					+ "from tipoasentamiento ta, asentamiento a, establecimiento e "
					+ "where ta.idtipoAsentamiento=a.tA_idtipoAsentamiento "
					+ "and a.idAsentamiento=e.a_idAsentamiento "
					+ "and e.idEstablecimiento=:id")
					.setParameter("id", idEstablecimiento); 
			direccion = (String) query.uniqueResult();
			
			query = session.createSQLQuery("select a.nombreAsentamiento "
					+ "from asentamiento a, establecimiento e "
					+ "where a.idAsentamiento=e.a_idAsentamiento "
					+ "and e.idEstablecimiento=:id")
					.setParameter("id", idEstablecimiento); 
			direccion = direccion + " " + (String) query.uniqueResult();
			
			query = session.createSQLQuery("select m.nombreMunicipio "
					+ "from asentamiento a, establecimiento e, municipio m "
					+ "where m.idMunicipio=a.m_idMunicipio "
					+ "and a.idAsentamiento=e.a_idAsentamiento "
					+ "and  e.idEstablecimiento=:id")
					.setParameter("id", idEstablecimiento); 
			direccion = direccion + ", " + (String) query.uniqueResult();
			
			query = session.createSQLQuery("select e.nombreEstado "
					+ "from asentamiento a, establecimiento es, municipio m, estado e "
					+ "where e.idEstado=e_idEstado "
					+ "and m.idMunicipio=a.m_idMunicipio "
					+ "and a.idAsentamiento=es.a_idAsentamiento "
					+ "and es.idEstablecimiento=:id")
					.setParameter("id", idEstablecimiento); 
			direccion = direccion + ", " + (String) query.uniqueResult();
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return direccion;
	}
	
}
