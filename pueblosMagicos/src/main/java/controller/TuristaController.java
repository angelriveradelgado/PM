package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.FormParam;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import common.Mail;
import dao.CalificacionatractivoturisticoDAO;
import dao.CalificacionpueblomagicoDAO;
import dao.EvaluacionservicioturisticoDAO;
import dao.GeneroDAO;
import dao.ListaHasAtractivoturisticoDAO;
import dao.ListaHasServicioturisticoDAO;
import dao.RegistrovisitaDAO;
import dao.TipousuarioDAO;
import dao.TuristaDAO;
import dao.UsuarioDAO;
import dto.Calificacionatractivoturistico;
import dto.Calificacionpueblomagico;
import dto.Evaluacionservicioturistico;
import dto.ListaHasAtractivoturistico;
import dto.ListaHasAtractivoturisticoId;
import dto.ListaHasServicioturistico;
import dto.ListaHasServicioturisticoId;
import dto.Registrovisita;
import dto.Turista;
import dto.Usuario;

@RestController
@Component
public class TuristaController 
{
	private static final Log log = LogFactory.getLog(TuristaController.class);
	
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
	@Autowired
	private ListaHasAtractivoturisticoDAO listaHasAtractivoturisticoDAO;
	@Autowired
	private ListaHasServicioturisticoDAO listaHasServicioturisticoDAO;
	@Autowired 
	private CalificacionatractivoturisticoDAO calificacionatractivoturisticoDAO;
	@Autowired 
	private CalificacionpueblomagicoDAO calificacionpueblomagicoDAO;
	@Autowired 
	private EvaluacionservicioturisticoDAO evaluacionservicioturisticoDAO;

	@RequestMapping(value = "/turista/{idUsuario}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getUsuario(@PathVariable Integer idUsuario) 
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
	public ResponseEntity<?> getRegistrosVisitaByIdUsuario(@PathVariable Integer idUsuario) 
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
	
	@RequestMapping(value = "/turista/{idUsuario}/registroVisitaAtractivoTuristico/{idAtractivoTuristico}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getRegistrosVisitaByIdUsuarioByIdAtractivoTuristico(@PathVariable Integer idUsuario,
			@PathVariable Integer idAtractivoTuristico) 
	{
		ResponseEntity<?> result = null;
		List<Registrovisita> r = null;
		try {

			r = registrovisitaDAO.getRegistrosByIdTuristaByIdAtractivoTuristico(idUsuario, idAtractivoTuristico);
			result = new ResponseEntity<List<Registrovisita>>(r, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	// WS que devuelve la lista de calificaciones de un atractivo turistico
	@RequestMapping(value = "/turista/{idUsuario}/calificacionAtractivoTuristico/{idAtractivoTuristico}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getCalificacionByIdUsuarioByIdAtractivoTuristico( @PathVariable Integer idUsuario, @PathVariable Integer idAtractivoTuristico )
	{
		List<Calificacionatractivoturistico> c = null;
		ResponseEntity<?> result = null;
		try
		{
			c = calificacionatractivoturisticoDAO.findByIdUsuarioByIdAtractivoTuristico(idUsuario, idAtractivoTuristico);
			result = new ResponseEntity<List<Calificacionatractivoturistico>>(c, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value = "/turista/{idUsuario}/registroVisitaPuebloMagico/{idPuebloMagico}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getRegistrosVisitaByIdUsuarioByIdPuebloMagico(@PathVariable Integer idUsuario,
			@PathVariable Integer idPuebloMagico) 
	{
		ResponseEntity<?> result = null;
		List<Registrovisita> r = null;
		try {

			r = registrovisitaDAO.getRegistrosByIdTuristaByIdPuebloMagico(idUsuario, idPuebloMagico);
			result = new ResponseEntity<List<Registrovisita>>(r, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	// WS que devuelve la lista de calificaciones de un atractivo turistico
	@RequestMapping(value = "/turista/{idUsuario}/calificacionPuebloMagico/{idPuebloMagico}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getCalificacionByIdUsuarioByIdPuebloMagico( @PathVariable Integer idUsuario, @PathVariable Integer idPuebloMagico )
	{
		List<Calificacionpueblomagico> c = null;
		ResponseEntity<?> result = null;
		try
		{
			c = calificacionpueblomagicoDAO.findByIdUsuarioByIdPuebloMagico(idUsuario, idPuebloMagico);
			result = new ResponseEntity<List<Calificacionpueblomagico>>(c, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value = "/turista/{idUsuario}/registroVisitaServicioTuristico/{idServicioTuristico}/{idEstablecimiento}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getRegistrosVisitaByIdUsuarioByIdServicioTuristico(@PathVariable Integer idUsuario,
			@PathVariable Integer idServicioTuristico,
			@PathVariable Integer idEstablecimiento) 
	{
		ResponseEntity<?> result = null;
		List<Registrovisita> r = null;
		try {

			r = registrovisitaDAO.getRegistrosByIdTuristaByIdEstablecimientoByIdServicioTuristico(idUsuario, idEstablecimiento, idServicioTuristico);
			result = new ResponseEntity<List<Registrovisita>>(r, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	// WS que devuelve la lista de calificaciones de un atractivo turistico
	@RequestMapping(value = "/turista/{idUsuario}/evaluacionServicioTuristico/{idServicioTuristico}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getEvaluacionByIdUsuarioByIdServicioTuristico( @PathVariable Integer idUsuario, @PathVariable Integer idServicioTuristico )
	{
		List<Evaluacionservicioturistico> c = null;
		ResponseEntity<?> result = null;
		try
		{
			c = evaluacionservicioturisticoDAO.findByIdUsuarioByIdServicioTuristico(idUsuario, idServicioTuristico);
			result = new ResponseEntity<List<Evaluacionservicioturistico>>(c, HttpStatus.OK);
		} catch (Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value = "/turista/{idUsuario}/registroVisitaNoEvaluados", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getRegistrosVisitaNoEvaluadosByIdUsuario(@PathVariable Integer idUsuario) 
	{
		ResponseEntity<?> result = null;
		List<Registrovisita> r = null;
		try {

			r = registrovisitaDAO.getRegistrosNoEvaluadosByIdTurista(idUsuario);
			result = new ResponseEntity<List<Registrovisita>>(r, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value = "/turista/{idUsuario}/registroVisitaEvaluados", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getRegistrosVisitaEvaluadosByIdUsuario(@PathVariable Integer idUsuario) 
	{
		ResponseEntity<?> result = null;
		List<Registrovisita> r = null;
		try {

			r = registrovisitaDAO.getRegistrosEvaluadosByIdTurista(idUsuario);
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
			
			Mail mail = new Mail(); 
			String message = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\"><head>  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />  <title>[SUBJECT]</title>  <style type=\"text/css\">  body {   padding-top: 0 !important;   padding-bottom: 0 !important;   padding-top: 0 !important;   padding-bottom: 0 !important;   margin:0 !important;   width: 100% !important;   -webkit-text-size-adjust: 100% !important;   -ms-text-size-adjust: 100% !important;   -webkit-font-smoothing: antialiased !important; } .tableContent img {   border: 0 !important;   display: block !important;   outline: none !important; } a{  color:#382F2E;}p, h1,h2,h3,ul,ol,li,div{  margin:0;  padding:0;}h1,h2{  font-weight: normal;  background:transparent !important;  border:none !important;}.contentEditable h2.big{  font-size: 30px !important;} .contentEditable h2.bigger{  font-size: 37px !important;}td,table{  vertical-align: top;}td.middle{  vertical-align: middle;}a.link1{  font-size:14px;  color:#D4D4D4;  text-decoration:none;  font-family: Helvetica Neue;}.link2{font-size:16px;color:#d2176e;text-decoration:none;line-height:24px;font-family: Helvetica;font-weight: bold;}.link3{padding:5px 10px;border-radius: 6px;background-color: #d2176e;font-size:13px;color:#f2f2f2;text-decoration:none;line-height:26px;font-family: Helvetica;font-weight: bold;}.contentEditable li{  margin-top:10px;  margin-bottom:10px;  list-style: none;  color:#ffffff;  text-align:center;  font-size:13px;  line-height:19px;}.appart p{  font-size:13px;  line-height:19px;  color:#aaaaaa !important;}h2{  color:#555555;  font-weight: normal;  font-size:28px;  color:#555555;  font-family: Georgia;  line-height: 28px;  font-style: italic;}.bgItem{background:#f2f2f2;}.bgBody{background:#ffffff;}@media only screen and (max-width:480px)		{		table[class=\"MainContainer\"], td[class=\"cell\"] 	{		width: 100% !important;		height:auto !important; 	}td[class=\"specbundle\"] 	{		width: 80% !important;		float:left !important;		font-size:13px !important;		line-height:17px !important;		display:block !important;		padding-bottom:15px !important;		text-align:center !important;		padding-left:20% !important;	}td[class=\"specbundle2\"] 	{		width:85% !important;		float:left !important;		font-size:13px !important;		line-height:17px !important;		display:block !important;		padding-left:10% !important;		padding-right:5% !important;		padding-bottom:10px !important;	}		td[class=\"specbundle3\"] 	{		width:20px !important;		float:left !important;		display:block !important;		background-color:#f2f2f2 !important;	}	td[class=\"specbundle4\"] 	{		width:90% !important;		float:left !important;		font-size:13px !important;		line-height:17px !important;		display:block !important;		padding-left:10% !important;		padding-right:5% !important;		padding-left:5% !important;	}		td[class=\"spechide\"] 	{		display:none !important;	}	    img[class=\"banner\"] 	{	          width: 100% !important;	          height: auto !important;	}		td[class=\"left_pad\"] 	{			padding-left:15px !important;			padding-right:15px !important;	}		 }	@media only screen and (max-width:540px) {		table[class=\"MainContainer\"], td[class=\"cell\"] 	{		width: 100% !important;		height:auto !important; 	}td[class=\"specbundle\"] 	{		width: 80% !important;		float:left !important;		font-size:13px !important;		line-height:17px !important;		display:block !important;		padding-bottom:15px !important;		text-align:center !important;		padding-left:20% !important;	}	td[class=\"specbundle2\"] 	{		width:85% !important;		float:left !important;		font-size:13px !important;		line-height:17px !important;		display:block !important;		padding-left:10% !important;		padding-right:5% !important;		padding-bottom:10px !important;	}		td[class=\"specbundle3\"] 	{		width:20px !important;		float:left !important;		display:block !important;		background-color:#f2f2f2 !important;	}	td[class=\"specbundle4\"] 	{		width:90% !important;		float:left !important;		font-size:13px !important;		line-height:17px !important;		display:block !important;		padding-left:10% !important;		padding-right:5% !important;		padding-left:5% !important;	}		td[class=\"spechide\"] 	{		display:none !important;	}	    img[class=\"banner\"] 	{	          width: 100% !important;	          height: auto !important;	}		td[class=\"left_pad\"] 	{			padding-left:15px !important;			padding-right:15px !important;	}	.font{		font-size:15px !important;		line-height:19px !important;		}		}</style><script type=\"colorScheme\" class=\"swatch active\">  {    \"name\":\"Default\",    \"bgBody\":\"ffffff\",    \"link\":\"d2176e\",    \"color\":\"555555\",    \"bgItem\":\"f2f2f2\",    \"title\":\"555555\"  }</script></head><body paddingwidth=\"0\" paddingheight=\"0\" bgcolor=\"#d1d3d4\"  style=\"padding-top: 0; padding-bottom: 0; padding-top: 0; padding-bottom: 0; background-repeat: repeat; width: 100% !important; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; -webkit-font-smoothing: antialiased;\" offset=\"0\" toppadding=\"0\" leftpadding=\"0\">	<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"tableContent bgBody\" align=\"center\" bgcolor=\"#FFFFFF\" style='font-family:Georgia, serif;'>    <!-- =============== START HEADER =============== -->  <tbody>    <tr>      <td><table width=\"560\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" class=\"MainContainer\">      <!-- =============== END HEADER =============== -->          <!-- =============== START BODY =============== -->  <tbody>    <tr>      <td class='movableContentContainer'>      	<div class=\"movableContent\" style=\"border: 0px; padding-top: 0px; position: relative;\">        <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">  <tbody>    <tr>      <td height=\"10\"></td>    </tr>    <tr>      <td><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">  <tbody>    <tr>      <td valign=\"top\" class=\"specbundle4\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">  <tbody>    <tr>      <td valign=\"top\" width=\"89\" class=\"specbundle4\" align=\"center\"><img src=\"http://148.204.86.18:8080/pueblosMagicos/img/icons/pm.jpg\" alt=\"Logo\" title=\"Logo\" width=\"89\" height=\"88\" data-max-width=\"100\"></td>      <td width=\"40\" valign=\"top\" class=\"spechide\">&nbsp;</td>      <td valign=\"middle\" class=\"specbundle4\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">  <tbody>    <tr>      <td height=\"25\" class=\"spechide\"></td>    </tr>    <tr>      <td class=\"font\"><div class=\"contentEditableContainer contentTextEditable\">                          <div class=\"contentEditable\" align='left' style='color:#555555;font-size:18px; padding-top:5px;'>                            <h1 style='line-height: 22px;font-size:18px;'>                              TT2015-B100                            </h1>                          </div>                        </div></td>    </tr>    <tr>      <td>&nbsp;</td>    </tr>  </tbody></table></td>    </tr>  </tbody></table></td>      <td valign=\"top\" width=\"20\" class=\"spechide\">&nbsp;</td>      <td valign=\"middle\" style='vertical-align: middle;' width='150' class=\"left_pad\">                          <div class='contentEditableContainer contentTextEditable'>                            <div class='contentEditable' style='text-align: right;'>                              <a target='_blank' href=\"http://148.204.86.18:8080/pueblosMagicos/\" class='link1' >Sitio Web</a>                            </div>                          </div>                        </td>    </tr>  </tbody></table></td>    </tr>    <tr>      <td height=\"10\"></td>    </tr>  </tbody></table>        </div>        <div class=\"movableContent\" style=\"border: 0px; padding-top: 0px; position: relative;\">        <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">                    <tr>                      <td>                        <div class=\"contentEditableContainer contentImageEditable\">                          <div class=\"contentEditable\" align='left'>                            <img class=\"banner\" src=\"http://148.204.86.18:8080/pueblosMagicos/img/logopm.PNG\" alt='header' data-default=\"placeholder\" data-max-width='560' width='560' height='242' border=\"0\" />                          </div>                        </div>                      </td>                    </tr>                    <tr>                      <td class='bgItem'>                        <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">                          <tr><td height='38'></td></tr>                          <tr>                            <td class=\"left_pad\">                              <div class=\"contentEditableContainer contentTextEditable\">                                <div class=\"contentEditable\">                                  <h2 style='font-size:38px;text-align:center; line-height:42px;'>Sistema de Gestión de Pueblos Mágicos</h2>                                </div>                              </div>                            </td>                          </tr>                          <tr><td height='20'></td></tr>                          <tr><td height='5' align='center'><hr style='width:117px; height:5px; background-color:#d2176e;border: none;'/></td></tr>                          <tr><td height='20'></td></tr>                          <tr>                            <td class=\"left_pad\">                              <div class=\"contentEditableContainer contentTextEditable\">                                <div class=\"contentEditable\" style='font-size:16px;color:#555555;text-align:center;line-height:24px;font-style: italic;'>                                  <p >"
				+ "Su registro se ha completado. Ahora puede iniciar sesión en el " 
				+ "</p>"+ "<a href=\"http://148.204.86.18:8080/pueblosMagicos/\">Sistema de Gestión de Pueblos Mágicos</a>"   +"                             </div>                              </div>                                              </td>                          </tr>                          <tr><td height='38'></td></tr>                          <tr><td height='10' bgcolor=\"#ffffff\"></td></tr>                        </table>                      </td>                    </tr>                  </table>        </div>                              </div>        <div class=\"movableContent\" style=\"border: 0px; padding-top: 0px; position: relative;\">        <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">              <tr><td height='10'></td></tr>              <tr><td><div style='border-top:1px solid #555555;'></div></td></tr>              <tr><td height='20'></td></tr>              <tr>                <td align='center'>                  <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">                    <tr>                      <td width=\"100%\" align=\"center\">                        <div class=\"contentEditableContainer contentTextEditable\">                          <div class=\"contentEditable\" style='color:#555555;text-align:center;font-size:13px;line-height:19px;'>                            <p>Sistema de Gestión de Pueblos Mágicos                            </p>                          </div>                        </div>                      </td>                    </tr>                  </table>                </td>              </tr>            </table>        </div>      </td>    </tr>  </tbody></table></td>    </tr>    <tr>    	<td height='20'>&nbsp;</td>    </tr>  </tbody></table>  </body>  </html>";
		mail.sendMail(u.getCorreo(), "Registro completado", message);
			
			
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		}catch(Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
	@RequestMapping(value = "/turista", method = RequestMethod.DELETE,  produces="application/json")
	public ResponseEntity<?> deleteUsuario( @PathVariable Integer idUsuario) 
	{
		ResponseEntity<?> result = null;
		
		if(idUsuario == null )
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		
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
	
	
	@RequestMapping(value = "/turistaEdit", method = RequestMethod.POST,  produces="application/json")
	public ResponseEntity<?> updateTurista(	@FormParam("idUsuario") Integer idUsuario,
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
		
		if(idUsuario == null || nombreUsuario == null || correo == null || genero == null )
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		
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
	
	
	@RequestMapping(value = "/turista/registrarVisita", method = RequestMethod.POST,  produces="application/json")
	public ResponseEntity<?> setVisita(	@FormParam("idUsuario")Integer idUsuario,
											@FormParam("idEstablecimiento")Integer idEstablecimiento,
											@FormParam("idPuebloMagico")Integer idPuebloMagico,
											@FormParam("idAtractivoTuristico")Integer idAtractivoTuristico,
											@FormParam("fecha")String fecha,
											@FormParam("idServicioTuristico")Integer idServicioTuristico)
	{
		ResponseEntity<?> result = null;
		
		Registrovisita r = new 	Registrovisita();	
		Boolean respuesta = false;
		
		if(idUsuario == null || fecha == null || fecha.isEmpty() )
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		log.debug("idAtractivo " + idAtractivoTuristico);
		try
		{
			r.setEIdEstablecimiento(idEstablecimiento);	
			r.setAtIdAtractivoTuristico(idAtractivoTuristico);
			r.setPmIdPuebloMagico(idPuebloMagico);
			r.setTIdUsuario(idUsuario);
			r.setEvaluado(0);
			r.setStIdServicioTuristico(idServicioTuristico);
			
			if(fecha != null && !fecha.isEmpty())
			{
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
				if(fecha.equals(formatoDelTexto.format(formatoDelTexto.parse(fecha))))
				{
					r.setFecha( formatoDelTexto.parse(fecha) );
					System.out.println("asigna valor fecha nacimiento");
				}else
				{
					System.out.println("formato fecha incorrecto " + fecha);
				}
				
			}
			respuesta = registrovisitaDAO.create(r);
			
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		}catch(Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
	@RequestMapping(value = "/turista/{idUsuario}/listaServiciosTuristios", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getListaServicioTuristicoByIdTurista(@PathVariable Integer idUsuario) 
	{
		ResponseEntity<?> result = null;
		List<ListaHasServicioturisticoId> lista= null;
		try {

			lista = listaHasServicioturisticoDAO.findByIdUsuario(idUsuario);
			result = new ResponseEntity<List<ListaHasServicioturisticoId>>(lista, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value = "/turista/setListaServicioTuristico", method = RequestMethod.POST,  produces="application/json")
	public ResponseEntity<?> setListaServicioTuristico(	@FormParam("idUsuario")Integer idUsuario,
											@FormParam("idServicioTuristico")Integer idServicioTuristico)
	{
		ResponseEntity<?> result = null;
		ListaHasServicioturisticoId id= new ListaHasServicioturisticoId();
		ListaHasServicioturistico l = new ListaHasServicioturistico();
		Boolean respuesta = false;
		
		if(idUsuario == null || idServicioTuristico == null )
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		
		try
		{
			id.setServicioTuristicoIdServicio(idServicioTuristico);
			id.setTuristaIdUsuario(idUsuario);
			
			l.setId(id);
			
			
			respuesta = listaHasServicioturisticoDAO.create(l);
			
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		}catch(Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
	@RequestMapping(value = "/turista/listaServicioTuristico", method = RequestMethod.DELETE,  produces="application/json")
	public ResponseEntity<?> unsetListaServicioTuristico(	@FormParam("idUsuario")Integer idUsuario,
											@FormParam("idServicioTuristico")Integer idServicioTuristico)
	{
		ResponseEntity<?> result = null;
		ListaHasServicioturisticoId id= new ListaHasServicioturisticoId();
		ListaHasServicioturistico l = new ListaHasServicioturistico();
		Boolean respuesta = false;
		
		if(idUsuario == null || idServicioTuristico == null )
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		
		try
		{
			id.setServicioTuristicoIdServicio(idServicioTuristico);
			id.setTuristaIdUsuario(idUsuario);
			
			l.setId(id);
			
			
			respuesta = listaHasServicioturisticoDAO.delete(l);
			
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		}catch(Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
	@RequestMapping(value = "/turista/{idUsuario}/listaAractivosTuristicos", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getListaAtractivoTuristicoByIdTurista(@PathVariable Integer idUsuario) 
	{
		ResponseEntity<?> result = null;
		List<ListaHasAtractivoturisticoId> lista= null;
		try {

			lista = listaHasAtractivoturisticoDAO.findByIdUsuario(idUsuario);
			result = new ResponseEntity<List<ListaHasAtractivoturisticoId>>(lista, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value = "/turista/setListaAtractivoTuristico", method = RequestMethod.POST,  produces="application/json")
	public ResponseEntity<?> setListaAtractivoTuristico(	@FormParam("idUsuario")Integer idUsuario,
											@FormParam("idAtractivoTuristico")Integer idAtractivoTuristico)
	{
		ResponseEntity<?> result = null;
		ListaHasAtractivoturisticoId id= new ListaHasAtractivoturisticoId();
		ListaHasAtractivoturistico l = new ListaHasAtractivoturistico();
		Boolean respuesta = false;
		
		if(idUsuario == null || idAtractivoTuristico == null )
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		
		try
		{
			id.setAtractivoTuristicoIdAtractivoTuristico(idAtractivoTuristico);
			id.setTuristaIdUsuario(idUsuario);
			
			l.setId(id);
			
			
			respuesta = listaHasAtractivoturisticoDAO.create(l);
			
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		}catch(Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
	@RequestMapping(value = "/turista/listaAtractivoTuristico", method = RequestMethod.DELETE,  produces="application/json")
	public ResponseEntity<?> unsetListaAtractivoTuristico(	@FormParam("idUsuario")Integer idUsuario,
											@FormParam("idAtractivoTuristico")Integer idAtractivoTuristico)
	{
		ResponseEntity<?> result = null;
		ListaHasAtractivoturisticoId id= new ListaHasAtractivoturisticoId();
		ListaHasAtractivoturistico l = new ListaHasAtractivoturistico();
		Boolean respuesta = false;
		
		if(idUsuario == null || idAtractivoTuristico == null )
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		
		try
		{
			id.setAtractivoTuristicoIdAtractivoTuristico(idAtractivoTuristico);
			id.setTuristaIdUsuario(idUsuario);
			
			l.setId(id);
			
			
			respuesta = listaHasAtractivoturisticoDAO.delete(l);
			
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		}catch(Exception e)
		{
			e.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
}
