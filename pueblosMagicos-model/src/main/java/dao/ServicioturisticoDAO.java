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

import dto.Evaluacionservicioturistico;
import dto.Fotopueblomagico;
import dto.Servicioturistico;

@Repository
public class ServicioturisticoDAO {

	private static final Log log = LogFactory.getLog(ServicioturisticoDAO.class);

	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}

	public boolean create(Servicioturistico transientInstance) {
		log.debug("creating Servicioturistico instance");
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
	
	public Servicioturistico read(Integer id) {
		log.debug("reading Servicioturistico instance");
		Servicioturistico u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			u = (Servicioturistico)session.get(Servicioturistico.class, id);
			tx.commit();
			log.debug("persist successful");
		} catch (HibernateException e) {
			if (tx!=null) 
				tx.rollback();
		}
		session.close();
		return u;
	}
	
	public List<Servicioturistico> readAll() {
		List<Servicioturistico> result = null;
		Session session = sessionFactory.openSession();
		try
		{
		result = session.createCriteria(Servicioturistico.class).list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}

	public boolean update(Servicioturistico transientInstance) {
		log.debug("updating Servicioturistico instance");
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

	public boolean delete(Servicioturistico transientInstance) {
		log.debug("deleting Servicioturistico instance");
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


	public Servicioturistico findById(java.lang.Integer id) {
		log.debug("getting Servicioturistico instance with id: " + id);
		Servicioturistico u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			 u = (Servicioturistico) session.get(Servicioturistico.class, id);
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
	
	public List<Servicioturistico> findByIdEstadoByLimit(java.lang.Integer id, Integer first, Integer numReg) {
		log.debug("getting Servicioturistico instance with id: " + id);
		List<Servicioturistico> u = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try 
		{
			tx = session.getTransaction();
			 //u = (Servicioturistico) session.get(Servicioturistico.class, id).setFirstResult(first);
			Criteria crit;
			crit = session.createCriteria(Servicioturistico.class);
			crit.add( Restrictions.like("erIdEstadoRegistro", id));
			crit.setFirstResult(first);
			crit.setMaxResults(numReg);
			u = crit.list();		
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
	
	public List<Servicioturistico> getServicioturisticoByLimit(Integer first, Integer numRegistros) 
	{
		log.debug("finding Pueblomagico instance by example");
		List<Servicioturistico> results = null;
		Session session = sessionFactory.openSession();
		try {
			Criteria crit;
			crit = session.createCriteria(Servicioturistico.class);
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
	
	
	///////////////////////////ordenados
	
//	public List<Servicioturistico> getServiciosturisticosOrderByPromedioAscOrderByTipoByLimit(Integer first, Integer numRegistros, Integer tipo) 
//	{
//		log.debug("finding Pueblomagico instance by example");
//		List<Servicioturistico> results = null;
//		Session session = sessionFactory.openSession();
//		try {
//			Criteria crit;
//			crit = session.createCriteria(Servicioturistico.class);
//			crit.add(Restrictions.eq("tST_idtST", tipo));
//			crit.addOrder(Order.asc("promedio"));
//			crit.setFirstResult(first);
//			crit.setMaxResults(numRegistros);
//			results = crit.list();			
//		} catch (RuntimeException re) {
//			log.error("find by example failed", re);
//			re.printStackTrace();
//		}
//		session.close();
//		return results;
//	}
	
	public List<Servicioturistico> getServicioturisticoByPMOrderByPromedioAscByIdTipoByLimit(Integer id, Integer first, Integer numRegistros, Integer idTipo) 
	{
		List<Servicioturistico> result = null;
		Session session = sessionFactory.openSession();
		
		try
		{
			Query query = session.createSQLQuery("select st.idServicioTuristico,  "
					+ "st.e_idEstablecimiento as EIdEstablecimiento, "
					+ "st.nombre, "
					+ "st.aforo, "
					+ "st.tST_idtST as tstIdtSt, "
					+ "st.v_idUsuario as VIdUsuario, "
					+ "st.precioMinimo, "
					+ "st.precioMaximo, "
					+ "st.precioMedio, "
					+ "st.descripcion, "
					+ "st.sitioWeb, "
					+ "st.eR_idEstadoRegistro as erIdEstadoRegistro, "
					+ "st.telefono, "
					+ "st.extensionTelefono, "
					+ "st.promedio  "
					+ "from servicioturistico st, establecimiento e, asentamiento a, municipio m, pueblomagico pm "
					+ "where st.e_idEstablecimiento=e.idEstablecimiento "
					+ "and e.a_idAsentamiento=a.idAsentamiento "
					+ "and a.m_idMunicipio=m.idMunicipio "
					+ "and m.idMunicipio=pm.m_idMunicipio "
					+ "and st.tST_idtST=:idTipo "
					+ "and pm.idPuebloMagico=:idPM "
					+ "order by st.promedio asc "
					+ "limit :first, :numRegistros")
					.addScalar("idServicioTuristico", new IntegerType())
					.addScalar("EIdEstablecimiento", new IntegerType())
					.addScalar("nombre", new StringType())
					.addScalar("aforo", new IntegerType())
					.addScalar("tstIdtSt", new IntegerType())
					.addScalar("VIdUsuario", new IntegerType())
					.addScalar("precioMinimo", new DoubleType())
					.addScalar("precioMaximo", new DoubleType())
					.addScalar("precioMedio", new DoubleType())
					.addScalar("descripcion", new StringType())
					.addScalar("sitioWeb", new StringType())
					.addScalar("erIdEstadoRegistro", new IntegerType())
					.addScalar("telefono", new StringType())
					.addScalar("extensionTelefono", new StringType())
					.addScalar("promedio", new FloatType())
					.setResultTransformer(Transformers.aliasToBean(Servicioturistico.class))
			.setParameter("idPM", id)
			.setParameter("idTipo", idTipo)
			.setParameter("first", first)
			.setParameter("numRegistros", numRegistros);
			
			result = (List<Servicioturistico>) query.list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}
	
	public List<Servicioturistico> getServicioturisticoByPMOrderByPromedioDescByIdTipoByLimit(Integer id, Integer first, Integer numRegistros, Integer idTipo) 
	{
		List<Servicioturistico> result = null;
		Session session = sessionFactory.openSession();
		
		try
		{
			Query query = session.createSQLQuery("select st.idServicioTuristico,  "
					+ "st.e_idEstablecimiento as EIdEstablecimiento, "
					+ "st.nombre, "
					+ "st.aforo, "
					+ "st.tST_idtST as tstIdtSt, "
					+ "st.v_idUsuario as VIdUsuario, "
					+ "st.precioMinimo, "
					+ "st.precioMaximo, "
					+ "st.precioMedio, "
					+ "st.descripcion, "
					+ "st.sitioWeb, "
					+ "st.eR_idEstadoRegistro as erIdEstadoRegistro, "
					+ "st.telefono, "
					+ "st.extensionTelefono, "
					+ "st.promedio  "
					+ "from servicioturistico st, establecimiento e, asentamiento a, municipio m, pueblomagico pm "
					+ "where st.e_idEstablecimiento=e.idEstablecimiento "
					+ "and e.a_idAsentamiento=a.idAsentamiento "
					+ "and a.m_idMunicipio=m.idMunicipio "
					+ "and m.idMunicipio=pm.m_idMunicipio "
					+ "and st.tST_idtST=:idTipo "
					+ "and pm.idPuebloMagico=:idPM "
					+ "order by st.promedio desc "
					+ "limit :first, :numRegistros")
					.addScalar("idServicioTuristico", new IntegerType())
					.addScalar("EIdEstablecimiento", new IntegerType())
					.addScalar("nombre", new StringType())
					.addScalar("aforo", new IntegerType())
					.addScalar("tstIdtSt", new IntegerType())
					.addScalar("VIdUsuario", new IntegerType())
					.addScalar("precioMinimo", new DoubleType())
					.addScalar("precioMaximo", new DoubleType())
					.addScalar("precioMedio", new DoubleType())
					.addScalar("descripcion", new StringType())
					.addScalar("sitioWeb", new StringType())
					.addScalar("erIdEstadoRegistro", new IntegerType())
					.addScalar("telefono", new StringType())
					.addScalar("extensionTelefono", new StringType())
					.addScalar("promedio", new FloatType())
					.setResultTransformer(Transformers.aliasToBean(Servicioturistico.class))
			.setParameter("idPM", id)
			.setParameter("idTipo", idTipo)
			.setParameter("first", first)
			.setParameter("numRegistros", numRegistros);
			
			result = (List<Servicioturistico>) query.list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}
	
	
	public List<Servicioturistico> getServicioturisticoByPMOrderByPrecioAscByIdTipoByLimit(Integer id, Integer first, Integer numRegistros, Integer idTipo) 
	{
		List<Servicioturistico> result = null;
		Session session = sessionFactory.openSession();
		
		try
		{
			Query query = session.createSQLQuery("select st.idServicioTuristico,  "
					+ "st.e_idEstablecimiento as EIdEstablecimiento, "
					+ "st.nombre, "
					+ "st.aforo, "
					+ "st.tST_idtST as tstIdtSt, "
					+ "st.v_idUsuario as VIdUsuario, "
					+ "st.precioMinimo, "
					+ "st.precioMaximo, "
					+ "st.precioMedio, "
					+ "st.descripcion, "
					+ "st.sitioWeb, "
					+ "st.eR_idEstadoRegistro as erIdEstadoRegistro, "
					+ "st.telefono, "
					+ "st.extensionTelefono, "
					+ "st.promedio  "
					+ "from servicioturistico st, establecimiento e, asentamiento a, municipio m, pueblomagico pm "
					+ "where st.e_idEstablecimiento=e.idEstablecimiento "
					+ "and e.a_idAsentamiento=a.idAsentamiento "
					+ "and a.m_idMunicipio=m.idMunicipio "
					+ "and m.idMunicipio=pm.m_idMunicipio "
					+ "and st.tST_idtST=:idTipo "
					+ "and pm.idPuebloMagico=:idPM "
					+ "order by st.precioMedio asc "
					+ "limit :first, :numRegistros")
					.addScalar("idServicioTuristico", new IntegerType())
					.addScalar("EIdEstablecimiento", new IntegerType())
					.addScalar("nombre", new StringType())
					.addScalar("aforo", new IntegerType())
					.addScalar("tstIdtSt", new IntegerType())
					.addScalar("VIdUsuario", new IntegerType())
					.addScalar("precioMinimo", new DoubleType())
					.addScalar("precioMaximo", new DoubleType())
					.addScalar("precioMedio", new DoubleType())
					.addScalar("descripcion", new StringType())
					.addScalar("sitioWeb", new StringType())
					.addScalar("erIdEstadoRegistro", new IntegerType())
					.addScalar("telefono", new StringType())
					.addScalar("extensionTelefono", new StringType())
					.addScalar("promedio", new FloatType())
					.setResultTransformer(Transformers.aliasToBean(Servicioturistico.class))
			.setParameter("idPM", id)
			.setParameter("idTipo", idTipo)
			.setParameter("first", first)
			.setParameter("numRegistros", numRegistros);
			
			result = (List<Servicioturistico>) query.list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}
	
	public List<Servicioturistico> getServicioturisticoByPMOrderByPrecioDescByIdTipoByLimit(Integer id, Integer first, Integer numRegistros, Integer idTipo) 
	{
		List<Servicioturistico> result = null;
		Session session = sessionFactory.openSession();
		
		try
		{
			Query query = session.createSQLQuery("select st.idServicioTuristico,  "
					+ "st.e_idEstablecimiento as EIdEstablecimiento, "
					+ "st.nombre, "
					+ "st.aforo, "
					+ "st.tST_idtST as tstIdtSt, "
					+ "st.v_idUsuario as VIdUsuario, "
					+ "st.precioMinimo, "
					+ "st.precioMaximo, "
					+ "st.precioMedio, "
					+ "st.descripcion, "
					+ "st.sitioWeb, "
					+ "st.eR_idEstadoRegistro as erIdEstadoRegistro, "
					+ "st.telefono, "
					+ "st.extensionTelefono, "
					+ "st.promedio  "
					+ "from servicioturistico st, establecimiento e, asentamiento a, municipio m, pueblomagico pm "
					+ "where st.e_idEstablecimiento=e.idEstablecimiento "
					+ "and e.a_idAsentamiento=a.idAsentamiento "
					+ "and a.m_idMunicipio=m.idMunicipio "
					+ "and m.idMunicipio=pm.m_idMunicipio "
					+ "and st.tST_idtST=:idTipo "
					+ "and pm.idPuebloMagico=:idPM "
					+ "order by st.precioMedio desc "
					+ "limit :first, :numRegistros")
					.addScalar("idServicioTuristico", new IntegerType())
					.addScalar("EIdEstablecimiento", new IntegerType())
					.addScalar("nombre", new StringType())
					.addScalar("aforo", new IntegerType())
					.addScalar("tstIdtSt", new IntegerType())
					.addScalar("VIdUsuario", new IntegerType())
					.addScalar("precioMinimo", new DoubleType())
					.addScalar("precioMaximo", new DoubleType())
					.addScalar("precioMedio", new DoubleType())
					.addScalar("descripcion", new StringType())
					.addScalar("sitioWeb", new StringType())
					.addScalar("erIdEstadoRegistro", new IntegerType())
					.addScalar("telefono", new StringType())
					.addScalar("extensionTelefono", new StringType())
					.addScalar("promedio", new FloatType())
					.setResultTransformer(Transformers.aliasToBean(Servicioturistico.class))
			.setParameter("idPM", id)
			.setParameter("idTipo", idTipo)
			.setParameter("first", first)
			.setParameter("numRegistros", numRegistros);
			
			result = (List<Servicioturistico>) query.list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}
	
	
	
	
	
	///////////////////////////////////////////////////////////////////
	
	public List<Servicioturistico> getServicioturisticoBiPMByLimit(Integer id, Integer first, Integer numRegistros) 
	{
		List<Servicioturistico> result = null;
		Session session = sessionFactory.openSession();
		
		try
		{
			Query query = session.createSQLQuery("select st.idServicioTuristico, "
					+ "	st.e_idEstablecimiento as EIdEstablecimiento, "
					+ "	st.nombre, "
					+ "	st.aforo, "
					+ "	st.tST_idtST as tstIdtSt, "
					+ "	st.v_idUsuario as VIdUsuario, "
					+ "	st.precioMinimo, "
					+ "	st.precioMaximo, "
					+ "	st.precioMedio, "
					+ "	st.descripcion, "
					+ "	st.sitioWeb, "
					+ "	st.eR_idEstadoRegistro as erIdEstadoRegistro, "
					+ "	st.telefono, "
					+ "	st.extensionTelefono, "
					+ "	st.promedio  "
					+ "from servicioturistico st, establecimiento e, asentamiento a, municipio m, pueblomagico pm "
					+ "where st.e_idEstablecimiento=e.idEstablecimiento "
					+ "and e.a_idAsentamiento=a.idAsentamiento  "
					+ "and a.m_idMunicipio=m.idMunicipio "
					+ "and m.idMunicipio=pm.m_idMunicipio "
					+ "and pm.idPuebloMagico=:idPM limit :first, :numRegistros")
					.addScalar("idServicioTuristico", new IntegerType())
					.addScalar("EIdEstablecimiento", new IntegerType())
					.addScalar("nombre", new StringType())
					.addScalar("aforo", new IntegerType())
					.addScalar("tstIdtSt", new IntegerType())
					.addScalar("VIdUsuario", new IntegerType())
					.addScalar("precioMinimo", new DoubleType())
					.addScalar("precioMaximo", new DoubleType())
					.addScalar("precioMedio", new DoubleType())
					.addScalar("descripcion", new StringType())
					.addScalar("sitioWeb", new StringType())
					.addScalar("erIdEstadoRegistro", new IntegerType())
					.addScalar("telefono", new StringType())
					.addScalar("extensionTelefono", new StringType())
					.addScalar("promedio", new FloatType())
					.setResultTransformer(Transformers.aliasToBean(Servicioturistico.class))
			.setParameter("idPM", id)
			.setParameter("first", first)
			.setParameter("numRegistros", numRegistros);
			
			result = (List<Servicioturistico>) query.list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}
	
	public List<Servicioturistico> getServiciosturisticosByIdEstablecimiento(Integer id) 
	{
		List<Servicioturistico> result = null;
		Session session = sessionFactory.openSession();
		
		try
		{
			Query query = session.createSQLQuery("select st.idServicioTuristico, "
					+ "	st.e_idEstablecimiento as EIdEstablecimiento, "
					+ "	st.nombre, "
					+ "	st.aforo, "
					+ "	st.tST_idtST as tstIdtSt, "
					+ "	st.v_idUsuario as VIdUsuario, "
					+ "	st.precioMinimo, "
					+ "	st.precioMaximo, "
					+ "	st.precioMedio, "
					+ "	st.descripcion, "
					+ "	st.sitioWeb, "
					+ "	st.eR_idEstadoRegistro as erIdEstadoRegistro, "
					+ "	st.telefono, "
					+ "	st.extensionTelefono, "
					+ "	st.promedio  "
					+ "from servicioturistico st, establecimiento e "
					+ "where st.e_idEstablecimiento=e.idEstablecimiento "
					+ "and st.e_idEstablecimiento=:id  ")
			.addScalar("idServicioTuristico", new IntegerType())
			.addScalar("EIdEstablecimiento", new IntegerType())
			.addScalar("nombre", new StringType())
			.addScalar("aforo", new IntegerType())
			.addScalar("tstIdtSt", new IntegerType())
			.addScalar("VIdUsuario", new IntegerType())
			.addScalar("precioMinimo", new DoubleType())
			.addScalar("precioMaximo", new DoubleType())
			.addScalar("precioMedio", new DoubleType())
			.addScalar("descripcion", new StringType())
			.addScalar("sitioWeb", new StringType())
			.addScalar("erIdEstadoRegistro", new IntegerType())
			.addScalar("telefono", new StringType())
			.addScalar("extensionTelefono", new StringType())
			.addScalar("promedio", new FloatType())
			.setResultTransformer(Transformers.aliasToBean(Servicioturistico.class))
			.setParameter("id", id);
			
			result = (List<Servicioturistico>) query.list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}
	
	public List<Servicioturistico> findByIdPuebloMagico(Integer id) 
	{
		List<Servicioturistico> result = null;
		Session session = sessionFactory.openSession();
		
		try
		{
			Query query = session.createSQLQuery("select st.idServicioTuristico, "
					+ "	st.e_idEstablecimiento as EIdEstablecimiento, "
					+ "	st.nombre, "
					+ "	st.aforo, "
					+ "	st.tST_idtST as tstIdtSt, "
					+ "	st.v_idUsuario as VIdUsuario, "
					+ "	st.precioMinimo, "
					+ "	st.precioMaximo, "
					+ "	st.precioMedio, "
					+ "	st.descripcion, "
					+ "	st.sitioWeb, "
					+ "	st.eR_idEstadoRegistro as erIdEstadoRegistro, "
					+ "	st.telefono, "
					+ "	st.extensionTelefono, "
					+ "	st.promedio  "
					+ "from servicioturistico st, establecimiento e, asentamiento a, municipio m, pueblomagico pm "
					+ "where st.e_idEstablecimiento=e.idEstablecimiento "
					+ "and e.a_idAsentamiento=a.idAsentamiento  "
					+ "and a.m_idMunicipio=m.idMunicipio "
					+ "and m.idMunicipio=pm.m_idMunicipio "
					+ "and pm.idPuebloMagico=:idPM")
					.addScalar("idServicioTuristico", new IntegerType())
					.addScalar("EIdEstablecimiento", new IntegerType())
					.addScalar("nombre", new StringType())
					.addScalar("aforo", new IntegerType())
					.addScalar("tstIdtSt", new IntegerType())
					.addScalar("VIdUsuario", new IntegerType())
					.addScalar("precioMinimo", new DoubleType())
					.addScalar("precioMaximo", new DoubleType())
					.addScalar("precioMedio", new DoubleType())
					.addScalar("descripcion", new StringType())
					.addScalar("sitioWeb", new StringType())
					.addScalar("erIdEstadoRegistro", new IntegerType())
					.addScalar("telefono", new StringType())
					.addScalar("extensionTelefono", new StringType())
					.addScalar("promedio", new FloatType())
					.setResultTransformer(Transformers.aliasToBean(Servicioturistico.class))
			.setParameter("idPM", id);
			
			result = (List<Servicioturistico>) query.list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return result;
	}
	
	public List<Servicioturistico> findByIdPST(Integer id) 
	{
		List<Servicioturistico> result = null;
		Session session = sessionFactory.openSession();
		
		try
		{
			Query query = session.createSQLQuery("select st.idServicioTuristico, "
					+ "	st.e_idEstablecimiento as EIdEstablecimiento,"
					+ "	st.nombre,"
					+ "	st.aforo,"
					+ "	st.tST_idtST as tstIdtSt,"
					+ "	st.v_idUsuario as VIdUsuario,"
					+ "	st.precioMinimo,"
					+ "	st.precioMaximo,"
					+ "	st.precioMedio,"
					+ "	st.descripcion,"
					+ "	st.sitioWeb,"
					+ "	st.eR_idEstadoRegistro as erIdEstadoRegistro,"
					+ "	st.telefono,"
					+ "	st.extensionTelefono,"
					+ "	st.promedio "
					+ "from servicioturistico st, establecimiento e, pst p "
					+ "where st.e_idEstablecimiento=e.idEstablecimiento "
					+ "and e.PST_idUsuario=p.idUsuario  "
					+ "and p.idUsuario=:id")
					.addScalar("idServicioTuristico", new IntegerType())
					.addScalar("EIdEstablecimiento", new IntegerType())
					.addScalar("nombre", new StringType())
					.addScalar("aforo", new IntegerType())
					.addScalar("tstIdtSt", new IntegerType())
					.addScalar("VIdUsuario", new IntegerType())
					.addScalar("precioMinimo", new DoubleType())
					.addScalar("precioMaximo", new DoubleType())
					.addScalar("precioMedio", new DoubleType())
					.addScalar("descripcion", new StringType())
					.addScalar("sitioWeb", new StringType())
					.addScalar("erIdEstadoRegistro", new IntegerType())
					.addScalar("telefono", new StringType())
					.addScalar("extensionTelefono", new StringType())
					.addScalar("promedio", new FloatType())
					.setResultTransformer(Transformers.aliasToBean(Servicioturistico.class))
			.setParameter("id", id);
			result = (List<Servicioturistico>) query.list();
		}catch(Exception e)
		{
			e.printStackTrace();
		}	
		
		session.close();
		return result;
	}
	
	public String getTipoPrincipal(Integer idServicioTuristico)
	{
		String nombre = null;
		Session session = sessionFactory.openSession();
		try
		{
			Query query = session.createSQLQuery("select pm.nombre "
					+ "from servicioturistico st, establecimiento e, asentamiento a, municipio m, pueblomagico pm "
					+ "where st.e_idEstablecimiento=e.idEstablecimiento "
					+ "and e.a_idAsentamiento=a.idAsentamiento  "
					+ "and a.m_idMunicipio=pm.m_idMunicipio "
					+ "and st.idServicioTuristico=:id")
					.setParameter("id", idServicioTuristico); 
			nombre = (String) query.uniqueResult();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return nombre;
	}
	
	public String getNombrePuebloMagico(Integer idServicioTuristico)
	{
		String nombre = null;;
		Session session = sessionFactory.openSession();
		try
		{
			Query query = session.createSQLQuery("select distinct pm.nombre "
					+ "from servicioturistico st, establecimiento e, asentamiento a, municipio m, pueblomagico pm "
					+ "where st.e_idEstablecimiento=e.idEstablecimiento "
					+ "and e.a_idAsentamiento=a.idAsentamiento "
					+ "and a.m_idMunicipio=pm.m_idMunicipio "
					+ "and st.idServicioTuristico=:id")
					.setParameter("id", idServicioTuristico); 
			nombre = (String) query.uniqueResult();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		return nombre;
	}
	
	public void updatePromedio( int idServicioTuristico )
	{
		log.debug("finding Pueblomagico instance by example");
		List<Float> promedios = null;
		Session session = sessionFactory.openSession();
		try 
		{
			
			Query query = session.createSQLQuery("select promedio  "
					+ "from evaluacionservicioturistico "
					+ "where sT_idServicioTuristico=:id")
					.setParameter("id", idServicioTuristico); 
			promedios = (List<Float>) query.list();
			
//			Criteria crit;
//			crit = session.createCriteria(Evaluacionservicioturistico.class)
//					.add( Restrictions.like("sTIdServicioTuristico", idServicioTuristico) )
//					.setProjection(Projections.property("promedio"))
//			;
//			promedios = crit.list();		
			
			float suma = 0;
			for(Float c : promedios)
			{
				suma = suma + c;
			}
			float promedio = suma / promedios.size();
			
			Servicioturistico s = new Servicioturistico();
			s = this.read(idServicioTuristico);
			s.setPromedio(promedio);
			this.update(s);
			
			
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			re.printStackTrace();
		}
		session.close();
	}
}
