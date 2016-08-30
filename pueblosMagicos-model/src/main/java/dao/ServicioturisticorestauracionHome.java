package dao;
// Generated 30/08/2016 02:56:52 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import dto.Servicioturisticorestauracion;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Servicioturisticorestauracion.
 * @see dao.Servicioturisticorestauracion
 * @author Hibernate Tools
 */
public class ServicioturisticorestauracionHome {

	private static final Log log = LogFactory.getLog(ServicioturisticorestauracionHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Servicioturisticorestauracion transientInstance) {
		log.debug("persisting Servicioturisticorestauracion instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Servicioturisticorestauracion instance) {
		log.debug("attaching dirty Servicioturisticorestauracion instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Servicioturisticorestauracion instance) {
		log.debug("attaching clean Servicioturisticorestauracion instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Servicioturisticorestauracion persistentInstance) {
		log.debug("deleting Servicioturisticorestauracion instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Servicioturisticorestauracion merge(Servicioturisticorestauracion detachedInstance) {
		log.debug("merging Servicioturisticorestauracion instance");
		try {
			Servicioturisticorestauracion result = (Servicioturisticorestauracion) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Servicioturisticorestauracion findById(int id) {
		log.debug("getting Servicioturisticorestauracion instance with id: " + id);
		try {
			Servicioturisticorestauracion instance = (Servicioturisticorestauracion) sessionFactory.getCurrentSession()
					.get("dao.Servicioturisticorestauracion", id);
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

	public List<Servicioturisticorestauracion> findByExample(Servicioturisticorestauracion instance) {
		log.debug("finding Servicioturisticorestauracion instance by example");
		try {
			List<Servicioturisticorestauracion> results = (List<Servicioturisticorestauracion>) sessionFactory
					.getCurrentSession().createCriteria("dao.Servicioturisticorestauracion").add(create(instance))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
