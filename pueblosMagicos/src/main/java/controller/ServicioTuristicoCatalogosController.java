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

import dao.FormapagoDAO;
import dao.ServicioadicionalDAO;
import dao.StHasFormapagoDAO;
import dao.StaHasServicioadicionalDAO;
import dao.StaHasTipohabitacionDAO;
import dao.TipoalojamientoDAO;
import dao.TipohabitacionDAO;
import dao.TipooperacionalojamientoDAO;
import dao.TiposervicioalojamientoDAO;
import dao.TiposervicioturisticoDAO;
import dto.Formapago;
import dto.Servicioadicional;
import dto.StHasFormapago;
import dto.StHasFormapagoId;
import dto.StaHasServicioadicional;
import dto.StaHasServicioadicionalId;
import dto.StaHasTipohabitacion;
import dto.StaHasTipohabitacionId;
import dto.Tipoalojamiento;
import dto.Tipohabitacion;
import dto.Tipooperacionalojamiento;
import dto.Tiposervicioalojamiento;
import dto.Tiposervicioturistico;

@RestController
@Component
public class ServicioTuristicoCatalogosController
{
	@Autowired
	TiposervicioturisticoDAO tiposervicioturisticoDAO;
	@Autowired
	StHasFormapagoDAO stHasFormapagoDAO;
	@Autowired
	FormapagoDAO formapagoDAO;
	@Autowired
	TiposervicioalojamientoDAO tiposervicioalojamientoDAO;
	@Autowired
	ServicioadicionalDAO servicioadicionalDAO;
	@Autowired
	StaHasServicioadicionalDAO staHasServicioadicionalDAO;
	@Autowired
	TipohabitacionDAO tipohabitacionDAO;
	@Autowired
	StaHasTipohabitacionDAO staHasTipohabitacionDAO;
	@Autowired
	TipooperacionalojamientoDAO tipooperacionalojamientoDAO;
	@Autowired
	TipoalojamientoDAO tipoalojamientoDAO;
	
	
	@RequestMapping(value = "/servicioTuristico/tiposServiciosTuristicos", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getTiposServiciosTuristicos( )
	{
		ResponseEntity<?> result = null;
		List<Tiposervicioturistico> tst = null;
		try
		{
			tst = tiposervicioturisticoDAO.readAll();
			result = new ResponseEntity<List<Tiposervicioturistico>>(tst, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value = "/servicioTuristico/tipos/idTipoServicioTuristico/{idTipoServicioTuristico}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getTipoServicioTuristicoById( @PathVariable Integer idTipoServicioTuristico )
	{
		ResponseEntity<?> result = null;
		Tiposervicioturistico tst = null;
		try
		{
			tst = tiposervicioturisticoDAO.read(idTipoServicioTuristico);
			result = new ResponseEntity<Tiposervicioturistico>(tst, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value = "/servicioTuristico/tipoPrincipal/{idTipoServicioTuristico}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getTipoPrincipalServicioTuristicoById( @PathVariable Integer idTipoServicioTuristico )
	{
		ResponseEntity<?> result = null;
		String t = null;
		try
		{
			t = tiposervicioturisticoDAO.getTipoPrincipal(idTipoServicioTuristico);
			result = new ResponseEntity<String>(t, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value = "/servicioTuristico/formaPago", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getFormasPago( )
	{
		ResponseEntity<?> result = null;
		List<Formapago> f = null;
		try
		{
			f = formapagoDAO.readAll();
			result = new ResponseEntity<List<Formapago>>(f, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value = "/servicioTuristico/formaPago/idFormaPago/{idFormaPago}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getFormaPagoById( @PathVariable Integer idFormaPago )
	{
		ResponseEntity<?> result = null;
		Formapago f = null;
		try
		{
			f = formapagoDAO.read(idFormaPago);
			result = new ResponseEntity<Formapago>(f, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value = "/servicioTuristico/formaPago", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> setFormaPago(@FormParam("stIdServicio") Integer stIdServicio,
			@FormParam("fpIdformaPago") Integer fpIdformaPago)
	{
		ResponseEntity<?> result = null;
		
		if( stIdServicio == null || fpIdformaPago == null )
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		
		StHasFormapago fp = new StHasFormapago();
		StHasFormapagoId fpId= new StHasFormapagoId();
		Boolean respuesta = false;

		try
		{
			fpId.setFpIdformaPago(fpIdformaPago);
			fpId.setStIdServicio(stIdServicio);
			fp.setId(fpId);
			

			respuesta = stHasFormapagoDAO.create(fp);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return result;
	}
	
	@RequestMapping(value = "/servicioTuristico/formaPago", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<?> unsetFormaPagoFromServicio(@FormParam("stIdServicio") Integer stIdServicio,
			@FormParam("fpIdformaPago") Integer fpIdformaPago)
	{
		ResponseEntity<?> result = null;
		
		if( stIdServicio == null || fpIdformaPago == null )
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		
		StHasFormapago fp = new StHasFormapago();
		StHasFormapagoId fpId= new StHasFormapagoId();
		Boolean respuesta = false;

		try
		{
			fpId.setFpIdformaPago(fpIdformaPago);
			fpId.setStIdServicio(stIdServicio);
			fp.setId(fpId);
			

			respuesta = stHasFormapagoDAO.delete(fp);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return result;
	}
	
	@RequestMapping(value = "/servicioTuristico/tiposServiciosAlojamiento", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getTiposServiciosTuristicosAlojamiento( )
	{
		ResponseEntity<?> result = null;
		List<Tiposervicioalojamiento> tst = null;
		try
		{
			tst = tiposervicioalojamientoDAO.readAll();
			result = new ResponseEntity<List<Tiposervicioalojamiento>>(tst, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value = "/servicioTuristico/tiposAlojamiento/idTipoAlojamiento/{idTipoAlojamiento}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getTiposServicioTuristicoAlojamientoById( @PathVariable Integer idTipoAlojamiento )
	{
		ResponseEntity<?> result = null;
		Tiposervicioalojamiento tst = null;
		try
		{
			tst = tiposervicioalojamientoDAO.read(idTipoAlojamiento);
			result = new ResponseEntity<Tiposervicioalojamiento>(tst, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value = "/servicioTuristico/servicioAdicionalAlojamiento", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getServiciosAdicionalesAlojamiento( )
	{
		ResponseEntity<?> result = null;
		List<Servicioadicional> sa = null;
		try
		{
			sa = servicioadicionalDAO.readAll();
			result = new ResponseEntity<List<Servicioadicional>>(sa, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value = "/servicioTuristico/servicioAdicionalAlojamiento/idServicioAdicionalAlojamiento/{idServicioAdicionalAlojamiento}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getServiciosAdicionalesAlojamientoById( @PathVariable Integer idServicioAdicionalAlojamiento )
	{
		ResponseEntity<?> result = null;
		Servicioadicional sa = null;
		try
		{
			sa = servicioadicionalDAO.read(idServicioAdicionalAlojamiento);
			result = new ResponseEntity<Servicioadicional>(sa, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

	@RequestMapping(value = "/servicioTuristico/servicioAdicionalAlojamiento", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> setServicioAdicionalAlojamiento(@FormParam("saIdservicioAdicional") Integer saIdservicioAdicional,
			@FormParam("staSTIdServicio") Integer staSTIdServicio)
	{
		ResponseEntity<?> result = null;
		
		if( saIdservicioAdicional == null || staSTIdServicio == null )
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		
		StaHasServicioadicional sa = new StaHasServicioadicional();
		StaHasServicioadicionalId saId= new StaHasServicioadicionalId();
		Boolean respuesta = false;

		try
		{
			saId.setSaIdservicioAdicional(saIdservicioAdicional);
			saId.setStaSTIdServicio(staSTIdServicio);
			sa.setId(saId);

			respuesta = staHasServicioadicionalDAO.create(sa);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return result;
	}
	
	@RequestMapping(value = "/servicioTuristico/servicioAdicionalAlojamiento", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<?> unsetServicioAdicionalAlojamiento(@FormParam("saIdservicioAdicional") Integer saIdservicioAdicional,
			@FormParam("staSTIdServicio") Integer staSTIdServicio)
	{
		ResponseEntity<?> result = null;
		
		if( saIdservicioAdicional == null || staSTIdServicio == null )
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		
		StaHasServicioadicional sa = new StaHasServicioadicional();
		StaHasServicioadicionalId saId= new StaHasServicioadicionalId();
		Boolean respuesta = false;

		try
		{
			saId.setSaIdservicioAdicional(saIdservicioAdicional);
			saId.setStaSTIdServicio(staSTIdServicio);
			sa.setId(saId);

			respuesta = staHasServicioadicionalDAO.delete(sa);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return result;
	}
	
	@RequestMapping(value = "/servicioTuristico/tipoHabitacion", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getTipoHabitacionAlojamiento()
	{
		ResponseEntity<?> result = null;
		List<Tipohabitacion> t = null;
		try
		{
			t = tipohabitacionDAO.readAll();
			result = new ResponseEntity<List<Tipohabitacion>>(t, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value = "/servicioTuristico/tipoHabitacion/idTipoHabitacion/{idTipoHabitacion}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getTipoHabitacionAlojamientoById( @PathVariable Integer idTipoHabitacion )
	{
		ResponseEntity<?> result = null;
		Tipohabitacion t = null;
		try
		{
			t = tipohabitacionDAO.read(idTipoHabitacion);
			result = new ResponseEntity<Tipohabitacion>(t, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

	@RequestMapping(value = "/servicioTuristico/tipoHabitacion", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> setTipoHabitacionAlojamiento(@FormParam("staSTIdServicio") Integer staSTIdServicio,
			@FormParam("IdTipoHabitacion") Integer IdTipoHabitacion)
	{
		ResponseEntity<?> result = null;
		
		if( staSTIdServicio == null || IdTipoHabitacion == null )
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		
		StaHasTipohabitacion th = new StaHasTipohabitacion();
		StaHasTipohabitacionId thId= new StaHasTipohabitacionId();
		Boolean respuesta = false;

		try
		{
			thId.setStaSTIdServicio(staSTIdServicio);
			thId.setThIdtH(IdTipoHabitacion);
			th.setId(thId);

			respuesta = staHasTipohabitacionDAO.create(th);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return result;
	}
	
	@RequestMapping(value = "/servicioTuristico/tipoHabitacion", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<?> unsetTipoHabitacionAlojamiento(@FormParam("staSTIdServicio") Integer staSTIdServicio,
			@FormParam("staSTIdServicio") Integer thIdtH)
	{
		ResponseEntity<?> result = null;
		
		if( staSTIdServicio == null || thIdtH == null )
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		
		StaHasTipohabitacion th = new StaHasTipohabitacion();
		StaHasTipohabitacionId thId= new StaHasTipohabitacionId();
		Boolean respuesta = false;

		try
		{
			thId.setStaSTIdServicio(staSTIdServicio);
			thId.setThIdtH(thIdtH);
			th.setId(thId);

			respuesta = staHasTipohabitacionDAO.delete(th);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return result;
	}
	
	@RequestMapping(value = "/servicioTuristico/tipoOperacionAlojamiento", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getTipoOperacionAlojamiento( )
	{
		ResponseEntity<?> result = null;
		List<Tipooperacionalojamiento> t = null;
		try
		{
			t = tipooperacionalojamientoDAO.readAll();
			result = new ResponseEntity<List<Tipooperacionalojamiento>>(t, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value = "/servicioTuristico/tipoOperacionAlojamiento/idTipoOperacionAlojamiento/{idTipoOperacionAlojamiento}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getTipoOperacionAlojamientoById( @PathVariable Integer idTipoOperacionAlojamiento )
	{
		ResponseEntity<?> result = null;
		Tipooperacionalojamiento t = null;
		try
		{
			t = tipooperacionalojamientoDAO.read(idTipoOperacionAlojamiento);
			result = new ResponseEntity<Tipooperacionalojamiento>(t, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

	@RequestMapping(value = "/servicioTuristico/tiposAlojamiento", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getTiposAlojamiento( )
	{
		ResponseEntity<?> result = null;
		List<Tipoalojamiento> t = null;
		try
		{
			t = tipoalojamientoDAO.readAll();
			result = new ResponseEntity<List<Tipoalojamiento>>(t, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	

	

}
