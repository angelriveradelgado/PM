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
import dto.Fotoservicioturistico;

@Repository
public class FotoservicioturisticoDAO{

	private static final Log log = LogFactory.getLog(FotoservicioturisticoDAO.class);

	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	public boolean create(Fotoservicioturistico transientInstance) {
		log.debug("creating Fotoservicioturistico instance");
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
	
	public Fotoservicioturistico read(int id) {
		log.debug("reading Fotoservicioturistico instance");
		Fotoservicioturistico u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (Fotoservicioturistico)session.get(Fotoservicioturistico.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}
	
	public List<Fotoservicioturistico> readAll() {
		List<Fotoservicioturistico> result = null;
		Session session = sessionFactory.openSession();
		result = session.createCriteria(Fotoservicioturistico.class).list();
		session.close();
		return result;
	}

	public boolean update(Fotoservicioturistico transientInstance) {
		log.debug("updating Fotoservicioturistico instance");
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

	public boolean delete(Fotoservicioturistico transientInstance) {
		log.debug("deleting Fotoservicioturistico instance");
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


	public Fotoservicioturistico findById(java.lang.Integer id) {
		log.debug("getting Fotoservicioturistico instance with id: " + id);
		Fotoservicioturistico u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			 u = (Fotoservicioturistico) session.get(Fotoservicioturistico.class, id);
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

	public List<FotoSimple> readAllSimpleByIdServicioTuristico(int id) 
	{
		List<FotoSimple> result = null;
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery("select idFotoServicioTuristico, descripcion from fotoServicioTuristico where sT_idServicio=:idST")
				.setParameter("idST", id);
		
		result = (List<FotoSimple>) query.list();
		session.close();
		return result;
	}
	
	public Fotoservicioturistico findByNombreFotoservicioturistico(String n) {
		log.debug("finding Fotoservicioturistico instance by example");
		Session session = sessionFactory.openSession();
		try {
			List<Fotoservicioturistico> results = session.createCriteria(Fotoservicioturistico.class).add( Restrictions.like("nombreFotoservicioturistico", n) ).list();
			log.debug("find by example successful, result size: " + results.size());
			return results.get(0);
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	
}
