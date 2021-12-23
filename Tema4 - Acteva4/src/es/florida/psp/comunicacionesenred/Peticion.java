// Lo que hara la peticion sera a traves de un metodo denominado encriptar sera,
// que cuando el servidor a traves de el hilo, llamara al metodo run,
// que tendra que pasar una contraseña vacia al cliente, este enviarle al servidor dicha contraseña 
// y entonces el servidor a traves de este metodo encriptara la contraseña y se la enviara al cliente.

package es.florida.psp.comunicacionesenred;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Peticion implements Runnable 
{
	BufferedReader br;
	PrintWriter pw;
	Socket socket;
	
	// El metodo peticion lo que hara sera llamar a la peticion que hara el servidor a traves del hilo.
	public Peticion(Socket socket) 
	{	
		this.socket = socket;
	}
	
	// El metodo encriptarContraseña lo que hara sera a traves de una contraseña, esta contraseña se pasara a un array vacio
	// y este array se recorra con la contraseña que ha enviado el cliente y lo devolvera como un string, con la contraseña encriptada.
	public String encriptarContraseña(String contraseña) 
	{
		char ascii[] = contraseña.toCharArray();
		
		for(int i = 0; i < ascii.length; i++) 
		{
			if(ascii[i] >= 0 && ascii[i] <= 31) 
			{
				ascii[i] = '*';
			}
			else 
			{
				ascii[i] = (char) (ascii[i] + (char)1);
			}
		}
		return String.valueOf(ascii);
	}

	// El metodo run lo que hara sera enviar una contraseña vacia al cliente, luego el servidor recibe la contraseña por parte del cliente,
	// la encriptara con el metodo de encriptaContraseña y le devolvera al cliente la contraseña encriptada.
	
	@Override
	public void run() 
	{
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			Contraseña contraseña = new Contraseña("Contraseña","Contraseña encriptada.");
			oos.writeObject(contraseña);
			System.out.println("El servidor le envia al cliente la contraseña: " + contraseña.getContraseña() + " y la contraseña encriptada " 
			+ contraseña.getContraseñaencriptada());
			
			System.err.println("El servidor recibe la contraseña del cliente.");
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			Contraseña c = (Contraseña) ois.readObject();
			
			System.err.println("El servidor realiza la encriptacion de la contraseña.");
			String contraseñaencriptada = encriptarContraseña(c.getContraseña());
			c.setContraseñaencriptada(contraseñaencriptada);
			
			System.err.println("El servidor devuelve la contraseña encriptada al cliente: " + contraseñaencriptada);
			oos.writeObject(c);
			
			System.err.println("El servidor espera una nueva peticion.");
			
			oos.close();
			ois.close();
			socket.close();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
}