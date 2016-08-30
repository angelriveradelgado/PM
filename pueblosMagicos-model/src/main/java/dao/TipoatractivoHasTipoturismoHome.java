package dao;
// Generated 30/08/2016 02:56:52 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import dto.TipoatractivoHasTipoturismo;
import dto.TipoatractivoHasTipoturismoId;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class TipoatractivoHasTipoturismo.
 * @see dao.TipoatractivoHasTipoturismo
 * @author Hibernate Tools
 */
public class TipoatractivoHasTipoturismoHome {

	private static final Log log = LogFactory.getLog(TipoatractivoHasTipoturismoHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(TipoatractivoHasTipoturismo transientInstance) {
		log.debug("persisting TipoatractivoHasTipoturismo instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TipoatractivoHasTipoturismo instance) {
		log.debug("attaching dirty TipoatractivoHasTipoturismo instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TipoatractivoHasTipoturismo instance) {
		log.debug("attaching clean TipoatractivoHasTipoturismo instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TipoatractivoHasTipoturismo persistentInstance) {
		log.debug("deleting TipoatractivoHasTipoturismo instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TipoatractivoHasTipoturismo merge(TipoatractivoHasTipoturismo detachedInstance) {
		log.debug("merging TipoatractivoHasTipoturismo instance");
		try {
			TipoatractivoHasTipoturismo result = (TipoatractivoHasTipoturismo) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TipoatractivoHasTipoturismo findById(TipoatractivoHasTipoturismoId id) {
		log.debug("getting TipoatractivoHasTipoturismo instance with id: " + id);
		try {
			TipoatractivoHasTipoturismo instance = (TipoatractivoHasTipoturismo) sessionFactory.getCurrentSession()
					.get("dao.TipoatractivoHasTipoturismo", id);
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

	public List<TipoatractivoHasTipoturismo> findByExample(TipoatractivoHasTipoturismo instance) {
		log.debug("finding TipoatractivoHasTipoturismo instance by example");
		try {
			List<TipoatractivoHasTipoturismo> results = (List<TipoatractivoHasTipoturismo>) sessionFactory
					.getCurrentSession().createCriteria("dao.TipoatractivoHasTipoturismo").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
