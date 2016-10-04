package controller;

import org.springframework.web.bind.annotation.PathVariable;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import dao.UsuarioDAO;
import dto.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.ws.rs.FormParam;

@RestController 
@Component
public class loginController 
{
	
	@Autowired 
	private UsuarioDAO usuarioDAO;
	
	@RequestMapping(value = "/login/{id}", method = RequestMethod.GET,headers="Accept=application/json")  
	 public String getCountryById(@PathVariable int id)  
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
	
	
	@RequestMapping(value="/login", method = RequestMethod.POST, headers="Accept=application/json")  
	public ResponseEntity<?> UserAuthentication(@FormParam("nombreUsuario")String nombreUsuario,
												@FormParam("contrasena")String contrasena)  
	 {  
		ResponseEntity<?> result = null;
		Usuario u = new Usuario();
		u.setNombreUsuario(nombreUsuario);
		
		try
		{
			u = usuarioDAO.findByNombreUsuario(u.getNombreUsuario());
			if( u.getContrasena().equals(contrasena) )  
			{
				result = new ResponseEntity<String>("Usuario autenticado", HttpStatus.OK);
			}else
			{
				result = new ResponseEntity<String>("Usuario no encontrado", HttpStatus.OK);
				System.out.println(u.getContrasena() + " = " + contrasena);
			}
		}catch(Exception e)
		{
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}			  
	    
		return result;  
	 }  

}
