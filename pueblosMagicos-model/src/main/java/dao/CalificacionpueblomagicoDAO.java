package dao;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.Atractivoturistico;
import dto.Calificacionatractivoturistico;
import dto.Calificacionpueblomagico;
import dto.Pueblomagico;

@Repository
public class CalificacionpueblomagicoDAO {

	private static final Log log = LogFactory.getLog(CalificacionpueblomagicoDAO.class);

	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	public boolean create(Calificacionpueblomagico transientInstance) {
		log.debug("creating Calificacionpueblomagico instance");
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
	
	public Calificacionpueblomagico read(Integer id) {
		log.debug("reading Calificacionpueblomagico instance");
		Calificacionpueblomagico u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (Calificacionpueblomagico)session.get(Calificacionpueblomagico.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}
	
	public List<Calificacionpueblomagico> readAll() {
		List<Calificacionpueblomagico> result = null;
		Session session = sessionFactory.openSession();
		try
		{
			result = session.createCriteria(Calificacionpueblomagico.class).list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}

	public boolean update(Calificacionpueblomagico transientInstance) {
		log.debug("updating Calificacionpueblomagico instance");
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

	public boolean delete(Calificacionpueblomagico transientInstance) {
		log.debug("deleting Calificacionpueblomagico instance");
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


	public Calificacionpueblomagico findById(java.lang.Integer id) {
		log.debug("getting Calificacionpueblomagico instance with id: " + id);
		Calificacionpueblomagico u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			 u = (Calificacionpueblomagico) session.get(Calificacionpueblomagico.class, id);
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

	public Calificacionpueblomagico findByNombreCalificacionpueblomagico(String n) {
		log.debug("finding Calificacionpueblomagico instance by example");
		List<Calificacionpueblomagico> results = null;
		Calificacionpueblomagico result = null;
		Session session = sessionFactory.openSession();
		try {
			results = session.createCriteria(Calificacionpueblomagico.class).add( Restrictions.like("nombreCalificacionpueblomagico", n) ).list();
			log.debug("find by example successful, result size: " + results.size());
			result =  results.get(0);
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			re.printStackTrace();
		}
		session.close();
		return result;
	}
	
	public List<Calificacionpueblomagico> findByIdPuebloMagico(Integer idPM) {
		log.debug("finding Calificacionatractivoturistico instance by example");
		List<Calificacionpueblomagico> results = null;
		Session session = sessionFactory.openSession();
		try {
			results = session.createCriteria(Calificacionpueblomagico.class).add( Restrictions.like("pmIdPuebloMagico", idPM) ).list();
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			re.printStackTrace();
		}
		session.close();
		return results;
	}
	
	public List<Calificacionpueblomagico> findByIdUsuarioByIdPuebloMagico(Integer idUsuario, Integer idPM) {
		log.debug("finding Calificacionpueblomagico instance by example");
		List<Calificacionpueblomagico> results = null;
		Session session = sessionFactory.openSession();
		try {
			results = session.createCriteria(Calificacionpueblomagico.class)
					.add( Restrictions.like("pmIdPuebloMagico", idPM) )
					.add( Restrictions.like("TIdUsuario", idUsuario) )
					.list();
			log.debug("find by example successful, result size: " + results.size());
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			re.printStackTrace();
		}
		session.close();
		return results;
	}
	
	public List<Calificacionpueblomagico> getCalificacionpueblomagicoByLimit(Integer first, Integer numRegistros) 
	{
		log.debug("finding Pueblomagico instance by example");
		List<Calificacionpueblomagico> results = null;
		Session session = sessionFactory.openSession();
		try {
			Criteria crit;
			crit = session.createCriteria(Calificacionpueblomagico.class);
			crit.setFirstResult(first);
			crit.setMaxResults(numRegistros);
			results = crit.list();			
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			re.printStackTrace();
		}
		session.close();
		return results;
	}
	
	public List<Calificacionpueblomagico> getCalificacionpueblomagicoByIdPMByLimit(Integer id,Integer first,Integer num)
	{
		List<Calificacionpueblomagico> result = null;
		Session session = sessionFactory.openSession();
		try
		{	
			
			Query query = session.createSQLQuery("select c.idCalificacion, c.calificacion, c.comentario, c.t_idUsuario as TIdUsuario, c.pM_idPuebloMagico as pmIdPuebloMagico "
					+ "from calificacionpueblomagico c "
					+ "where c.pM_idPuebloMagico = :id "
					+ "limit :first, :num")
					.addScalar("idCalificacion", new IntegerType())
					.addScalar("calificacion", new IntegerType())
					.addScalar("comentario", new StringType())
					.addScalar("TIdUsuario", new IntegerType())
					.addScalar("pmIdPuebloMagico", new IntegerType())
					.setResultTransformer(Transformers.aliasToBean(Calificacionpueblomagico.class))
					.setParameter("id", id)
					.setParameter("first", first)
					.setParameter("num", num); 
			result = (List<Calificacionpueblomagico>) query.list();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}
	
}
