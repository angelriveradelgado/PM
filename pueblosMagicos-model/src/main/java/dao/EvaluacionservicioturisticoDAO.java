package dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.FloatType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.Calificacionpueblomagico;
import dto.Evaluacionservicioturistico;
import dto.Pueblomagico;
import dto.Servicioturistico;

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
	
	public List<Evaluacionservicioturistico> getEvaluacionservicioturisticoByIdServicioAndByLimit( int id, int first, int numRegistros  )
	{
		List<Evaluacionservicioturistico> result = null;
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery("select e.idEvaluacion, e.comentario, "
				+ "e.t_idUsuario as TIdUsuario, e.aspectoEstablecimiento, "
				+ "e.atencionCliente, e.eficienciaServicio,"
				+ "e.higieneEstablecimiento, e.relacionPrecioCalidad, "
				+ "e.accesibilidad, e.comunicacion, e.manejoIdiomas,"
				+ "e.senalamientoInterno, e.senalamientoExterno, "
				+ "e.sT_idServicioTuristico as sTIdServicioTuristico, e.tE_idEvaluacion as teIdEvaluacion, "
				+ "e.promedio "
				+ "from evaluacionServicioTuristico e, servicioTuristico s	"
				+ "where s.idServicioTuristico=e.sT_idServicioTuristico "
				+ "and s.idServicioTuristico=:id "
				+ "limit first, numRegistros")
		.addScalar("idEvaluacion", new IntegerType())
		.addScalar("comentario", new StringType())
		.addScalar("TIdUsuario", new IntegerType())
		.addScalar("teIdEvaluacion", new IntegerType())
		.addScalar("aspectoEstablecimiento", new IntegerType())
		.addScalar("atencionCliente", new IntegerType())
		.addScalar("eficienciaServicio", new IntegerType())
		.addScalar("higieneEstablecimiento", new IntegerType())
		.addScalar("relacionPrecioCalidad", new IntegerType())
		.addScalar("accesibilidad", new IntegerType())
		.addScalar("comunicacion", new IntegerType())
		.addScalar("manejoIdiomas", new IntegerType())
		.addScalar("senalamientoInterno", new IntegerType())
		.addScalar("senalamientoExterno", new IntegerType())
		.addScalar("sTIdServicioTuristico", new IntegerType())
		.addScalar("promedio", new FloatType())
		.setResultTransformer(Transformers.aliasToBean(Evaluacionservicioturistico.class))
		.setParameter("id", id);
		
		result = (List<Evaluacionservicioturistico>) query.list();
		session.close();
		return result;
	}

}
