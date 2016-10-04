package dao;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.TipoturismoHasTiposervicioturistico;
import dto.TipoturismoHasTiposervicioturisticoId;

@Repository
public class TipoturismoHasTiposervicioturisticoDAO {

	private static final Log log = LogFactory.getLog(TipoturismoHasTiposervicioturisticoDAO.class);

	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	public boolean create(TipoturismoHasTiposervicioturistico transientInstance) {
		log.debug("creating TipoturismoHasTiposervicioturistico instance");
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
	
	public TipoturismoHasTiposervicioturistico read(TipoturismoHasTiposervicioturisticoId id) {
		log.debug("reading TipoturismoHasTiposervicioturistico instance");
		TipoturismoHasTiposervicioturistico u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (TipoturismoHasTiposervicioturistico)session.get(TipoturismoHasTiposervicioturistico.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}
	
	public List<TipoturismoHasTiposervicioturistico> readAll() {
		List<TipoturismoHasTiposervicioturistico> result = null;
		Session session = sessionFactory.openSession();
		result = session.createCriteria(TipoturismoHasTiposervicioturistico.class).list();
		session.close();
		return result;
	}

	public boolean update(TipoturismoHasTiposervicioturistico transientInstance) {
		log.debug("updating TipoturismoHasTiposervicioturistico instance");
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

	public boolean delete(TipoturismoHasTiposervicioturistico transientInstance) {
		log.debug("deleting TipoturismoHasTiposervicioturistico instance");
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


	public TipoturismoHasTiposervicioturistico findById(java.lang.Integer id) {
		log.debug("getting TipoturismoHasTiposervicioturistico instance with id: " + id);
		TipoturismoHasTiposervicioturistico u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			 u = (TipoturismoHasTiposervicioturistico) session.get(TipoturismoHasTiposervicioturistico.class, id);
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
