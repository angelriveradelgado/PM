package dao;
// Generated 30/08/2016 02:56:52 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import dto.Tiposervicioturistico;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Tiposervicioturistico.
 * @see dao.Tiposervicioturistico
 * @author Hibernate Tools
 */
public class TiposervicioturisticoHome {

	private static final Log log = LogFactory.getLog(TiposervicioturisticoHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Tiposervicioturistico transientInstance) {
		log.debug("persisting Tiposervicioturistico instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Tiposervicioturistico instance) {
		log.debug("attaching dirty Tiposervicioturistico instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tiposervicioturistico instance) {
		log.debug("attaching clean Tiposervicioturistico instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Tiposervicioturistico persistentInstance) {
		log.debug("deleting Tiposervicioturistico instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tiposervicioturistico merge(Tiposervicioturistico detachedInstance) {
		log.debug("merging Tiposervicioturistico instance");
		try {
			Tiposervicioturistico result = (Tiposervicioturistico) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Tiposervicioturistico findById(java.lang.Integer id) {
		log.debug("getting Tiposervicioturistico instance with id: " + id);
		try {
			Tiposervicioturistico instance = (Tiposervicioturistico) sessionFactory.getCurrentSession()
					.get("dao.Tiposervicioturistico", id);
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

	public List<Tiposervicioturistico> findByExample(Tiposervicioturistico instance) {
		log.debug("finding Tiposervicioturistico instance by example");
		try {
			List<Tiposervicioturistico> results = (List<Tiposervicioturistico>) sessionFactory.getCurrentSession()
					.createCriteria("dao.Tiposervicioturistico").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
