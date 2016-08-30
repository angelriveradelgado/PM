package dao;
// Generated 30/08/2016 02:56:52 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import dto.Genero;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Genero.
 * @see dao.Genero
 * @author Hibernate Tools
 */
public class GeneroHome {

	private static final Log log = LogFactory.getLog(GeneroHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Genero transientInstance) {
		log.debug("persisting Genero instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Genero instance) {
		log.debug("attaching dirty Genero instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Genero instance) {
		log.debug("attaching clean Genero instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Genero persistentInstance) {
		log.debug("deleting Genero instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Genero merge(Genero detachedInstance) {
		log.debug("merging Genero instance");
		try {
			Genero result = (Genero) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Genero findById(java.lang.Integer id) {
		log.debug("getting Genero instance with id: " + id);
		try {
			Genero instance = (Genero) sessionFactory.getCurrentSession().get("dao.Genero", id);
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

	public List<Genero> findByExample(Genero instance) {
		log.debug("finding Genero instance by example");
		try {
			List<Genero> results = (List<Genero>) sessionFactory.getCurrentSession().createCriteria("dao.Genero")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
