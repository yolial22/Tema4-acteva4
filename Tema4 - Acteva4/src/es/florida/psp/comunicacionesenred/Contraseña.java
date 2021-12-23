// El objecto contrase�a sera el objeto que se pasaran el servidor y el cliente para comunicarse,
// a traves de este objeto.

package es.florida.psp.comunicacionesenred;

import java.io.Serializable;

public class Contrase�a implements Serializable
{
	String contrase�a = "";
	String contrase�aencriptada;
	
	public Contrase�a(String contrase�a, String contrase�aencriptada) 
	{
		this.contrase�a = contrase�a;
		this.contrase�aencriptada = contrase�aencriptada;
	}
	
	public Contrase�a() 
	{
		super();
	}

	public String getContrase�a() 
	{
		return contrase�a;
	}

	public void setContrase�a(String contrase�a) 
	{
		this.contrase�a = contrase�a;
	}

	public String getContrase�aencriptada() 
	{
		return contrase�aencriptada;
	}

	public void setContrase�aencriptada(String contrase�aencriptada) 
	{
		this.contrase�aencriptada = contrase�aencriptada;
	}
}