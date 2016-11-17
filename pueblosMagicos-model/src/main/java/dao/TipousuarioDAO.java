package dao;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.Tipousuario;

@Repository
public class TipousuarioDAO {

	private static final Log log = LogFactory.getLog(TipousuarioDAO.class);

	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	public boolean create(Tipousuario transientInstance) {
		log.debug("creating Tipousuario instance");
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
	
	public Tipousuario read(Integer id) {
		log.debug("reading Tipousuario instance");
		Tipousuario u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (Tipousuario)session.get(Tipousuario.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}
	
	public List<Tipousuario> readAll() {
		List<Tipousuario> result = null;
		Session session = sessionFactory.openSession();
		try
		{
			result = session.createCriteria(Tipousuario.class).list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}

	public boolean update(Tipousuario transientInstance) {
		log.debug("updating Tipousuario instance");
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

	public boolean delete(Tipousuario transientInstance) {
		log.debug("deleting Tipousuario instance");
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


	public Tipousuario findById(Integer id) {
		log.debug("getting Tipousuario instance with id: " + id);
		Tipousuario u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			 u = (Tipousuario) session.get(Tipousuario.class, id);
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
	
	public Integer getId(String n) {
		log.debug("finding Usuario instance by example");
		List<Tipousuario> results = null;
		Integer result = null;
		Session session = sessionFactory.openSession();
		try {
			results = session.createCriteria(Tipousuario.class).add(Restrictions.like("nombre", n))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			result =  results.get(0).getIdtipoUsuario();
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			re.printStackTrace();
		}
		session.close();
		return result;
	}
}
