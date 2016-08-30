package dao;
// Generated 30/08/2016 02:56:52 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import dto.Evaluacionalojamiento;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Evaluacionalojamiento.
 * @see dao.Evaluacionalojamiento
 * @author Hibernate Tools
 */
public class EvaluacionalojamientoHome {

	private static final Log log = LogFactory.getLog(EvaluacionalojamientoHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Evaluacionalojamiento transientInstance) {
		log.debug("persisting Evaluacionalojamiento instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Evaluacionalojamiento instance) {
		log.debug("attaching dirty Evaluacionalojamiento instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Evaluacionalojamiento instance) {
		log.debug("attaching clean Evaluacionalojamiento instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Evaluacionalojamiento persistentInstance) {
		log.debug("deleting Evaluacionalojamiento instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Evaluacionalojamiento merge(Evaluacionalojamiento detachedInstance) {
		log.debug("merging Evaluacionalojamiento instance");
		try {
			Evaluacionalojamiento result = (Evaluacionalojamiento) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Evaluacionalojamiento findById(int id) {
		log.debug("getting Evaluacionalojamiento instance with id: " + id);
		try {
			Evaluacionalojamiento instance = (Evaluacionalojamiento) sessionFactory.getCurrentSession()
					.get("dao.Evaluacionalojamiento", id);
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

	public List<Evaluacionalojamiento> findByExample(Evaluacionalojamiento instance) {
		log.debug("finding Evaluacionalojamiento instance by example");
		try {
			List<Evaluacionalojamiento> results = (List<Evaluacionalojamiento>) sessionFactory.getCurrentSession()
					.createCriteria("dao.Evaluacionalojamiento").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
