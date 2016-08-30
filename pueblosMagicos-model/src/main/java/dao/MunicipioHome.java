package dao;
// Generated 30/08/2016 02:56:52 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import dto.Municipio;
import dto.MunicipioId;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Municipio.
 * @see dao.Municipio
 * @author Hibernate Tools
 */
public class MunicipioHome {

	private static final Log log = LogFactory.getLog(MunicipioHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Municipio transientInstance) {
		log.debug("persisting Municipio instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Municipio instance) {
		log.debug("attaching dirty Municipio instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Municipio instance) {
		log.debug("attaching clean Municipio instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Municipio persistentInstance) {
		log.debug("deleting Municipio instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Municipio merge(Municipio detachedInstance) {
		log.debug("merging Municipio instance");
		try {
			Municipio result = (Municipio) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Municipio findById(MunicipioId id) {
		log.debug("getting Municipio instance with id: " + id);
		try {
			Municipio instance = (Municipio) sessionFactory.getCurrentSession().get("dao.Municipio", id);
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

	public List<Municipio> findByExample(Municipio instance) {
		log.debug("finding Municipio instance by example");
		try {
			List<Municipio> results = (List<Municipio>) sessionFactory.getCurrentSession()
					.createCriteria("dao.Municipio").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
