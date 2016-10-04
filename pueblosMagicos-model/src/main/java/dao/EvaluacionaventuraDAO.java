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

import dto.Evaluacionaventura;
import dto.Pueblomagico;

@Repository
public class EvaluacionaventuraDAO{

	private static final Log log = LogFactory.getLog(EvaluacionaventuraDAO.class);

	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	public boolean create(Evaluacionaventura transientInstance) {
		log.debug("creating Evaluacionaventura instance");
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
	
	public Evaluacionaventura read(int id) {
		log.debug("reading Evaluacionaventura instance");
		Evaluacionaventura u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (Evaluacionaventura)session.get(Evaluacionaventura.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}
	
	public List<Evaluacionaventura> readAll() {
		List<Evaluacionaventura> result = null;
		Session session = sessionFactory.openSession();
		result = session.createCriteria(Evaluacionaventura.class).list();
		session.close();
		return result;
	}

	public boolean update(Evaluacionaventura transientInstance) {
		log.debug("updating Evaluacionaventura instance");
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

	public boolean delete(Evaluacionaventura transientInstance) {
		log.debug("deleting Evaluacionaventura instance");
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


	public Evaluacionaventura findById(java.lang.Integer id) {
		log.debug("getting Evaluacionaventura instance with id: " + id);
		Evaluacionaventura u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			 u = (Evaluacionaventura) session.get(Evaluacionaventura.class, id);
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

	public Evaluacionaventura findByNombreEvaluacionaventura(String n) {
		log.debug("finding Evaluacionaventura instance by example");
		Session session = sessionFactory.openSession();
		try {
			List<Evaluacionaventura> results = session.createCriteria(Evaluacionaventura.class).add( Restrictions.like("nombreEvaluacionaventura", n) ).list();
			log.debug("find by example successful, result size: " + results.size());
			return results.get(0);
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<Evaluacionaventura> getEvaluacionaventuraLimit(int first, int numRegistros) 
	{
		log.debug("finding Pueblomagico instance by example");
		List<Evaluacionaventura> results = null;
		Session session = sessionFactory.openSession();
		try {
			Criteria crit;
			crit = session.createCriteria(Evaluacionaventura.class);
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
