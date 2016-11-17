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

import dto.ListaHasServicioturistico;
import dto.ListaHasServicioturisticoId;

@Repository
public class ListaHasServicioturisticoDAO {

	private static final Log log = LogFactory.getLog(ListaHasServicioturisticoDAO.class);

	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	public boolean create(ListaHasServicioturistico transientInstance) {
		log.debug("creating ListaHasServicioturistico instance");
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
	
	public ListaHasServicioturistico read(ListaHasServicioturisticoId id) {
		log.debug("reading ListaHasServicioturistico instance");
		ListaHasServicioturistico u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (ListaHasServicioturistico)session.get(ListaHasServicioturistico.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}
	
	public List<ListaHasServicioturistico> readAll() {
		List<ListaHasServicioturistico> result = null;
		Session session = sessionFactory.openSession();
		try
		{
			result = session.createCriteria(ListaHasServicioturistico.class).list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}

	public boolean update(ListaHasServicioturistico transientInstance) {
		log.debug("updating ListaHasServicioturistico instance");
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

	public boolean delete(ListaHasServicioturistico transientInstance) {
		log.debug("deleting ListaHasServicioturistico instance");
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


	public ListaHasServicioturistico findById(Integer id) {
		ListaHasServicioturistico u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			 u = (ListaHasServicioturistico) session.get(ListaHasServicioturistico.class, id);
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

	public List<ListaHasServicioturisticoId> findByIdUsuario(Integer id) {
		log.debug("finding ListaHasServicioturistico instance by example");
		List<ListaHasServicioturisticoId> results = null;
		Session session = sessionFactory.openSession();
		try {
			results = session.createCriteria(ListaHasServicioturisticoId.class).add( Restrictions.like("turistaIdUsuario", id) ).list();
			log.debug("find by example successful, result size: " + results.size());
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			re.printStackTrace();
		}
		session.close();
		return results;
	}
	
	
}
