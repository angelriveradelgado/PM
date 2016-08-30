package dao;
// Generated 30/08/2016 02:56:52 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import dto.StaHasAsociacion;
import dto.StaHasAsociacionId;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class StaHasAsociacion.
 * @see dao.StaHasAsociacion
 * @author Hibernate Tools
 */
public class StaHasAsociacionHome {

	private static final Log log = LogFactory.getLog(StaHasAsociacionHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(StaHasAsociacion transientInstance) {
		log.debug("persisting StaHasAsociacion instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(StaHasAsociacion instance) {
		log.debug("attaching dirty StaHasAsociacion instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(StaHasAsociacion instance) {
		log.debug("attaching clean StaHasAsociacion instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(StaHasAsociacion persistentInstance) {
		log.debug("deleting StaHasAsociacion instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public StaHasAsociacion merge(StaHasAsociacion detachedInstance) {
		log.debug("merging StaHasAsociacion instance");
		try {
			StaHasAsociacion result = (StaHasAsociacion) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public StaHasAsociacion findById(StaHasAsociacionId id) {
		log.debug("getting StaHasAsociacion instance with id: " + id);
		try {
			StaHasAsociacion instance = (StaHasAsociacion) sessionFactory.getCurrentSession()
					.get("dao.StaHasAsociacion", id);
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

	public List<StaHasAsociacion> findByExample(StaHasAsociacion instance) {
		log.debug("finding StaHasAsociacion instance by example");
		try {
			List<StaHasAsociacion> results = (List<StaHasAsociacion>) sessionFactory.getCurrentSession()
					.createCriteria("dao.StaHasAsociacion").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
