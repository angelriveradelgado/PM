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
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.Estado;
import dto.Pst;
import dto.Turista;
import dto.Usuario;

@Repository
public class PstDAO {

	private static final Log log = LogFactory.getLog(PstDAO.class);

	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	public boolean create(Pst p, Usuario u) {
		log.debug("creating PST instance");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		boolean conf = false; 
		
		try
		{
			tx = session.beginTransaction();
			System.out.println("pst dao");
			session.beginTransaction();
			Query query = session.createSQLQuery(
			        "insert into usuario(nombreUsuario,contrasena,nombre,apellidoPaterno,apellidoMaterno,correo,tipoUsuario_idtipoUsuario) "
			        + " values(:nombreUsuario,:contrasena,:nombre,:apellidoPat,:apellidoMat,:correo,:tipoUsuario)")
			        .setParameter("nombreUsuario", u.getNombreUsuario())
			        .setParameter("contrasena", u.getContrasena())
			        .setParameter("nombre", u.getNombre())
			        .setParameter("apellidoPat", u.getApellidoPaterno())
			        .setParameter("apellidoMat", u.getApellidoMaterno())
			        .setParameter("correo", u.getCorreo())
			        .setParameter("tipoUsuario", 2);
			
			Query query1 = session.createSQLQuery(
			        "insert into pst(idUsuario,numeroRNT,telefono,razonSocialEmpresa,eR_idEstadoRegistro) "
			        + "values((select idUsuario from usuario order by idUsuario desc limit 1),:numeroRNT,:telefono,:razonSocialEmpresa,:eR_idEstadoRegistro)")
					.setParameter("numeroRNT", p.getNumeroRnt())
			        .setParameter("telefono", p.getTelefono())
			        .setParameter("razonSocialEmpresa", p.getRazonSocialEmpresa())
			        .setParameter("eR_idEstadoRegistro", p.getErIdEstadoRegistro());
			
			
			
			if(query.executeUpdate() != 0 && query1.executeUpdate() != 0)
			{
				tx.commit();
				conf = true;
				System.out.println("pst creado en bd");
			}
			conf = false;
		}catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		
		return conf;
	}
	
	public Pst read(int id) {
		log.debug("reading Pst instance");
		Pst u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (Pst)session.get(Pst.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}
	
	public List<Pst> readAll() {
		List<Pst> result = null;
		Session session = sessionFactory.openSession();
		result = session.createCriteria(Pst.class).list();
		session.close();
		return result;
	}

	public boolean update(Pst transientInstance) {
		log.debug("updating Pst instance");
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

	public boolean delete(Pst transientInstance) {
		log.debug("deleting Pst instance");
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


	public Pst findById(java.lang.Integer id) {
		log.debug("getting Pst instance with id: " + id);
		Pst u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			 u = (Pst) session.get(Pst.class, id);
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

	public Pst findByNombrePst(String n) {
		log.debug("finding Pst instance by example");
		Session session = sessionFactory.openSession();
		try {
			List<Pst> results = session.createCriteria(Pst.class).add( Restrictions.like("nombrePst", n) ).list();
			log.debug("find by example successful, result size: " + results.size());
			return results.get(0);
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<Pst> getPstByLimit(int first, int numRegistros) 
	{
		log.debug("finding Pueblomagico instance by example");
		List<Pst> results = null;
		Session session = sessionFactory.openSession();
		try {
			Criteria crit;
			crit = session.createCriteria(Pst.class);
			crit.setFirstResult(first);
			crit.setMaxResults(numRegistros);
			results = crit.list();			
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
		return results;
	}
	
	
	public List<Pst> readAllPSTByEstadoRegistro(String estado) 
	{
		List<Pst> result = null;
		Session session = sessionFactory.openSession();
		
		try
		{
			Query q = session.createSQLQuery("select p.* from PST p, estadoRegistro e "
					+ "where p.eR_idEstadoRegistro=e.idEstadoRegistro "
					+ "and e.estado=:nombreEstado")
					.addScalar("idUsuario", new IntegerType())
					.addScalar("numeroRNT", new StringType())
					.addScalar("telefono", new StringType())
					.addScalar("razonSocialEmpresa", new StringType())
					.addScalar("v_idUsuario", new IntegerType())
					.addScalar("eR_idEstadoRegistro", new IntegerType())
					.setResultTransformer(Transformers.aliasToBean(Pst.class))
					.setParameter("nombreEstado", estado);
				
			result = q.list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}
	
	
}