package dao;
// Generated 30/08/2016 02:56:52 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import dto.StHasFormapago;
import dto.StHasFormapagoId;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class StHasFormapago.
 * @see dao.StHasFormapago
 * @author Hibernate Tools
 */
public class StHasFormapagoHome {

	private static final Log log = LogFactory.getLog(StHasFormapagoHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(StHasFormapago transientInstance) {
		log.debug("persisting StHasFormapago instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(StHasFormapago instance) {
		log.debug("attaching dirty StHasFormapago instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(StHasFormapago instance) {
		log.debug("attaching clean StHasFormapago instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(StHasFormapago persistentInstance) {
		log.debug("deleting StHasFormapago instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public StHasFormapago merge(StHasFormapago detachedInstance) {
		log.debug("merging StHasFormapago instance");
		try {
			StHasFormapago result = (StHasFormapago) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public StHasFormapago findById(StHasFormapagoId id) {
		log.debug("getting StHasFormapago instance with id: " + id);
		try {
			StHasFormapago instance = (StHasFormapago) sessionFactory.getCurrentSession().get("dao.StHasFormapago", id);
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

	public List<StHasFormapago> findByExample(StHasFormapago instance) {
		log.debug("finding StHasFormapago instance by example");
		try {
			List<StHasFormapago> results = (List<StHasFormapago>) sessionFactory.getCurrentSession()
					.createCriteria("dao.StHasFormapago").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
