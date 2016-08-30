package dao;
// Generated 30/08/2016 02:56:52 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import dto.Tipoatractivo;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Tipoatractivo.
 * @see dao.Tipoatractivo
 * @author Hibernate Tools
 */
public class TipoatractivoHome {

	private static final Log log = LogFactory.getLog(TipoatractivoHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Tipoatractivo transientInstance) {
		log.debug("persisting Tipoatractivo instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Tipoatractivo instance) {
		log.debug("attaching dirty Tipoatractivo instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tipoatractivo instance) {
		log.debug("attaching clean Tipoatractivo instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Tipoatractivo persistentInstance) {
		log.debug("deleting Tipoatractivo instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tipoatractivo merge(Tipoatractivo detachedInstance) {
		log.debug("merging Tipoatractivo instance");
		try {
			Tipoatractivo result = (Tipoatractivo) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Tipoatractivo findById(java.lang.Integer id) {
		log.debug("getting Tipoatractivo instance with id: " + id);
		try {
			Tipoatractivo instance = (Tipoatractivo) sessionFactory.getCurrentSession().get("dao.Tipoatractivo", id);
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

	public List<Tipoatractivo> findByExample(Tipoatractivo instance) {
		log.debug("finding Tipoatractivo instance by example");
		try {
			List<Tipoatractivo> results = (List<Tipoatractivo>) sessionFactory.getCurrentSession()
					.createCriteria("dao.Tipoatractivo").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
