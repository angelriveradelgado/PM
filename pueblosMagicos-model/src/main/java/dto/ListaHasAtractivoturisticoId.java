package dto;
// Generated 29/09/2016 02:45:38 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ListaHasAtractivoturisticoId generated by hbm2java
 */
@Embeddable
public class ListaHasAtractivoturisticoId implements java.io.Serializable
{
	private int atractivoTuristicoIdAtractivoTuristico;
	private int turistaIdUsuario;

	public ListaHasAtractivoturisticoId()
	{
	}

	public ListaHasAtractivoturisticoId(int atractivoTuristicoIdAtractivoTuristico,
			int turistaIdUsuario)
	{
		this.atractivoTuristicoIdAtractivoTuristico = atractivoTuristicoIdAtractivoTuristico;
		this.turistaIdUsuario = turistaIdUsuario;
	}


	@Column(name = "AtractivoTuristico_idAtractivoTuristico", nullable = false)
	public int getAtractivoTuristicoIdAtractivoTuristico( )
	{
		return this.atractivoTuristicoIdAtractivoTuristico;
	}

	public void setAtractivoTuristicoIdAtractivoTuristico( int atractivoTuristicoIdAtractivoTuristico )
	{
		this.atractivoTuristicoIdAtractivoTuristico = atractivoTuristicoIdAtractivoTuristico;
	}

	@Column(name = "Turista_idUsuario", nullable = false)
	public int getTuristaIdUsuario( )
	{
		return this.turistaIdUsuario;
	}

	public void setTuristaIdUsuario( int turistaIdUsuario )
	{
		this.turistaIdUsuario = turistaIdUsuario;
	}

	public boolean equals( Object other )
	{
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ListaHasAtractivoturisticoId))
			return false;
		ListaHasAtractivoturisticoId castOther = (ListaHasAtractivoturisticoId) other;

		return (this.getAtractivoTuristicoIdAtractivoTuristico() == castOther
						.getAtractivoTuristicoIdAtractivoTuristico())
				&& (this.getTuristaIdUsuario() == castOther.getTuristaIdUsuario());
	}

	public int hashCode( )
	{
		int result = 17;

		result = 37 * result + this.getAtractivoTuristicoIdAtractivoTuristico();
		result = 37 * result + this.getTuristaIdUsuario();
		return result;
	}

}
