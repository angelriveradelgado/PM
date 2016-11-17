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

import dto.Evaluacionaventura;
import dto.Evaluacionservicioturistico;
import dto.Pst;
import dto.Pueblomagico;
import dto.Servicioturistico;
import dto.Servicioturisticoalojamiento;
import dto.Servicioturisticoaventura;
import dto.Usuario;

@Repository
public class ServicioturisticoaventuraDAO {

	private static final Log log = LogFactory.getLog(ServicioturisticoaventuraDAO.class);

	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	public boolean create(Servicioturistico st, Servicioturisticoaventura sta) {
		log.debug("creating PST instance");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		boolean conf = false; 
		
		try
		{
			tx = session.beginTransaction();
			Query query = session.createSQLQuery(
			        "insert into servicioturistico(e_idEstablecimiento, nombre, aforo, tST_idtST, "
			        + "precioMinimo, precioMaximo, precioMedio, descripcion, sitioWEB, "
			        + "eR_estadoRegistro, telefono, extensionTelefono) values(:idEstablecimiento, :nombreServ, :aforo, :tipoServ, "
			        + ":precioMinimo, :precioMaximo, :precioMedio, :descripcion, :sitioWEB, "
			        + ":edoRegistro, :telefono, :extensionTelefono)")
			        .setParameter("idEstablecimiento", st.getEIdEstablecimiento())
			        .setParameter("nombreServ", st.getNombre())
			        .setParameter("aforo", st.getAforo())
			        .setParameter("tipoServ", st.getIdServicioTuristico())
			        .setParameter("precioMinimo", st.getPrecioMinimo() )
			        .setParameter("precioMaximo", st.getPrecioMaximo())
			        .setParameter("precioMedio", st.getPrecioMedio())
			        .setParameter("descripcion", st.getDescripcion())
			        .setParameter("sitioWEB", st.getSitioWeb())
			        .setParameter("edoRegistro", st.getErIdEstadoRegistro())
			        .setParameter("telefono", st.getTelefono() )
			        .setParameter("extensionTelefono", st.getExtensionTelefono());
			
			Query query1 = session.createSQLQuery("insert into servicioturisticoaventura values((select idServicioTuristico from "
					+ " servicioturistico order by idServicioTuristico desc limit 1))");
			
			
			
			if(query.executeUpdate() != 0 && query1.executeUpdate() != 0)
			{
				tx.commit();
				conf = true;
			}
			conf = false;
		}catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return conf;
	}
	
	public Servicioturisticoaventura read(Integer id) {
		log.debug("reading Servicioturisticoaventura instance");
		Servicioturisticoaventura u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (Servicioturisticoaventura)session.get(Servicioturisticoaventura.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}
	
	public List<Servicioturisticoaventura> readAll() {
		List<Servicioturisticoaventura> result = null;
		Session session = sessionFactory.openSession();
		try
		{
			result = session.createCriteria(Servicioturisticoaventura.class).list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}

	public boolean update(Servicioturisticoaventura transientInstance) {
		log.debug("updating Servicioturisticoaventura instance");
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

	public boolean delete(Servicioturisticoaventura transientInstance) {
		log.debug("deleting Servicioturisticoaventura instance");
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


	public Servicioturisticoaventura findById(Integer id) {
		log.debug("getting Servicioturisticoaventura instance with id: " + id);
		Servicioturisticoaventura u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			 u = (Servicioturisticoaventura) session.get(Servicioturisticoaventura.class, id);
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
	
	public List<Servicioturisticoaventura> getServicioturisticoaventuraByLimit(Integer first, Integer numRegistros) 
	{
		log.debug("finding Pueblomagico instance by example");
		List<Servicioturisticoaventura> results = null;
		Session session = sessionFactory.openSession();
		try {
			Criteria crit;
			crit = session.createCriteria(Servicioturisticoaventura.class);
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

}
