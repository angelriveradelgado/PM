package dao;
// Generated 30/08/2016 02:56:52 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import dto.Tipoasentamiento;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Tipoasentamiento.
 * @see dao.Tipoasentamiento
 * @author Hibernate Tools
 */
public class TipoasentamientoHome {

	private static final Log log = LogFactory.getLog(TipoasentamientoHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Tipoasentamiento transientInstance) {
		log.debug("persisting Tipoasentamiento instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Tipoasentamiento instance) {
		log.debug("attaching dirty Tipoasentamiento instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tipoasentamiento instance) {
		log.debug("attaching clean Tipoasentamiento instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Tipoasentamiento persistentInstance) {
		log.debug("deleting Tipoasentamiento instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tipoasentamiento merge(Tipoasentamiento detachedInstance) {
		log.debug("merging Tipoasentamiento instance");
		try {
			Tipoasentamiento result = (Tipoasentamiento) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Tipoasentamiento findById(java.lang.Integer id) {
		log.debug("getting Tipoasentamiento instance with id: " + id);
		try {
			Tipoasentamiento instance = (Tipoasentamiento) sessionFactory.getCurrentSession()
					.get("dao.Tipoasentamiento", id);
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

	public List<Tipoasentamiento> findByExample(Tipoasentamiento instance) {
		log.debug("finding Tipoasentamiento instance by example");
		try {
			List<Tipoasentamiento> results = (List<Tipoasentamiento>) sessionFactory.getCurrentSession()
					.createCriteria("dao.Tipoasentamiento").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
