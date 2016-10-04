package dao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.TipoatractivoHasTipoturismo;
import dto.TipoatractivoHasTipoturismoId;

@Repository
public class TipoatractivoHasTipoturismoDAO {

	private static final Log log = LogFactory.getLog(TipoatractivoHasTipoturismoDAO.class);

	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	public boolean create(TipoatractivoHasTipoturismo transientInstance) {
		log.debug("creating TipoatractivoHasTipoturismo instance");
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
	
	public TipoatractivoHasTipoturismo read(TipoatractivoHasTipoturismoId id) {
		log.debug("reading TipoatractivoHasTipoturismo instance");
		TipoatractivoHasTipoturismo u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (TipoatractivoHasTipoturismo)session.get(TipoatractivoHasTipoturismo.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}

	public boolean update(TipoatractivoHasTipoturismo transientInstance) {
		log.debug("updating TipoatractivoHasTipoturismo instance");
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

	public boolean delete(TipoatractivoHasTipoturismo transientInstance) {
		log.debug("deleting TipoatractivoHasTipoturismo instance");
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


	public TipoatractivoHasTipoturismo findById(java.lang.Integer id) {
		log.debug("getting TipoatractivoHasTipoturismo instance with id: " + id);
		TipoatractivoHasTipoturismo u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			 u = (TipoatractivoHasTipoturismo) session.get(TipoatractivoHasTipoturismo.class, id);
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
}
