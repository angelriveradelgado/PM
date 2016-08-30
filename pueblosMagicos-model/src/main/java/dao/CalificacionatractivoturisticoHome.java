package dao;
// Generated 30/08/2016 02:56:52 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import dto.Calificacionatractivoturistico;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Calificacionatractivoturistico.
 * @see dao.Calificacionatractivoturistico
 * @author Hibernate Tools
 */
public class CalificacionatractivoturisticoHome {

	private static final Log log = LogFactory.getLog(CalificacionatractivoturisticoHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Calificacionatractivoturistico transientInstance) {
		log.debug("persisting Calificacionatractivoturistico instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Calificacionatractivoturistico instance) {
		log.debug("attaching dirty Calificacionatractivoturistico instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Calificacionatractivoturistico instance) {
		log.debug("attaching clean Calificacionatractivoturistico instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Calificacionatractivoturistico persistentInstance) {
		log.debug("deleting Calificacionatractivoturistico instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Calificacionatractivoturistico merge(Calificacionatractivoturistico detachedInstance) {
		log.debug("merging Calificacionatractivoturistico instance");
		try {
			Calificacionatractivoturistico result = (Calificacionatractivoturistico) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Calificacionatractivoturistico findById(java.lang.Integer id) {
		log.debug("getting Calificacionatractivoturistico instance with id: " + id);
		try {
			Calificacionatractivoturistico instance = (Calificacionatractivoturistico) sessionFactory
					.getCurrentSession().get("dao.Calificacionatractivoturistico", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Calificacionatractivoturistico> findByExample(Calificacionatractivoturistico instance) {
		log.debug("finding Calificacionatractivoturistico instance by example");
		try {
			List<Calificacionatractivoturistico> results = (List<Calificacionatractivoturistico>) sessionFactory
					.getCurrentSession().createCriteria("dao.Calificacionatractivoturistico").add(create(instance))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
