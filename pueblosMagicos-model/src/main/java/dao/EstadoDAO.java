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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.Estado;
import dto.Pueblomagico;

@Repository
public class EstadoDAO 
{

	private static final Log log = LogFactory.getLog(EstadoDAO.class);

	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	public boolean create(Estado transientInstance) {
		log.debug("creating Estado instance");
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
	
	public Estado read(int id) {
		log.debug("reading Estado instance");
		Estado u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (Estado)session.get(Estado.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}
	
	public List<Estado> readAll() 
	{
		List<Estado> result = null;
		Session session = sessionFactory.openSession();
		result = session.createCriteria(Estado.class).list();
		session.close();
		return result;
	}
	
	public List<Estado> readAllWithPM() 
	{
		List<Estado> result = null;
		Session session = sessionFactory.openSession();
		
		result = (List<Estado>) session.createSQLQuery("select distinct e.* "
				+ " from estado e, municipio m, pueblomagico pm "
				+ " where e.idEstado=m.e_idestado and m.idmunicipio=pm.m_idmunicipio").list();
		session.close();
		return result;
	}
	
	
	public boolean update(Estado transientInstance) {
		log.debug("updating Estado instance");
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

	public boolean delete(Estado transientInstance) {
		log.debug("deleting Estado instance");
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


	public Estado findById(java.lang.Integer id) {
		log.debug("getting Estado instance with id: " + id);
		Estado u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			 u = (Estado) session.get(Estado.class, id);
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

	public Estado findByNombreEstado(String n) {
		log.debug("finding Estado instance by example");
		Session session = sessionFactory.openSession();
		try {
			List<Estado> results = session.createCriteria(Estado.class).add( Restrictions.like("nombreEstado", n) ).list();
			log.debug("find by example successful, result size: " + results.size());
			return results.get(0);
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	
}
