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

import dto.Servicioturistico;
import dto.Servicioturisticoalojamiento;

@Repository
public class ServicioturisticoalojamientoDAO {

	private static final Log log = LogFactory.getLog(ServicioturisticoalojamientoDAO.class);

	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	public boolean create(Servicioturistico st, Servicioturisticoalojamiento sta) {
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
			        + "eR_idEstadoRegistro, telefono, extensionTelefono) values(:idEstablecimiento, :nombreServ, :aforo, :tipoServ, "
			        + ":precioMinimo, :precioMaximo, :precioMedio, :descripcion, :sitioWEB, "
			        + ":edoRegistro, :telefono, :extensionTelefono)")
			        .setParameter("idEstablecimiento", st.getEIdEstablecimiento())
			        .setParameter("nombreServ", st.getNombre())
			        .setParameter("aforo", st.getAforo())
			        .setParameter("tipoServ", st.getTstIdtSt())
			        .setParameter("precioMinimo", st.getPrecioMinimo() )
			        .setParameter("precioMaximo", st.getPrecioMaximo())
			        .setParameter("precioMedio", st.getPrecioMedio())
			        .setParameter("descripcion", st.getDescripcion())
			        .setParameter("sitioWEB", st.getSitioWeb())
			        .setParameter("edoRegistro", st.getErIdEstadoRegistro())
			        .setParameter("telefono", st.getTelefono() )
			        .setParameter("extensionTelefono", st.getExtensionTelefono());
			
			Query query1 = session.createSQLQuery(
			        "insert into servicioturisticoalojamiento (sT_idServicio, tO_idtipoOperacion, tA_idtipoAlojamiento, tSA_idtipoServicioAlojamiento) values((select idServicioTuristico "
			        + " from servicioturistico order by idServicioTuristico desc limit 1), "
			        + ":tipoOperacion,:tipoAlojamiento,:tipoServicioAlojamiento)")
			        .setParameter("tipoOperacion", sta.getToIdtipoOperacion())
			        .setParameter("tipoAlojamiento", sta.getTaIdtipoAlojamiento())
			        .setParameter("tipoServicioAlojamiento", sta.getTsaIdtipoServicioAlojamiento());
			
			
			
			if(query.executeUpdate() != 0 && query1.executeUpdate() != 0)
			{
				tx.commit();
				conf = true;
			}
		}catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return conf;
	}
	
	public Servicioturisticoalojamiento read(Integer id) {
		log.debug("reading Servicioturisticoalojamiento instance");
		Servicioturisticoalojamiento u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (Servicioturisticoalojamiento)session.get(Servicioturisticoalojamiento.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}
	
	public List<Servicioturisticoalojamiento> readAll() {
		List<Servicioturisticoalojamiento> result = null;
		Session session = sessionFactory.openSession();
		try
		{
			result = session.createCriteria(Servicioturisticoalojamiento.class).list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}

	public boolean update(Servicioturisticoalojamiento transientInstance) {
		log.debug("updating Servicioturisticoalojamiento instance");
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

	public boolean delete(Servicioturisticoalojamiento transientInstance) {
		log.debug("deleting Servicioturisticoalojamiento instance");
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


	public Servicioturisticoalojamiento findById(Integer id) {
		log.debug("getting Servicioturisticoalojamiento instance with id: " + id);
		Servicioturisticoalojamiento u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			 u = (Servicioturisticoalojamiento) session.get(Servicioturisticoalojamiento.class, id);
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
	
	public List<Servicioturisticoalojamiento> getServicioturisticoalojamientoByLimit(Integer first, Integer numRegistros) 
	{
		log.debug("finding Pueblomagico instance by example");
		List<Servicioturisticoalojamiento> results = null;
		Session session = sessionFactory.openSession();
		try {
			Criteria crit;
			crit = session.createCriteria(Servicioturisticoalojamiento.class);
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
