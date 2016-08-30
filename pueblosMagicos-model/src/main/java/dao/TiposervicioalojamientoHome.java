package dao;
// Generated 30/08/2016 02:56:52 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import dto.Tiposervicioalojamiento;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Tiposervicioalojamiento.
 * @see dao.Tiposervicioalojamiento
 * @author Hibernate Tools
 */
public class TiposervicioalojamientoHome {

	private static final Log log = LogFactory.getLog(TiposervicioalojamientoHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Tiposervicioalojamiento transientInstance) {
		log.debug("persisting Tiposervicioalojamiento instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Tiposervicioalojamiento instance) {
		log.debug("attaching dirty Tiposervicioalojamiento instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tiposervicioalojamiento instance) {
		log.debug("attaching clean Tiposervicioalojamiento instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Tiposervicioalojamiento persistentInstance) {
		log.debug("deleting Tiposervicioalojamiento instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tiposervicioalojamiento merge(Tiposervicioalojamiento detachedInstance) {
		log.debug("merging Tiposervicioalojamiento instance");
		try {
			Tiposervicioalojamiento result = (Tiposervicioalojamiento) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Tiposervicioalojamiento findById(java.lang.Integer id) {
		log.debug("getting Tiposervicioalojamiento instance with id: " + id);
		try {
			Tiposervicioalojamiento instance = (Tiposervicioalojamiento) sessionFactory.getCurrentSession()
					.get("dao.Tiposervicioalojamiento", id);
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

	public List<Tiposervicioalojamiento> findByExample(Tiposervicioalojamiento instance) {
		log.debug("finding Tiposervicioalojamiento instance by example");
		try {
			List<Tiposervicioalojamiento> results = (List<Tiposervicioalojamiento>) sessionFactory.getCurrentSession()
					.createCriteria("dao.Tiposervicioalojamiento").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
