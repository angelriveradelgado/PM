package dao;

import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.Estado;
import dto.Pueblomagico;

@Repository
public class EstadoDAO
{

	private static final Log log = LogFactory.getLog(EstadoDAO.class);

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

	public boolean create( Estado transientInstance )
	{
		log.debug("creating Estado instance");
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

	public Estado read( Integer id )
	{
		log.debug("reading Estado instance");
		Estado u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try
		{
			tx = session.beginTransaction();
			u = (Estado) session.get(Estado.class, id);
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

	public List<Estado> readAll( )
	{
		List<Estado> result = null;
		Session session = sessionFactory.openSession();
		try
		{
			result = session.createCriteria(Estado.class).list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}

	public List<Estado> readAllWithPM() 
	{
		List<Estado> result = null;
		Session session = sessionFactory.openSession();
		
		try
		{
			/*result = (List<Estado>) session.createSQLQuery("select distinct e.* "
					+ " from estado e, municipio m, pueblomagico pm "
					+ " where e.idEstado=m.e_idestado and m.idmunicipio=pm.m_idmunicipio").list();*/
			Query q = session.createSQLQuery("select distinct e.* "
					+ " from estado e, municipio m, pueblomagico pm "
					+ " where e.idEstado=m.e_idestado and m.idmunicipio=pm.m_idmunicipio")
					.addScalar("idEstado", new IntegerType())
					.addScalar("nombreEstado", new StringType())
					.setResultTransformer(Transformers.aliasToBean(Estado.class));
		
			/*for(Object e: q.list())
			{
				result.add((Estado)e);
			}*/
			
			result = q.list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}

	public boolean update( Estado transientInstance )
	{
		log.debug("updating Estado instance");
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

	public boolean delete( Estado transientInstance )
	{
		log.debug("deleting Estado instance");
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

	public Estado findById( java.lang.Integer id )
	{
		log.debug("getting Estado instance with id: " + id);
		Estado u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try
		{
			tx = session.getTransaction();
			u = (Estado) session.get(Estado.class, id);
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

	public Estado findByNombreEstado( String n )
	{
		log.debug("finding Estado instance by example");
		Session session = sessionFactory.openSession();
		List<Estado> results = null;
		Estado result = null;
		try
		{
			results = session.createCriteria(Estado.class).add(Restrictions.like("nombreEstado", n))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			
			result = results.get(0);
		} catch (RuntimeException re)
		{
			log.error("find by example failed", re);
			re.printStackTrace();
		}
		session.close();
		return result;
	}

}
