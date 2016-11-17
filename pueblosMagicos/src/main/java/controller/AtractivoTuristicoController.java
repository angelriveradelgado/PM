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

import dao.AtractivoturisticoDAO;
import dao.CalificacionatractivoturisticoDAO;
import dao.FotoatractivoturisticoDAO;
import dao.TipoatractivoDAO;
import dto.Atractivoturistico;
import dto.Calificacionatractivoturistico;
import dto.Direccion;
import dto.FotoAtractivoTuristicoSimple;
import dto.Fotoatractivoturistico;
import dto.Tipoatractivo;

@RestController
@Component
public class AtractivoTuristicoController
{
	@Autowired
	private AtractivoturisticoDAO atractivoTuristicoDAO;
	
	@Autowired
	private TipoatractivoDAO tipoatractivoDAO;

	@Autowired
	private FotoatractivoturisticoDAO fotoatractivoturisticoDAO;
	
	@Autowired
	CalificacionatractivoturisticoDAO calificacionatractivoturisticoDAO;

	// WS que devuelve un atractivo turistico por su id
	@RequestMapping(value = "/atractivoTuristico/{idAtractivoTuristico}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?>  getAtractivoturistico( @PathVariable Integer idAtractivoTuristico )
	{
		ResponseEntity<?> result = null;
		Atractivoturistico u = null;
		try
		{
			u = atractivoTuristicoDAO.findById(idAtractivoTuristico);
			result = new ResponseEntity<Atractivoturistico>(u, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

	// WS que devuelve una lista con los atractivos turisticos en un rango de
	// resultados (limit)
	@RequestMapping(value = "/atractivoturistico/{first}/{numRegistros}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?>  getSomeAtractivoTuristico( @PathVariable Integer first, @PathVariable Integer numRegistros )
	{
		ResponseEntity<?> result = null;
		List<Atractivoturistico> a = null;
		try
		{

			a = atractivoTuristicoDAO.getAtractivoturisticoByLimit(first, numRegistros);
			result = new ResponseEntity<List<Atractivoturistico>>(a, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

	// WS que devuelve una lista de las fotos de un AT. Recibe el id del
	// atractivo
	@RequestMapping(value = "/atractivoTuristico/{id}/fotos", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getAllImages( @PathVariable("id") Integer id )
	{
		List<FotoAtractivoTuristicoSimple> fotos = null;
		ResponseEntity<?> result = null;
		try
		{
			fotos = fotoatractivoturisticoDAO.readAllSimpleByIdAtractivoTuristico(id);
			result = new ResponseEntity<List<FotoAtractivoTuristicoSimple>>(fotos, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

	// WS que devuelve una foto de un AT. Recibe el id de la foto
	@RequestMapping(value = "/atractivoTuristico/foto/{id}", method = RequestMethod.GET, produces = "image/jpg")
	public ResponseEntity<?> getAtractivoTuristicoFoto( @PathVariable Integer id )
	{
		ResponseEntity<?> result = null;

		Fotoatractivoturistico foto = new Fotoatractivoturistico();
		foto = fotoatractivoturisticoDAO.read(id);
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
	
	// WS que devuelve una lista con todos los AT de un PM. Recibe el id del PM
	@RequestMapping(value = "/atractivoTuristico/puebloMagico/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getAllAtractivosTuristicosByPM( @PathVariable Integer id )
	{
		List<Atractivoturistico> ats = null;
		ResponseEntity<?> result = null;
		try
		{
			ats = atractivoTuristicoDAO.findByIdPuebloMagico(id);
			result = new ResponseEntity<List<Atractivoturistico>>(ats, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	// WS que devuelve una lista con todos los AT de un PM. Recibe el id del PM
	@RequestMapping(value = "/atractivoTuristico/puebloMagico/{id}/limit/{first}/{numRegistros}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getAllAtractivosTuristicosByPMByLimit( @PathVariable Integer id,
			@PathVariable Integer first,
			@PathVariable Integer numRegistros)
	{
		List<Atractivoturistico> ats = null;
		ResponseEntity<?> result = null;
		try
		{
			ats = atractivoTuristicoDAO.getAtractivoturisticoByPMByLimit(id, first, numRegistros);
			result = new ResponseEntity<List<Atractivoturistico>>(ats, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	
	// WS que devuelve la lista de calificaciones de un atractivo turistico
	@RequestMapping(value = "/atractivoTuristico/{id}/Calificacion", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getCalificacionesByIdAtractivoTuristico( @PathVariable Integer id )
	{
		List<Calificacionatractivoturistico> c = null;
		ResponseEntity<?> result = null;
		try
		{
			c = calificacionatractivoturisticoDAO.findByIdAtractivoTuristico(id);
			result = new ResponseEntity<List<Calificacionatractivoturistico>>(c, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	// WS que devuelve la lista de calificaciones de un atractivo turistico
	@RequestMapping(value = "/atractivoTuristico/{id}/Calificacion/limit/{first}/{numRegistros}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getCalificacionesByIdAtractivoTuristicoByLimit( @PathVariable Integer id,
			@PathVariable Integer first,@PathVariable Integer numRegistros)
	{
		List<Calificacionatractivoturistico> c = null;
		ResponseEntity<?> result = null;
		try
		{
			c = calificacionatractivoturisticoDAO.getCalificacionatractivoturisticoByLimit(id, first, numRegistros);
			result = new ResponseEntity<List<Calificacionatractivoturistico>>(c, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	// WS que devuelve la lista de calificaciones de un atractivo turistico
	@RequestMapping(value = "/atractivoTuristico/{id}/direccion", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getDireccionIdAtractivoTuristico( @PathVariable Integer id )
	{
		String direccion = null;
		Direccion d = new Direccion();
		
		ResponseEntity<?> result = null;
		try
		{
			direccion = atractivoTuristicoDAO.getDireccion(id);
			d.setDireccion(direccion);
			result = new ResponseEntity<Direccion>(d, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
		
	
	@RequestMapping(value = "/atractivoturistico/tipos/atractivos", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?>  getTiposAtractivoTuristico( )
	{
		ResponseEntity<?> result = null;
		List<Tipoatractivo> t = null;
		try
		{

			t = tipoatractivoDAO.readAll();
			result = new ResponseEntity<List<Tipoatractivo>>(t, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}


	
	
	
	
	/////////////////
	///////////////////
	@RequestMapping(value = "/atractivoTuristico", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> insertAtractivoTuristico( 
			@FormParam("nombre") String nombre, 
			@FormParam("descripcion") String descripcion, 
			@FormParam("latitud") Double latitud, 
			@FormParam("longitud") Double longitud,
			@FormParam("idTurista") Integer idTurista,	
			@FormParam("idTipoAtractivo") Integer idTipoAtractivo,
			@FormParam("idAdministrador") Integer idAdministrador, 			
			@FormParam("idAsentamiento") Integer idAsentamiento)
	{
		ResponseEntity<?> result = null;
		if( nombre == null || latitud == null || longitud == null )
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		
		Atractivoturistico at = new Atractivoturistico();
		Boolean respuesta = false;
		

		try
		{
			at.setNombre(nombre);
			at.setDescripcion(descripcion);
			at.setLatitud(latitud);
			at.setLongitud(longitud);
			at.setTIdUsuario(idTurista);
			at.setTaIdtipoAtractivo(idTipoAtractivo);
			at.setAIdUsuario(idAdministrador);
			at.setAIdAsentamiento(idAsentamiento);
			at.setErIdEstadoRegistro(2);

			respuesta = atractivoTuristicoDAO.create(at);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		return result;
		
	}
	
	@RequestMapping(value = "/atractivoTuristicoEdit", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> updatetAtractivoTuristico( 
			@FormParam("idAtractivoTuristico") Integer idAtractivoTuristico, 
			@FormParam("nombre") String nombre, 
			@FormParam("descripcion") String descripcion, 
			@FormParam("latitud") Double latitud, 
			@FormParam("longitud") Double longitud,
			@FormParam("idTurista") Integer idTurista,	
			@FormParam("idTipoAtractivo") Integer idTipoAtractivo,
			@FormParam("idAdministrador") Integer idAdministrador, 			
			@FormParam("idAsentamiento") Integer idAsentamiento,
			@FormParam("idEstadoRegistro") Integer idEstadoRegistro)
	{
		ResponseEntity<?> result = null;
		Atractivoturistico at = new Atractivoturistico();
		Boolean respuesta = false;

		if( nombre == null || latitud == null || longitud == null || idAsentamiento == null || idAtractivoTuristico == null )
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		
		try
		{
			at = atractivoTuristicoDAO.read(idAtractivoTuristico);
			at.setNombre(nombre);
			at.setDescripcion(descripcion);
			at.setLatitud(latitud);
			at.setLongitud(longitud);
			at.setTIdUsuario(idTurista);
			at.setTaIdtipoAtractivo(idTipoAtractivo);
			at.setAIdUsuario(idAdministrador);
			at.setAIdAsentamiento(idAsentamiento);
			at.setErIdEstadoRegistro(idEstadoRegistro);
			
			respuesta = atractivoTuristicoDAO.update(at);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		return result;
	}

	@RequestMapping(value = "/atractivoTuristico/{idAtractivoTuristico}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<?> deleteAtractivoTuristico( @PathVariable Integer idAtractivoTuristico )
	{
		ResponseEntity<?> result = null;
		Atractivoturistico at = new Atractivoturistico();
		Boolean respuesta = false;

		if( idAtractivoTuristico == null )
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		
		try
		{
			at.setIdAtractivoTuristico(idAtractivoTuristico);
			at.setErIdEstadoRegistro(0);
			respuesta = atractivoTuristicoDAO.delete(at);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return result;
	}

	
}
