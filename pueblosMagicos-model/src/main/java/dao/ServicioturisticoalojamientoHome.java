package dao;
// Generated 30/08/2016 02:56:52 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import dto.Servicioturisticoalojamiento;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Servicioturisticoalojamiento.
 * @see dao.Servicioturisticoalojamiento
 * @author Hibernate Tools
 */
public class ServicioturisticoalojamientoHome {

	private static final Log log = LogFactory.getLog(ServicioturisticoalojamientoHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Servicioturisticoalojamiento transientInstance) {
		log.debug("persisting Servicioturisticoalojamiento instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Servicioturisticoalojamiento instance) {
		log.debug("attaching dirty Servicioturisticoalojamiento instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Servicioturisticoalojamiento instance) {
		log.debug("attaching clean Servicioturisticoalojamiento instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Servicioturisticoalojamiento persistentInstance) {
		log.debug("deleting Servicioturisticoalojamiento instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Servicioturisticoalojamiento merge(Servicioturisticoalojamiento detachedInstance) {
		log.debug("merging Servicioturisticoalojamiento instance");
		try {
			Servicioturisticoalojamiento result = (Servicioturisticoalojamiento) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Servicioturisticoalojamiento findById(int id) {
		log.debug("getting Servicioturisticoalojamiento instance with id: " + id);
		try {
			Servicioturisticoalojamiento instance = (Servicioturisticoalojamiento) sessionFactory.getCurrentSession()
					.get("dao.Servicioturisticoalojamiento", id);
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

	public List<Servicioturisticoalojamiento> findByExample(Servicioturisticoalojamiento instance) {
		log.debug("finding Servicioturisticoalojamiento instance by example");
		try {
			List<Servicioturisticoalojamiento> results = (List<Servicioturisticoalojamiento>) sessionFactory
					.getCurrentSession().createCriteria("dao.Servicioturisticoalojamiento").add(create(instance))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
