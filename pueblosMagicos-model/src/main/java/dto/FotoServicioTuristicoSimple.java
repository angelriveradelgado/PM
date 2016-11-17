package dto;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fotoservicioturistico", catalog = "pueblosMagicos")
public class FotoServicioTuristicoSimple
{
	private Integer idfotoServicioTuristico;
	private String descripcion;

	public FotoServicioTuristicoSimple()
	{
	}


	public FotoServicioTuristicoSimple(String urlfoto, String descripcion, Integer pmIdPuebloMagico)
	{
		this.descripcion = descripcion;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idfotoPuebloMagico", unique = true, nullable = false)
	public Integer getIdfotoServicioTuristico( )
	{
		return this.idfotoServicioTuristico;
	}

	public void setIdfotoServicioTuristico( Integer idfotoPuebloMagico )
	{
		this.idfotoServicioTuristico = idfotoPuebloMagico;
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