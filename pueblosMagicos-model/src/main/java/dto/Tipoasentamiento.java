package dto;
// Generated 29/09/2016 02:45:38 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Tipoasentamiento generated by hbm2java
 */
@Entity
@Table(name = "tipoasentamiento", catalog = "pueblosMagicos")
public class Tipoasentamiento implements java.io.Serializable
{

	private Integer idtipoAsentamiento;
	private String nombre;

	public Tipoasentamiento()
	{
	}

	public Tipoasentamiento(String nombre)
	{
		this.nombre = nombre;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idtipoAsentamiento", unique = true, nullable = false)
	public Integer getIdtipoAsentamiento( )
	{
		return this.idtipoAsentamiento;
	}

	public void setIdtipoAsentamiento( Integer idtipoAsentamiento )
	{
		this.idtipoAsentamiento = idtipoAsentamiento;
	}

	@Column(name = "nombre", length = 45)
	public String getNombre( )
	{
		return this.nombre;
	}

	public void setNombre( String nombre )
	{
		this.nombre = nombre;
	}

}