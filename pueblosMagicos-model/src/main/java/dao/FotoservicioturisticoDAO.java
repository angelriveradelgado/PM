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

import dto.FotoServicioTuristicoSimple;
import dto.Fotoservicioturistico;

@Repository
public class FotoservicioturisticoDAO{

	private static final Log log = LogFactory.getLog(FotoservicioturisticoDAO.class);

	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	public boolean create(Fotoservicioturistico transientInstance) {
		log.debug("creating Fotoservicioturistico instance");
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
	
	public Fotoservicioturistico read(Integer id) {
		log.debug("reading Fotoservicioturistico instance");
		Fotoservicioturistico u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (Fotoservicioturistico)session.get(Fotoservicioturistico.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}
	
	public List<Fotoservicioturistico> readAll() {
		List<Fotoservicioturistico> result = null;
		Session session = sessionFactory.openSession();
		try 
		{
			result = session.createCriteria(Fotoservicioturistico.class).list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}

	public boolean update(Fotoservicioturistico transientInstance) {
		log.debug("updating Fotoservicioturistico instance");
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

	public boolean delete(Fotoservicioturistico transientInstance) {
		log.debug("deleting Fotoservicioturistico instance");
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


	public Fotoservicioturistico findById(java.lang.Integer id) {
		log.debug("getting Fotoservicioturistico instance with id: " + id);
		Fotoservicioturistico u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			 u = (Fotoservicioturistico) session.get(Fotoservicioturistico.class, id);
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

	public List<FotoServicioTuristicoSimple> readAllSimpleByIdServicioTuristico(Integer id) 
	{
		List<FotoServicioTuristicoSimple> result = null;
		Session session = sessionFactory.openSession();
		
		try
		{
			Query query = session.createSQLQuery("select idfotoServicioTuristico, descripcion from fotoservicioturistico where sT_idServicio=:idST")
					.addScalar("idfotoServicioTuristico", new IntegerType())
					.addScalar("descripcion", new StringType())
					.setResultTransformer(Transformers.aliasToBean(FotoServicioTuristicoSimple.class))
					.setParameter("idST", id);
			
			result = (List<FotoServicioTuristicoSimple>) query.list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		session.close();
		return result;
	}
	
	public Fotoservicioturistico findByNombreFotoservicioturistico(String n) {
		log.debug("finding Fotoservicioturistico instance by example");
		List<Fotoservicioturistico> results = null;
		Fotoservicioturistico result = null;
		Session session = sessionFactory.openSession();
		try {
			results = session.createCriteria(Fotoservicioturistico.class).add( Restrictions.like("nombreFotoservicioturistico", n) ).list();
			log.debug("find by example successful, result size: " + results.size());
			result = results.get(0);
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			re.printStackTrace();
		}
		session.close();
		return result;
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
				+ "WHERE TABLE_SCHEMA = \"pueblosMagicos\" "
				+ "AND TABLE_NAME = \"fotoservicioturistico\"");
		result = ((BigInteger) query.uniqueResult()).intValue();
		}catch(Exception e )
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}
}
