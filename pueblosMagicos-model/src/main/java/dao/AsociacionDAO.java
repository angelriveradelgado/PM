package dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.Asociacion;

@Repository
public class AsociacionDAO {

	private static final Log log = LogFactory.getLog(AsociacionDAO.class);

	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	public boolean create(Asociacion transientInstance) {
		log.debug("creating Asociacion instance");
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
	
	public Asociacion read(int id) {
		log.debug("reading Asociacion instance");
		Asociacion u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (Asociacion)session.get(Asociacion.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}
	
	public List<Asociacion> readAll() {
		List<Asociacion> result = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			result = session.createCriteria(Asociacion.class).list();
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return result;
	}

	public boolean update(Asociacion transientInstance) {
		log.debug("updating Asociacion instance");
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

	public boolean delete(Asociacion transientInstance) {
		log.debug("deleting Asociacion instance");
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


	public Asociacion findById(java.lang.Integer id) {
		log.debug("getting Asociacion instance with id: " + id);
		Asociacion u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			 u = (Asociacion) session.get(Asociacion.class, id);
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

	public Asociacion findByNombreAsociacion(String n) {
		log.debug("finding Asociacion instance by example");
		List<Asociacion> results = null;
		Asociacion result = null;
		Session session = sessionFactory.openSession();
		try {
			results = session.createCriteria(Asociacion.class).add( Restrictions.like("nombreAsociacion", n) ).list();
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
