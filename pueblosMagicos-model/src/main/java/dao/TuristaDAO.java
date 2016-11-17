package dao;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.Turista;
import dto.Usuario;

@Repository
public class TuristaDAO {

	private static final Log log = LogFactory.getLog(TuristaDAO.class);

	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}
	
	 /*private final SessionFactory sessionFactory = getSessionFactory();
	  
	  protected SessionFactory getSessionFactory() { try { SessionFactory
	  sessionFactory = new Configuration().configure( "hibernate.cfg.xml")
	  .buildSessionFactory(); return sessionFactory; } catch (Exception e) {
	  log.error("Initial SessionFactory creation failed." + e); throw new
	  IllegalStateException("Initial Session Factory creation failed."); } }
	 */

	public boolean create(Turista t, Usuario u) {
		log.debug("creating Turista instance");
		Session session = sessionFactory.openSession();
		boolean conf = false; 
		Transaction tx = null;
		try
		{
			tx = session.beginTransaction();
			
			Query query = session.createSQLQuery(
			        "insert into usuario(nombreUsuario,contrasena,URLfotografia,nombre,apellidoPaterno,apellidoMaterno,correo,tipousuario_idtipoUsuario)"
			        + " values(:nombreUsuario,:contrasena,:foto,:nombre,:apellidoPat,:apellidoMat,:correo,1)")
			        .setParameter("nombreUsuario", u.getNombreUsuario())
			        .setParameter("contrasena", u.getContrasena())
			        .setParameter("foto", u.getUrlfotografia())
			        .setParameter("nombre", u.getNombre())
			        .setParameter("apellidoPat", u.getApellidoPaterno() )
			        .setParameter("apellidoMat", u.getApellidoMaterno())
			        .setParameter("correo", u.getCorreo());
			
			Query query1 = session.createSQLQuery(
			        "insert into turista(idUsuario,fechaNacimiento,g_idGenero) "
			        + " values((select idUsuario from usuario order by idUsuario desc limit 1),:fechaNacimiento,:idGenero)")
			        .setParameter("fechaNacimiento", t.getFechaNacimiento())
			        .setParameter("idGenero", t.getGIdgenero());
			
//			Query query = session.createSQLQuery(
//			        "CALL sp_InsertTurista(:nombreUsuario, :contrasena, :foto, :nombre, :apellidoPat, :apellidoMat, :correo, :fechaNacimiento, :idGenero)")
//			        .setParameter("nombreUsuario", u.getNombreUsuario())
//			        .setParameter("contrasena", u.getContrasena())
//			        .setParameter("foto", u.getUrlfotografia())
//			        .setParameter("nombre", u.getNombre())
//			        .setParameter("apellidoPat", u.getApellidoPaterno())
//			        .setParameter("apellidoMat", u.getApellidoMaterno())
//			        .setParameter("correo", u.getCorreo())
//			        .setParameter("fechaNacimiento", t.getFechaNacimiento())
//			        .setParameter("idGenero", t.getGIdgenero() );
			
			if(query.executeUpdate() != 0 && query1.executeUpdate() != 0)
			{
				conf = true;
				tx.commit();
			}else
			{
				conf = false;
			}
		}catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		
		return conf;
	}
	
	public Turista read(Integer id) {
		log.debug("reading Turista instance");
		Turista u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (Turista)session.get(Turista.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}
	
	public List<Turista> readAll() {
		List<Turista> result = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
		result = session.createCriteria(Turista.class).list();
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return result;
	}

	public boolean update(Turista t, Usuario u) {
		log.debug("updating Turista instance");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		boolean conf = false; 
		try {
			tx = session.beginTransaction();
			session.update(t);
			session.update(u);
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

	public boolean delete(Turista t) {
		log.debug("deleting Turista instance");
		Usuario u = new Usuario();
		u.setIdUsuario(t.getIdUsuario());
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		boolean conf = false;
		try {
			tx = session.beginTransaction();
			session.delete(t);
			session.delete(u);
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


	public Turista findById(Integer id) {
		log.debug("getting Turista instance with id: " + id);
		Turista u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			 u = (Turista) session.get(Turista.class, id);
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
	
	/*public static void main(String args[])
	{
		Usuario u = new Usuario();
		Turista t = new Turista();
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaNacimiento = new Date();
		try
		{
			fechaNacimiento = formatoDelTexto.parse("1990-02-12");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		u.setApellidoMaterno("fdf");
		u.setApellidoPaterno("das");
		u.setContrasena("asdfasdf");
		u.setCorreo("corr@gg");
		u.setNombre("asdf");
		u.setNombreUsuario("Mayuscula");
		u.setTipoUsuarioIdtipoUsuario(1);
		u.setUrlfotografia(null);
		
		t.setFechaNacimiento(fechaNacimiento);
		t.setGIdgenero(2);
		
		TuristaDAO tDAO = new TuristaDAO();
		tDAO.create(t, u);
		
	}*/
}
