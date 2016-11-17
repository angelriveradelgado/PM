package controller;

import org.springframework.web.bind.annotation.PathVariable;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import dao.UsuarioDAO;
import dto.Usuario;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.ws.rs.FormParam;

@RestController 
@Component
public class LoginController 
{
	private static final Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired 
	private UsuarioDAO usuarioDAO;
	
	@RequestMapping(value = "/login/{id}", method = RequestMethod.GET,headers="Accept=application/json")  
	 public String getCountryById(@PathVariable Integer id)  
	 {  
	   try
	   {
		   Usuario u = new Usuario();
		   u = usuarioDAO.findById(id);
		   return u.getNombre();
	   }catch(Exception e)
	   {
		   e.printStackTrace();
	   }
	  return null;  
	 }  
	
	
	@RequestMapping(value="/loginService", method = RequestMethod.POST, headers="Accept=application/json")  
	public ResponseEntity<?> UserAuthentication(@FormParam("nombreUsuario")String nombreUsuario,
												@FormParam("contrasena")String contrasena)  
	 {  
		ResponseEntity<?> result = null;
		
		if( nombreUsuario == null )
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			logger.debug("Datos invalidos en login service");
			return result;
		}
		
		Usuario u = new Usuario();
		u.setNombreUsuario(nombreUsuario);
		
		try
		{
			u = usuarioDAO.findByNombreUsuario(u.getNombreUsuario());
			if( u != null )
			{
				if( u.getContrasena().equals(contrasena) )  
				{
					//result = new ResponseEntity<String>("Usuario autenticado", HttpStatus.OK);
					result = new ResponseEntity<Usuario>(u, HttpStatus.OK);
				}else
				{
					// = new ResponseEntity<String>("Usuario no encontrado", HttpStatus.OK);
					System.out.println(u.getContrasena() + " = " + contrasena);
				}
			}else
			{
				System.out.println("es null");
			}
		}catch(Exception e)
		{
			logger.error("Error en login service", e);
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}			  
	    
		return result;  
	 }  

}
