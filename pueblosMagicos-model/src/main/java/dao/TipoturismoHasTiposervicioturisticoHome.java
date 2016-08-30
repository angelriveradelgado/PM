package dao;
// Generated 30/08/2016 02:56:52 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import dto.TipoturismoHasTiposervicioturistico;
import dto.TipoturismoHasTiposervicioturisticoId;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class TipoturismoHasTiposervicioturistico.
 * @see dao.TipoturismoHasTiposervicioturistico
 * @author Hibernate Tools
 */
public class TipoturismoHasTiposervicioturisticoHome {

	private static final Log log = LogFactory.getLog(TipoturismoHasTiposervicioturisticoHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(TipoturismoHasTiposervicioturistico transientInstance) {
		log.debug("persisting TipoturismoHasTiposervicioturistico instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TipoturismoHasTiposervicioturistico instance) {
		log.debug("attaching dirty TipoturismoHasTiposervicioturistico instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TipoturismoHasTiposervicioturistico instance) {
		log.debug("attaching clean TipoturismoHasTiposervicioturistico instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TipoturismoHasTiposervicioturistico persistentInstance) {
		log.debug("deleting TipoturismoHasTiposervicioturistico instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TipoturismoHasTiposervicioturistico merge(TipoturismoHasTiposervicioturistico detachedInstance) {
		log.debug("merging TipoturismoHasTiposervicioturistico instance");
		try {
			TipoturismoHasTiposervicioturistico result = (TipoturismoHasTiposervicioturistico) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TipoturismoHasTiposervicioturistico findById(TipoturismoHasTiposervicioturisticoId id) {
		log.debug("getting TipoturismoHasTiposervicioturistico instance with id: " + id);
		try {
			TipoturismoHasTiposervicioturistico instance = (TipoturismoHasTiposervicioturistico) sessionFactory
					.getCurrentSession().get("dao.TipoturismoHasTiposervicioturistico", id);
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

	public List<TipoturismoHasTiposervicioturistico> findByExample(TipoturismoHasTiposervicioturistico instance) {
		log.debug("finding TipoturismoHasTiposervicioturistico instance by example");
		try {
			List<TipoturismoHasTiposervicioturistico> results = (List<TipoturismoHasTiposervicioturistico>) sessionFactory
					.getCurrentSession().createCriteria("dao.TipoturismoHasTiposervicioturistico").add(create(instance))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
