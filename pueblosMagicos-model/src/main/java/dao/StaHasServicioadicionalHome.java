package dao;
// Generated 30/08/2016 02:56:52 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import dto.StaHasServicioadicional;
import dto.StaHasServicioadicionalId;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class StaHasServicioadicional.
 * @see dao.StaHasServicioadicional
 * @author Hibernate Tools
 */
public class StaHasServicioadicionalHome {

	private static final Log log = LogFactory.getLog(StaHasServicioadicionalHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(StaHasServicioadicional transientInstance) {
		log.debug("persisting StaHasServicioadicional instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(StaHasServicioadicional instance) {
		log.debug("attaching dirty StaHasServicioadicional instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(StaHasServicioadicional instance) {
		log.debug("attaching clean StaHasServicioadicional instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(StaHasServicioadicional persistentInstance) {
		log.debug("deleting StaHasServicioadicional instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public StaHasServicioadicional merge(StaHasServicioadicional detachedInstance) {
		log.debug("merging StaHasServicioadicional instance");
		try {
			StaHasServicioadicional result = (StaHasServicioadicional) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public StaHasServicioadicional findById(StaHasServicioadicionalId id) {
		log.debug("getting StaHasServicioadicional instance with id: " + id);
		try {
			StaHasServicioadicional instance = (StaHasServicioadicional) sessionFactory.getCurrentSession()
					.get("dao.StaHasServicioadicional", id);
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

	public List<StaHasServicioadicional> findByExample(StaHasServicioadicional instance) {
		log.debug("finding StaHasServicioadicional instance by example");
		try {
			List<StaHasServicioadicional> results = (List<StaHasServicioadicional>) sessionFactory.getCurrentSession()
					.createCriteria("dao.StaHasServicioadicional").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
