package dao;
// Generated 30/08/2016 02:56:52 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import dto.Tiposerviciorestauracion;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Tiposerviciorestauracion.
 * @see dao.Tiposerviciorestauracion
 * @author Hibernate Tools
 */
public class TiposerviciorestauracionHome {

	private static final Log log = LogFactory.getLog(TiposerviciorestauracionHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Tiposerviciorestauracion transientInstance) {
		log.debug("persisting Tiposerviciorestauracion instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Tiposerviciorestauracion instance) {
		log.debug("attaching dirty Tiposerviciorestauracion instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tiposerviciorestauracion instance) {
		log.debug("attaching clean Tiposerviciorestauracion instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Tiposerviciorestauracion persistentInstance) {
		log.debug("deleting Tiposerviciorestauracion instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tiposerviciorestauracion merge(Tiposerviciorestauracion detachedInstance) {
		log.debug("merging Tiposerviciorestauracion instance");
		try {
			Tiposerviciorestauracion result = (Tiposerviciorestauracion) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Tiposerviciorestauracion findById(java.lang.Integer id) {
		log.debug("getting Tiposerviciorestauracion instance with id: " + id);
		try {
			Tiposerviciorestauracion instance = (Tiposerviciorestauracion) sessionFactory.getCurrentSession()
					.get("dao.Tiposerviciorestauracion", id);
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

	public List<Tiposerviciorestauracion> findByExample(Tiposerviciorestauracion instance) {
		log.debug("finding Tiposerviciorestauracion instance by example");
		try {
			List<Tiposerviciorestauracion> results = (List<Tiposerviciorestauracion>) sessionFactory.getCurrentSession()
					.createCriteria("dao.Tiposerviciorestauracion").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
