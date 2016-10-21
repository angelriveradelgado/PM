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

import dto.AsentamientoId;
import dto.Asentamiento;

@Repository
public class AsentamientoDAO {
	private static final Log log = LogFactory.getLog(AsentamientoDAO.class);

	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	public boolean create(Asentamiento transientInstance) {
		log.debug("creating Asentamiento instance");
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
	
	public Asentamiento read(AsentamientoId id) {
		log.debug("reading Asentamiento instance");
		Asentamiento u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (Asentamiento)session.get(Asentamiento.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}
	
	public List<Asentamiento> readAll() {
		List<Asentamiento> result = null;
		Session session = sessionFactory.openSession();
		result = session.createCriteria(Asentamiento.class).list();
		session.close();
		return result;
	}

	public boolean update(Asentamiento transientInstance) {
		log.debug("updating Asentamiento instance");
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

	public boolean delete(Asentamiento transientInstance) {
		log.debug("deleting Asentamiento instance");
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


	public Asentamiento findById(java.lang.Integer id) {
		log.debug("getting Asentamiento instance with id: " + id);
		Asentamiento u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			 u = (Asentamiento) session.get(Asentamiento.class, id);
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

	public Asentamiento findByNombreAsentamiento(String n) {
		log.debug("finding Asentamiento instance by example");
		Session session = sessionFactory.openSession();
		try {
			List<Asentamiento> results = session.createCriteria(Asentamiento.class).add( Restrictions.like("nombreAsentamiento", n) ).list();
			log.debug("find by example successful, result size: " + results.size());
			session.close();
			return results.get(0);
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			session.close();
			throw re;
		}
	}
	
	
	
}
