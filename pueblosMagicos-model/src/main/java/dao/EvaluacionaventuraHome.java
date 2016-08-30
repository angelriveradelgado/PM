package dao;
// Generated 30/08/2016 02:56:52 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import dto.Evaluacionaventura;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Evaluacionaventura.
 * @see dao.Evaluacionaventura
 * @author Hibernate Tools
 */
public class EvaluacionaventuraHome {

	private static final Log log = LogFactory.getLog(EvaluacionaventuraHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Evaluacionaventura transientInstance) {
		log.debug("persisting Evaluacionaventura instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Evaluacionaventura instance) {
		log.debug("attaching dirty Evaluacionaventura instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Evaluacionaventura instance) {
		log.debug("attaching clean Evaluacionaventura instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Evaluacionaventura persistentInstance) {
		log.debug("deleting Evaluacionaventura instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Evaluacionaventura merge(Evaluacionaventura detachedInstance) {
		log.debug("merging Evaluacionaventura instance");
		try {
			Evaluacionaventura result = (Evaluacionaventura) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Evaluacionaventura findById(int id) {
		log.debug("getting Evaluacionaventura instance with id: " + id);
		try {
			Evaluacionaventura instance = (Evaluacionaventura) sessionFactory.getCurrentSession()
					.get("dao.Evaluacionaventura", id);
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

	public List<Evaluacionaventura> findByExample(Evaluacionaventura instance) {
		log.debug("finding Evaluacionaventura instance by example");
		try {
			List<Evaluacionaventura> results = (List<Evaluacionaventura>) sessionFactory.getCurrentSession()
					.createCriteria("dao.Evaluacionaventura").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
