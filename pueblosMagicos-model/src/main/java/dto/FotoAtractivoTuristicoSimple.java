package dto;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fotoatractivoturistico", catalog = "pueblosMagicos")
public class FotoAtractivoTuristicoSimple
{
	private Integer idfotoAtractivoTuristico;
	private String descripcion;

	public FotoAtractivoTuristicoSimple()
	{
	}


	public FotoAtractivoTuristicoSimple(String urlfoto, String descripcion, Integer pmIdPuebloMagico)
	{
		this.descripcion = descripcion;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idfotoAtractivoTuristico", unique = true, nullable = false)
	public Integer getIdfotoAtractivoTuristico( )
	{
		return this.idfotoAtractivoTuristico;
	}

	public void setIdfotoAtractivoTuristico( Integer idfotoAtractivoTuristico )
	{
		this.idfotoAtractivoTuristico = idfotoAtractivoTuristico;
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