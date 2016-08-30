package dao;
// Generated 30/08/2016 02:56:52 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import dto.Fotoservicioemergencias;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Fotoservicioemergencias.
 * @see dao.Fotoservicioemergencias
 * @author Hibernate Tools
 */
public class FotoservicioemergenciasHome {

	private static final Log log = LogFactory.getLog(FotoservicioemergenciasHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Fotoservicioemergencias transientInstance) {
		log.debug("persisting Fotoservicioemergencias instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Fotoservicioemergencias instance) {
		log.debug("attaching dirty Fotoservicioemergencias instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Fotoservicioemergencias instance) {
		log.debug("attaching clean Fotoservicioemergencias instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Fotoservicioemergencias persistentInstance) {
		log.debug("deleting Fotoservicioemergencias instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Fotoservicioemergencias merge(Fotoservicioemergencias detachedInstance) {
		log.debug("merging Fotoservicioemergencias instance");
		try {
			Fotoservicioemergencias result = (Fotoservicioemergencias) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Fotoservicioemergencias findById(java.lang.Integer id) {
		log.debug("getting Fotoservicioemergencias instance with id: " + id);
		try {
			Fotoservicioemergencias instance = (Fotoservicioemergencias) sessionFactory.getCurrentSession()
					.get("dao.Fotoservicioemergencias", id);
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

	public List<Fotoservicioemergencias> findByExample(Fotoservicioemergencias instance) {
		log.debug("finding Fotoservicioemergencias instance by example");
		try {
			List<Fotoservicioemergencias> results = (List<Fotoservicioemergencias>) sessionFactory.getCurrentSession()
					.createCriteria("dao.Fotoservicioemergencias").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
