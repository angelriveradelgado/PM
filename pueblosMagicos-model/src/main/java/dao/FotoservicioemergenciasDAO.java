package dao;

import java.math.BigInteger;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.FotoServicioEmergenciasSimple;
import dto.FotoServicioTuristicoSimple;
import dto.Fotoservicioemergencias;

@Repository
public class FotoservicioemergenciasDAO{

	private static final Log log = LogFactory.getLog(FotoservicioemergenciasDAO.class);

	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	public boolean create(Fotoservicioemergencias transientInstance) {
		log.debug("creating Fotoservicioemergencias instance");
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
	
	public Fotoservicioemergencias read(int id) {
		log.debug("reading Fotoservicioemergencias instance");
		Fotoservicioemergencias u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (Fotoservicioemergencias)session.get(Fotoservicioemergencias.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}
	
	public List<Fotoservicioemergencias> readAll() {
		List<Fotoservicioemergencias> result = null;
		Session session = sessionFactory.openSession();
		result = session.createCriteria(Fotoservicioemergencias.class).list();
		session.close();
		return result;
	}

	public boolean update(Fotoservicioemergencias transientInstance) {
		log.debug("updating Fotoservicioemergencias instance");
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

	public boolean delete(Fotoservicioemergencias transientInstance) {
		log.debug("deleting Fotoservicioemergencias instance");
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


	public Fotoservicioemergencias findById(java.lang.Integer id) {
		log.debug("getting Fotoservicioemergencias instance with id: " + id);
		Fotoservicioemergencias u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			 u = (Fotoservicioemergencias) session.get(Fotoservicioemergencias.class, id);
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

	public List<FotoServicioEmergenciasSimple> readAllSimpleByIdServicioEmergencias(int id) 
	{
		List<FotoServicioEmergenciasSimple> result = null;
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery("select idfotoServicioEmergencias from fotoServicioEmergencias where sDE_idservicioDeEmergencias=:idSE")
				.addScalar("idfotoServicioEmergencias", new IntegerType())
				.addScalar("descripcion", new StringType())
				.setResultTransformer(Transformers.aliasToBean(FotoServicioEmergenciasSimple.class))
				.setParameter("idSE", id);
		
		result = (List<FotoServicioEmergenciasSimple>) query.list();
		session.close();
		return result;
	}
	
	public Fotoservicioemergencias findByNombreFotoservicioemergencias(String n) {
		log.debug("finding Fotoservicioemergencias instance by example");
		Session session = sessionFactory.openSession();
		try {
			List<Fotoservicioemergencias> results = session.createCriteria(Fotoservicioemergencias.class).add( Restrictions.like("nombreFotoservicioemergencias", n) ).list();
			log.debug("find by example successful, result size: " + results.size());
			return results.get(0);
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public Integer getAutoIncrementValue()
	{
		Integer result=0;
		Session session = sessionFactory.openSession();
		try
		{
		//Query query = session.createSQLQuery("select idfotoPuebloMagico from fotoPuebloMagico order by idfotoPuebloMagico desc limit 1");
		Query query = session.createSQLQuery("SELECT AUTO_INCREMENT "
				+ "FROM  INFORMATION_SCHEMA.TABLES "
				+ "WHERE TABLE_SCHEMA = \"pueblosmagicos\" "
				+ "AND TABLE_NAME = \"fotoServicioEmergencias\"");
		result = ((BigInteger) query.uniqueResult()).intValue();
		session.close();
		}catch(Exception e )
		{
			e.printStackTrace();
		}
		
		return result;
	}
	
}
