package dao;
// Generated 30/08/2016 02:56:52 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import dto.Tipovialidad;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Tipovialidad.
 * @see dao.Tipovialidad
 * @author Hibernate Tools
 */
public class TipovialidadHome {

	private static final Log log = LogFactory.getLog(TipovialidadHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Tipovialidad transientInstance) {
		log.debug("persisting Tipovialidad instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Tipovialidad instance) {
		log.debug("attaching dirty Tipovialidad instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tipovialidad instance) {
		log.debug("attaching clean Tipovialidad instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Tipovialidad persistentInstance) {
		log.debug("deleting Tipovialidad instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tipovialidad merge(Tipovialidad detachedInstance) {
		log.debug("merging Tipovialidad instance");
		try {
			Tipovialidad result = (Tipovialidad) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Tipovialidad findById(java.lang.Integer id) {
		log.debug("getting Tipovialidad instance with id: " + id);
		try {
			Tipovialidad instance = (Tipovialidad) sessionFactory.getCurrentSession().get("dao.Tipovialidad", id);
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

	public List<Tipovialidad> findByExample(Tipovialidad instance) {
		log.debug("finding Tipovialidad instance by example");
		try {
			List<Tipovialidad> results = (List<Tipovialidad>) sessionFactory.getCurrentSession()
					.createCriteria("dao.Tipovialidad").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
