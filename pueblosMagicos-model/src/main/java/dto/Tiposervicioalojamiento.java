package dto;
// Generated 29/09/2016 02:45:38 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Tiposervicioalojamiento generated by hbm2java
 */
@Entity
@Table(name = "tiposervicioalojamiento", catalog = "pueblosMagicos")
public class Tiposervicioalojamiento implements java.io.Serializable
{

	private Integer idTipoServicioAlojamiento;
	private String nombre;

	public Tiposervicioalojamiento()
	{
	}

	public Tiposervicioalojamiento(String nombre)
	{
		this.nombre = nombre;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idTipoServicioAlojamiento", unique = true, nullable = false)
	public Integer getIdTipoServicioAlojamiento( )
	{
		return this.idTipoServicioAlojamiento;
	}

	public void setIdTipoServicioAlojamiento( Integer idTipoServicioAlojamiento )
	{
		this.idTipoServicioAlojamiento = idTipoServicioAlojamiento;
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