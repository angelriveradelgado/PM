package dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.Atractivoturistico;
import dto.Calificacionatractivoturistico;
import dto.Estado;

@Repository
public class CalificacionatractivoturisticoDAO {

	private static final Log log = LogFactory.getLog(CalificacionatractivoturisticoDAO.class);

	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	public boolean create(Calificacionatractivoturistico transientInstance) {
		log.debug("creating Calificacionatractivoturistico instance");
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
	
	public Calificacionatractivoturistico read(Integer id) {
		log.debug("reading Calificacionatractivoturistico instance");
		Calificacionatractivoturistico u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (Calificacionatractivoturistico)session.get(Calificacionatractivoturistico.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}
	
	public List<Calificacionatractivoturistico> readAll() {
		List<Calificacionatractivoturistico> result = null;
		Session session = sessionFactory.openSession();
		try
		{
			result = session.createCriteria(Calificacionatractivoturistico.class).list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}

	public boolean update(Calificacionatractivoturistico transientInstance) {
		log.debug("updating Calificacionatractivoturistico instance");
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

	public boolean delete(Calificacionatractivoturistico transientInstance) {
		log.debug("deleting Calificacionatractivoturistico instance");
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


	public Calificacionatractivoturistico findById(java.lang.Integer id) {
		log.debug("getting Calificacionatractivoturistico instance with id: " + id);
		Calificacionatractivoturistico u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			 u = (Calificacionatractivoturistico) session.get(Calificacionatractivoturistico.class, id);
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

	public Calificacionatractivoturistico findByNombreCalificacionatractivoturistico(String n) {
		log.debug("finding Calificacionatractivoturistico instance by example");
		List<Calificacionatractivoturistico> results = null;
		Calificacionatractivoturistico result =  null;
		Session session = sessionFactory.openSession();
		try {
			results = session.createCriteria(Calificacionatractivoturistico.class).add( Restrictions.like("nombreCalificacionatractivoturistico", n) ).list();
			log.debug("find by example successful, result size: " + results.size());
			result =  results.get(0);
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			re.printStackTrace();
		}
		session.close();
		return result;
	}
	
	public List<Calificacionatractivoturistico> findByIdAtractivoTuristico(Integer idAT) {
		log.debug("finding Calificacionatractivoturistico instance by example");
		List<Calificacionatractivoturistico> results = null;
		Session session = sessionFactory.openSession();
		try {
			results = session.createCriteria(Calificacionatractivoturistico.class).add( Restrictions.like("atIdatractivoTuristico", idAT) ).list();
			log.debug("find by example successful, result size: " + results.size());
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			re.printStackTrace();
		}
		session.close();
		return results;
	}
	
	public List<Calificacionatractivoturistico> findByIdUsuarioByIdAtractivoTuristico(Integer idUsuario, Integer idAT) {
		log.debug("finding Calificacionatractivoturistico instance by example");
		List<Calificacionatractivoturistico> results = null;
		Session session = sessionFactory.openSession();
		try {
			results = session.createCriteria(Calificacionatractivoturistico.class)
					.add( Restrictions.like("atIdatractivoTuristico", idAT) )
					.add( Restrictions.like("TIdusuario", idUsuario) )
					.list();
			log.debug("find by example successful, result size: " + results.size());
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			re.printStackTrace();
		}
		session.close();
		return results;
	}
	
	public List<Calificacionatractivoturistico> getCalificacionatractivoturisticoByLimit(Integer idAT, Integer first, Integer numRegistros) 
	{
		log.debug("finding Pueblomagico instance by example");
		List<Calificacionatractivoturistico> results = null;
		Session session = sessionFactory.openSession();
		try {
			Criteria crit;
			crit = session.createCriteria(Calificacionatractivoturistico.class);
			crit.setFirstResult(first);
			crit.setMaxResults(numRegistros);
			crit.add( Restrictions.like("atIdatractivoTuristico", idAT));
			results = crit.list();			
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			re.printStackTrace();
		}
		session.close();
		return results;
	}
	
	public Float getPromedio(Integer id) 
	{
		Float result = (float) 0;
		Session session = sessionFactory.openSession();
		
		try
		{
			Query q = session.createSQLQuery("select AVG(calificacion) from calificacionatractivoturistico "
					+ "where aT_idatractivoTuristico=:id")
					.setParameter("id", id);
		
			
			result = (Float)q.list().get(0);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}
	
	
	
}
