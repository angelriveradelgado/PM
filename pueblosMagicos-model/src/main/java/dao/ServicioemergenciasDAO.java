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
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.Pueblomagico;
import dto.Servicioemergencias;
import dto.Servicioturistico;

@Repository
public class ServicioemergenciasDAO {

	private static final Log log = LogFactory.getLog(ServicioemergenciasDAO.class);

	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	public boolean create(Servicioemergencias transientInstance) {
		log.debug("creating Servicioemergencias instance");
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
	
	public Servicioemergencias read(int id) {
		log.debug("reading Servicioemergencias instance");
		Servicioemergencias u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (Servicioemergencias)session.get(Servicioemergencias.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}
	
	public List<Servicioemergencias> readAll() {
		List<Servicioemergencias> result = null;
		Session session = sessionFactory.openSession();
		result = session.createCriteria(Servicioemergencias.class).list();
		session.close();
		return result;
	}

	public boolean update(Servicioemergencias transientInstance) {
		log.debug("updating Servicioemergencias instance");
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

	public boolean delete(Servicioemergencias transientInstance) {
		log.debug("deleting Servicioemergencias instance");
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


	public Servicioemergencias findById(java.lang.Integer id) {
		log.debug("getting Servicioemergencias instance with id: " + id);
		Servicioemergencias u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			 u = (Servicioemergencias) session.get(Servicioemergencias.class, id);
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
	
	public List<Servicioemergencias> getServicioemergenciasByLimit(int first, int numRegistros) 
	{
		log.debug("finding Pueblomagico instance by example");
		List<Servicioemergencias> results = null;
		Session session = sessionFactory.openSession();
		try {
			Criteria crit;
			crit = session.createCriteria(Servicioemergencias.class);
			crit.setFirstResult(first);
			crit.setMaxResults(numRegistros);
			results = crit.list();			
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
		return results;
	}
	
	public List<Servicioemergencias> findByIdPuebloMagico(int id) 
	{
		List<Servicioemergencias> result = null;
		Session session = sessionFactory.openSession();
		
		Query query = session.createSQLQuery("select s.idservicioEmergencias, s.nombre, s.longitud, s.latitud, "
				+ "s.descripcion, s.horaInicio, s.horaFin, s.a_idAsentamiento as aIdAsentamiento "
				+ "from servicioEmergencias s, asentamiento a, pueblomagico pm "
				+ "where s.a_idAsentamiento=a.idAsentamiento "
				+ "and a.m_idMunicipio=pm.m_idMunicipio "
				+ "and pm.idPuebloMagico=:idPM")
		.addScalar("idservicioEmergencias", new IntegerType())
		.addScalar("nombre", new IntegerType())
		.addScalar("aIdAsentamiento", new IntegerType())
		.addScalar("longitud", new IntegerType())
		.addScalar("latitud", new IntegerType())
		.addScalar("descripcion", new IntegerType())
		.addScalar("horaInicio", new IntegerType())
		.addScalar("horaFin", new IntegerType())
		.setResultTransformer(Transformers.aliasToBean(Servicioemergencias.class))
		.setParameter("idPM", id);
		
		result = (List<Servicioemergencias>) query.list();
		session.close();
		return result;
	}
	
	public String getNombrePuebloMagico(int idServicioEmergencias)
	{
		String nombre = null;;
		
		try
		{
			Session session = sessionFactory.openSession();
			
			Query query = session.createSQLQuery("select pm.nombre "
					+ "from servicioEmergencias s, asentamiento a, pueblomagico pm "
					+ "where s.a_idAsentamiento=a.idAsentamiento "
					+ "and a.m_idMunicipio=pm.m_idMunicipio "
					+ "and s.idservicioEmergencias=:id")
					.setParameter("id", idServicioEmergencias); 
			nombre = (String) query.uniqueResult();
			session.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return nombre;
	}

}
