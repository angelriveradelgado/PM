package dao;
// Generated 30/08/2016 02:56:52 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import dto.Estadoregistropst;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Estadoregistropst.
 * @see dao.Estadoregistropst
 * @author Hibernate Tools
 */
public class EstadoregistropstHome {

	private static final Log log = LogFactory.getLog(EstadoregistropstHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Estadoregistropst transientInstance) {
		log.debug("persisting Estadoregistropst instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Estadoregistropst instance) {
		log.debug("attaching dirty Estadoregistropst instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Estadoregistropst instance) {
		log.debug("attaching clean Estadoregistropst instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Estadoregistropst persistentInstance) {
		log.debug("deleting Estadoregistropst instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Estadoregistropst merge(Estadoregistropst detachedInstance) {
		log.debug("merging Estadoregistropst instance");
		try {
			Estadoregistropst result = (Estadoregistropst) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Estadoregistropst findById(java.lang.Integer id) {
		log.debug("getting Estadoregistropst instance with id: " + id);
		try {
			Estadoregistropst instance = (Estadoregistropst) sessionFactory.getCurrentSession()
					.get("dao.Estadoregistropst", id);
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

	public List<Estadoregistropst> findByExample(Estadoregistropst instance) {
		log.debug("finding Estadoregistropst instance by example");
		try {
			List<Estadoregistropst> results = (List<Estadoregistropst>) sessionFactory.getCurrentSession()
					.createCriteria("dao.Estadoregistropst").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
