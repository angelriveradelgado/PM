package dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.Estadoregistroservicioturistico;

@Repository
public class EstadoregistroservicioturisticoDAO  {

	private static final Log log = LogFactory.getLog(EstadoregistroservicioturisticoDAO.class);

	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	public boolean create(Estadoregistroservicioturistico transientInstance) {
		log.debug("creating Estadoregistroservicioturistico instance");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		boolean conf = false; 
		try {
			tx = session.beginTransaction();
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
	
	public Estadoregistroservicioturistico read(Integer id) {
		log.debug("reading Estadoregistroservicioturistico instance");
		Estadoregistroservicioturistico u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (Estadoregistroservicioturistico)session.get(Estadoregistroservicioturistico.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}
	
	public List<Estadoregistroservicioturistico> readAll() {
		List<Estadoregistroservicioturistico> result = null;
		Session session = sessionFactory.openSession();
		try
		{
			result = session.createCriteria(Estadoregistroservicioturistico.class).list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}

	public boolean update(Estadoregistroservicioturistico transientInstance) {
		log.debug("updating Estadoregistroservicioturistico instance");
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

	public boolean delete(Estadoregistroservicioturistico transientInstance) {
		log.debug("deleting Estadoregistroservicioturistico instance");
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
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return conf;
	}


	public Estadoregistroservicioturistico findById(java.lang.Integer id) {
		log.debug("getting Estadoregistroservicioturistico instance with id: " + id);
		Estadoregistroservicioturistico u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			 u = (Estadoregistroservicioturistico) session.get(Estadoregistroservicioturistico.class, id);
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

	public Estadoregistroservicioturistico findByNombreEstadoregistroservicioturistico(String n) {
		log.debug("finding Estadoregistroservicioturistico instance by example");
		List<Estadoregistroservicioturistico> results =  null;
		Estadoregistroservicioturistico result = null;
		Session session = sessionFactory.openSession();
		try {
			results = session.createCriteria(Estadoregistroservicioturistico.class).add( Restrictions.like("nombreEstadoregistroservicioturistico", n) ).list();
			log.debug("find by example successful, result size: " + results.size());
			result = results.get(0);
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			re.printStackTrace();
		}
		session.close();
		return result;
	}
	
	
}
