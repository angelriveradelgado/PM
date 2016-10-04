package controller;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Time;
import java.util.Date;
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
import dto.Atractivoturistico;
import dto.Calificacionatractivoturistico;
import dto.FotoSimple;
import dto.Fotoatractivoturistico;
import dto.Pueblomagico;

@RestController
@Component
public class AtractivoTuristicoController
{
	@Autowired
	private AtractivoturisticoDAO atractivoTuristicoDAO;

	@Autowired
	private FotoatractivoturisticoDAO fotoatractivoturisticoDAO;
	
	@Autowired
	CalificacionatractivoturisticoDAO calificacionatractivoturisticoDAO;

	// WS que devuelve un atractivo turistico por su id
	@RequestMapping(value = "/atractivoTuristico/{idAtractivoTuristico}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?>  getAtractivoturistico( @PathVariable int idAtractivoTuristico )
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

	// WS que devuelve una lista con los pueblos magicos en un rango de
	// resultados (limit)
	@RequestMapping(value = "/atractivoturistico/{first}/{numRegistros}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?>  getSomeAtractivoTuristico( @PathVariable int first, @PathVariable int numRegistros )
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

	// WS que devuelve una lista de las imagenes de un AT. Recibe el id del
	// atractivo
	@RequestMapping(value = "/atractivoTuristico/{id}/images", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getAllImages( @PathVariable("id") int id )
	{
		List<FotoSimple> fotos = null;
		ResponseEntity<?> result = null;
		try
		{
			fotos = fotoatractivoturisticoDAO.readAllSimpleByIdAtractivoTuristico(id);
			result = new ResponseEntity<List<FotoSimple>>(fotos, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

	// WS que devuelve una imagen de un AT. Recibe el id de la imagen
	@RequestMapping(value = "/atractivoTuristico/image/{id}", method = RequestMethod.GET, produces = "image/jpg")
	public ResponseEntity<?> getPuebloMagicoImage( @PathVariable int id )
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
	public ResponseEntity<?> getAllPueblosMagicosByEstado( @PathVariable int id )
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
		@RequestMapping(value = "/atractivoTuristico/{id}/Calificacion", method = RequestMethod.GET, produces = "application/json")
		public ResponseEntity<?> getCalificacionesByIdAtractivoTuristico( @PathVariable int id )
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


	
	
	
	
	/////////////////
	///////////////////
	@RequestMapping(value = "/atractivoTuristico", method = RequestMethod.POST, headers = "Accept=application/json")
	public Boolean insertAtractivoTuristico( @FormParam("idAsentamiento") int idAsentamiento,
			@FormParam("idAdministrador") int idAdministrador, @FormParam("idTurista") int idTurista,
			@FormParam("descripcion") String descripcion, @FormParam("estadoRegistro") String estadoRegistro,
			@FormParam("fechaFin") Date fechaFin, @FormParam("fechaInicio") Date fechaInicio,
			@FormParam("horaFin") Time horaFin, @FormParam("horaInicio") Time horaInicio,
			@FormParam("latitud") double latitud, @FormParam("longitud") double longitud,
			@FormParam("nombre") String nombre, @FormParam("idTipoAtractivo") int idTipoAtractivo )
	{
		Atractivoturistico at = new Atractivoturistico();
		Boolean respuesta = false;

		try
		{
			at.setAIdAsentamiento(idAsentamiento);
			at.setAIdUsuario(idAdministrador);
			at.setTIdUsuario(idTurista);
			at.setDescripcion(descripcion);
			at.setLatitud(latitud);
			at.setLongitud(longitud);
			at.setNombre(nombre);
			at.setTaIdtipoAtractivo(idTipoAtractivo);

			respuesta = atractivoTuristicoDAO.create(at);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return respuesta;
	}

}
