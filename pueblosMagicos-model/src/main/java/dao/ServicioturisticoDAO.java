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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.Atractivoturistico;
import dto.Pueblomagico;
import dto.Servicioturistico;

@Repository
public class ServicioturisticoDAO {

	private static final Log log = LogFactory.getLog(ServicioturisticoDAO.class);

	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	public boolean create(Servicioturistico transientInstance) {
		log.debug("creating Servicioturistico instance");
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
	
	public Servicioturistico read(int id) {
		log.debug("reading Servicioturistico instance");
		Servicioturistico u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (Servicioturistico)session.get(Servicioturistico.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}
	
	public List<Servicioturistico> readAll() {
		List<Servicioturistico> result = null;
		Session session = sessionFactory.openSession();
		result = session.createCriteria(Servicioturistico.class).list();
		session.close();
		return result;
	}

	public boolean update(Servicioturistico transientInstance) {
		log.debug("updating Servicioturistico instance");
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

	public boolean delete(Servicioturistico transientInstance) {
		log.debug("deleting Servicioturistico instance");
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


	public Servicioturistico findById(java.lang.Integer id) {
		log.debug("getting Servicioturistico instance with id: " + id);
		Servicioturistico u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			 u = (Servicioturistico) session.get(Servicioturistico.class, id);
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
	
	public List<Servicioturistico> getServicioturisticoByLimit(int first, int numRegistros) 
	{
		log.debug("finding Pueblomagico instance by example");
		List<Servicioturistico> results = null;
		Session session = sessionFactory.openSession();
		try {
			Criteria crit;
			crit = session.createCriteria(Servicioturistico.class);
			crit.setFirstResult(first);
			crit.setMaxResults(numRegistros);
			results = crit.list();			
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
		return results;
	}
	
	public List<Servicioturistico> findByIdPuebloMagico(int id) 
	{
		List<Servicioturistico> result = null;
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery("select st.* from servicioTuristico st, establecimiento e, asentamiento a, pueblomagico pm "
				+ "where st.e_idEstablecimiento=e.idEstablecimiento "
				+ "and e.a_idAsentamiento=a.idAsentamiento  "
				+ "and a.m_idMunicipio=m.idMunicipio "
				+ "and m.idMunicipio=pm.m_idMunicipio "
				+ "and pm.idPuebloMagico:=idPM")
		.setParameter("idPM", id);
		
		result = (List<Servicioturistico>) query.list();
		session.close();
		return result;
	}
}
