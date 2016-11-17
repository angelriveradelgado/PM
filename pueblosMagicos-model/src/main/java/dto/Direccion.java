package dto;

public class Direccion implements java.io.Serializable
{

	private String direccion;

	public Direccion()
	{
	}


	public String  getDireccion( )
	{
		return this.direccion;
	}

	public void setDireccion( String direccion )
	{
		this.direccion = direccion;
	}

}

