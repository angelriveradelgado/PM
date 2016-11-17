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
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.Registrovisita;

@Repository
public class RegistrovisitaDAO{

	private static final Log log = LogFactory.getLog(RegistrovisitaDAO.class);

	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	public boolean create(Registrovisita transientInstance) {
		log.debug("creating Registrovisita instance");
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
	
	public Registrovisita read(Integer id) {
		log.debug("reading Registrovisita instance");
		Registrovisita u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (Registrovisita)session.get(Registrovisita.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}
	
	public List<Registrovisita> readAll() {
		List<Registrovisita> result = null;
		Session session = sessionFactory.openSession();
		try
		{
			result = session.createCriteria(Registrovisita.class).list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}

	public boolean update(Registrovisita transientInstance) {
		log.debug("updating Registrovisita instance");
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

	public boolean delete(Registrovisita transientInstance) {
		log.debug("deleting Registrovisita instance");
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


	public Registrovisita findById(java.lang.Integer id) {
		log.debug("getting Registrovisita instance with id: " + id);
		Registrovisita u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			 u = (Registrovisita) session.get(Registrovisita.class, id);
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

	public Registrovisita findByNombreRegistrovisita(String n) {
		log.debug("finding Registrovisita instance by example");
		List<Registrovisita> results = null;
		Registrovisita result = null;
		Session session = sessionFactory.openSession();
		try {
			results = session.createCriteria(Registrovisita.class).add( Restrictions.like("nombreRegistrovisita", n) ).list();
			log.debug("find by example successful, result size: " + results.size());
			result = results.get(0);
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			re.printStackTrace();
		}
		session.close();
		return result;
	}
	
	public List<Registrovisita> getRegistrovisitaByLimit(Integer first, Integer numRegistros) 
	{
		log.debug("finding Pueblomagico instance by example");
		List<Registrovisita> results = null;
		Session session = sessionFactory.openSession();
		try {
			Criteria crit;
			crit = session.createCriteria(Registrovisita.class);
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
	
	public List<Registrovisita> getRegistrosByIdTurista(Integer id) 
	{
		List<Registrovisita> result = null;
		Session session = sessionFactory.openSession();
		
		try
		{
			Query query = session.createSQLQuery("select r.idregistroVisita, r.t_idUsuario as TIdUsuario,  "
					+ "r.e_idEstablecimiento as EIdEstablecimiento,  "
					+ "r.pM_idPuebloMagico as pmIdPuebloMagico,  "
					+ "r.aT_idAtractivoTuristico as atIdAtractivoTuristico, "
					+ "r.sT_idServicioTuristico as stIdServicioTuristico, "
					+ "r.fecha, r.evaluado "
					+ "from registrovisita r, turista t "
					+ "where r.t_idUsuario=t.idUsuario "
					+ "and t.idUsuario=:id")
			.addScalar("idregistroVisita", new IntegerType())
			.addScalar("TIdUsuario", new IntegerType())
			.addScalar("EIdEstablecimiento", new IntegerType())
			.addScalar("pmIdPuebloMagico", new IntegerType())
			.addScalar("atIdAtractivoTuristico", new IntegerType())
			.addScalar("stIdServicioTuristico", new IntegerType())
			.addScalar("fecha", new DateType())
			.addScalar("evaluado", new IntegerType())
			.setResultTransformer(Transformers.aliasToBean(Registrovisita.class))
			.setParameter("id", id);
			
			result = (List<Registrovisita>) query.list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}
	
	public List<Registrovisita> getRegistrosByIdTuristaByIdAtractivoTuristico(Integer idTurista, Integer idAtractivo) 
	{
		List<Registrovisita> result = null;
		Session session = sessionFactory.openSession();
		
		try
		{
			Query query = session.createSQLQuery("select r.idregistroVisita, r.t_idUsuario as TIdUsuario,  "
					+ "r.e_idEstablecimiento as EIdEstablecimiento,  "
					+ "r.pM_idPuebloMagico as pmIdPuebloMagico,  "
					+ "r.aT_idAtractivoTuristico as atIdAtractivoTuristico, "
					+ "r.sT_idServicioTuristico as stIdServicioTuristico, "
					+ "r.fecha, r.evaluado "
					+ "from registrovisita r "
					+ "where r.t_idUsuario=:idTurista "
					+ "and r.aT_idAtractivoTuristico=:idAtractivo")
			.addScalar("idregistroVisita", new IntegerType())
			.addScalar("TIdUsuario", new IntegerType())
			.addScalar("EIdEstablecimiento", new IntegerType())
			.addScalar("pmIdPuebloMagico", new IntegerType())
			.addScalar("atIdAtractivoTuristico", new IntegerType())
			.addScalar("stIdServicioTuristico", new IntegerType())
			.addScalar("fecha", new DateType())
			.addScalar("evaluado", new IntegerType())
			.setResultTransformer(Transformers.aliasToBean(Registrovisita.class))
			.setParameter("idTurista", idTurista)
			.setParameter("idAtractivo", idAtractivo);
			
			result = (List<Registrovisita>) query.list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}
	
	public List<Registrovisita> getRegistrosByIdTuristaByIdEstablecimientoByIdServicioTuristico(Integer idTurista, Integer idEstablecimiento, Integer idServicioTuristico) 
	{
		List<Registrovisita> result = null;
		Session session = sessionFactory.openSession();
		
		try
		{
			Query query = session.createSQLQuery("select r.idregistroVisita, r.t_idUsuario as TIdUsuario,  "
					+ "r.e_idEstablecimiento as EIdEstablecimiento,  "
					+ "r.pM_idPuebloMagico as pmIdPuebloMagico,  "
					+ "r.aT_idAtractivoTuristico as atIdAtractivoTuristico, "
					+ "r.sT_idServicioTuristico as stIdServicioTuristico, "
					+ "r.fecha, r.evaluado "
					+ "from registrovisita r "
					+ "where r.t_idUsuario=:idTurista "
					+ "and r.e_idEstablecimiento=:idEstablecimiento "
					+ "and r.sT_idServicioTuristico=:idServicioTuristico")
			.addScalar("idregistroVisita", new IntegerType())
			.addScalar("TIdUsuario", new IntegerType())
			.addScalar("EIdEstablecimiento", new IntegerType())
			.addScalar("pmIdPuebloMagico", new IntegerType())
			.addScalar("atIdAtractivoTuristico", new IntegerType())
			.addScalar("stIdServicioTuristico", new IntegerType())
			.addScalar("fecha", new DateType())
			.addScalar("evaluado", new IntegerType())
			.setResultTransformer(Transformers.aliasToBean(Registrovisita.class))
			.setParameter("idTurista", idTurista)
			.setParameter("idEstablecimiento", idEstablecimiento)
			.setParameter("idServicioTuristico", idServicioTuristico);
			
			result = (List<Registrovisita>) query.list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}
	
	public List<Registrovisita> getRegistrosByIdTuristaByIdPuebloMagico(Integer idTurista, Integer idPuebloMagico) 
	{
		List<Registrovisita> result = null;
		Session session = sessionFactory.openSession();
		
		try
		{
			Query query = session.createSQLQuery("select r.idregistroVisita, r.t_idUsuario as TIdUsuario,  "
					+ "r.e_idEstablecimiento as EIdEstablecimiento,  "
					+ "r.pM_idPuebloMagico as pmIdPuebloMagico,  "
					+ "r.aT_idAtractivoTuristico as atIdAtractivoTuristico, "
					+ "r.sT_idServicioTuristico as stIdServicioTuristico, "
					+ "r.fecha, r.evaluado "
					+ "from registrovisita r "
					+ "where r.t_idUsuario=:idTurista "
					+ "and r.pM_idPuebloMagico=:idPuebloMagico")
			.addScalar("idregistroVisita", new IntegerType())
			.addScalar("TIdUsuario", new IntegerType())
			.addScalar("EIdEstablecimiento", new IntegerType())
			.addScalar("pmIdPuebloMagico", new IntegerType())
			.addScalar("atIdAtractivoTuristico", new IntegerType())
			.addScalar("stIdServicioTuristico", new IntegerType())
			.addScalar("fecha", new DateType())
			.addScalar("evaluado", new IntegerType())
			.setResultTransformer(Transformers.aliasToBean(Registrovisita.class))
			.setParameter("idTurista", idTurista)
			.setParameter("idPuebloMagico", idPuebloMagico);
			
			result = (List<Registrovisita>) query.list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}
	
	public List<Registrovisita> getRegistrosNoEvaluadosByIdTurista(Integer id) 
	{
		List<Registrovisita> result = null;
		Session session = sessionFactory.openSession();
		
		try
		{
			Query query = session.createSQLQuery("select r.idregistroVisita, r.t_idUsuario as TIdUsuario,  "
					+ "r.e_idEstablecimiento as EIdEstablecimiento,  "
					+ "r.pM_idPuebloMagico as pmIdPuebloMagico,  "
					+ "r.aT_idAtractivoTuristico as atIdAtractivoTuristico, "
					+ "r.sT_idServicioTuristico as stIdServicioTuristico, "
					+ "r.fecha, r.evaluado "
					+ "from registrovisita r, turista t "
					+ "where r.t_idUsuario=t.idUsuario "
					+ "and t.idUsuario=:id and r.evaluado=0")
			.addScalar("idregistroVisita", new IntegerType())
			.addScalar("TIdUsuario", new IntegerType())
			.addScalar("EIdEstablecimiento", new IntegerType())
			.addScalar("pmIdPuebloMagico", new IntegerType())
			.addScalar("atIdAtractivoTuristico", new IntegerType())
			.addScalar("stIdServicioTuristico", new IntegerType())
			.addScalar("fecha", new DateType())
			.addScalar("evaluado", new IntegerType())
			.setResultTransformer(Transformers.aliasToBean(Registrovisita.class))
			.setParameter("id", id);
			
			result = (List<Registrovisita>) query.list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}
	
	public List<Registrovisita> getRegistrosEvaluadosByIdTurista(Integer id) 
	{
		List<Registrovisita> result = null;
		Session session = sessionFactory.openSession();
		
		try
		{
			Query query = session.createSQLQuery("select r.idregistroVisita, r.t_idUsuario as TIdUsuario,  "
					+ "r.e_idEstablecimiento as EIdEstablecimiento,  "
					+ "r.pM_idPuebloMagico as pmIdPuebloMagico,  "
					+ "r.aT_idAtractivoTuristico as atIdAtractivoTuristico, "
					+ "r.sT_idServicioTuristico as stIdServicioTuristico, "
					+ "r.fecha, r.evaluado "
					+ "from registrovisita r, turista t "
					+ "where r.t_idUsuario=t.idUsuario "
					+ "and t.idUsuario=:id and r.evaluado=1")
			.addScalar("idregistroVisita", new IntegerType())
			.addScalar("TIdUsuario", new IntegerType())
			.addScalar("EIdEstablecimiento", new IntegerType())
			.addScalar("pmIdPuebloMagico", new IntegerType())
			.addScalar("atIdAtractivoTuristico", new IntegerType())
			.addScalar("stIdServicioTuristico", new IntegerType())
			.addScalar("fecha", new DateType())
			.addScalar("evaluado", new IntegerType())
			.setResultTransformer(Transformers.aliasToBean(Registrovisita.class))
			.setParameter("id", id);
			
			result = (List<Registrovisita>) query.list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}
	
	
}
