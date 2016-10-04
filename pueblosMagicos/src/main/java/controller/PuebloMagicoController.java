package controller;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dao.CalificacionpueblomagicoDAO;
import dao.FotopueblomagicoDAO;
import dao.PueblomagicoDAO;
import dto.Calificacionatractivoturistico;
import dto.Calificacionpueblomagico;
import dto.FotoSimple;
import dto.Fotopueblomagico;
import dto.Pueblomagico;

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

	// WS que devuelve un pm por su id
	@RequestMapping(value = "/puebloMagico/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getPuebloMagico( @PathVariable int id )
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


	@RequestMapping(value = "/puebloMagico/{first}/{max}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getSomePuebloMagico( @PathVariable("first") int first, @PathVariable("max") int max )
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
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

	// WS que devuelve una lista de las imagenes de un pm. Recibe el id del pm
	@RequestMapping(value = "/puebloMagico/{id}/images", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getAllImages( @PathVariable("id") int id )
	{
		List<FotoSimple> fotos = null;
		ResponseEntity<?> result = null;
		try
		{
			fotos = fotopueblomagicoDAO.readAllSimpleByIdPuebloMagico(id);
			result = new ResponseEntity<List<FotoSimple>>(fotos, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

	// WS que devuelve una imagen de un pm. Recibe el id de la imagen
	@RequestMapping(value = "/puebloMagico/image/{id}", method = RequestMethod.GET, produces = "image/jpg")
	public ResponseEntity<?> getPuebloMagicoImage( @PathVariable int id )
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
	public ResponseEntity<?> getAllPueblosMagicosByEstado( @PathVariable int id )
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

	// WS que devuelve la califcacion de un atractivo turistico
	@RequestMapping(value = "/puebloMagico/{id}/Calificacion", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getCalificacionesByIdPuebloMagico( @PathVariable int id )
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

	// WS que recibe un pm y lo inserta en la bd
	@RequestMapping(value = "/puebloMagico", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> insertPuebloMagico( @FormParam("nombre") String nombre,
			@FormParam("latitud") double latitud, @FormParam("longitud") double longitud,
			@FormParam("descripcion") String descripcion, @FormParam("idMunicipio") int idMunicipio)
	{
		ResponseEntity<?> result = null;
		Pueblomagico pm = new Pueblomagico();
		Boolean respuesta = false;

		try
		{
			pm.setNombre(nombre);
			pm.setLatitud(latitud);
			pm.setLongitud(longitud);
			pm.setDescripcion(descripcion);
			pm.setEpmIdestadoPuebloMagico(1);
			pm.setMIdMunicipio(idMunicipio);
			respuesta = puebloMagicoDAO.create(pm);

			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		} catch (Exception e2)
		{
			e2.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return result;
	}

	@RequestMapping(value = "/puebloMagico", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<?> deletePuebloMagico( @FormParam("idPuebloMagico") Integer idPuebloMagico )
	{
		ResponseEntity<?> result = null;
		Pueblomagico pm = new Pueblomagico();
		Boolean respuesta = false;

		try
		{
			pm.setIdPuebloMagico(idPuebloMagico);
			respuesta = puebloMagicoDAO.delete(pm);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return result;
	}

	@RequestMapping(value = "/puebloMagico", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseEntity<?> updatePuebloMagico( @FormParam("idPuebloMagico") int idPuebloMagico,
			@FormParam("nombre") String nombre, @FormParam("latitud") double latitud,
			@FormParam("longitud") double longitud, @FormParam("descripcion") String descripcion,
			@FormParam("idEstadoPubloMagico") int idEstadoPubloMagico,
			@FormParam("idMunicipio") int idMunicipio )
	{
		ResponseEntity<?> result = null;
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

}
