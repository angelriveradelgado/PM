package controller;

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

import dao.AsentamientoDAO;
import dao.EstadoDAO;
import dao.MunicipioDAO;
import dao.PueblomagicoDAO;
import dto.Asentamiento;
import dto.Estado;
import dto.Municipio;
import dto.Pueblomagico;

@RestController 
@Component
public class EstadoController
{
	@Autowired
	private EstadoDAO estadoDAO;
	@Autowired
	private MunicipioDAO municipioDAO;
	@Autowired
	private AsentamientoDAO asentamientoDAO;
	
	//WS que devuelve una lista de estados que tienen pms
	@RequestMapping(value = "/estados", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getEstados() 
	{
		ResponseEntity<?> result = null;
		List<Estado> e = null;
		try {
			e = estadoDAO.readAll();
			result = new ResponseEntity<List<Estado>>(e, HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	//WS que devuelve una lista de estados que tienen pms
	@RequestMapping(value = "/estados/pm", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getEstadosPM() 
	{
		ResponseEntity<?> result = null;
		List<Estado> e = null;
		try {
			e = estadoDAO.readAllWithPM();
			result = new ResponseEntity<List<Estado>>(e, HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	//WS que devuelve una lista de municipios en un estado
	@RequestMapping(value = "/estados/pm/municipios/idEstado/{idEstado}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getMunicipiosByIdEstado(@PathVariable ("idEstado")Integer idEstado) 
	{
		ResponseEntity<?> result = null;
		List<Municipio> m = null;
		try 
		{
			m = municipioDAO.findByidEstado(idEstado);
			result = new ResponseEntity<List<Municipio>>(m, HttpStatus.OK);
		} catch (Exception ex) 
		{
			ex.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	
	//WS que devuelve una lista de municipios en un estado
	@RequestMapping(value = "/estados/pm/municipiosWithPM/idEstado/{idEstado}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getMunicipiosByIdEstadoWithPM(@PathVariable ("idEstado")Integer idEstado) 
	{
		ResponseEntity<?> result = null;
		List<Municipio> m = null;
		try 
		{
			m = municipioDAO.readAllByIdEstadoWithPM(idEstado);
			result = new ResponseEntity<List<Municipio>>(m, HttpStatus.OK);
		} catch (Exception ex) 
		{
			ex.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	//WS que devuelve una lista de municipios en un estado
	@RequestMapping(value = "/estados/pm/asentamientos/idMunicipio/{idMunicipio}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getAsentamientosByIdMunicipio(@PathVariable ("idMunicipio")Integer idMunicipio) 
	{
		ResponseEntity<?> result = null;
		List<Asentamiento> a = null;
		try 
		{
			a = asentamientoDAO.findByidMunicipio(idMunicipio);
			result = new ResponseEntity<List<Asentamiento>>(a, HttpStatus.OK);
		} catch (Exception ex) 
		{
			ex.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
}
