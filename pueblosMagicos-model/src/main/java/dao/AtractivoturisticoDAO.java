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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.FloatType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import dto.Atractivoturistico;
import dto.Calificacionatractivoturistico;
import dto.Evaluacionservicioturistico;
import dto.FotoServicioTuristicoSimple;
import dto.Servicioturistico;

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
	
	public Atractivoturistico read(Integer id) {
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
		try
		{
			result = session.createCriteria(Atractivoturistico.class).addOrder(Order.asc("nombre")).list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
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
			e.printStackTrace();
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
		List<Atractivoturistico> results = null;
		Atractivoturistico result = null;
		Session session = sessionFactory.openSession();
		try {
			results = session.createCriteria(Atractivoturistico.class).add( Restrictions.like("nombreAtractivoturistico", n) ).list();
			log.debug("find by example successful, result size: " + results.size());
			result = results.get(0);
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			session.close();
			re.printStackTrace();
		}
		session.close();
		return result;
	}
	
	public List<Atractivoturistico> getAtractivoturisticoByLimit(Integer first, Integer numRegistros) 
	{
		log.debug("finding Pueblomagico instance by example");
		List<Atractivoturistico> results = null;
		Session session = sessionFactory.openSession();
		try {
			Criteria crit;
			crit = session.createCriteria(Atractivoturistico.class);
			crit.setFirstResult(first);
			crit.setMaxResults(numRegistros);
			crit.addOrder(Order.asc("nombre"));
			results = crit.list();			
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			re.printStackTrace();
		}
		session.close();
		return results;
	}
	
	public List<Atractivoturistico> getAtractivoturisticoByPMByLimit(Integer id, Integer first, Integer numRegistros) 
	{
		List<Atractivoturistico> result = null;
		Session session = sessionFactory.openSession();
		
		try
		{
			Query query = session.createSQLQuery("select at.idAtractivoTuristico, at.nombre, at.descripcion, at.latitud, at.longitud, at.t_idUsuario as TIdUsuario, "
					+ "at.tA_idtipoAtractivo as taIdtipoAtractivo, at.a_idUsuario as AIdUsuario, at.a_idAsentamiento as AIdAsentamiento, "
					+ "at.eR_idEstadoRegistro as erIdEstadoRegistro, at.promedio "
					+ "from atractivoturistico at, asentamiento a, pueblomagico pm "
					+ "where at.a_idAsentamiento=a.idAsentamiento "
					+ "and a.m_idMunicipio=pm.m_idMunicipio "
					+ "and pm.idPuebloMagico=:idPM order by at.nombre asc limit :first, :numRegistros ")
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
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}
	
	public List<Atractivoturistico> findByIdPuebloMagico(Integer id) 
	{
		List<Atractivoturistico> result = null;
		Session session = sessionFactory.openSession();
		
		try
		{
			Query query = session.createSQLQuery("select at.idAtractivoTuristico, at.nombre, at.descripcion, at.latitud, at.longitud, at.t_idUsuario as TIdUsuario, "
					+ "at.tA_idtipoAtractivo as taIdtipoAtractivo, at.a_idUsuario as AIdUsuario, at.a_idAsentamiento as AIdAsentamiento, "
					+ "at.eR_idEstadoRegistro as erIdEstadoRegistro, at.promedio "
					+ "from atractivoturistico at, asentamiento a, pueblomagico pm "
					+ "where at.a_idAsentamiento=a.idAsentamiento "
					+ "and a.m_idMunicipio=pm.m_idMunicipio "
					+ "and pm.idPuebloMagico=:idPM order by at.nombre asc")
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
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}
	
	public String getNombrePuebloMagico(Integer idAtractivoTuristico)
	{
		String nombre = null;
		Session session = sessionFactory.openSession();
		try
		{	
			
			Query query = session.createSQLQuery("select distinct pm.nombre "
					+ "from atractivoturistico at, asentamiento a, pueblomagico pm "
					+ "where at.a_idAsentamiento=a.idAsentamiento "
					+ "and a.m_idMunicipio=pm.m_idMunicipio "
					+ "and at.idAtractivoTuristico=:id")
					.setParameter("id", idAtractivoTuristico); 
			nombre = (String) query.uniqueResult();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return nombre;
	}
	
	public String getDireccion(Integer idAtractivoTuristico)
	{
		String direccion = null;
		Session session = sessionFactory.openSession();
		try
		{
			
			
			Query query = session.createSQLQuery("select ta.nombre "
					+ "from tipoasentamiento ta, asentamiento a, atractivoturistico at "
					+ "where ta.idtipoAsentamiento=a.tA_idtipoAsentamiento "
					+ "and a.idAsentamiento=at.a_idAsentamiento "
					+ "and at.idAtractivoTuristico=:id")
					.setParameter("id", idAtractivoTuristico); 
			direccion = (String) query.uniqueResult();
			
			query = session.createSQLQuery("select a.nombreAsentamiento "
					+ "from asentamiento a, atractivoturistico at "
					+ "where a.idAsentamiento=at.a_idAsentamiento "
					+ "and at.idAtractivoTuristico=:id")
					.setParameter("id", idAtractivoTuristico); 
			direccion = direccion + " " + (String) query.uniqueResult();
			
			query = session.createSQLQuery("select m.nombreMunicipio "
					+ "from asentamiento a, atractivoturistico at, municipio m "
					+ "where m.idMunicipio=a.m_idMunicipio "
					+ "and a.idAsentamiento=at.a_idAsentamiento "
					+ "and at.idAtractivoTuristico=:id")
					.setParameter("id", idAtractivoTuristico); 
			direccion = direccion + ", " + (String) query.uniqueResult();
			
			query = session.createSQLQuery("select e.nombreEstado "
					+ "from asentamiento a, atractivoturistico at, municipio m, estado e "
					+ "where e.idEstado=e_idEstado "
					+ "and m.idMunicipio=a.m_idMunicipio "
					+ "and a.idAsentamiento=at.a_idAsentamiento "
					+ "and at.idAtractivoTuristico=:id")
					.setParameter("id", idAtractivoTuristico); 
			direccion = direccion + ", " + (String) query.uniqueResult();
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return direccion;
	}
	
	public void updatePromedio( int idAtractivoTuristico )
	{
		log.debug("updating promedio atractivo");
		List<Integer> promedios = null;
		Session session = sessionFactory.openSession();
		try {
			Criteria crit;
			crit = session.createCriteria(Calificacionatractivoturistico.class)
					.setProjection(Projections.property("calificacion")).
					add(Restrictions.like("atIdatractivoTuristico", idAtractivoTuristico));
			promedios = crit.list();		
			
			float suma = 0;
//			for(int i = 0; i< promedios.size(); i++)
//			{
//				suma = suma + promedios.get(i).getCalificacion();
//			}
			
			for(Integer c : promedios)
			{
				suma = suma + c;
			}
			
			float promedio = suma / promedios.size();
			
			Atractivoturistico a = new Atractivoturistico();
			a = this.read(idAtractivoTuristico);
			a.setPromedio(promedio);
			this.update(a);
			
			
		} catch (RuntimeException re) 
		{
			log.error("update cal aT error", re);
			re.printStackTrace();
		}
		session.close();
	}
	
	
}
