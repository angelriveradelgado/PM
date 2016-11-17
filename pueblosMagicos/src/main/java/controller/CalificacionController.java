package controller;

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
import dao.PueblomagicoDAO;
import dao.RegistrovisitaDAO;
import dto.Calificacionatractivoturistico;
import dto.Registrovisita;

@RestController 
@Component
public class CalificacionController
{
	@Autowired 
	private CalificacionatractivoturisticoDAO calificacionatractivoturisticoDAO;
	
	@Autowired
	private AtractivoturisticoDAO atractivoturisticoDAO;
	
	@Autowired
	private RegistrovisitaDAO registrovisitaDAO;
	
	@RequestMapping(value = "/calificacionAtractivoTuristico", method = RequestMethod.POST,  produces="application/json")
	public ResponseEntity<?> insertCalificacionAtractivoTuristico(	
											@FormParam("idAtractivo")Integer idAtractivo,
											@FormParam("idUsuario")Integer idUsuario,
											@FormParam("calificacion")Integer calificacion,
											@FormParam("comentario")String comentario,
											@FormParam("idRegistroVisita")Integer idRegistroVisita)
	{
		ResponseEntity<?> result = null;
		Calificacionatractivoturistico c = new Calificacionatractivoturistico();
		Boolean respuesta = false;
		
		if( idAtractivo == null || idUsuario == null || calificacion == null )
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		
		try
		{
			
			Registrovisita r = new 	Registrovisita();			
			r = registrovisitaDAO.read(idRegistroVisita);
			if( r.getEvaluado() == 1 || r.getTIdUsuario()!=idUsuario || r.getAtIdAtractivoTuristico() != idAtractivo)
			{
				result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
				return result;
			}			
			r.setEvaluado(1);
			registrovisitaDAO.update(r);
			c.setAtIdatractivoTuristico(idAtractivo);
			c.setTIdusuario(idUsuario);
			c.setCalificacion(calificacion);
			c.setComentario(comentario);
			
			respuesta = calificacionatractivoturisticoDAO.create(c);
			atractivoturisticoDAO.updatePromedio(idAtractivo);
			
			
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		}catch(Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
	@RequestMapping(value = "/calificacionServicioTuristico/{idCalificacion}", method = RequestMethod.DELETE,  produces="application/json")
	public ResponseEntity<?> deleteCalificacionAtractivoTuristico(	
			@PathVariable Integer idCalificacion)
	{
		ResponseEntity<?> result = null;
		Calificacionatractivoturistico c = new Calificacionatractivoturistico();
		Boolean respuesta = false;
		
		if( idCalificacion == null )
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		
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
