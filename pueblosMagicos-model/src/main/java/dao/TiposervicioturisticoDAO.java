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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.Tiposervicioturistico;

@Repository
public class TiposervicioturisticoDAO {

	private static final Log log = LogFactory.getLog(TiposervicioturisticoDAO.class);

	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	public boolean create(Tiposervicioturistico transientInstance) {
		log.debug("creating Tiposervicioturistico instance");
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
	
	public Tiposervicioturistico read(Integer id) {
		log.debug("reading Tiposervicioturistico instance");
		Tiposervicioturistico u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (Tiposervicioturistico)session.get(Tiposervicioturistico.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}
	
	public List<Tiposervicioturistico> readAll() {
		List<Tiposervicioturistico> result = null;
		Session session = sessionFactory.openSession();
		try
		{
			result = session.createCriteria(Tiposervicioturistico.class).addOrder(Order.asc("nombre")).list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		session.close();
		return result;
	}

	public boolean update(Tiposervicioturistico transientInstance) {
		log.debug("updating Tiposervicioturistico instance");
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

	public boolean delete(Tiposervicioturistico transientInstance) {
		log.debug("deleting Tiposervicioturistico instance");
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


	public Tiposervicioturistico findById(Integer id) {
		log.debug("getting Tiposervicioturistico instance with id: " + id);
		Tiposervicioturistico u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			 u = (Tiposervicioturistico) session.get(Tiposervicioturistico.class, id);
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
	
	public String getTipoPrincipal(Integer idTipoServicio)
	{
		String nombre = null;
		Session session = sessionFactory.openSession();
		try
		{
			Query query = session.createSQLQuery("select tipo from( "
					+ "select t.idTipoServicioTuristico, t.nombre as tipo, @pv\\:=t.tST_idTipoServicioTuristico as parent "
					+ "from (select * from tiposervicioturistico order by idTipoServicioTuristico desc ) t "
					+ "join "
					+ "(select @pv\\:=:id)tmp "
					+ "where t.idTipoServicioTuristico=@pv) as a "
					+ "order by idTipoServicioTuristico asc "
					+ "limit 0,1")
					.setParameter("id", idTipoServicio); 
			nombre = (String) query.uniqueResult();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return nombre;
	}
	
}
