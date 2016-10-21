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
import dao.FotoservicioturisticoDAO;
import dao.ServicioturisticoDAO;
import dao.ServicioturisticoalojamientoDAO;
import dao.ServicioturisticoaventuraDAO;
import dto.Atractivoturistico;
import dto.Calificacionpueblomagico;
import dto.Evaluacionalojamiento;
import dto.Evaluacionaventura;
import dto.Evaluacionservicioturistico;
import dto.FotoServicioTuristicoSimple;
import dto.Fotoatractivoturistico;
import dto.Fotoservicioturistico;
import dto.Pueblomagico;
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

	// WS que devuelve un servicio turistico por su id
	@RequestMapping(value = "/servicioTuristico/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getServicioTuristico( @PathVariable int id )
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

	// WS que devuelve una lista con los servicios turisticos en un rango de
	// resultados (limit)
	@RequestMapping(value = "/servicioTuristico/{first}/{numRegistros}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getSomeServicioTuristico( @PathVariable int first, @PathVariable int numRegistros )
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
	public ResponseEntity<?> getAllImages( @PathVariable("id") int id )
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
	@RequestMapping(value = "/servicioTuristico/image/{id}", method = RequestMethod.GET, produces = "image/jpg")
	public ResponseEntity<?> getPuebloMagicoImage( @PathVariable int id )
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
	public ResponseEntity<?> getAllServiciosTuristicosByPM( @PathVariable int id )
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
	
	// WS que devuelve una lista con todos los ST de un PM. Recibe el id del PM
	@RequestMapping(value = "/servicioTuristico/puebloMagico/{id}/limit/{first}/{numRegistros}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getAllServiciosTuristicosByPMByLimit( @PathVariable int id,
			@PathVariable int first,
			@PathVariable int numRegistros )
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

	// WS que devuelve la evaluacion de un servicio turistico
	@RequestMapping(value = "/servicioTuristico/{id}/evaluacion", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getEvaluacionesByIdServicioTuristico( @PathVariable int id )
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
	public ResponseEntity<?> getEvaluacionesByIdServicioTuristicoAndLimit( @PathVariable int id,
			@PathVariable int first, @PathVariable int numReg )
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
	public ResponseEntity<?> getEvaluacionAventura( @PathVariable int idEvaluacion )
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
	public ResponseEntity<?> getEvaluacionAlojamiento( @PathVariable int idEvaluacion )
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
	public Boolean insertServicioTuristico( @FormParam("idEstablecimiento") Integer idEstablecimiento,
			@FormParam("nombre") String nombre, @FormParam("aforo") Integer aforo, @FormParam("idTST") Integer idTST,
			@FormParam("precioMinimo") double precioMinimo, @FormParam("precioMaximo") double precioMaximo,
			@FormParam("precioMedio") double precioMedio, @FormParam("descripcion") String descripcion,
			@FormParam("sitioWeb") String sitioWeb, @FormParam("idEstadoRegistro") int idEstadoRegistro,
			@FormParam("telefono") String telefono, @FormParam("extTelefono") String extTelefono,
			@FormParam("promedio") float promedio )
	{
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
			st.setErIdEstadoRegistro(idEstadoRegistro);
			st.setTelefono(telefono);
			st.setExtensionTelefono(extTelefono);
			st.setPromedio(promedio);

			respuesta = servicioturisticoDAO.create(st);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return respuesta;
	}
	
	@RequestMapping(value = "/servicioTuristicoAventura", method = RequestMethod.POST, headers = "Accept=application/json")
	public Boolean insertServicioTuristicoAventura( @FormParam("idEstablecimiento") Integer idEstablecimiento,
			@FormParam("nombre") String nombre, @FormParam("aforo") Integer aforo, @FormParam("idTST") Integer idTST,
			@FormParam("precioMinimo") double precioMinimo, @FormParam("precioMaximo") double precioMaximo,
			@FormParam("precioMedio") double precioMedio, @FormParam("descripcion") String descripcion,
			@FormParam("sitioWeb") String sitioWeb, @FormParam("idEstadoRegistro") int idEstadoRegistro,
			@FormParam("telefono") String telefono, @FormParam("extTelefono") String extTelefono,
			@FormParam("stIdServicio") int stIdServicio )
	{
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
			st.setErIdEstadoRegistro(idEstadoRegistro);
			st.setTelefono(telefono);
			st.setExtensionTelefono(extTelefono);
			
			sa.setStIdServicio(stIdServicio);

			respuesta = servicioturisticoaventuraDAO.create(st, sa);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return respuesta;
	}
	
	@RequestMapping(value = "/servicioTuristicoAlojamiento", method = RequestMethod.POST, headers = "Accept=application/json")
	public Boolean insertServicioTuristicoAlojamiento( @FormParam("idEstablecimiento") Integer idEstablecimiento,
			@FormParam("nombre") String nombre, @FormParam("aforo") Integer aforo, @FormParam("idTST") Integer idTST,
			@FormParam("precioMinimo") double precioMinimo, @FormParam("precioMaximo") double precioMaximo,
			@FormParam("precioMedio") double precioMedio, @FormParam("descripcion") String descripcion,
			@FormParam("sitioWeb") String sitioWeb, @FormParam("idEstadoRegistro") int idEstadoRegistro,
			@FormParam("telefono") String telefono, @FormParam("extTelefono") String extTelefono,
			@FormParam("tipoOperacion") int tipoOperacion, @FormParam("tipoAlojamiento") int tipoAlojamiento,
			@FormParam("tipoServicioAlojamiento") int tipoServicioAlojamiento)
	{
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
			st.setErIdEstadoRegistro(idEstadoRegistro);
			st.setTelefono(telefono);
			st.setExtensionTelefono(extTelefono);

			sa.setTaIdtipoAlojamiento(tipoAlojamiento);
			sa.setToIdtipoOperacion(tipoOperacion);
			sa.setTsaIdtipoServicioAlojamiento(tipoServicioAlojamiento);
			

			respuesta = servicioturisticoDAO.create(st);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return respuesta;
	}

	@RequestMapping(value = "/servicioTuristico", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<?> deleteServicioTuristico( @FormParam("idServicioTuristico") Integer idServicioTuristico )
	{
		ResponseEntity<?> result = null;
		Servicioturistico st = new Servicioturistico();
		Boolean respuesta = false;

		try
		{
			st.setIdServicioTuristico(idServicioTuristico);
			respuesta = servicioturisticoDAO.delete(st);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return result;
	}

	@RequestMapping(value = "/servicioAventura/{id}/evaluacion", method = RequestMethod.POST, headers = "Accept=application/json")
	public Boolean insertEvaluacionServicioTuristicoAventura( 
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
			@FormParam("acuerdoRiesgos") Integer acuerdoRiesgos)
	{
		Evaluacionservicioturistico est = new Evaluacionservicioturistico();
		Evaluacionaventura ea = new Evaluacionaventura();
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
			

			respuesta = evaluacionaventuraDAO.create(est, ea);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return respuesta;
	}
	
	@RequestMapping(value = "/servicioAlojamiento/{id}/evaluacion", method = RequestMethod.POST, headers = "Accept=application/json")
	public Boolean insertEvaluacionServicioTuristicoAlojamiento( 
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
			

			respuesta = evaluacionalojamientoDAO.create(est, ea);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return respuesta;
	}

}
