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

import dto.Estadoregistropst;

@Repository
public class EstadoregistropstDAO{

	private static final Log log = LogFactory.getLog(EstadoregistropstDAO.class);
	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	public boolean create(Estadoregistropst transientInstance) {
		log.debug("creating Estadoregistropst instance");
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
	
	public Estadoregistropst read(Integer id) {
		log.debug("reading Estadoregistropst instance");
		Estadoregistropst u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (Estadoregistropst)session.get(Estadoregistropst.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}

	public List<Estadoregistropst> readAll() {
		List<Estadoregistropst> result = null;
		Session session = sessionFactory.openSession();
		try
		{
			result = session.createCriteria(Estadoregistropst.class).list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}
	
	public boolean update(Estadoregistropst transientInstance) {
		log.debug("updating Estadoregistropst instance");
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

	public boolean delete(Estadoregistropst transientInstance) {
		log.debug("deleting Estadoregistropst instance");
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


	public Estadoregistropst findById(java.lang.Integer id) {
		log.debug("getting Estadoregistropst instance with id: " + id);
		Estadoregistropst u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			 u = (Estadoregistropst) session.get(Estadoregistropst.class, id);
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

	public Estadoregistropst findByNombreEstadoregistropst(String n) {
		log.debug("finding Estadoregistropst instance by example");
		List<Estadoregistropst> results = null;
		Estadoregistropst result = null;
		Session session = sessionFactory.openSession();
		try {
			results = session.createCriteria(Estadoregistropst.class).add( Restrictions.like("nombreEstadoregistropst", n) ).list();
			log.debug("find by example successful, result size: " + results.size());
			result =  results.get(0);
		} catch (RuntimeException re) {
			log.error("find by example failed", re);			
			re.printStackTrace();
		}
		session.close();
		return result;
	}
	
	
}

