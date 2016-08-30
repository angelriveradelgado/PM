package dao;
// Generated 30/08/2016 02:56:52 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import dto.Atractivoturistico;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Atractivoturistico.
 * @see dao.Atractivoturistico
 * @author Hibernate Tools
 */
public class AtractivoturisticoHome {

	private static final Log log = LogFactory.getLog(AtractivoturisticoHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Atractivoturistico transientInstance) {
		log.debug("persisting Atractivoturistico instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Atractivoturistico instance) {
		log.debug("attaching dirty Atractivoturistico instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Atractivoturistico instance) {
		log.debug("attaching clean Atractivoturistico instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Atractivoturistico persistentInstance) {
		log.debug("deleting Atractivoturistico instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Atractivoturistico merge(Atractivoturistico detachedInstance) {
		log.debug("merging Atractivoturistico instance");
		try {
			Atractivoturistico result = (Atractivoturistico) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Atractivoturistico findById(java.lang.Integer id) {
		log.debug("getting Atractivoturistico instance with id: " + id);
		try {
			Atractivoturistico instance = (Atractivoturistico) sessionFactory.getCurrentSession()
					.get("dao.Atractivoturistico", id);
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

	public List<Atractivoturistico> findByExample(Atractivoturistico instance) {
		log.debug("finding Atractivoturistico instance by example");
		try {
			List<Atractivoturistico> results = (List<Atractivoturistico>) sessionFactory.getCurrentSession()
					.createCriteria("dao.Atractivoturistico").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
