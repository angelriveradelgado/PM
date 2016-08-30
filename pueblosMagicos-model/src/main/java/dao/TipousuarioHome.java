package dao;
// Generated 30/08/2016 02:56:52 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import dto.Tipousuario;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Tipousuario.
 * @see dao.Tipousuario
 * @author Hibernate Tools
 */
public class TipousuarioHome {

	private static final Log log = LogFactory.getLog(TipousuarioHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Tipousuario transientInstance) {
		log.debug("persisting Tipousuario instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Tipousuario instance) {
		log.debug("attaching dirty Tipousuario instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tipousuario instance) {
		log.debug("attaching clean Tipousuario instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Tipousuario persistentInstance) {
		log.debug("deleting Tipousuario instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tipousuario merge(Tipousuario detachedInstance) {
		log.debug("merging Tipousuario instance");
		try {
			Tipousuario result = (Tipousuario) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Tipousuario findById(java.lang.Integer id) {
		log.debug("getting Tipousuario instance with id: " + id);
		try {
			Tipousuario instance = (Tipousuario) sessionFactory.getCurrentSession().get("dao.Tipousuario", id);
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

	public List<Tipousuario> findByExample(Tipousuario instance) {
		log.debug("finding Tipousuario instance by example");
		try {
			List<Tipousuario> results = (List<Tipousuario>) sessionFactory.getCurrentSession()
					.createCriteria("dao.Tipousuario").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
