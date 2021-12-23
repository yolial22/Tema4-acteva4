// El cliente lo que hara principalmente sera conectarse a un servidor y recibir un objeto contraseña vacio
// y despues le enviara al servidor la contraseña que el cliente ha escrito y por ultimo,
// recibira esa contraseña pero encriptada.

package es.florida.psp.comunicacionesenred;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente 
{
	public static void main(String[] args) throws IOException, ClassNotFoundException 
	{
		Scanner teclado = new Scanner(System.in);
		
		String host = "localhost";
		
		int puerto = 1234;
		
		System.out.println("El cliente espera ha recibir una peticion.");
		System.out.println("El cliente se ha conectado al servidor con exito.");
		
		InetSocketAddress direccion = new InetSocketAddress(host, puerto);
		
		Socket socket = new Socket();
		socket.connect(direccion);
		
		System.out.println("El cliente esta preparado para recibir lo que le envia el servidor.");
		
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		Contraseña c = (Contraseña) ois.readObject();
		System.out.println("El cliente recibe del servidor la contraseña: " + c.getContraseña());
		c.setContraseña("Yosu");
		
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(c);
		System.out.println("El cliente le envia al servidor la contraseña: " + c.getContraseña());
		
		c = (Contraseña) ois.readObject();
		System.out.println("El cliente recibe el resultado del servidor: " + c.getContraseñaencriptada());
		
		ois.close();
		oos.close();
		socket.close();
	}
}