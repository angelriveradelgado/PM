package dao;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.Estado;
import dto.Municipio;
import dto.MunicipioId;

@Repository
public class MunicipioDAO{

	private static final Log log = LogFactory.getLog(MunicipioDAO.class);
	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	public boolean create(Municipio transientInstance) {
		log.debug("creating Municipio instance");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		boolean conf = false; 
		try {
			tx = session.beginTransaction();
			session.save(transientInstance);
			tx.commit();
			conf = true;
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return conf;
	}
	
	public Municipio read(MunicipioId id) {
		log.debug("reading Municipio instance");
		Municipio u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (Municipio)session.get(Municipio.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}
	
	public List<Municipio> readAllByIdEstadoWithPM(Integer idEstado) {
		List<Municipio> result = null;
		Session session = sessionFactory.openSession();
		
		try
		{
			Query q = session.createSQLQuery("select distinct m.idMunicipio, m.nombreMunicipio, m.e_idEstado as EIdEstado "
					+ " from estado e, municipio m, pueblomagico pm  "
					+ "where e.idEstado=m.e_idestado and m.idmunicipio=pm.m_idmunicipio "
					+ "and e.idEstado=:id")
					.addScalar("idMunicipio", new IntegerType())
					.addScalar("nombreMunicipio", new StringType())
					.addScalar("EIdEstado", new IntegerType())
					.setParameter("id", idEstado)
					.setResultTransformer(Transformers.aliasToBean(Municipio.class));
		
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
	
	public List<Municipio> readAll() {
		List<Municipio> result = null;
		Session session = sessionFactory.openSession();
		try
		{
			result = session.createCriteria(Municipio.class).list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}


	public boolean update(Municipio transientInstance) {
		log.debug("updating Municipio instance");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		boolean conf = false; 
		try {
			tx = session.beginTransaction();
			session.update(transientInstance);
			tx.commit();
			conf = true;
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return conf;
	}

	public boolean delete(Municipio transientInstance) {
		log.debug("deleting Municipio instance");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		boolean conf = false;
		try {
			tx = session.beginTransaction();
			session.delete(transientInstance);
			tx.commit();
			conf = true;
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return conf;
	}


	public Municipio findById(java.lang.Integer id) {
		log.debug("getting Municipio instance with id: " + id);
		Municipio u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			 u = (Municipio) session.get(Municipio.class, id);
			tx.commit();
			if (u == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}

	public Municipio findByNombreMunicipio(String n) 
	{
		log.debug("finding Municipio instance by example");
		List<Municipio> results = null;
		Municipio result = null;
		Session session = sessionFactory.openSession();
		try {
			results = session.createCriteria(Municipio.class).add( Restrictions.like("nombreMunicipio", n) ).list();
			log.debug("find by example successful, result size: " + results.size());
			result = results.get(0);
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			re.printStackTrace();
		}
		session.close();
		return result;
	}
	
	public List<Municipio> findByidEstado(Integer id) 
	{
		log.debug("finding Municipio instance by example");
		Session session = sessionFactory.openSession();
		List<Municipio> results = null;
		try {
			results = session.createCriteria(Municipio.class).add( Restrictions.like("EIdEstado", id) ).addOrder(Order.asc("nombreMunicipio")).list();
			log.debug("find by example successful, result size: " + results.size());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return results;
	}
	
}
