package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dao.EstadoDAO;
import dao.PueblomagicoDAO;
import dto.Estado;
import dto.Pueblomagico;

@RestController 
@Component
public class EstadoController
{
	@Autowired
	private EstadoDAO estadoDAO;
	
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
}
