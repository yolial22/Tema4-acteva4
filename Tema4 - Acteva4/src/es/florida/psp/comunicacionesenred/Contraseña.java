// El objecto contraseña sera el objeto que se pasaran el servidor y el cliente para comunicarse,
// a traves de este objeto.

package es.florida.psp.comunicacionesenred;

import java.io.Serializable;

public class Contraseña implements Serializable
{
	String contraseña = "";
	String contraseñaencriptada;
	
	public Contraseña(String contraseña, String contraseñaencriptada) 
	{
		this.contraseña = contraseña;
		this.contraseñaencriptada = contraseñaencriptada;
	}
	
	public Contraseña() 
	{
		super();
	}

	public String getContraseña() 
	{
		return contraseña;
	}

	public void setContraseña(String contraseña) 
	{
		this.contraseña = contraseña;
	}

	public String getContraseñaencriptada() 
	{
		return contraseñaencriptada;
	}

	public void setContraseñaencriptada(String contraseñaencriptada) 
	{
		this.contraseñaencriptada = contraseñaencriptada;
	}
}