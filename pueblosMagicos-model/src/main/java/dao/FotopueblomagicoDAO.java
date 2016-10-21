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

import dto.Estado;
import dto.FotoPuebloMagicoSimple;
import dto.Fotopueblomagico;

@Repository
public class FotopueblomagicoDAO {

	private static final Log log = LogFactory.getLog(FotopueblomagicoDAO.class);

	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	public boolean create(Fotopueblomagico transientInstance) {
		log.debug("creating Fotopueblomagico instance");
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
	
	public Fotopueblomagico read(int id) {
		log.debug("reading Fotopueblomagico instance");
		Fotopueblomagico u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (Fotopueblomagico)session.get(Fotopueblomagico.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}

	public List<Fotopueblomagico> readAll() {
		List<Fotopueblomagico> result = null;
		Session session = sessionFactory.openSession();
		result = session.createCriteria(Fotopueblomagico.class).list();
		session.close();
		return result;
	}
	
	
	public boolean update(Fotopueblomagico transientInstance) {
		log.debug("updating Fotopueblomagico instance");
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

	public boolean delete(Fotopueblomagico transientInstance) {
		log.debug("deleting Fotopueblomagico instance");
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


	public Fotopueblomagico findById(java.lang.Integer id) {
		log.debug("getting Fotopueblomagico instance with id: " + id);
		Fotopueblomagico u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			 u = (Fotopueblomagico) session.get(Fotopueblomagico.class, id);
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
	

	public List<FotoPuebloMagicoSimple> readAllSimpleByIdPuebloMagico(Integer idPuebloMagico) 
	{
		List<FotoPuebloMagicoSimple> result = null;
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery("select idfotoPuebloMagico, descripcion from fotopueblomagico where pm_idpueblomagico=:idPM")
				.addScalar("idfotoPuebloMagico", new IntegerType())
				.addScalar("descripcion", new StringType())
				.setResultTransformer(Transformers.aliasToBean(FotoPuebloMagicoSimple.class))
				.setParameter("idPM", idPuebloMagico);
			
		result = (List<FotoPuebloMagicoSimple>) query.list();
		session.close();
		return result;
	}

	public Fotopueblomagico findByNombreFotopueblomagico(String n) {
		log.debug("finding Fotopueblomagico instance by example");
		Session session = sessionFactory.openSession();
		try {
			List<Fotopueblomagico> results = session.createCriteria(Fotopueblomagico.class).add( Restrictions.like("nombreFotopueblomagico", n) ).list();
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
				+ "AND TABLE_NAME = \"fotoPuebloMagico\"");
		System.out.println("list");
		result = ((BigInteger) query.uniqueResult()).intValue();
		System.out.println("count " + result);
		session.close();
		}catch(Exception e )
		{
			e.printStackTrace();
		}
		
		return result;
	}
	
	
}
