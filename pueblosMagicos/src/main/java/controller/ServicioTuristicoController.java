package controller;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dao.EvaluacionservicioturisticoDAO;
import dao.FotoservicioturisticoDAO;
import dao.ServicioturisticoDAO;
import dto.Atractivoturistico;
import dto.Calificacionpueblomagico;
import dto.Evaluacionservicioturistico;
import dto.FotoSimple;
import dto.Fotoatractivoturistico;
import dto.Fotoservicioturistico;
import dto.Servicioturistico;

@RestController
@Component
public class ServicioTuristicoController
{

	@Autowired
	private ServicioturisticoDAO servicioturisticoDAO;
	
	@Autowired
	private FotoservicioturisticoDAO fotoservicioTuristicoDAO;
	
	@Autowired EvaluacionservicioturisticoDAO evaluacionservicioturisticoDAO;
	
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
		public ResponseEntity<?>  getSomeServicioTuristico( @PathVariable int first, @PathVariable int numRegistros )
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
		@RequestMapping(value = "/servicioTuristico/{id}/images", method = RequestMethod.GET, produces = "application/json")
		public ResponseEntity<?> getAllImages( @PathVariable("id") int id )
		{
			List<FotoSimple> fotos = null;
			ResponseEntity<?> result = null;
			try
			{
				fotos = fotoservicioTuristicoDAO.readAllSimpleByIdServicioTuristico(id);
				result = new ResponseEntity<List<FotoSimple>>(fotos, HttpStatus.OK);
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
		public ResponseEntity<?> getAllPueblosMagicosByEstado( @PathVariable int id )
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

		// WS que devuelve la califcacion de un atractivo turistico
		@RequestMapping(value = "/servicioTuristico/{id}/Calificacion", method = RequestMethod.GET, produces = "application/json")
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
		
}
