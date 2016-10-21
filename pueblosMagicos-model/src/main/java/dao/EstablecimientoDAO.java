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
	
	public Establecimiento read(int id) {
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
		result = session.createCriteria(Establecimiento.class).list();
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
		try {
			List<Establecimiento> results = session.createCriteria(Establecimiento.class).add( Restrictions.like("nombreEstablecimiento", n) ).list();
			log.debug("find by example successful, result size: " + results.size());
			return results.get(0);
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<Establecimiento> findByIdPST(Integer id) {
		log.debug("finding Establecimiento instance by example");
		Session session = sessionFactory.openSession();
		try {
			List<Establecimiento> results = session.createCriteria(Establecimiento.class).add( Restrictions.like("PST_idUsuario", id) ).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	
}
