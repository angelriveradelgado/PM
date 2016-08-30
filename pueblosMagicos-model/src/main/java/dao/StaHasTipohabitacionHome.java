package dao;
// Generated 30/08/2016 02:56:52 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import dto.StaHasTipohabitacion;
import dto.StaHasTipohabitacionId;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class StaHasTipohabitacion.
 * @see dao.StaHasTipohabitacion
 * @author Hibernate Tools
 */
public class StaHasTipohabitacionHome {

	private static final Log log = LogFactory.getLog(StaHasTipohabitacionHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(StaHasTipohabitacion transientInstance) {
		log.debug("persisting StaHasTipohabitacion instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(StaHasTipohabitacion instance) {
		log.debug("attaching dirty StaHasTipohabitacion instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(StaHasTipohabitacion instance) {
		log.debug("attaching clean StaHasTipohabitacion instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(StaHasTipohabitacion persistentInstance) {
		log.debug("deleting StaHasTipohabitacion instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public StaHasTipohabitacion merge(StaHasTipohabitacion detachedInstance) {
		log.debug("merging StaHasTipohabitacion instance");
		try {
			StaHasTipohabitacion result = (StaHasTipohabitacion) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public StaHasTipohabitacion findById(StaHasTipohabitacionId id) {
		log.debug("getting StaHasTipohabitacion instance with id: " + id);
		try {
			StaHasTipohabitacion instance = (StaHasTipohabitacion) sessionFactory.getCurrentSession()
					.get("dao.StaHasTipohabitacion", id);
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

	public List<StaHasTipohabitacion> findByExample(StaHasTipohabitacion instance) {
		log.debug("finding StaHasTipohabitacion instance by example");
		try {
			List<StaHasTipohabitacion> results = (List<StaHasTipohabitacion>) sessionFactory.getCurrentSession()
					.createCriteria("dao.StaHasTipohabitacion").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
