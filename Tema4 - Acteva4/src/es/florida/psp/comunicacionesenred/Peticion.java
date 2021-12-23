// Lo que hara la peticion sera a traves de un metodo denominado encriptar sera,
// que cuando el servidor a traves de el hilo, llamara al metodo run,
// que tendra que pasar una contrase�a vacia al cliente, este enviarle al servidor dicha contrase�a 
// y entonces el servidor a traves de este metodo encriptara la contrase�a y se la enviara al cliente.

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
	
	// El metodo encriptarContrase�a lo que hara sera a traves de una contrase�a, esta contrase�a se pasara a un array vacio
	// y este array se recorra con la contrase�a que ha enviado el cliente y lo devolvera como un string, con la contrase�a encriptada.
	public String encriptarContrase�a(String contrase�a) 
	{
		char ascii[] = contrase�a.toCharArray();
		
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

	// El metodo run lo que hara sera enviar una contrase�a vacia al cliente, luego el servidor recibe la contrase�a por parte del cliente,
	// la encriptara con el metodo de encriptaContrase�a y le devolvera al cliente la contrase�a encriptada.
	
	@Override
	public void run() 
	{
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			Contrase�a contrase�a = new Contrase�a("Contrase�a","Contrase�a encriptada.");
			oos.writeObject(contrase�a);
			System.out.println("El servidor le envia al cliente la contrase�a: " + contrase�a.getContrase�a() + " y la contrase�a encriptada " 
			+ contrase�a.getContrase�aencriptada());
			
			System.err.println("El servidor recibe la contrase�a del cliente.");
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			Contrase�a c = (Contrase�a) ois.readObject();
			
			System.err.println("El servidor realiza la encriptacion de la contrase�a.");
			String contrase�aencriptada = encriptarContrase�a(c.getContrase�a());
			c.setContrase�aencriptada(contrase�aencriptada);
			
			System.err.println("El servidor devuelve la contrase�a encriptada al cliente: " + contrase�aencriptada);
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