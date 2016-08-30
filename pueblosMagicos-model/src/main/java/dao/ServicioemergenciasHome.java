package dao;
// Generated 30/08/2016 02:56:52 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import dto.Servicioemergencias;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Servicioemergencias.
 * @see dao.Servicioemergencias
 * @author Hibernate Tools
 */
public class ServicioemergenciasHome {

	private static final Log log = LogFactory.getLog(ServicioemergenciasHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Servicioemergencias transientInstance) {
		log.debug("persisting Servicioemergencias instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Servicioemergencias instance) {
		log.debug("attaching dirty Servicioemergencias instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Servicioemergencias instance) {
		log.debug("attaching clean Servicioemergencias instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Servicioemergencias persistentInstance) {
		log.debug("deleting Servicioemergencias instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Servicioemergencias merge(Servicioemergencias detachedInstance) {
		log.debug("merging Servicioemergencias instance");
		try {
			Servicioemergencias result = (Servicioemergencias) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Servicioemergencias findById(java.lang.Integer id) {
		log.debug("getting Servicioemergencias instance with id: " + id);
		try {
			Servicioemergencias instance = (Servicioemergencias) sessionFactory.getCurrentSession()
					.get("dao.Servicioemergencias", id);
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

	public List<Servicioemergencias> findByExample(Servicioemergencias instance) {
		log.debug("finding Servicioemergencias instance by example");
		try {
			List<Servicioemergencias> results = (List<Servicioemergencias>) sessionFactory.getCurrentSession()
					.createCriteria("dao.Servicioemergencias").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
