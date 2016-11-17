package dto;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fotopueblomagico", catalog = "pueblosMagicos")
public class FotoPuebloMagicoSimple implements java.io.Serializable
{
	private Integer idfotoPuebloMagico;
	private String descripcion;

	public FotoPuebloMagicoSimple()
	{
	}


	public FotoPuebloMagicoSimple(String urlfoto, String descripcion, Integer pmIdPuebloMagico)
	{
		this.descripcion = descripcion;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idfotoPuebloMagico", unique = true, nullable = false)
	public Integer getIdfotoPuebloMagico( )
	{
		return this.idfotoPuebloMagico;
	}

	public void setIdfotoPuebloMagico( Integer idfotoPuebloMagico )
	{
		this.idfotoPuebloMagico = idfotoPuebloMagico;
	}

	@Column(name = "descripcion", length = 65535)
	public String getDescripcion( )
	{
		return this.descripcion;
	}

	public void setDescripcion( String descripcion )
	{
		this.descripcion = descripcion;
	}

}
