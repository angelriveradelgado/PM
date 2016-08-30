package dao;
// Generated 30/08/2016 02:56:52 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import dto.Fotopueblomagico;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Fotopueblomagico.
 * @see dao.Fotopueblomagico
 * @author Hibernate Tools
 */
public class FotopueblomagicoHome {

	private static final Log log = LogFactory.getLog(FotopueblomagicoHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Fotopueblomagico transientInstance) {
		log.debug("persisting Fotopueblomagico instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Fotopueblomagico instance) {
		log.debug("attaching dirty Fotopueblomagico instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Fotopueblomagico instance) {
		log.debug("attaching clean Fotopueblomagico instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Fotopueblomagico persistentInstance) {
		log.debug("deleting Fotopueblomagico instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Fotopueblomagico merge(Fotopueblomagico detachedInstance) {
		log.debug("merging Fotopueblomagico instance");
		try {
			Fotopueblomagico result = (Fotopueblomagico) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Fotopueblomagico findById(java.lang.Integer id) {
		log.debug("getting Fotopueblomagico instance with id: " + id);
		try {
			Fotopueblomagico instance = (Fotopueblomagico) sessionFactory.getCurrentSession()
					.get("dao.Fotopueblomagico", id);
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

	public List<Fotopueblomagico> findByExample(Fotopueblomagico instance) {
		log.debug("finding Fotopueblomagico instance by example");
		try {
			List<Fotopueblomagico> results = (List<Fotopueblomagico>) sessionFactory.getCurrentSession()
					.createCriteria("dao.Fotopueblomagico").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
