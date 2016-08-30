package dao;
// Generated 30/08/2016 02:56:52 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import dto.Evaluacionservicioturistico;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Evaluacionservicioturistico.
 * @see dao.Evaluacionservicioturistico
 * @author Hibernate Tools
 */
public class EvaluacionservicioturisticoHome {

	private static final Log log = LogFactory.getLog(EvaluacionservicioturisticoHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Evaluacionservicioturistico transientInstance) {
		log.debug("persisting Evaluacionservicioturistico instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Evaluacionservicioturistico instance) {
		log.debug("attaching dirty Evaluacionservicioturistico instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Evaluacionservicioturistico instance) {
		log.debug("attaching clean Evaluacionservicioturistico instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Evaluacionservicioturistico persistentInstance) {
		log.debug("deleting Evaluacionservicioturistico instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Evaluacionservicioturistico merge(Evaluacionservicioturistico detachedInstance) {
		log.debug("merging Evaluacionservicioturistico instance");
		try {
			Evaluacionservicioturistico result = (Evaluacionservicioturistico) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Evaluacionservicioturistico findById(java.lang.Integer id) {
		log.debug("getting Evaluacionservicioturistico instance with id: " + id);
		try {
			Evaluacionservicioturistico instance = (Evaluacionservicioturistico) sessionFactory.getCurrentSession()
					.get("dao.Evaluacionservicioturistico", id);
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

	public List<Evaluacionservicioturistico> findByExample(Evaluacionservicioturistico instance) {
		log.debug("finding Evaluacionservicioturistico instance by example");
		try {
			List<Evaluacionservicioturistico> results = (List<Evaluacionservicioturistico>) sessionFactory
					.getCurrentSession().createCriteria("dao.Evaluacionservicioturistico").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
