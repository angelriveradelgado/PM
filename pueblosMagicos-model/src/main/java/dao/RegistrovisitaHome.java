package dao;
// Generated 30/08/2016 02:56:52 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import dto.Registrovisita;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Registrovisita.
 * @see dao.Registrovisita
 * @author Hibernate Tools
 */
public class RegistrovisitaHome {

	private static final Log log = LogFactory.getLog(RegistrovisitaHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Registrovisita transientInstance) {
		log.debug("persisting Registrovisita instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Registrovisita instance) {
		log.debug("attaching dirty Registrovisita instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Registrovisita instance) {
		log.debug("attaching clean Registrovisita instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Registrovisita persistentInstance) {
		log.debug("deleting Registrovisita instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Registrovisita merge(Registrovisita detachedInstance) {
		log.debug("merging Registrovisita instance");
		try {
			Registrovisita result = (Registrovisita) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Registrovisita findById(java.lang.Integer id) {
		log.debug("getting Registrovisita instance with id: " + id);
		try {
			Registrovisita instance = (Registrovisita) sessionFactory.getCurrentSession().get("dao.Registrovisita", id);
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

	public List<Registrovisita> findByExample(Registrovisita instance) {
		log.debug("finding Registrovisita instance by example");
		try {
			List<Registrovisita> results = (List<Registrovisita>) sessionFactory.getCurrentSession()
					.createCriteria("dao.Registrovisita").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
