package dao;
// Generated 30/08/2016 02:56:52 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import dto.Tipoalojamiento;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Tipoalojamiento.
 * @see dao.Tipoalojamiento
 * @author Hibernate Tools
 */
public class TipoalojamientoHome {

	private static final Log log = LogFactory.getLog(TipoalojamientoHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Tipoalojamiento transientInstance) {
		log.debug("persisting Tipoalojamiento instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Tipoalojamiento instance) {
		log.debug("attaching dirty Tipoalojamiento instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tipoalojamiento instance) {
		log.debug("attaching clean Tipoalojamiento instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Tipoalojamiento persistentInstance) {
		log.debug("deleting Tipoalojamiento instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tipoalojamiento merge(Tipoalojamiento detachedInstance) {
		log.debug("merging Tipoalojamiento instance");
		try {
			Tipoalojamiento result = (Tipoalojamiento) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Tipoalojamiento findById(int id) {
		log.debug("getting Tipoalojamiento instance with id: " + id);
		try {
			Tipoalojamiento instance = (Tipoalojamiento) sessionFactory.getCurrentSession().get("dao.Tipoalojamiento",
					id);
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

	public List<Tipoalojamiento> findByExample(Tipoalojamiento instance) {
		log.debug("finding Tipoalojamiento instance by example");
		try {
			List<Tipoalojamiento> results = (List<Tipoalojamiento>) sessionFactory.getCurrentSession()
					.createCriteria("dao.Tipoalojamiento").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
