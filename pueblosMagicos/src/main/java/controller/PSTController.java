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

import common.Mail;
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
	public ResponseEntity<?> getPST( @PathVariable Integer id )
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

	// WS que devuelve un PST por su estado
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
	
	// WS que devuelve una lista de PST por su id de estado de registro y limit
	@RequestMapping(value = "/pst/estadoRegistro/{idEstadoRegistro}/limit/{first}/{numReg}", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<?> getPSTByIdEstadoRegistroByLimit( @PathVariable Integer idEstadoRegistro,
			@PathVariable Integer first,
			@PathVariable Integer numReg )
	{
		ResponseEntity<?> result = null;
		List<Pst> p = null;
		try
		{
			p = pstDAO.readAllPSTByIdEstadoRegistroByLimit(idEstadoRegistro, first, numReg);
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
		
		if( nombreUsuario == null || contrasena == null || nombre == null || correo == null || numeroRNT == null)
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		
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
			Mail mail = new Mail(); 
			String message = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\"><head>  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />  <title>[SUBJECT]</title>  <style type=\"text/css\">  body {   padding-top: 0 !important;   padding-bottom: 0 !important;   padding-top: 0 !important;   padding-bottom: 0 !important;   margin:0 !important;   width: 100% !important;   -webkit-text-size-adjust: 100% !important;   -ms-text-size-adjust: 100% !important;   -webkit-font-smoothing: antialiased !important; } .tableContent img {   border: 0 !important;   display: block !important;   outline: none !important; } a{  color:#382F2E;}p, h1,h2,h3,ul,ol,li,div{  margin:0;  padding:0;}h1,h2{  font-weight: normal;  background:transparent !important;  border:none !important;}.contentEditable h2.big{  font-size: 30px !important;} .contentEditable h2.bigger{  font-size: 37px !important;}td,table{  vertical-align: top;}td.middle{  vertical-align: middle;}a.link1{  font-size:14px;  color:#D4D4D4;  text-decoration:none;  font-family: Helvetica Neue;}.link2{font-size:16px;color:#d2176e;text-decoration:none;line-height:24px;font-family: Helvetica;font-weight: bold;}.link3{padding:5px 10px;border-radius: 6px;background-color: #d2176e;font-size:13px;color:#f2f2f2;text-decoration:none;line-height:26px;font-family: Helvetica;font-weight: bold;}.contentEditable li{  margin-top:10px;  margin-bottom:10px;  list-style: none;  color:#ffffff;  text-align:center;  font-size:13px;  line-height:19px;}.appart p{  font-size:13px;  line-height:19px;  color:#aaaaaa !important;}h2{  color:#555555;  font-weight: normal;  font-size:28px;  color:#555555;  font-family: Georgia;  line-height: 28px;  font-style: italic;}.bgItem{background:#f2f2f2;}.bgBody{background:#ffffff;}@media only screen and (max-width:480px)		{		table[class=\"MainContainer\"], td[class=\"cell\"] 	{		width: 100% !important;		height:auto !important; 	}td[class=\"specbundle\"] 	{		width: 80% !important;		float:left !important;		font-size:13px !important;		line-height:17px !important;		display:block !important;		padding-bottom:15px !important;		text-align:center !important;		padding-left:20% !important;	}td[class=\"specbundle2\"] 	{		width:85% !important;		float:left !important;		font-size:13px !important;		line-height:17px !important;		display:block !important;		padding-left:10% !important;		padding-right:5% !important;		padding-bottom:10px !important;	}		td[class=\"specbundle3\"] 	{		width:20px !important;		float:left !important;		display:block !important;		background-color:#f2f2f2 !important;	}	td[class=\"specbundle4\"] 	{		width:90% !important;		float:left !important;		font-size:13px !important;		line-height:17px !important;		display:block !important;		padding-left:10% !important;		padding-right:5% !important;		padding-left:5% !important;	}		td[class=\"spechide\"] 	{		display:none !important;	}	    img[class=\"banner\"] 	{	          width: 100% !important;	          height: auto !important;	}		td[class=\"left_pad\"] 	{			padding-left:15px !important;			padding-right:15px !important;	}		 }	@media only screen and (max-width:540px) {		table[class=\"MainContainer\"], td[class=\"cell\"] 	{		width: 100% !important;		height:auto !important; 	}td[class=\"specbundle\"] 	{		width: 80% !important;		float:left !important;		font-size:13px !important;		line-height:17px !important;		display:block !important;		padding-bottom:15px !important;		text-align:center !important;		padding-left:20% !important;	}	td[class=\"specbundle2\"] 	{		width:85% !important;		float:left !important;		font-size:13px !important;		line-height:17px !important;		display:block !important;		padding-left:10% !important;		padding-right:5% !important;		padding-bottom:10px !important;	}		td[class=\"specbundle3\"] 	{		width:20px !important;		float:left !important;		display:block !important;		background-color:#f2f2f2 !important;	}	td[class=\"specbundle4\"] 	{		width:90% !important;		float:left !important;		font-size:13px !important;		line-height:17px !important;		display:block !important;		padding-left:10% !important;		padding-right:5% !important;		padding-left:5% !important;	}		td[class=\"spechide\"] 	{		display:none !important;	}	    img[class=\"banner\"] 	{	          width: 100% !important;	          height: auto !important;	}		td[class=\"left_pad\"] 	{			padding-left:15px !important;			padding-right:15px !important;	}	.font{		font-size:15px !important;		line-height:19px !important;		}		}</style><script type=\"colorScheme\" class=\"swatch active\">  {    \"name\":\"Default\",    \"bgBody\":\"ffffff\",    \"link\":\"d2176e\",    \"color\":\"555555\",    \"bgItem\":\"f2f2f2\",    \"title\":\"555555\"  }</script></head><body paddingwidth=\"0\" paddingheight=\"0\" bgcolor=\"#d1d3d4\"  style=\"padding-top: 0; padding-bottom: 0; padding-top: 0; padding-bottom: 0; background-repeat: repeat; width: 100% !important; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; -webkit-font-smoothing: antialiased;\" offset=\"0\" toppadding=\"0\" leftpadding=\"0\">	<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"tableContent bgBody\" align=\"center\" bgcolor=\"#FFFFFF\" style='font-family:Georgia, serif;'>    <!-- =============== START HEADER =============== -->  <tbody>    <tr>      <td><table width=\"560\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" class=\"MainContainer\">      <!-- =============== END HEADER =============== -->          <!-- =============== START BODY =============== -->  <tbody>    <tr>      <td class='movableContentContainer'>      	<div class=\"movableContent\" style=\"border: 0px; padding-top: 0px; position: relative;\">        <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">  <tbody>    <tr>      <td height=\"10\"></td>    </tr>    <tr>      <td><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">  <tbody>    <tr>      <td valign=\"top\" class=\"specbundle4\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">  <tbody>    <tr>      <td valign=\"top\" width=\"89\" class=\"specbundle4\" align=\"center\"><img src=\"http://148.204.86.18:8080/pueblosMagicos/img/icons/pm.jpg\" alt=\"Logo\" title=\"Logo\" width=\"89\" height=\"88\" data-max-width=\"100\"></td>      <td width=\"40\" valign=\"top\" class=\"spechide\">&nbsp;</td>      <td valign=\"middle\" class=\"specbundle4\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">  <tbody>    <tr>      <td height=\"25\" class=\"spechide\"></td>    </tr>    <tr>      <td class=\"font\"><div class=\"contentEditableContainer contentTextEditable\">                          <div class=\"contentEditable\" align='left' style='color:#555555;font-size:18px; padding-top:5px;'>                            <h1 style='line-height: 22px;font-size:18px;'>                              TT2015-B100                            </h1>                          </div>                        </div></td>    </tr>    <tr>      <td>&nbsp;</td>    </tr>  </tbody></table></td>    </tr>  </tbody></table></td>      <td valign=\"top\" width=\"20\" class=\"spechide\">&nbsp;</td>      <td valign=\"middle\" style='vertical-align: middle;' width='150' class=\"left_pad\">                          <div class='contentEditableContainer contentTextEditable'>                            <div class='contentEditable' style='text-align: right;'>                              <a target='_blank' href=\"http://148.204.86.18:8080/pueblosMagicos/\" class='link1' >Sitio Web</a>                            </div>                          </div>                        </td>    </tr>  </tbody></table></td>    </tr>    <tr>      <td height=\"10\"></td>    </tr>  </tbody></table>        </div>        <div class=\"movableContent\" style=\"border: 0px; padding-top: 0px; position: relative;\">        <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">                    <tr>                      <td>                        <div class=\"contentEditableContainer contentImageEditable\">                          <div class=\"contentEditable\" align='left'>                            <img class=\"banner\" src=\"http://148.204.86.18:8080/pueblosMagicos/img/logopm.PNG\" alt='header' data-default=\"placeholder\" data-max-width='560' width='560' height='242' border=\"0\" />                          </div>                        </div>                      </td>                    </tr>                    <tr>                      <td class='bgItem'>                        <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">                          <tr><td height='38'></td></tr>                          <tr>                            <td class=\"left_pad\">                              <div class=\"contentEditableContainer contentTextEditable\">                                <div class=\"contentEditable\">                                  <h2 style='font-size:38px;text-align:center; line-height:42px;'>Sistema de Gestión de Pueblos Mágicos</h2>                                </div>                              </div>                            </td>                          </tr>                          <tr><td height='20'></td></tr>                          <tr><td height='5' align='center'><hr style='width:117px; height:5px; background-color:#d2176e;border: none;'/></td></tr>                          <tr><td height='20'></td></tr>                          <tr>                            <td class=\"left_pad\">                              <div class=\"contentEditableContainer contentTextEditable\">                                <div class=\"contentEditable\" style='font-size:16px;color:#555555;text-align:center;line-height:24px;font-style: italic;'>                                  <p >"
				+ "Su registro se ha completado. Ahora puede iniciar sesión en el " 
				+ "</p>"+ "<a href=\"http://148.204.86.18:8080/pueblosMagicos/\">Sistema de Gestión de Pueblos Mágicos</a>"   +"                             </div>                              </div>                                              </td>                          </tr>                          <tr><td height='38'></td></tr>                          <tr><td height='10' bgcolor=\"#ffffff\"></td></tr>                        </table>                      </td>                    </tr>                  </table>        </div>                              </div>        <div class=\"movableContent\" style=\"border: 0px; padding-top: 0px; position: relative;\">        <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">              <tr><td height='10'></td></tr>              <tr><td><div style='border-top:1px solid #555555;'></div></td></tr>              <tr><td height='20'></td></tr>              <tr>                <td align='center'>                  <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">                    <tr>                      <td width=\"100%\" align=\"center\">                        <div class=\"contentEditableContainer contentTextEditable\">                          <div class=\"contentEditable\" style='color:#555555;text-align:center;font-size:13px;line-height:19px;'>                            <p>Sistema de Gestión de Pueblos Mágicos                            </p>                          </div>                        </div>                      </td>                    </tr>                  </table>                </td>              </tr>            </table>        </div>      </td>    </tr>  </tbody></table></td>    </tr>    <tr>    	<td height='20'>&nbsp;</td>    </tr>  </tbody></table>  </body>  </html>";
		mail.sendMail(u.getCorreo(), "Registro completado", message);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		} catch (Exception e2)
		{
			e2.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return result;
	}
	
	// WS que recibe un pst y lo inserta en la bd
	@RequestMapping(value = "/PSTEdit", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> updatePST( @FormParam("idUsuario") Integer idUsuario,
			@FormParam("erIdEstadoRegistro") Integer erIdEstadoRegistro,
			@FormParam("numeroRNT") String numeroRNT, @FormParam("telefono") String telefono,
			@FormParam("razonSocialEmpresa") String razonSocialEmpresa )
	{
		ResponseEntity<?> result = null;
		
		if( erIdEstadoRegistro == null )
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		
		Pst pst = new Pst();
		Boolean respuesta = false;

		try
		{

			pst.setErIdEstadoRegistro(erIdEstadoRegistro);
			pst.setIdUsuario(idUsuario);
			pst.setNumeroRnt(numeroRNT);
			pst.setTelefono(telefono);
			pst.setRazonSocialEmpresa(razonSocialEmpresa);

			respuesta = pstDAO.update(pst);
			result = new ResponseEntity<Boolean>(respuesta, HttpStatus.OK);
		} catch (Exception e2)
		{
			e2.printStackTrace();
			result = new ResponseEntity<String>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return result;
	}

	// WS que recibe un pst y lo inserta en la bd
	@RequestMapping(value = "/usuarioPSTEdit", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> updateUsuarioPST( @FormParam("idUsuario") Integer idUsuario,
			@FormParam("nombreUsuario") String nombreUsuario, @FormParam("contrasena") String contrasena,
			@FormParam("nombre") String nombre, @FormParam("apellidoPaterno") String apellidoPaterno,
			@FormParam("apellidoMaterno") String apellidoMaterno, @FormParam("correo") String correo,
			@FormParam("numeroRNT") String numeroRNT, @FormParam("telefono") String telefono,
			@FormParam("razonSocialEmpresa") String razonSocialEmpresa,@FormParam("erIdEstadoRegistro") Integer erIdEstadoRegistro )
	{
		ResponseEntity<?> result = null;
		
		if( nombreUsuario == null || contrasena == null || nombre == null || correo == null )
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		
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

			pst.setErIdEstadoRegistro(erIdEstadoRegistro);
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
	@RequestMapping(value = "/pst/{idUsuario}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<?> deletePST( @PathVariable Integer idUsuario)
	{
		ResponseEntity<?> result = null;
		
		if( idUsuario == null )
		{
			result = new ResponseEntity<String>("Datos invalidos", HttpStatus.BAD_REQUEST);
			return result;
		}
		
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
