package dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.Evaluacionalojamiento;
import dto.Pueblomagico;

@Repository
public class EvaluacionalojamientoDAO {

	private static final Log log = LogFactory.getLog(EvaluacionalojamientoDAO.class);

	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	public boolean create(Evaluacionalojamiento transientInstance) {
		log.debug("creating Evaluacionalojamiento instance");
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
	
	public Evaluacionalojamiento read(int id) {
		log.debug("reading Evaluacionalojamiento instance");
		Evaluacionalojamiento u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (Evaluacionalojamiento)session.get(Evaluacionalojamiento.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}

	public List<Evaluacionalojamiento> readAll() {
		List<Evaluacionalojamiento> result = null;
		Session session = sessionFactory.openSession();
		result = session.createCriteria(Evaluacionalojamiento.class).list();
		session.close();
		return result;
	}
	
	public boolean update(Evaluacionalojamiento transientInstance) {
		log.debug("updating Evaluacionalojamiento instance");
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

	public boolean delete(Evaluacionalojamiento transientInstance) {
		log.debug("deleting Evaluacionalojamiento instance");
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


	public Evaluacionalojamiento findById(java.lang.Integer id) {
		log.debug("getting Evaluacionalojamiento instance with id: " + id);
		Evaluacionalojamiento u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			 u = (Evaluacionalojamiento) session.get(Evaluacionalojamiento.class, id);
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

	public Evaluacionalojamiento findByNombreEvaluacionalojamiento(String n) {
		log.debug("finding Evaluacionalojamiento instance by example");
		Session session = sessionFactory.openSession();
		try {
			List<Evaluacionalojamiento> results = session.createCriteria(Evaluacionalojamiento.class).add( Restrictions.like("nombreEvaluacionalojamiento", n) ).list();
			log.debug("find by example successful, result size: " + results.size());
			return results.get(0);
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<Evaluacionalojamiento> getEvaluacionalojamientoByLimit(int first, int numRegistros) 
	{
		log.debug("finding Pueblomagico instance by example");
		List<Evaluacionalojamiento> results = null;
		Session session = sessionFactory.openSession();
		try {
			Criteria crit;
			crit = session.createCriteria(Evaluacionalojamiento.class);
			crit.setFirstResult(first);
			crit.setMaxResults(numRegistros);
			results = crit.list();			
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
		return results;
	}
	
}
