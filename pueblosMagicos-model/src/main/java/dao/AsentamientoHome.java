package dao;
// Generated 30/08/2016 02:56:52 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;

import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import dto.AsentamientoId;
import dto.Asentamiento;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Asentamiento.
 * @see dao.Asentamiento
 * @author Hibernate Tools
 */
public class AsentamientoHome {

	private static final Log log = LogFactory.getLog(AsentamientoHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Asentamiento transientInstance) {
		log.debug("persisting Asentamiento instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Asentamiento instance) {
		log.debug("attaching dirty Asentamiento instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Asentamiento instance) {
		log.debug("attaching clean Asentamiento instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Asentamiento persistentInstance) {
		log.debug("deleting Asentamiento instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Asentamiento merge(Asentamiento detachedInstance) {
		log.debug("merging Asentamiento instance");
		try {
			Asentamiento result = (Asentamiento) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Asentamiento findById(AsentamientoId id) {
		log.debug("getting Asentamiento instance with id: " + id);
		try {
			Asentamiento instance = (Asentamiento) sessionFactory.getCurrentSession().get("dao.Asentamiento", id);
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

	public List<Asentamiento> findByExample(Asentamiento instance) {
		log.debug("finding Asentamiento instance by example");
		try {
			List<Asentamiento> results = (List<Asentamiento>) sessionFactory.getCurrentSession()
					.createCriteria("dao.Asentamiento").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
