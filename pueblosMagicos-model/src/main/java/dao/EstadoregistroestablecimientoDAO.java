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

import dto.Estadoregistroestablecimiento;

@Repository
public class EstadoregistroestablecimientoDAO{

	private static final Log log = LogFactory.getLog(EstadoregistroestablecimientoDAO.class);

	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	public boolean create(Estadoregistroestablecimiento transientInstance) {
		log.debug("creating Estadoregistroestablecimiento instance");
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
	
	public Estadoregistroestablecimiento read(Integer id) {
		log.debug("reading Estadoregistroestablecimiento instance");
		Estadoregistroestablecimiento u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (Estadoregistroestablecimiento)session.get(Estadoregistroestablecimiento.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}
	
	public List<Estadoregistroestablecimiento> readAll() {
		List<Estadoregistroestablecimiento> result = null;
		Session session = sessionFactory.openSession();
		try
		{
			result = session.createCriteria(Estadoregistroestablecimiento.class).list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}

	public boolean update(Estadoregistroestablecimiento transientInstance) {
		log.debug("updating Estadoregistroestablecimiento instance");
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

	public boolean delete(Estadoregistroestablecimiento transientInstance) {
		log.debug("deleting Estadoregistroestablecimiento instance");
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


	public Estadoregistroestablecimiento findById(java.lang.Integer id) {
		log.debug("getting Estadoregistroestablecimiento instance with id: " + id);
		Estadoregistroestablecimiento u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			 u = (Estadoregistroestablecimiento) session.get(Estadoregistroestablecimiento.class, id);
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

	public Estadoregistroestablecimiento findByNombreEstadoregistroestablecimiento(String n) {
		log.debug("finding Estadoregistroestablecimiento instance by example");
		List<Estadoregistroestablecimiento> results = null;
		Estadoregistroestablecimiento result = null;
		Session session = sessionFactory.openSession();
		try {
			results = session.createCriteria(Estadoregistroestablecimiento.class).add( Restrictions.like("nombreEstadoregistroestablecimiento", n) ).list();
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
