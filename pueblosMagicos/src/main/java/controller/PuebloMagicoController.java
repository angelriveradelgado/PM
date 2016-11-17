package controller;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dao.CalificacionpueblomagicoDAO;
import dao.FotopueblomagicoDAO;
import dao.PueblomagicoDAO;
import dao.RegistrovisitaDAO;
import dto.Calificacionatractivoturistico;
import dto.Calificacionpueblomagico;
import dto.FotoPuebloMagicoSimple;
import dto.Fotopueblomagico;
import dto.Pueblomagico;
import dto.Registrovisita;

@RestController
@Component
public class PuebloMagicoController
{
	@Autowired
	private PueblomagicoDAO puebloMagicoDAO;

	@Autowired
	private FotopueblomagicoDAO fotopueblomagicoDAO;

	@Autowired
	CalificacionpueblomagicoDAO calificacionpueblomagicoDAO;

	@Autowired
	private RegistrovisitaDAO registrovisitaDAO;
	
	// WS que devuelve un pm por su id
	@RequestMapping(value = "/puebloMagico/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getPuebloMagico( @PathVariable Integer id )
	{
		ResponseEntity<?> result = null;
		Pueblomagico p = null;
		try
		{
			p = puebloMagicoDAO.findById(id);
			result = new ResponseEntity<Pueblomagico>(p, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

	// WS que devuelve un pm por su nombre
	@RequestMapping(value = "/puebloMagico/nombre/{nombre}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getPuebloMagicoByNombre( @PathVariable String nombre )
	{
		ResponseEntity<?> result = null;
		Pueblomagico p = null;
		try
		{
			p = puebloMagicoDAO.findByNombrePueblomagico(nombre);
			result = new ResponseEntity<Pueblomagico>(p, HttpStatus.OK);
		} catch (Exception e)
		{
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			
			result = new ResponseEntity<String>(sw.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

	@RequestMapping(value = "/puebloMagico/{first}/{max}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getSomePuebloMagico( @PathVariable("first") Integer first, @PathVariable("max") Integer max )
	{
		List<Pueblomagico> pms = null;
		ResponseEntity<?> result = null;
		try
		{
			System.out.println("inicia");
			pms = puebloMagicoDAO.getPueblomagicoByLimit(first, max);
			System.out.println("termina");
			result = new ResponseEntity<List<Pueblomagico>>(pms, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

	// WS que devuelve una lista con todos los pm
	@RequestMapping(value = "/puebloMagico", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getAllPueblosMagicos( )
	{
		List<Pueblomagico> pms = null;
		ResponseEntity<?> result = null;
		try
		{
			pms = puebloMagicoDAO.readAll();
			result = new ResponseEntity<List<Pueblomagico>>(pms, HttpStatus.OK);
		} catch (Exception e)
		{
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			
			result = new ResponseEntity<String>(sw.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

	// WS que devuelve una lista de las fotos de un pm. Recibe el id del pm
	@RequestMapping(value = "/puebloMagico/{id}/fotos", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getAllFotos( @PathVariable("id") Integer id )
	{
		List<FotoPuebloMagicoSimple> fotos = null;
		ResponseEntity<?> result = null;
		try
		{
			fotos = fotopueblomagicoDAO.readAllSimpleByIdPuebloMagico(id);
			result = new ResponseEntity<List<FotoPuebloMagicoSimple>>(fotos, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

	// WS que devuelve una foto de un pm. Recibe el id de la imagen
	@RequestMapping(value = "/puebloMagico/foto/{id}", method = RequestMethod.GET, produces = "image/jpg")
	public ResponseEntity<?> getPuebloMagicoFoto( @PathVariable Integer id )
	{
		ResponseEntity<?> result = null;

		Fotopueblomagico foto = new Fotopueblomagico();
		foto = fotopueblomagicoDAO.read(id);
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

	// WS que devuelve una lista con todos los pm de un estado. Recibe el id del
	// estado
	@RequestMapping(value = "/puebloMagico/estado/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getAllPueblosMagicosByIdEstado( @PathVariable Integer id )
	{
		List<Pueblomagico> pms = null;
		ResponseEntity<?> result = null;
		try
		{
			pms = puebloMagicoDAO.findByIdEstado(id);
			result = new ResponseEntity<List<Pueblomagico>>(pms, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

	// WS que devuelve una lista de califcaciones de un pueblo mágico
	@RequestMapping(value = "/puebloMagico/{id}/Calificacion", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getCalificacionesByIdPuebloMagico( @PathVariable Integer id )
	{
		List<Calificacionpueblomagico> c = null;
		ResponseEntity<?> result = null;
		try
		{
			c = calificacionpueblomagicoDAO.findByIdPuebloMagico(id);
			result = new ResponseEntity<List<Calificacionpueblomagico>>(c, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	// WS que devuelve la califcacion de un pueblo mágico
	@RequestMapping(value = "/puebloMagico/{id}/Calificacion/limit/{first}/{num}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getCalificacionesByIdPuebloMagicoByLimit( @PathVariable Integer id, 
			@PathVariable Integer first, @PathVariable Integer num )
	{
		List<Calificacionpueblomagico> c = null;
		ResponseEntity<?> result = null;
		try
		{
			c = calificacionpueblomagicoDAO.getCalificacionpueblomagicoByIdPMByLimit(id, first, num);
			result = new ResponseEntity<List<Calificacionpueblomagico>>(c, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

	// WS que recibe un pm y lo inserta en la bd
	@RequestMapping(value = "/puebloMagico", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> insertPuebloMagico( @FormParam("nombre") String nombre,
			@FormParam("latitud") Double latitud, @FormParam("longitud") Double longitud,
			@FormParam("descripcion") String descripcion, @FormParam("idMunicipio") Integer idMunicipio )
	{
		ResponseEntity<?> result = null;
		
		if( nombre == null || latitud == null || longitud == null || idMunicipio == null )
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		
		Pueblomagico pm = new Pueblomagico();
		Boolean respuesta = false;

		try
		{
			pm.setNombre(nombre);
			pm.setLatitud(latitud);
			pm.setLongitud(longitud);
			pm.setDescripcion(descripcion);
			pm.setMIdMunicipio(idMunicipio);
			pm.setPromedio( (float) 0 );
			pm.setEpmIdestadoPuebloMagico(1);
			respuesta = puebloMagicoDAO.create(pm);

			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		} catch (Exception e2)
		{
			e2.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return result;
	}

	@RequestMapping(value = "/puebloMagico/{idPuebloMagico}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<?> deletePuebloMagico( @PathVariable Integer idPuebloMagico )
	{
		ResponseEntity<?> result = null;
		Pueblomagico pm = new Pueblomagico();
		Boolean respuesta = false;

		try
		{
			pm.setIdPuebloMagico(idPuebloMagico);
			pm.setMIdMunicipio(0);
			respuesta = puebloMagicoDAO.delete(pm);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return result;
	}

	@RequestMapping(value = "/puebloMagicoEdit", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> updatePuebloMagico( @FormParam("idPuebloMagico") Integer idPuebloMagico,
			@FormParam("nombre") String nombre, @FormParam("latitud") Double latitud,
			@FormParam("longitud") Double longitud, @FormParam("descripcion") String descripcion,
			@FormParam("idEstadoPubloMagico") Integer idEstadoPubloMagico, @FormParam("idMunicipio") Integer idMunicipio )
	{
		ResponseEntity<?> result = null;
		
		if( nombre == null || latitud == null || longitud == null || idMunicipio == null )
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		
		Pueblomagico pm = new Pueblomagico();
		Boolean respuesta = false;

		try
		{
			pm.setIdPuebloMagico(idPuebloMagico);
			pm.setNombre(nombre);
			pm.setLatitud(latitud);
			pm.setLongitud(longitud);
			pm.setDescripcion(descripcion);
			pm.setEpmIdestadoPuebloMagico(idEstadoPubloMagico);
			pm.setMIdMunicipio(idMunicipio);

			respuesta = puebloMagicoDAO.update(pm);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return result;
	}

	// WS que inserta una cal de un pm
	@RequestMapping(value = "/puebloMagico/calificacion", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> setCalificacionPuebloMagico( @FormParam("calificacion") Integer calificacion,
			@FormParam("comentario") String comentario, @FormParam("idUsuario") Integer idUsuario,
			@FormParam("idPuebloMagico") Integer idPuebloMagico,
			@FormParam("idRegistroVisita")Integer idRegistroVisita)
	{
		ResponseEntity<?> result = null;
		
		if( idUsuario == null || idPuebloMagico == null )
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		
		Calificacionpueblomagico c = new Calificacionpueblomagico();
		Boolean respuesta = false;

		try
		{
			
			Registrovisita r = new 	Registrovisita();			
			r = registrovisitaDAO.read(idRegistroVisita);
			if( r.getEvaluado() == 1 || r.getTIdUsuario()!=idUsuario || r.getPmIdPuebloMagico() != idPuebloMagico)
			{
				result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
				return result;
			}
			r.setEvaluado(1);
			registrovisitaDAO.update(r);
			c.setCalificacion(calificacion);
			c.setComentario(comentario);
			c.setPmIdPuebloMagico(idPuebloMagico);
			c.setTIdUsuario(idUsuario);
			respuesta = calificacionpueblomagicoDAO.create(c);
			puebloMagicoDAO.updatePromedio(idPuebloMagico);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		} catch (Exception e2)
		{
			e2.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return result;
	}
}
