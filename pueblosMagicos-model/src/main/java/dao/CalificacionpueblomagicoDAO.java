package dao;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.Calificacionatractivoturistico;
import dto.Calificacionpueblomagico;
import dto.Pueblomagico;

@Repository
public class CalificacionpueblomagicoDAO {

	private static final Log log = LogFactory.getLog(CalificacionpueblomagicoDAO.class);

	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	public boolean create(Calificacionpueblomagico transientInstance) {
		log.debug("creating Calificacionpueblomagico instance");
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
	
	public Calificacionpueblomagico read(int id) {
		log.debug("reading Calificacionpueblomagico instance");
		Calificacionpueblomagico u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (Calificacionpueblomagico)session.get(Calificacionpueblomagico.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}
	
	public List<Calificacionpueblomagico> readAll() {
		List<Calificacionpueblomagico> result = null;
		Session session = sessionFactory.openSession();
		result = session.createCriteria(Calificacionpueblomagico.class).list();
		session.close();
		return result;
	}

	public boolean update(Calificacionpueblomagico transientInstance) {
		log.debug("updating Calificacionpueblomagico instance");
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

	public boolean delete(Calificacionpueblomagico transientInstance) {
		log.debug("deleting Calificacionpueblomagico instance");
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


	public Calificacionpueblomagico findById(java.lang.Integer id) {
		log.debug("getting Calificacionpueblomagico instance with id: " + id);
		Calificacionpueblomagico u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			 u = (Calificacionpueblomagico) session.get(Calificacionpueblomagico.class, id);
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

	public Calificacionpueblomagico findByNombreCalificacionpueblomagico(String n) {
		log.debug("finding Calificacionpueblomagico instance by example");
		Session session = sessionFactory.openSession();
		try {
			List<Calificacionpueblomagico> results = session.createCriteria(Calificacionpueblomagico.class).add( Restrictions.like("nombreCalificacionpueblomagico", n) ).list();
			log.debug("find by example successful, result size: " + results.size());
			return results.get(0);
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<Calificacionpueblomagico> findByIdPuebloMagico(int idPM) {
		log.debug("finding Calificacionatractivoturistico instance by example");
		Session session = sessionFactory.openSession();
		try {
			List<Calificacionpueblomagico> results = session.createCriteria(Calificacionpueblomagico.class).add( Restrictions.like("pM_idPuebloMagico", idPM) ).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<Calificacionpueblomagico> getCalificacionpueblomagicoByLimit(int first, int numRegistros) 
	{
		log.debug("finding Pueblomagico instance by example");
		List<Calificacionpueblomagico> results = null;
		Session session = sessionFactory.openSession();
		try {
			Criteria crit;
			crit = session.createCriteria(Calificacionpueblomagico.class);
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
