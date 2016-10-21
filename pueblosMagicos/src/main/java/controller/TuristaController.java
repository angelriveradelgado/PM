package controller;

import java.text.SimpleDateFormat;
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

import dao.GeneroDAO;
import dao.RegistrovisitaDAO;
import dao.TipousuarioDAO;
import dao.TuristaDAO;
import dao.UsuarioDAO;
import dto.Registrovisita;
import dto.Turista;
import dto.Usuario;

@RestController
@Component
public class TuristaController 
{
	@Autowired
	private UsuarioDAO usuarioDAO;
	@Autowired
	private TuristaDAO turistaDAO;
	@Autowired
	private TipousuarioDAO tipoUsuarioDAO;
	@Autowired
	private GeneroDAO generoDAO;
	@Autowired
	private RegistrovisitaDAO registrovisitaDAO;
	
	

	@RequestMapping(value = "/turista/{idUsuario}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getUsuario(@PathVariable int idUsuario) 
	{
		ResponseEntity<?> result = null;
		Usuario u = null;
		try {

			u = usuarioDAO.findById(idUsuario);
			result = new ResponseEntity<Usuario>(u, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value = "/turista", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getUsuarioLogin() 
	{
		ResponseEntity<?> result = null;
		Usuario u = null;
		try {

			u = usuarioDAO.findById(1);
			result = new ResponseEntity<Usuario>(u, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value = "/turista/{idUsuario}/registroVisita", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getRegistroVisitaByIdUsuario(@PathVariable int idUsuario) 
	{
		ResponseEntity<?> result = null;
		List<Registrovisita> r = null;
		try {

			r = registrovisitaDAO.getRegistrosByIdTurista(idUsuario);
			result = new ResponseEntity<List<Registrovisita>>(r, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value = "/turista", method = RequestMethod.POST,  produces="application/json")
	public ResponseEntity<?> insertTurista(	@FormParam("nombreUsuario")String nombreUsuario,
											@FormParam("contrasena")String contrasena,
											@FormParam("nombre")String nombre,
											@FormParam("apellidoPaterno")String apellidoPaterno,
											@FormParam("apellidoMaterno")String apellidoMaterno,
											@FormParam("correo")String correo,
											@FormParam("fechaNacimiento")String fechaNacimiento,
											@FormParam("genero")String genero)
	{
		ResponseEntity<?> result = null;
		Usuario u = new Usuario();		
		Turista t = new Turista();		
		Boolean respuesta = false;
		
		if(nombreUsuario == null || contrasena == null || nombre == null || genero == null || genero.isEmpty())
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		
		try
		{
			u.setNombreUsuario(nombreUsuario);
			u.setContrasena(contrasena);
			u.setNombre(nombre);
			u.setApellidoPaterno(apellidoPaterno);
			u.setApellidoMaterno(apellidoMaterno);
			u.setCorreo(correo);
			u.setTipoUsuarioIdtipoUsuario( tipoUsuarioDAO.getId("Turista") );
			System.out.println("Crear turista dto");
			
			System.out.println("genero " + genero + genero.toUpperCase());
			System.out.println("generoid " + generoDAO.getId(genero.toUpperCase()) );
			if( generoDAO.getId(genero.toUpperCase()) != 0)
			{				
				t.setGIdgenero( generoDAO.getId(genero.toUpperCase()) );	
				System.out.println("obtiene idgenero");
			}else
			{
				System.out.println("regresa error bad request");
				result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
				return result;
			}
			//t.setGIdgenero(1);
			
			if(fechaNacimiento != null && !fechaNacimiento.isEmpty())
			{
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
				if(fechaNacimiento.equals(formatoDelTexto.format(formatoDelTexto.parse(fechaNacimiento))))
				{
					t.setFechaNacimiento( formatoDelTexto.parse(fechaNacimiento) );
					System.out.println("asigna valor fecha nacimiento");
				}else
				{
					System.out.println("formato fecha incorrecto " + fechaNacimiento);
				}
				
			}
			System.out.println("antes de crear turista");
			respuesta = turistaDAO.create(t, u);
			System.out.println("despues de crear turista");
			
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		}catch(Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
	@RequestMapping(value = "/turista", method = RequestMethod.DELETE,  produces="application/json")
	public ResponseEntity<?> deleteUsuario(@FormParam("idUsuario")int idUsuario) 
	{
		ResponseEntity<?> result = null;
		Turista t = new Turista();
		t.setIdUsuario(idUsuario);
		Boolean respuesta = false;
		
		try 
		{
			respuesta = turistaDAO.delete(t);	
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
	
	@RequestMapping(value = "/turista", method = RequestMethod.PUT,  produces="application/json")
	public ResponseEntity<?> updateTurista(	@FormParam("idUsuario") int idUsuario,
											@FormParam("nombreUsuario")String nombreUsuario,
											@FormParam("contrasena")String contrasena,
											@FormParam("nombre")String nombre,
											@FormParam("apellidoPaterno")String apellidoPaterno,
											@FormParam("apellidoMaterno")String apellidoMaterno,
											@FormParam("correo")String correo,
											@FormParam("fechaNacimiento")String fechaNacimiento,
											@FormParam("genero")String genero)
	{
		ResponseEntity<?> result = null;
		Usuario u = new Usuario();		
		Turista t = new Turista();		
		Boolean respuesta = false;
		
		try
		{
			u.setIdUsuario(idUsuario);
			u.setNombreUsuario(nombreUsuario);
			u.setContrasena(contrasena);
			u.setNombre(nombre);
			u.setApellidoPaterno(apellidoPaterno);
			u.setApellidoMaterno(apellidoMaterno);
			u.setCorreo(correo);
			u.setTipoUsuarioIdtipoUsuario( tipoUsuarioDAO.getId("Turista") );
			
			t.setIdUsuario(idUsuario);
			t.setGIdgenero( generoDAO.getId(genero.toUpperCase()) );
			if(fechaNacimiento != null && !fechaNacimiento.isEmpty())
			{
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
				t.setFechaNacimiento( formatoDelTexto.parse(fechaNacimiento) );
			}
			
			
			respuesta = turistaDAO.update(t, u);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		}catch(Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
}
