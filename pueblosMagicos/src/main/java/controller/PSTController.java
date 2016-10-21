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

import dao.PstDAO;
import dao.TipousuarioDAO;
import dao.UsuarioDAO;
import dto.Pst;
import dto.Usuario;

@RestController
@Component
public class PSTController
{
	@Autowired
	private UsuarioDAO usuarioDAO;
	@Autowired
	private PstDAO pstDAO;
	@Autowired
	private TipousuarioDAO tipoUsuarioDAO;

	// WS que devuelve un PST por su id
	@RequestMapping(value = "/pst/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getPST( @PathVariable int id )
	{
		ResponseEntity<?> result = null;
		Pst p = null;
		try
		{
			p = pstDAO.findById(id);
			result = new ResponseEntity<Pst>(p, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

	// WS que devuelve un PST por su id
	@RequestMapping(value = "/pst/estadoRegistro/{estado}", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<?> getPSTByEstadoRegistro( @PathVariable String estado )
	{
		ResponseEntity<?> result = null;
		List<Pst> p = null;
		try
		{
			p = pstDAO.readAllPSTByEstadoRegistro(estado);
			result = new ResponseEntity<List<Pst>>(p, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

	// WS que recibe un pst y lo inserta en la bd
	@RequestMapping(value = "/pst", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<?> insertPST( @FormParam("nombreUsuario") String nombreUsuario,
			@FormParam("contrasena") String contrasena, @FormParam("nombre") String nombre,
			@FormParam("apellidoPaterno") String apellidoPaterno, @FormParam("apellidoMaterno") String apellidoMaterno,
			@FormParam("correo") String correo, @FormParam("numeroRNT") String numeroRNT,
			@FormParam("telefono") String telefono, @FormParam("razonSocialEmpresa") String razonSocialEmpresa )
	{
		ResponseEntity<?> result = null;
		Usuario u = new Usuario();
		Pst pst = new Pst();
		Boolean respuesta = false;
		System.out.println("ws del pst");
		try
		{
			u.setNombreUsuario(nombreUsuario);
			u.setContrasena(contrasena);
			u.setNombre(nombre);
			u.setApellidoPaterno(apellidoPaterno);
			u.setApellidoMaterno(apellidoMaterno);
			u.setCorreo(correo);
			u.setTipoUsuarioIdtipoUsuario(tipoUsuarioDAO.getId("PST"));

			pst.setNumeroRnt(numeroRNT);
			pst.setTelefono(telefono);
			pst.setRazonSocialEmpresa(razonSocialEmpresa);
			pst.setErIdEstadoRegistro(2);

			respuesta = pstDAO.create(pst, u);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		} catch (Exception e2)
		{
			e2.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return result;
	}

	// WS que recibe un pst y lo inserta en la bd
	@RequestMapping(value = "/pst", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseEntity<?> updatePST( @FormParam("idUsuario") Integer idUsuario,
			@FormParam("nombreUsuario") String nombreUsuario, @FormParam("contrasena") String contrasena,
			@FormParam("nombre") String nombre, @FormParam("apellidoPaterno") String apellidoPaterno,
			@FormParam("apellidoMaterno") String apellidoMaterno, @FormParam("correo") String correo,
			@FormParam("numeroRNT") String numeroRNT, @FormParam("telefono") String telefono,
			@FormParam("razonSocialEmpresa") String razonSocialEmpresa )
	{
		ResponseEntity<?> result = null;
		Usuario u = new Usuario();
		Pst pst = new Pst();
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
			u.setTipoUsuarioIdtipoUsuario(tipoUsuarioDAO.getId("PST"));

			pst.setIdUsuario(idUsuario);
			pst.setNumeroRnt(numeroRNT);
			pst.setTelefono(telefono);
			pst.setRazonSocialEmpresa(razonSocialEmpresa);

			respuesta = pstDAO.update(pst) && usuarioDAO.update(u);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		} catch (Exception e2)
		{
			e2.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return result;
	}

	// WS que recibe un pst y lo inserta en la bd
	@RequestMapping(value = "/pst", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<?> deletePST( @FormParam("idUsuario") Integer idUsuario)
	{
		ResponseEntity<?> result = null;
		Usuario u = new Usuario();
		Pst pst = new Pst();
		Boolean respuesta = false;

		try
		{
			u.setIdUsuario(idUsuario);
			
			pst.setIdUsuario(idUsuario);
			
			respuesta = pstDAO.delete(pst) && usuarioDAO.delete(u);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		} catch (Exception e2)
		{
			e2.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return result;
	}

}
