package controller;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.ws.rs.FormParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dao.EvaluacionalojamientoDAO;
import dao.EvaluacionaventuraDAO;
import dao.EvaluacionservicioturisticoDAO;
import dao.FormapagoDAO;
import dao.FotoservicioturisticoDAO;
import dao.RegistrovisitaDAO;
import dao.ServicioturisticoDAO;
import dao.ServicioturisticoalojamientoDAO;
import dao.ServicioturisticoaventuraDAO;
import dao.StHasFormapagoDAO;
import dao.TiposervicioturisticoDAO;
import dto.Evaluacionalojamiento;
import dto.Evaluacionaventura;
import dto.Evaluacionservicioturistico;
import dto.FotoServicioTuristicoSimple;
import dto.Fotoservicioturistico;
import dto.Registrovisita;
import dto.Servicioturistico;
import dto.Servicioturisticoalojamiento;
import dto.Servicioturisticoaventura;

@RestController
@Component
public class ServicioTuristicoController
{

	@Autowired
	private ServicioturisticoDAO servicioturisticoDAO;
	@Autowired
	private ServicioturisticoaventuraDAO servicioturisticoaventuraDAO;
	@Autowired
	private ServicioturisticoalojamientoDAO servicioturisticoalojamientoDAO;

	@Autowired
	private FotoservicioturisticoDAO fotoservicioTuristicoDAO;

	@Autowired
	EvaluacionservicioturisticoDAO evaluacionservicioturisticoDAO;
	@Autowired
	EvaluacionaventuraDAO evaluacionaventuraDAO;
	@Autowired
	EvaluacionalojamientoDAO evaluacionalojamientoDAO;
	@Autowired
	TiposervicioturisticoDAO tiposervicioturisticoDAO;
	@Autowired
	StHasFormapagoDAO stHasFormapagoDAO;
	@Autowired
	FormapagoDAO formapagoDAO;
	
	@Autowired
	private RegistrovisitaDAO registrovisitaDAO;


	// WS que devuelve un servicio turistico por su id
	@RequestMapping(value = "/servicioTuristico/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getServicioTuristico( @PathVariable Integer id )
	{
		ResponseEntity<?> result = null;
		Servicioturistico st = null;
		try
		{
			st = servicioturisticoDAO.findById(id);
			result = new ResponseEntity<Servicioturistico>(st, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value = "/servicioTuristico/estadoRegistro/{idEstadoRegistro}/limit/{first}/{numReg}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> findByIdEstadoRegistroByLimit( @PathVariable Integer idEstadoRegistro,
			@PathVariable Integer first, @PathVariable Integer numReg)
	{
		ResponseEntity<?> result = null;
		List<Servicioturistico> st = null;
		try
		{
			st = servicioturisticoDAO.findByIdEstadoByLimit(idEstadoRegistro, first, numReg);
			result = new ResponseEntity<List<Servicioturistico>>(st, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	// WS que devuelve un servicio turistico de aventura por su id 
	//primero en el lado del cliente se pide el servicios turistico y con su id se pide el st de aventura
	@RequestMapping(value = "/servicioTuristicoAventura/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getServicioTuristicoAventura( @PathVariable Integer id )
	{
		ResponseEntity<?> result = null;
		Servicioturisticoaventura sa = null;
		try
		{
			sa = servicioturisticoaventuraDAO.findById(id);
			result = new ResponseEntity<Servicioturisticoaventura>(sa, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	// WS que devuelve un servicio turistico de alojamiento por su id 
	//primero en el lado del cliente se pide el servicios turistico y con su id se pide el st de alojamiento
	@RequestMapping(value = "/servicioTuristicoAlojamiento/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getServicioTuristicoAlojamiento( @PathVariable Integer id )
	{
		ResponseEntity<?> result = null;
		Servicioturisticoalojamiento sa = null;
		try
		{
			sa = servicioturisticoalojamientoDAO.findById(id);
			result = new ResponseEntity<Servicioturisticoalojamiento>(sa, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}	

	// WS que devuelve una lista con los servicios turisticos en un rango de
	// resultados (limit)
	@RequestMapping(value = "/servicioTuristico/{first}/{numRegistros}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getSomeServicioTuristico( @PathVariable Integer first, @PathVariable Integer numRegistros )
	{
		ResponseEntity<?> result = null;
		List<Servicioturistico> a = null;
		try
		{

			a = servicioturisticoDAO.getServicioturisticoByLimit(first, numRegistros);
			result = new ResponseEntity<List<Servicioturistico>>(a, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

	// WS que devuelve una lista de las imagenes de un ST. Recibe el id del
	// servicio
	@RequestMapping(value = "/servicioTuristico/{id}/fotos", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getAllFotos( @PathVariable Integer id )
	{
		List<FotoServicioTuristicoSimple> fotos = null;
		ResponseEntity<?> result = null;
		try
		{
			fotos = fotoservicioTuristicoDAO.readAllSimpleByIdServicioTuristico(id);
			result = new ResponseEntity<List<FotoServicioTuristicoSimple>>(fotos, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

	// WS que devuelve una imagen de un ST. Recibe el id de la imagen
	@RequestMapping(value = "/servicioTuristico/foto/{id}", method = RequestMethod.GET, produces = "image/jpg")
	public ResponseEntity<?> getAtractivoTuristicoFoto( @PathVariable Integer id )
	{
		ResponseEntity<?> result = null;

		Fotoservicioturistico foto = new Fotoservicioturistico();
		foto = fotoservicioTuristicoDAO.read(id);
		String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();

		try
		{
			InputStream is = new BufferedInputStream(new FileInputStream(path + foto.getUrlfoto()));

			// Prepare buffered image.
			BufferedImage img = ImageIO.read(is);

			// Create a byte array output stream.
			ByteArrayOutputStream bao = new ByteArrayOutputStream();

			// Write to output stream
			ImageIO.write(img, "jpg", bao);

			URL location = PuebloMagicoController.class.getProtectionDomain().getCodeSource().getLocation();
			System.out.println(location.getFile());

			result = new ResponseEntity<byte[]>(bao.toByteArray(), HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	

	// WS que devuelve una lista con todos los ST de un PM. Recibe el id del PM
	@RequestMapping(value = "/servicioTuristico/puebloMagico/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getAllServiciosTuristicosByPM( @PathVariable Integer id )
	{
		List<Servicioturistico> sts = null;
		ResponseEntity<?> result = null;
		try
		{
			sts = servicioturisticoDAO.findByIdPuebloMagico(id);
			result = new ResponseEntity<List<Servicioturistico>>(sts, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	// WS que devuelve una lista con todos los ST de un Establecimiento. Recibe el id del Establecimiento
	@RequestMapping(value = "/servicioTuristico/Establecimiento/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getAllServiciosTuristicosByEstablecimiento( @PathVariable Integer id )
	{
		List<Servicioturistico> sts = null;
		ResponseEntity<?> result = null;
		try
		{
			sts = servicioturisticoDAO.getServiciosturisticosByIdEstablecimiento(id);
			result = new ResponseEntity<List<Servicioturistico>>(sts, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	// WS que devuelve una lista con todos los ST de un PM. Recibe el id del PM
	@RequestMapping(value = "/servicioTuristico/puebloMagico/{id}/limit/{first}/{numRegistros}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getAllServiciosTuristicosByPMByLimit( @PathVariable Integer id,
			@PathVariable Integer first,
			@PathVariable Integer numRegistros )
	{
		List<Servicioturistico> sts = null;
		ResponseEntity<?> result = null;
		try
		{
			sts = servicioturisticoDAO.getServicioturisticoBiPMByLimit(id, first, numRegistros);
			result = new ResponseEntity<List<Servicioturistico>>(sts, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

	/////////////////////orderby
	// WS que devuelve una lista con todos los ST de un PM. Recibe el id del PM
	@RequestMapping(value = "/servicioTuristicoOrderByPromedioAsc/puebloMagico/{id}/idTipo/{idTipo}/limit/{first}/{numRegistros}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getServicioturisticoByPMOrderByPromedioAscByTipoByLimit( @PathVariable Integer id,
			@PathVariable Integer first,
			@PathVariable Integer numRegistros, 
			@PathVariable Integer idTipo )
	{
		List<Servicioturistico> sts = null;
		ResponseEntity<?> result = null;
		try
		{
			sts = servicioturisticoDAO.getServicioturisticoByPMOrderByPromedioAscByIdTipoByLimit(id, first, numRegistros, idTipo);
			result = new ResponseEntity<List<Servicioturistico>>(sts, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	// WS que devuelve una lista con todos los ST de un PM. Recibe el id del PM
	//ordenado por el promedio de los servicios
	//tipo de servicio
	@RequestMapping(value =  "/servicioTuristicoOrderByPromedioDesc/puebloMagico/{id}/idTipo/{idTipo}/limit/{first}/{numRegistros}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getServicioturisticoByPMOrderByPromedioDescByIdTipoByLimit( @PathVariable Integer id,
			@PathVariable Integer first,
			@PathVariable Integer numRegistros, 
			@PathVariable Integer idTipo )
	{
		List<Servicioturistico> sts = null;
		ResponseEntity<?> result = null;
		try
		{
			sts = servicioturisticoDAO.getServicioturisticoByPMOrderByPromedioDescByIdTipoByLimit(id, first, numRegistros, idTipo);
			result = new ResponseEntity<List<Servicioturistico>>(sts, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	// WS que devuelve una lista con todos los ST de un PM. Recibe el id del PM
	@RequestMapping(value =  "/servicioTuristicoOrderByPrecioAsc/puebloMagico/{id}/idTipo/{idTipo}/limit/{first}/{numRegistros}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getServicioturisticoByPMOrderByPrecioAscByIdTipoByLimit( @PathVariable Integer id,
			@PathVariable Integer first,
			@PathVariable Integer numRegistros, 
			@PathVariable Integer idTipo )
	{
		List<Servicioturistico> sts = null;
		ResponseEntity<?> result = null;
		try
		{
			sts = servicioturisticoDAO.getServicioturisticoByPMOrderByPrecioAscByIdTipoByLimit(id, first, numRegistros, idTipo);
			result = new ResponseEntity<List<Servicioturistico>>(sts, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	// WS que devuelve una lista con todos los ST de un PM. Recibe el id del PM
	@RequestMapping(value =  "/servicioTuristicoOrderByPrecioDesc/puebloMagico/{id}/idTipo/{idTipo}/limit/{first}/{numRegistros}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getServicioturisticoByPMOrderByPrecioDescByIdTipoByLimit( @PathVariable Integer id,
			@PathVariable Integer first,
			@PathVariable Integer numRegistros, 
			@PathVariable Integer idTipo )
	{
		List<Servicioturistico> sts = null;
		ResponseEntity<?> result = null;
		try
		{
			sts = servicioturisticoDAO.getServicioturisticoByPMOrderByPrecioDescByIdTipoByLimit(id, first, numRegistros, idTipo);
			result = new ResponseEntity<List<Servicioturistico>>(sts, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

	
	// WS que devuelve la evaluacion de un servicio turistico
	@RequestMapping(value = "/servicioTuristico/{id}/evaluacion", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getEvaluacionesByIdServicioTuristico( @PathVariable Integer id )
	{
		List<Evaluacionservicioturistico> c = null;
		ResponseEntity<?> result = null;
		try
		{
			c = evaluacionservicioturisticoDAO.findByIdServicioTuristico(id);
			result = new ResponseEntity<List<Evaluacionservicioturistico>>(c, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

	// WS que devuelve un intervalo de las evaluaciones de un servicio turistico
	@RequestMapping(value = "/servicioTuristico/{id}/evaluacion/first/{first}/numReg/{numReg}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getEvaluacionesByIdServicioTuristicoAndLimit( @PathVariable Integer id,
			@PathVariable Integer first, @PathVariable Integer numReg )
	{
		List<Evaluacionservicioturistico> c = null;
		ResponseEntity<?> result = null;
		try
		{
			c = evaluacionservicioturisticoDAO.getEvaluacionservicioturisticoByIdServicioAndByLimit(id, first, numReg);
			result = new ResponseEntity<List<Evaluacionservicioturistico>>(c, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

	// WS que devuelve la evaluacion derivada de un atractivo turistico recibe
	// el id de la evaluacionservicioturistico
	@RequestMapping(value = "/servicioTuristico/evaluacionAventura/{idEvaluacion}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getEvaluacionAventura( @PathVariable Integer idEvaluacion )
	{
		Evaluacionaventura c = null;
		ResponseEntity<?> result = null;
		try
		{
			c = evaluacionaventuraDAO.findById(idEvaluacion);
			result = new ResponseEntity<Evaluacionaventura>(c, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

	// WS que devuelve la evaluacion derivada de un atractivo turistico recibe
	// el id de la evaluacionservicioturistico
	@RequestMapping(value = "/servicioTuristico/evaluacionAlojamiento/{idEvaluacion}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getEvaluacionAlojamiento( @PathVariable Integer idEvaluacion )
	{
		Evaluacionalojamiento c = null;
		ResponseEntity<?> result = null;
		try
		{
			c = evaluacionalojamientoDAO.findById(idEvaluacion);
			result = new ResponseEntity<Evaluacionalojamiento>(c, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

	@RequestMapping(value = "/servicioTuristico", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> insertServicioTuristico( @FormParam("idEstablecimiento") Integer idEstablecimiento,
			@FormParam("nombre") String nombre, @FormParam("aforo") Integer aforo, @FormParam("idTST") Integer idTST,
			@FormParam("precioMinimo") Double precioMinimo, @FormParam("precioMaximo") Double precioMaximo,
			@FormParam("precioMedio") Double precioMedio, @FormParam("descripcion") String descripcion,
			@FormParam("sitioWeb") String sitioWeb,
			@FormParam("telefono") String telefono, @FormParam("extTelefono") String extTelefono)
	{
		ResponseEntity<?> result = null;
		
		if( idEstablecimiento == null )
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		
		Servicioturistico st = new Servicioturistico();
		Boolean respuesta = false;

		try
		{
			st.setEIdEstablecimiento(idEstablecimiento);
			st.setNombre(nombre);
			st.setAforo(aforo);
			st.setTstIdtSt(idTST);
			st.setPrecioMinimo(precioMinimo);
			st.setPrecioMaximo(precioMaximo);
			st.setPrecioMedio(precioMedio);
			st.setDescripcion(descripcion);
			st.setSitioWeb(sitioWeb);
			st.setErIdEstadoRegistro(2);
			st.setTelefono(telefono);
			st.setExtensionTelefono(extTelefono);
			st.setPromedio((float)0);

			respuesta = servicioturisticoDAO.create(st);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return result;
	}
	
	
	// WS que devuelve los servicios por el id del pst al que está asociado
	@RequestMapping(value = "/servicioTuristico/idpst/{idpst}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getServiciosByidPST( @PathVariable Integer idpst )
	{
		ResponseEntity<?> result = null;
		List<Servicioturistico> s = null;
		try
		{
			s = servicioturisticoDAO.findByIdPST(idpst);
			result = new ResponseEntity<List<Servicioturistico>>(s, HttpStatus.OK);
		} catch (Exception ex)
		{
			ex.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	
	@RequestMapping(value = "/servicioTuristicoAventura", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> insertServicioTuristicoAventura( @FormParam("idEstablecimiento") Integer idEstablecimiento,
			@FormParam("nombre") String nombre, @FormParam("aforo") Integer aforo, @FormParam("idTST") Integer idTST,
			@FormParam("precioMinimo") Double precioMinimo, @FormParam("precioMaximo") Double precioMaximo,
			@FormParam("precioMedio") Double precioMedio, @FormParam("descripcion") String descripcion,
			@FormParam("sitioWeb") String sitioWeb,
			@FormParam("telefono") String telefono, @FormParam("extTelefono") String extTelefono )
	{
		ResponseEntity<?> result = null;
		
		if( idEstablecimiento == null )
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		
		Servicioturistico st = new Servicioturistico();
		Servicioturisticoaventura sa = new Servicioturisticoaventura();
		Boolean respuesta = false;

		try
		{
			st.setEIdEstablecimiento(idEstablecimiento);
			st.setNombre(nombre);
			st.setAforo(aforo);
			st.setTstIdtSt(idTST);
			st.setPrecioMinimo(precioMinimo);
			st.setPrecioMaximo(precioMaximo);
			st.setPrecioMedio(precioMedio);
			st.setDescripcion(descripcion);
			st.setSitioWeb(sitioWeb);
			st.setErIdEstadoRegistro(2);
			st.setTelefono(telefono);
			st.setExtensionTelefono(extTelefono);
			

			respuesta = servicioturisticoaventuraDAO.create(st, sa);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return result;
	}
	
	@RequestMapping(value = "/servicioTuristicoEdit", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> updateServicioTuristico( @FormParam("idServicioTuristico") Integer idServicioTuristico,
			@FormParam("idEstablecimiento") Integer idEstablecimiento,
			@FormParam("nombre") String nombre, @FormParam("aforo") Integer aforo, @FormParam("idTST") Integer idTST,
			@FormParam("precioMinimo") Double precioMinimo, @FormParam("precioMaximo") Double precioMaximo,
			@FormParam("precioMedio") Double precioMedio, @FormParam("descripcion") String descripcion,
			@FormParam("sitioWeb") String sitioWeb, //no se pide el estado de registro porque no lo puede editar el pst
			@FormParam("telefono") String telefono, @FormParam("extensionTelefono") String extensionTelefono )
	{
		
		ResponseEntity<?> result = null;
		
		if( idEstablecimiento == null || idTST == null)
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		
		Servicioturistico st = new Servicioturistico();
		Servicioturisticoaventura sa = new Servicioturisticoaventura();
		Boolean respuesta = false;

		try
		{
			//obetener st
			System.out.println("idServicioTuristico" + idServicioTuristico);
			System.out.println("idEstablecimiento" + idServicioTuristico);
			st = servicioturisticoDAO.read(idServicioTuristico);
			//editar campos permitidos
			//st.setEIdEstablecimiento(idEstablecimiento);
			st.setNombre(nombre);
			st.setAforo(aforo);
			st.setTstIdtSt(idTST);
			System.out.println("precioMinimo" + precioMinimo);
			st.setPrecioMinimo(precioMinimo);
			st.setPrecioMaximo(precioMaximo);
			st.setPrecioMedio(precioMedio);
			st.setDescripcion(descripcion);
			st.setSitioWeb(sitioWeb);
			st.setTelefono(telefono);
			st.setExtensionTelefono(extensionTelefono);			

			respuesta = servicioturisticoDAO.update(st);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return result;
	}	
	
	@RequestMapping(value = "/servicioTuristicoAventuraEdit", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> updateServicioTuristicoAventura( @FormParam("idServicioTuristico") Integer idServicioTuristico,
			@FormParam("idEstablecimiento") Integer idEstablecimiento,
			@FormParam("nombre") String nombre, @FormParam("aforo") Integer aforo, @FormParam("idTST") Integer idTST,
			@FormParam("precioMinimo") Double precioMinimo, @FormParam("precioMaximo") Double precioMaximo,
			@FormParam("precioMedio") Double precioMedio, @FormParam("descripcion") String descripcion,
			@FormParam("sitioWeb") String sitioWeb, //no se pide el estado de registro porque no lo puede editar el pst
			@FormParam("telefono") String telefono, @FormParam("extTelefono") String extTelefono )
	{
		ResponseEntity<?> result = null;
		
		if( idEstablecimiento == null || idTST == null)
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		Servicioturistico st = new Servicioturistico();
		Servicioturisticoaventura sa = new Servicioturisticoaventura();
		Boolean respuesta = false;

		try
		{
			//obetener st
			st = servicioturisticoDAO.findById(idServicioTuristico);
			//editar campos permitidos
			st.setEIdEstablecimiento(idEstablecimiento);
			st.setNombre(nombre);
			st.setAforo(aforo);
			st.setTstIdtSt(idTST);
			st.setPrecioMinimo(precioMinimo);
			st.setPrecioMaximo(precioMaximo);
			st.setPrecioMedio(precioMedio);
			st.setDescripcion(descripcion);
			st.setSitioWeb(sitioWeb);
			st.setTelefono(telefono);
			st.setExtensionTelefono(extTelefono);			

			respuesta = servicioturisticoDAO.update(st);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return result;
	}	
	
	@RequestMapping(value = "/servicioTuristicoAlojamiento", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> insertServicioTuristicoAlojamiento(@FormParam("idEstablecimiento") Integer idEstablecimiento,
			@FormParam("nombre") String nombre, @FormParam("aforo") Integer aforo, @FormParam("idTST") Integer idTST,
			@FormParam("precioMinimo") Double precioMinimo, @FormParam("precioMaximo") double precioMaximo,
			@FormParam("precioMedio") Double precioMedio, @FormParam("descripcion") String descripcion,
			@FormParam("sitioWeb") String sitioWeb, 
			@FormParam("telefono") String telefono, @FormParam("extTelefono") String extTelefono,
			@FormParam("tipoOperacion") Integer tipoOperacion, @FormParam("tipoAlojamiento") Integer tipoAlojamiento,
			@FormParam("tipoServicioAlojamiento") Integer tipoServicioAlojamiento)
	{
		ResponseEntity<?> result = null;
		
		if( idEstablecimiento == null )
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		Servicioturistico st = new Servicioturistico();
		Servicioturisticoalojamiento sa = new Servicioturisticoalojamiento();
		Boolean respuesta = false;

		try
		{
			st.setEIdEstablecimiento(idEstablecimiento);
			st.setNombre(nombre);
			st.setAforo(aforo);
			st.setTstIdtSt(idTST);
			st.setPrecioMinimo(precioMinimo);
			st.setPrecioMaximo(precioMaximo);
			st.setPrecioMedio(precioMedio);
			st.setDescripcion(descripcion);
			st.setSitioWeb(sitioWeb);
			st.setErIdEstadoRegistro(2);
			st.setTelefono(telefono);
			st.setExtensionTelefono(extTelefono);

			sa.setTaIdtipoAlojamiento(tipoAlojamiento);
			sa.setToIdtipoOperacion(tipoOperacion);
			sa.setTsaIdtipoServicioAlojamiento(tipoServicioAlojamiento);
			

			respuesta = servicioturisticoalojamientoDAO.create(st, sa);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return result;
	}
	
	@RequestMapping(value = "/servicioTuristicoAlojamientoEdit", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?>  updateServicioTuristicoAlojamiento(@FormParam("idServicioTuristico") Integer idServicioTuristico,
			@FormParam("idEstablecimiento") Integer idEstablecimiento,
			@FormParam("nombre") String nombre, @FormParam("aforo") Integer aforo, @FormParam("idTST") Integer idTST,
			@FormParam("precioMinimo") Double precioMinimo, @FormParam("precioMaximo") Double precioMaximo,
			@FormParam("precioMedio") Double precioMedio, @FormParam("descripcion") String descripcion,
			@FormParam("sitioWeb") String sitioWeb, 
			@FormParam("telefono") String telefono, @FormParam("extTelefono") String extTelefono,
			@FormParam("tipoOperacion") Integer tipoOperacion, @FormParam("tipoAlojamiento") Integer tipoAlojamiento,
			@FormParam("tipoServicioAlojamiento") Integer tipoServicioAlojamiento)
	{
		ResponseEntity<?> result = null;
		
		if( idEstablecimiento == null || idTST == null)
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		Servicioturistico st = new Servicioturistico();
		Servicioturisticoalojamiento sa = new Servicioturisticoalojamiento();
		Boolean respuesta = false;

		try
		{
			//obetener st
			st = servicioturisticoDAO.findById(idServicioTuristico);
			//editar campos permitidos
			st.setEIdEstablecimiento(idEstablecimiento);
			st.setNombre(nombre);
			st.setAforo(aforo);
			st.setTstIdtSt(idTST);
			st.setPrecioMinimo(precioMinimo);
			st.setPrecioMaximo(precioMaximo);
			st.setPrecioMedio(precioMedio);
			st.setDescripcion(descripcion);
			st.setSitioWeb(sitioWeb);
			st.setTelefono(telefono);
			st.setExtensionTelefono(extTelefono);

			sa = servicioturisticoalojamientoDAO.findById(idServicioTuristico);
			sa.setTaIdtipoAlojamiento(tipoAlojamiento);
			sa.setToIdtipoOperacion(tipoOperacion);
			sa.setTsaIdtipoServicioAlojamiento(tipoServicioAlojamiento);
			

			respuesta = servicioturisticoDAO.update(st) && servicioturisticoalojamientoDAO.update(sa);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return result;
	}



	@RequestMapping(value = "/servicioTuristicoAventura/{idServicioTuristico}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<?> deleteServicioTuristicoAventra( @PathVariable Integer idServicioTuristico )
	{
		ResponseEntity<?> result = null;
		Servicioturistico st = new Servicioturistico();
		Servicioturisticoaventura sa = new Servicioturisticoaventura();
		Boolean respuesta = false;

		try
		{
			st.setIdServicioTuristico(idServicioTuristico);
			st.setEIdEstablecimiento(0);
			st.setTstIdtSt(0);
			st.setErIdEstadoRegistro(0);
			sa.setStIdServicio(idServicioTuristico);
			respuesta = servicioturisticoaventuraDAO.delete(sa);
			respuesta = respuesta && servicioturisticoDAO.delete(st);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return result;
	}
	
	@RequestMapping(value = "/servicioTuristicoAlojamiento/{idServicioTuristico}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<?> deleteServicioTuristicoAlojamiento( @PathVariable Integer idServicioTuristico )
	{
		ResponseEntity<?> result = null;
		Servicioturistico st = new Servicioturistico();
		Servicioturisticoalojamiento sa = new Servicioturisticoalojamiento();
		Boolean respuesta = false;

		try
		{
			st.setIdServicioTuristico(idServicioTuristico);
			st.setEIdEstablecimiento(0);
			st.setTstIdtSt(0);
			st.setErIdEstadoRegistro(0);
			sa.setStIdServicio(idServicioTuristico);
			sa.setTaIdtipoAlojamiento(0);
			sa.setToIdtipoOperacion(0);
			sa.setTsaIdtipoServicioAlojamiento(0);
			respuesta = servicioturisticoalojamientoDAO.delete(sa);
			respuesta = respuesta && servicioturisticoDAO.delete(st);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return result;
	}	

	@RequestMapping(value = "/servicioAventura/evaluacion", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> insertEvaluacionServicioTuristicoAventura( 
			@FormParam("comentario") String comentario, 
			@FormParam("idUsuario") Integer idUsuario, 
			@FormParam("aspectoEstablecimiento") Integer aspectoEstablecimiento,
			@FormParam("atencionCliente") Integer atencionCliente, 
			@FormParam("eficienciaServicio") Integer eficienciaServicio,
			@FormParam("higieneEstablecimiento") Integer higieneEstablecimiento, 
			@FormParam("relacionPrecioCalidad") Integer relacionPrecioCalidad,
			@FormParam("accesibilidad") Integer accesibilidad, 
			@FormParam("comunicacion") Integer comunicacion,
			@FormParam("manejoIdiomas") Integer manejoIdiomas, 
			@FormParam("senalamientoInterno") Integer senalamientoInterno,
			@FormParam("senalamientoExterno") Integer senalamientoExterno, 
			@FormParam("idServicioTuristico") Integer idServicioTuristico, 
			
			@FormParam("equipamientoYMaterial") Integer equipamientoYMaterial, 
			@FormParam("informacionActividad") Integer informacionActividad, 
			@FormParam("informacionRiesgos") Integer informacionRiesgos, 
			@FormParam("condicionEquipo") Integer condicionEquipo, 
			@FormParam("informacionRequisitos") Integer informacionRequisitos, 
			@FormParam("servicioMedico") Integer servicioMedico, 
			@FormParam("seguroVida") Integer seguroVida, 
			@FormParam("supervision") Integer supervision, 
			@FormParam("asistencia") Integer asistencia, 
			@FormParam("informacionReservaLugar") Integer informacionReservaLugar, 
			@FormParam("acuerdoRiesgos") Integer acuerdoRiesgos,
			
			@FormParam("idRegistroVisita")Integer idRegistroVisita)
	{
		ResponseEntity<?> result = null;
		if( idUsuario == null || idServicioTuristico == null )
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		
		Evaluacionservicioturistico est = new Evaluacionservicioturistico();
		Evaluacionaventura ea = new Evaluacionaventura();
		Boolean respuesta = false;
		Servicioturistico s = new Servicioturistico();
		
		
		try
		{
			s = servicioturisticoDAO.read(idServicioTuristico);
			Registrovisita r = new 	Registrovisita();			
			r = registrovisitaDAO.read(idRegistroVisita);
			if( r.getEvaluado() == 1 || r.getTIdUsuario()!=idUsuario || r.getEIdEstablecimiento() != s.getEIdEstablecimiento())
			{
				result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
				return result;
			}
			r.setEvaluado(1);
			registrovisitaDAO.update(r);
			
			est.setComentario(comentario);
			est.setTIdUsuario(idUsuario);
			est.setAspectoEstablecimiento(aspectoEstablecimiento);
			est.setAtencionCliente(atencionCliente);
			est.setEficienciaServicio(eficienciaServicio);
			est.setHigieneEstablecimiento(higieneEstablecimiento);
			est.setRelacionPrecioCalidad(relacionPrecioCalidad);
			est.setAccesibilidad(accesibilidad);
			est.setComunicacion(comunicacion);
			est.setManejoIdiomas(manejoIdiomas);
			est.setSenalamientoInterno(senalamientoInterno);
			est.setSenalamientoExterno(senalamientoExterno);
			est.setSTIdServicioTuristico(idServicioTuristico);
			est.setTeIdEvaluacion(3);
		
			ea.setEquipamientoYMaterial(equipamientoYMaterial);
			ea.setInformacionActividad(informacionActividad);
			ea.setInformacionRiesgos(informacionRiesgos);
			ea.setCondicionEquipo(condicionEquipo);
			ea.setInformacionRequisitos(informacionRequisitos);
			ea.setServicioMedico(servicioMedico);
			ea.setSeguroVida(seguroVida);
			ea.setSupervision(supervision);
			ea.setAsistencia(asistencia);
			ea.setInformacionReservaLugar(informacionReservaLugar);
			ea.setAcuerdoRiesgos(acuerdoRiesgos);
			
			Float promedio = (float)(est.getAspectoEstablecimiento() + est.getAtencionCliente() + est.getEficienciaServicio()
							+ est.getHigieneEstablecimiento() + est.getRelacionPrecioCalidad() + est.getAccesibilidad()
							+ est.getComunicacion() + est.getManejoIdiomas() + est.getSenalamientoInterno()
							+ est.getSenalamientoExterno()
							
							+ ea.getEquipamientoYMaterial() + ea.getInformacionActividad() + ea.getInformacionRiesgos()
							+ ea.getCondicionEquipo() + ea.getInformacionRequisitos() + ea.getServicioMedico()
							+ ea.getSeguroVida() + ea.getAcuerdoRiesgos() + ea.getSupervision()
							+ ea.getAsistencia() + ea.getInformacionReservaLugar())
							/ 21;
			est.setPromedio(promedio);

			respuesta = evaluacionaventuraDAO.create(est, ea);
			servicioturisticoDAO.updatePromedio(idServicioTuristico);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return result;
	}
	
	@RequestMapping(value = "/servicioAlojamiento/evaluacion", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> insertEvaluacionServicioTuristicoAlojamiento( 
			@FormParam("comentario") String comentario, 
			@FormParam("idUsuario") Integer idUsuario, 
			@FormParam("idServicioTuristico") Integer idServicioTuristico, 
			@FormParam("aspectoEstablecimiento") Integer aspectoEstablecimiento,
			@FormParam("atencionCliente") Integer atencionCliente, 
			@FormParam("eficienciaServicio") Integer eficienciaServicio,
			@FormParam("higieneEstablecimiento") Integer higieneEstablecimiento, 
			@FormParam("relacionPrecioCalidad") Integer relacionPrecioCalidad,
			@FormParam("accesibilidad") Integer accesibilidad, 
			@FormParam("comunicacion") Integer comunicacion,
			@FormParam("manejoIdiomas") Integer manejoIdiomas, 
			@FormParam("senalamientoInterno") Integer senalamientoInterno,
			@FormParam("senalamientoExterno") Integer senalamientoExterno, 			
			
			@FormParam("instalacionesRecepcion") Integer instalacionesRecepcion, 
			@FormParam("servicioPortero") Integer servicioPortero, 
			@FormParam("servicioMaletero") Integer servicioMaletero, 
			@FormParam("registroEntrada") Integer registroEntrada, 
			@FormParam("iluminacionHabitacion") Integer iluminacionHabitacion, 
			@FormParam("confortCama") Integer confortCama, 
			@FormParam("limpiezaBano") Integer limpiezaBano, 
			@FormParam("mobiliario") Integer mobiliario, 
			@FormParam("equipamientoElectronicos") Integer equipamientoElectronicos, 
			@FormParam("servicioLavadoPlanchado") Integer servicioLavadoPlanchado)
	{
		ResponseEntity<?> result = null;
		
		if( idUsuario == null || idServicioTuristico == null )
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		
		Evaluacionservicioturistico est = new Evaluacionservicioturistico();
		Evaluacionalojamiento ea = new Evaluacionalojamiento();
		Boolean respuesta = false;

		try
		{
			est.setComentario(comentario);
			est.setTIdUsuario(idUsuario);
			est.setAspectoEstablecimiento(aspectoEstablecimiento);
			est.setAtencionCliente(atencionCliente);
			est.setEficienciaServicio(eficienciaServicio);
			est.setHigieneEstablecimiento(higieneEstablecimiento);
			est.setRelacionPrecioCalidad(relacionPrecioCalidad);
			est.setAccesibilidad(accesibilidad);
			est.setComunicacion(comunicacion);
			est.setManejoIdiomas(manejoIdiomas);
			est.setSenalamientoInterno(senalamientoInterno);
			est.setSenalamientoExterno(senalamientoExterno);
			est.setSTIdServicioTuristico(idServicioTuristico);
			est.setTeIdEvaluacion(1);
		
			ea.setInstalacionesRecepcion(instalacionesRecepcion);
			ea.setServicioPortero(servicioPortero);
			ea.setServicioMaletero(servicioMaletero);
			ea.setRegistroEntrada(registroEntrada);
			ea.setIluminacionHabitacion(iluminacionHabitacion);
			ea.setConfortCama(confortCama);
			ea.setLimpiezaBano(limpiezaBano);
			ea.setMobiliario(mobiliario);
			ea.setEquipamientoElectronicos(equipamientoElectronicos);
			ea.setServicioLavadoPlanchado(servicioLavadoPlanchado);
			
			Float promedio = (float)(est.getAspectoEstablecimiento() + est.getAtencionCliente() + est.getEficienciaServicio()
			+ est.getHigieneEstablecimiento() + est.getRelacionPrecioCalidad() + est.getAccesibilidad()
			+ est.getComunicacion() + est.getManejoIdiomas() + est.getSenalamientoInterno()
			+ est.getSenalamientoExterno()
			
			+ ea.getInstalacionesRecepcion() + ea.getServicioPortero() + ea.getServicioMaletero()
			+ ea.getRegistroEntrada() + ea.getIluminacionHabitacion() + ea.getConfortCama()
			+ ea.getLimpiezaBano() + ea.getMobiliario() + ea.getMobiliario()
			+ ea.getEquipamientoElectronicos() + ea.getServicioLavadoPlanchado())
			/ 21;
			est.setPromedio(promedio);


			respuesta = evaluacionalojamientoDAO.create(est, ea);
			
			servicioturisticoDAO.updatePromedio(idServicioTuristico);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return result;
	}
	
	
	


}
