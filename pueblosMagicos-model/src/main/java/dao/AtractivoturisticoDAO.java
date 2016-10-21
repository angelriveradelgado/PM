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
import org.hibernate.type.DoubleType;
import org.hibernate.type.FloatType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import dto.Atractivoturistico;
import dto.FotoServicioTuristicoSimple;

@Repository
public class AtractivoturisticoDAO{

	private static final Log log = LogFactory.getLog(AtractivoturisticoDAO.class);

	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	public boolean create(Atractivoturistico transientInstance) {
		log.debug("creating Atractivoturistico instance");
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
	
	public Atractivoturistico read(int id) {
		log.debug("reading Atractivoturistico instance");
		Atractivoturistico u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (Atractivoturistico)session.get(Atractivoturistico.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}

	public List<Atractivoturistico> readAll() {
		List<Atractivoturistico> result = null;
		Session session = sessionFactory.openSession();
		result = session.createCriteria(Atractivoturistico.class).list();
		session.close();
		return result;
	}
	
	public boolean update(Atractivoturistico transientInstance) {
		log.debug("updating Atractivoturistico instance");
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

	public boolean delete(Atractivoturistico transientInstance) {
		log.debug("deleting Atractivoturistico instance");
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


	public Atractivoturistico findById(java.lang.Integer id) {
		log.debug("getting Atractivoturistico instance with id: " + id);
		Atractivoturistico u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			 u = (Atractivoturistico) session.get(Atractivoturistico.class, id);
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

	public Atractivoturistico findByNombreAtractivoturistico(String n) {
		log.debug("finding Atractivoturistico instance by example");
		Session session = sessionFactory.openSession();
		try {
			List<Atractivoturistico> results = session.createCriteria(Atractivoturistico.class).add( Restrictions.like("nombreAtractivoturistico", n) ).list();
			log.debug("find by example successful, result size: " + results.size());
			return results.get(0);
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<Atractivoturistico> getAtractivoturisticoByLimit(int first, int numRegistros) 
	{
		log.debug("finding Pueblomagico instance by example");
		List<Atractivoturistico> results = null;
		Session session = sessionFactory.openSession();
		try {
			Criteria crit;
			crit = session.createCriteria(Atractivoturistico.class);
			crit.setFirstResult(first);
			crit.setMaxResults(numRegistros);
			results = crit.list();			
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
		return results;
	}
	
	public List<Atractivoturistico> getAtractivoturisticoByPMByLimit(int id, int first, int numRegistros) 
	{
		List<Atractivoturistico> result = null;
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery("select at.idAtractivoTuristico, at.nombre, at.descripcion, at.latitud, at.longitud, at.t_idUsuario as TIdUsuario, "
				+ "at.tA_idtipoAtractivo as taIdtipoAtractivo, at.a_idUsuario as AIdUsuario, at.a_idAsentamiento as AIdAsentamiento, "
				+ "at.eR_idEstadoRegistro as erIdEstadoRegistro, at.promedio "
				+ "from atractivoTuristico at, asentamiento a, pueblomagico pm "
				+ "where at.a_idAsentamiento=a.idAsentamiento "
				+ "and a.m_idMunicipio=pm.m_idMunicipio "
				+ "and pm.idPuebloMagico=:idPM limit :first, :numRegistros")
		.addScalar("idAtractivoTuristico", new IntegerType())
		.addScalar("nombre", new StringType())
		.addScalar("descripcion", new StringType())
		.addScalar("latitud", new DoubleType())
		.addScalar("longitud", new DoubleType())
		.addScalar("TIdUsuario", new IntegerType())
		.addScalar("taIdtipoAtractivo", new IntegerType())
		.addScalar("AIdUsuario", new IntegerType())
		.addScalar("AIdAsentamiento", new IntegerType())
		.addScalar("erIdEstadoRegistro", new IntegerType())		
		.addScalar("promedio", new FloatType())		
		.setResultTransformer(Transformers.aliasToBean(Atractivoturistico.class))
		.setParameter("idPM", id)
		.setParameter("first", first)
		.setParameter("numRegistros", numRegistros);
		
		result = (List<Atractivoturistico>) query.list();
		session.close();
		return result;
	}
	
	public List<Atractivoturistico> findByIdPuebloMagico(int id) 
	{
		List<Atractivoturistico> result = null;
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery("select at.idAtractivoTuristico, at.nombre, at.descripcion, at.latitud, at.longitud, at.t_idUsuario as TIdUsuario, "
				+ "at.tA_idtipoAtractivo as taIdtipoAtractivo, at.a_idUsuario as AIdUsuario, at.a_idAsentamiento as AIdAsentamiento, "
				+ "at.eR_idEstadoRegistro as erIdEstadoRegistro, at.promedio "
				+ "from atractivoTuristico at, asentamiento a, pueblomagico pm "
				+ "where at.a_idAsentamiento=a.idAsentamiento "
				+ "and a.m_idMunicipio=pm.m_idMunicipio "
				+ "and pm.idPuebloMagico=:idPM")
		.addScalar("idAtractivoTuristico", new IntegerType())
		.addScalar("nombre", new StringType())
		.addScalar("descripcion", new StringType())
		.addScalar("latitud", new DoubleType())
		.addScalar("longitud", new DoubleType())
		.addScalar("TIdUsuario", new IntegerType())
		.addScalar("taIdtipoAtractivo", new IntegerType())
		.addScalar("AIdUsuario", new IntegerType())
		.addScalar("AIdAsentamiento", new IntegerType())
		.addScalar("erIdEstadoRegistro", new IntegerType())		
		.addScalar("promedio", new FloatType())		
		.setResultTransformer(Transformers.aliasToBean(Atractivoturistico.class))
		.setParameter("idPM", id);
		
		result = (List<Atractivoturistico>) query.list();
		session.close();
		return result;
	}
	
	public String getNombrePuebloMagico(int idAtractivoTuristico)
	{
		String nombre = null;;
		
		try
		{
			Session session = sessionFactory.openSession();
			
			Query query = session.createSQLQuery("select pm.nombre "
					+ "from atractivoTuristico at, asentamiento a, pueblomagico pm "
					+ "where at.a_idAsentamiento=a.idAsentamiento "
					+ "and a.m_idMunicipio=pm.m_idMunicipio "
					+ "and at.idAtractivoTuristico=:id")
					.setParameter("id", idAtractivoTuristico); 
			nombre = (String) query.uniqueResult();
			session.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return nombre;
	}
	
	public String getDireccion(int idAtractivoTuristico)
	{
		String direccion = null;
				
		try
		{
			Session session = sessionFactory.openSession();
			
			Query query = session.createSQLQuery("select ta.nombre "
					+ "from tipoasentamiento ta, asentamiento a, atractivoTuristico at "
					+ "where ta.idtipoAsentamiento=a.tA_idtipoAsentamiento "
					+ "and a.idAsentamiento=at.a_idAsentamiento "
					+ "and at.idAtractivoTuristico=:id")
					.setParameter("id", idAtractivoTuristico); 
			direccion = (String) query.uniqueResult();
			
			query = session.createSQLQuery("select a.nombreAsentamiento "
					+ "from asentamiento a, atractivoTuristico at "
					+ "where a.idAsentamiento=at.a_idAsentamiento "
					+ "and at.idAtractivoTuristico=:id")
					.setParameter("id", idAtractivoTuristico); 
			direccion = direccion + " " + (String) query.uniqueResult();
			
			query = session.createSQLQuery("select m.nombreMunicipio "
					+ "from asentamiento a, atractivoTuristico at, municipio m "
					+ "where m.idMunicipio=a.m_idMunicipio "
					+ "and a.idAsentamiento=at.a_idAsentamiento "
					+ "and at.idAtractivoTuristico=:id")
					.setParameter("id", idAtractivoTuristico); 
			direccion = direccion + ", " + (String) query.uniqueResult();
			
			query = session.createSQLQuery("select e.nombreEstado "
					+ "from asentamiento a, atractivoTuristico at, municipio m, estado e "
					+ "where e.idEstado=e_idEstado "
					+ "and m.idMunicipio=a.m_idMunicipio "
					+ "and a.idAsentamiento=at.a_idAsentamiento "
					+ "and at.idAtractivoTuristico=:id")
					.setParameter("id", idAtractivoTuristico); 
			direccion = direccion + ", " + (String) query.uniqueResult();
			
			session.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return direccion;
	}
	
	
	
}
