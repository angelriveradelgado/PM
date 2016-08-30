package dao;
// Generated 30/08/2016 02:56:52 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import dto.Servicioturistico;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Servicioturistico.
 * @see dao.Servicioturistico
 * @author Hibernate Tools
 */
public class ServicioturisticoHome {

	private static final Log log = LogFactory.getLog(ServicioturisticoHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Servicioturistico transientInstance) {
		log.debug("persisting Servicioturistico instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Servicioturistico instance) {
		log.debug("attaching dirty Servicioturistico instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Servicioturistico instance) {
		log.debug("attaching clean Servicioturistico instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Servicioturistico persistentInstance) {
		log.debug("deleting Servicioturistico instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Servicioturistico merge(Servicioturistico detachedInstance) {
		log.debug("merging Servicioturistico instance");
		try {
			Servicioturistico result = (Servicioturistico) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Servicioturistico findById(java.lang.Integer id) {
		log.debug("getting Servicioturistico instance with id: " + id);
		try {
			Servicioturistico instance = (Servicioturistico) sessionFactory.getCurrentSession()
					.get("dao.Servicioturistico", id);
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

	public List<Servicioturistico> findByExample(Servicioturistico instance) {
		log.debug("finding Servicioturistico instance by example");
		try {
			List<Servicioturistico> results = (List<Servicioturistico>) sessionFactory.getCurrentSession()
					.createCriteria("dao.Servicioturistico").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
