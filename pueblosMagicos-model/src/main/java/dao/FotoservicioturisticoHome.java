package dao;
// Generated 30/08/2016 02:56:52 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import dto.Fotoservicioturistico;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Fotoservicioturistico.
 * @see dao.Fotoservicioturistico
 * @author Hibernate Tools
 */
public class FotoservicioturisticoHome {

	private static final Log log = LogFactory.getLog(FotoservicioturisticoHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Fotoservicioturistico transientInstance) {
		log.debug("persisting Fotoservicioturistico instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Fotoservicioturistico instance) {
		log.debug("attaching dirty Fotoservicioturistico instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Fotoservicioturistico instance) {
		log.debug("attaching clean Fotoservicioturistico instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Fotoservicioturistico persistentInstance) {
		log.debug("deleting Fotoservicioturistico instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Fotoservicioturistico merge(Fotoservicioturistico detachedInstance) {
		log.debug("merging Fotoservicioturistico instance");
		try {
			Fotoservicioturistico result = (Fotoservicioturistico) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Fotoservicioturistico findById(java.lang.Integer id) {
		log.debug("getting Fotoservicioturistico instance with id: " + id);
		try {
			Fotoservicioturistico instance = (Fotoservicioturistico) sessionFactory.getCurrentSession()
					.get("dao.Fotoservicioturistico", id);
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

	public List<Fotoservicioturistico> findByExample(Fotoservicioturistico instance) {
		log.debug("finding Fotoservicioturistico instance by example");
		try {
			List<Fotoservicioturistico> results = (List<Fotoservicioturistico>) sessionFactory.getCurrentSession()
					.createCriteria("dao.Fotoservicioturistico").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
