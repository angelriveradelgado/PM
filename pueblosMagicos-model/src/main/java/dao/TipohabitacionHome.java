package dao;
// Generated 30/08/2016 02:56:52 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import dto.Tipohabitacion;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Tipohabitacion.
 * @see dao.Tipohabitacion
 * @author Hibernate Tools
 */
public class TipohabitacionHome {

	private static final Log log = LogFactory.getLog(TipohabitacionHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Tipohabitacion transientInstance) {
		log.debug("persisting Tipohabitacion instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Tipohabitacion instance) {
		log.debug("attaching dirty Tipohabitacion instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tipohabitacion instance) {
		log.debug("attaching clean Tipohabitacion instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Tipohabitacion persistentInstance) {
		log.debug("deleting Tipohabitacion instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tipohabitacion merge(Tipohabitacion detachedInstance) {
		log.debug("merging Tipohabitacion instance");
		try {
			Tipohabitacion result = (Tipohabitacion) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Tipohabitacion findById(java.lang.Integer id) {
		log.debug("getting Tipohabitacion instance with id: " + id);
		try {
			Tipohabitacion instance = (Tipohabitacion) sessionFactory.getCurrentSession().get("dao.Tipohabitacion", id);
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

	public List<Tipohabitacion> findByExample(Tipohabitacion instance) {
		log.debug("finding Tipohabitacion instance by example");
		try {
			List<Tipohabitacion> results = (List<Tipohabitacion>) sessionFactory.getCurrentSession()
					.createCriteria("dao.Tipohabitacion").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}