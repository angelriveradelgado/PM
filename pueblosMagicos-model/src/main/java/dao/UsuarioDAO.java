package dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import dto.Usuario;

@Repository
public class UsuarioDAO {
	public UsuarioDAO() {
	}

	private static final Log log = LogFactory.getLog(UsuarioDAO.class);

	/*
	 * private final SessionFactory sessionFactory = getSessionFactory();
	 * 
	 * protected SessionFactory getSessionFactory() { try { SessionFactory
	 * sessionFactory = new Configuration().configure( "hibernate.cfg.xml")
	 * .buildSessionFactory(); return sessionFactory; } catch (Exception e) {
	 * log.error("Initial SessionFactory creation failed." + e); throw new
	 * IllegalStateException("Initial Session Factory creation failed."); } }
	 */

	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}
	

	public boolean create(Usuario u) 
	{
		log.debug("creating Usuario instance");
		Session session = sessionFactory.openSession();
		
		
		
		Transaction tx = null;
		boolean conf = false;
		try {
			tx = session.beginTransaction();
			session.save(u);
			tx.commit();
			conf = true;
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
		}
		session.close();
		return conf;
	}

	public Usuario read(Integer id) {
		log.debug("reading Usuario instance");
		Usuario u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (Usuario) session.get(Usuario.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
		}
		session.close();
		return u;
	}

	public List<Usuario> readAll() {
		List<Usuario> result = null;
		Session session = sessionFactory.openSession();
		try
		{
			result = session.createCriteria(Usuario.class).list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}
	
	public boolean update(Usuario transientInstance) {
		log.debug("updating Usuario instance");
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
			if (tx != null)
				tx.rollback();
		}
		session.close();
		return conf;
	}

	public boolean delete(Usuario transientInstance) {
		log.debug("deleting Usuario instance");
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
			if (tx != null)
				tx.rollback();
		}
		session.close();
		return conf;
	}

	public Usuario findById(java.lang.Integer id) {
		log.debug("getting Usuario instance with id: " + id);
		Usuario u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			u = (Usuario) session.get(Usuario.class, id);
			tx.commit();
			if (u == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
		}
		session.close();
		return u;
	}

	public Usuario findByNombreUsuario(String n) {
		log.debug("finding Usuario instance by example");
		Usuario u = null;
		Session session = sessionFactory.openSession();
		
		try {
			
			List<Usuario> results = session.createCriteria(Usuario.class).add(Restrictions.like("nombreUsuario", n))
					.list();
			
			log.debug("find by example successful, result size: " + results.size());
			u =  results.get(0);
			
			
		} catch (Exception e ) {
			e.printStackTrace();
		}
		session.close();
		return u;
	}

	/*
	 * public static void main(String[] args) { Usuario u = new Usuario();
	 * UsuarioDAO uh = new UsuarioDAO();
	 * 
	 * //u.setIdUsuario(3); //u.setApellidoMaterno("Delgado");
	 * //u.setApellidoPaterno("Rivera"); //u.setContrasena("Contrasena");
	 * //u.setCorreo("angel@gmail.com"); //u.setNombre("AngelMod");
	 * //u.setNombreUsuario("user"); //u.setTipoUsuarioIdtipoUsuario(1);
	 * //u.setUrlfotografia("fotos/f.jpg");
	 * 
	 * //System.out.println( uh.read(1).getNombre() ); //uh.delete(u); u =
	 * uh.findByNombreUsuario("user"); //u = uh.findById(2); System.out.println(
	 * "DAO" + u.getNombre() ); }
	 */

}
