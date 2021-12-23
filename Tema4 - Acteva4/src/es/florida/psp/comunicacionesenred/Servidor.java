// El servidor lo que hara sera que ha traves de un hilo, recibir un objeto contraseña,
// encriptara esa contraseña y se la devolvera al cliente.

package es.florida.psp.comunicacionesenred;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor 
{
	public static void main(String[] args) throws IOException, ClassNotFoundException 
	{
		// TODO Auto-generated method stub
		
		int numpuerto = 1234;
		
		System.err.println("El servidor espera una peticion.");
		ServerSocket socketEscucha = null;
		
		try 
		{
			socketEscucha = new ServerSocket(numpuerto);
		} catch (IOException e) 
		{
			System.err.println("Error en una peticion al servidor.");
			return;
		}
		while (true) 
		{
			Socket conexioncliente = socketEscucha.accept();
			System.err.println("El servidor ha establecido la conexion con exito y lanzara el hilo de la clase peticion.");
			
			Peticion p = new Peticion(conexioncliente);
			
			Thread t = new Thread(p);
				
			t.start();
		}
	}
}
