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

import dto.Tipovialidad;

@Repository
public class TipovialidadDAO {

	private static final Log log = LogFactory.getLog(TipovialidadDAO.class);

	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	public boolean create(Tipovialidad transientInstance) {
		log.debug("creating Tipovialidad instance");
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
	
	public Tipovialidad read(Integer id) {
		log.debug("reading Tipovialidad instance");
		Tipovialidad u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (Tipovialidad)session.get(Tipovialidad.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}
	
	public List<Tipovialidad> readAll() {
		List<Tipovialidad> result = null;
		Session session = sessionFactory.openSession();
		try
		{
			result = session.createCriteria(Tipovialidad.class).list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	
		session.close();
		return result;
	}

	public boolean update(Tipovialidad transientInstance) {
		log.debug("updating Tipovialidad instance");
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

	public boolean delete(Tipovialidad transientInstance) {
		log.debug("deleting Tipovialidad instance");
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


	public Tipovialidad findById(java.lang.Integer id) {
		log.debug("getting Tipovialidad instance with id: " + id);
		Tipovialidad u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			 u = (Tipovialidad) session.get(Tipovialidad.class, id);
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
