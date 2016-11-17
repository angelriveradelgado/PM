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

import dao.EstablecimientoDAO;
import dao.PstDAO;
import dao.TipovialidadDAO;
import dto.Atractivoturistico;
import dto.Direccion;
import dto.Establecimiento;
import dto.Tipovialidad;

@RestController
@Component
public class EstablecimientoController
{
	@Autowired
	EstablecimientoDAO establecimientoDAO;

	@Autowired
	PstDAO pstDAO;
	
	@Autowired
	TipovialidadDAO tipovialidadDAO;


	// WS que devuelve un establecimiento por su id
	@RequestMapping(value = "/establecimiento/{idEstablecimiento}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getEstablecimiento( @PathVariable Integer idEstablecimiento )
	{
		ResponseEntity<?> result = null;
		Establecimiento e = null;
		try
		{
			e = establecimientoDAO.findById(idEstablecimiento);
			result = new ResponseEntity<Establecimiento>(e, HttpStatus.OK);
		} catch (Exception ex)
		{
			ex.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	// WS que devuelve un establecimiento por su id
	@RequestMapping(value = "/establecimiento/all", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getEstablecimientos( )
	{
		ResponseEntity<?> result = null;
		List<Establecimiento> e = null;
		try
		{
			e = establecimientoDAO.readAll();
			result = new ResponseEntity<List<Establecimiento>>(e, HttpStatus.OK);
		} catch (Exception ex)
		{
			ex.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

	// WS que devuelve los establecimientos por el id del pst al que está asociado
	@RequestMapping(value = "/establecimiento/idpst/{idpst}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getEstablecimientoByidPST( @PathVariable Integer idpst )
	{
		ResponseEntity<?> result = null;
		List<Establecimiento> e = null;
		try
		{
			e = establecimientoDAO.findByIdPST(idpst);
			result = new ResponseEntity<List<Establecimiento>>(e, HttpStatus.OK);
		} catch (Exception ex)
		{
			ex.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	// WS que devuelve los establecimientos por el id del pst al que está asociado
	@RequestMapping(value = "/establecimiento/idEstadoRegistro/{idEstadoRegistro}/limit/{first}/{numReg}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getEstablecimientosByIdEstadoRegistro( @PathVariable Integer idEstadoRegistro,
			 @PathVariable Integer first,  @PathVariable Integer numReg)
	{
		ResponseEntity<?> result = null;
		List<Establecimiento> e = null;
		try
		{
			e = establecimientoDAO.findByIdEstablecimiento(idEstadoRegistro, first, numReg);
			result = new ResponseEntity<List<Establecimiento>>(e, HttpStatus.OK);
		} catch (Exception ex)
		{
			ex.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	
	
	// WS que devuelve un establecimiento por su id
	@RequestMapping(value = "/tiposVialidad", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getTiposVialidad()
	{
		ResponseEntity<?> result = null;
		List<Tipovialidad> t = null;
		try
		{
			t = tipovialidadDAO.readAll();
			result = new ResponseEntity<List<Tipovialidad>>(t, HttpStatus.OK);
		} catch (Exception ex)
		{
			ex.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	// WS que devuelve la lista de calificaciones de un atractivo turistico
	@RequestMapping(value = "/establecimiento/{id}/direccion", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getDireccionIdAtractivoTuristico( @PathVariable Integer id )
	{
		Direccion d = new Direccion();
		String direccion = null;
		ResponseEntity<?> result = null;
		try
		{
			direccion = establecimientoDAO.getDireccion(id);
			d.setDireccion(direccion);
			result = new ResponseEntity<Direccion>(d, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value = "/establecimiento", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> insertEstablecimiento( 
			@FormParam("pstIdUsuario") Integer pstIdUsuario, 
			@FormParam("nombreComercial") String nombreComercial, 
			@FormParam("rfc") String rfc, 
			@FormParam("nombreVialidad") String nombreVialidad,
			@FormParam("numeroExterior") String numeroExterior,	
			@FormParam("numeroInterior") String numeroInterior,
			@FormParam("tvIdTipoVialidad") Integer tvIdTipoVialidad, 			
			@FormParam("longitud") Double longitud,
			@FormParam("latitud") Double latitud,
			@FormParam("AIdAsentamiento") Integer AIdAsentamiento)
	
	{
		ResponseEntity<?> result = null;
		Establecimiento e = new Establecimiento();
		Boolean respuesta = false;

		if( pstIdUsuario == null || tvIdTipoVialidad == null || longitud == null )
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		
		try
		{
			e.setPstIdUsuario(pstIdUsuario);
			e.setNombreComercial(nombreComercial);
			e.setRfc(rfc);
			e.setNombreVialidad(nombreVialidad);
			e.setNumeroExterior(numeroExterior);
			e.setNumeroInterior(numeroInterior);
			e.setTvIdTipoVialidad(tvIdTipoVialidad);
			e.setLongitud(longitud);
			e.setLatitud(latitud);
			e.setAIdAsentamiento(AIdAsentamiento);
			e.setErIdEstadoRegistro(2);
			e.setVIdUsuario(null);

			respuesta = establecimientoDAO.create(e);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}

		return result;
	}
	
	@RequestMapping(value = "/establecimientoEdit", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> updateEstablecimiento( 
			@FormParam("idEstablecimiento") Integer idEstablecimiento, 
			@FormParam("pstIdUsuario") Integer pstIdUsuario, 
			@FormParam("nombreComercial") String nombreComercial, 
			@FormParam("rfc") String rfc, 
			@FormParam("nombreVialidad") String nombreVialidad,
			@FormParam("numeroExterior") String numeroExterior,	
			@FormParam("numeroInterior") String numeroInterior,
			@FormParam("VIdUsuario") Integer VIdUsuario, 
			@FormParam("tvIdTipoVialidad") Integer tvIdTipoVialidad, 			
			@FormParam("longitud") Double longitud,
			@FormParam("latitud") Double latitud,
			@FormParam("AIdAsentamiento") Integer AIdAsentamiento,
			@FormParam("erIdEstadoRegistro") Integer erIdEstadoRegistro)
	
	{
		ResponseEntity<?> result = null;
		Establecimiento e = new Establecimiento();
		Boolean respuesta = false;

		if( idEstablecimiento == null || pstIdUsuario == null || tvIdTipoVialidad == null || longitud == null )
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		
		try
		{
			e.setIdEstablecimiento(idEstablecimiento);
			e.setPstIdUsuario(pstIdUsuario);
			e.setNombreComercial(nombreComercial);
			e.setRfc(rfc);
			e.setNombreVialidad(nombreVialidad);
			e.setNumeroExterior(numeroExterior);
			e.setNumeroInterior(numeroInterior);
			e.setVIdUsuario(VIdUsuario);
			e.setTvIdTipoVialidad(tvIdTipoVialidad);
			e.setLongitud(longitud);
			e.setLatitud(latitud);
			e.setAIdAsentamiento(AIdAsentamiento);
			e.setErIdEstadoRegistro(erIdEstadoRegistro);

			respuesta = establecimientoDAO.update(e);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}

		return result;
	}
	
	@RequestMapping(value = "/establecimiento/{idEstablecimiento}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<?> deleteEstablecimiento( 
			@PathVariable Integer idEstablecimiento)
	
	{
		ResponseEntity<?> result = null;
		Establecimiento e = new Establecimiento();
		Boolean respuesta = false;

		if( idEstablecimiento == null )
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		
		try
		{
			e.setIdEstablecimiento(idEstablecimiento);
			e.setAIdAsentamiento(0);
			e.setErIdEstadoRegistro(0);
			e.setPstIdUsuario(0);
			e.setTvIdTipoVialidad(0);
			respuesta = establecimientoDAO.delete(e);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}

		return result;
	}

}
