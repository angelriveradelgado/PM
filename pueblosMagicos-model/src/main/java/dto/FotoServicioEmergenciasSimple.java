package dto;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fotoServicioEmergencias", catalog = "pueblosMagicos")
public class FotoServicioEmergenciasSimple
{
	private Integer idfotoServicioEmergencias;
	private String descripcion;

	public FotoServicioEmergenciasSimple()
	{
	}


	public FotoServicioEmergenciasSimple(String urlfoto, String descripcion, int pmIdPuebloMagico)
	{
		this.descripcion = descripcion;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idfotoPuebloMagico", unique = true, nullable = false)
	public Integer getIdfotoServicioEmergencias( )
	{
		return this.idfotoServicioEmergencias;
	}

	public void setIdfotoServicioEmergencias( Integer idfotoServicioEmergencias )
	{
		this.idfotoServicioEmergencias = idfotoServicioEmergencias;
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