package controller;

import java.text.SimpleDateFormat;

import javax.ws.rs.FormParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dao.ListaHasAtractivoturisticoDAO;
import dao.ListaHasServicioturisticoDAO;
import dto.ListaHasAtractivoturistico;
import dto.ListaHasAtractivoturisticoId;
import dto.ListaHasServicioturistico;
import dto.ListaHasServicioturisticoId;
import dto.Turista;
import dto.Usuario;

@RestController 
@Component
public class ListaController
{
	@Autowired 
	private ListaHasServicioturisticoDAO listaHasServicioturistico;
	
	@Autowired 
	private ListaHasAtractivoturisticoDAO listaHasAtractivoturisticoDAO;
	
	@RequestMapping(value = "/listaAtractivoTuristico", method = RequestMethod.POST,  produces="application/json")
	public ResponseEntity<?> insertListaAtractivoTuristico(	
											@FormParam("idAtractivo")Integer idAtractivo,
											@FormParam("idUsuario")Integer idUsuario)
	{
		ResponseEntity<?> result = null;
		ListaHasAtractivoturisticoId lId = new ListaHasAtractivoturisticoId();
		ListaHasAtractivoturistico l = new	ListaHasAtractivoturistico();
		Boolean respuesta = false;
		
		try
		{
			lId.setAtractivoTuristicoIdAtractivoTuristico(idAtractivo);
			lId.setTuristaIdUsuario(idUsuario);
			l.setId(lId);
			
			
			respuesta = listaHasAtractivoturisticoDAO.create(l);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		}catch(Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
	@RequestMapping(value = "/listaAtractivoTuristico", method = RequestMethod.DELETE,  produces="application/json")
	public ResponseEntity<?> deleteListaAtractivoTuristico(	
											@FormParam("idAtractivo")Integer idAtractivo,
											@FormParam("idUsuario")Integer idUsuario)
	{
		ResponseEntity<?> result = null;
		ListaHasAtractivoturisticoId lId = new ListaHasAtractivoturisticoId();
		ListaHasAtractivoturistico l = new	ListaHasAtractivoturistico();
		Boolean respuesta = false;
		
		try
		{
			lId.setAtractivoTuristicoIdAtractivoTuristico(idAtractivo);
			lId.setTuristaIdUsuario(idUsuario);
			l.setId(lId);
			
			
			respuesta = listaHasAtractivoturisticoDAO.delete(l);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		}catch(Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
	@RequestMapping(value = "/listaServicioTuristico", method = RequestMethod.POST,  produces="application/json")
	public ResponseEntity<?> insertListaServicioTuristico(	
											@FormParam("idServicio")Integer idServicio,
											@FormParam("idUsuario")Integer idUsuario)
	{
		ResponseEntity<?> result = null;
		ListaHasServicioturisticoId lId = new ListaHasServicioturisticoId();
		ListaHasServicioturistico l = new	ListaHasServicioturistico();
		Boolean respuesta = false;
		
		try
		{
			lId.setServicioTuristicoIdServicio(idServicio);
			lId.setTuristaIdUsuario(idUsuario);
			l.setId(lId);
			
			
			respuesta = listaHasServicioturistico.create(l);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		}catch(Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
	@RequestMapping(value = "/listaServicioTuristico", method = RequestMethod.DELETE,  produces="application/json")
	public ResponseEntity<?> deleteListaServicioTuristico(	
											@FormParam("idServicio")Integer idServicio,
											@FormParam("idUsuario")Integer idUsuario)
	{
		ResponseEntity<?> result = null;
		ListaHasServicioturisticoId lId = new ListaHasServicioturisticoId();
		ListaHasServicioturistico l = new	ListaHasServicioturistico();
		Boolean respuesta = false;
		
		try
		{
			lId.setServicioTuristicoIdServicio(idServicio);
			lId.setTuristaIdUsuario(idUsuario);
			l.setId(lId);
			
			
			respuesta = listaHasServicioturistico.delete(l);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		}catch(Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
}
