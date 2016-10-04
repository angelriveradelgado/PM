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

import dao.ListaHasAtractivoturisticoDAO;
import dao.ListaHasServicioturisticoDAO;
import dao.UsuarioDAO;
import dto.ListaHasAtractivoturistico;
import dto.ListaHasServicioturistico;
import dto.Pueblomagico;
import dto.Usuario;

@RestController 
@Component
public class UsuarioController
{
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired 
	private ListaHasServicioturisticoDAO listaHasServicioturistico;
	
	@Autowired 
	private ListaHasAtractivoturisticoDAO listaHasAtractivoturisticoDAO;

	@RequestMapping(value = "/usuario/{nombreUsuario}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getPuebloMagico(@PathVariable String nombreUsuario) 
	{
		ResponseEntity<?> result = null;
		Usuario u = null;
		try {
			u = usuarioDAO.findByNombreUsuario(nombreUsuario);
			result = new ResponseEntity<Usuario>(u, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value = "/usuario/id/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getPuebloMagico(@PathVariable int id) 
	{
		ResponseEntity<?> result = null;
		Usuario u = null;
		try {
			u = usuarioDAO.findById(id);
			result = new ResponseEntity<Usuario>(u, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value = "/usuario/{id}/listaServicios", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getListaServicios(@PathVariable int id) 
	{
		ResponseEntity<?> result = null;
		List<ListaHasServicioturistico> lista = null;
		try {
			lista = listaHasServicioturistico.findByIdUsuario(id);
			result = new ResponseEntity<List<ListaHasServicioturistico>>(lista, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value = "/usuario/{id}/listaAtractivos", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getListaAtractivos(@PathVariable int id) 
	{
		ResponseEntity<?> result = null;
		List<ListaHasAtractivoturistico> lista = null;
		try {
			lista = listaHasAtractivoturisticoDAO.findByIdUsuario(id);
			result = new ResponseEntity<List<ListaHasAtractivoturistico>>(lista, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
}
