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
import dto.Atractivoturistico;
import dto.Establecimiento;

@RestController
@Component
public class EstablecimientoController
{
	@Autowired
	EstablecimientoDAO establecimientoDAO;

	@Autowired
	PstDAO pstDAO;

	// WS que devuelve un establecimiento por su id
	@RequestMapping(value = "/establecimiento/{idEstablecimiento}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getEstablecimiento( @PathVariable int idEstablecimiento )
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

	// WS que devuelve los establecimientos por el id del pst al que está asociado
	@RequestMapping(value = "/establecimiento/idpst/{idpst}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getEstablecimientoByidPST( @PathVariable int idpst )
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
	
	
	
	
	
	@RequestMapping(value = "/establecimiento", method = RequestMethod.POST, headers = "Accept=application/json")
	public Boolean insertEstablecimiento( 
			@FormParam("pstIdUsuario") int pstIdUsuario, 
			@FormParam("nombreComercial") String nombreComercial, 
			@FormParam("rfc") String rfc, 
			@FormParam("nombreVialidad") String nombreVialidad,
			@FormParam("numeroExterior") String numeroExterior,	
			@FormParam("numeroInterior") String numeroInterior,
			@FormParam("tvIdTipoVialidad") int tvIdTipoVialidad, 			
			@FormParam("longitud") double longitud,
			@FormParam("latitud") double latitud,
			@FormParam("AIdAsentamiento") int AIdAsentamiento)
	
	{
		Establecimiento e = new Establecimiento();
		Boolean respuesta = false;

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
			e.setLalitud(latitud);
			e.setAIdAsentamiento(AIdAsentamiento);
			e.setErIdEstadoRegistro(2);

			respuesta = establecimientoDAO.create(e);
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}

		return respuesta;
	}
	
	@RequestMapping(value = "/establecimiento", method = RequestMethod.PUT, headers = "Accept=application/json")
	public Boolean updateEstablecimiento( 
			@FormParam("idEstablecimiento") int idEstablecimiento, 
			@FormParam("pstIdUsuario") int pstIdUsuario, 
			@FormParam("nombreComercial") String nombreComercial, 
			@FormParam("rfc") String rfc, 
			@FormParam("nombreVialidad") String nombreVialidad,
			@FormParam("numeroExterior") String numeroExterior,	
			@FormParam("numeroInterior") String numeroInterior,
			@FormParam("VIdUsuario") int VIdUsuario, 
			@FormParam("tvIdTipoVialidad") int tvIdTipoVialidad, 			
			@FormParam("longitud") double longitud,
			@FormParam("latitud") double latitud,
			@FormParam("AIdAsentamiento") int AIdAsentamiento,
			@FormParam("erIdEstadoRegistro") int erIdEstadoRegistro)
	
	{
		Establecimiento e = new Establecimiento();
		Boolean respuesta = false;

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
			e.setLalitud(latitud);
			e.setAIdAsentamiento(AIdAsentamiento);
			e.setErIdEstadoRegistro(erIdEstadoRegistro);

			respuesta = establecimientoDAO.update(e);
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}

		return respuesta;
	}
	
	@RequestMapping(value = "/establecimiento", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public Boolean deleteEstablecimiento( 
			@FormParam("idEstablecimiento") int idEstablecimiento)
	
	{
		Establecimiento e = new Establecimiento();
		Boolean respuesta = false;

		try
		{
			e.setIdEstablecimiento(idEstablecimiento);

			respuesta = establecimientoDAO.update(e);
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}

		return respuesta;
	}

}
