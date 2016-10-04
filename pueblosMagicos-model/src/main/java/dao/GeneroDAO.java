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

import dto.Genero;
import dto.Tipousuario;

@Repository
public class GeneroDAO {

	private static final Log log = LogFactory.getLog(GeneroDAO.class);

	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	public boolean create(Genero transientInstance) {
		log.debug("creating Genero instance");
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
	
	public Genero read(int id) {
		log.debug("reading Genero instance");
		Genero u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (Genero)session.get(Genero.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}
	
	public List<Genero> readAll() {
		List<Genero> result = null;
		Session session = sessionFactory.openSession();
		result = session.createCriteria(Genero.class).list();
		session.close();
		return result;
	}

	public boolean update(Genero transientInstance) {
		log.debug("updating Genero instance");
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

	public boolean delete(Genero transientInstance) {
		log.debug("deleting Genero instance");
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


	public Genero findById(java.lang.Integer id) {
		log.debug("getting Genero instance with id: " + id);
		Genero u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			 u = (Genero) session.get(Genero.class, id);
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

	public Genero findByNombreGenero(String n) {
		log.debug("finding Genero instance by example");
		Session session = sessionFactory.openSession();
		try {
			List<Genero> results = session.createCriteria(Genero.class).add( Restrictions.like("nombreGenero", n) ).list();
			log.debug("find by example successful, result size: " + results.size());
			return results.get(0);
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public int getId(String n) {
		log.debug("finding Usuario instance by example");
		Session session = sessionFactory.openSession();
		try {
			List<Genero> results = session.createCriteria(Tipousuario.class).add(Restrictions.like("nombre", n))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			return results.get(0).getIdgenero();
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
