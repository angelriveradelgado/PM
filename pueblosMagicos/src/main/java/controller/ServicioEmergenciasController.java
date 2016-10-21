package controller;

import java.util.Date;
import java.util.List;

import javax.ws.rs.FormParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dao.ServicioemergenciasDAO;
import dto.Servicioemergencias;

@RestController
@Component
public class ServicioEmergenciasController
{
	@Autowired
	private ServicioemergenciasDAO servicioemergenciasDAO;
	
	// WS que devuelve un servicio turistico por su id
	@RequestMapping(value = "/servicioEmergencias/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getServicioEmergencias( @PathVariable int id )
	{
		ResponseEntity<?> result = null;
		Servicioemergencias st = null;
		try
		{
			st = servicioemergenciasDAO.findById(id);
			result = new ResponseEntity<Servicioemergencias>(st, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	// WS que devuelve una lista con todos los ST de un PM. Recibe el id del PM
	@RequestMapping(value = "/servicioEmergencias/puebloMagico/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getAllPueblosMagicosByEstado( @PathVariable int id )
	{
		List<Servicioemergencias> se = null;
		ResponseEntity<?> result = null;
		try
		{
			se = servicioemergenciasDAO.findByIdPuebloMagico(id);
			result = new ResponseEntity<List<Servicioemergencias>>(se, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	
	
	@RequestMapping(value = "/servicioEmergencias", method = RequestMethod.POST, headers = "Accept=application/json")
	public Boolean insertAtractivoTuristico( 
			@FormParam("nombre") String nombre, 
			@FormParam("aIdAsentamiento") Integer aIdAsentamiento, 
			@FormParam("longitud") double longitud,
			@FormParam("latitud") double latitud,	
			@FormParam("descripcion") String descripcion,
			@FormParam("horaInicio") Date horaInicio, 			
			@FormParam("horaFin") Date horaFin)
	{
		Servicioemergencias se = new Servicioemergencias();
		Boolean respuesta = false;

		try
		{
			se.setNombre(nombre);
			se.setAIdAsentamiento(aIdAsentamiento);
			se.setLongitud(longitud);
			se.setLatitud(latitud);
			se.setDescripcion(descripcion);
			se.setHoraInicio(horaInicio);
			se.setHoraFin(horaFin);

			respuesta = servicioemergenciasDAO.create(se);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return respuesta;
	}
	
	@RequestMapping(value = "/servicioEmergencias", method = RequestMethod.PUT, headers = "Accept=application/json")
	public Boolean updateAtractivoTuristico( 
			@FormParam("idservicioEmergencias") Integer idservicioEmergencias, 
			@FormParam("nombre") String nombre, 
			@FormParam("aIdAsentamiento") Integer aIdAsentamiento, 
			@FormParam("longitud") double longitud,
			@FormParam("latitud") double latitud,	
			@FormParam("descripcion") String descripcion,
			@FormParam("horaInicio") Date horaInicio, 			
			@FormParam("horaFin") Date horaFin)
	{
		Servicioemergencias se = new Servicioemergencias();
		Boolean respuesta = false;

		try
		{
			se.setIdservicioEmergencias(idservicioEmergencias);
			se.setNombre(nombre);
			se.setAIdAsentamiento(aIdAsentamiento);
			se.setLongitud(longitud);
			se.setLatitud(latitud);
			se.setDescripcion(descripcion);
			se.setHoraInicio(horaInicio);
			se.setHoraFin(horaFin);

			respuesta = servicioemergenciasDAO.update(se);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return respuesta;
	}
	
	@RequestMapping(value = "/servicioEmergencias", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public Boolean deleteAtractivoTuristico( 
			@FormParam("idservicioEmergencias") Integer idservicioEmergencias)
	{
		Servicioemergencias se = new Servicioemergencias();
		Boolean respuesta = false;

		try
		{
			se.setIdservicioEmergencias(idservicioEmergencias);

			respuesta = servicioemergenciasDAO.delete(se);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return respuesta;
	}
	
	
}
