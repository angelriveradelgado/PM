package controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MultivaluedMap;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import dao.AtractivoturisticoDAO;
import dao.FotoatractivoturisticoDAO;
import dao.FotopueblomagicoDAO;
import dao.FotoservicioemergenciasDAO;
import dao.FotoservicioturisticoDAO;
import dao.PueblomagicoDAO;
import dao.ServicioemergenciasDAO;
import dao.ServicioturisticoDAO;
import dao.UsuarioDAO;
import dto.Atractivoturistico;
import dto.Fotoatractivoturistico;
import dto.Fotopueblomagico;
import dto.Fotoservicioturistico;
import dto.Pueblomagico;
import dto.Servicioemergencias;
import dto.Servicioturistico;
import dto.Usuario;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@RestController
@Component
public class FotoUploadController
{
	private static final Log log = LogFactory.getLog(FotoUploadController.class);
	@Autowired
	private PueblomagicoDAO puebloMagicoDAO;
	@Autowired
	private UsuarioDAO usuarioDAO;
	@Autowired
	private ServicioturisticoDAO servicioturisticoDAO;
	@Autowired
	private ServicioemergenciasDAO servicioemergenciasDAO;
	@Autowired
	private AtractivoturisticoDAO atractivoturisticoDAO;
	@Autowired
	private FotopueblomagicoDAO fotopueblomagicoDAO;
	@Autowired
	private FotoatractivoturisticoDAO fotoatractivoturisticoDAO;
	@Autowired
	private FotoservicioturisticoDAO fotoservicioturisticoDAO;

	@Autowired
	private FotoservicioemergenciasDAO fotoservicioemergenciasDAO;

	// WS que recibe una imagen y el id del pm e inserta la imagen
	@RequestMapping(value = "/puebloMagico/{idPuebloMagico}/uploadFotoPuebloMagico", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
	public ResponseEntity<?> uploadFotoPuebloMagico( @PathVariable("idPuebloMagico") Integer idPuebloMagico,
			@FormParam("file") MultipartFile file,
			@FormParam("descripcion") String descripcion)
	{
		ResponseEntity<?> result = null;
		Boolean respuesta = false;

		if (file != null && !file.isEmpty())
		{
			try
			{
				byte[] bytes = file.getBytes();

				Fotopueblomagico f = new Fotopueblomagico();
				Pueblomagico pm = new Pueblomagico();
				pm = puebloMagicoDAO.findById(idPuebloMagico);

				String nombrePuebloMagico = pm.getNombre();
				nombrePuebloMagico = replaceCharacters(nombrePuebloMagico);

				String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath() + "/img/pueblosMagicos/" + nombrePuebloMagico;
				System.out.println("nombrePM " + nombrePuebloMagico);
				File dir = new File(path);
				if ( !dir.exists() )
				{
					dir.mkdirs();
				}
				
				// Create the file on server
				System.out.println("auto ");
				System.out.println("auto "+  fotopueblomagicoDAO.getAutoIncrementValue());
				System.out.println("nombte "+  file.getOriginalFilename());
				System.out.println("split0 "+ file.getOriginalFilename().split(Pattern.quote("."))[0]);
				System.out.println("split1 "+ file.getOriginalFilename().split(Pattern.quote("."))[1]);
				String nombreFoto = fotopueblomagicoDAO.getAutoIncrementValue() + "." + file.getOriginalFilename().split(Pattern.quote("."))[1];
				System.out.println("nombreFoto "+ nombreFoto);
				File serverFile = new File(dir.getAbsolutePath() + File.separator + nombreFoto);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				
				//Inserta los datos de la imagen en la base de datos
				f.setUrlfoto("/img/pueblosMagicos/" + nombrePuebloMagico + "/" + nombreFoto);
				f.setDescripcion(descripcion);
				f.setPmIdPuebloMagico(pm.getIdPuebloMagico());
				fotopueblomagicoDAO.create(f);
				
				System.out.println("Server File Location=" + serverFile.getAbsolutePath());
				
				respuesta = true;
				result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
			} catch (Exception e)
			{
				result = new ResponseEntity<String>("Error interno You failed to upload",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else
		{
			result = new ResponseEntity<String>("Error interno You failed to upload because the file was empty", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return result;
	}

	// WS que recibe una imagen y el id del pm e inserta la imagen
	@RequestMapping(value = "/atractivoTuristico/{idAtractivoTuristico}/uploadFotoAtractivoTuristico", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
	public ResponseEntity<?> uploadFotoAtractivoTuristico( @PathVariable("idAtractivoTuristico") Integer idAtractivoTuristico,
			@FormParam("file") MultipartFile file,
			@FormParam("descripcion") String descripcion)
	{
		ResponseEntity<?> result = null;
		Boolean respuesta = false;
		log.debug("creating foto, id: " + idAtractivoTuristico);
		if (file != null && !file.isEmpty())
		{
			try
			{
				byte[] bytes = file.getBytes();

				Fotoatractivoturistico f = new Fotoatractivoturistico();
				Atractivoturistico a = new Atractivoturistico();
				
				a = atractivoturisticoDAO.read(idAtractivoTuristico);

				String nombrePuebloMagico = atractivoturisticoDAO.getNombrePuebloMagico(idAtractivoTuristico);
				nombrePuebloMagico = replaceCharacters(nombrePuebloMagico);
				
				String nombreAtractivoTuristico = a.getNombre();
				nombreAtractivoTuristico = replaceCharacters(nombreAtractivoTuristico);

				String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath() + "/img/pueblosMagicos/" + nombrePuebloMagico + "/AtractivosTuristicos/" + nombreAtractivoTuristico;
				
				File dir = new File(path);
				if ( !dir.exists() )
				{
					dir.mkdirs();
				}
				
				// Create the file on server
				String nombreFoto = fotoatractivoturisticoDAO.getAutoIncrementValue() + "." + file.getOriginalFilename().split(Pattern.quote("."))[1];
				File serverFile = new File(dir.getAbsolutePath() + File.separator + nombreFoto);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				
				//Inserta los datos de la imagen en la base de datos
				f.setUrlfoto("/img/pueblosMagicos/" + nombrePuebloMagico + "/AtractivosTuristicos/" + nombreAtractivoTuristico + "/" + nombreFoto);
				f.setDescripcion(descripcion);
				f.setAtIdAtractivoTuristico(a.getIdAtractivoTuristico());
				fotoatractivoturisticoDAO.create(f);
				
				System.out.println("Server File Location=" + serverFile.getAbsolutePath());
				
				respuesta = true;
				result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
			} catch (Exception e)
			{
				result = new ResponseEntity<String>("Error interno You failed to upload",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else
		{
			result = new ResponseEntity<String>("Error interno You failed to upload because the file was empty", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return result;
	}
	
	@RequestMapping(value = "/atractivoTuristico/eliminarFoto/{idfotoAtractivoTuristico}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<?> eliminarFotoAtractivoTuristico( @PathVariable Integer idfotoAtractivoTuristico)
	{
		ResponseEntity<?> result = null;
		Fotoatractivoturistico foto = new Fotoatractivoturistico();
		Boolean respuesta = false;

		if( idfotoAtractivoTuristico == null )
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		
		try
		{
			foto.setIdfotoAtractivoTuristico(idfotoAtractivoTuristico);
			foto.setAtIdAtractivoTuristico(0);
			respuesta = fotoatractivoturisticoDAO.delete(foto);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return result;
	}
	
	
	// WS que recibe una imagen y el id del pm e inserta la imagen
	@RequestMapping(value = "/servicioTuristico/{idServicioTuristico}/uploadFotoServicioTuristico", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
	public ResponseEntity<?> uploadFotoServicioTuristico( @PathVariable("idServicioTuristico") Integer idServicioTuristico,
			@FormParam("file") MultipartFile file,
			@FormParam("descripcion") String descripcion)
	{
		ResponseEntity<?> result = null;
		Boolean respuesta = false;

		if (file != null && !file.isEmpty())
		{
			try
			{
				byte[] bytes = file.getBytes();

				Fotoservicioturistico f = new Fotoservicioturistico();
				Servicioturistico s = new Servicioturistico();
				
				s = servicioturisticoDAO.findById(idServicioTuristico);

				String nombrePuebloMagico = servicioturisticoDAO.getNombrePuebloMagico(idServicioTuristico);
				System.out.println("nomPM " + nombrePuebloMagico); 
				nombrePuebloMagico = replaceCharacters(nombrePuebloMagico);
				System.out.println("nomPM " + nombrePuebloMagico); 
				
				String nombreServicioTuristico = s.getNombre();
				System.out.println("nombreServicioTuristico  " + nombreServicioTuristico); 
				nombreServicioTuristico = replaceCharacters(nombreServicioTuristico);

				String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath() + "/img/pueblosMagicos/" + nombrePuebloMagico + "/ServiciosTuristicos/" + nombreServicioTuristico;
				
				File dir = new File(path);
				if ( !dir.exists() )
				{
					dir.mkdirs();
				}
				
				// Create the file on server
				String nombreFoto = fotoservicioturisticoDAO.getAutoIncrementValue() + "." + file.getOriginalFilename().split(Pattern.quote("."))[1];
				File serverFile = new File(dir.getAbsolutePath() + File.separator + nombreFoto);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				
				//Inserta los datos de la imagen en la base de datos
				f.setUrlfoto("/img/pueblosMagicos/" + nombrePuebloMagico + "/ServiciosTuristicos/" + nombreServicioTuristico + "/" + nombreFoto);
				f.setDescripcion(descripcion);
				f.setStIdServicio(s.getIdServicioTuristico());
				fotoservicioturisticoDAO.create(f);
				
				System.out.println("Server File Location=" + serverFile.getAbsolutePath());
				
				respuesta = true;
				result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
			} catch (Exception e)
			{
				result = new ResponseEntity<String>("Error interno You failed to upload",
						HttpStatus.INTERNAL_SERVER_ERROR);
				e.printStackTrace();
			}
		} else
		{
			result = new ResponseEntity<String>("Error interno You failed to upload because the file was empty", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return result;
	}
	
	@RequestMapping(value = "/servicioTuristico/eliminarFoto/{idfotoServicioTuristico}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<?> eliminarFotoServicioTuristico( @PathVariable Integer idfotoServicioTuristico)
	{
		ResponseEntity<?> result = null;
		Fotoservicioturistico foto = new Fotoservicioturistico();
		Boolean respuesta = false;

		if( idfotoServicioTuristico == null )
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		
		try
		{
			foto.setIdfotoServicioTuristico(idfotoServicioTuristico);
			foto.setStIdServicio(0);
			respuesta = fotoservicioturisticoDAO.delete(foto);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return result;
	}

	// WS que recibe una imagen y el id del pm e inserta la imagen
	@RequestMapping(value = "/servicioEmergencias/{idServicioEmergencias}/uploadFotoServicioEmergencias", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
	public ResponseEntity<?> uploadFotoServicioEmergencias( @PathVariable("idServicioEmergencias") Integer idServicioEmergencias,
			@FormParam("file") MultipartFile file,
			@FormParam("descripcion") String descripcion)
	{
		ResponseEntity<?> result = null;
		Boolean respuesta = false;

		if (file != null && !file.isEmpty())
		{
			try
			{
				byte[] bytes = file.getBytes();

				Fotoservicioturistico f = new Fotoservicioturistico();
				Servicioemergencias s = new Servicioemergencias();
				
				s = servicioemergenciasDAO.read(idServicioEmergencias);

				String nombrePuebloMagico = atractivoturisticoDAO.getNombrePuebloMagico(idServicioEmergencias);
				nombrePuebloMagico = replaceCharacters(nombrePuebloMagico);
				
				String nombreServicioEmergencias = s.getNombre();
				nombreServicioEmergencias = replaceCharacters(nombreServicioEmergencias);

				String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath() + "/img/pueblosMagicos/" + nombrePuebloMagico + "/ServiciosEmergencias/" + nombreServicioEmergencias;
				
				File dir = new File(path);
				if ( !dir.exists() )
				{
					dir.mkdirs();
				}
				
				// Create the file on server
				String nombreFoto = fotoservicioemergenciasDAO.getAutoIncrementValue() + "." + file.getOriginalFilename().split(Pattern.quote("."))[1];
				File serverFile = new File(dir.getAbsolutePath() + File.separator + nombreFoto);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				
				//Inserta los datos de la imagen en la base de datos
				f.setUrlfoto("/img/pueblosMagicos/" + nombrePuebloMagico + "/ServiciosEmergencias/" + nombreServicioEmergencias + "/" + nombreFoto);
				f.setDescripcion(descripcion);
				f.setStIdServicio(s.getIdservicioEmergencias());
				fotoservicioturisticoDAO.create(f);
				
				System.out.println("Server File Location=" + serverFile.getAbsolutePath());
				
				respuesta = true;
				result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
			} catch (Exception e)
			{
				result = new ResponseEntity<String>("Error interno You failed to upload",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else
		{
			result = new ResponseEntity<String>("Error interno You failed to upload because the file was empty", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return result;
	}

	
	// WS que recibe una imagen y el id del pm e inserta la imagen
	@RequestMapping(value = "/puebloMagico/{idPuebloMagico}/uploadFotoUsuario", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
	public ResponseEntity<?> uploadFotoUsuario( @PathVariable("idUsuario") Integer idUsuario,
			@FormParam("file") MultipartFile file)
	{
		ResponseEntity<?> result = null;
		Boolean respuesta = false;

		if (file != null && !file.isEmpty())
		{
			try
			{
				byte[] bytes = file.getBytes();

				Usuario u = new Usuario();
				u = usuarioDAO.findById(idUsuario);


				String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath() + "/img/usuarios/" + u.getIdUsuario();
			
				File dir = new File(path);
				if ( !dir.exists() )
				{
					dir.mkdirs();
				}
				
				// Create the file on server
				
				String nombreFoto = "usuario";
				File serverFile = new File(dir.getAbsolutePath() + File.separator + nombreFoto);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				
				//Inserta los datos de la imagen en la base de datos
				u.setUrlfotografia("/img/usuarios/" + u.getIdUsuario() + nombreFoto);
				
				System.out.println("Server File Location=" + serverFile.getAbsolutePath());
				
				respuesta = true;
				result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
			} catch (Exception e)
			{
				result = new ResponseEntity<String>("Error interno You failed to upload",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else
		{
			result = new ResponseEntity<String>("Error interno You failed to upload because the file was empty", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return result;
	}
	

	
	public static String replaceCharacters( String input )
	{
		// Cadena de caracteres original a sustituir.
		String original = "·‡‰ÈËÎÌÏÔÛÚˆ˙˘uÒ¡¿ƒ…»ÀÕÃœ”“÷⁄Ÿ‹—Á«";
		// Cadena de caracteres ASCII que reemplazar·n los originales.
		String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
		String output = input;
		for (int i = 0; i < original.length(); i++)
		{
			// Reemplazamos los caracteres especiales.
			output = output.replace(original.charAt(i), ascii.charAt(i));
		} // for i

		// quitar espacios
		output = output.replace(" ", "");
		return output;
	}

	public void store( MultipartFile file )
	{
		try
		{
			if (file.isEmpty())
			{
				System.out.println("empty file");
			}
			Path path = Paths.get(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
			System.out.println("path " + path.toString());
			Files.copy(file.getInputStream(), path.resolve(file.getOriginalFilename()));
		} catch (IOException e)
		{
			System.out.println("Failed to store file " + file.getOriginalFilename());
		}
	}
	
	//http://www.journaldev.com/2573/spring-mvc-file-upload-example-single-multiple-files

}
