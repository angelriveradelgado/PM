package dao;
// Generated 30/08/2016 02:56:52 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import dto.Calificacionpueblomagico;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Calificacionpueblomagico.
 * @see dao.Calificacionpueblomagico
 * @author Hibernate Tools
 */
public class CalificacionpueblomagicoHome {

	private static final Log log = LogFactory.getLog(CalificacionpueblomagicoHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Calificacionpueblomagico transientInstance) {
		log.debug("persisting Calificacionpueblomagico instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Calificacionpueblomagico instance) {
		log.debug("attaching dirty Calificacionpueblomagico instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Calificacionpueblomagico instance) {
		log.debug("attaching clean Calificacionpueblomagico instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Calificacionpueblomagico persistentInstance) {
		log.debug("deleting Calificacionpueblomagico instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Calificacionpueblomagico merge(Calificacionpueblomagico detachedInstance) {
		log.debug("merging Calificacionpueblomagico instance");
		try {
			Calificacionpueblomagico result = (Calificacionpueblomagico) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Calificacionpueblomagico findById(java.lang.Integer id) {
		log.debug("getting Calificacionpueblomagico instance with id: " + id);
		try {
			Calificacionpueblomagico instance = (Calificacionpueblomagico) sessionFactory.getCurrentSession()
					.get("dao.Calificacionpueblomagico", id);
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

	public List<Calificacionpueblomagico> findByExample(Calificacionpueblomagico instance) {
		log.debug("finding Calificacionpueblomagico instance by example");
		try {
			List<Calificacionpueblomagico> results = (List<Calificacionpueblomagico>) sessionFactory.getCurrentSession()
					.createCriteria("dao.Calificacionpueblomagico").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
