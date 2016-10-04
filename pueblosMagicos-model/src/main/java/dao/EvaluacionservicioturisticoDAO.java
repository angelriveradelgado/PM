package dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.Calificacionpueblomagico;
import dto.Evaluacionservicioturistico;
import dto.Pueblomagico;

@Repository
public class EvaluacionservicioturisticoDAO
{

	private static final Log log = LogFactory.getLog(EvaluacionservicioturisticoDAO.class);

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory( )
	{
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory( SessionFactory sessionFactory )
	{
		this.sessionFactory = sessionFactory;
	}

	public boolean create( Evaluacionservicioturistico transientInstance )
	{
		log.debug("creating Evaluacionservicioturistico instance");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		boolean conf = false;
		try
		{
			tx = session.beginTransaction();
			session.save(transientInstance);
			tx.commit();
			conf = true;
			log.debug("persist successful");
		} catch (HibernateException e)
		{
			if (tx != null)
				tx.rollback();
		}
		session.close();
		return conf;
	}

	public Evaluacionservicioturistico read( int id )
	{
		log.debug("reading Evaluacionservicioturistico instance");
		Evaluacionservicioturistico u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try
		{
			tx = session.beginTransaction();
			u = (Evaluacionservicioturistico) session.get(Evaluacionservicioturistico.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e)
		{
			if (tx != null)
				tx.rollback();
		}
		session.close();
		return u;
	}

	public List<Evaluacionservicioturistico> readAll( )
	{
		List<Evaluacionservicioturistico> result = null;
		Session session = sessionFactory.openSession();
		result = session.createCriteria(Evaluacionservicioturistico.class).list();
		session.close();
		return result;
	}

	public boolean update( Evaluacionservicioturistico transientInstance )
	{
		log.debug("updating Evaluacionservicioturistico instance");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		boolean conf = false;
		try
		{
			tx = session.beginTransaction();
			session.update(transientInstance);
			tx.commit();
			conf = true;
			log.debug("persist successful");
		} catch (HibernateException e)
		{
			if (tx != null)
				tx.rollback();
		}
		session.close();
		return conf;
	}

	public boolean delete( Evaluacionservicioturistico transientInstance )
	{
		log.debug("deleting Evaluacionservicioturistico instance");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		boolean conf = false;
		try
		{
			tx = session.beginTransaction();
			session.delete(transientInstance);
			tx.commit();
			conf = true;
			log.debug("persist successful");
		} catch (HibernateException e)
		{
			if (tx != null)
				tx.rollback();
		}
		session.close();
		return conf;
	}

	public Evaluacionservicioturistico findById( java.lang.Integer id )
	{
		log.debug("getting Evaluacionservicioturistico instance with id: " + id);
		Evaluacionservicioturistico u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try
		{
			tx = session.getTransaction();
			u = (Evaluacionservicioturistico) session.get(Evaluacionservicioturistico.class, id);
			tx.commit();
			if (u == null)
			{
				log.debug("get successful, no instance found");
			} else
			{
				log.debug("get successful, instance found");
			}
		} catch (HibernateException e)
		{
			if (tx != null)
				tx.rollback();
		}
		session.close();
		return u;
	}

	public Evaluacionservicioturistico findByNombreEvaluacionservicioturistico( String n )
	{
		log.debug("finding Evaluacionservicioturistico instance by example");
		Session session = sessionFactory.openSession();
		try
		{
			List<Evaluacionservicioturistico> results = session.createCriteria(Evaluacionservicioturistico.class)
					.add(Restrictions.like("nombreEvaluacionservicioturistico", n)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results.get(0);
		} catch (RuntimeException re)
		{
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<Evaluacionservicioturistico> findByIdServicioTuristico( int idST )
	{
		log.debug("finding Calificacionatractivoturistico instance by example");
		Session session = sessionFactory.openSession();
		try
		{
			List<Evaluacionservicioturistico> results = session.createCriteria(Evaluacionservicioturistico.class)
					.add(Restrictions.like("sT_idServicioTuristico", idST)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re)
		{
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<Evaluacionservicioturistico> getEvaluacionservicioturisticoByLimit( int first, int numRegistros )
	{
		log.debug("finding Pueblomagico instance by example");
		List<Evaluacionservicioturistico> results = null;
		Session session = sessionFactory.openSession();
		try
		{
			Criteria crit;
			crit = session.createCriteria(Evaluacionservicioturistico.class);
			crit.setFirstResult(first);
			crit.setMaxResults(numRegistros);
			results = crit.list();
		} catch (RuntimeException re)
		{
			log.error("find by example failed", re);
			throw re;
		}
		return results;
	}

}
