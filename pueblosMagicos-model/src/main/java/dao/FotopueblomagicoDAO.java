package dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.Estado;
import dto.FotoSimple;
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
	

	public List<FotoSimple> readAllSimpleByIdPuebloMagico(int idPuebloMagico) 
	{
		List<FotoSimple> result = null;
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery("select idfotopueblomagico, descripcion from fotopueblomagico where pm_idpueblomagico=:idPM")
				.setParameter("idPM", idPuebloMagico);
		
		result = (List<FotoSimple>) query.list();
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
	
	
}
