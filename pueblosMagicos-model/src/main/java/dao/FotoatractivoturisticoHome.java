package dao;
// Generated 30/08/2016 02:56:52 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import dto.Fotoatractivoturistico;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Fotoatractivoturistico.
 * @see dao.Fotoatractivoturistico
 * @author Hibernate Tools
 */
public class FotoatractivoturisticoHome {

	private static final Log log = LogFactory.getLog(FotoatractivoturisticoHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Fotoatractivoturistico transientInstance) {
		log.debug("persisting Fotoatractivoturistico instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Fotoatractivoturistico instance) {
		log.debug("attaching dirty Fotoatractivoturistico instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Fotoatractivoturistico instance) {
		log.debug("attaching clean Fotoatractivoturistico instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Fotoatractivoturistico persistentInstance) {
		log.debug("deleting Fotoatractivoturistico instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Fotoatractivoturistico merge(Fotoatractivoturistico detachedInstance) {
		log.debug("merging Fotoatractivoturistico instance");
		try {
			Fotoatractivoturistico result = (Fotoatractivoturistico) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Fotoatractivoturistico findById(java.lang.Integer id) {
		log.debug("getting Fotoatractivoturistico instance with id: " + id);
		try {
			Fotoatractivoturistico instance = (Fotoatractivoturistico) sessionFactory.getCurrentSession()
					.get("dao.Fotoatractivoturistico", id);
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

	public List<Fotoatractivoturistico> findByExample(Fotoatractivoturistico instance) {
		log.debug("finding Fotoatractivoturistico instance by example");
		try {
			List<Fotoatractivoturistico> results = (List<Fotoatractivoturistico>) sessionFactory.getCurrentSession()
					.createCriteria("dao.Fotoatractivoturistico").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
