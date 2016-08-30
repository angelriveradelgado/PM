package dao;
// Generated 30/08/2016 02:56:52 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import dto.Servicioturisticoaventura;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Servicioturisticoaventura.
 * @see dao.Servicioturisticoaventura
 * @author Hibernate Tools
 */
public class ServicioturisticoaventuraHome {

	private static final Log log = LogFactory.getLog(ServicioturisticoaventuraHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Servicioturisticoaventura transientInstance) {
		log.debug("persisting Servicioturisticoaventura instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Servicioturisticoaventura instance) {
		log.debug("attaching dirty Servicioturisticoaventura instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Servicioturisticoaventura instance) {
		log.debug("attaching clean Servicioturisticoaventura instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Servicioturisticoaventura persistentInstance) {
		log.debug("deleting Servicioturisticoaventura instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Servicioturisticoaventura merge(Servicioturisticoaventura detachedInstance) {
		log.debug("merging Servicioturisticoaventura instance");
		try {
			Servicioturisticoaventura result = (Servicioturisticoaventura) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Servicioturisticoaventura findById(int id) {
		log.debug("getting Servicioturisticoaventura instance with id: " + id);
		try {
			Servicioturisticoaventura instance = (Servicioturisticoaventura) sessionFactory.getCurrentSession()
					.get("dao.Servicioturisticoaventura", id);
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

	public List<Servicioturisticoaventura> findByExample(Servicioturisticoaventura instance) {
		log.debug("finding Servicioturisticoaventura instance by example");
		try {
			List<Servicioturisticoaventura> results = (List<Servicioturisticoaventura>) sessionFactory
					.getCurrentSession().createCriteria("dao.Servicioturisticoaventura").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
