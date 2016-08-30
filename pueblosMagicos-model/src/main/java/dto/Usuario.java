package dto;
// Generated 30/08/2016 02:54:23 PM by Hibernate Tools 5.1.0.Beta1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Usuario generated by hbm2java
 */
@Entity
@Table(name = "usuario", catalog = "pueblosMagicos")
public class Usuario implements java.io.Serializable {

	private Integer idUsuario;
	private String nombreUsuario;
	private String contrasena;
	private String urlfotografia;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String correo;
	private int tipoUsuarioIdtipoUsuario;

	public Usuario() {
	}

	public Usuario(String nombreUsuario, String contrasena, String correo, int tipoUsuarioIdtipoUsuario) {
		this.nombreUsuario = nombreUsuario;
		this.contrasena = contrasena;
		this.correo = correo;
		this.tipoUsuarioIdtipoUsuario = tipoUsuarioIdtipoUsuario;
	}

	public Usuario(String nombreUsuario, String contrasena, String urlfotografia, String nombre, String apellidoPaterno,
			String apellidoMaterno, String correo, int tipoUsuarioIdtipoUsuario) {
		this.nombreUsuario = nombreUsuario;
		this.contrasena = contrasena;
		this.urlfotografia = urlfotografia;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.correo = correo;
		this.tipoUsuarioIdtipoUsuario = tipoUsuarioIdtipoUsuario;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idUsuario", unique = true, nullable = false)
	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Column(name = "nombreUsuario", nullable = false, length = 30)
	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	@Column(name = "contrasena", nullable = false, length = 20)
	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	@Column(name = "URLfotografia")
	public String getUrlfotografia() {
		return this.urlfotografia;
	}

	public void setUrlfotografia(String urlfotografia) {
		this.urlfotografia = urlfotografia;
	}

	@Column(name = "nombre", length = 45)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "apellidoPaterno", length = 45)
	public String getApellidoPaterno() {
		return this.apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	@Column(name = "apellidoMaterno", length = 45)
	public String getApellidoMaterno() {
		return this.apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	@Column(name = "correo", nullable = false, length = 45)
	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Column(name = "tipoUsuario_idtipoUsuario", nullable = false)
	public int getTipoUsuarioIdtipoUsuario() {
		return this.tipoUsuarioIdtipoUsuario;
	}

	public void setTipoUsuarioIdtipoUsuario(int tipoUsuarioIdtipoUsuario) {
		this.tipoUsuarioIdtipoUsuario = tipoUsuarioIdtipoUsuario;
	}

}