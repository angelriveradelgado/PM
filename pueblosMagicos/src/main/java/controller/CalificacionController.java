package controller;

import javax.ws.rs.FormParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dao.CalificacionatractivoturisticoDAO;
import dto.Calificacionatractivoturistico;

@RestController 
@Component
public class CalificacionController
{
	@Autowired 
	private CalificacionatractivoturisticoDAO calificacionatractivoturisticoDAO;
	
	@RequestMapping(value = "/calificacionAtractivoTuristico", method = RequestMethod.POST,  produces="application/json")
	public ResponseEntity<?> insertListaAtractivoTuristico(	
											@FormParam("idAtractivo")Integer idAtractivo,
											@FormParam("idUsuario")Integer idUsuario,
											@FormParam("calificacion")Integer calificacion,
											@FormParam("comentario")String comentario)
	{
		ResponseEntity<?> result = null;
		Calificacionatractivoturistico c = new Calificacionatractivoturistico();
		Boolean respuesta = false;
		
		try
		{
			c.setAtIdatractivoTuristico(idAtractivo);
			c.setTIdusuario(idUsuario);
			c.setCalificacion(calificacion);
			c.setComentario(comentario);
			
			
			
			respuesta = calificacionatractivoturisticoDAO.create(c);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		}catch(Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
	@RequestMapping(value = "/calificacionServicioTuristico", method = RequestMethod.DELETE,  produces="application/json")
	public ResponseEntity<?> deleteListaAtractivoTuristico(	
											@FormParam("idCalificacion")Integer idCalificacion)
	{
		ResponseEntity<?> result = null;
		Calificacionatractivoturistico c = new Calificacionatractivoturistico();
		Boolean respuesta = false;
		
		try
		{
			c.setIdCalificacionAtractivoTuristico(idCalificacion);
			
			
			
			respuesta = calificacionatractivoturisticoDAO.delete(c);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		}catch(Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
}
