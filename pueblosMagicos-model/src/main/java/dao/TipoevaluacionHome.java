package dao;
// Generated 30/08/2016 02:56:52 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import dto.Tipoevaluacion;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Tipoevaluacion.
 * @see dao.Tipoevaluacion
 * @author Hibernate Tools
 */
public class TipoevaluacionHome {

	private static final Log log = LogFactory.getLog(TipoevaluacionHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Tipoevaluacion transientInstance) {
		log.debug("persisting Tipoevaluacion instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Tipoevaluacion instance) {
		log.debug("attaching dirty Tipoevaluacion instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tipoevaluacion instance) {
		log.debug("attaching clean Tipoevaluacion instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Tipoevaluacion persistentInstance) {
		log.debug("deleting Tipoevaluacion instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tipoevaluacion merge(Tipoevaluacion detachedInstance) {
		log.debug("merging Tipoevaluacion instance");
		try {
			Tipoevaluacion result = (Tipoevaluacion) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Tipoevaluacion findById(java.lang.Integer id) {
		log.debug("getting Tipoevaluacion instance with id: " + id);
		try {
			Tipoevaluacion instance = (Tipoevaluacion) sessionFactory.getCurrentSession().get("dao.Tipoevaluacion", id);
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

	public List<Tipoevaluacion> findByExample(Tipoevaluacion instance) {
		log.debug("finding Tipoevaluacion instance by example");
		try {
			List<Tipoevaluacion> results = (List<Tipoevaluacion>) sessionFactory.getCurrentSession()
					.createCriteria("dao.Tipoevaluacion").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
