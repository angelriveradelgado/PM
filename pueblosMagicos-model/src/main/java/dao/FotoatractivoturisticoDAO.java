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

import dto.FotoAtractivoTuristicoSimple;
import dto.Fotoatractivoturistico;

@Repository
public class FotoatractivoturisticoDAO {

	private static final Log log = LogFactory.getLog(FotoatractivoturisticoDAO.class);

	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	public boolean create(Fotoatractivoturistico transientInstance) {
		log.debug("creating Fotoatractivoturistico instance");
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
	
	public Fotoatractivoturistico read(Integer id) {
		log.debug("reading Fotoatractivoturistico instance");
		Fotoatractivoturistico u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (Fotoatractivoturistico)session.get(Fotoatractivoturistico.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}
	
	public List<Fotoatractivoturistico> readAll() {
		List<Fotoatractivoturistico> result = null;
		Session session = sessionFactory.openSession();
		try
		{
		result = session.createCriteria(Fotoatractivoturistico.class).list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}

	public boolean update(Fotoatractivoturistico transientInstance) {
		log.debug("updating Fotoatractivoturistico instance");
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

	public boolean delete(Fotoatractivoturistico transientInstance) {
		log.debug("deleting Fotoatractivoturistico instance");
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


	public Fotoatractivoturistico findById(java.lang.Integer id) {
		log.debug("getting Fotoatractivoturistico instance with id: " + id);
		Fotoatractivoturistico u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			 u = (Fotoatractivoturistico) session.get(Fotoatractivoturistico.class, id);
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
	
	public List<FotoAtractivoTuristicoSimple> readAllSimpleByIdAtractivoTuristico(Integer id) 
	{
		List<FotoAtractivoTuristicoSimple> result = null;
		Session session = sessionFactory.openSession();
		
		try
		{
		Query query = session.createSQLQuery("select idfotoAtractivoTuristico, descripcion from fotoatractivoturistico where aT_idAtractivoTuristico=:idAT")
				.addScalar("idfotoAtractivoTuristico", new IntegerType())
				.addScalar("descripcion", new StringType())
				.setResultTransformer(Transformers.aliasToBean(FotoAtractivoTuristicoSimple.class))
				.setParameter("idAT", id);
		
		result = (List<FotoAtractivoTuristicoSimple>) query.list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}

	public Fotoatractivoturistico findByNombreFotoatractivoturistico(String n) {
		log.debug("finding Fotoatractivoturistico instance by example");
		List<Fotoatractivoturistico> results = null;
		Fotoatractivoturistico result = null;
		Session session = sessionFactory.openSession();
		try {
			results = session.createCriteria(Fotoatractivoturistico.class).add( Restrictions.like("nombreFotoatractivoturistico", n) ).list();
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
				+ "AND TABLE_NAME = \"fotoatractivoturistico\"");
		result = ((BigInteger) query.uniqueResult()).intValue();
		}catch(Exception e )
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}
	
}
