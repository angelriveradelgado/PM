package dto;
// Generated 30/08/2016 02:54:23 PM by Hibernate Tools 5.1.0.Beta1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Servicioturisticoaventura generated by hbm2java
 */
@Entity
@Table(name = "servicioturisticoaventura", catalog = "pueblosMagicos")
public class Servicioturisticoaventura implements java.io.Serializable {

	private int stIdServicio;

	public Servicioturisticoaventura() {
	}

	public Servicioturisticoaventura(int stIdServicio) {
		this.stIdServicio = stIdServicio;
	}

	@Id

	@Column(name = "sT_idServicio", unique = true, nullable = false)
	public int getStIdServicio() {
		return this.stIdServicio;
	}

	public void setStIdServicio(int stIdServicio) {
		this.stIdServicio = stIdServicio;
	}

}