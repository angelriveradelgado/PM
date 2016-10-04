package dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.FotoSimple;
import dto.Fotoatractivoturistico;

@Repository
public class FotoatractivoturisticoDAO {

	private static final Log log = LogFactory.getLog(FotoatractivoturisticoDAO.class);

	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	public boolean create(Fotoatractivoturistico transientInstance) {
		log.debug("creating Fotoatractivoturistico instance");
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
	
	public Fotoatractivoturistico read(int id) {
		log.debug("reading Fotoatractivoturistico instance");
		Fotoatractivoturistico u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (Fotoatractivoturistico)session.get(Fotoatractivoturistico.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}
	
	public List<Fotoatractivoturistico> readAll() {
		List<Fotoatractivoturistico> result = null;
		Session session = sessionFactory.openSession();
		result = session.createCriteria(Fotoatractivoturistico.class).list();
		session.close();
		return result;
	}

	public boolean update(Fotoatractivoturistico transientInstance) {
		log.debug("updating Fotoatractivoturistico instance");
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

	public boolean delete(Fotoatractivoturistico transientInstance) {
		log.debug("deleting Fotoatractivoturistico instance");
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


	public Fotoatractivoturistico findById(java.lang.Integer id) {
		log.debug("getting Fotoatractivoturistico instance with id: " + id);
		Fotoatractivoturistico u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			 u = (Fotoatractivoturistico) session.get(Fotoatractivoturistico.class, id);
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
	
	public List<FotoSimple> readAllSimpleByIdAtractivoTuristico(int id) 
	{
		List<FotoSimple> result = null;
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery("select idFotoAtractivoTuristico, descripcion from fotoAtractivoTuristico where aT_idAtractivoTuristico=:idAT")
				.setParameter("idAT", id);
		
		result = (List<FotoSimple>) query.list();
		session.close();
		return result;
	}

	public Fotoatractivoturistico findByNombreFotoatractivoturistico(String n) {
		log.debug("finding Fotoatractivoturistico instance by example");
		Session session = sessionFactory.openSession();
		try {
			List<Fotoatractivoturistico> results = session.createCriteria(Fotoatractivoturistico.class).add( Restrictions.like("nombreFotoatractivoturistico", n) ).list();
			log.debug("find by example successful, result size: " + results.size());
			return results.get(0);
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	
}
