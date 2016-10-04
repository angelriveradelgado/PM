package dto;
// Generated 29/09/2016 02:45:38 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TipoatractivoHasTipoturismo generated by hbm2java
 */
@Entity
@Table(name = "tipoatractivo_has_tipoturismo", catalog = "pueblosMagicos")
public class TipoatractivoHasTipoturismo implements java.io.Serializable
{

	private TipoatractivoHasTipoturismoId id;

	public TipoatractivoHasTipoturismo()
	{
	}

	public TipoatractivoHasTipoturismo(TipoatractivoHasTipoturismoId id)
	{
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "taIdtipoAtractivo", column = @Column(name = "tA_idtipoAtractivo", nullable = false)),
			@AttributeOverride(name = "ttIdtipoTurismo", column = @Column(name = "tT_idtipoTurismo", nullable = false)) })
	public TipoatractivoHasTipoturismoId getId( )
	{
		return this.id;
	}

	public void setId( TipoatractivoHasTipoturismoId id )
	{
		this.id = id;
	}

}