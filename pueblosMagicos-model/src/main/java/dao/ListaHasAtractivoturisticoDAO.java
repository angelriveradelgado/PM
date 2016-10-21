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

import dto.ListaHasAtractivoturistico;
import dto.ListaHasAtractivoturisticoId;
import dto.ListaHasServicioturistico;

@Repository
public class ListaHasAtractivoturisticoDAO{

	private static final Log log = LogFactory.getLog(ListaHasAtractivoturisticoDAO.class);

	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	public boolean create(ListaHasAtractivoturistico transientInstance) {
		log.debug("creating ListaHasAtractivoturistico instance");
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
	
	public ListaHasAtractivoturistico read(ListaHasAtractivoturisticoId id) {
		log.debug("reading ListaHasAtractivoturistico instance");
		ListaHasAtractivoturistico u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (ListaHasAtractivoturistico)session.get(ListaHasAtractivoturistico.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}
	
	public List<ListaHasAtractivoturistico> readAll() {
		List<ListaHasAtractivoturistico> result = null;
		Session session = sessionFactory.openSession();
		result = session.createCriteria(ListaHasAtractivoturistico.class).list();
		session.close();
		return result;
	}

	public boolean update(ListaHasAtractivoturistico transientInstance) {
		log.debug("updating ListaHasAtractivoturistico instance");
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

	public boolean delete(ListaHasAtractivoturistico transientInstance) {
		log.debug("deleting ListaHasAtractivoturistico instance");
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


	public ListaHasAtractivoturistico findById(java.lang.Integer id) {
		log.debug("getting ListaHasAtractivoturistico instance with id: " + id);
		ListaHasAtractivoturistico u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			 u = (ListaHasAtractivoturistico) session.get(ListaHasAtractivoturistico.class, id);
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

	public List<ListaHasAtractivoturisticoId> findByIdUsuario(int id) {
		log.debug("finding ListaHasServicioturistico instance by example");
		Session session = sessionFactory.openSession();
		try {
			List<ListaHasAtractivoturisticoId> results = session.createCriteria(ListaHasAtractivoturisticoId.class).add( Restrictions.like("Turista_idUsuario", id) ).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
}
